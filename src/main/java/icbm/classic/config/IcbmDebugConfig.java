package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmDebugConfig {

    public final ModConfigSpec.ConfigValue<Boolean> threads;
    public final ModConfigSpec.ConfigValue<Boolean> explosives;
    public final ModConfigSpec.ConfigValue<Boolean> missileLaunches;
    public final ModConfigSpec.ConfigValue<Boolean> missileLogic;
    public final ModConfigSpec.ConfigValue<Boolean> missileTracker;

    public IcbmDebugConfig(ModConfigSpec.Builder builder) {
        threads = builder.comment("Enables extra console output to check the state of the explosive threads.")
            .translation(IcbmConstants.MOD_ID + "config.debug.threads")
            .define("threads", false);

        explosives = builder.comment("Enables extra console output to check the state of the explosive logic.")
            .translation(IcbmConstants.MOD_ID + "config.debug.explosives")
            .define("explosives", false);

        missileLaunches = builder.comment("When enabled, all missile launches are logged, including target and origin coordinates.")
            .translation(IcbmConstants.MOD_ID + "config.debug.missile_launches")
            .define("missile_launches", false);

        missileLogic = builder.comment("When enabled, missile logic will be logged, including flight steps and target changes.")
            .translation(IcbmConstants.MOD_ID + "config.debug.missile_logic")
            .define("missile_logic", false);

        missileTracker = builder.comment("When enabled, additional debug output is written to the console regarding the missile tracker.")
            .translation(IcbmConstants.MOD_ID + "config.debug.missile_tracker")
            .define("missile_tracker", false);
    }
}
