package icbm.classic.lib.saving.nodes;

import icbm.classic.lib.saving.NbtSaveNode;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class SaveNodeBlockPos<E> extends NbtSaveNode<E, CompoundTag> {
    public SaveNodeBlockPos(final String name, Function<E, BlockPos> save, BiConsumer<E, BlockPos> load) {
        super(name,
            (obj) -> {
                final BlockPos pos = save.apply(obj);
                if (pos != null) {
                    final CompoundTag compound = new CompoundTag();
                    compound.putInt("x", pos.getX());
                    compound.putInt("y", pos.getY());
                    compound.putInt("z", pos.getZ());
                    return compound;
                }
                return null;
            },
            (obj, data) -> {
                final BlockPos pos = new BlockPos(
                    data.getInt("x"),
                    data.getInt("y"),
                    data.getInt("z")
                );
                load.accept(obj, pos);
            }
        );
    }
}
