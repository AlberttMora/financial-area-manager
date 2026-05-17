package app;

import controller.ControllerGestorAreas;

public class Main {
	public static void main(String[] args) throws Exception { 
			Inicializador init = new Inicializador();
			new ControllerGestorAreas(init.getGestorAreas(), init.getGestorAdmin(), init.getAdmin());
	}
}