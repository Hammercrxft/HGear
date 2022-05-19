package me.hammercroft.hgear.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.hammercroft.hgear.HGear;

public class StatusCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // TODO Auto-generated method stub
    StringBuilder sb = new StringBuilder();
    sb.append("Current loaded gear: ");
    sb.append(HGear.globalGearTotal);
    sb.append("\n--END OF STATUS--");
    sender.sendMessage(sb.toString());
    return true;
  }

}
