import java.awt.*;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;

public class Project extends JPanel{
    private   JLabel username;
    private   JLabel label2;
    private  JFrame frame2;
    private   JTextField textarea;
    private   JLabel password;
    private   JPasswordField password1;
    private JButton  login;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private  Project thisApp;
    private   JPanel panel1;
    private   JPanel panel2;
    private   JPanel panel3;
  private Inventory inventory;

    static private JFrame frame;
  
    public Project(){
        super(new GridLayout(2,1));
        
        thisApp=this;

        panel1 = new JPanel();
        panel2 = new JPanel();
        //panel3= new JPanel ();
        

        panel1.setLayout(new GridLayout(2,2));

        username = new JLabel("USERNAME");
        username.setBounds(10,20,80,25);
    
        textarea =new JTextField(15);
        textarea.setBounds(100, 20, 165, 25);
    
        password = new JLabel("PASSWORD");
        password.setBounds(50,50,80,25);
    
        password1 = new JPasswordField(15);
        password1.setBounds(100,50,165,25);
        
        login = new JButton("Sign in");
        login.setBounds(50,80,80,25);
        
        panel1.add(username); 
        panel1.add(textarea);
        panel1.add(password); 
        panel1.add(password1);
        //panel1.add(login);

        panel2.add(login);
        add(panel1);
		add(panel2);

        login.addActionListener(new FileLoginButtonListener());

    } // End of Quote



    private static void SetUPGUI(){  
        frame = new JFrame("Login");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        Project PJ= new Project(); 
     
        frame.setContentPane(PJ);
        frame.setVisible(true);
        
        frame.pack();
    
    } // end of quote


    public static void main(String [] args){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SetUPGUI();
                System.out.println("Welcome to Sterling's Bicycle Management System.");
                
            }
        });
}


     private class FileLoginButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e){
            
			try{
				Scanner fscan = new Scanner(new File("files/credentials.txt"));
				String tusern = textarea.getText();
				String tpassw = new String(password1.getPassword());
				
				while(fscan.hasNext()){
					String [] cred = fscan.nextLine().split(" ");
					String runame = cred[1];
					String rpword = cred[2];
					
					if(tusern.equals(runame) && tpassw.equals(rpword)){
						Inventory invent = new Inventory();
						System.out.println("works!");
						frame.dispose();
					}
					else{
						System.out.println("wwhoopps!");
					}
				}
			}
			catch(Exception fe)
			{
				System.out.println("exception");
			}
        }
	}

}