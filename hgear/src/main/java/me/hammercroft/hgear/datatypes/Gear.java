package me.hammercroft.hgear.datatypes;

import java.util.HashSet;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import me.hammercroft.hgear.HGear;

/**
 * Class representative of a kind of gear. It contains properties and data pertaining to the gear,
 * including its ItemStack.
 * 
 * @author hammercroft
 *
 */
public class Gear {
  /**
   * Creates an instance of this kind of gear.
   * 
   * @param material The Bukkit Material ID of the item that will represent this gear.
   * @param internalName Name used as identification. Shall only contain letters and underscores.
   * @param intId Integer used as the gear item's customModelData and is also used for efficient
   *        identification.
   * @throws IllegalArgumentException When internalName contains numbers and/or special symbols;
   */
  // public Gear(Material material, String internalName, Integer intId)
  // throws IllegalArgumentException {
  // gearItemStack = new ItemStack(material);
  // gearItemMeta = gearItemStack.getItemMeta();
  //
  // String internalNameCache = internalName;
  // internalNameCache.toLowerCase();
  // {
  // if ((internalNameCache.isBlank()) || (internalNameCache.isEmpty())) {
  // throw new IllegalArgumentException("Gear internal name cannot be blank!");
  // }
  // Pattern pat = Pattern.compile("[^a-z_]", Pattern.CASE_INSENSITIVE);
  // Matcher mat = pat.matcher(internalNameCache);
  // if (mat.find()) {
  // throw new IllegalArgumentException(
  // "Gear internal names shall only contain alphabetic characters and underscores!");
  // }
  // }
  // internalName = internalNameCache;
  //
  // gearIntegerId = intId;
  // }// end of constructor definition

  public Gear(String internalName) {
    gearItemStack = new ItemStack(Material.STICK);
    gearInternalName = internalName;
  }// end of constructor definition

  // [DATA]
  public ItemStack gearItemStack = null;

  /** This gear's internal name, for use as identification within the plugin. */
  public String gearInternalName = null;
  /** This gear's numeric ID, and customModelData, for use as identification from the game world. */
  public Integer gearIntegerId = 0;

  /** Contains the namespaced keys of recipes that outputs this gear. For informational use. */
  public HashSet<NamespacedKey> gearRecipeKeys = new HashSet<NamespacedKey>();


  // [METHODS]
  public void addToGlobalGearList() {
    HGear.globalGearList.add(this);
  }

}
