package icbm.classic.core.registries;

import icbm.classic.IcbmConstants;
import icbm.classic.api.missiles.IMissile;
import icbm.classic.api.missiles.parts.MissileFlightLogic;
import icbm.classic.api.missiles.parts.MissileTargetingModel;
import icbm.classic.api.reg.ExplosiveType;
import icbm.classic.world.missile.logic.source.cause.MissileCauseType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public final class IcbmRegistries {

    public static final ResourceKey<Registry<ExplosiveType>> EXPLOSIVES = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "explosives"));

    public static final ResourceKey<Registry<IMissile>> MISSILES = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "missiles"));

    public static final ResourceKey<Registry<ExplosiveType>> GRENADES = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "grenades"));

    public static final ResourceKey<Registry<MissileTargetingModel>> MISSILE_TARGETING = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "missile_targeting"));

    public static final ResourceKey<Registry<MissileFlightLogic>> MISSILE_FLIGHT_LOGIC = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "missile_flight_logic"));

    public static final ResourceKey<Registry<MissileCauseType<?>>> MISSILE_CAUSE = ResourceKey.createRegistryKey(new ResourceLocation(IcbmConstants.MOD_ID, "missile_cause_type"));
}
