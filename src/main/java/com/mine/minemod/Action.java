package com.mine.minemod;

import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Action implements ActionInterface
{
	// Indica si la acción ha terminado para pasar a la siguiente o no
	public boolean terminado;
	
	public boolean ended() { return this.terminado; }
	public void setTerminado(boolean v) { this.terminado = v; }
	
	public void reset(EntityPlayerSP input)
	{
		input.movementInput.moveForward = 0.0f; 
		input.movementInput.moveStrafe = 0.0f;
		input.setSneaking(false);
		input.setSprinting(false);
		input.setJumping(false);
	}
}
