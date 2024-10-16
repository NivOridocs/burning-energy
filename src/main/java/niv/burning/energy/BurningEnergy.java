package niv.burning.energy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import niv.burning.api.BurningStorage;
import niv.burning.energy.config.Configuration;
import team.reborn.energy.api.EnergyStorage;

public class BurningEnergy implements ModInitializer {

    public static final String MOD_ID = "burning-energy";

    public static final String MOD_NAME = "Burning Energy";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize");

        EnergyStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableEnergyToBurning, BurningStorage.SIDED, BurningFallbackEnergyStorage::new));

        BurningStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableBurningToEnergy, EnergyStorage.SIDED, EnergyFallbackBurningStorage::new));

        Configuration.LOADED.register(() -> LOGGER.info("Load configuration"));

        Configuration.init();
    }

    public static final int getEnergyDuration(ItemStack stack) {
        return AbstractFurnaceBlockEntity.getFuel().getOrDefault(stack.getItem(), 0) * 5 / 2;
    }
}
