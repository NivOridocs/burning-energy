package niv.burning.energy;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import niv.burning.api.Burning;
import niv.burning.api.BurningStorage;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.DelegatingEnergyStorage;

public final class EnergyFallbackBurningStorage extends DelegatingEnergyStorage implements BurningStorage {

    public EnergyFallbackBurningStorage(EnergyStorage target) {
        super(target, () -> true);
    }

    @Override
    public Burning insert(Burning burning, TransactionContext transaction) {
        return burning.withValue(
                (int) this.insert(burning.getValue(BurningEnergy::getEnergyDuration).longValue(), transaction),
                BurningEnergy::getEnergyDuration);
    }

    @Override
    public Burning extract(Burning burning, TransactionContext transaction) {
        return burning.withValue(
                (int) this.extract(burning.getValue(BurningEnergy::getEnergyDuration).longValue(), transaction),
                BurningEnergy::getEnergyDuration);
    }

    @Override
    public Burning getBurning() {
        return Burning.MIN_VALUE.withValue((int) this.getAmount(), BurningEnergy::getEnergyDuration);
    }
}
