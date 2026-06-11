package net.spigbop.hotc.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.cooking.recipe.CookingRecipeManager;

public class CookDebugCommand {
    public static void register(
        CommandDispatcher<CommandSourceStack> dispatcher,
        CommandBuildContext context
    ) {
        dispatcher.register(Commands
            .literal("cook")
            .requires(source -> source.hasPermission(2))
            .then(Commands
                .argument("item1", ItemArgument.item(context))
                .then(Commands
                    .argument("item2", ItemArgument.item(context))
                    .then(Commands
                        .argument("item3", ItemArgument.item(context))
                        .then(Commands
                            .argument("item4", ItemArgument.item(context))
                            .executes(CookDebugCommand::cook))))));
    }

    private static int cook(CommandContext<CommandSourceStack> ctx)
        throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        Level level = source.getLevel();

        Constants.LOG.info("Recipe manager has {} recipes",
            CookingRecipeManager.INSTANCE.all().size());

        ItemStack[] items = {
            ItemArgument.getItem(ctx, "item1").createItemStack(1, false),
            ItemArgument.getItem(ctx, "item2").createItemStack(1, false),
            ItemArgument.getItem(ctx, "item3").createItemStack(1, false),
            ItemArgument.getItem(ctx, "item4").createItemStack(1, false),
            };

        CookingRecipeManager.INSTANCE
            .findMatch(level, level.getRandom(), items)
            .ifPresentOrElse(
                recipe -> {
                    ItemStack result = recipe.result().copy();
                    source.sendSuccess(
                        () -> Component.literal("Result: " +
                                                result
                                                    .getItem()
                                                    .getDescriptionId() +
                                                " x" +
                                                result.getCount()), false
                    );
                },
                () -> source.sendFailure(Component.literal(
                    "No matching recipe found."))
            );

        return Command.SINGLE_SUCCESS;
    }
}