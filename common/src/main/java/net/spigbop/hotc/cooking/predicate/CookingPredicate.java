package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class CookingPredicate {
    public static final Codec<CookingPredicate> CODEC = CookingPredicateTypes.REGISTRY
        .byNameCodec()
        .dispatch(CookingPredicate::codec, mapCodec -> mapCodec);

    public abstract MapCodec<? extends CookingPredicate> codec();

    public abstract boolean test(Level level, ItemStack... items);
}
