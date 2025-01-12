package icbm.classic.world.missile.logic.source.cause;

import icbm.classic.IcbmConstants;
import icbm.classic.api.missiles.cause.IMissileCause;
import icbm.classic.lib.saving.NbtSaveHandler;
import lombok.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.common.DimensionManager;

/**
 * General purpose block cause
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class BlockCause extends MissileCause implements IMissileCause.IBlockCause {

    private Level level;
    private BlockPos blockPos;
    private BlockState blockState;

    private int worldId;

    public BlockCause(MissileCauseType<BlockCause> type) {
        super(type);
    }

    public Level getLevel() {
        if (world == null) {
            world = DimensionManager.getLevel(worldId);
        }
        return world;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return REG_NAME;
    }

    @Override
    public CompoundTag serializeNBT() {
        return SAVE_LOGIC.save(this, super.serializeNBT());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        SAVE_LOGIC.load(this, nbt);
    }

    private static final NbtSaveHandler<BlockCause> SAVE_LOGIC = new NbtSaveHandler<BlockCause>()
        .mainRoot()
        /* */.nodeInteger("level", BlockCause::getLevelId, BlockCause::setWorldId)
        /* */.nodeBlockPos("pos", BlockCause::getBlockPos, BlockCause::setBlockPos)
        /* */.nodeBlockState("state", BlockCause::getBlockState, BlockCause::setBlockState)
        .base();
}
