package bear.unstable_ice.mobEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FrozenEffect extends MobEffect {

    final int MAX_FROZEN_TICKS = 200;
    final int FROZEN_THREASHOLD = 140;

    public FrozenEffect() {
        super(MobEffectCategory.HARMFUL, 0x9BE3FF);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        int frozenTicks = livingEntity.getTicksFrozen();
        if (frozenTicks < FROZEN_THREASHOLD) {
            livingEntity.setTicksFrozen((int) (frozenTicks + 15 + i * 1.5));
        } else if (frozenTicks < MAX_FROZEN_TICKS + i * 20) {
            livingEntity.setTicksFrozen((int) (frozenTicks + Math.max(1, 15 + i * 1.5 - (double) frozenTicks / 35)));
        }

    }
}

