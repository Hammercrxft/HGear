package me.hammercroft.hgear.propertyloaders;

import org.bukkit.configuration.ConfigurationSection;
import me.hammercroft.hgear.datatypes.Gear;

public interface PropertyLoaderInterface {
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException;
}
