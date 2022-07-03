package me.hammercroft.hgear;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.hgear.propertyloaders.DisplaySectionLoader;
import me.hammercroft.hgear.propertyloaders.MainSectionLoader;
import me.hammercroft.hgear.propertyloaders.PropertyLoaderBase;
import me.hammercroft.hgear.propertyloaders.DirectAttributeLoader;
import me.hammercroft.plugintools.PluginTools.PluginToolsSetUtil;
import me.hammercroft.plugintools.UniConfig;

/**
 * Singleton containing a function that loads all gear from disk by applying gear property data
 * against property loaders. To be used whilst plugin is enabling.
 * 
 * @author hammercroft
 *
 */
public class GearInitialization {
  private GearInitialization(HGear the_hgr) {
    hgr = the_hgr;
    gearConfigField = hgr.gearConfig;
  }

  /**
   * Returns the only instance of this singleton, initializing the singleton if nonexistent.
   * 
   * @param the_hgr HGear's class
   * @return GearInitialization singleton
   */
  public static GearInitialization getInstance(HGear the_hgr) {
    if (singleInstance == null) {
      singleInstance = new GearInitialization(the_hgr);
    }

    return singleInstance;
  }

  /**
   * Discards the current instance of this singleton.
   */
  public void killInstance() {
    GearInitialization.singleInstance = null;
  }

  private static GearInitialization singleInstance = null;
  HGear hgr;
  UniConfig gearConfigField;

  /**
   * Provide the class' Gear-initializing actions.
   */
  public void engage() {
    hgr.slog.log("[HGear] Loading gears...");
    gearConfigField.saveDefaultConfig();

    Configuration gearConfig = gearConfigField.getConfig();
    String[] gearEntries = PluginToolsSetUtil.stringSet2Array(gearConfig.getKeys(false));
    HGear.globalGearTotal = gearEntries.length;
    hgr.slog.log("[HGear] gear.yml has " + HGear.globalGearTotal + " entries.");

    // iterate for each gear
    for (int ce = 0; ce < HGear.globalGearTotal; ce++) {
      hgr.slog.log("[HGear] Loading gear" + gearEntries[ce]);
      try {
        Gear gear = new Gear(gearEntries[ce]);
        ConfigurationSection propertySection = gearConfig.getConfigurationSection(gearEntries[ce]);
        String[] propertyEntries =
            PluginToolsSetUtil.stringSet2Array(propertySection.getKeys(false));
        int propertyTotal = propertyEntries.length;
        boolean hasMain = false;

        // iterate for each gear's properties
        for (int k = 0; k < propertyTotal; k++) {
          String currentProperty = propertyEntries[k];
          PropertyLoaderBase loader;
          switch (currentProperty) {
            // TODO add cases as we add property loaders
          	// The current property checks are deliberately redundant as to avoid loading default values :/
            case "main":
              if (propertySection.contains(currentProperty, true)) {
            	  hgr.dbg.log("main property"); //TODO DEL ME
                loader = new MainSectionLoader();
                gear = loader.engage(propertySection.getConfigurationSection(currentProperty), gear,
                    gearEntries[ce]);
                hasMain = true;
              }
              break;
            case "display":
              if (propertySection.contains(currentProperty, true)) {
            	  hgr.dbg.log("display property"); //TODO DEL ME
                loader = new DisplaySectionLoader();
                gear = loader.engage(propertySection.getConfigurationSection(currentProperty), gear,
                    gearEntries[ce]);
              }
              break;
            case "direct_attribute":
                if (propertySection.contains(currentProperty, true)) {
                	hgr.dbg.log("direct_attribute property"); //TODO DEL ME
                  loader = new DirectAttributeLoader();
                  gear = loader.engage(propertySection.getConfigurationSection(currentProperty), gear,
                      gearEntries[ce]);
                }
                break;
          }// end of currentProperty switch iteration
        } // end of properties for-iteration
        if (hasMain) {
          gear.addToGlobalGearList();
        } else {
          hgr.slog.severe("[HGear] Refusing this gear entry as it has no 'main' property section.");
        }
      } // end of per-gear trial
      catch (Exception owch) {
        hgr.slog.severe("[HGear] Skipping iteration of this gear due to exception:");
        hgr.slog.echoStackTrace(owch);
      } // end of per-gear exception handling
    } // end of per-gear for-iteration
  }// end of engage() method
} // end of main class
