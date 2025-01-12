package icbm.classic.world.block.launcher;

import icbm.classic.api.launcher.ILauncherSolution;
import icbm.classic.api.launcher.IMissileLauncher;
import icbm.classic.api.missiles.parts.MissileTargetingModel;
import lombok.Data;

@Data
public class LauncherSolution implements ILauncherSolution {

    private final MissileTargetingModel target;
    private final int firingGroup;
    private final int firingCount;

    public LauncherSolution(MissileTargetingModel target) {
        this.target = target;
        this.firingGroup = -1;
        this.firingCount = -1;
    }

    public LauncherSolution(MissileTargetingModel target, int group, int firingCount) {
        this.target = target;
        this.firingGroup = group;
        this.firingCount = firingCount;
    }

    @Override
    public MissileTargetingModel getTarget(IMissileLauncher launcher) {
        return target;
    }
}
