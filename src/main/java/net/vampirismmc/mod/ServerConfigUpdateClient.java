package net.vampirismmc.mod;

import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ServerConfigUpdateClient {
    private static boolean waitingForAck = false;

    public static void sendUpdate(ModConfigServer config) {
        var buf = PacketByteBufs.create();

        // Protocol version
        buf.writeInt(1);

        // Config
        try {
            var data = ModConfigServer.CODEC.encodeStart(JsonOps.INSTANCE, config)
                    .getOrThrow(false, VampirismMCEnhanced.logger()::warn).toString();
            buf.writeUtf(data);
        } catch (RuntimeException e) {
            VampirismMCEnhanced.logger().warn("Failed to send server config update to server: ", e);
        }

        ClientPlayNetworking.send(new ResourceLocation("vampirismmc","server_config_update"), buf);
        waitingForAck = true;
    }

    public static void updateAcknowledged(FriendlyByteBuf buf) {
        if (waitingForAck) {
            waitingForAck = false;

            try {
                var protocolVersion = buf.readInt();
                if (protocolVersion != 1) {
                    VampirismMCEnhanced.logger().warn("Received config update ack with unknown protocol version: {}, will attempt to read anyway", protocolVersion);
                }

                var success = buf.readBoolean();
                if (success) {
//                    ToastUtil.toasty("server_config_updated");
                    VampirismMCEnhanced.logger().info("Updated config!");
                } else {
//                    ToastUtil.toasty("server_config_update_failed");
                    VampirismMCEnhanced.logger().info("Failed to update config!");
                }
            } catch (RuntimeException e) {
                VampirismMCEnhanced.logger().warn("Failed to read config update ack from server: ", e);
            }
        }
    }
}