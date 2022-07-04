package me.hammercroft.hgear.propertyloaders;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PluginToolsSetUtil;

public class EnchantmentSectionLoader extends PropertyLoaderBase {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    String[] enchantmentEntries = 
    		PluginToolsSetUtil.stringSet2Array(section.getKeys(false));
    int total = enchantmentEntries.length;
    
    for (int c = 0; c < total; c++) {
    	Enchantment ench = null;
    	int level = 0;
    	
    	try {
    		ench = Enchantment.getByKey(
    				NamespacedKey.minecraft(enchantmentEntries[c].toLowerCase())
    				);
    		level = section.getInt(enchantmentEntries[c]); //the value contained by the enchantment
    													   //keys are the enchantment levels.
    		gearMeta.addEnchant(ench, level, false);
    	} catch (IllegalArgumentException owch) {
    		throw propertyKeyValueComplaint(
    				ench + " " + level,
    				"Enchantment entry was not a valid enchant.",
    				alias,
    				gearInternalName
    				);
    	}
    }
    
    gear.gearItemStack.setItemMeta(gearMeta);
    return gear;
  }
  String alias = "enchantments";

}
