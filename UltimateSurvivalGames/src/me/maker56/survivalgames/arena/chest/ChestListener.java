package me.maker56.survivalgames.arena.chest;

import me.maker56.survivalgames.SurvivalGames;
import me.maker56.survivalgames.arena.Arena;
import me.maker56.survivalgames.game.Game;
import me.maker56.survivalgames.game.GameState;
import me.maker56.survivalgames.user.User;
import me.maker56.survivalgames.user.UserManager;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestListener implements Listener {
	
	private UserManager um = SurvivalGames.userManger;
	private ChestManager cm = SurvivalGames.chestManager;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = event.getPlayer();
			
			if(um.isPlaying(p.getName())) {
				
				User user = um.getUser(p.getName());
				Game game = user.getGame();
				
				if(game.getState() != GameState.INGAME && game.getState() != GameState.DEATHMATCH) {
					event.setCancelled(true);
					return;
				}
				
				Arena arena = game.getCurrentArena();
				Block b = event.getClickedBlock();
				
				if(b.getType() == arena.getChestType()) {
					if((arena.getChestData() >= 0 && b.getData() == arena.getChestData()) || arena.getChestData() < 0) {
						Location loc = b.getLocation();
						event.setCancelled(true);
						if(game.isChestRegistered(loc)) {
							p.openInventory(game.getChest(loc).getInventory());
						} else {
							Chest chest = cm.getRandomChest(p, loc);
							game.registerChest(chest);
							p.openInventory(chest.getInventory());
						}
					}
				}
			}
		}
	}
}
