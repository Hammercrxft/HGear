package me.hammercroft.hgear.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.hammercroft.hgear.HGear;
import me.hammercroft.hgear.datatypes.Gear;
import me.hammercroft.plugintools.PluginTools.PTLS;

public class GearListCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // TODO Auto-generated method stub
    PTLS.SL.logArray(args);

    // for players
    if ((sender instanceof Player) || args.length > 1) {
      sender.sendMessage("is player/page sent");
      return true;
    }

    // when non-players
    try {
      // Use cached list, else output on-demand.
      if (HGear.globalGearListCache != null) {
        sender.sendMessage(HGear.globalGearListCache);
      } else {
        for (Gear specimen : HGear.globalGearList) {
          StringBuilder sb = new StringBuilder();
          sb.append("Entry #");
          sb.append(HGear.globalGearList.indexOf(specimen));
          sb.append(" - ");
          sb.append(specimen.gearInternalName);
          sender.sendMessage(sb.toString());
        }
      }
    } catch (

    Exception owch) {
      return false;
    }

    return true;
  }

}
