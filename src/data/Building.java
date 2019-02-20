package data;

public class Building {

	public enum buildType { BASE, STORAGE }
	
	public buildType type;
	public int foodProd;
	public int indProd;
	public int goldProd;
	public int infProd;

	public int happy;
	public int cost;
	public int upkeep;
	
	
	public static Building CreateBuilding(buildType type) {
		Building b = new Building();
		b.type = type;
		switch (type) {
		case BASE:
			b.foodProd = 200;
			b.indProd = 200;
			b.upkeep = 50;
			return b;
		case STORAGE:
			b.cost = 2000;
			b.upkeep = 20;
			return b;
		default:
			return null;
		}
	}
}
