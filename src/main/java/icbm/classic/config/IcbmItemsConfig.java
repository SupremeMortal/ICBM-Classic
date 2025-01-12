package icbm.classic.config;

import icbm.classic.IcbmConstants;
import net.neoforged.neoforge.common.ModConfigSpec;

public class IcbmItemsConfig {

    public final ModConfigSpec.ConfigValue<Boolean> recipes;
    public final ModConfigSpec.ConfigValue<Boolean> circuits;
    public final ModConfigSpec.ConfigValue<Boolean> ingots;
    public final ModConfigSpec.ConfigValue<Boolean> plates;
    public final ModConfigSpec.ConfigValue<Boolean> wires;
    public final ModConfigSpec.ConfigValue<Boolean> batteries;
    public final ModConfigSpec.ConfigValue<Boolean> sulfurDrops;
    public final ModConfigSpec.ConfigValue<Boolean> lootDrops;

    public IcbmItemsConfig(ModConfigSpec.Builder builder) {
        String prefix = IcbmConstants.MOD_ID + ".config.items.";

        recipes = builder.comment("Enables crafting items required to make most of the content. Only disable if other mods provide the resources or custom recipes are implemented.")
            .translation(prefix + "crafting")
            .define("crafting", true);

        circuits = builder.comment("Enables circuits")
            .translation(prefix + "circuits")
            .define("circuits", true);

        ingots = builder.comment("Enables ingots")
            .translation(prefix + "ingots")
            .define("ingots", true);

        plates = builder.comment("Enables plates")
            .translation(prefix + "plates")
            .define("plates", true);

        wires = builder.comment("Enables wires")
            .translation(prefix + "wires")
            .define("wires", true);

        batteries = builder.comment("Enables batteries")
            .translation(prefix + "batteries")
            .define("batteries", true);

        sulfurDrops = builder.comment("Enables dropping sulfur from creepers and blazes. Use a loot table mod to change the drop rates.")
            .translation(prefix + "sulfur_drops")
            .define("sulfur_drops", true);

        lootDrops = builder.comment("Enables finding crafting items inside of chests as loot. Use a loot table mod to change the drop rates.")
            .translation(prefix + "loot_drops")
            .define("loot_drops", true);
    }
}
