package bear.unstable_ice.client;

import bear.unstable_ice.network.ShakePacket;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;

public class UnstableIceClient {
    public static void init() {
        System.out.println("\n\na\n\n");
        NetworkManager.registerReceiver(NetworkManager.s2c(), ShakePacket.SHAKE_PACKET, ((friendlyByteBuf, packetContext) -> {
            BlockPos pos = friendlyByteBuf.readBlockPos();
            packetContext.queue(() -> ShakingIceRenderer.add(pos));
        }));
    }
}
