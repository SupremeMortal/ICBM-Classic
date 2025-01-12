package icbm.classic.world.missile.logic.targeting;

import icbm.classic.IcbmConstants;
import icbm.classic.api.missiles.parts.MissileTargetingModel;
import icbm.classic.core.registries.IcbmRegistries;
import icbm.classic.world.missile.entity.anti.SAMTargetingData;
import lombok.experimental.UtilityClass;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class IcbmMissileTargetingModels {

    public static final DeferredRegister<MissileTargetingModel> REGISTER = DeferredRegister.create(IcbmRegistries.MISSILE_TARGETING, IcbmConstants.MOD_ID);

    public static final DeferredHolder<MissileTargetingModel, BasicTargetingModel> BASIC = REGISTER.register("basic", () -> new BasicTargetingModel());
    public static final DeferredHolder<MissileTargetingModel, BallisticTargetingModel> BALLISTIC = REGISTER.register("ballistic", BallisticTargetingModel::new);
    public static final DeferredHolder<MissileTargetingModel, SAMTargetingData> SAM = REGISTER.register("sam", () -> null); //Can't be restored from save but reserving name
}
