package com.github.nopothegamer.skyhax2.mixin;

import at.hannibal2.skyhanni.features.event.chocolatefactory.HoppityEggType;
import at.hannibal2.skyhanni.features.event.chocolatefactory.HoppityEggsManager;
import at.hannibal2.skyhanni.utils.LorenzVec;
import com.github.nopothegamer.skyhax2.HoppityEggsManagerHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = HoppityEggsManager.class, remap = false)
public class MixinHoppityEggsManager {

    @Inject(method = "shareWaypointPrompt", cancellable = true, at = @At(value = "INVOKE", target = "Lat/hannibal2/skyhanni/utils/DelayedRun;runNextTick(Lkotlin/jvm/functions/Function0;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void shareWaypointPrompt(CallbackInfo ci, HoppityEggType meal) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
        LorenzVec lorenzVec = new LorenzVec(thePlayer.posX, thePlayer.posY, thePlayer.posZ);
        HoppityEggsManagerHook HoppityEggsManagerHook = new HoppityEggsManagerHook();
        HoppityEggsManagerHook.shareLocation(lorenzVec, meal);
        ci.cancel();

    }
}
