package me.dimqnd.UltraHardCore.items;

import com.google.common.collect.Multimap;
import me.dimqnd.UltraHardCore.Main;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import org.apache.commons.collections4.MultiMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.jar.Attributes;

public class DragonSword extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public DragonSword(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.BLAZE_POWDER));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.DIAMOND_SWORD));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.OBSIDIAN));
        materials.add(new ItemStack(Material.BLAZE_POWDER));
        materials.add(new ItemStack(Material.OBSIDIAN));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aDragon Sword"));

        meta = mainReference.getCoreInstance().applyNamespacedKey(meta, "dragon-sword");

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 8.0,
        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -2.4,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speedModifier);

        sword.setItemMeta(meta);
        return sword;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
