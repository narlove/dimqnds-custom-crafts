package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HideOfLeviathan extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public HideOfLeviathan(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.LAPIS_BLOCK));
        materials.add(new ItemStack(Material.WATER_BUCKET));
        materials.add(new ItemStack(Material.LAPIS_BLOCK));
        materials.add(new ItemStack(Material.DIAMOND));
        materials.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        materials.add(new ItemStack(Material.DIAMOND));
        materials.add(new ItemStack(Material.LILY_PAD));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.LILY_PAD));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta meta = pants.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aHide of Leviathan"));

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        meta.addEnchant(Enchantment.OXYGEN, 2, true);
        meta.addEnchant(Enchantment.WATER_WORKER, 1, false);

        meta = mainReference.getCoreInstance().applyNamespacedKey(meta, "hide-of-leviathan");

        pants.setItemMeta(meta);

        return pants;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
