package me.hammercroft.hgear.cachers;

import me.hammercroft.hgear.HGear;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PTLS;

public class GearListCacher extends DataCacherBase {

  @Override
  public void engage() {
    PTLS.SL.log("[HGear] Caching Gear entry name list...");
    StringBuilder sb = new StringBuilder();
    sb.append("[HGear] Gear List (");
    sb.append(HGear.globalGearTotal);
    sb.append(" entries)\n");
    for (Gear specimen : HGear.globalGearList) {
      sb.append("Entry #");
      sb.append(HGear.globalGearList.indexOf(specimen));
      sb.append(" - ");
      sb.append(specimen.gearInternalName);
      sb.append("\n"); // line break
    }
    sb.append("--END OF GEAR LIST--");
    HGear.globalGearListCache = sb.toString();
  }

}
