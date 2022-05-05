package me.hammercroft.plugintools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import com.google.common.base.Charsets;

// god bless my soul

/**
 * Instance class containing individual configuration data and manipulation methods. Cloned from
 * Bukkit's configuration implementation.
 * 
 * @see org.bukkit.plugin.java.JavaPlugin
 * @author Bukkit
 */
public class UniConfig {
  /**
   * Create a configuration.
   * 
   * @param nameOfFile File name in UTF-8
   * @param inputPlugin The class that extends JavaPlugin - your plugin class.
   * @param debugger The plugin's ToggleableVerbose instance.
   * @param doReplaceDefaults If false, will not be replaced with default values after file
   *        creation.
   */
  public UniConfig(String nameOfFile, Plugin inputPlugin, ToggleableVerbose debugger,
      boolean doReplaceDefaults) {
    plugin = inputPlugin;
    fileName = nameOfFile;
    configFile = new File(plugin.getDataFolder(), fileName);
    replaceWithDefaults = doReplaceDefaults;
  }

  private Plugin plugin;
  ShortenedLogger q = new ShortenedLogger();

  public String fileName;
  public boolean replaceWithDefaults;
  File configFile = null;
  FileConfiguration config = null;
  boolean initialized = false;


  /**
   * Get this UniConfig's FileConfiguration. Will initialize configuration if empty.
   * 
   * @see org.bukkit.plugin.java.JavaPlugin
   * @author Bukkit
   */
  public FileConfiguration getConfig() {
    if (config == null) {
      reloadConfig();
    }
    return config;
  }

  /**
   * Loads configuration data unto this UniConfig's FileConfiguration. Will try to load from the
   * data folder. If it does not exist, it will load its defaults instead (unless
   * replaceWithDefaults is false).
   * 
   * @see org.bukkit.plugin.java.JavaPlugin
   * @author Bukkit
   */
  public void reloadConfig() {
    config = YamlConfiguration.loadConfiguration(configFile);

    if ((config.getKeys(false).size() == 0) || (replaceWithDefaults)) {
      final InputStream defConfigStream = plugin.getResource(fileName);
      if (defConfigStream == null) {
        return;
      }
      config.setDefaults(YamlConfiguration
          .loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }
  }

  /**
   * Saves configuration data to file.
   * 
   * @see org.bukkit.plugin.java.JavaPlugin
   * @author Bukkit
   */
  public void saveConfig() {
    try {
      getConfig().save(configFile);
    } catch (IOException ex) {
      q.log("Could not save config to " + fileName);
    }
  }

  /**
   * If configuration file does not exist on disk, will save a configuration file with default
   * values.
   * 
   * @see org.bukkit.plugin.java.JavaPlugin
   * @author Bukkit
   */
  public void saveDefaultConfig() {
    if (!configFile.exists()) {
      plugin.saveResource(fileName, false);
    }
  }

}
