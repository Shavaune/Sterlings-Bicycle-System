import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class BikeSearch extends JPanel
{
    private JLabel msg;
    private JLabel idLbl;
    private JLabel found;
    private JTextField idTxt;
    private JButton search;
    private JButton cancel;

    private JPanel msgPanel;
    private JPanel idPanel;
    private JPanel buttonPanel;
    private JPanel bikeFound;
  
    private BikeSearch thisSearch;
    private JFrame frame;
    private JFrame dFrame;

    private int idFromField;

	//Constructor
    public BikeSearch(JFrame frame)
    {
        thisSearch = this;
        this.frame = frame;

        msgPanel = new JPanel();
        idPanel = new JPanel();
        idPanel.setLayout(new GridLayout(0,2));
        buttonPanel = new JPanel();
        bikeFound = new JPanel();

        msg = new JLabel("Enter the ID of the bike to edit");
        msgPanel.add(msg);
        idLbl = new JLabel("ID:");
        idPanel.add(idLbl);
        idTxt = new JTextField(4);
        idPanel.add(idTxt);
        idPanel.setPreferredSize(new Dimension(150, 30));

        search = new JButton("Search");
        search.addActionListener(new SearchListener());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelListener());
		
		
        buttonPanel.add(search);
        buttonPanel.add(cancel);
        buttonPanel.setPreferredSize(new Dimension(349, 40));

        found = new JLabel("");
        bikeFound.add(found);

        add(msgPanel);
        add(idPanel);
        add(buttonPanel);
        add(bikeFound);

        setPreferredSize(new Dimension(400, 150));
    }

	
  
  /** 
   * @return int
   */
  public int getIDFromField()
  {
    idFromField = Integer.parseInt(idTxt.getText());
    return idFromField;
  }


  private class SearchListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        int iD = Integer.parseInt(idTxt.getText());
        BufferedReader br = new BufferedReader(new FileReader("files/bikes.txt"));
		String bike;
		while((bike = br.readLine()) != null){
			String [] bikestuff = bike.split(" ");
			if(Integer.parseInt(bikestuff[0])==iD){
				dFrame = new JFrame("Bike "+iD);
				dFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                    
				BikeDisplay bDisplay = new BikeDisplay(thisSearch, dFrame, bikestuff);                    
				bDisplay.setOpaque(true);
				dFrame.setContentPane(bDisplay);
				dFrame.pack();
				dFrame.setVisible(true);
				break;				
			}
			else{
			  throw new Exception();
			}
		} 
	  }
      catch(Exception exception)
      {
        found.setText("Bike not found. Please try again");
        idTxt.setText("");                
        System.out.println(exception.getClass().getName());
      }       
    }
  }

	//button to cancel searching
  private class CancelListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        setVisible(false);
        frame.dispose();
      }
  }   
}
