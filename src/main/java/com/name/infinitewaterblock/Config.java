package com.name.infinitewaterblock;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = InfiniteWaterBlockMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue AUTO_PUSH = BUILDER.comment("Whether to push water automatically").define("autoPush", true);
    private static final ModConfigSpec.BooleanValue ACCEPT_WATER = BUILDER.comment("Whether the block should accept water input. This will void any water that is inputted.").define("acceptWater", false);
    private static final ModConfigSpec.BooleanValue REQUIRE_REDSTONE_TO_PUSH = BUILDER.comment("Whether the block should require being powered by redstone to push water").define("requireRedstoneToPush", false);

    private static final ModConfigSpec.IntValue PUSH_PER_TICK = BUILDER.comment("How much water to push per side per tick").defineInRange("pushAmount", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue TANK_COUNT = BUILDER.comment("How many tanks the block should have (leave it at 1 if you dont know what this does)").defineInRange("tankCount", 1, 1, Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue AMOUNT_PER_TANK = BUILDER.comment("How much water each tank should have (in mb)").defineInRange("amountPerTank", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean autoPush;
    public static boolean acceptWater;
    public static boolean requireRedstoneToPush;

    public static int pushPerTick;
    public static int tankCount;
    public static int amountPerTank;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        autoPush = AUTO_PUSH.get();
        acceptWater = ACCEPT_WATER.get();
        requireRedstoneToPush = REQUIRE_REDSTONE_TO_PUSH.get();

        pushPerTick = PUSH_PER_TICK.get();
        tankCount = TANK_COUNT.get();
        amountPerTank = AMOUNT_PER_TANK.get();
    }
}
