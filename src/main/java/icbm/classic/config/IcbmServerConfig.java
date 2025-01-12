package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmServerConfig {

    public final IcbmBatteryConfig battery;
    public final IcbmDebugConfig debug;
    public final IcbmEmpConfig emp;
    public final IcbmFlyingBlocksConfig flyingBlocks;
    public final IcbmItemsConfig items;
    public final IcbmThreadConfig threads;

    public final ModConfigSpec.ConfigValue<Boolean> requiresPower;
    public final ModConfigSpec.ConfigValue<Integer> rocketLauncherTierFireLimit;

    public IcbmServerConfig(ModConfigSpec.Builder builder) {
        battery = new IcbmBatteryConfig(builder.push("battery"));
        builder.pop();
        debug = new IcbmDebugConfig(builder.push("debug"));
        builder.pop();
        emp = new IcbmEmpConfig(builder.push("emp"));
        builder.pop();
        flyingBlocks = new IcbmFlyingBlocksConfig(builder.push("flying_blocks"));
        builder.pop();
        items = new IcbmItemsConfig(builder.push("items"));
        builder.pop();
        threads = new IcbmThreadConfig(builder.push("threads"));
        builder.pop();

        requiresPower = builder.comment("Range of tier 1 launcher")
            .translation(IcbmConstants.MOD_ID + ".config.use_energy")
            .define("use_energy", true);

        rocketLauncherTierFireLimit = builder.comment("Limits the max tier the handheld launcher can fire,} outside of creative mode")
            .translation(IcbmConstants.MOD_ID + ".config.handheld_launcher_tier_limit")
            .defineInRange("handheld_launcher_tier_limit", 2, 1, 4);
    }
}
