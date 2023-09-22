package continued.hideaway.mod.feat.config;

import continued.hideaway.mod.util.Constants;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Sync;

@Sync(Option.SyncMode.NONE)
@Modmenu(modId = Constants.MOD_ID)
@Config(name = "hc-config", wrapperName = "HideawayContinuedConfig")
public class HideawayContinuedConfigModel {
    // General
    public boolean pipNav = true;
    public boolean hideCosmetics = true;
    public boolean discordRPC = true;

    // Rooms
    public boolean autoEnableEditor = true;

    // Sound
    public boolean jukebox = true;
    public boolean noAmbientSounds = true;
}