package net.vampirismmc.mod;

import net.vampirismmc.mod.util.Chars;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.*;

public class Prompt {

    public static void info(String message) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.sendSystemMessage(
            MutableComponent.create(ComponentContents.EMPTY)
                .append(Component.literal("[").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                .append(Component.literal("VampirismMC: Enhanced").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)))
                .append(Component.literal("] ").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                .append(Component.literal(message).setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)))
        );
    }

    public static void error(String message) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.sendSystemMessage(
            MutableComponent.create(ComponentContents.EMPTY)
                .append(Component.literal("[").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                .append(Chars.badge())
                .append(Component.literal("] ").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                .append(Component.literal(message).setStyle(Style.EMPTY.withColor(ChatFormatting.RED)))
        );
    }

    public static void trace(String message) {
        if (VampirismMCEnhanced.debug()) {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.sendSystemMessage(
                MutableComponent.create(ComponentContents.EMPTY)
                    .append(Component.literal("[").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                    .append(Chars.badge())
                    .append(Component.literal(" Debug").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)))
                    .append(Component.literal("] ").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                    .append(Component.literal(message).setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)))
            );
        }
    }

    public static void trace(Component message) {
        if (VampirismMCEnhanced.debug()) {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.sendSystemMessage(
                    MutableComponent.create(ComponentContents.EMPTY)
                            .append(Component.literal("[").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                            .append(Chars.badge())
                            .append(Component.literal(" Debug").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)))
                            .append(Component.literal("] ").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                            .append(MutableComponent.create(message.getContents()))
            );
        }
    }

    public static void traceWithClick(Component message, String hover) {
        if (VampirismMCEnhanced.debug()) {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.sendSystemMessage(
                    MutableComponent.create(ComponentContents.EMPTY)
                            .append(Component.literal("[").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                            .append(Chars.badge())
                            .append(Component.literal("Debug").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)))
                            .append(Component.literal("] ").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)))
                            .append(MutableComponent.create(message.getContents()))
                            .setStyle(Style.EMPTY.withHoverEvent(
                                    new HoverEvent(
                                            HoverEvent.Action.SHOW_TEXT,
                                            Component.literal(hover)
                                    )
                            ).withClickEvent(
                                new ClickEvent(
                                    ClickEvent.Action.COPY_TO_CLIPBOARD,
                                    message.getString()
                                )
                            ))
            );
        }
    }

}
