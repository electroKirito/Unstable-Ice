package bear.unstable_ice.forge.client;

import bear.unstable_ice.client.ShakingIceRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "unstable_ice", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class UnstableIceForegeClient {

    @SubscribeEvent
    public static void onRender(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            ShakingIceRenderer.render(event.getPoseStack(), event.getCamera(), event.getPartialTick());
        }
    }

}
