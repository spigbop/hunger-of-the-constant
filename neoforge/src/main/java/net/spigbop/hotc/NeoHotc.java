package net.spigbop.hotc;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.spigbop.hotc.registry.NeoRegistry;

@Mod(Constants.MOD_ID)
public class NeoHotc {
    public NeoHotc(IEventBus eventBus) {
        Hotc.init();
        NeoRegistry.register(eventBus);
    }
}