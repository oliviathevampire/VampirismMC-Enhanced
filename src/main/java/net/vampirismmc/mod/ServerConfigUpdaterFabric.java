package net.vampirismmc.mod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class ServerConfigUpdaterFabric {
    public static void startListening(ServerGamePacketListenerImpl handler) {
        ServerPlayNetworking.registerReceiver(handler, new ResourceLocation("vampirismmc","server_config_update"), (server, player, handler1, buf, responseSender) -> {
            /*responseSender.sendPacket(
                    new ResourceLocation("vampirismmc","server_config_update"),
                    DoABarrelRoll.CONFIG_HOLDER.clientSendsUpdate(player, buf)
            );*/
            player.sendSystemMessage(Component.literal("Testing"));
        });
    }
}