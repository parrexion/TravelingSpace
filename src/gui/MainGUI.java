package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Building;
import data.City;
import data.City.Res;

public class MainGUI {

	private JFrame mainFrame;
	private CityGUIGlue glue;

	private JLabel pop;
	
	private JLabel foodTot;
	private JLabel indTot;
	private JLabel goldTot;
	private JLabel infTot;
	private JLabel happyTot;
	
	private JLabel foodProd;
	private JLabel indProd;
	private JLabel goldProd;
	private JLabel infProd;
	
	private JLabel buildQueue;
	
	
	public MainGUI(CityGUIGlue glue) {
		this.glue = glue;
		
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.add(SetupValueLabels(), BorderLayout.NORTH);
		mainFrame.add(buildPanel(), BorderLayout.WEST);
		mainFrame.add(turnPanel(), BorderLayout.EAST);

		JPanel commandPanel = new JPanel();
		commandPanel.setPreferredSize(new Dimension(500, 100));
		commandPanel.setLayout(new BorderLayout());
		commandPanel.add(setupProdLabels(), BorderLayout.NORTH);
		commandPanel.add(upgradePanel(), BorderLayout.SOUTH);
		mainFrame.add(commandPanel, BorderLayout.SOUTH);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public void updateCity(City city) {
		int turns = (int) Math.ceil((City.GROWTH - city.getTot(Res.FOOD)) / (double)city.getProd(Res.FOOD));
		pop.setText("POP:  " + city.pop + " - New in " + turns + " Turns");
		foodTot.setText("FOOD:  " + city.getTot(Res.FOOD));
		Building build = city.getBulidQueue();
		if (build != null) {
			turns = (int) Math.ceil((build.cost - city.getTot(Res.IND)) / (double)city.getProd(Res.IND));
			indTot.setText("IND:  " + city.getTot(Res.IND) + "  - Done in " + turns + " Turns");
		}
		else {			
			indTot.setText("IND:  " + city.getTot(Res.IND));
		}
		goldTot.setText("GOLD:  " + city.getTot(Res.GOLD));
		infTot.setText("INF:  " + city.getTot(Res.INF));
		happyTot.setText("HAPPY:  " + city.getTot(Res.HAPPY) + "%");
		
		foodProd.setText("FOOD:  " + city.getProd(Res.FOOD));
		indProd.setText("IND:  " + city.getProd(Res.IND));
		goldProd.setText("GOLD:  " + city.getProd(Res.GOLD));
		infProd.setText("INF:  " + city.getProd(Res.INF));
		
		Building b = city.getBulidQueue();
		buildQueue.setText("BUILD:  " + ((b != null) ? b.type.toString() : "X"));
	}
	
	private JPanel SetupValueLabels() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		pop = new JLabel("POP");
		foodTot = new JLabel("FOOD");
		indTot = new JLabel("IND");
		goldTot = new JLabel("GOLD");
		infTot = new JLabel("INF");
		happyTot = new JLabel("HAPPY");
		panel.add(pop);
		panel.add(foodTot);
		panel.add(indTot);
		panel.add(goldTot);
		panel.add(infTot);
		panel.add(happyTot);
		
		return panel;
	}
	
	private JPanel setupProdLabels() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300,50));

		foodProd = new JLabel("FOOD");
		indProd = new JLabel("IND");
		goldProd = new JLabel("GOLD");
		infProd = new JLabel("INF");
		panel.add(foodProd);
		panel.add(indProd);
		panel.add(goldProd);
		panel.add(infProd);
		
		return panel;
	}
	
	private JPanel upgradePanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300,50));
		JButton foodButton = new JButton("FOOD");
		foodButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.UpgradeProd(Res.FOOD); }
		});
		panel.add(foodButton);
		JButton indButton = new JButton("IND");
		indButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.UpgradeProd(Res.IND); }
		});
		panel.add(indButton);
		JButton goldButton = new JButton("GOLD");
		goldButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.UpgradeProd(Res.GOLD); }
		});
		panel.add(goldButton);
		JButton infButton = new JButton("INF");
		infButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.UpgradeProd(Res.INF); }
		});
		panel.add(infButton);
		return panel;
	}
	
	private JPanel turnPanel() {
		JPanel panel = new JPanel();
		JButton endButton = new JButton("END TURN");
		endButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.endTurn(); }
		});
		panel.add(endButton);
		return panel;
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel();
		buildQueue = new JLabel("BUILD");
		panel.add(buildQueue);
		JButton buildButton = new JButton("BUILD");
		panel.add(buildButton, BorderLayout.SOUTH);
		buildButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) { glue.Build(); }
		});
		return panel;
	}
}
