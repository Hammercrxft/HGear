package me.hammercroft.hgear.propertyloaders;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PTLS;

public class MainSectionLoader extends PropertyLoaderBase {
  @Override
  public Gear engage(ConfigurationSection section, Gear gear, String gearInternalName)
      throws IllegalArgumentException {

    // Get the material then assign
    String materialCandidate = section.getString("material");
    if (materialCandidate == null || materialCandidate.isBlank()) {
      throw propertyKeyValueComplaint("'material' entry was empty or unspecified!", alias,
          gearInternalName);
    }
    PTLS.TV.log("[HGear Verbose] " + materialCandidate);
    materialCandidate.toUpperCase();
    Material material;
    try {
      material = Material.valueOf(materialCandidate);
    } catch (IllegalArgumentException owch) {
      throw propertyKeyValueComplaint(materialCandidate,
          "'material' entry contained an invalid Bukkit Material ID, or was unspecified", alias,
          gearInternalName);
    }
    if ((!material.isItem()) || (material.isAir())) {
      throw propertyKeyValueComplaint(materialCandidate,
          "'material' entry was a Bukkit Material ID of an unobtainable item.", alias,
          gearInternalName);
    }
    gear.gearItemStack.setType(material);

    // Get the intData then assign
    Integer intData = section.getInt("intdata");
    if (intData == 0) {
      PTLS.SL.warn(
          "intdata of current gear is 0. This could be because there is no value specified. Note that two or more gear with the same intData will share their Trait properties.");
    }
    gear.gearItemStack.getItemMeta().setCustomModelData(intData);

    // we are done here.
    return gear;
  }

  String alias = "main";
}
