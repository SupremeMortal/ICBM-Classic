package icbm.classic.world.missile.logic.flight;

import icbm.classic.IcbmConstants;
import icbm.classic.api.missiles.parts.MissileFlightLogic;
import icbm.classic.core.registries.IcbmRegistries;
import icbm.classic.world.missile.logic.flight.move.MoveByFacingLogic;
import icbm.classic.world.missile.logic.flight.move.MoveForTicksLogic;
import lombok.experimental.UtilityClass;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class IcbmFlightLogicTypes {

    public static final DeferredRegister<MissileFlightLogic> REGISTER = DeferredRegister.create(IcbmRegistries.MISSILE_FLIGHT_LOGIC, IcbmConstants.MOD_ID);

    public static final DeferredHolder<MissileFlightLogic, ?> DIRECT = REGISTER.register("direct", () -> new DirectFlightLogic());
    public static final DeferredHolder<MissileFlightLogic, ?> BALLISTIC_OLD = REGISTER.register("ballistic_old", () -> new BallisticFlightLogicOld());
    public static final DeferredHolder<MissileFlightLogic, ?> ARC = REGISTER.register("arc", ArcFlightLogic::new);
    public static final DeferredHolder<MissileFlightLogic, ?> WARMUP = REGISTER.register("warmup", WarmupFlightLogic::new);
    public static final DeferredHolder<MissileFlightLogic, ?> MOVE_BY_FACING = REGISTER.register("move_by_facing", MoveByFacingLogic::new);
    public static final DeferredHolder<MissileFlightLogic, ?> MOVE_FOR_TICKS = REGISTER.register("move_for_ticks", MoveForTicksLogic::new);
    public static final DeferredHolder<MissileFlightLogic, ?> DEAD = REGISTER.register("dead", () -> new DeadFlightLogic());
    public static final DeferredHolder<MissileFlightLogic, ?> FOLLOW_TARGET = REGISTER.register("follow_target", () -> new FollowTargetLogic());
}
