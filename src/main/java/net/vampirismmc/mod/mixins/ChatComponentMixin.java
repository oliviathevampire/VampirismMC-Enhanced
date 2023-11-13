package net.vampirismmc.mod.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.*;
import net.vampirismmc.mod.VampirismMCEnhanced;
import net.vampirismmc.mod.util.Chars;
import net.vampirismmc.mod.util.DisplayNameUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {
    @ModifyVariable(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;ILnet/minecraft/client/GuiMessageTag;Z)V", argsOnly = true)
    private Component addMessageIcons(Component message) {
        MutableComponent newMessage = MutableComponent.create(ComponentContents.EMPTY);
        String username = message.getString().substring(2, message.getString().indexOf(">>"));
        String playerID = DisplayNameUtil.modPlayerID(username);

        if (VampirismMCEnhanced.debugUsers.contains(username)) newMessage.append(Chars.badge());
        if (VampirismMCEnhanced.debugUsers.contains(username)) newMessage.append(Chars.devBadge());
        if (VampirismMCEnhanced.debugUsers.contains(username))
            newMessage.append(Chars.friendBadge())
                            .withStyle(Style.EMPTY.withHoverEvent(
                                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("")
                                            .append(Component.translatable("vmce.tooltip.friend")
                                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)))
                                    )
                            ));
        newMessage.append(message);
        return newMessage;
    }
}