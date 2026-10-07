package bear.unstable_ice.block;
import bear.unstable_ice.UnstableIceMobEffects;
import bear.unstable_ice.network.ShakePacket;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnstableIceBlock extends Block implements Fallable {

    public static final BooleanProperty SHAKE = BooleanProperty.create("starts_fall");
    private static final List<FallingBlockEntity> FALLING = new ArrayList<>();

    public UnstableIceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.getStateDefinition().any().setValue(SHAKE, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAKE);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        super.stepOn(level, blockPos, blockState, entity);
        BlockState blockBelow = level.getBlockState(blockPos.below());
        if (level.isClientSide || !(entity instanceof LivingEntity) || blockState.getValue(SHAKE) || !blockBelow.isAir()) return;
        level.setBlock(blockPos, blockState.setValue(SHAKE, true), Block.UPDATE_ALL);

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeBlockPos(blockPos);
        NetworkManager.sendToPlayers(((ServerLevel) level).players(), ShakePacket.SHAKE_PACKET, buf);

        level.scheduleTick(blockPos, this, 10);
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos blockPos, FallingBlockEntity fallingBlockEntity) {
        if (level.isClientSide) return;
        level.levelEvent(2001, blockPos, Block.getId(fallingBlockEntity.getBlockState()));
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.getValue(SHAKE)) {
            return;
        }
        FallingBlockEntity falling = FallingBlockEntity.fall(serverLevel, blockPos, blockState.setValue(SHAKE, false));
        falling.disableDrop();
        FALLING.add(falling);
    }

    public static void checkCollisions(ServerLevel level) {
        Iterator<FallingBlockEntity> it = FALLING.iterator();
        while (it.hasNext()) {
            FallingBlockEntity falling = it.next();

            if (falling.isRemoved()) {
                it.remove();
                continue;
            }
            if (falling.level() != level) continue;


            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class, falling.getBoundingBox());

            targets.removeIf(target ->
                        falling.getDeltaMovement().y >= target.getDeltaMovement().y
                    );

            if (targets.isEmpty()) continue;
            shatter(level, falling.blockPosition(), falling, targets);
            falling.discard();
            it.remove();
        }
    }

    private static void shatter(Level level, BlockPos pos, FallingBlockEntity falling,
                                List<LivingEntity> targets) {
        BlockState state = falling.getBlockState();
        level.levelEvent(2001, pos, Block.getId(state));
        Block.dropResources(state, level, pos);

        for (LivingEntity target : targets) {
            target.hurt(level.damageSources().fallingBlock(falling), 2.0f);
            target.addEffect(new MobEffectInstance(UnstableIceMobEffects.FROZEN.get(), 60, 0));
        }
    }
}
