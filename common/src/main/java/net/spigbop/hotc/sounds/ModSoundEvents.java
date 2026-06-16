package net.spigbop.hotc.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.spigbop.hotc.Constants;

public class ModSoundEvents {
    public static final SoundEvent CROCK_POT_OPEN =
        SoundEvent.createVariableRangeEvent(
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID,
            "block.crock_pot.open"
        ));
    public static final SoundEvent CROCK_POT_CLOSE =
        SoundEvent.createVariableRangeEvent(
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID,
            "block.crock_pot.close"
        ));
    public static final SoundEvent CROCK_POT_FINISH =
        SoundEvent.createVariableRangeEvent(
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID,
            "block.crock_pot.finish"
        ));
    public static final SoundEvent CROCK_POT_RATTLE =
        SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "block.crock_pot.rattle"
            ));
    public static final SoundEvent CROCK_POT_BOIL =
        SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "block.crock_pot.boil"
            ));

    public static final SoundEvent MANDRAKE_BREAK =
        SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "block.mandrake.break"
            ));
}
