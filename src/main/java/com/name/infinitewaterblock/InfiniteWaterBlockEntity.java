package com.name.infinitewaterblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class InfiniteWaterBlockEntity extends BlockEntity {

    public InfiniteWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(InfiniteWaterBlockMod.INFINITE_WATER_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void tick() {
        if (level == null || level.isClientSide() || !Config.autoPush) {
            return;
        }

        // Check for redstone if the config needs it
        if (Config.requireRedstoneToPush && !level.hasNeighborSignal(worldPosition)) {
            return;
        }

        // Push water to all 6 faces
        for (Direction direction : Direction.values()) {
            pushWaterToFace(direction);
        }
    }

    private void pushWaterToFace(Direction direction) {
        BlockPos targetPos = worldPosition.relative(direction);

        // Get the fluid handler on the adjacent block
        var fluidHandler = level.getCapability(Capabilities.FluidHandler.BLOCK, targetPos, direction.getOpposite());

        if (fluidHandler != null) {
            FluidStack waterStack = new FluidStack(Fluids.WATER, Config.pushPerTick);

            // Try to fill the adjacent fluid handler
            int filled = fluidHandler.fill(waterStack, IFluidHandler.FluidAction.EXECUTE);

//            if (filled > 0) {
                // In case we want to do something in the future such as particles
//            }
        }
    }
}