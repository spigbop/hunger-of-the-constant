package net.spigbop.hotc.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.spigbop.hotc.sounds.ModSoundEvents;

public class ModSoundType {
    public static final SoundType MANDRAKE = new SoundType(
        1.0F,
        1.0F,
        ModSoundEvents.MANDRAKE_BREAK,
        SoundEvents.GRASS_STEP,
        SoundEvents.BONE_MEAL_USE,
        SoundEvents.GRASS_HIT,
        SoundEvents.GRASS_FALL
    );
}
