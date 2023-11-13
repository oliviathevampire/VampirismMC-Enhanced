package net.vampirismmc.mod.mixins;

import net.vampirismmc.mod.VampirismMCEnhanced;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementsScreen.class)
public class AdvancementScreenMixin {

    /**
     * @author Skye Redwood
     * @reason Advancements break Hideaway ew
     */
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (VampirismMCEnhanced.connected()) {
            ci.cancel();
        }
    }

}
