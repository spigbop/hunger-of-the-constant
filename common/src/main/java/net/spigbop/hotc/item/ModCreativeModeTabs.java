package net.spigbop.hotc.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab HOTC = CreativeModeTab
        .builder(CreativeModeTab.Row.TOP, 0)
        .title(Component.translatable("itemGroup.hotc.Hotc"))
        .icon(() -> new ItemStack(ModItems.MANDRAKE))
        .displayItems((p, out) -> {
            out.accept(ModItems.MONSTER_MEAT);
            out.accept(ModItems.HONEY);
            out.accept(ModItems.MANDRAKE);

            out.accept(ModItems.CROCK_POT);

            out.accept(ModItems.WET_GOOP);
            out.accept(ModItems.MEATBALLS);
            out.accept(ModItems.HONEY_HAM);
            out.accept(ModItems.MONSTER_LASAGNA);
            out.accept(ModItems.RATATOUILLE);
            out.accept(ModItems.TRAIL_MIX);
            out.accept(ModItems.TAFFY);
            out.accept(ModItems.FIST_FULL_OF_JAM);
            out.accept(ModItems.MEATY_STEW);
            out.accept(ModItems.MANDRAKE_SOUP);
        })
        .build();
}
