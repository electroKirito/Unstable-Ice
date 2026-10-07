package bear.unstable_ice.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import bear.unstable_ice.UnstableIce;

@Mod(UnstableIce.MOD_ID)
public final class UnstableIceForge {
    public UnstableIceForge() {
        EventBuses.registerModEventBus(UnstableIce.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        UnstableIce.init();
    }
}
