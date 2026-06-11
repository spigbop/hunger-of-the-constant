package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NotCookingPredicate<T extends CookingPredicate>
    extends CookingPredicate
{
    public static final MapCodec<NotCookingPredicate> CODEC = RecordCodecBuilder.mapCodec(
        i -> i
            .group(CookingPredicate.CODEC
                .fieldOf("predicate")
                .forGetter(p -> p.predicate))
            .apply(i, NotCookingPredicate::new));

    private final T predicate;

    public NotCookingPredicate(T predicate) {
        this.predicate = predicate;
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level level, ItemStack... items) {
        return !predicate.test(level, items);
    }
}
