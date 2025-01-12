package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmThreadConfig {

    public final ModConfigSpec.ConfigValue<Integer> threads;

    public IcbmThreadConfig(ModConfigSpec.Builder builder) {
        String prefix = IcbmConstants.MOD_ID + ".config.threads.";

        threads = builder.comment("Number of worker threads to run to handle blast calculations. " +
            "Try to only match 50% of the number of cores your machine can support. " +
            "Otherwise the main game thread will slow down while the workers are processing. " +
            "Which is counter to the reason threads exist.")
            .translation(prefix + "thread_count")
            .defineInRange("thread_count", 1, 1, Runtime.getRuntime().availableProcessors());
    }
}
