package bear.unstable_ice;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UnstableIceCreaciveTabs {
    public static final CreativeModeTab UNSTABLE_ICE =
            CreativeTabRegistry.create(
                    Component.translatable("itemGroup.unstable_ice"),
                    () -> new ItemStack(UnstableIceItems.UNSTABLE_ICE_BLOCK_ITEM.get())
            );

    public static void register() {
        System.out.println("Registering creative tabs...");
    }
}
