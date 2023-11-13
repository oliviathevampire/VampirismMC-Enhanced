package net.vampirismmc.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.vampirismmc.mod.feat.config.VampirismMCEnhancedConfig;
import net.vampirismmc.mod.feat.discord.DiscordManager;
import net.vampirismmc.mod.feat.keyboard.KeyboardManager;
import net.vampirismmc.mod.feat.lifecycle.Lifecycle;
import net.vampirismmc.mod.feat.lifecycle.Task;
import net.vampirismmc.mod.feat.location.Location;
import net.vampirismmc.mod.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Environment(EnvType.CLIENT)
public class VampirismMCEnhanced implements ClientModInitializer {
    public static final HandshakeClient<LimitedModConfigServer, ModConfigServer> HANDSHAKE_CLIENT = new HandshakeClient<>(
            ModConfigServer.CODEC,
            LimitedModConfigServer.getCodec(),
            ClientEvents::updateServerConfig
    );

    private static final Logger LOGGER = LogManager.getLogger(Constants.MOD_NAME);
    public static final ArrayList<String> debugUsers = new ArrayList<>();

    public static DiscordManager DISCORD_MANAGER;

    private static final VampirismMCEnhancedConfig CONFIG = new VampirismMCEnhancedConfig();
    private static Location LOCATION = Location.UNKNOWN;
    private static Lifecycle LIFECYCLE;

    private static final Queue<Component> TOAST_STACK = new LinkedList<>();

    @Override
    public void onInitializeClient() {
        // Initalize debug users (USE UUID)
        debugUsers.add("OliviaTheVampire"); // OliviaTheVampire
        debugUsers.add("RalgragDemon"); // OliviaTheVampire

        // Managers and services that need to be retained after
        // initialization, and/or be accessed by other services, should
        // be initialized here.
        LIFECYCLE = new Lifecycle();

        // Managers and services that do not need to be retained after
        // initialization should be initialized here.
        new KeyboardManager();

        if (config().discordRPC) DISCORD_MANAGER = new DiscordManager().start();


        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            ClientPlayNetworking.registerReceiver(new ResourceLocation("vampirismmc","handshake"), (client1, handler1, buf, responseSender) -> {

            });
        });

        // Lifecycle tasks should be initialized here.
        lifecycle()
                .add(Task.of(Location::check, 20))
                .add(Task.of(() -> {
                    if (DiscordManager.active) DISCORD_MANAGER.update();
                    if (DiscordManager.active && !VampirismMCEnhanced.config().discordRPC) DISCORD_MANAGER.stop();
                    if (!DiscordManager.active && VampirismMCEnhanced.config().discordRPC) DISCORD_MANAGER.start();

                }, 10));

    }

    public static boolean connected() {
        if (Minecraft.getInstance().getCurrentServer() != null) {
            String ip = Minecraft.getInstance().getCurrentServer().ip;
            return ip.endsWith("play.vampirismmc.net") || ip.endsWith("dev.vampirismmc.net") || ip.endsWith("localhost");
        } else return false;
    }

    public static String version() {
        return String.valueOf(
            FabricLoader.getInstance().getModContainer(Constants.MOD_ID).get().getMetadata().getVersion()
        );
    }

    public static Logger logger() { return LOGGER; }

    public static boolean debug() { return debugUsers.contains(Minecraft.getInstance().getUser().getUuid()); }

    public static Minecraft client() { return Minecraft.getInstance(); }

    public static LocalPlayer player() { return client().player; }

    public static Queue<Component> toastStack() { return TOAST_STACK; }

    public static VampirismMCEnhancedConfig config() { return CONFIG; }

    public static DiscordManager discord() { return DISCORD_MANAGER; }

    public static Lifecycle lifecycle() { return LIFECYCLE; }

    public static Location location() { return LOCATION; }

    public static void setLocation(Location l) { LOCATION = l; }
}