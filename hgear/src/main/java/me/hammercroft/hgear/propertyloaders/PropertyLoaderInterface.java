package me.hammercroft.hgear.propertyloaders;

import org.bukkit.configuration.ConfigurationSection;
import me.hammercroft.hgear.datatypes.Gear;

public interface PropertyLoaderInterface {

	/***
	 * Applies attributes to a gear based on what is parsed from the configuration section of this property.
	 * @param section
	 * @param gear
	 * @param gearInternalName
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
			throws IllegalArgumentException;

	public String getPropertyKey();
}
