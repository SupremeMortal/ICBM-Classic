package icbm.classic.world.missile.logic.source.cause;

import icbm.classic.IcbmConstants;
import icbm.classic.core.registries.IcbmRegistries;
import lombok.experimental.UtilityClass;
import net.neoforged.neoforge.registries.DeferredRegister;

@UtilityClass
public class MissileCauseTypes {

    public static final DeferredRegister<MissileCauseType<?>> REGISTER = DeferredRegister.create(IcbmRegistries.MISSILE_CAUSE, IcbmConstants.MOD_ID);

    public static final MissileCauseType<BlockCause> BLOCK = REGISTER.register("block", () -> new MissileCauseType<>(BlockCause::new));
}
