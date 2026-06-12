package net.spigbop.hotc.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class EffectGivingFoodItem extends Item {
    public EffectGivingFoodItem(
        Properties properties
    ) {
        super(properties);
    }

    public static Component formatEffect(
        MobEffectInstance instance,
        float probability
    ) {
        int amplifier = instance.getAmplifier();
        int ticks = instance.getDuration();
        int seconds = ticks / 20;
        String duration = String.format("%d:%02d", seconds / 60, seconds % 60);

        MutableComponent result = instance
            .getEffect()
            .value()
            .getDisplayName()
            .copy();

        if (amplifier > 0) {
            result
                .append(" ")
                .append(Component.translatable("enchantment.level." +
                                               (amplifier + 1)));
        }

        result.append(" ").append(Component.literal(duration));

        if (probability != 1.0) {
            result
                .append(" (")
                .append(Component.literal(String.valueOf(((int) Math.round((
                    probability *
                    100.0))))))
                .append("%)");
        }

        return result;
    }

    @Override
    public void appendHoverText(
        ItemStack stack,
        TooltipContext context,
        List<Component> tooltipComponents,
        TooltipFlag tooltipFlag
    ) {
        FoodProperties food = stack
            .getItem()
            .components()
            .get(DataComponents.FOOD);
        List<FoodProperties.PossibleEffect> effects = food.effects();
        if (!effects.isEmpty()) {
            tooltipComponents.add(Component
                .translatable("tooltip.hotc.effect_item")
                .withStyle(ChatFormatting.GRAY));
        }
        for (FoodProperties.PossibleEffect effect : effects) {
            tooltipComponents.add(formatEffect(
                effect.effect(),
                effect.probability()
            ));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
