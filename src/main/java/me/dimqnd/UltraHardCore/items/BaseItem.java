package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class BaseItem {

    public BaseItem(Main mainReference) { this.mainReference = mainReference; }
    protected Main mainReference;

    public abstract ItemStack returnItem();
    public abstract List<ItemStack> getMaterials();
}
