package bear.unstable_ice;

import bear.unstable_ice.block.UnstableIceBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class UnstableIceBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(UnstableIce.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> UNSTABLE_ICE =
            BLOCKS.register("unstable_ice", () ->
                new UnstableIceBlock(
                        BlockBehaviour.Properties.of()
                                .strength(2.8F)
                                .friction(0.992F)
                                .sound(SoundType.GLASS)
                )
            );


    public static void register() {
        BLOCKS.register();
    }



}
