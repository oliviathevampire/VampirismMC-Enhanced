package net.vampirismmc.mod.feat.keyboard;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.commands.arguments.ArgumentSignatures;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import net.vampirismmc.mod.VampirismMCEnhanced;
import org.lwjgl.glfw.GLFW;

import java.time.Instant;
import java.util.BitSet;

public class KeyboardManager {
    public KeyboardManager() {
        var cosmetics = new KeyMapping("key.vmce.cosmetics", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, "categories.vmce");
        var evamp = new KeyMapping("key.vmce.evamp", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, "categories.vmce");
        var profile = new KeyMapping("key.vmce.profile", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, "categories.vmce");
        var friends = new KeyMapping("key.vmce.friends", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, "categories.vmce");
        var mail = new KeyMapping("key.vmce.mail", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, "categories.vmce");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (friends.consumeClick()) {
                LastSeenMessages.Update messages = new LastSeenMessages.Update(0, new BitSet());
                Instant now = Instant.now();
                VampirismMCEnhanced.client().player.connection.send(new ServerboundChatCommandPacket(
                        "friends",
                        now,
                        0L,
                        ArgumentSignatures.EMPTY,
                        messages
                ));
            }
            while (profile.consumeClick()) {
                LastSeenMessages.Update messages = new LastSeenMessages.Update(0, new BitSet());
                Instant now = Instant.now();
                VampirismMCEnhanced.client().player.connection.send(new ServerboundChatCommandPacket(
                        "profile",
                        now,
                        0L,
                        ArgumentSignatures.EMPTY,
                        messages
                ));
            }
            while (friends.consumeClick()) {
                LastSeenMessages.Update messages = new LastSeenMessages.Update(0, new BitSet());
                Instant now = Instant.now();
                VampirismMCEnhanced.client().player.connection.send(new ServerboundChatCommandPacket(
                        "friends",
                        now,
                        0L,
                        ArgumentSignatures.EMPTY,
                        messages
                ));
            }
            while (mail.consumeClick()) {
                LastSeenMessages.Update messages = new LastSeenMessages.Update(0, new BitSet());
                Instant now = Instant.now();
                VampirismMCEnhanced.client().player.connection.send(new ServerboundChatCommandPacket(
                    "mailbox",
                    now,
                    0L,
                    ArgumentSignatures.EMPTY,
                    messages
                ));
            }
//            while (explore.consumeClick()) {
//                InventorySlotsUI.clickSlot(44, client);
//            }
        });

        KeyBindingHelper.registerKeyBinding(profile);
        KeyBindingHelper.registerKeyBinding(friends);
        KeyBindingHelper.registerKeyBinding(mail);
    }
}
