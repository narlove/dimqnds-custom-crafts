package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LightAnvil extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public LightAnvil(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.IRON_INGOT));
        materials.add(new ItemStack(Material.IRON_INGOT));
        materials.add(new ItemStack(Material.IRON_INGOT));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.IRON_BLOCK));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.IRON_INGOT));
        materials.add(new ItemStack(Material.IRON_INGOT));
        materials.add(new ItemStack(Material.IRON_INGOT));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack anvil = new ItemStack(Material.ANVIL);
        ItemMeta meta = anvil.getItemMeta();

        meta = mainReference.getCoreInstance().applyNamespacedKey(meta, "light-anvil");

        anvil.setItemMeta(meta);
        return anvil;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
