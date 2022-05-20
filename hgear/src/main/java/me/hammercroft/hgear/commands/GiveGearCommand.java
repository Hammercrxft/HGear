package me.hammercroft.hgear.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.hammercroft.hgear.HGear;

public class GiveGearCommand implements CommandExecutor {

  public GiveGearCommand(HGear dhgr) {
    hgr = dhgr;
  }

  HGear hgr;

  enum status {
    INITIAL, NOARGS, TARGET, GEAR, AMOUNT, TOOMANY, MISSINGGEAR, INVALIDAMOUNT, INVALIDGEAR, INVALIDTARGET, PASS
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player target = null;
    ItemStack gear = null;
    int amount = 1;
    status state = status.INITIAL;

    if (args.length == 0) {
      state = status.NOARGS;
    } else if (args.length == 1) {
      state = status.MISSINGGEAR;
    } else if (args.length == 2) {
      state = status.GEAR;
    } else if (args.length == 3) {
      state = status.AMOUNT;
    } else if (args.length > 3) {
      state = status.TOOMANY;
    }

    boolean done = false;
    while (!done) {
      switch (state) {
        case AMOUNT:
          try {
            amount = Integer.valueOf(args[2]);
            state = status.GEAR;
          } catch (Exception owch) {
            state = status.INVALIDAMOUNT;
          }
          break;
        case GEAR:
          try {
            try {
              gear = HGear.globalGearList.get(Integer.valueOf(args[1])).gearItemStack;
            } catch (NumberFormatException owch) {
              gear = HGear.findUsingEnhancedForLoop(args[1], HGear.globalGearList).gearItemStack;
            }
            state = status.TARGET;
          } catch (Exception owch) {
            state = status.INVALIDGEAR;
          }
          break;
        case TARGET:
          target = hgr.getServer().getPlayerExact(args[0]);
          state = status.PASS;
          if (target == null) {
            state = status.INVALIDTARGET;
          } else {
            target.getInventory().addItem(gear);
            sender.sendMessage("[HGear] Gave " + amount + " " + gear.getItemMeta().getDisplayName()
                + " to " + args[0]);
          }
          break;

        case NOARGS:
          sender.sendMessage("[HGear] Missing arguments!");
          sender.sendMessage("Usage: hgeargive <target> <gear> [amount]");
          state = status.PASS;
          break;
        case MISSINGGEAR:
          sender.sendMessage("[HGear] Please specify a weapon to give.");
          sender.sendMessage("[HGear] Do /hgearlist to get the gear list.");
          sender.sendMessage("Usage: hgeargive <target> <gear> [amount]");
          state = status.PASS;
          break;
        case TOOMANY:
          sender.sendMessage("[HGear] Too many arguments!");
          sender.sendMessage("Usage: hgeargive <target> <gear> [amount]");
          state = status.PASS;
          break;
        case INVALIDAMOUNT:
          sender.sendMessage("[HGear] Invalid amount. Please use a whole number.");
          state = status.PASS;
          break;
        case INVALIDGEAR:
          sender.sendMessage("[HGear] Couldn't identify weapon/gear.");
          sender.sendMessage("[HGear] Do /weaponryx:list to get the gear list.");
          sender.sendMessage("Usage: hgeargive <target> <gear> [amount]");
          state = status.PASS;
          break;
        case INVALIDTARGET:
          sender.sendMessage("[HGear] Couldn't identify target.");
          sender
              .sendMessage("Please make sure that the target is online, and check capitalization.");
          state = status.PASS;
          break;
        case PASS:
          done = true;
          break;
        default:
          state = status.PASS;
          break;
      }
    }
    return true;
  }

}
