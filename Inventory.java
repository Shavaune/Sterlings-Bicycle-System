import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;


public class Inventory extends JFrame{
	private JFrame thisFrame;
	private JPanel panel;
	private JPanel panel2;
	private JLabel msg;
	
	private JButton seebutton;
	private JButton updatebutton;
	private JButton button3;
	private JButton close;
	
	//Constructor
	public Inventory(){
		thisFrame=this;
		setTitle("Sterling's Inventory");
		panel = new JPanel();
		panel2 = new JPanel();
		
		setSize(550, 100);
		
		msg = new JLabel("Welcome to Sterling's Bike Inventory");
		panel.add(msg);
		
		seebutton =  new JButton("See Available Bikes");
		updatebutton =  new JButton("Update Bike Log");
		close =  new JButton("Logout");
		
		seebutton.addActionListener(new SeeButtonListener());
		updatebutton.addActionListener(new UpdateButtonListener());
		close.addActionListener(new CloseButtonListener());

		panel2.add(seebutton);
    panel2.add(updatebutton);
		panel2.add(close);
		panel.add(panel2);
		
		add(panel);
		
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);
    pack();
  }
	
	
	private class SeeButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			JFrame seeFrame = new JFrame("Sterling's Bike Listings");
            seeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            BikeListing lst = new BikeListing(seeFrame, thisFrame, "files/bikes.txt");
            lst.setOpaque(true);
			
            seeFrame.setContentPane(lst);
            seeFrame.pack();
            seeFrame.setVisible(true);
		}

    }
	
	
	private class UpdateButtonListener implements ActionListener{
    

		public void actionPerformed(ActionEvent e){
			JFrame upFrame = new JFrame("Search for a Bike");
            upFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            BikeSearch lst = new BikeSearch(upFrame);
            lst.setOpaque(true);
			
            upFrame.setContentPane(lst);
            upFrame.pack();
            upFrame.setVisible(true);
		}

    }
	
	
	private class CloseButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			Project ns = new Project();
			setVisible(false);
        	dispose();
		}

    }
}