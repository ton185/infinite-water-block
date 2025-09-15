package com.name.infinitewaterblock;

import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

public class InfiniteWaterFluidHandler implements IFluidHandler {

    @Override
    public int getTanks() {
        return Config.tankCount;
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        return new FluidStack(Fluids.WATER, Config.amountPerTank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return Config.amountPerTank;
    }

    @Override
    public boolean isFluidValid(int i, FluidStack fluidStack) {
        return fluidStack.is(Fluids.WATER);
    }

    @Override
    public int fill(@NotNull FluidStack resource, @NotNull FluidAction action) {
        if (Config.acceptWater && resource.getFluid() == Fluids.WATER) {
            return resource.getAmount(); // void all inputted water, ignore anything else
        }
        return 0;
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, @NotNull FluidAction action) {
        if (resource.getFluid() == Fluids.WATER) {
            return new FluidStack(Fluids.WATER, Math.min(resource.getAmount(), Config.amountPerTank));
        }
        return FluidStack.EMPTY;
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, @NotNull FluidAction action) {
        return new FluidStack(Fluids.WATER, Math.min(maxDrain, Config.amountPerTank));
    }
}