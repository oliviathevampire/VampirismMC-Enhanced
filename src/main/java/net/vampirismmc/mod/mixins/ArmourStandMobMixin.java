package net.vampirismmc.mod.mixins;

import net.vampirismmc.mod.VampirismMCEnhanced;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStand.class)
public abstract class ArmourStandMobMixin {
    @Shadow public abstract void setItemSlot(EquipmentSlot slot, ItemStack stack);

    @Unique
    private ItemStack oldHeadStack = null;

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo ci){
        ArmorStand armorStand = (ArmorStand) (Object) this;
        boolean hasCosmetic = armorStand.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items.LEATHER_HORSE_ARMOR;
        if (hasCosmetic) oldHeadStack = armorStand.getItemBySlot(EquipmentSlot.HEAD);

        if (hasCosmetic && VampirismMCEnhanced.connected() && VampirismMCEnhanced.config().hideCosmetics) this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        if (!hasCosmetic && VampirismMCEnhanced.connected() && !VampirismMCEnhanced.config().hideCosmetics && oldHeadStack != null) this.setItemSlot(EquipmentSlot.HEAD, oldHeadStack);
    }

}
