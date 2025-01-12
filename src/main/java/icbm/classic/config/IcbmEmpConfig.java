package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmEmpConfig {

    public final ModConfigSpec.ConfigValue<Boolean> chargeCreepers;
    public final ModConfigSpec.ConfigValue<Boolean> effectMissiles;
    public final ModConfigSpec.ConfigValue<Boolean> dropMissiles;
    public final ModConfigSpec.ConfigValue<Boolean> effectEntityInventories;
    public final ModConfigSpec.ConfigValue<Boolean> effectBlockInventories;
    public final ModConfigSpec.ConfigValue<Boolean> effectItemInventories;
    public final ModConfigSpec.ConfigValue<Boolean> effectDroppedItems;
    public final ModConfigSpec.ConfigValue<Boolean> effectEntityEnergy;
    public final ModConfigSpec.ConfigValue<Boolean> effectItemEnergy;
    public final ModConfigSpec.ConfigValue<Boolean> effectBlockEnergy;
    public final ModConfigSpec.ConfigValue<Boolean> effectEntities;
    public final ModConfigSpec.ConfigValue<Boolean> effectBlocks;

    public IcbmEmpConfig(ModConfigSpec.Builder builder) {
        String prefix = IcbmConstants.MOD_ID + ".config.emp.";
        chargeCreepers = builder.comment("Creepers become charged when hit by an EMP")
            .translation(prefix + "charge_creepers")
            .define("charge_creepers", true);

        effectMissiles = builder.comment("Missile guidance systems are disabled by an EMP")
            .translation(prefix + "effect_missiles")
            .define("effect_missiles", true);

        dropMissiles = builder.comment("Missiles are dropped when hit by an EMP")
            .translation(prefix + "drop_missiles")
            .define("drop_missiles", true);

        effectEntityInventories = builder.comment("Entities with inventories are affected by an EMP")
            .translation(prefix + "effect_entity_inventories")
            .define("effect_entity_inventories", true);

        effectBlockInventories = builder.comment("Blocks with inventories are affected by an EMP")
            .translation(prefix + "effect_block_inventories")
            .define("effect_block_inventories", true);

        effectItemInventories = builder.comment("Items with inventories are affected by an EMP")
            .translation(prefix + "emp.effect_item_inventories")
            .define("effect_item_inventories", true);

        effectDroppedItems = builder.comment("Dropped items are affected by an EMP")
            .translation(prefix + "effect_dropped_items")
            .define("effect_dropped_items", true);

        effectEntityEnergy = builder.comment("Entities without direct EMP support are affected by an EMP")
            .translation(prefix + "effect_entity_energy")
            .define("effect_entity_energy", true);

        effectItemEnergy = builder.comment("Items without direct EMP support are affected by an EMP")
            .translation(prefix + "effect_item_energy")
            .define("effect_item_energy", true);

        effectBlockEnergy = builder.comment("Blocks without direct EMP support are affected by an EMP")
            .translation(prefix + "effect_block_energy")
            .define("effect_block_energy", true);

        effectEntities = builder.comment("Entities are affected by an EMP")
            .translation(prefix + "effect_entities")
            .define("effect_entities", true);

        effectBlocks = builder.comment("Blocks are affected by an EMP")
            .translation(prefix + "effect_blocks")
            .define("effect_blocks", true);
    }
}
