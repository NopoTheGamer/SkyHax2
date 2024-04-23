package com.github.nopothegamer.skyhax;

import at.hannibal2.skyhanni.deps.moulconfig.internal.KeybindHelper;
import at.hannibal2.skyhanni.events.LorenzChatEvent;
import at.hannibal2.skyhanni.events.LorenzTickEvent;
import com.github.nopothegamer.skyhax2.ChocolateFactoryInventoryHook;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import tv.twitch.chat.ChatEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@Mod(modid = "neudevmod", useMetadata=false)
public class ExampleMod {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Dirt: " + Blocks.dirt.getUnlocalizedName());
        MinecraftForge.EVENT_BUS.register(new ChocolateFactoryInventoryHook());
        //MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public void onTick(ClientChatReceivedEvent event) {
//        IChatComponent message = event.message;
//        String chatMessage = message.getUnformattedText();
//        System.out.println(chatMessage);
//        if (chatMessage.equals("§e[SkyHanni] Click here to share the location of this chocolate egg with the server!")) {
//            if (message.getChatStyle().getChatClickEvent().getAction() == ClickEvent.Action.RUN_COMMAND) {
//                if (message.getChatStyle().getChatClickEvent().getValue().contains("shaction")) {
//                    ClientCommandHandler.instance.executeCommand(  Minecraft.getMinecraft().thePlayer, message.getChatStyle().getChatClickEvent().getValue());
//                }
//            }
//        }
//    }

    /*@SubscribeEvent
    public static void onTick(LorenzTickEvent event) {

    }*/

    public static boolean isKeyValid(int keyCode) {
        return keyCode != 0;
    }

    public static boolean isKeyDown(int keyCode) {
        if (!isKeyValid(keyCode)) {
            return false;
        } else if (keyCode < 0) {
            return Mouse.isButtonDown(keyCode + 100);
        } else {
            return Keyboard.isKeyDown(keyCode);
        }
    }

    public static boolean isKeyHeld(int key) {
        if (key == 0) return false;
        if (key < 0) {
            return Mouse.isButtonDown(key + 100);
        } else {
            return isKeyDown(key);
        }
    }

    private static LinkedHashMap<Integer, Boolean> pressedKeys = new LinkedHashMap<>();

    public static boolean isKeyClicked(int key) {

        if (isKeyHeld(key)) {
            if (!pressedKeys.get(key)) {
                pressedKeys.put(key, true);
                return true;
            } else {
                return false;
            }
        } else {
            pressedKeys.put(key, false);
            return false;
        }
    }
}
