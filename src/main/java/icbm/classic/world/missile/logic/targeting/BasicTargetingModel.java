package icbm.classic.world.missile.logic.targeting;

import icbm.classic.IcbmConstants;
import icbm.classic.api.missiles.parts.MissileTargetingModel;
import icbm.classic.api.missiles.parts.IMissileTargetingDelayed;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

/**
 * Simple 3D position targeting data
 */
public class BasicTargetingModel implements MissileTargetingModel, IMissileTargetingDelayed {

    public static final ResourceLocation REG_NAME = new ResourceLocation(IcbmConstants.MOD_ID, "basic");
    private Vec3 position;
    private int firingDelay = 0;

    public BasicTargetingModel() {
        //Only used for save/load
    }

    public BasicTargetingModel(double x, double y, double z) {
        this.position = new Vec3(x, y, z);
    }

    public BasicTargetingModel(Vec3 position) {
        this.position = position;
    }

    public BasicTargetingModel(BlockPos pos) {
        this.position = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    public Vec3 getPosition() {
        return position;
    }

    public BasicTargetingModel withFiringDelay(int ticks) {
        this.firingDelay = ticks;
        return this;
    }

    @Override
    public int getFiringDelay() {
        return firingDelay;
    }

    @Override
    public MissileTargetingModel cloneWithoutDelay() {
        return new BasicTargetingModel(position);
    }

    @Override
    public boolean isValid() {
        return position != null
            && Double.isNaN(getX()) && Double.isFinite(getX())
            && Double.isNaN(getY()) && Double.isFinite(getY())
            && Double.isNaN(getZ()) && Double.isFinite(getZ());
    }

    @Override
    public double getX() {
        return position != null ? position.x : Double.NaN;
    }

    @Override
    public double getY() {
        return position != null ? position.y : Double.NaN;
    }

    @Override
    public double getZ() {
        return position != null ? position.z : Double.NaN;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return REG_NAME;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag saveData = new CompoundTag();
        saveData.putDouble("x", position.x);
        saveData.putDouble("y", position.y);
        saveData.putDouble("z", position.z);
        saveData.putInt("firingDelay", firingDelay);
        return saveData;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        double x = nbt.getDouble("x");
        double y = nbt.getDouble("y");
        double z = nbt.getDouble("z");
        this.position = new Vec3(x, y, z);
        this.firingDelay = nbt.getInt("firingDelay");
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BasicTargetingModel) {
            return Objects.equals(((BasicTargetingModel) other).position, position);
        }
        return false;
    }
}
