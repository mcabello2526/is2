package Presentacion.FactoryGUI;

import Presentacion.IGUI;

public abstract class FactoryGUI {
	private static FactoryGUI instance;

	public static FactoryGUI getInstance() {
		if (instance == null) {
			instance = new FactoryGUIImp();
		}
		return instance;
	}

	public abstract IGUI getGUI(int evento);
	
}
