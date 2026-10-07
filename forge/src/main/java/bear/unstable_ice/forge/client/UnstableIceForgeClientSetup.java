package bear.unstable_ice.forge.client;

import bear.unstable_ice.client.UnstableIceClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "unstable_ice", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnstableIceForgeClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(UnstableIceClient::init);
    }
}