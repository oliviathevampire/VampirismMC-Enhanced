package net.vampirismmc.mod.util;

import net.minecraft.resources.ResourceLocation;

public class Constants {
	public static final String VERSION = "0.1.0+build.1-1.20.1";
	public static final String MOD_ID = "vmce";
	public static final String MOD_NAME = "VampirismMC: Enhanced";
	
	public static final String PUBLIC_BUKKIT_VALUES = "PublicBukkitValues";
	
	public static String hideawayId(String path) {
		return new ResourceLocation("pixelhideawaycore", path).toString();
	}
}
