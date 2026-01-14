package floabgdebug;

// Thanks to @Snoobinoob from Necesse discord for this BETTER Recipe Debug code.
import necesse.engine.modLoader.LoadedMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import net.bytebuddy.asm.Advice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class RecipeDebug {
    public static final HashMap<Recipe, LoadedMod> recipeToModList = new HashMap<>();

    @ModMethodPatch(target = Recipes.class, name = "registerModRecipe", arguments = {Recipe.class})
    public static class RegisterModRecipePatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.Argument(0) Recipe recipe) {
            LoadedMod mod = LoadedMod.getRunningMod();
            System.out.println("Recipe for " + recipe.resultStringID + " added by " + mod.name);
            recipeToModList.put(recipe, mod);
        }
    }

    @ModMethodPatch(target = Recipes.class, name = "closeModRecipeRegistry", arguments = {})
    public static class CloseModRecipeRegistryPatch {
        @Advice.OnMethodExit
        public static void onExit() {
            for (Recipe recipe : Recipes.getRecipes()) {
                System.out.printf(
                        "Recipe Debug: (%s) (%s) %s = %dx %s @ %s\n",
                        recipe.getRecipeHash(),
                        recipeToModList.containsKey(recipe) ? recipeToModList.get(recipe).name : "Vanilla",
                        formatIngredients(recipe),
                        recipe.resultAmount,
                        recipe.resultStringID,
                        recipe.tech.getStringID()
                );
            }
        }
    }

    public static String formatIngredients(Recipe recipe) {
        return "[" +
                Arrays.stream(recipe.ingredients)
                        .map(i -> i.getIngredientAmount() + "x " + i.ingredientStringID)
                        .collect(Collectors.joining(", ")) +
                "]";
    }
}