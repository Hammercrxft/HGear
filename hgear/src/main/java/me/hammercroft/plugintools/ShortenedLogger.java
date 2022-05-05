package me.hammercroft.plugintools;

import java.util.List;
import org.bukkit.Bukkit;

/**
 * Class with small methods for console logging. Its purpose is to make logging less sizeable, so do
 * make instance field names shortened.
 * 
 * @author Hammercroft
 **/
public class ShortenedLogger {
  /**
   * Relays a message through the the standard output stream.
   * 
   * @see java.io.PrintStream.print(Object)
   */
  public void echo(Object input) {
    System.out.print(input);
  }

  /**
   * Sends a normal log message to the console.
   */
  public void log(String input) {
    Bukkit.getLogger().info(input);
  }

  /**
   * Sends a (yellow) warning log message to the console.
   */
  public void warn(String input) {
    Bukkit.getLogger().warning(input);
  }

  /**
   * Sends a (red) severe log message to the console.
   */
  public void severe(String input) {
    Bukkit.getLogger().severe(input);
  }

  /**
   * Sends a normal log message to the logger. May not appear on console.
   */
  public void fine(String input) {
    Bukkit.getLogger().fine(input);
  }

  /**
   * Prints out exception details through the standard output stream.
   * 
   * @see java.lang.Throwable.printStackTrace(PrintStream)
   */
  public void echoStackTrace(Exception owch) {
    owch.printStackTrace(System.out);
  }

  /**
   * Sends a string array to the console, with one value in each line.
   */
  public void logArray(String[] stringarray) {
    int limit = stringarray.length;
    for (int c = 0; c < limit; c++) {
      log(stringarray[c]);
    }
  }

  /**
   * Sends a message then a string array to the console, with one value in each line.
   */
  public void logArray(String prefix, String[] stringarray) {
    int limit = stringarray.length;
    for (int c = 0; c < limit; c++) {
      log(prefix + stringarray[c]);
    }
  }

  /**
   * Sends a list of strings to the console, starting from the lowest index, with one value in each
   * line.
   */
  public void logList(List<String> stringlist) {
    for (String x : stringlist) {
      log(x);
    }
  }

  /**
   * Sends a message then a list of strings to the console, starting from the lowest index, with one
   * value in each line.
   */
  public void logList(String prefix, List<String> stringlist) {
    for (String x : stringlist) {
      log(prefix + x);
    }
  }

}
