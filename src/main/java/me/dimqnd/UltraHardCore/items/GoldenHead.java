package me.dimqnd.UltraHardCore.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.dimqnd.UltraHardCore.Main;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.stream.events.Namespace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GoldenHead {
    private Main mainReference;

    private final ItemStack IPLAYER_HEAD = new ItemStack(Material.PLAYER_HEAD, 1);
    private final ItemStack IGOLD_INGOT = new ItemStack(Material.GOLD_INGOT, 1);

    private List<ItemStack> materials = Arrays.asList(IGOLD_INGOT, IGOLD_INGOT, IGOLD_INGOT,
            IGOLD_INGOT, IPLAYER_HEAD, IGOLD_INGOT,
            IGOLD_INGOT, IGOLD_INGOT, IGOLD_INGOT);

    public GoldenHead(Main mainReference) { this.mainReference = mainReference; }

    public ItemStack returnItem() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString("57a8704d-b3f4-4c8f-bea0-64675011fe7b"), null);

        String signature = "CynaCoJtCYwcHEd53uSAQ54/MyAABTG4vdR6b3MxAjEuW4UBb3ekLKbv/JT4wY/gARQPdqvTu96XFRvGk+k8x7h6f4fiupOVXZcV0okC+TaQFYe5T1bhF/hKYxeQ9oJ5hWEMpWYUd5VmMcOKokfWp7IZJcOAcISu6bBFf1aoiJ2mLqi97yOD//7pxD9D/iVYZJ5rDLkImBGYsGxsIK7TZrPooIEmH6hI5Yn08GuEITVxdZuTo8SILRzzCQTNlObkFglSngCmGl4l5P4GNE0ITFIyjuATvugqVxdQ9XlFg2/bbesNP/rljZm9ut78GWaq4dAkWVmfQRDd8OAjEiMwhe2+gtRr4ylq2iP3Ag+3AAke++sfYrfpkzct7BGk9K08ya5MOi9XQrPJt529yP85IQPUW4hIIJX6g/CNWaEHm/blmw9umTv4l/lpDv79m3uWV/TeNX1seBAQ3lW3s6ZOvNFhFVHmrAB8C51nJTnPCww5mFRVgIbl/ZXg3WZZQhWZ4IkOPpva/nWQ9Jl02Ts9TGEKdNq9MHC9EbIrpky0G3LItgqF/RrdIgzVBS8myJVS6X4aB4ZvPkGR92YhO03u5+9IAXsyZSnvyn4EnYW5BQMqMYh2ztWMkqunS0YVAPLXQXje03u1/jpy+7g+2kQBhfG/vVL2aorN79zzt6yV7rY=";
        String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY1OTg0MTU1NTEyMCwKICAicHJvZmlsZUlkIiA6ICI1N2E4NzA0ZGIzZjQ0YzhmYmVhMDY0Njc1MDExZmU3YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaGFudG9tVHVwYWMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjk4Nzg4NTM2NTRjM2JiMjZmZDMzZTgwZjhlZDNjZGYwM2FiMzI0N2Y3MzY3ODQ2NGUwNmRhMTQzZGJkMGMxNyIKICAgIH0KICB9Cn0=";

        profile.getProperties().put("textures", new Property("textures", texture, signature));

        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);

        try {
            profileField.set(meta, profile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Golden head"));
        NamespacedKey key = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "golden-head");

        skull.setItemMeta(meta);

        return skull;
    }

    public void applyClickEffects(Player player) {
        ItemStack itemToRemove = player.getInventory().getItemInMainHand();
        itemToRemove.setAmount(itemToRemove.getAmount() - 1);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&aYou ate a &6Golden Head &aand gained 5 seconds of Regeneration IIII and 2 minutes of Absorption!"));
        player.sendMessage(ChatColor.GREEN + "You gained 21 seconds of Speed II");

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 3, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2 * 60 * 20, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 21 * 20, 1, false, false));
    }

    public List<ItemStack> getMaterials() { return materials; }
}
