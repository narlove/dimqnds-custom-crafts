package me.dimqnd.UltraHardCore.events;

import me.dimqnd.UltraHardCore.CraftingCooldowns;
import me.dimqnd.UltraHardCore.recipes.BasicCustomRecipe;
import me.dimqnd.UltraHardCore.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CustomCrafts implements Listener {
    private Main mainReference;

    public CustomCrafts(Main mainReference) {
        this.mainReference = mainReference;
    }

    // Custom crafts

    @EventHandler
    public void onCraftAttempt(PrepareItemCraftEvent e) {
        CraftingInventory inv = e.getInventory();
        ItemStack[] itemMatrix = inv.getMatrix();

        if (e.getInventory().getResult() == null || e.getInventory().getResult().getType() == Material.AIR) return;
        if (!(e.getView().getPlayer() instanceof Player)) return;

        Player player = (Player) e.getView().getPlayer();

        BasicCustomRecipe recipe = BasicCustomRecipe.identifyRecipeByResult(e.getInventory().getResult());

        if (recipe == null) return;
        CraftingCooldowns playerCraftCooldowns = CraftingCooldowns.allPlayersWithCooldowns.get(player.getUniqueId());
        if (playerCraftCooldowns == null) return;

        boolean flag = true;
        for (int i = 0; i < 9 && flag; i++) {
            if (e.getInventory().getItem(i+1) == null || e.getInventory().getItem(i+1).getType() == Material.AIR)
                flag = recipe.getMaterials().get(i).getType() == Material.AIR;

            else if (e.getInventory().getItem(i+1).getType() == Material.PLAYER_HEAD) {
                if (recipe.getMaterials().get(i).getType() != Material.PLAYER_HEAD) { flag = false; break; }
                boolean hasCode = false;
                try {
                     hasCode = e.getInventory().getItem(i+1).getItemMeta().getPersistentDataContainer()
                             .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                             .equals("player-head");
                } catch (NullPointerException npe) { flag = false; break; }

                flag = hasCode;
            }

            else flag = recipe.getMaterials().get(i).isSimilar(e.getInventory().getItem(i+1));
        }

        if (!flag) e.getInventory().setResult(new ItemStack(Material.AIR));
        if (!playerCraftCooldowns.isAllowedCraft(recipe.getRecipe().getKey())) e.getInventory().setResult(new ItemStack(Material.AIR));
    }

    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        if (!(e.getView().getPlayer() instanceof Player)) return;

        Player player = (Player) e.getView().getPlayer();

        BasicCustomRecipe recipe = BasicCustomRecipe.identifyRecipeByResult(e.getInventory().getResult());
        if (recipe == null) return;

        CraftingCooldowns playerCraftCooldowns = CraftingCooldowns.allPlayersWithCooldowns.get(player.getUniqueId());
        if (playerCraftCooldowns == null) return;

        playerCraftCooldowns.incrementCraft(recipe.getRecipe().getKey());
        playerCraftCooldowns.messagePlayer(recipe.getRecipe().getKey(), player);
    }
}