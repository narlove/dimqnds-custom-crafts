package me.dimqnd.UltraHardCore.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
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

public class Tarnhelm {
    private Main mainReference;

    private final ItemStack IDIAMOND = new ItemStack(Material.DIAMOND, 1);
    private final ItemStack IREDSTONE_BLOCK = new ItemStack(Material.REDSTONE_BLOCK, 1);
    private final ItemStack IIRON_INGOT = new ItemStack(Material.IRON_INGOT, 1);
    private final ItemStack IAIR = new ItemStack(Material.AIR, 1);

    private List<ItemStack> materials = Arrays.asList(IDIAMOND, IIRON_INGOT, IDIAMOND,
            IDIAMOND, IREDSTONE_BLOCK, IDIAMOND,
            IAIR, IAIR, IAIR);

    public Tarnhelm(Main mainReference) { this.mainReference = mainReference; }

    public ItemStack returnItem() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta meta = helmet.getItemMeta();

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        meta.addEnchant(Enchantment.WATER_WORKER, 3, true);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aTarnhelm"));

        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "tarnhelm");

        helmet.setItemMeta(meta);
        return helmet;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
