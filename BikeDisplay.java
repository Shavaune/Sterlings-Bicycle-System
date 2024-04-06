import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.*;

public class BikeDisplay extends JPanel
{
	private JButton delete;
	private JButton update;
	private JButton close;
	private JPanel tblPanel;
	private JPanel crsPanel;
	private JPanel buttonPanel;    
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane crsScroll;
	private JTable crsTable;
	private DefaultTableModel crsModel;

	private BikeDisplay thisDisplay;
	private BikeSearch bScreen;
	private String [] b;
	private JFrame frame;
	
	private BikeListing bikeListing;
	private ArrayList<Bike> blist = new ArrayList<Bike>();

	
	//Constructor
	public BikeDisplay(BikeSearch bScreen, JFrame frame, String[] bike)
	{
		super(new GridLayout(3, 1));
		thisDisplay = this;
		this.bScreen = bScreen;
		this.frame = frame;
		this.b = bike;
		
		tblPanel = new JPanel();
		crsPanel = new JPanel();
		buttonPanel = new JPanel();        

		String[] columns = {"Bike ID", "Status", "Brand", "Color"};
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
			
		System.out.println(bike[0]);
		String [] bInfo = {bike[0], bike[1], bike[2], bike[3]};
		model.addRow(bInfo);

		table.setPreferredScrollableViewportSize(new Dimension(700, bInfo.length*15+50));
		table.setFillsViewportHeight(true);

		scroll = new JScrollPane(table);
		tblPanel.add(scroll);   
		tblPanel.setPreferredSize(new Dimension(700, 50)); 

		delete = new JButton("Delete");
		delete.addActionListener(new ButtonListener());
		close = new JButton("Close");
		close.addActionListener(new CloseListener());
		
		buttonPanel.add(delete);
		buttonPanel.add(close);

		add(tblPanel);
		add(crsPanel);
		add(buttonPanel, BorderLayout.SOUTH);

		frame.add(thisDisplay);

		setPreferredSize(new Dimension(700, 400));
	}



    public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           
		try{
			int j = 0;
			
			blist = bikeListing.loadBikes("files/bikes.txt");
			System.out.println(blist);
			if (blist.size()>=0)
			{
				for (int i=0; i<blist.size(); i++)
				{
					if(blist.get(i).getID()==Integer.parseInt(b[0]))
					{
						blist.remove(i);
						FileWriter bik = new FileWriter(new File("files/bikes"), false);
						PrintWriter bwOb = new PrintWriter(bik, false);
						bwOb.flush();
						bwOb.close();

						File newb = new File("files/bikes");
						FileWriter rewriteb= new FileWriter(newb,true);

						while(j<blist.size()){
						  Bike rbike = blist.get(j);
						  int id = rbike.getID();
						  Status stat = rbike.getStatus();
						  Brand brand = rbike.getBrand();
						  String col = rbike.getColor();
						  
						  String bike = ""+id+" "+stat.name()+" "+brand.name()+" "+col+"\n";
						  rewriteb.write(bike);
						  j++;
						}
						rewriteb.close();
						break;
					}
				}
			}
		}
		catch(Exception io)
		{}
        }
     }
 


	//method to close the current screen
    public class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent a)
        {
            setVisible(false);
            frame.dispose();
        }
    }
}
