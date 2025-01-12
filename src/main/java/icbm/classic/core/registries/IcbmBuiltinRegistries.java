package icbm.classic.core.registries;

import icbm.classic.api.missiles.IMissile;
import icbm.classic.api.missiles.parts.MissileFlightLogic;
import icbm.classic.api.missiles.parts.MissileTargetingModel;
import icbm.classic.api.reg.ExplosiveType;
import icbm.classic.world.missile.logic.source.cause.MissileCauseType;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.RegistryBuilder;

public final class IcbmBuiltinRegistries {

    public static final Registry<ExplosiveType> EXPLOSIVES = new RegistryBuilder<>(IcbmRegistries.EXPLOSIVES)
        .create();

    public static final Registry<IMissile> MISSILES = new RegistryBuilder<>(IcbmRegistries.MISSILES)
        .create();

    public static final Registry<ExplosiveType> GRENADES = new RegistryBuilder<>(IcbmRegistries.GRENADES)
        .create();

    public static final Registry<MissileTargetingModel> MISSILE_TARGETS = new RegistryBuilder<>(IcbmRegistries.MISSILE_TARGETING)
        .create();

    public static final Registry<MissileFlightLogic> MISSILE_FLIGHT_LOGIC = new RegistryBuilder<>(IcbmRegistries.MISSILE_FLIGHT_LOGIC)
        .create();

    public static final Registry<MissileCauseType<?>> MISSILE_CAUSE = new RegistryBuilder<>(IcbmRegistries.MISSILE_CAUSE)
        .create();
}
