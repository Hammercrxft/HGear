package me.hammercroft.hgear.cachers;

import me.hammercroft.hgear.HGear;
import me.hammercroft.hgear.datatypes.DataCacherBase;
import me.hammercroft.plugintools.PluginTools.PTLS;

public class StatusCacher extends DataCacherBase {

  @Override
  public void engage() {
    // TODO Auto-generated method stub
    PTLS.SL.log("[HGear] Caching plugin status report...");
    StringBuilder sb = new StringBuilder();
    sb.append("[HGear] --PLUGIN STATUS--\n");
    sb.append("Running version ");
    sb.append(HGear.pluginPDF.getVersion());
    sb.append("\nCurrent loaded gear: ");
    sb.append(HGear.globalGearTotal);
    sb.append("\n--END OF STATUS--");
    HGear.globalStatusCache = sb.toString();
  }

}
