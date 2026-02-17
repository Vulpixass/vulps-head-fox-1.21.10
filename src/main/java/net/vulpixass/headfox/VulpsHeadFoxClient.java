package net.vulpixass.headfox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EntityType;
import net.vulpixass.headfox.client.HeadFoxModelLayers;
import net.vulpixass.headfox.client.model.fox_baby;
import net.vulpixass.headfox.client.render.FoxHatFeatureRenderer;

public class VulpsHeadFoxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HeadFoxModelLayers.FOX_LAYER, fox_baby::getTexturedModelData );
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, registrationHelper, ctx) -> {
            if (entityType == EntityType.PLAYER && renderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new FoxHatFeatureRenderer(playerRenderer));
            }
        });
        System.out.println("Fox: I have to add this print line so I can upload the 1.21.10 version of the mod to modrinth");
    }
}
