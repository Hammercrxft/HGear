package me.hammercroft.plugintools;

import java.util.List;
import org.bukkit.Bukkit;

/**
 * A small logging utility class with logger methods which will only function if the {@code verbose}
 * boolean field is true.
 * 
 * @see me.hammercroft.plugintools.ToggleableVerbose.verbose
 * @author Hammercroft
 **/
public class ToggleableVerbose {
  /**
   * Set this to true to show messages from this tool.
   **/
  public boolean verbose = false;

  /**
   * Relays a message through the the standard output stream.
   * 
   * @see java.io.PrintStream.print(Object)
   */
  public void echo(Object input) {
    if (verbose)
      System.out.print(input);
  }

  /**
   * Sends a normal log message to the console.
   */
  public void log(String input) {
    if (verbose)
      Bukkit.getLogger().info(input);
  }

  /**
   * Sends a 'fine' message, will be invisible on the console.
   */
  public void fine(String input) {
    if (verbose)
      Bukkit.getLogger().fine(input);
  }

  /**
   * Sends a (yellow) warning log message to the console.
   */
  public void warn(String input) {
    if (verbose)
      Bukkit.getLogger().warning(input);
  }

  /**
   * Prints out exception details through the standard output stream.
   * 
   * @see java.lang.Throwable.printStackTrace(PrintStream)
   */
  public void echoStackTrace(Exception owch) {
    if (verbose) {
      owch.printStackTrace(System.out);
    }
  }

  /**
   * Sends a string array to the console, with one value in each line.
   */
  public void logArray(String[] stringarray) {
    if (verbose) {
      int limit = stringarray.length;
      for (int c = 0; c < limit; c++) {
        log(stringarray[c]);
      }
    }
  }

  /**
   * Sends a string array to the console, with one value in each line.
   */
  public void logArray(String prefix, String[] stringarray) {
    if (verbose) {
      int limit = stringarray.length;
      for (int c = 0; c < limit; c++) {
        log(prefix + stringarray[c]);
      }
    }
  }

  /**
   * Sends a list of strings to the console, starting from the lowest index, with one value in each
   * line.
   */
  public void logList(List<String> stringlist) {
    if (verbose) {
      for (String x : stringlist) {
        log(x);
      }
    }
  }

  /**
   * Sends a list of strings to the console, starting from the lowest index, with one value in each
   * line.
   */
  public void logList(String prefix, List<String> stringlist) {
    if (verbose) {
      for (String x : stringlist) {
        log(prefix + x);
      }
    }
  }

}
