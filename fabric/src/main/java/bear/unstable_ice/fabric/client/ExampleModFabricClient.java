package bear.unstable_ice.fabric.client;

import bear.unstable_ice.client.ShakingIceRenderer;
import bear.unstable_ice.client.UnstableIceClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.Minecraft;

public final class ExampleModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UnstableIceClient.init();
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ctx ->
                        ShakingIceRenderer.render(ctx.matrixStack(), ctx.camera(), ctx.tickDelta())
                );
    }
}
