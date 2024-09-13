package dev.dubhe.anvilcraft.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

import org.jetbrains.annotations.NotNull;

public class AmethystSwordItem extends SwordItem {
    /**
     *
     */
    public AmethystSwordItem(Properties properties) {
        super(ModTiers.AMETHYST, properties.attributes(AxeItem.createAttributes(ModTiers.AMETHYST, 3, -2.4f)));
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        return stack;
    }
}
