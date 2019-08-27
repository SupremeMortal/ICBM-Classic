package icbm.classic.content.blast.threaded;

import icbm.classic.client.ICBMSounds;
import icbm.classic.content.blast.BlastHelpers;
import icbm.classic.content.entity.EntityExplosion;
import icbm.classic.content.blast.BlastRedmatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.function.Consumer;

public class BlastAntimatter extends BlastThreaded
{

    private boolean destroyBedrock;

    public BlastAntimatter()
    {
    }

    public BlastAntimatter(boolean destroyBedrock)
    {
        this.destroyBedrock = destroyBedrock;
    }

    /**
     * Called before an explosion happens
     */
    @Override
    public void setupBlast()
    {
        super.setupBlast();
        ICBMSounds.ANTIMATTER.play(world, this.location.x(), this.location.y(), this.location.z(), 7F, (float) (this.world().rand.nextFloat() * 0.1 + 0.9F), true);
        this.doDamageEntities(this.getBlastRadius() * 2, Integer.MAX_VALUE);
    }

    @Override
    public void destroyBlock(BlockPos blockPos)
    {
        final IBlockState blockState = world.getBlockState(blockPos);
        if (!blockState.getBlock().isAir(blockState, world, blockPos))
        {
            final double dist = location.distance(blockPos);
            //TODO change to not use sqrt for better performance
            //TODO maybe do the random in the thread?
            if (dist < this.getBlastRadius() - 1 || world().rand.nextFloat() > 0.7)
            {
                if (blockState.getBlockHardness(this.world(), blockPos) >= 0 || destroyBedrock)
                {
                    world.setBlockToAir(blockPos);
                }
            }
        }
    }

    @Override
    public boolean doRun(int loops, Consumer<BlockPos> edits)
    {
        BlastHelpers.loopInRadius(this.getBlastRadius(), (x, y, z) ->
                edits.accept(new BlockPos(xi() + x, yi() + y, zi() + z)));
        return false;
    }

    @Override
    public boolean doExplode(int callCount)
    {
        super.doExplode(callCount);

        // TODO: Render antimatter shockwave
        /*
         * else if (ZhuYao.proxy.isGaoQing()) { for (int x = -this.getRadius(); x <
         * this.getRadius(); x++) { for (int y = -this.getRadius(); y < this.getRadius(); y++) { for
         * (int z = -this.getRadius(); z < this.getRadius(); z++) { Vector3 targetPosition =
         * Vector3.add(position, new Vector3(x, y, z)); double distance =
         * position.distanceTo(targetPosition);
         * if (targetPosition.getBlockID(worldObj) == 0) { if (distance < this.getRadius() &&
         * distance > this.getRadius() - 1 && worldObj.rand.nextFloat() > 0.5) {
         * ParticleSpawner.spawnParticle("antimatter", worldObj, targetPosition); } } } } } }
         */
        return false;
    }

    @Override
    public void onBlastCompleted()
    {
        super.onBlastCompleted();
        this.doDamageEntities(this.getBlastRadius() * 2, Integer.MAX_VALUE);
    }

    @Override
    protected boolean onDamageEntity(Entity entity)
    {
        if (entity instanceof EntityExplosion)
        {
            if (((EntityExplosion) entity).getBlast() instanceof BlastRedmatter)
            {
                entity.setDead();
                return true;
            }
        }

        return false;
    }
}
