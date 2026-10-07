package bear.unstable_ice;

import bear.unstable_ice.block.UnstableIceBlock;
import dev.architectury.event.events.common.TickEvent;

public final class UnstableIce {
    public static final String MOD_ID = "unstable_ice";

    public static void init() {
        UnstableIceBlocks.register();
        UnstableIceItems.register();
        UnstableIceCreaciveTabs.register();
        UnstableIceMobEffects.init();
        TickEvent.SERVER_LEVEL_POST.register(UnstableIceBlock::checkCollisions);
    }
}
