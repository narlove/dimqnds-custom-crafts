package me.dimqnd.UltraHardCore;

import me.dimqnd.UltraHardCore.commands.CreateNPC;
import me.dimqnd.UltraHardCore.commands.DeleteNPC;
import me.dimqnd.UltraHardCore.commands.Ping;
import me.dimqnd.UltraHardCore.events.CustomCrafts;
import me.dimqnd.UltraHardCore.events.Events;
import me.dimqnd.UltraHardCore.events.RightClick;
import me.dimqnd.UltraHardCore.items.*;
import me.dimqnd.UltraHardCore.recipes.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Core coreInstance;

    private PlayerHead playerHeadInstance;
    private RecipeManager recipeManagerInstance;

    // TODO: Stop players wearing golden heads and player heads lmao
    // TODO: Add a cooldown for spamming heads
    // TODO: Make anything that checks hands be avaliable for both regular and offhand
    @Override
    public void onEnable() {
        coreInstance = new Core(this);
        playerHeadInstance = new PlayerHead(this);
        recipeManagerInstance = new RecipeManager(this);

        BasicCustomRecipe.registerRecipes();

        getServer().getPluginManager().registerEvents(new Events(this), this);
        getServer().getPluginManager().registerEvents(new RightClick(this), this);
        getServer().getPluginManager().registerEvents(new CustomCrafts(this), this);

        getCommand("ping").setExecutor(new Ping(this));
        getCommand("createnpc").setExecutor(new CreateNPC(this));
        getCommand("deletenpc").setExecutor(new DeleteNPC(this));

        BasicCustomRecipe.setMainReference(this);
        CraftingCooldowns.setMaximumValues();

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            new CraftingCooldowns(player.getUniqueId());
        }

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Dimqnd's UHC] Successfully enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Dimqnd's UHC] Successfully disabled!");
    }

    public Core getCoreInstance() { return coreInstance; }
    public RecipeManager getRecipeManagerInstance() { return recipeManagerInstance; }
    public PlayerHead getPlayerHeadInstance() { return playerHeadInstance; }
}
