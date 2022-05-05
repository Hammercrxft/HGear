package me.hammercroft.hgear.propertyloaders;

public abstract class PropertyLoaderBase implements PropertyLoaderInterface {

  /**
   * Throws an exception with a message and information about the current iterating entry.
   * 
   * @param message The error message the exception will bring.
   * @return an IllegalArgumentException with debugging information.
   * @throws IllegalArgumentException as return output.
   */
  public IllegalArgumentException propertyKeyValueComplaint(String message, String alias,
      String gearInternalName) {
    StringBuilder sb = new StringBuilder();
    sb.append(message);
    sb.append(" \nException occured whilst iterating property '");
    sb.append(alias);
    sb.append("' in gear entry '");
    sb.append(gearInternalName);
    sb.append("'.\nPlease check and fix your 'gear.yml' file.");
    return new IllegalArgumentException(sb.toString());
  }

  /**
   * Throws an exception with a message and information about the current iterating entry.
   * 
   * @param item Thing causing this error.
   * @param message The error message the exception will bring.
   * @return IllegalArgumentException with debugging information.
   * @throws IllegalArgumentException as return output.
   */
  public IllegalArgumentException propertyKeyValueComplaint(String item, String message,
      String alias, String gearInternalName) {
    StringBuilder sb = new StringBuilder();
    sb.append(message);
    sb.append(" \nException occured upon applying value '");
    sb.append(item);
    sb.append("' whilst iterating property '");
    sb.append(alias);
    sb.append("' in gear entry '");
    sb.append(gearInternalName);
    sb.append("'.\nPlease check and fix your 'gear.yml' file.");
    return new IllegalArgumentException(sb.toString());
  }
}
