package bear.unstable_ice;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class UnstableIceItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(UnstableIce.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> UNSTABLE_ICE_BLOCK_ITEM =
            ITEMS.register("unstable_ice", () ->
                    new BlockItem(
                            UnstableIceBlocks.UNSTABLE_ICE.get(),
                            new Item.Properties()
                    ));


    public static final RegistrySupplier<Item> ICE_BALL_ITEM =
            ITEMS.register("ice_ball", () ->
                    new Item(new Item.Properties())
                    );

    public static void register() {
        ITEMS.register();
    }
}
