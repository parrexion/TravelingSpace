package data;

import java.util.ArrayList;
import java.util.Random;

import data.Building.buildType;

public class City {

	public static final int GROWTH = 3000;
	public static final int EAT = 20;
	public static final int POP_UNHAPPY = 2;
	
	public enum Res { FOOD, IND, GOLD, INF, HAPPY }

	public int pop;
	
	private int foodTot;
	private int indTot;
	private int goldTot;
	private int infTot;
	private int happyTot;
	
	private int foodProd;
	private int indProd;
	private int goldProd;
	private int infProd;
	
	private Building buildQueue;
	private ArrayList<Building> buildings;
	
	
	public City() {
		pop = 1;
		
		foodTot = 20;
		indTot = 15;
		goldTot = 10;
		infTot = 5;
		happyTot = 50;
		
		Random r = new Random();
		foodProd = r.nextInt(12)*5 + 50;
		indProd = r.nextInt(12)*5 + 40;
		goldProd = r.nextInt(12)*5 + 30;
		infProd = r.nextInt(7)*5 + 10;
		
		buildings = new ArrayList<Building>();
		buildings.add(Building.CreateBuilding(buildType.BASE));
	}
	
	public int getTot(Res type) {
		switch (type) {
		case FOOD:
			return foodTot;
		case GOLD:
			return goldTot;
		case IND:
			return indTot;
		case INF:
			return infTot;
		case HAPPY:
			return happyTot - pop * POP_UNHAPPY;
		default:
			return -1;
		}
	}
	
	public int getProd(Res type) {
		switch (type) {
		case FOOD:
			return (foodProd - EAT) * pop + buildingProd(type);
		case GOLD:
			return goldProd * pop - totalUpkeep() + buildingProd(type);
		case IND:
			return indProd * pop + buildingProd(type);
		case INF:
			return infProd * pop + buildingProd(type);
		default:
			return -1;
		}
	}
	
	public void produce() {
		foodTot += getProd(Res.FOOD);
		indTot += getProd(Res.IND);
		goldTot += getProd(Res.GOLD);
		infTot += getProd(Res.INF);
		
		if (foodTot >= GROWTH) {
			foodTot -= GROWTH;
			pop++;
		}
		if (buildQueue != null && indTot >= buildQueue.cost) {
			indTot -= buildQueue.cost;
			buildings.add(buildQueue);
			buildQueue = null;
		}
	}
	
	public void improveProd(Res type) {
		switch (type) {
		case FOOD:
			foodProd += 5;
			break;
		case GOLD:
			goldProd += 5;
			break;
		case IND:
			indProd += 5;
			break;
		case INF:
			infProd += 5;
			break;
		default:
			break;
		}
	}
	
	public int totalUpkeep() {
		int sum = 0;
		for (int i = 0; i < buildings.size(); i++) {
			sum += buildings.get(i).upkeep;
		}
		return sum;
	}
	
	private int buildingProd(Res type) {
		int sum = 0;
		for (int i = 0; i < buildings.size(); i++) {
			Building b = buildings.get(i);
			switch (type) {
			case FOOD:
				sum += b.foodProd;
				break;
			case GOLD:
				sum += b.goldProd;
				break;
			case IND:
				sum += b.indProd;
				break;
			case INF:
				sum += b.infProd;
				break;
			case HAPPY:
				sum += b.happy;
				break;
			default:
				break;
			}
		}
		return sum;
	}
	
	public void StartBuild(Building.buildType type) {
		if (buildQueue != null)
			return;
		buildQueue = Building.CreateBuilding(type);
	}
	
	public Building getBulidQueue() {
		return buildQueue;
	}
}
