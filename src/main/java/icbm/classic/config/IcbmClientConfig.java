package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmClientConfig {

    public final ModConfigSpec.ConfigValue<Boolean> missileEngineSmoke;
    public final ModConfigSpec.ConfigValue<Boolean> missileLaunchSmoke;

    public IcbmClientConfig(ModConfigSpec.Builder builder) {
        missileEngineSmoke = builder.comment("Enables engine smoke effect for missiles")
            .translation(IcbmConstants.MOD_ID + ".config.client.missile_engine_smoke")
            .define("missile_engine_smoke", true);

        missileLaunchSmoke = builder.comment("Enables smoke effect for launched missiles")
            .translation(IcbmConstants.MOD_ID + ".config.client.missile_launch_smoke")
            .define("missile_launch_smoke", true);
    }
}
