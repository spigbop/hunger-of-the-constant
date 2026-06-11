package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AlwaysCookingPredicate
    extends CookingPredicate
{
    public static final AlwaysCookingPredicate INSTANCE =
        new AlwaysCookingPredicate();
    public static final MapCodec<AlwaysCookingPredicate> CODEC =
        MapCodec.unit(INSTANCE);

    protected AlwaysCookingPredicate() {
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level _level, ItemStack... _items) {
        return true;
    }
}
