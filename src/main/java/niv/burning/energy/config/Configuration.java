package niv.burning.energy.config;

import java.util.stream.Stream;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class Configuration {

    public static final Event<Runnable> LOADED = EventFactory
            .createArrayBacked(Runnable.class,
                    runnables -> () -> Stream.of(runnables).forEach(Runnable::run));

    private boolean enableEnergyToBurning = true;
    private boolean enableBurningToEnergy = false;

    Configuration() {
    }

    private static final Configuration getInstance() {
        return ConfigurationLoader.getConfiguration();
    }

    public static final void init() {
        getInstance();
    }

    public static final boolean enableEnergyToBurning() {
        return getInstance().enableEnergyToBurning;
    }

    public static final boolean enableBurningToEnergy() {
        return getInstance().enableBurningToEnergy;
    }
}
