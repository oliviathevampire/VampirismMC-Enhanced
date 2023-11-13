package net.vampirismmc.mod.feat.config;

import blue.endless.jankson.Jankson;
import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class HideawayPlusConfig extends ConfigWrapper<net.vampirismmc.mod.feat.config.HideawayPlusConfigModel> {

    public final Keys keys = new Keys();

    private final Option<java.lang.Boolean> pipNav = this.optionForKey(this.keys.pipNav);
    private final Option<java.lang.Boolean> hideCosmetics = this.optionForKey(this.keys.hideCosmetics);
    private final Option<java.lang.Boolean> discordRPC = this.optionForKey(this.keys.discordRPC);
    private final Option<java.lang.Boolean> autoEnableEditor = this.optionForKey(this.keys.autoEnableEditor);
    private final Option<java.lang.Boolean> jukebox = this.optionForKey(this.keys.jukebox);
    private final Option<java.lang.Boolean> noAmbientSounds = this.optionForKey(this.keys.noAmbientSounds);
    private final Option<java.lang.Boolean> noActivitySongs = this.optionForKey(this.keys.noActivitySongs);

    private HideawayPlusConfig() {
        super(net.vampirismmc.mod.feat.config.HideawayPlusConfigModel.class);
    }

    private HideawayPlusConfig(Consumer<Jankson.Builder> janksonBuilder) {
        super(net.vampirismmc.mod.feat.config.HideawayPlusConfigModel.class, janksonBuilder);
    }

    public static HideawayPlusConfig createAndLoad() {
        var wrapper = new HideawayPlusConfig();
        wrapper.load();
        return wrapper;
    }

    public static HideawayPlusConfig createAndLoad(Consumer<Jankson.Builder> janksonBuilder) {
        var wrapper = new HideawayPlusConfig(janksonBuilder);
        wrapper.load();
        return wrapper;
    }

    public boolean pipNav() {
        return pipNav.value();
    }

    public void pipNav(boolean value) {
        pipNav.set(value);
    }

    public boolean hideCosmetics() {
        return hideCosmetics.value();
    }

    public void hideCosmetics(boolean value) {
        hideCosmetics.set(value);
    }

    public boolean discordRPC() {
        return discordRPC.value();
    }

    public void discordRPC(boolean value) {
        discordRPC.set(value);
    }

    public boolean autoEnableEditor() {
        return autoEnableEditor.value();
    }

    public void autoEnableEditor(boolean value) {
        autoEnableEditor.set(value);
    }

    public boolean jukebox() {
        return jukebox.value();
    }

    public void jukebox(boolean value) {
        jukebox.set(value);
    }

    public boolean noAmbientSounds() {
        return noAmbientSounds.value();
    }

    public void noAmbientSounds(boolean value) {
        noAmbientSounds.set(value);
    }

    public boolean noActivitySongs() {
        return noActivitySongs.value();
    }

    public void noActivitySongs(boolean value) {
        noActivitySongs.set(value);
    }


    public static class Keys {
        public final Option.Key pipNav = new Option.Key("pipNav");
        public final Option.Key hideCosmetics = new Option.Key("hideCosmetics");
        public final Option.Key discordRPC = new Option.Key("discordRPC");
        public final Option.Key autoEnableEditor = new Option.Key("autoEnableEditor");
        public final Option.Key jukebox = new Option.Key("jukebox");
        public final Option.Key noAmbientSounds = new Option.Key("noAmbientSounds");
        public final Option.Key noActivitySongs = new Option.Key("noActivitySongs");
    }
}

