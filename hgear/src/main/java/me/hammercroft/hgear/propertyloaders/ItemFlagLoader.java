package me.hammercroft.hgear.propertyloaders;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;

public class ItemFlagLoader extends PropertyLoader {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    List<String> flagEntries = section.getStringList("vanilla");
    
    for (String entry : flagEntries) {
    	try {
    		gearMeta.addItemFlags(ItemFlag.valueOf(entry));
    	}
    	catch (IllegalArgumentException owch) {
    		throw propertyKeyValueComplaint(
    				entry,
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
  
  @Override
  public String getPropertyKey() {
  	// TODO Auto-generated method stub
  	return alias;
  }

}
