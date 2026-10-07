package bear.unstable_ice.client;

import bear.unstable_ice.UnstableIceBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class ShakingIceRenderer {
    public static final Map<BlockPos, Long> SHAKING = new HashMap<>();
    public static final int DURATION = 11;



    public static void render(PoseStack pose, Camera camera, float partialTick) {
        if (SHAKING.isEmpty()) return;
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) { SHAKING.clear(); return; }

        Vec3 cam = camera.getPosition();
        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        BlockState base = UnstableIceBlocks.UNSTABLE_ICE.get().defaultBlockState();
        float time = level.getGameTime() + partialTick;

        Iterator<Map.Entry<BlockPos, Long>> it = SHAKING.entrySet().iterator();
        while (it.hasNext()) {
            var e = it.next();
            BlockPos pos = e.getKey();
            if (level.getGameTime() - e.getValue() > DURATION
                    || !level.getBlockState(pos).is(base.getBlock())) {
                if (level.getGameTime() - e.getValue() > 2) { it.remove(); continue; }
            }

            double seed = pos.hashCode();
            double dx = Math.sin(time * 3.0 + seed) * 0.03;
            double dz = Math.cos(time * 3.7 + seed) * 0.03;

            pose.pushPose();
            pose.translate(pos.getX() - cam.x + dx, pos.getY() - cam.y, pos.getZ() - cam.z + dz);
            mc.getBlockRenderer().renderSingleBlock(base, pose, buffers,
                    LevelRenderer.getLightColor(level, pos.above()), OverlayTexture.NO_OVERLAY);
            pose.popPose();
        }
        buffers.endBatch();
    }

    public static void add(BlockPos pos) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) SHAKING.put(pos, level.getGameTime());
    }
}
