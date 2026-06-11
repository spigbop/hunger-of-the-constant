package net.spigbop.hotc.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.spigbop.hotc.Constants;

public class NeoMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU,
        Constants.MOD_ID
    );

    public static final DeferredHolder<MenuType<?>, MenuType<CrockPotMenu>> CROCK_POT = REGISTER.register("crock_pot",
        () -> IMenuTypeExtension.create(CrockPotMenu::new)
    );
}
