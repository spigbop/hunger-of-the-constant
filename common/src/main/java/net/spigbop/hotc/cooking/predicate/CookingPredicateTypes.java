package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.spigbop.hotc.Constants;

public class CookingPredicateTypes {

    public static final ResourceKey<Registry<MapCodec<? extends CookingPredicate>>> REGISTRY_KEY = ResourceKey.createRegistryKey(
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID,
            "cooking_predicate"
        ));

    public static final Registry<MapCodec<? extends CookingPredicate>> REGISTRY = makeRegistry();

    public static final MapCodec<AlwaysCookingPredicate> ALWAYS = register("always",
        AlwaysCookingPredicate.CODEC
    );
    public static final MapCodec<AnyCookingPredicate> ANY = register(
        "any",
        AnyCookingPredicate.CODEC
    );
    public static final MapCodec<ContainsCookingPredicate> CONTAINS = register("contains",
        ContainsCookingPredicate.CODEC
    );
    public static final MapCodec<ContainsFillerCookingPredicate> CONTAINS_FILLER = register("contains_filler",
        ContainsFillerCookingPredicate.CODEC
    );
    public static final MapCodec<FillerAtLeastCookingPredicate> FILLER_AT_LEAST = register("filler_at_least",
        FillerAtLeastCookingPredicate.CODEC
    );
    public static final MapCodec<FillerAtMostCookingPredicate> FILLER_AT_MOST = register("filler_at_most",
        FillerAtMostCookingPredicate.CODEC
    );
    public static final MapCodec<FillerExactCookingPredicate> FILLER_EXACT = register("filler_exact",
        FillerExactCookingPredicate.CODEC
    );
    public static final MapCodec<FillerLessThanCookingPredicate> FILLER_LESS_THAN = register("filler_less_than",
        FillerLessThanCookingPredicate.CODEC
    );
    public static final MapCodec<FillerMoreThanCookingPredicate> FILLER_MORE_THAN = register("filler_more_than",
        FillerMoreThanCookingPredicate.CODEC
    );
    public static final MapCodec<NeverCookingPredicate> NEVER = register(
        "never",
        NeverCookingPredicate.CODEC
    );
    public static final MapCodec<NotCookingPredicate> NOT = register(
        "not",
        NotCookingPredicate.CODEC
    );

    private static Registry<MapCodec<? extends CookingPredicate>> makeRegistry() {
        return new MappedRegistry<>(REGISTRY_KEY, Lifecycle.stable());
    }

    private static <T extends CookingPredicate> MapCodec<T> register(
        String name,
        MapCodec<T> codec
    ) {
        Constants.LOG.info("Registering cooking predicate: " + name);
        return Registry.register(
            REGISTRY,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
            codec
        );
    }

    public static void init() {
    }
}
