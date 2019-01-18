/*
 * NewJPanel.java
 *
 * Created on 16 דצמבר 2008, 16:48
 */



/**
 *
 * @author  Orel_Shai
 */
/**
public class main extends javax.swing.JPanel {

 
    public main() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JFrame();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(61, 61, 61)
                .addComponent(jButton2)
                .addGap(36, 36, 36)
                .addComponent(jButton3)
                .addContainerGap(482, Short.MAX_VALUE))
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrame1))
        );
        
        
        jInternalFrame1.setLayout(new GridLayout());
		java.awt.Container cp=getRootPane().getContentPane();
		
		cp.setLayout(new GridLayout(8,8));
		
		cp.add(_orderNum);
		cp.add(new JLabel());
		cp.add(_toppings);
		cp.add(new JLabel());
		cp.add(_olives);
		cp.add(new JLabel());
		cp.add(_onion);
		cp.add(new JLabel());
		cp.add(_mushrooms);
		cp.add(new JLabel());
		cp.add(_totalPrice);
		cp.add(new JLabel());
		cp.add(_send);
		cp.add(new JLabel());
		cp.add(_orderStatus);
		cp.add(new JLabel());
		cp.add(_oldOrder);
		cp.add(_exit);
		
		
		
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private JFrame jInternalFrame1;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration

}
*/
/**
package othello;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class main implements ActionListener {
	
	private JFrame window = new JFrame("Tic-Tac-Toe");
	private JButton button1 = new JButton("x");
	private JButton button2 = new JButton("x");
	private JButton button3 = new JButton("x");
	private JButton button4 = new JButton("x");
	private JButton button5 = new JButton("x");
	private JButton button6 = new JButton("x");
	private JButton button7 = new JButton("x");
	private JButton button8 = new JButton("x");
	private JButton button9 = new JButton("x");


	
	public main(){

	
	window.setSize(300,300);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setLayout(new GridLayout(3,3));
	
	
	window.add(button1);
	window.add(button2);
	window.add(button3);
	window.add(button4);
	window.add(button5);
	window.add(button6);
	window.add(button7);
	window.add(button8);
	window.add(button9);
	
	
	button1.addActionListener(this);
	button2.addActionListener(this);
	button3.addActionListener(this);
	button4.addActionListener(this);
	button5.addActionListener(this);
	button6.addActionListener(this);
	button7.addActionListener(this);
	button8.addActionListener(this);
	button9.addActionListener(this);
	

	window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a) {
	System.out.println("A button was pressed.");
		
	}
	
	public static void main(String[] args){
		new main();
	}
}*/
package othello;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class main implements ActionListener
{
    //Class constants
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int TEXT_WIDTH = 20;
   
    //Design
    private static final GridLayout LAYOUT_STYLE = new GridLayout(8,8);
   
    //Instance variables
    private JFrame window = new JFrame("TIC-TAC-TOE");
    
 //   private JButton box1 = new JButton("");
  //  private JButton box2 = new JButton("");
  //  private JButton box3 = new JButton("");
  //  private JButton box4 = new JButton("");
  //  private JButton box5 = new JButton("");
  //  private JButton box6 = new JButton("");
  //  private JButton box7 = new JButton("");
  //  private JButton box8 = new JButton("");
  //  private JButton box9 = new JButton("");
    private JButton[] boxes;
    String mark = "X";
    boolean win = false;
    Color black = new Color(0,0,0);
    Color notblack = new Color(255,255,255);
    //Constructor
    public main()
    {
        window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //Add to window
        boxes=new JButton[64];
        window.getContentPane().setLayout(LAYOUT_STYLE);
        for (int  i= 0;  i<boxes.length ; i++) {
        	boxes[i]=new JButton();
        	window.getContentPane().add(boxes[i]);
        	boxes[i].addActionListener(this);
		}
        
       

        window.setVisible(true);
    }
        //ActionPerformed
    public void actionPerformed(ActionEvent e)
    {
    	
    }
    
    /**
        int count=1;
    do
    {
       
    if (e.getSource()==box1)
    {
        box1.setText(mark);
        box1.setEnabled(false);
    }
    else if (e.getSource()==box2)
    {
        box2.setText(mark);
        box2.setEnabled(false);
    }
    else if (e.getSource()==box3)
    {
        box3.setText(mark);
        box3.setEnabled(false);
    }
    else if (e.getSource()==box4)
    {
        box4.setText(mark);
        box4.setEnabled(false);
    }
    else if (e.getSource()==box5)
    {
        box5.setText(mark);
        box5.setEnabled(false);
    }
    else if (e.getSource()==box6)
    {
        box6.setText(mark);
        box6.setEnabled(false);
    }
    else if (e.getSource()==box7)
    {
        box7.setText(mark);
        box7.setEnabled(false);
    }
    else if (e.getSource()==box8)
    {
        box8.setText(mark);
        box8.setEnabled(false);
    }
    else if (e.getSource()==box9)
    {
        box9.setText(mark);
        box9.setEnabled(false);
    }
   
    //vert check
    if (box1.getText().equals(box2.getText()) && box2.getText().equals(box3.getText()) && box1.getText().equals("")==false)
    {
        box1.setBackground(notblack);
        box2.setBackground(notblack);
        box3.setBackground(notblack);
        win=true;        
    }
    else if (box4.getText().equals(box5.getText()) && box5.getText().equals(box6.getText())&& box4.getText().equals("")==false)
    {
        box4.setBackground(notblack);
        box5.setBackground(notblack);
        box6.setBackground(notblack);    
        win=true;    
    }
    else if (box7.getText().equals(box8.getText()) && box8.getText().equals(box9.getText())&& box7.getText().equals("")==false)
    {
        box7.setBackground(notblack);
        box8.setBackground(notblack);
        box9.setBackground(notblack);
        win=true;        
    }
    else if (box1.getText().equals(box4.getText()) && box4.getText().equals(box7.getText())&& box1.getText().equals("")==false)
    {
        box1.setBackground(notblack);
        box4.setBackground(notblack);
        box7.setBackground(notblack);
        win=true;        
    }
    else if (box2.getText().equals(box5.getText()) && box5.getText().equals(box8.getText())&& box2.getText().equals("")==false)
    {
        box2.setBackground(notblack);
        box5.setBackground(notblack);
        box8.setBackground(notblack);
        win=true;        
    }
    else if (box3.getText().equals(box6.getText()) && box6.getText().equals(box9.getText())&& box3.getText().equals("")==false)
    {
        box3.setBackground(notblack);
        box6.setBackground(notblack);
        box9.setBackground(notblack);        
        win=true;    
    }
    else if (box1.getText().equals(box5.getText()) && box5.getText().equals(box9.getText())&& box1.getText().equals("")==false)
    {
        box1.setBackground(notblack);
        box5.setBackground(notblack);
        box9.setBackground(notblack);        
        win=true;
    }
    else if (box3.getText().equals(box5.getText()) && box5.getText().equals(box7.getText())&& box3.getText().equals("")==false)
    {
        box3.setBackground(notblack);
        box5.setBackground(notblack);
        box7.setBackground(notblack);        
        win=true;
    }
    else if (count==9 && win==false)
    {
        JOptionPane.showMessageDialog(null, "It's a tie!");
        win=true;
    }

    System.out.println(count);
    if (count!=9 && win==true)
    {
        JOptionPane.showMessageDialog(null, mark + " WINS!");
        System.exit(1);
    }    
        if (mark.equals("X"))
    {
        mark="O";
    }
    else
    {
        mark="X";
    }    
   
    }

        while(win=false);
       
        }*/
public static void main(String[] args)
    {
            main gui = new main();
    }
}

