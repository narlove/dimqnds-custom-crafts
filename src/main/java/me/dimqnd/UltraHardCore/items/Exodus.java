package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Exodus {
    private Main mainReference;

    private final ItemStack IDIAMOND = new ItemStack(Material.DIAMOND, 1);
    private final ItemStack IPLAYER_HEAD = new ItemStack(Material.PLAYER_HEAD, 1);
    private final ItemStack IGOLDEN_CARROT = new ItemStack(Material.GOLDEN_CARROT, 1);
    private final ItemStack IEMEREALD = new ItemStack(Material.EMERALD, 1);

    private List<ItemStack> materials = Arrays.asList(IDIAMOND, IDIAMOND, IDIAMOND,
            IDIAMOND, IPLAYER_HEAD, IDIAMOND,
            IEMEREALD, IGOLDEN_CARROT, IEMEREALD);

    public Exodus(Main mainReference) { this.mainReference = mainReference; }

    public ItemStack returnItem() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta meta = helmet.getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aExodus"));
        List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&9Aeon"));
        meta.setLore(lore);

        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "exodus");

        helmet.setItemMeta(meta);
        return helmet;
    }

    public List<ItemStack> getMaterials() { return materials; }
}
