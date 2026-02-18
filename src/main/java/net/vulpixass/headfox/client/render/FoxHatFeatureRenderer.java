package net.vulpixass.headfox.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.vulpixass.headfox.client.model.fox_baby;
import net.vulpixass.headfox.command.client.FoxTypesEnum;

import static net.vulpixass.headfox.command.client.ClientCommands.FoxType;

public class FoxHatFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long nextTwitchTime = 0;
    private float twitchAmount = 0;
    private final fox_baby foxModel;
    public static int randomSoundTimer = 0;

    private FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context;

    private static Identifier FOX_TEXTURE = Identifier.of("headfox", "textures/entity/regular_fox_baby.png");

    public FoxHatFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
        ModelPart part = fox_baby.getTexturedModelData().createModel();
        this.context = context;
        this.foxModel = new fox_baby(part);
        System.out.println("Fox model children: " + context.getModel().getParts());
        System.out.println("Fox texture: " + FOX_TEXTURE);

    }
    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity me = client.player;
        fox_baby fox = this.foxModel;
        PlayerEntity target = (PlayerEntity) client.world.getEntityById(state.id);
        switch (FoxType) {
            case FoxTypesEnum.REGULAR: FOX_TEXTURE = Identifier.of("headfox", "textures/entity/regular_fox_baby.png"); break;
            case FoxTypesEnum.SNOW: FOX_TEXTURE = Identifier.of("headfox", "textures/entity/snow_fox_baby.png"); break;
        }
        if (me == null) return;
        if (target == null || !target.getUuid().equals(me.getUuid())) {return;}
        ItemStack helmet = me.getEquippedStack(EquipmentSlot.HEAD);
        boolean hasHelmet = !helmet.isEmpty();
        matrices.push();
        this.getContextModel().head.applyTransform(matrices);
        matrices.translate(0.0F, -1.1F, 0.0F);
        matrices.scale(0.5F, 0.5F, 0.5F);
        long time = me.age;
        if (time >= nextTwitchTime) {
            twitchAmount = 0.3F;
            nextTwitchTime = (time + 20 + client.world.random.nextInt(10))*20;
        }
        twitchAmount *= 0.8F;
        float breathe = 1.0F + (float)(Math.sin(time * 0.1F) * 0.025F);
        foxModel.bb_main.xScale = breathe;
        foxModel.bb_main.yScale = breathe;
        foxModel.bb_main.zScale = breathe;
        float growth = (breathe - 1.0F);
        if (hasHelmet) {matrices.translate(0.0, -0.098, 0.0);}
        matrices.scale(breathe, breathe, breathe);
        matrices.translate(0.0F, -growth * 1.2F, 0.2F);

        foxModel.head.yaw = twitchAmount * (client.world.random.nextBoolean() ? 1 : -1);
        foxModel.head.pitch = twitchAmount * (client.world.random.nextBoolean() ? 1 : -1);
        float sway = (float)Math.sin(time * (2 * Math.PI / 200.0)) * 0.10F;
        sway = Math.max(-0.15F, Math.min(0.15F, sway));
        foxModel.head.roll = sway;

        //Render Fox
        fox.head.resetTransform();
        // client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_FOX, 1.0f));
        FeatureRenderer.render(foxModel, FOX_TEXTURE, matrices, queue, light, state, 0xFFFFFF, 0);
        matrices.pop();
    }
}
