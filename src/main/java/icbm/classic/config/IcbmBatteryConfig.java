package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmBatteryConfig {

    public final ModConfigSpec.ConfigValue<Integer> capacity;
    public final ModConfigSpec.ConfigValue<Integer> inputLimit;
    public final ModConfigSpec.ConfigValue<Integer> outputLimit;

    public IcbmBatteryConfig(ModConfigSpec.Builder builder) {
        capacity = builder.comment("Amount of energy the battery can store")
            .translation(IcbmConstants.MOD_ID + ".config.battery.tier_1_capacity")
            .defineInRange("tier_1_capacity", 100000, 1, Integer.MAX_VALUE);

        inputLimit = builder.comment("Transfer limit into the battery")
            .translation(IcbmConstants.MOD_ID + ".config.battery.tier_1_input")
            .defineInRange("tier_1_input_limit", 10000, 1, Integer.MAX_VALUE);

        outputLimit = builder.comment("Transfer limit out of the battery")
            .translation(IcbmConstants.MOD_ID + ".config.battery.tier_1_output")
            .defineInRange("tier_1_output_limit", 10000, 1, Integer.MAX_VALUE);
    }
}
