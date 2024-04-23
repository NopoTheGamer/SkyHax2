package com.github.nopothegamer.skyhax2;

import com.google.common.collect.Sets;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;

import java.util.Set;

// cough nothing to see here
public class RendererLivingEntityHook {

    // TODO: Convert this to UUIDs instead of names
    // no don't ask to be added lol, for now these are just like my admins
    private static final Set<String> coolPeople = Sets.newHashSet("Dinnerbone", "Biscut", "Pinpointed", "Berded", "Potat_owo", "Pnda__", "Throwpo", "StopUsingSBE", "catgirlseraid", "ThatGravyBoat", "CalMWolfs", "Wolfie586");
    private static boolean isCoolPerson;

    public static boolean isCoolPerson(String string) {
        isCoolPerson = coolPeople.contains(string);
        return isCoolPerson;
    }

    public static boolean isWearing(EntityPlayer entityPlayer, EnumPlayerModelParts p_175148_1_) {
        return isCoolPerson || entityPlayer.isWearing(p_175148_1_);
    }
}