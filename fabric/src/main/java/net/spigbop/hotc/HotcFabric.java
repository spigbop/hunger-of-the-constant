package net.spigbop.hotc;

import net.fabricmc.api.ModInitializer;
import net.spigbop.hotc.registry.FabricRegistry;

public class HotcFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Hotc.init();
        FabricRegistry.register();
    }
}
