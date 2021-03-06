package me.maker56.survivalgames.user;

import java.util.Collection;
import java.util.Iterator;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class UserState {
	
	protected Player player;
	private double health;
	private float walk, fly;
	private int food;
	private float exp;
	private int level;
	private int fireticks;
	private ItemStack[][] inventory;
	private Location loc;
	private GameMode gamemode;
	private boolean allowFlying, flying;
	private Collection<PotionEffect> ape;
	private float fall;

	public UserState(Player p) {
		this.player = p;
		this.health = ((Damageable) p).getHealth();
		this.food = p.getFoodLevel();
		this.exp = p.getExp();
		this.level = p.getLevel();
		this.fireticks = p.getFireTicks();
		this.loc = p.getLocation();
		this.gamemode = p.getGameMode();
		this.allowFlying = p.getAllowFlight();
		this.flying = p.isFlying();
		this.ape = p.getActivePotionEffects();
		this.fall = p.getFallDistance();
		this.walk = p.getWalkSpeed();
		this.fly = p.getFlySpeed();

		ItemStack[][] store = new ItemStack[2][1];
		store[0] = p.getInventory().getContents();
		store[1] = p.getInventory().getArmorContents();
		this.inventory = store;
	}

	public float getFallDistance() {
		return fall;
	}
	
	public float getWalkSpeed() {
		return walk;
	}
	
	public float getFlySpeed() {
		return fly;
	}

	public ItemStack[] getContents() {
		return inventory[0];
	}

	public ItemStack[] getArmorContents() {
		return inventory[1];
	}

	public Collection<PotionEffect> getActivePotionEffects() {
		return ape;
	}

	public GameMode getGameMode() {
		return gamemode;
	}

	public boolean getAllowFlight() {
		return allowFlying;
	}

	public boolean isFlying() {
		return flying;
	}

	public Location getLocation() {
		return this.loc;
	}

	public double getHealth() {
		return this.health;
	}

	public int getFoodLevel() {
		return this.food;
	}

	public float getExp() {
		return this.exp;
	}

	public int getLevel() {
		return this.level;
	}

	public int getFireTicks() {
		return this.fireticks;
	}

	public ItemStack[][] getInventory() {
		return this.inventory;
	}

	public void clear() {
		for (Iterator<PotionEffect> i = player.getActivePotionEffects().iterator(); i.hasNext();) {
			player.removePotionEffect(i.next().getType());
		}
		player.setWalkSpeed(0.2F);
		player.setFlySpeed(0.2F);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setExp(0);
		player.setFireTicks(0);
		player.setGameMode(GameMode.SURVIVAL);
		player.setFlying(false);
		player.setAllowFlight(false);

		clearInventory();
	}

	@SuppressWarnings("deprecation")
	public void clearInventory() {
		ItemStack[] inv = player.getInventory().getContents();
		for (int i = 0; i < inv.length; i++) {
			inv[i] = null;
		}
		player.getInventory().setContents(inv);
		inv = player.getInventory().getArmorContents();
		for (int i = 0; i < inv.length; i++) {
			inv[i] = null;
		}
		player.getInventory().setArmorContents(inv);
		player.updateInventory();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getName() {
		return player.getName();
	}
	
	public void sendMessage(String message) {
		player.sendMessage(message);
	}

}