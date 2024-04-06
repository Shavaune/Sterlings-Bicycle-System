import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;
import java.io.IOException;


public class BikeListing extends JPanel{
	private JTable bikeTable;
	private DefaultTableModel bikeModel;
	private JLabel bikeMsg;
	
	private JScrollPane scrollPane;
	private JPanel pnlDisplay;
	private JPanel msgDisplay;
	private JPanel container;
	private JPanel pnlCommand;
	
	private JButton     cmdSortID;
	private JButton     cmdSortBrand;
	private JButton     cmdClose;
	private JButton     cmdAdd;
	
	private JFrame frame;
	private JFrame main;
	private BikeListing thisList;
	private ArrayList<Bike> bikeList = new ArrayList<Bike>();
	
	public BikeListing(JFrame frame, JFrame main, String fname){
		super(new GridLayout(2, 1));
		thisList = this;
		this.frame = frame;
		this.main = main;
		
		pnlCommand=new JPanel();
		pnlDisplay=new JPanel();
		msgDisplay=new JPanel();
		container=new JPanel(new GridLayout(3, 1));
		
		bikeList = loadBikes(fname);
		String[] columnNames = {"Bike ID", "Status", "Brand", "Color"};

		//Adds a table model to the first section of the grid
		bikeMsg = new JLabel("Welcome to Bike Listings");
		msgDisplay.add(bikeMsg);
		
		bikeModel = new DefaultTableModel(columnNames, 0);
		bikeTable = new JTable(bikeModel);
		bikeModel.setRowCount(0);
		showTable(bikeList);

		bikeTable.setPreferredScrollableViewportSize(new Dimension(500, bikeList.size()*25+75));
		bikeTable.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(bikeTable);
		pnlDisplay.add(scrollPane);
		
		container.add(msgDisplay);
		container.add(pnlDisplay);
		add(container);
		
		cmdSortID = new JButton("Sort by ID");
		cmdSortBrand = new JButton("Sort by Brand");
		cmdAdd = new JButton("Add Bike");
		cmdClose = new JButton("Exit Bike Listing");
		
		cmdSortID.addActionListener(new SortButtonListener());
		cmdSortBrand.addActionListener(new SortButtonListener());
		cmdAdd.addActionListener(new AddButtonListener());
		cmdClose.addActionListener(new CloseButtonListener());
	
		pnlCommand.add(cmdSortID);
		pnlCommand.add(cmdSortBrand);
		pnlCommand.add(cmdAdd);
		pnlCommand.add(cmdClose);
		
		add(pnlCommand);
		frame.setPreferredSize(new Dimension(600, 550));
	}
	
	private void showTable(ArrayList<Bike> bikeList)
    {
      if (bikeList.size()>0)
        for(int j=0;bikeList.size()>j;j++)
          addToTable(bikeList.get(j));
    }
	
	private void addToTable(Bike b)
	{
		int iD = b.getID();
		Status stat = b.getStatus();
		Brand brand = b.getBrand();
		String col = b.getColor();

		String[] item= {""+iD, ""+stat.name(), brand.name(), col};
		bikeModel.addRow(item);
	}
	
	public void addBike(Bike b)
	{
		bikeList.add(b);
		addToTable(b);
	}
	
	public ArrayList<Bike> loadBikes(String bfile)
	{
    Scanner bscan = null;
	ArrayList<Bike> bList = new ArrayList<Bike>();
	
    try
    {
      bscan  = new Scanner(new File(bfile));
      while(bscan.hasNext())
      {
        String [] nextLine = bscan.nextLine().split(" ");

		int iD = Integer.parseInt(nextLine[0]);
		Status stat = Status.valueOf(nextLine[1]);
		Brand brand = Brand.valueOf(nextLine[2]);
		String col = nextLine[3];
		
		Bike bike = new Bike(iD, stat, brand, col);
		bList.add(bike);
      }
      bscan.close();
    }
    catch(IOException e)
    {}
    return bList;
  }
  
  public class SortButtonListener implements ActionListener
  {
    //Overrided Method
    public void actionPerformed(ActionEvent s)
    {
      if(s.getSource()==cmdSortID)
      {
        Collections.sort(bikeList, new CompareByID());
        bikeModel.setRowCount(0);
        showTable(bikeList);	
      }
      else if(s.getSource()==cmdSortBrand)
      {
        Collections.sort(bikeList, new CompareByBrand());
        bikeModel.setRowCount(0);
        showTable(bikeList);
      }
    }
  }
	private class AddButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			BikeEntry entry = new BikeEntry(thisList);
		}

    }
	
	//Action Listener for Close Button
	public class CloseButtonListener implements ActionListener
	{
		//Overrided Method
		public void actionPerformed(ActionEvent c)
		{
			frame.dispose();
			main.setVisible(true);
		}
	}

}