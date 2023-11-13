package net.vampirismmc.mod;

import com.mojang.serialization.Codec;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Optional;
import java.util.function.Consumer;

public class HandshakeClient<L, F extends L> {
    public static final int PROTOCOL_VERSION = 3;

    private final Codec<F> transferCodec;
    private final Codec<L> limitedTransferCodec;
    private final Consumer<L> updateCallback;
    private L serverConfig = null;
    private F fullServerConfig = null;
    private boolean hasConnected = false;

    public HandshakeClient(Codec<F> transferCodec, Codec<L> limitedTransferCodec, Consumer<L> updateCallback) {
        this.transferCodec = transferCodec;
        this.limitedTransferCodec = limitedTransferCodec;
        this.updateCallback = updateCallback;
    }

    /**
     * Returns the server config if the client has received one for this server,
     * returns an empty optional in any other case.
     */
    public Optional<L> getConfig() {
        return Optional.ofNullable(serverConfig);
    }

    public Optional<F> getFullConfig() {
        return Optional.ofNullable(fullServerConfig);
    }

//    public void setConfig(@Nullable L config) {
//        serverConfig = config;
//        updateCallback.accept(serverConfig);
//        hasConnected = serverConfig != null;
//    }

    public boolean hasConnected() {
        return hasConnected;
    }

    public FriendlyByteBuf handleConfigSync(FriendlyByteBuf buf) {
        hasConnected = true;
        VampirismMCEnhanced.logger().info("Received config from server");
        var returnBuf = new FriendlyByteBuf(Unpooled.buffer());
        returnBuf.writeInt(PROTOCOL_VERSION);
        returnBuf.writeBoolean(serverConfig != null);
        return returnBuf;
    }

    public void reset() {
        serverConfig = null;
        hasConnected = false;
    }
}