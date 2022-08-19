package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

import java.util.ArrayList;
import java.util.List;

public class SharpnessBook extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public SharpnessBook(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.FLINT));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.PAPER));
        materials.add(new ItemStack(Material.PAPER));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.PAPER));
        materials.add(new ItemStack(Material.IRON_SWORD));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();

        meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, false);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eEnchanted Book"));

        meta = (EnchantmentStorageMeta) mainReference.getCoreInstance().applyNamespacedKey(meta, "sharpness-book");

        book.setItemMeta(meta);
        return book;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
