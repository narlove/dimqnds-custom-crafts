package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class PhilosophersPickaxe {

    private Main mainReference;

    public PhilosophersPickaxe(Main mainReference) { this.mainReference = mainReference; }

    private final ItemStack IIRON_ORE = new ItemStack(Material.IRON_ORE, 1);
    private final ItemStack ILAPIS_BLOCK = new ItemStack(Material.LAPIS_BLOCK, 1);
    private final ItemStack IGOLD_ORE = new ItemStack(Material.GOLD_ORE, 1);
    private final ItemStack ISTICK = new ItemStack(Material.STICK, 1);
    private final ItemStack IAIR = new ItemStack(Material.AIR, 1);

    private List<ItemStack> materials = Arrays.asList(IIRON_ORE, IGOLD_ORE, IIRON_ORE,
            ILAPIS_BLOCK, ISTICK, ILAPIS_BLOCK,
            IAIR, ISTICK, IAIR);

    public ItemStack returnItem() {
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta meta = pick.getItemMeta();

        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, false);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aPhilosopher's Pickaxe"));

        Damageable itemDamageable = (Damageable) meta;
        itemDamageable.setDamage(1559);

        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "philosophers-pickaxe");

        pick.setItemMeta(meta);
        return pick;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
