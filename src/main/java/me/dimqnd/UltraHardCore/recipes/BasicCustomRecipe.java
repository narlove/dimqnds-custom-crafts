package me.dimqnd.UltraHardCore.recipes;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BasicCustomRecipe {

    public static List<BasicCustomRecipe> allRecipes = new ArrayList<>();

    private static Main mainReference;

    private ItemStack result;
    private List<ItemStack> materials;

    private ShapedRecipe recipe;
    public int maximumCrafts;

    public BasicCustomRecipe(List<ItemStack> materials, ItemStack result, String key, int maximumCrafts) {
        //mainReference = mainReferenceInput;
        this.materials = materials;
        this.result = result;
        this.maximumCrafts = maximumCrafts;

        recipe = new ShapedRecipe(NamespacedKey.minecraft(key), result);
        recipe.shape("abc", "def", "ghi");

        for (int i = 0; i < 9; i++) {
            if (materials.get(i) == null || materials.get(i).getType() == Material.AIR) continue;
            RecipeChoice.MaterialChoice itemData = new RecipeChoice.MaterialChoice(materials.get(i).getType());
            recipe.setIngredient((char)(i+'a'), itemData);
        }

        allRecipes.add(this);
    }

    public static void setMainReference(Main inputMainReference) {
        mainReference = inputMainReference;
    }

    public static void registerRecipes() {
        for (BasicCustomRecipe recipe : allRecipes) {
            Bukkit.getServer().addRecipe(recipe.getRecipe());
        }
    }

    public static BasicCustomRecipe identifyRecipeByResult(ItemStack result) {
        if (result == null || result.getType() == Material.AIR) return null;
        String resultCustomTag = result.getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING);
        if (resultCustomTag == null) return null;

        for (BasicCustomRecipe recipe : allRecipes) {
            String recipeCustomTag = recipe.getResult().getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING);

            if (recipeCustomTag.equals(resultCustomTag)) {
                return recipe;
            }
        }

        return null;
    }

    // Getters

    public ShapedRecipe getRecipe() {
        return recipe;
    }

    public ItemStack getResult() {
        return result;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
}
