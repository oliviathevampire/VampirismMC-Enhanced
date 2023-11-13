package net.vampirismmc.mod.feat.config;

import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;

import java.nio.file.Path;

public class VampirismMCEnhancedConfig {
    public static final ConfigInstance<VampirismMCEnhancedConfig> GSON = GsonConfigInstance.createBuilder(VampirismMCEnhancedConfig.class)
            .setPath(Path.of("./config/vampirismmc-enhanced.json"))
            .build();

    // General
    @ConfigEntry
    public boolean hideCosmetics = false;
    @ConfigEntry
    public boolean discordRPC = true;
}