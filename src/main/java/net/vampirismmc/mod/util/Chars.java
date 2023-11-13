package net.vampirismmc.mod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;

public class Chars {
    public static Component disc() {
        return Component.literal("\uE003 ").setStyle(Style.EMPTY.withFont(
                new ResourceLocation(Constants.MOD_ID, "text")
        )).withStyle(ChatFormatting.WHITE);
    }
    public static Component devBadge() {
        return Component.literal("\uE001").setStyle(Style.EMPTY.withFont(
                new ResourceLocation(Constants.MOD_ID, "text")
        )).withStyle(ChatFormatting.WHITE);
    }
    public static Component badge() {
        return Component.literal("\uE001").setStyle(Style.EMPTY.withFont(
                new ResourceLocation(Constants.MOD_ID, "text")
        )).withStyle(ChatFormatting.WHITE);
    }
    public static Component friendBadge() {
        return Component.literal("\uE004").setStyle(Style.EMPTY.withFont(
                new ResourceLocation(Constants.MOD_ID, "text")
        )).withStyle(ChatFormatting.WHITE);
    }
}
