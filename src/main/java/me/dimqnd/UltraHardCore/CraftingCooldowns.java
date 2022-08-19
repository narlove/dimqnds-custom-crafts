package me.dimqnd.UltraHardCore;

import me.dimqnd.UltraHardCore.recipes.BasicCustomRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

// TODO: Fix shift clickng
// TODO: Make sure to add a command that resets a specific craft for a player (by the namespacedkey) or all recipes
// TODO: MAYBE make a command that turns on infinite recipes for a player
public class CraftingCooldowns {

    public static Map<UUID, CraftingCooldowns> allPlayersWithCooldowns = new HashMap<>();
    private UUID playerUUID;
    private Player playerObject;
    private static Main mainReference;
    private HashMap<NamespacedKey, Integer> timesCrafted = new HashMap<>();
    private static HashMap<NamespacedKey, Integer> maximumValues = new HashMap<>();
    
    public static void setMaximumValues() {
        for (BasicCustomRecipe recipe : BasicCustomRecipe.allRecipes) {
            maximumValues.put(recipe.getRecipe().getKey(), recipe.maximumCrafts);
        }
    }

    public static void setMainReference(Main inputMainReference) {
        mainReference = inputMainReference;
    }

    public CraftingCooldowns(UUID playerUUID) {
        this.playerUUID = playerUUID;

        this.playerObject = Bukkit.getPlayer(this.playerUUID);

        for (BasicCustomRecipe recipe : BasicCustomRecipe.allRecipes) {
            timesCrafted.put(recipe.getRecipe().getKey(), 0);
        }

        allPlayersWithCooldowns.put(playerUUID, this);
    }

    public boolean isAllowedCraft(NamespacedKey recipeKey) {
        if (!timesCrafted.containsKey(recipeKey)) return false;
        if (timesCrafted.get(recipeKey) == null) return false;
        if (maximumValues.get(recipeKey) == null) return false;
        if (timesCrafted.get(recipeKey) >= maximumValues.get(recipeKey)) return false;

        return true;
    }

    public void incrementCraft(NamespacedKey recipeKey) {
        if (!timesCrafted.containsKey(recipeKey)) return;
        if (timesCrafted.get(recipeKey) == null) return;
        timesCrafted.put(recipeKey, (timesCrafted.get(recipeKey) + 1));
    }

    public void messagePlayer(NamespacedKey recipeKey, Player player) {
        if (!timesCrafted.containsKey(recipeKey)) return;
        if (timesCrafted.get(recipeKey) == null) return;
        if (player == null) return;

        ItemStack resultItem = null;
        for (BasicCustomRecipe recipe : BasicCustomRecipe.allRecipes) {
            NamespacedKey resultRecipeKey = recipe.getRecipe().getKey();
            if (!recipeKey.equals(resultRecipeKey)) continue;
            resultItem = recipe.getResult();
            break;
        }

        if (resultItem == null) return;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou have crafted the &a" + ChatColor.stripColor(resultItem.getItemMeta().getDisplayName()) + "&e! &e(" + timesCrafted.get(recipeKey) + "/" + maximumValues.get(recipeKey) + ")"));
    }
}
