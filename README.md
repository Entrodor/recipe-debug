Recipe Debug mod for Necesse mod/modpack devs.

## How to use:
1. run client and server with this mod active
2. check client and server logs for the log entries from this mod
3. compare recipes between client and server

## Example output:
There will be two types of output from the mod.  
### Output 1:
Logs when a mod adds a recipe.
```text
[2026-01-14 17:03:06] Recipe for spinelchestplate added by Aphorea Mod
```
### Output 2: 
Logs recipes added by mods/vanilla and the relevant recipe info.  
Format: (RecipeHash) (ModName) [\<ingredients>] = \<result> @ \<techLevel>
```text
[2026-01-14 17:03:06] Recipe Debug: (1127053226) (Aphorea Mod) [5x willowlog, 3x swampsludge, 2x stardust] = 1x swampchestplate @ ironanvil
```

## future plans:
- output to a seperate file, not main log file/console

## Credits:
- @Snoobinoob from the Necesse discord, for remaking the RecipeDebug class file.