package net.vampirismmc.mod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public interface SyncableConfig<T, L> {
    Integer getSyncTimeout();

    Component getSyncTimeoutMessage();

    L getLimited(ServerGamePacketListenerImpl handler);
}