package me.hammercroft.hgear.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.hammercroft.hgear.HGear;
import me.hammercroft.plugintools.PluginTools.HexNByteManip;

public class ResourcepackHandling implements Listener {
	HGear hgr;
	public ResourcepackHandling (HGear dahgr) {hgr=dahgr;}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		Runnable action = new Runnable() {
			@Override
			public void run() {
				if (HGear.globalResourcePackMD5==null|HGear.globalResourcePackMD5.isBlank()) {
					event.getPlayer().setResourcePack(HGear.globalResourcePackURL);
				}
				else {
					event.getPlayer().setResourcePack(		
							HGear.globalResourcePackURL,
							HexNByteManip.decodeHexString(HGear.globalResourcePackMD5)
							);
				}
			}
		};//END of action.
		Bukkit.getScheduler().scheduleSyncDelayedTask(hgr, action, 20);
    }
}
