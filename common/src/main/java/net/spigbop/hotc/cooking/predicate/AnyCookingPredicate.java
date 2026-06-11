package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AnyCookingPredicate extends CookingPredicate {
    public static final MapCodec<AnyCookingPredicate> CODEC = RecordCodecBuilder.mapCodec(
        i -> i
            .group(CookingPredicate.CODEC
                .listOf()
                .fieldOf("predicates")
                .forGetter(p -> p.predicates))
            .apply(i, AnyCookingPredicate::new));

    private final List<CookingPredicate> predicates;

    public AnyCookingPredicate(CookingPredicate... predicates) {
        this.predicates = List.of(predicates);
    }

    public AnyCookingPredicate(List<CookingPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level level, ItemStack... items) {
        for (CookingPredicate predicate : predicates) {
            if (predicate.test(level, items)) {
                return true;
            }
        }

        return false;
    }
}
