package me.hammercroft.hgear.propertyloaders;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PluginToolsSetUtil;

public class ItemFlagLoader extends PropertyLoaderBase {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    String[] flagEntries = 
    		PluginToolsSetUtil.stringSet2Array(section.getKeys(false));
    int total = flagEntries.length;
    
    for (int c = 0; c < total; c++) {
    	try {
    		gearMeta.addItemFlags(ItemFlag.valueOf(flagEntries[total]));
    	}
    	catch (IllegalArgumentException owch) {
    		throw propertyKeyValueComplaint(
    				flagEntries[total],
    				"flag entry is not a valid item flag.",
    				alias,
    				gearInternalName
    				);
    	}
    }
    
    gear.gearItemStack.setItemMeta(gearMeta);
    return gear;
  }
  String alias = "flags";

}
