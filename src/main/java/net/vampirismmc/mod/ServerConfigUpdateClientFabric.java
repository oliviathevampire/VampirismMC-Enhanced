package net.vampirismmc.mod;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ServerConfigUpdateClientFabric {
    public static void startListening() {
        ClientPlayNetworking.registerReceiver(new ResourceLocation("vampirismmc","server_config_update"), (client, handler, buf, responseSender) -> {
            ServerConfigUpdateClient.updateAcknowledged(buf);
            client.player.sendSystemMessage(Component.literal("Testing aaaaaaaaaaaa"));
        });
    }
}