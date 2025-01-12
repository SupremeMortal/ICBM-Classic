package icbm.classic;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.function.BiConsumer;


public class IcbmLootTableProvider implements LootTableSubProvider {

    public static final LootTableProvider.SubProviderEntry INSTANCE = new LootTableProvider.SubProviderEntry(
        IcbmLootTableProvider::new, LootContextParamSets.BLOCK);

    private IcbmLootTableProvider() {
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
        LootTable.lootTable()
            .
            .build();
    }
}
