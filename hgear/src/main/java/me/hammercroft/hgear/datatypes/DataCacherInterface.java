package me.hammercroft.hgear.datatypes;

/**
 * Inteface for all data-caching classes.
 * 
 * @implNote Caching aids in performance by replacing redundant code. If for some reason that
 *           caching is unavailable, methods relying on caches shall fallback to slower logic.
 * @author hammercroft
 *
 */
public interface DataCacherInterface {
  public void engage();

}
