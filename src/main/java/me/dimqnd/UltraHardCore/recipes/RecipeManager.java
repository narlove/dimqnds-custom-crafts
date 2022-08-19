package me.dimqnd.UltraHardCore.recipes;

import me.dimqnd.UltraHardCore.CraftingCooldowns;
import me.dimqnd.UltraHardCore.Main;

import me.dimqnd.UltraHardCore.items.*;
import me.dimqnd.UltraHardCore.recipes.*;

import javax.swing.plaf.basic.BasicSeparatorUI;
import java.util.Arrays;

public class RecipeManager {
    // TO ADD AN ITEM:
    // Create a new item in me.dimqnd.UltraHardCore.items that 'extends BaseItem'
    // Create the item recipe and the ItemStack in there
    // Come to this file, and in RecipeManager() create a reference to the file of the object
    // Then, go further down into RecipeManager() and create a BasicCustomRecipe instance, taking appropriate functions from the file reference

    private Main mainReference;

    // Golden head has a getter because its item file has an applyClickEffects function and we need a getter
    private GoldenHead goldenHeadFile;

    // TODO: need to make separate recipes for recipes that are shaped but can be crafted in multiple ways (vorpal sword)
    // TODO: make all items extend BaseItem incase we need any generics in the future
    public RecipeManager(Main mainReference) {
        this.mainReference = mainReference;

        Exodus exodusFile = new Exodus(mainReference);
        Anduril andurilFile = new Anduril(mainReference);
        DragonSword dragonSwordFile = new DragonSword(mainReference);
        HermesBoots hermesBootsFile = new HermesBoots(mainReference);
        HideOfLeviathan hideOfLeviathanFile = new HideOfLeviathan(mainReference);

        PhilosophersPickaxe philosophersPickaxeFile = new PhilosophersPickaxe(mainReference);
        Tarnhelm tarnhelmFile = new Tarnhelm(mainReference);

        QuickPick quickPickFile = new QuickPick(mainReference);
        goldenHeadFile = new GoldenHead(mainReference);
        VorpalSword vorpalSwordFile = new VorpalSword(mainReference);
        SharpnessBook sharpnessBookFile = new SharpnessBook(mainReference);
        PowerBook powerBookFile = new PowerBook(mainReference);
        LightAnvil lightAnvilFile = new LightAnvil(mainReference);

        // Extra ultimates
        BasicCustomRecipe exodus = new BasicCustomRecipe(exodusFile.getMaterials(), exodusFile.returnItem(), "exodus-recipe", 1);
        BasicCustomRecipe anduril = new BasicCustomRecipe(andurilFile.getMaterials(), andurilFile.returnItem(), "anduril-recipe", 1);
        BasicCustomRecipe dragonSword = new BasicCustomRecipe(dragonSwordFile.getMaterials(), dragonSwordFile.returnItem(), "dragon-sword-recipe", 1);
        BasicCustomRecipe hermesBoots = new BasicCustomRecipe(hermesBootsFile.getMaterials(), hermesBootsFile.returnItem(), "hermes-boots-recipe", 1);
        BasicCustomRecipe hideOfLeviathan = new BasicCustomRecipe(hideOfLeviathanFile.getMaterials(),
                hideOfLeviathanFile.returnItem(), "hide-of-leviathan-recipe", 1);

        // Ultimates
        BasicCustomRecipe philosophersPickaxe = new BasicCustomRecipe(philosophersPickaxeFile.getMaterials(),
                philosophersPickaxeFile.returnItem(), "philosophers-pickaxe-recipe", 2);
        BasicCustomRecipe tarnhelm = new BasicCustomRecipe(tarnhelmFile.getMaterials(), tarnhelmFile.returnItem(), "tarnhelm-recipe", 2);

        // Recipes
        BasicCustomRecipe quickPick = new BasicCustomRecipe(quickPickFile.getMaterials(), quickPickFile.returnItem(), "quick-pick-recipe", 4);
        BasicCustomRecipe goldenHead = new BasicCustomRecipe(goldenHeadFile.getMaterials(), goldenHeadFile.returnItem(), "golden-head-recipe", 4);
        BasicCustomRecipe vorpalSword = new BasicCustomRecipe(vorpalSwordFile.getMaterials(), vorpalSwordFile.returnItem(), "vorpal-sword-recipe", 4);
        BasicCustomRecipe sharpnessBook = new BasicCustomRecipe(sharpnessBookFile.getMaterials(), sharpnessBookFile.returnItem(), "sharp-book-recipe", 4);
        BasicCustomRecipe powerBook = new BasicCustomRecipe(powerBookFile.getMaterials(), powerBookFile.returnItem(), "power-book-recipe", 4);
        BasicCustomRecipe lightAnvil = new BasicCustomRecipe(lightAnvilFile.getMaterials(), lightAnvilFile.returnItem(), "light-anvil-recipe", 4);

    }

    public GoldenHead getGoldenHeadFile() {
        return goldenHeadFile;
    }
}
