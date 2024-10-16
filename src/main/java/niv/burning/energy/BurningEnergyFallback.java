package niv.burning.energy;

import static java.util.function.Predicate.not;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup.BlockApiProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class BurningEnergyFallback<A, B> implements BlockApiProvider<B, @Nullable Direction> {

    private final BooleanSupplier enable;

    private final BlockApiLookup<A, @Nullable Direction> lookup;

    private final Function<A, B> constructor;

    public BurningEnergyFallback(BooleanSupplier enable,
            BlockApiLookup<A, @Nullable Direction> lookup,
            Function<A, B> constructor) {
        this.enable = Objects.requireNonNull(enable);
        this.lookup = Objects.requireNonNull(lookup);
        this.constructor = Objects.requireNonNull(constructor);
    }

    @Override
    public @Nullable B find(Level world, BlockPos pos, BlockState state,
            @Nullable BlockEntity blockEntity, @Nullable Direction direction) {
        if (this.enable.getAsBoolean()) {
            return Optional.ofNullable(this.lookup.getProvider(state.getBlock()))
                    .filter(not(BurningEnergyFallback.class::isInstance))
                    .map(provider -> provider.find(world, pos, state, blockEntity, direction))
                    .map(this.constructor)
                    .orElse(null);
        } else {
            return null;
        }
    }
}
