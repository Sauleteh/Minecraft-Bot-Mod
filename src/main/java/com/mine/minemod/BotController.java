package com.mine.minemod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;

public class BotController
{
	private EntityPlayerSP input;
	private List<Action> acciones;
	private int indice;
	
	public BotController(EntityPlayerSP p)
	{
		this.input = p;
		this.acciones = new ArrayList<Action>();
		this.indice = 0;
		
		// TODO: Cargamos todas las acciones en el array
		// accion = new Action(...); acciones.add(accion); ...
	}
	
	public void execute()
	{
		Action accion = acciones.get(indice);
		if (!accion.ended()) accion.makeAction(input);
		else
		{
			if (++indice >= acciones.size()) indice = 0;
			this.execute();
		}
	}
}
