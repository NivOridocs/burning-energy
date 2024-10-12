package niv.burning.energy.config;

import java.util.stream.Stream;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class Configuration {

    public static final Event<Runnable> LOADED = EventFactory
            .createArrayBacked(Runnable.class,
                    runnables -> () -> Stream.of(runnables).forEach(Runnable::run));

    private boolean enableEnergyStorageFallback = true;
    private boolean enableBurningStorageFallback = false;

    Configuration() {
    }

    private static final Configuration getInstance() {
        return ConfigurationLoader.getConfiguration();
    }

    public static final void init() {
        getInstance();
    }

    public static final boolean enableEnergyStorageFallback() {
        return getInstance().enableEnergyStorageFallback;
    }

    public static final boolean enableBurningStorageFallback() {
        return getInstance().enableBurningStorageFallback;
    }
}
