package pw.xwy.factions.objects;
// made by reeve
// on 7:12 PM

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Glow extends Enchantment {
	
	public Glow(int id) {
		
		super(id);
	}
	
	@Override
	public boolean canEnchantItem(ItemStack itemStack) {
		
		return false;
	}
	
	@Override
	public boolean conflictsWith(Enchantment enchantment) {
		
		return false;
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		
		return null;
	}
	
	@Override
	public int getMaxLevel() {
		
		return 0;
	}
	
	@Override
	public String getName() {
		
		return null;
	}
	
	@Override
	public int getStartLevel() {
		
		return 0;
	}
	
}
