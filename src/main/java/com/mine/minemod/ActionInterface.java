package com.mine.minemod;

import net.minecraft.client.entity.EntityPlayerSP;

public interface ActionInterface
{
	/**
	 * Realizar la acción sobre el input dado y actualizar la variable <i>terminado</i> si procede
	 * @param input es el objeto input donde se realizan las acciones necesarias
	 */
	public void makeAction(EntityPlayerSP input);
	
	/**
	 * Comprobación para indicar la finalización de la acción (actualizar variable <i>terminado</i>).
	 * Generalmente, esta función se ejecuta justo después del makeAction()
	 * @param input es el objeto input donde se realizan las acciones necesarias
	 */
	public void endAction(EntityPlayerSP input);
}
