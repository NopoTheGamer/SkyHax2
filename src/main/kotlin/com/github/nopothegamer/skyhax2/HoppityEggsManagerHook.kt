package com.github.nopothegamer.skyhax2

import at.hannibal2.skyhanni.features.event.chocolatefactory.HoppityEggType
import at.hannibal2.skyhanni.features.event.chocolatefactory.HoppityEggsShared.shareNearbyEggLocation
import at.hannibal2.skyhanni.utils.LorenzVec

class HoppityEggsManagerHook {
    fun shareLocation(a: LorenzVec, b: HoppityEggType) {
        shareNearbyEggLocation(a, b)
    }
}