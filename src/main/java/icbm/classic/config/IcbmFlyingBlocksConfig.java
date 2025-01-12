package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class IcbmFlyingBlocksConfig {

    public final ModConfigSpec.ConfigValue<Boolean> enable;
    public final ModConfigSpec.ConfigValue<List<String>> replacements;
    public final ModConfigSpec.ConfigValue<BlockMode> blockMode;
    public final ModConfigSpec.ConfigValue<List<String>> blocks;

    public IcbmFlyingBlocksConfig(ModConfigSpec.Builder builder) {
        String prefix = IcbmConstants.MOD_ID + ".config.flying_blocks.";

        enable = builder.comment("Enables flying blocks, set to false to prevent additional usage in blasts. Doesn't remove existing or prevent other mods from spawning more.")
            .translation(prefix + "enable")
            .define("enable", true);

        replacements = builder.comment("Replacements to use, Format: 'blockStateA | blockStateB' Docs: https://github.com/BuiltBrokenModding/ICBM-Classic/wiki/config-block-states")
            .translation(prefix + "replacements")
            .define("replacements", List.of("minecraft:water | minecraft:ice"));

        blockMode = builder.comment("Whether to allow or deny the blocks in the list")
            .translation(prefix + "block_mode")
            .defineEnum("block_mode", BlockMode.DENY);

        blocks = builder.comment("List of blocks that to be banned or allowed ")
            .translation(prefix + "blocks")
            .define("blocks", List.of("minecraft:fire"));
    }

    public enum BlockMode {
        ALLOW,
        DENY
    }
}
