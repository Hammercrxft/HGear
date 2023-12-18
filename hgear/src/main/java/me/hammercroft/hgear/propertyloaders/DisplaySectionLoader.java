package me.hammercroft.hgear.propertyloaders;

import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.ItemMeta;
import me.hammercroft.hgear.datatypes.Gear;

public class DisplaySectionLoader extends PropertyLoader {

  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {
    ItemMeta gearMeta = gear.gearItemStack.getItemMeta();
    String displayName = section.getString("itemname");
    if ((displayName != null) && (displayName.isBlank() == false)) {
      gearMeta.setDisplayName(displayName);
    }
    List<String> displayLore = section.getStringList("description");
    if ((displayLore != null) && (displayLore.isEmpty() == false)) {
      gearMeta.setLore(displayLore);
    }
    gear.gearItemStack.setItemMeta(gearMeta);
    return gear;
  }
  
  @Override
  public String getPropertyKey() {
  	// TODO Auto-generated method stub
  	return "display";
  }

}
