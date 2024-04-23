package com.github.nopothegamer.skyhax2.mixin;

import com.github.nopothegamer.skyhax2.RendererLivingEntityHook;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RendererLivingEntity.class)
public class MixinRendererLivingEntity<T extends EntityLivingBase> {

    @Redirect(method = "rotateCorpse", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean rotateCorpse(String displayName, Object v2, T bat, float p_77043_2_, float p_77043_3_, float partialTicks) {
        return RendererLivingEntityHook.isCoolPerson(displayName);
    }

    @Redirect(method = "rotateCorpse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isWearing(Lnet/minecraft/entity/player/EnumPlayerModelParts;)Z"))
    private boolean rotateCorpse(EntityPlayer bat, EnumPlayerModelParts p_175148_1_) {
        return RendererLivingEntityHook.isWearing(bat, EnumPlayerModelParts.CAPE);
    }
}