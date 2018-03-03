# Factions

<strong>Jenkins:</strong>

Use Jenkins to get the latest build of the plugin

Link: [here](http://xwy.pw)

Username: factions

Password: XtucnCYHwb20

<strong>How it works</strong>:

When the plugin is loaded, if there are any factions saved in the factions folder, it loads them into memory along with their claims.
The claims are stored in a claim manager which can be accessed to check if a chunk is claimed or not.
When a faction is created it is assigned a UUID.  This UUID is used to access it as well as the name, although the name can change.  In odrder to keep the factions straight, the factions are saved by their UUID and their current name is saved in that file.  The list of factions are stored in an ArrayList and can be accessed through the FactionManager.  Each faction is stored as a XFaction, which stores the config file associated with the faction, the groups, the name, and other properties of the faction.

<strong>From the doc:</strong>

Factions Plugin

Commands/What they do!

-/f create (name) - Will create a faction and set the creator of the faction as the leader.

-/f disband - Will disband the faction, and everyone in it will be set back to wilderness faction.

/f leave - Will leave the faction, the leader can’t leave the faction they must disband it.

/f join (factionname) - Will join the faction, if the faction is open you will join and be set as a recruit rank with no faction perms. If it does not  exist it will display a msg which i will list below.

/f open - will open the faction for all players to join.

/f close - will close the faction making it invite only.

/f ally (factionname) - will ally the faction if it exists, and if you ally someone that has requested to ally you, you would become allies.

/f neutral (factionname) - will set that allyship to a neutral faction. Will send a msg if that faction does not exist. 

/f enemy (factionname) - WIll enemy the faction, and if that faction does not exist it will send a msg to you in chat listed below.

/f c f - sets your chat to faction chat where you and your faction can only see the msgs.

/f c a - sets your chat to faction chat where you and your faction and allies see the msgs.

/f c a - sets your chat to global again, default where everyone will see your msgs.

/f who (name) - will show the factions information and if you do /f who with no faction name it will displays yours.

/f tag - Will change your faction name, eg faction name is LOl and you do /f tag Test, your faciton is now called test.

/f claim (radius) - Will claim land for your faction. No one can claim it unless you are overclaimable. If not set a raidus it will claim the chunk you are in

/f unclaim (radius) - Unclaims land from your faction. If you dont set a radius it unclaims the chunk you are in.

/f groups - will pull up a gui of all your faction groups gui sizes 9,18,27,36,45,54, I want it to be 9. Will display it later.

/f group create (name) - creates a groups for your faction, maxium number of groups you can create is 9.

/f group delete (name) - Will delete the faction group and all members in that group will be put in recruit which is default rank.

/f group (player) set (group) - this will set the player in the faction group said. They will get all perms that group has

/f group (group) prefix set - Will set a prefix for the group, allowed colors.

/f map - Will display all claims around you in a certain raidius, allies enimies neutral and your territory will be shown.

/f warp create (name) - Will create a warp for your members with permission to warp to it. Limit 5

/f warp delete (name) - will delete the warp.

/f sethome - will set the faction home for players with the permission to use it.

/fly - will enable players to fly, only in their territory tho, if an enemy is 50 blocks away fly will be disbaled but they wont take fall damage from it being disabled.

/f admin (name) - will give the player leader of the faction and you will be set to recruit rank default rank





Ops

Should be able to disband any faction even if they are not in it, should be able to join any faction and give them self leader and others leader of the faction.

Faction Group Rundown:

So basically there is 4 lines in an inventory i want 1 line to be shown, images will be displayed below So if the leader is to do /f groups it will display the list of groups you have in a 1 by 9  display below if you click on the group it will bring up anoither gui this is the perms for that group, you can click to enable the perm, the stained glass will become enchanted, then you can click to disbale then it will be unenchanted. It should be disbaled on default. Once you have a group created using the command in the commands list above you can set a prefix the prefix will be displayed in faction chat and in your faction information. You should be able to make the prefixes color coded for example &c&lWould be bolded red then&c&lTest, it should hide the color coding tho. In the factions plugin there should be a default rank which is called recruit, it has no perms and if you were to delete a groups all members in that group will be made member rank again if you were to give up your ownership you will become a recruit. You should be able to set players group to recruit aswell using /f group (player) set Recruit. Although this group is not visible in the /f groups, list.

Faction Chat Design

Should be displayed Like

\[Faction\] (group prefix) playername: Msg

Ally Chat Deisgn

Should be displayed like

\[Faction\] playername: Msg

There should be a large f map that shows 15 chunks north and south and 20 chunks east and west. This is displayed in f map, /f map.














CONFIG

There should be a config where we can add what ever we like example it could default have 5 pages long with 8 commands each menu.

----------------------FactionsHelp-----------------------

/f map - displays f map

/f create - creates a fac


This will go until it hits 8 commands then it should go to page 2 automaticalls so /f help 2,3,4,5 

Msgs

All these msgs should be configurable and should be able to be changed in the config. The msgs are:

Failed msg:

No faction with that name

A faction with that name is already created

You cant claim this land

You cant claim anymroe land

Created a faction

Left the faction

Joined a faction

Disbanded a faction

Created faction group

Removed faction group

Set group prefix

Enemied a faction

Sent ally request

Recieved allly request

Neutral a faction

FTOP

Ftop should contain a configurable but easy yml file or what ever the file is, so in the config it should have the ftop display, and what it would show if you hover over a faction name

It will also need adjustable config for all this including adjusting ftop prices.

All spawners should be shown in the plugin, and below that there should be one where i can add blocks for prices.

Fly

Faction fly is pretty simple the players will either be allowed with the permission to fly by there leader or not if they have permission they can do /fly to toggle there fly in faction territory, if an enemy is wihtin a 50 block radius you should be put out of fly and fall to the floor without taking falldamage from it. Factions fly should only work in your faction territory.
