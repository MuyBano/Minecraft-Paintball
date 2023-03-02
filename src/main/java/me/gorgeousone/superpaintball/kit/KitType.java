package me.gorgeousone.superpaintball.kit;

import me.gorgeousone.superpaintball.util.version.VersionUtil;
import org.bukkit.Material;

public enum KitType {
	
	RIFLE(1, 3,3, 0, 1),
	SHOTGUN(8, 1,1.5f, .3f, 1),
	MACHINE_GUN( 1, 2,1, .1f, 1),
	SNIPER( -1, 4, -1, 0, 1);
	
	public Material gunItem;
	public final int bulletDmg;
	public final int bulletCount;
	public final float bulletSpeed;
	public final float bulletSpread;
	public final float fireRate;
	
	KitType(int bulletCount, int bulletDmg, float bulletSpeed, float bulletSpread, float fireRate) {
		this.bulletDmg = bulletDmg;
		this.bulletCount = bulletCount;
		this.bulletSpeed = bulletSpeed;
		this.bulletSpread = bulletSpread;
		this.fireRate = fireRate;
	}
	
	public static void setup() {
		RIFLE.gunItem = VersionUtil.IS_LEGACY_SERVER ? Material.valueOf("IRON_BARDING") : Material.valueOf("IRON_HORSE_ARMOR");
		SHOTGUN.gunItem = VersionUtil.IS_LEGACY_SERVER ? Material.valueOf("GOLD_BARDING") : Material.valueOf("GOLDEN_HORSE_ARMOR");
		MACHINE_GUN.gunItem = VersionUtil.IS_LEGACY_SERVER ? Material.valueOf("DIAMOND_BARDING") : Material.valueOf("DIAMOND_HORSE_ARMOR");
		SNIPER.gunItem = Material.DIAMOND_HOE;
	}
}
