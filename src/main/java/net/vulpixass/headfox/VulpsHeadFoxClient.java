package net.vulpixass.headfox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundEvents;
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
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            FoxHatFeatureRenderer.randomSoundTimer++;
            int timer = FoxHatFeatureRenderer.randomSoundTimer;
            if(timer >= 12000){
                int sound = (int) (Math.random() * 2);
                switch (sound) {
                    case 0: MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_FOX_SLEEP, 1.0f));
                    case 1: MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_FOX_AMBIENT, 1.0f));
                    case 2: MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_FOX_SNIFF, 1.0f));
                    case 3: MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_FOX_AMBIENT, 1.0f));
                }
            }
        });
        System.out.println("Fox: I have to add this print line so I can upload the 1.21.10 version of the mod to modrinth");
    }
}
