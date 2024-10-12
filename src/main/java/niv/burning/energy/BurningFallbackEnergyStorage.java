package niv.burning.energy;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import niv.burning.api.Burning;
import niv.burning.api.BurningStorage;
import niv.burning.api.base.ForwardingBurningStorage;
import team.reborn.energy.api.EnergyStorage;

public final class BurningFallbackEnergyStorage extends ForwardingBurningStorage implements EnergyStorage {

    public BurningFallbackEnergyStorage(BurningStorage target) {
        super(target);
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return this.target.get()
                .insert(Burning.MIN_VALUE.withValue((int) maxAmount, BurningEnergy::getEnergyDuration), transaction)
                .getValue(BurningEnergy::getEnergyDuration).longValue();
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return this.target.get()
                .extract(Burning.MIN_VALUE.withValue((int) maxAmount, BurningEnergy::getEnergyDuration), transaction)
                .getValue(BurningEnergy::getEnergyDuration).longValue();
    }

    @Override
    public long getAmount() {
        return this.target.get().getBurning().getValue(BurningEnergy::getEnergyDuration).longValue();
    }

    @Override
    public long getCapacity() {
        return this.target.get().getBurning().getBurnDuration(BurningEnergy::getEnergyDuration);
    }
}
