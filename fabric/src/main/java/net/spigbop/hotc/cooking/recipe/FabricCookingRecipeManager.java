package net.spigbop.hotc.cooking.recipe;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.spigbop.hotc.Constants;

public class FabricCookingRecipeManager extends CookingRecipeManager
    implements IdentifiableResourceReloadListener
{
    public static final FabricCookingRecipeManager FABRIC_INSTANCE = new FabricCookingRecipeManager();

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,
        DIRECTORY_NAME
    );

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }

    static {
        INSTANCE = FABRIC_INSTANCE;
    }
}
