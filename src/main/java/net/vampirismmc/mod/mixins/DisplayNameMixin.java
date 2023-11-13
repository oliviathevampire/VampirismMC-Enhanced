package net.vampirismmc.mod.mixins;

import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.vampirismmc.mod.VampirismMCEnhanced;
import net.vampirismmc.mod.util.Chars;
import net.vampirismmc.mod.util.DisplayNameUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerTabOverlay.class)
public class DisplayNameMixin {
    @Inject(at = @At("RETURN"), method = "getNameForDisplay", cancellable = true)
    public void getDisplayName(PlayerInfo entry, CallbackInfoReturnable<Component> cir) {
        Component name = cir.getReturnValue();
        if (VampirismMCEnhanced.connected()){
            String result = DisplayNameUtil.ignFromDisplayName(name.getString());
            String playerID = DisplayNameUtil.modPlayerID(result);

            MutableComponent newName = MutableComponent.create(ComponentContents.EMPTY);
            newName.append(name);

            if (/*StaticValues.modUsers.containsValue(result) && !StaticValues.modDevelopers.contains(playerID)*/VampirismMCEnhanced.debugUsers.contains(playerID)) newName.append(" ").append(Chars.badge());
            if (/*StaticValues.modDevelopers.contains(playerID)*/VampirismMCEnhanced.debugUsers.contains(playerID)) newName.append(" ").append(Chars.devBadge());
            if (/*StaticValues.friendsList.contains(result)*/VampirismMCEnhanced.debugUsers.contains(playerID)) newName.append(" ").append(Chars.friendBadge());
            if (!newName.toString().equals(result)) cir.setReturnValue(newName);
        }
    }
}
