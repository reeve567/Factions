package pw.xwy.Factions.utility.handlers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Sell;
import pw.xwy.Factions.objects.Glow;
import pw.xwy.Factions.objects.ItemBuilder;
import pw.xwy.Factions.objects.faction.XRank;
import pw.xwy.Factions.utility.ItemUtility;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.inventories.*;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class InventoryHandler implements Listener {
	
	private Economy eco;
	
	public InventoryHandler(Economy eco) {
		this.eco = eco;
	}
	
	private boolean acCheck(InventoryAction a, InventoryClickEvent e) {
		return e.getAction().equals(a);
	}
	
	private boolean balCheck(Player p, Double price) {
		return (eco.getBalance(p) - price) >= 0;
	}
	
	private void balRem(Player p, Double price) {
		eco.withdrawPlayer(p, price);
	}
	
	private int[] calcTotal(InventoryClickEvent e) {
		int price = 0;
		int total = 0;
		int amount = 0;
		for (int i = 0; i < 27; i++) {
			for (Sell s : Sell.values()) {
				if (e.getInventory() != null && e.getInventory().getItem(i) != null && s.getMaterial().equals(e.getInventory().getItem(i).getType())) {
					price = (int) (s.getPrice() * e.getInventory().getItem(i).getAmount());
					amount += e.getInventory().getItem(i).getAmount();
				}
			}
			total += price;
			price = 0;
		}
		return new int[]{total, amount};
	}
	
	private ItemStack toggle(boolean toggled, ItemStack it, XRank rank) {
		ItemBuilder builder = new ItemBuilder(it);
		String s = it.getItemMeta().getDisplayName().substring(4);
		String prefix;
		if (toggled) {
			builder.removeGlow();
			prefix = "&c&l";
			rank.removePerm(PermissionsSubMenu.perms.get(s));
		} else {
			builder.addGlow();
			prefix = "&a&l";
			rank.addPerm(PermissionsSubMenu.perms.get(s));
		}
		it = builder.setName(prefix + s).get();
		return it;
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Shops")) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
				String name = e.getCurrentItem().getItemMeta().getDisplayName();
				if (StringUtility.colorCompare(name, "&c&lRaiding Shop"))
					e.getWhoClicked().openInventory(RaidingMenu.getInv());
				else if (StringUtility.colorCompare(name, "&a&lBrewing Shop"))
					e.getWhoClicked().openInventory(BrewingMenu.getInv());
				else if (StringUtility.colorCompare(name, "&3&lSpawners Shop"))
					e.getWhoClicked().openInventory(SpawnersMenu.getInv());
				else if (StringUtility.colorCompare(name, "&b&lCombat Shop")) {
					e.getWhoClicked().openInventory(CombatMenu.getInv());
				} else if (StringUtility.colorCompare(name, "&2&lFarming Shop"))
					e.getWhoClicked().openInventory(FarmingMenu.getInv());
				else if (StringUtility.colorCompare(name, "&9&lTools Shop"))
					e.getWhoClicked().openInventory(ToolsMenu.getInv());
				else if (StringUtility.colorCompare(name, "&5&lBuilding Shop"))
					e.getWhoClicked().openInventory(BuildingMenu.getInv());
				else if (StringUtility.colorCompare(name, "&f&lMisc Shop"))
					e.getWhoClicked().openInventory(MiscMenu.getInv());
				else if (StringUtility.colorCompare(name, "&6&lSell Shop")) {
					e.getWhoClicked().openInventory(SellMenu.getInv((Player) e.getWhoClicked()));
				}
			}
		} else if (e.getInventory().getName().equals(ToolsMenu.getInv().getName())) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasLore()) {
					double price = Double.parseDouble(e.getCurrentItem().getItemMeta().getLore().get(0).substring(5, e.getCurrentItem().getItemMeta().getLore().get(0)
							.length()));
					if (balCheck((Player) e.getWhoClicked(), price)) {
						if (e.getWhoClicked().getInventory().firstEmpty() != -1) {
							if (e.getCurrentItem().getType() != Material.MONSTER_EGG) {
								BuyMenu.open((Player) e.getWhoClicked(), new ItemStack(e.getCurrentItem().getType(), 1, e.getCurrentItem().getDurability()), e.getCurrentItem().getItemMeta(), price);
							} else {
								short dur = e.getCurrentItem().getDurability();
								String type = "";
								switch (dur) {
									case 99:
										type = "IronGolem";
										break;
									case 61:
										type = "Blaze";
										break;
									case 55:
										type = "Slime";
										break;
									case 54:
										type = "Zombie";
										break;
									case 66:
										type = "Witch";
										break;
									case 51:
										type = "Skeleton";
										break;
									case 93:
										type = "Chicken";
										break;
									case 92:
										type = "Cow";
										break;
									case 91:
										type = "Sheep";
										break;
									case 96:
										type = "Mooshroom";
										break;
									case 90:
										type = "Pig";
										break;
									case 94:
										type = "Squid";
										break;
									case 65:
										type = "Bat";
										break;
									case 57:
										type = "ZombiePigman";
										break;
									case 95:
										type = "Wolf";
										break;
									case 98:
										type = "Ocelot";
										break;
									case 52:
										type = "Spider";
										break;
									case 59:
										type = "CaveSpider";
										break;
									case 62:
										type = "MagmaCube";
										break;
									case 50:
										type = "Creeper";
										break;
								}
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ss give " + e.getWhoClicked().getName() + " " + type);
							}
						}
					}
				} else {
					if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
						String name = e.getCurrentItem().getItemMeta().getDisplayName();
						if (StringUtility.colorCompare(name, "&aNext Page")) {
							if (e.getInventory().getItem(0).getType().equals(Material.STONE))
								e.getWhoClicked().openInventory(BuildingMenu.getInv2());
							if (e.getInventory().getItem(0).getType().equals(Material.DIAMOND_BLOCK))
								e.getWhoClicked().openInventory(BuildingMenu.getInv3());
						} else if (StringUtility.colorCompare(name, "&cPrevious Page")) {
							if (e.getInventory().getItem(0).getType().equals(Material.STONE))
								e.setCancelled(true);
							else if (e.getInventory().getItem(0).getType().equals(Material.DIAMOND_BLOCK))
								e.getWhoClicked().openInventory(BuildingMenu.getInv());
							else if (e.getInventory().getItem(0).getType().equals(Material.ENDER_STONE))
								e.getWhoClicked().openInventory(BuildingMenu.getInv2());
						}
					}
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase(SellMenu.getInv((Player) e.getWhoClicked()).getName())) {
			if (e.getRawSlot() <= 44) {
				if (acCheck(InventoryAction.PICKUP_ALL, e) || acCheck(InventoryAction.PICKUP_HALF, e) || acCheck(InventoryAction.PICKUP_ONE, e) || acCheck(InventoryAction.PICKUP_SOME, e)) {
					if (e.getCurrentItem().getType() == Material.GOLD_NUGGET) {
						e.setCancelled(true);
					} else if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
						if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
							if (e.getCurrentItem().getDurability() == 5) {
								int[] calc = calcTotal(e);
								int total = calc[0];
								int amount = calc[1];
								e.getWhoClicked().closeInventory();
								eco.depositPlayer((OfflinePlayer) e.getWhoClicked(), total);
								e.getWhoClicked().sendMessage(StringUtility.conv("&aSold " + amount + " items, for $" + total));
							} else if (e.getCurrentItem().getDurability() == 14) {
								e.getWhoClicked().closeInventory();
								e.getWhoClicked().sendMessage(StringUtility.conv("&cCancelled."));
							}
						} else {
							e.getWhoClicked().sendMessage(ChatColor.RED + "This cannot be sold.");
							e.setCancelled(true);
						}
						e.setCancelled(true);
					} else if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
						e.setCancelled(true);
					} else {
						boolean found = false;
						for (Sell s : Sell.values()) {
							if (s.getMaterial().equals(e.getCurrentItem().getType())) {
								found = true;
							}
						}
						if (!found) {
							e.getWhoClicked().sendMessage(StringUtility.conv("&cThis cannot be sold."));
							e.setCancelled(true);
						} else {
							if (!e.isCancelled() && e.getRawSlot() <= 44) {
								ItemStack it = e.getCurrentItem();
								int price;
								int[] calc = calcTotal(e);
								int total = calc[0];
								int amount = calc[1];
								for (Sell s : Sell.values()) {
									if (s.getMaterial().equals(it.getType())) {
										price = (int) s.getPrice() * it.getAmount();
										amount -= it.getAmount();
										total -= price;
									}
								}
								int j = e.getInventory().first(Material.GOLD_NUGGET);
								int k = e.getInventory().first(Material.FIREWORK_CHARGE);
								ItemStack i = e.getInventory().getItem(j);
								ItemStack l = e.getInventory().getItem(k);
								ItemUtility.setLore(i, StringUtility.conv("&6" + total));
								ItemUtility.setLore(l, StringUtility.conv("&a" + amount));
								e.getInventory().remove(Material.GOLD_NUGGET);
								e.getInventory().setItem(j, i);
								e.getInventory().remove(Material.FIREWORK_CHARGE);
								e.getInventory().setItem(k, l);
							}
						}
					}
				} else if (acCheck(InventoryAction.PLACE_ALL, e) || acCheck(InventoryAction.PLACE_ONE, e) || acCheck(InventoryAction.PLACE_SOME, e)) {
					boolean found = false;
					for (Sell s : Sell.values()) {
						if (s.getMaterial().equals(e.getCursor().getType())) {
							found = true;
						}
					}
					if (!found) {
						e.getWhoClicked().sendMessage(StringUtility.conv("&cThis cannot be sold."));
						e.setCancelled(true);
					} else {
						if (!e.isCancelled()) {
							ItemStack it = e.getCursor();
							int price;
							int[] calc = calcTotal(e);
							int total = calc[0];
							int amount = calc[1];
							for (Sell s : Sell.values()) {
								if (s.getMaterial().equals(it.getType())) {
									price = (int) (s.getPrice() * it.getAmount());
									total += price;
									amount += it.getAmount();
								}
							}
							int j = e.getInventory().first(Material.GOLD_NUGGET);
							int k = e.getInventory().first(Material.FIREWORK_CHARGE);
							ItemStack i = e.getInventory().getItem(j);
							ItemStack l = e.getInventory().getItem(k);
							ItemUtility.setLore(i, StringUtility.conv("&6" + total));
							ItemUtility.setLore(l, StringUtility.conv("&a" + amount));
							e.getInventory().remove(Material.GOLD_NUGGET);
							e.getInventory().setItem(j, i);
							e.getInventory().remove(Material.FIREWORK_CHARGE);
							e.getInventory().setItem(k, l);
						}
					}
				} else {
					e.setCancelled(true);
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase(StringUtility.conv("&a&lBuy Menu"))) {
			if (e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
				if (e.getCurrentItem().getDurability() == 14) {
					if (e.getInventory().getContents()[13].getAmount() - e.getCurrentItem().getAmount() >= 1) {
						e.getInventory().getContents()[13].setAmount(e.getInventory().getContents()[13].getAmount() - e.getCurrentItem().getAmount());
					} else {
						e.getInventory().getContents()[13].setAmount(1);
					}
					
				} else if (e.getCurrentItem().getDurability() == 5) {
					if (e.getInventory().getContents()[13].getAmount() + e.getCurrentItem().getAmount() <= e.getInventory().getContents()[13].getMaxStackSize()) {
						e.getInventory().getContents()[13].setAmount(e.getInventory().getContents()[13].getAmount() + e.getCurrentItem().getAmount());
					} else {
						e.getInventory().getContents()[13].setAmount(e.getInventory().getContents()[13].getMaxStackSize());
					}
				}
			} else if (e.getCurrentItem().getType().equals(Material.STAINED_CLAY)) {
				if (e.getCurrentItem().getDurability() == 14) {
					e.getWhoClicked().closeInventory();
				} else if (e.getCurrentItem().getDurability() == 5) {
					double price = Double.valueOf(e.getInventory().getItem(40).getItemMeta().getDisplayName()) * e.getInventory().getItem(13).getAmount();
					if (balCheck((Player) e.getWhoClicked(), price)) {
						balRem((Player) e.getWhoClicked(), price);
						e.getWhoClicked().getInventory().addItem(new ItemStack(e.getInventory().getItem(13)));
						e.getWhoClicked().sendMessage(StringUtility.conv("&2$&a" + price + " was charged from your account."));
						e.getWhoClicked().closeInventory();
					}
				}
			}
			e.setCancelled(true);
		} else if (e.getInventory().getName().startsWith("Permissions")) {
			e.setCancelled(true);
			if (e.getAction() != InventoryAction.NOTHING && e.getCurrentItem() != null) {
				ItemStack item = e.getCurrentItem();
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
					boolean toggled = item.containsEnchantment(new Glow(999));
					String rank = e.getInventory().getName().substring(14);
					Player player = (Player) e.getWhoClicked();
					player.sendMessage(rank);
					if (PlayerManager.getOnlinePlayerFaction(player).getRole(player.getUniqueId()).hasPerm("ManagePerms", true) || PlayerManager.getOnlinePlayerFaction(player).isLeader(player)) {
						XRank rank1 = PlayerManager.getOnlinePlayerFaction(player).getRole(rank);
						if (rank1 != null) {
							player.sendMessage(rank + " - " + toggled + " - ");
							e.setCurrentItem(toggle(toggled, e.getCurrentItem(), rank1));
						}
					}
				}
			}
		} else if (e.getInventory().getName().equalsIgnoreCase("Groups")) {
			e.setCancelled(true);
			if (e.getAction() != InventoryAction.NOTHING && e.getCurrentItem() != null) {
				if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getDurability() == 1) {
					Player p = (Player) e.getWhoClicked();
					String group = e.getCurrentItem().getItemMeta().getDisplayName().substring(10);
					p.closeInventory();
					p.openInventory(PermissionsSubMenu.get(p, PlayerManager.getOnlinePlayerFaction(p).getRole(group)));
				}
			}
		}
	}
}
