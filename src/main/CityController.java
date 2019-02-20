package main;

import data.City;
import gui.CityGUIGlue;
import gui.MainGUI;

public class CityController {

	private CityGUIGlue glue;
	private City city;
	private MainGUI gui;
	
	
	public CityController() {
		glue = new CityGUIGlue(this);
		gui = new MainGUI(glue);
		city = new City();
		gui.updateCity(city);
	}
	
	public City getCity() {
		return city;
	}
	
	public void updateGUI() {
		gui.updateCity(city);
	}
}
