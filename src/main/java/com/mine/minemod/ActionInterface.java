package com.mine.minemod;

import net.minecraft.client.entity.EntityPlayerSP;

public interface ActionInterface
{
	/**
	 * Realizar la acci�n sobre el input dado y actualizar la variable <i>terminado</i> si procede
	 * @param input es el objeto input donde se realizan las acciones necesarias
	 */
	public void makeAction(EntityPlayerSP input);
	
	/**
	 * Comprobaci�n para indicar la finalizaci�n de la acci�n (actualizar variable <i>terminado</i>).
	 * Generalmente, esta funci�n se ejecuta justo despu�s del makeAction()
	 * @param input es el objeto input donde se realizan las acciones necesarias
	 */
	public void endAction(EntityPlayerSP input);
}
