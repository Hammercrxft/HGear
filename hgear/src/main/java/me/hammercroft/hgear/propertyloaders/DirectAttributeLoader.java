package me.hammercroft.hgear.propertyloaders;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PTLS;
import me.hammercroft.plugintools.PluginTools.PluginToolsSetUtil;

public class DirectAttributeLoader extends PropertyLoaderBase {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
	  PTLS.TV.log("in directattributeloader engage");
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    String[] attributeEntries =
            PluginToolsSetUtil.stringSet2Array(section.getKeys(false));
    int attributeTotal = attributeEntries.length;
    
    for (int k = 0; k < attributeTotal;k++) {
    	PTLS.TV.log("in per-attribute iteration");
    	AttributeModifier attributeMod = null;
    	Attribute attribute = null;
    	try {
    		PTLS.TV.log("in attribute mod test");
    		attributeMod = new AttributeModifier(
    				UUID.randomUUID(),
    				section.getString(attributeEntries[k] + ".name"),
    				section.getDouble(attributeEntries[k] + ".amount"),
    				amovosgako(section, attributeEntries, k, gearInternalName),
    				evsgaks(section, attributeEntries, k, gearInternalName)
    				);
    	
    		try {
    			PTLS.TV.log("in attribute test");
    			attribute = Attribute.valueOf(attributeEntries[k]);
    		}
    		catch (IllegalArgumentException owch) {
    			PTLS.TV.log("in attribute fail");
    				throw propertyKeyValueComplaint(
    						attributeEntries[k],
    						"Section name was not a valid attribute type.",
    						alias,
    						gearInternalName
    						);
    			}
    	}
    	catch (Exception owch) {PTLS.TV.log("in attributemod fail");throw owch;}
    	PTLS.TV.log("post assignment and tests");
    	
    	if (!gearMeta.addAttributeModifier(attribute, attributeMod)) {PTLS.TV.log("ADD ATTTRIBUTE FAIL");}
    }
    
    gear.gearItemStack.setItemMeta(gearMeta);
    PTLS.TV.log("returning gear");
    return gear;
  }//End of engage function
  
  AttributeModifier.Operation amovosgako(ConfigurationSection section, String[] attributeEntries, int k, String gearInternalName) {
	  PTLS.TV.log("in amo");
	  AttributeModifier.Operation amo = Operation.ADD_NUMBER;
	  
	  if ((section.getString(attributeEntries[k] + ".operation") != null) && // entry is not null
			  (!(section.getString(attributeEntries[k] + ".operation").isBlank())) //entry is not blank
			  ){
		  PTLS.TV.log("amo if");
		  try {
			  PTLS.TV.log("amo test");
			  amo = AttributeModifier.Operation.valueOf(section.getString(attributeEntries[k] + ".operation"));
		  }
		  catch (IllegalArgumentException owch) {
			  PTLS.TV.log("amo test fail");
			  throw propertyKeyValueComplaint(section.getString(attributeEntries[k] + ".operation"),
			          "'operation' entry was a not a valid Attribute Modifier operation.", alias,
			          gearInternalName);
			    }
		  PTLS.TV.log("amo if end");
		  }
	  return amo;
  }//end of amo function
  
  EquipmentSlot evsgaks(ConfigurationSection section, String[] attributeEntries, int k, String gearInternalName) {
	  PTLS.TV.log("in e");
	  EquipmentSlot e = EquipmentSlot.HAND;
	  
	  if ((section.getString(attributeEntries[k] + ".slot") != null) && // entry is not null
			  (!(section.getString(attributeEntries[k] + ".slot").isBlank())) //entry is not blank
			  ){
		  PTLS.TV.log("in e if");
		  try {
			  PTLS.TV.log("in e test");
			  e = EquipmentSlot.valueOf(section.getString(attributeEntries[k] + ".slot"));
		  }
		  catch (IllegalArgumentException owch) {
			  PTLS.TV.log("in e test fail");
			  throw propertyKeyValueComplaint(section.getString(attributeEntries[k] + ".slot"),
			          "'slot' entry was a not a valid Equipment Slot type.", alias,
			          gearInternalName);
			    }
		  PTLS.TV.log("e if end");
		  }
	  return e;
  }//end of e function
  
String alias = "direct_attributes";

}
