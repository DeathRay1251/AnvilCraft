package dev.dubhe.anvilcraft.event;

import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.api.event.anvil.AnvilFallOnLandEvent;
import dev.dubhe.anvilcraft.api.event.anvil.AnvilHurtEntityEvent;
import dev.dubhe.anvilcraft.api.event.anvil.AnvilEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = AnvilCraft.MOD_ID)
public class AnvilEntityEventListener {
    /**
     * @param e 铁砧落地事件
     */
    @SubscribeEvent
    public static void onLand(@NotNull AnvilEvent.OnLand e) {
        AnvilFallOnLandEvent event = new AnvilFallOnLandEvent(e.getLevel(), e.getPos(), e.getEntity(), e.getFallDistance());
        NeoForge.EVENT_BUS.post(event);
        e.setAnvilDamage(event.isAnvilDamage());
    }

    /**
     * @param e 铁砧伤害实体事件
     */
    @SubscribeEvent
    public static void hurt(@NotNull AnvilEvent.HurtEntity e) {
        NeoForge.EVENT_BUS.post(new AnvilHurtEntityEvent(e.getEntity(), e.getPos(), e.getLevel(), e.getHurtedEntity(), e.getDamage()));
    }
}
