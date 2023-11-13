package net.vampirismmc.mod.mixins;

import net.vampirismmc.mod.VampirismMCEnhanced;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

    @Redirect(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z",
                    ordinal = 5)
    )
    private boolean renderCustomHead(PlayerRenderer instance, RenderLayer renderLayer) {
        return (!VampirismMCEnhanced.config().hideCosmetics && !VampirismMCEnhanced.connected()) || !VampirismMCEnhanced.connected();
    }

}
