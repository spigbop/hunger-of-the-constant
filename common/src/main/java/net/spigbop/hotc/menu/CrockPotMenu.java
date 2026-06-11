package net.spigbop.hotc.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.block.entity.AbstractCrockPotBlockEntity;
import net.spigbop.hotc.cooking.category.IngredientCategoryManager;

public class CrockPotMenu extends AbstractContainerMenu {
    private final Container container;

    private class InputSlot extends Slot {
        private Level level;

        public InputSlot(
            Container container,
            int slot,
            int x,
            int y,
            Level level
        ) {
            super(container, slot, x, y);
            this.level = level;
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return IngredientCategoryManager.INSTANCE.isInAnyCategory(
                level.registryAccess(),
                stack
            );
        }
    }

    public CrockPotMenu(
        int containerId,
        Inventory playerInventory,
        Container container
    ) {
        super(ModMenuTypes.CROCK_POT, containerId);
        this.container = container;
        checkContainerSize(container, 5); // 4 input + 1 output

        // Input slots
        Level level = playerInventory.player.level();
        addSlot(new InputSlot(container, 0, 18, 31, level));
        addSlot(new InputSlot(container, 1, 36, 31, level));
        addSlot(new InputSlot(container, 2, 54, 31, level));
        addSlot(new InputSlot(container, 3, 72, 31, level));

        // Output slot
        addSlot(new Slot(container, 4, 134, 31) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; // output only
            }
        });

        // Player inventory
        for (int row = 0;
             row < 3;
             row++
        ) {
            for (int col = 0;
                 col < 9;
                 col++
            ) {
                addSlot(new Slot(
                    playerInventory,
                    col + row * 9 + 9,
                    8 + col * 18,
                    84 + row * 18
                ));
            }
        }

        // Player hotbar
        for (int col = 0;
             col < 9;
             col++
        ) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        if (container instanceof AbstractCrockPotBlockEntity be) {
            be.startOpen(playerInventory.player);
        }
    }

    public CrockPotMenu(
        int containerId,
        Inventory playerInventory,
        FriendlyByteBuf buf
    ) {
        this(containerId, playerInventory, new SimpleContainer(5));
    }

    public CrockPotMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(5));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (container instanceof AbstractCrockPotBlockEntity be) {
            be.stopOpen(player);
        }
    }

    public Container getContainer() {
        return container;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();

            if (index == 4) {
                // Output -> player inventory
                if (!moveItemStackTo(stack, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 4) {
                // Input -> player inventory
                if (!moveItemStackTo(stack, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Player inventory -> input slots
                if (!moveItemStackTo(stack, 0, 4, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack);
        }

        return result;
    }
}
