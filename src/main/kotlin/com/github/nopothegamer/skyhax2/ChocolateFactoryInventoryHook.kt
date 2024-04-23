package com.github.nopothegamer.skyhax2

import at.hannibal2.skyhanni.events.RenderInventoryItemTipEvent
import at.hannibal2.skyhanni.features.event.chocolatefactory.ChocolateFactoryAPI
import at.hannibal2.skyhanni.utils.InventoryUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse

class ChocolateFactoryInventoryHook {

    var lastClick : Long = -1

    @SubscribeEvent
    fun onBackgroundDrawn(event: RenderInventoryItemTipEvent) {
        if (!ChocolateFactoryAPI.inChocolateFactory) return
        ChocolateFactoryAPI.bestUpgrade ?: return
        if (ChocolateFactoryAPI.bestUpgrade != event.slot.slotIndex) return
        if (isKeyClicked(Keyboard.KEY_F)) {
            if (lastClick != -1L && System.currentTimeMillis() - lastClick < 200) return
            lastClick = System.currentTimeMillis()
            println(lastClick)

            val id = Minecraft.getMinecraft().currentScreen as? GuiChest ?: return
            val idd = id.inventorySlots.windowId

            Minecraft.getMinecraft().playerController.windowClick(
                idd,
                event.slot.slotIndex, 2, 3, Minecraft.getMinecraft().thePlayer
            )
        }

    }

    fun getSlot(): Slot? {
        for (slot in InventoryUtils.getItemsInOpenChest()) {
            if (slot.slotIndex in ChocolateFactoryAPI.upgradeableSlots) {
                if (slot.slotIndex == ChocolateFactoryAPI.bestUpgrade) {
                    return slot
                }
            }
        }
        return null
    }

    fun isKeyValid(keyCode: Int): Boolean {
        return keyCode != 0
    }

    fun isKeyDown(keyCode: Int): Boolean {
        return if (!isKeyValid(keyCode)) {
            false
        } else if (keyCode < 0) {
            Mouse.isButtonDown(keyCode + 100)
        } else {
            Keyboard.isKeyDown(keyCode)
        }
    }

    fun isKeyHeld(key: Int): Boolean {
        if (key == 0) return false
        return if (key < 0) {
            Mouse.isButtonDown(key + 100)
        } else {
            isKeyDown(key)
        }
    }


    var pressedKeys: LinkedHashMap<Int, Boolean> = LinkedHashMap()

    fun isKeyClicked(key: Int): Boolean {
        if (isKeyHeld(key)) {
            if (!pressedKeys[key]!!) {
                pressedKeys[key] = true
                return true
            } else {
                return false
            }
        } else {
            pressedKeys[key] = false
            return false
        }
    }

}