package me.dimqnd.UltraHardCore.events;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RightClick implements Listener {

    private Main mainReference;

    public RightClick(Main mainReference) { this.mainReference = mainReference; }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getHand().equals(EquipmentSlot.HAND)) return;

        // if the player is holding a custom head in either of their hands
        if (mainReference.getCoreInstance().checkHandsForItems(e.getPlayer(), "player-head")) {
            mainReference.getPlayerHeadInstance().applyClickEffects(e.getPlayer());
        } else if (mainReference.getCoreInstance().checkHandsForItems(e.getPlayer(), "golden-head")) {
            mainReference.getRecipeManagerInstance().getGoldenHeadFile().applyClickEffects(e.getPlayer());
        } else {
            return; // because it must not be a custom item
        }
    }

}
