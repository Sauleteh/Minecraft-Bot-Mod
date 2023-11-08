package com.mine.minemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;

public class BotMovementInput extends MovementInput
{
	private EntityPlayerSP originalPlayer;
	protected MovementInput originalMovementInput;
	private BotController botController;
	
	public BotMovementInput(EntityPlayerSP thePlayer)
	{
		this.originalPlayer = thePlayer;
		this.originalMovementInput = thePlayer.movementInput;
		this.botController = new BotController(this.originalPlayer);
		System.out.println("Constructor BotMovementInput inicializado");
	}
	
	int tick = 0;
	@Override
	public void updatePlayerMoveState()
	{
		originalMovementInput.updatePlayerMoveState();
		botController.execute();
	}
}
