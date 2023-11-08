package com.mine.minemod;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@Mod(modid = MineBot.MODID, version = MineBot.VERSION)
public class MineBot
{
    public static final String MODID = "minebot";
    public static final String VERSION = "0.5";
    public static Configuration config;
    public static KeyBinding keyToggleBot;
    public static boolean botActivado;
    public static MovementInput originalInput;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	if (event.getSide() == Side.SERVER)
    	{
    		FMLLog.bigWarning("Safety-check failed: Client-side mod installed on the server side. Please, remove this mod.");
    		FMLCommonHandler.instance().exitJava(1, false);
    	}
    	MinecraftForge.EVENT_BUS.register(this);
    	
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	syncConfig();
    	
    	keyToggleBot = new KeyBinding("key.minebot.togglebot", Keyboard.KEY_B, "key.categories.misc");
    	ClientRegistry.registerKeyBinding(keyToggleBot);
    	
    	botActivado = false;
    	originalInput = null;
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    
    public static void syncConfig() {
    	try {
    		config.load();
    		
    		// Para leer y escribir una variable en config:
    		// Property prop = config.get(Configuration.CATEGORY_GENERAL, "variable", 2 (Valor inicial de la variable), "Descripción");
            // variable = (int)prop.getInt();
    	}
    	catch (Exception e) {} finally { if (config.hasChanged()) config.save(); }
    }
    
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event) { syncConfig(); }
    
    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event)
    {
    	if (keyToggleBot.isPressed())
    	{
    		System.out.println("Tocao");
    		botActivado = !botActivado;
    		
    		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
    		if (player != null) {
    			if (originalInput == null) originalInput = player.movementInput;
    			if (botActivado) player.movementInput = new BotMovementInput(player);
    			else player.movementInput = originalInput;
    		}
    	}
    }
    
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent event)
    {
    	Minecraft minecraft = Minecraft.getMinecraft();
    	EntityPlayerSP player = minecraft.thePlayer;
    	if (botActivado)
    	{
    		minecraft.fontRendererObj.drawString("Bot enabled", 5, 5, 0x34B384, true);
    	}
    }
}
