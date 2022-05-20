package me.hammercroft.hgear;

import me.hammercroft.hgear.cachers.GearListCacher;
import me.hammercroft.hgear.cachers.StatusCacher;
import me.hammercroft.plugintools.PluginTools.PTLS;

/**
 * Contains public instances of data-caching classes, and a method to execute them all.
 * 
 * @implNote Caching aids in performance by replacing redundant code. If for some reason that
 *           caching is unavailable, methods relying on caches shall fallback to slower logic.
 * @author hammercroft
 * @see me.hammercroft.hgear.datatypes.DataCacherBase
 */
public class DataCacher {
  public GearListCacher gelica;
  public StatusCacher staca;

  public DataCacher() {
    PTLS.SL.log("[HGear] Loading cacher classes...");
    gelica = new GearListCacher();
    staca = new StatusCacher();
  }

  // Execute all caching methods.
  public void engage() {
    PTLS.SL.log("[HGear] Caching all data...");
    gelica.engage();
    staca.engage();
  }
}
