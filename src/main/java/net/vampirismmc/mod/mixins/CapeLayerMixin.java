package net.vampirismmc.mod.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.vampirismmc.mod.VampirismMCEnhanced;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo info) {
        if (VampirismMCEnhanced.connected()) {
            ItemStack playerChestplate = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
            if (playerChestplate != ItemStack.EMPTY) {
                info.cancel();
            }
        }
    }
}
