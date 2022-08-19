package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerHead {
    private Main mainReference;

    public PlayerHead(Main mainReference) { this.mainReference = mainReference; }

    public ItemStack returnItem(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6" + player.getDisplayName() + "&c's head"));
        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "player-head");

        item.setItemMeta(meta);

        return item;
    }

    public void applyClickEffects(Player player) {
        ItemStack itemToRemove = player.getInventory().getItemInMainHand();
        itemToRemove.setAmount(itemToRemove.getAmount() - 1);

        player.sendMessage(ChatColor.GREEN + "You ate a player head and gained 4 seconds of Regeneration III!");
        player.sendMessage(ChatColor.GREEN + "You gained 21 seconds of Speed II");

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * 20, 2, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 21 * 20, 1, false, false));
    }
}
