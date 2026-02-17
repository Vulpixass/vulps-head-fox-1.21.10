package net.vulpixass.headfox.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class fox_baby extends EntityModel<EntityRenderState> {
	public final ModelPart root;
	public final ModelPart head;
	public final ModelPart bb_main;
	public fox_baby(ModelPart root) {
		super(root);
		this.root = root;
        this.head = root.getChild("head");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(20, 10).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.0F, -5.0F, 1.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F))
				.uv(22, 4).cuboid(1.0F, -7.0F, 2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(22, 7).cuboid(-3.0F, -7.0F, 2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 19.0F, -11.0F, 0.3492F, 0.4971F, 0.1719F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 10).cuboid(-2.0F, -9.0F, -7.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0, 0, 0));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, -1.0F, -0.4363F, 0.0F, 0.0F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(22, 0).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(20, 18).cuboid(-1.0F, -2.0F, 3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -6.0F, -6.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(20, 14).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(14, 20).cuboid(-1.0F, -2.0F, -5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -5.0F, -2.0F, 0.0F, 0.0F, 1.5708F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(EntityRenderState state) {}
}