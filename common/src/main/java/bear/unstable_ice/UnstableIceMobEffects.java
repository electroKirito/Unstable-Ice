package bear.unstable_ice;

import bear.unstable_ice.mobEffects.FrozenEffect;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class UnstableIceMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(UnstableIce.MOD_ID, Registries.MOB_EFFECT);


    public static final RegistrySupplier<MobEffect> FROZEN =
            EFFECTS.register("frozen", FrozenEffect::new);

    public static void init() {
        EFFECTS.register();
    }

}
