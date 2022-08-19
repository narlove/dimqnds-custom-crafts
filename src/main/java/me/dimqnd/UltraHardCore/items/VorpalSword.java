package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class VorpalSword extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public VorpalSword(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.BONE));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.IRON_SWORD));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.ROTTEN_FLESH));
        materials.add(new ItemStack(Material.AIR));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = sword.getItemMeta();

        meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 2, false);
        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, false);
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 2, false);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aVorpal Sword"));

        meta = mainReference.getCoreInstance().applyNamespacedKey(meta, "vorpal-sword");

        sword.setItemMeta(meta);
        return sword;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
