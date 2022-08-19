package me.dimqnd.UltraHardCore.items;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HermesBoots extends BaseItem {

    private List<ItemStack> materials = new ArrayList<>();

    public HermesBoots(Main mainReference) {
        super(mainReference);

        materials.add(new ItemStack(Material.DIAMOND));
        materials.add(new ItemStack(Material.PLAYER_HEAD));
        materials.add(new ItemStack(Material.DIAMOND));
        materials.add(new ItemStack(Material.BLAZE_POWDER));
        materials.add(new ItemStack(Material.DIAMOND_BOOTS));
        materials.add(new ItemStack(Material.BLAZE_POWDER));
        materials.add(new ItemStack(Material.FEATHER));
        materials.add(new ItemStack(Material.AIR));
        materials.add(new ItemStack(Material.FEATHER));
    }

    @Override
    public ItemStack returnItem() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = boots.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aHermes' Boots"));

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addEnchant(Enchantment.DURABILITY, 2, false);

        meta = mainReference.getCoreInstance().applyNamespacedKey(meta, "hermes-boots");

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", 0.1,
                AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);

        AttributeModifier normalMod1 = new AttributeModifier(UUID.randomUUID(), "generic.armour", 3,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);

        AttributeModifier normalMod2 = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 2,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);

        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, normalMod1);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, normalMod2);

        boots.setItemMeta(meta);
        return boots;
    }

    @Override
    public List<ItemStack> getMaterials() {
        return materials;
    }
}
