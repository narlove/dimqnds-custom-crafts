package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class QuickPick {
    private Main mainReference;

    private final ItemStack IIRON_ORE = new ItemStack(Material.IRON_ORE, 1);
    private final ItemStack ISTICK = new ItemStack(Material.STICK, 1);
    private final ItemStack ICOAL = new ItemStack(Material.COAL, 1);
    private final ItemStack IAIR = new ItemStack(Material.AIR, 1);

    private List<ItemStack> materials = Arrays.asList(IIRON_ORE, IIRON_ORE, IIRON_ORE,
            ICOAL, ISTICK, ICOAL,
            IAIR, ISTICK, IAIR);

    public QuickPick(Main mainReference) { this.mainReference = mainReference; }

    public ItemStack returnItem() {
        ItemStack pick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta = pick.getItemMeta();

        itemMeta.addEnchant(Enchantment.DIG_SPEED, 1, false);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aQuick Pick"));

        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "quickpick");

        pick.setItemMeta(itemMeta);
        return pick;
    }

    public List<ItemStack> getMaterials() { return materials; }
}
