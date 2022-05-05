package me.hammercroft.hgear;

import org.bukkit.configuration.ConfigurationSection;

public class PreferenceInitialization {
  /**
   * Singleton containing a function that reads from the plugin configuration.
   * 
   * @author hammercroft
   *
   */
  private PreferenceInitialization(HGear the_hgr) {
    hgr = the_hgr;
  }

  /**
   * Returns the only instance of this singleton, initializing the singleton if nonexistent.
   * 
   * @param the_hgr HGear's class
   * @return PreferenceInitialization singleton
   */
  public static PreferenceInitialization getInstance(HGear the_hgr) {
    if (singleInstance == null) {
      singleInstance = new PreferenceInitialization(the_hgr);
    }

    return singleInstance;
  }

  /**
   * Discards the current instance of this singleton.
   */
  public void killInstance() {
    PreferenceInitialization.singleInstance = null;
  }

  private static PreferenceInitialization singleInstance = null;
  HGear hgr;

  /**
   * Provide the class' preference-initializing actions.
   */
  public void engage() {
    hgr.slog.log("[HGear] Fetching preferences from config.yml");
    hgr.saveDefaultConfig();
    ConfigurationSection prefConfigCache = hgr.getConfig();

    // verbose
    hgr.dbg.verbose = prefConfigCache.getBoolean("verbose");
    if (hgr.dbg.verbose) {
      hgr.slog.warn("[HGear] Verbose logging enabled!");
    }
  }
}
