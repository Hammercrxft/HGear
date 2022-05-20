package me.hammercroft.hgear;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import me.hammercroft.hgear.commands.GearListCommand;
import me.hammercroft.hgear.commands.GiveGearCommand;
import me.hammercroft.hgear.commands.StatusCommand;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools;
import me.hammercroft.plugintools.PluginTools.PTLS;
import me.hammercroft.plugintools.ShortenedLogger;
import me.hammercroft.plugintools.ToggleableVerbose;
import me.hammercroft.plugintools.UniConfig;

/**
 * The plugin class. Provides action during loading, registers events/commands/autocompleters, sets
 * scheduled tasks, contains all global fields, and initializes class objects.
 */
public final class HGear extends JavaPlugin {

  // [INCLUSIONS]
  public static Plugin hgr;
  PluginTools pluginTools;
  ShortenedLogger slog;
  ToggleableVerbose dbg;
  UniConfig gearConfig;
  DataCacher cacher;

  public static PreferenceInitialization prefInit;
  public static GearInitialization gearInit;

  // [CONSTANTS, CACHES, COLLECTIONS AND VARIABLES]

  public static PluginDescriptionFile pluginPDF; // plugin.yml values
  public static ArrayList<Gear> globalGearList = new ArrayList<Gear>();
  public static int globalGearTotal = 0;
  public static String globalGearListCache = null; // cache of /hgearlist output when sender is
                                                   // console or a non-player thing.
  public static String globalStatusCache = null; // cache of the static part in /hgearstatus output

  // [DRIVER CODE]
  @Override
  public void onEnable() {
    hgr = this;
    pluginTools = new PluginTools();
    slog = new ShortenedLogger();
    dbg = new ToggleableVerbose();
    PTLS.SL = slog; // use only in independent classes.
    PTLS.TV = dbg; // use only in independent classes.
    gearConfig = new UniConfig("gears.yml", this, dbg, false);
    cacher = new DataCacher();

    pluginPDF = this.getDescription();
    gearInit = GearInitialization.getInstance(this);
    prefInit = PreferenceInitialization.getInstance(this);
    slog.log("[HGear] Pre-initialization done.");

    slog.log("[HGear] Loading commands...");
    this.getCommand("hgearlist").setExecutor(new GearListCommand());
    this.getCommand("hgearstatus").setExecutor(new StatusCommand());
    this.getCommand("hgeargive").setExecutor(new GiveGearCommand(this));

    prefInit.engage();
    gearInit.engage();
    cacher.engage();

    slog.log("[HGear] Loading finished!");

  }

  @Override
  public void onDisable() {
    dbg = null;
    slog = null;
    gearInit = null;
    pluginTools = null;
    hgr = null;
    System.gc();
  }

  // [SMALL FUNCTIONS]

  // for finding a gear by its internal name
  public static Gear findUsingEnhancedForLoop(String name, List<Gear> gears) {
    for (Gear subject : gears) {
      if (subject.gearInternalName.equals(name)) {
        return subject;
      }
    }
    return null;
  }
}


