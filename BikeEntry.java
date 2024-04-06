import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.event.InternalFrameListener;

public class BikeEntry extends JFrame //subclass
{
	private String [] statusOptions = {"Rented", "Repaired", "Available", "Broken"};
	private String [] brandOptions = {"Giant", "Kina", "Short", "Santa_Cruz"};
	private JComboBox<String> brandChoice = new JComboBox<String>(brandOptions);
	private JComboBox<String> statusChoice = new JComboBox<String>(statusOptions);
	
	private JPanel      pnlCommand;
	private JPanel      pnlDisplay;
	private JTextField  txtColor;   
	private JButton     cmdSave;
	private JButton     cmdClose;
	private JButton     cmdClearAll;
	
	private int ID = 6200;
	
	private BikeListing blist;
	
	//----------Constructor----------
	public BikeEntry(BikeListing blist){
		this.blist = blist; //instantiatiation
		setTitle("Entering new bike");
	  
		pnlCommand = new JPanel(); 
		pnlDisplay = new JPanel(new GridLayout(3,2));
	  
		brandChoice.setBounds(80, 50, 140, 20);
		statusChoice.setBounds(80, 50, 140, 20);
		pnlDisplay.add(new JLabel("Bike Brand:"));
		pnlDisplay.add(brandChoice);
		pnlDisplay.add(new JLabel("Bike Status:"));
		pnlDisplay.add(statusChoice);
	  
		pnlDisplay.add(new JLabel("Color:"));
		txtColor = new JTextField(20);
		pnlDisplay.add(txtColor);
	  
		cmdSave    = new JButton("Save");
		cmdClose   = new JButton("Close");
		
		cmdClose.addActionListener(new ButtonListener());
		cmdSave.addActionListener(new ButtonListener());
		
		pnlCommand.add(cmdSave);
		pnlCommand.add(cmdClose);
	  
		add(pnlDisplay, BorderLayout.CENTER);
		add(pnlCommand, BorderLayout.SOUTH);
	  
		pack();
		setVisible(true);
	}
	
	
	/** 
	 * @return int
	 */
	public int makeID()
	{
		try
		{
			File file=new File("files/bikes.txt");
			Scanner fscan=new Scanner(file);
			ID = 6200;
			while(fscan.hasNext())
			{
				String [] nextLine = fscan.nextLine().split(" ");
				if(Integer.parseInt(nextLine[0])==ID)
				{
					ID++;
				}
			}
		}
		catch(Exception e)
		{ }
		return ID;
	}


  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent cs){
      if(cs.getSource()==cmdSave){
		  boolean check = false;
        try{
          String brand = brandChoice.getItemAt(brandChoice.getSelectedIndex());
          String status = statusChoice.getItemAt(statusChoice.getSelectedIndex());
          String color = txtColor.getText();
		  int id = makeID();
		  
		  char [] colchars= color.toCharArray();
		  
		  for(char c:colchars){
			  if(Character.isDigit(c))
			  {
				check=true;
			  }
		  }
		  if(check!=true){
			  Bike b = new Bike(id, Status.valueOf(status), Brand.valueOf(brand), color);
			  blist.addBike(b);
			  
			  FileWriter file = new FileWriter(new File("files/bikes.txt"), true);
			  String bike = ""+id+" "+status+" "+brand+" "+color+"\n";
			  file.write(bike);
			  file.close();

			  setVisible(false);
			  dispose(); //destroy the Jframe
		  }
        }
        catch(Exception e){
          txtColor.setText("");
        }
      }
      else{   
        setVisible(false);
        dispose(); //destroy the Jframe 
      }
    }
  }
  
}

