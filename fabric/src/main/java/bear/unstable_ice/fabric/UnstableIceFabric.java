package bear.unstable_ice.fabric;

import net.fabricmc.api.ModInitializer;

import bear.unstable_ice.UnstableIce;

public final class UnstableIceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UnstableIce.init();
    }
}
