package me.hammercroft.plugintools;

import java.util.Set;

public class PluginTools {


  /**
   * Utilities for manipulating from Sets.
   * 
   * @author hammercroft
   *
   */
  public static class PluginToolsSetUtil {
    /**
     * Converts a Set of strings, to a string array.
     * 
     * @param stringSet
     * @return a string array.
     */
    public static String[] stringSet2Array(Set<String> stringSet) {
      int size = stringSet.size();
      String[] output = new String[size];
      int counter = 0;
      for (String x : stringSet) {
        output[counter++] = x;
      }
      return output;
    }
  } // END OF SETUTIL



  /**
   * PluginTools Logging Staticinator. For convenient access to your plugin's PT loggers.
   * 
   * @author hammercroft
   *
   */
  public static class PTLS {
    /**
     * Your plugin's PT logger utility. Should be initialized with an instance of the
     * ShortenedLogger class.
     */
    public static ShortenedLogger SL;
    /**
     * Your plugin's PT debug logger utility. Should be initialized with an instance of the
     * ToggleableVerbose class.
     */
    public static ToggleableVerbose TV;
  }// END OF LOGGING STATICINATOR



  /**
   * A full copy of methods in Baeldung's Hex-To-Byte tutorial article.
   * 
   * @author Baeldung
   *
   */
  public final class HexNByteManip {
    /**
     * Converts a string-represented single-byte hex value into a Java byte.
     * 
     * @param hexString single-byte Hex value represented in String.
     * @return Value in byte.
     */
    static public byte hexToByte(String hexString) {
      int firstDigit = toDigit(hexString.charAt(0));
      int secondDigit = toDigit(hexString.charAt(1));
      return (byte) ((firstDigit << 4) + secondDigit);
    }

    static private int toDigit(char hexChar) {
      int digit = Character.digit(hexChar, 16);
      if (digit == -1) {
        throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
      }
      return digit;
    }

    /**
     * Converts a string-represented hex value into an array of bytes. This is used in applying a
     * string-represented MD5 checksum for use as a resourcepack's hash.
     * 
     * @param hexString Hex value represented as a readable string.
     * @return Value in Java byte array.
     * @throws IllegalArgumentException if the string is not.
     */
    static public byte[] decodeHexString(String hexString) {
      if (hexString.length() % 2 == 1) {
        throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
      }

      byte[] bytes = new byte[hexString.length() / 2];
      for (int i = 0; i < hexString.length(); i += 2) {
        bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
      }
      return bytes;

    }

    /**
     * Converts a single byte into a hex value in string representation.
     * 
     * @param num the byte.
     * @return Hex value represented in a readable string.
     */
    static public String byteToHex(byte num) {
      char[] hexDigits = new char[2];
      hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
      hexDigits[1] = Character.forDigit((num & 0xF), 16);
      return new String(hexDigits);
    }
  } // END OF HEXNBYTEMANIP

}
