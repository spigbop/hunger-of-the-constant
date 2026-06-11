package net.spigbop.hotc.block.entity;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.spigbop.hotc.block.CrockPotBlock;
import net.spigbop.hotc.cooking.predicate.AlwaysCookingPredicate;
import net.spigbop.hotc.cooking.recipe.CookingRecipe;
import net.spigbop.hotc.menu.CrockPotMenu;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCrockPotBlockEntity
    extends BaseContainerBlockEntity implements WorldlyContainer
{
    private static final float BASE_COOK_TIME_TICKS = 400.0f;

    private int openCount;

    public void startOpen(Player player) {
        if (!player.isSpectator()) {
            if (openCount < 0) {
                openCount = 0;
            }
            openCount++;
            level.playSound(
                null,
                getBlockPos(),
                SoundEvents.BARREL_OPEN,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
            );
        }
    }

    public void stopOpen(Player player) {
        if (!player.isSpectator()) {
            openCount--;
            if (openCount <= 0) {
                level.playSound(
                    null,
                    getBlockPos(),
                    SoundEvents.BARREL_CLOSE,
                    SoundSource.BLOCKS,
                    1.0f,
                    1.0f
                );
            }
        }
    }

    private int cookTicks = 0;
    private CookingRecipe recipe = null;
    private NonNullList<ItemStack> items = NonNullList.withSize(
        5,
        ItemStack.EMPTY
    );

    protected AbstractCrockPotBlockEntity(
        BlockEntityType<?> type,
        BlockPos pos,
        BlockState blockState
    ) {
        super(type, pos, blockState);
    }

    public int[] getSlotsForFace(Direction direction) {
        return switch (direction) {
            case UP -> new int[]{0, 1, 2, 3}; // input slots from top
            case DOWN -> new int[]{4};        // output slot from bottom
            default -> new int[]{0, 1, 2, 3}; // input slots from sides
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(
        int i,
        ItemStack itemStack,
        @Nullable Direction direction
    ) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(
        int i,
        ItemStack itemStack,
        Direction direction
    ) {
        return true;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.hotc.crock_pot");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (isCooking()) {
            return;
        }
        super.setItem(slot, stack);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new CrockPotMenu(i, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean canOpen(Player player) {
        return !isCooking();
    }

    protected void cookTick() {
        if (!this.isCooking()) {
            return;
        }

        int totalTicks = (int) (BASE_COOK_TIME_TICKS * recipe.time());
        if (totalTicks - cookTicks <= 0) {
            finishCook();
        }

        cookTicks += 1;
    }

    public boolean isCooking() {
        return this.recipe != null;
    }

    public void finishCook() {
        ItemStack[] ingredients = new ItemStack[4];
        for (int i = 0;
             i < 4;
             i++
        ) {
            items.set(i, ItemStack.EMPTY);
        }

        this.items.set(4, CrockPotBlock.finishCook(recipe));
        this.recipe = null;
        this.cookTicks = 0;

        setChanged();
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }

        this.getLevel().playSound(
            null,
            this.getBlockPos(),
            SoundEvents.ARROW_HIT,
            SoundSource.BLOCKS
        );
    }

    public boolean tryStartCook() {
        for (int i = 0;
             i < 4;
             i++
        ) {
            if (items.get(i).isEmpty()) {
                return false;
            }
        }
        if (!items.get(4).isEmpty()) {
            return false;
        }

        ItemStack[] ingredients = new ItemStack[4];
        for (int i = 0;
             i < 4;
             i++
        ) {
            ingredients[i] = items.get(i);
            items.set(i, ItemStack.EMPTY);
        }
        this.recipe = CrockPotBlock.startCook(this.getLevel(), ingredients);

        if (level instanceof ServerLevel) {
            List<ServerPlayer> viewers = ((ServerLevel) level)
                .players()
                .stream()
                .filter(p -> p.containerMenu instanceof CrockPotMenu menu &&
                             menu.getContainer() == this)
                .toList();
            viewers.forEach(ServerPlayer::closeContainer);
        }

        setChanged();
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }

        this.getLevel().playSound(
            null,
            this.getBlockPos(),
            SoundEvents.ARROW_SHOOT,
            SoundSource.BLOCKS
        );

        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("cook_ticks", cookTicks);
        tag.putBoolean("is_cooking", isCooking());
        if (recipe != null) {
            tag.put("result", recipe.result().copy().save(registries));
            tag.putFloat("recipe_time", recipe.time());
        }

        ContainerHelper.saveAllItems(tag, items, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        cookTicks = tag.getInt("cook_ticks");
        ContainerHelper.loadAllItems(tag, items, registries);
        // restore recipe if was cooking
        if (tag.getBoolean("is_cooking")) {
            if (tag.contains("result")) {
                ItemStack result = ItemStack.parse(registries, tag.getCompound("result")).orElse(ItemStack.EMPTY);
                float time = tag.getFloat("recipe_time");
                this.recipe = new CookingRecipe(10, List.of(
                    AlwaysCookingPredicate.INSTANCE), time, result);
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("is_cooking", isCooking());
        tag.putInt("cook_ticks", cookTicks);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
//        CompoundTag tag = pkt.getTag();
//        if (tag != null) {
//            cookTicks = tag.getInt("cook_ticks");
//            // client only needs to know if cooking for animation
//            boolean wasCooking = isCooking();
//            // set a client-side cooking flag
//        }
//    }
}
