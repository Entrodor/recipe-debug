// Not yet working, need to find the ModMethodPatch class for servers
package floabgdebug.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.networkInfo.NetworkInfo;
import necesse.engine.network.server.Server;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import net.bytebuddy.asm.Advice;

import java.util.Iterator;

// RecipeServerPatch code from here: https://github.com/Jaco909/Ranged-Arsenal/blob/main/main/java/rangedarsenal/patches/RecipePatchDedicatedServer.java
@ModMethodPatch(target = Server.class, name = "addClient", arguments = {NetworkInfo.class, long.class, String.class, boolean.class, boolean.class})
public class RecipeDebugServer {

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
    static void onExit(@Advice.This Server server) {
        Iterator recipes = Recipes.getRecipes().iterator();
        while (recipes.hasNext()) {
            Recipe recipe = (Recipe) recipes.next();

            String recipeResult = recipe.resultStringID;
            int recipeHash = recipe.getRecipeHash();
            String recipeIngredients = formatIngredients(recipe.ingredients);

            System.out.println("(Server) Recipe Debug: Result: "+ recipeResult +" Hash: "+ recipeHash +" RecipeID: "+recipe+" Ingredients: "+ recipeIngredients);
        }
    }
}