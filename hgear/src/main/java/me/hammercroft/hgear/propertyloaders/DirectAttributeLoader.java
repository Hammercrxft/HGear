package me.hammercroft.hgear.propertyloaders;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PluginToolsSetUtil;

public class DirectAttributeLoader extends PropertyLoaderBase {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    String[] attributeEntries =
            PluginToolsSetUtil.stringSet2Array(section.getKeys(false));
    int attributeTotal = attributeEntries.length;
    
    for (int k = 0; k < attributeTotal;k++) {
    	AttributeModifier attributeMod = null;
    	Attribute attribute = null;
    	try {
    		attributeMod = new AttributeModifier(
    				UUID.randomUUID(),
    				section.getString(attributeEntries[k] + ".name"),
    				section.getDouble(attributeEntries[k] + ".amount"),
    				amovosgako(section, attributeEntries, k, gearInternalName),
    				evsgaks(section, attributeEntries, k, gearInternalName)
    				);
    	
    		try {
    			attribute = Attribute.valueOf(attributeEntries[k]);
    		}
    		catch (IllegalArgumentException owch) {
    				throw propertyKeyValueComplaint(
    						attributeEntries[k],
    						"Section name was not a valid attribute type.",
    						alias
    						);
    			}
    	}
    	catch (Exception owch) {throw owch;}
    	
    	gearMeta.addAttributeModifier(attribute, attributeMod);
    	gear.gearItemStack.setItemMeta(gearMeta);
    	
    	//check if modifier is valid
    }
    
        
    gear.gearItemStack.setItemMeta(gearMeta);
    return gear;
  }//End of engage function
  
  AttributeModifier.Operation amovosgako(ConfigurationSection section, String[] attributeEntries, int k, String gearInternalName) {
	  AttributeModifier.Operation amo = Operation.ADD_NUMBER;
	  
	  if (!(section.getString(attributeEntries[k] + ".operation").isBlank())){
		  try {
			  amo = AttributeModifier.Operation.valueOf(section.getString(attributeEntries[k] + ".operation"));
		  }
		  catch (IllegalArgumentException owch) {
			  throw propertyKeyValueComplaint(section.getString(attributeEntries[k] + ".operation"),
			          "'operation' entry was a not a valid Attribute Modifier operation.", alias,
			          gearInternalName);
			    }
		  }
	  return amo;
  }//end of amo function
  
  EquipmentSlot evsgaks(ConfigurationSection section, String[] attributeEntries, int k, String gearInternalName) {
	  EquipmentSlot e = EquipmentSlot.HAND;
	  
	  if (!(section.getString(attributeEntries[k] + ".slot").isBlank())){
		  try {
			  e = EquipmentSlot.valueOf(section.getString(attributeEntries[k] + ".slot"));
		  }
		  catch (IllegalArgumentException owch) {
			  throw propertyKeyValueComplaint(section.getString(attributeEntries[k] + ".slot"),
			          "'slot' entry was a not a valid Equipment Slot type.", alias,
			          gearInternalName);
			    }
		  }
	  return e;
  }//end of e function
  
String alias = "direct_attribute";

}
