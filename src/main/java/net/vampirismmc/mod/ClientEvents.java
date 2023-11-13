package net.vampirismmc.mod;

public interface ClientEvents {
    Event<ServerConfigUpdateEvent> SERVER_CONFIG_UPDATE = new EventImpl<>();

    interface ServerConfigUpdateEvent {
        void updateServerConfig(LimitedModConfigServer config);
    }

    static void updateServerConfig(LimitedModConfigServer config) {
        for (var listener : SERVER_CONFIG_UPDATE.getListeners()) {
            listener.updateServerConfig(config);
        }
    }
}