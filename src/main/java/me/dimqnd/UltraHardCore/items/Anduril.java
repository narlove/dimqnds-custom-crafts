package me.dimqnd.UltraHardCore.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Anduril {
    private Main mainReference;

    public Anduril(Main mainReference) { this.mainReference = mainReference; }

    private final ItemStack IFEATHER = new ItemStack(Material.FEATHER, 1);
    private final ItemStack IIRON_BLOCK = new ItemStack(Material.IRON_BLOCK, 1);
    private final ItemStack IBLAZE_ROD = new ItemStack(Material.BLAZE_ROD, 1);

    private List<ItemStack> materials = Arrays.asList(IFEATHER, IIRON_BLOCK, IFEATHER,
            IFEATHER, IIRON_BLOCK, IFEATHER,
            IFEATHER, IBLAZE_ROD, IFEATHER);

    public ItemStack returnItem() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aAndúril"));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);

        List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&r&9Resistance I (While Holding)"),
                ChatColor.translateAlternateColorCodes('&', "&r&9Speed I (While Holding)"),
                ChatColor.translateAlternateColorCodes('&', "&r&9Justice"));
        meta.setLore(lore);

        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "anduril");

        sword.setItemMeta(meta);
        return sword;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
