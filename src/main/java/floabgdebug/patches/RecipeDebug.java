package floabgdebug.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.state.MainMenu;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.inventory.recipe.Ingredient;
import net.bytebuddy.asm.Advice;

import java.util.Arrays;
import java.util.Iterator;

@ModMethodPatch(target = MainMenu.class, name = "init", arguments = {})
public class RecipeDebug {

    public static String formatIngredients(Ingredient[] ingredients) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Ingredient ing : ingredients) {
            sb.append(ing.ingredientStringID)
                    .append(" x")
                    .append(ing.getIngredientAmount())
                    .append(" | ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Advice.OnMethodExit()
    static void onExit(@Advice.This MainMenu menu) throws NoSuchFieldException {

        Iterator recipes = Recipes.getRecipes().iterator();
        while (recipes.hasNext()) {
            Recipe recipe = (Recipe) recipes.next();

            String recipeResult = recipe.resultStringID;
            int recipeHash = recipe.getRecipeHash();
            String recipeIngredients = formatIngredients(recipe.ingredients);

            System.out.println("Recipe Debug: Result: "+ recipeResult +" Hash: "+ recipeHash +" RecipeID: "+recipe+" Ingredients: "+ recipeIngredients);
        }
    }
}
