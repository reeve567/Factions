# Factions
<strong>How it works</strong>:

When the plugin is loaded, if there are any factions saved in the factions folder, it loads them into memory along with their claims.
The claims are stored in a claim manager which can be accessed to check if a chunk is claimed or not.
When a faction is created it is assigned a UUID.  This UUID is used to access it as well as the name, although the name can change.  In odrder to keep the factions straight, the factions are saved by their UUID and their current name is saved in that file.  The list of factions are stored in an ArrayList and can be accessed through the FactionManager.  Each faction is stored as a XFaction, which stores the config file associated with the faction, the groups, the name, and other properties of the faction.

