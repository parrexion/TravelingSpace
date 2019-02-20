package gui;

import data.Building;
import data.City;
import data.City.Res;
import main.CityController;

public class CityGUIGlue {

	private CityController cityController;

	
	public CityGUIGlue(CityController cityController) {
		this.cityController = cityController;
	}
	
	public void endTurn() {
		City city = cityController.getCity();
		city.produce();
		cityController.updateGUI();
	}
	
	public void UpgradeProd(Res type) {
		City city = cityController.getCity();
		city.improveProd(type);
		cityController.updateGUI();
	}
	
	public void Build() {
		City city = cityController.getCity();
		city.StartBuild(Building.buildType.STORAGE);
		cityController.updateGUI();
	}
}
