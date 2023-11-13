package net.vampirismmc.mod.feat.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import net.vampirismmc.mod.VampirismMCEnhanced;
import net.vampirismmc.mod.feat.location.Location;
import net.vampirismmc.mod.util.HUDUtil;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Objects;

public class DiscordManager {
    public static boolean active = false;
    private static IPCClient client;
    private static Instant start;

    public DiscordManager start() {
        if (!active && VampirismMCEnhanced.config().discordRPC) {
            VampirismMCEnhanced.logger().info("Starting Discord RPC client...");
            client = new IPCClient(1136888078510858323L);
            client.setListener(new IPCListener() {
                @Override
                public void onReady(IPCClient client) {
                    VampirismMCEnhanced.logger().info("Discord RPC client connected!");
                    active = true;
                    start = Instant.now();
                    update();
                }

                @Override
                public void onDisconnect(IPCClient client, Throwable t) {
                    active = false;
                }

                @Override
                public void onClose(IPCClient client, JSONObject j) {
                    active = false;
                }
            });

            try {
                client.connect();
            } catch (Exception ignored) {}
        }
        return this;
    }

    public void update() {
        if (active && VampirismMCEnhanced.config().discordRPC) {
            Location loc = VampirismMCEnhanced.location();
            RichPresence.Builder builder = new RichPresence.Builder();
            builder.setState(loc.description)
                    .setDetails(loc.name.contains("<player>") ? loc.name.replace("<player>", Objects.requireNonNull(HUDUtil.getCurrentRoomName())) : loc.name)
                    .setStartTimestamp(Instant.ofEpochSecond(start.toEpochMilli()).atOffset(ZoneOffset.UTC))
                    .setLargeImage(loc.largeIcon.key(), "VampirismMC: Enhanced v" + VampirismMCEnhanced.version())
                    .setSmallImage(loc.smallIcon.key(), "Nothing to see here...");
            client.sendRichPresence(builder.build());
        } else start();
    }

    public void stop() {
            client.sendRichPresence(null);
            active = false;
    }
}