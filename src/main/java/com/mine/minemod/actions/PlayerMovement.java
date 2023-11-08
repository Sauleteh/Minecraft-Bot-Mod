package com.mine.minemod.actions;

import com.mine.minemod.Action;
import com.mine.minemod.BotMovementInput;

import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerMovement extends Action
{
	private float movimientoLongitudinal; // Valores negativos para ir hacia atrás, positivos para ir hacia adelante y 0 para no moverse longitudinalmente
	private float movimientoLateral; // Valores negativos para ir hacia la izquierda, positivos para ir hacia la derecha y 0 para no moverse lateralmente
	private boolean agachandose;
	private boolean corriendo;
	private boolean saltando;
	
	// Posiciones del jugador cuando empieza la acción por primera vez
	private Float startX;
	private Float startZ;
	
	public PlayerMovement(float mLon, float mLat, boolean a, boolean c, boolean s)
	{
		this.movimientoLongitudinal = mLon;
		this.movimientoLateral = mLat;
		this.agachandose = a;
		this.corriendo = c;
		this.saltando = s;
		
		this.startX = null;
		this.startZ = null;
	}
	
	@Override
	public void makeAction(EntityPlayerSP input)
	{
		if (startX == null && startZ == null)
		{
			//this.startX = input.playerLocation.getX();
			//this.startZ = input.playerLocation.getZ();
		}
		
		
		input.movementInput.moveForward = calculateSpeed(input, this.movimientoLongitudinal); 
		input.movementInput.moveStrafe = calculateSpeed(input, this.movimientoLateral);
		input.setSneaking(this.agachandose);
		input.setSprinting(this.corriendo);
		input.setJumping(this.saltando);
		
		this.endAction(input);
	}

	@Override
	public void endAction(EntityPlayerSP input)
	{
		// TODO
		
		// Si hay que terminar la acción, ejecutar el reset de arriba
	}
	
	private float calculateSpeed(EntityPlayerSP input, float move)
	{
		float speed = 0.0f;
		
		if (move > 0) speed = 1.0f;
		else if (move < 0) speed = -1.0f;
		if (this.agachandose) speed *= 0.3f;
		
		return speed;
	}
}
