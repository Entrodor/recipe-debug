Recipe Debug mod for Necesse mod/modpack devs.  
Note: Server Debug Recipes not working _yet_

## How to use:
1. run client and server with this mod active
2. check client and server logs for the log entries from this mod
3. compare recipe hashes

## Example format:
```text
[2026-01-12 18:45:41] Recipe Debug: Result: chromaticspellbook Hash: -2076806922 RecipeID: necesse.inventory.recipe.Recipe@418bdde9 Ingredients: [bloodvolley x1 | shadowbolt x1 | swamptome x1 | ]
```
- Result: output item name
- Hash: Recipe Hash
- RecipeID: java objectid of the recipe
- Ingredients: list of ingredients and amounts

## future plans:
- output to a seperate file, not log file
- include mod name and workstation name for each recipe
- maybe sort recipes by mod?