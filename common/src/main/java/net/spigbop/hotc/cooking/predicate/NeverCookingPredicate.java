package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NeverCookingPredicate
    extends CookingPredicate
{
    public static final NeverCookingPredicate INSTANCE =
        new NeverCookingPredicate();
    public static final MapCodec<NeverCookingPredicate> CODEC =
        MapCodec.unit(INSTANCE);

    protected NeverCookingPredicate() {
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level _level, ItemStack... _items) {
        return false;
    }
}
