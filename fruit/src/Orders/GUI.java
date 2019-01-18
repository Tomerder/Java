package Orders;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.xmlbeans.XmlException;

public class GUI 
{
	private static final String VERSION = "2.08";
	//2.04 - make order + add order remark to CSV + outputs name by date and time
	//2.05 - add ability to skip customer if not exist on DB (customers excel file)
	//2.06 - add ability to replace customer if not exist on DB (customers excel file)
	//2.07 - add ability to replace item if not exist on DB (items excel file) + fix customer not exist bugg (name = null on output) 
	//2.08 - add UOM validation 
	
    static String pathSelected;
    static String dateSelected;

	//----------------------------------------------------------------

	public static String getDateSelected() 
	{
		return dateSelected;
	}

	static JFrame frame;
	
	//----------------------------------------------------------------
	
	public static void main(String[] args)
	{
	    // schedule this for the event dispatch thread (edt)
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      @Override
		public void run()
	      {
		    	if(IsExpireDateOver()){
		    		displayExpireMsg();
				}else{  				
					try {
						displayJFrame();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	      }
	    });
	  }
	  
	  //----------------------------------------------------------------
	 
	  static public String GetPathSelected()
	  {
		  return pathSelected;
	  }
	  
	  //----------------------------------------------------------------
	  
	  /*Logger logger = Logger.getLogger(getClass());  */
	  
	  static void displayJFrame() throws FileNotFoundException
	  {   
		  File file = new File("log.log");
		  final PrintStream ps = new PrintStream(file);
		    
		  		  
	    frame = new JFrame(" הזמנות " + VERSION);
	    
	    //create date text field
	    Date today = new Date();
	    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	    if(today.getHours() > 12)
	    {
	    	today.setDate(today.getDate() + 1);
	    }
	    String date = format1.format(today);            
	    final JTextField dateTextField = new JTextField(date);
	    dateTextField.setSize(20, 10);
	    	    
	    //create PATH text field
	    String fullPath = GUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    String path = fullPath.substring(0, fullPath.lastIndexOf("/") + 1);
	    final JTextField pathTextField = new JTextField(path,20);
	    	    
	    
	    // create our jbutton
	    JButton showDialogButton = new JButton("יצירת קובץ הזמנות");
	    showDialogButton.setSize(10, 5);
	    
	    // add the listener to the jbutton to handle the "pressed" event
	    showDialogButton.addActionListener(new ActionListener()
	    {
	      @Override
		public void actionPerformed(ActionEvent e)
	      {
	        // display/center the jdialog when the button is pressed
	        /*JDialog d = new JDialog(frame, "Hello", true);
	        d.setLocationRelativeTo(frame);
	        d.setVisible(true);
	        */  
	    	Boolean isExeption = false;  
	    	pathSelected = pathTextField.getText();
	    	dateSelected = dateTextField.getText();
	    	
	    	String[] args = null;
	    	try {
				Main.main(args);                           //run MAIN
			} catch (IOException e1) {
				isExeption = true;
				e1.printStackTrace(ps);
			} catch (ParseException e1) {
				isExeption = true;
				e1.printStackTrace(ps);
			} catch (XmlException e1) {
				isExeption = true;
				e1.printStackTrace(ps);
			} catch (RuntimeException e1) {
				isExeption = true;
				e1.printStackTrace(ps);
			} catch (Exception e1) {
				isExeption = true;
				e1.printStackTrace(ps);
			}finally{
				ps.close();
				JDialog d = null;
				if (isExeption){
					d = new JDialog(frame, "שגיאה", true);
				}else{
					d = new JDialog(frame, "קובץ הזמנות נוצר", true);
				}	    	
		        d.setBounds(10, 10, 200 ,30);
		    	d.setLocationRelativeTo(frame);
		        d.setVisible(true);		   	        
			}
	      }
	    });

	    // put the button on the frame
	    JPanel area0 = new JPanel();
	    area0.add(dateTextField);
	    
	    JPanel area1 = new JPanel();
	    area1.add(pathTextField);
	    
	    JPanel area2 = new JPanel();
	    area2.add(showDialogButton);
	    
	    
	  //  frame.getContentPane().setLayout(new FlowLayout());
	    frame.getContentPane().setLayout(new BorderLayout());
	    frame.add(area0 , BorderLayout.NORTH);
	    frame.add(area1 , BorderLayout.CENTER);
	    frame.add(area2 , BorderLayout.SOUTH);
	    
	    //frame.add(pathTextField);
	    //frame.add(showDialogButton);

	    // set up the jframe, then display it
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(300, 200));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	  }
	  
	  
	  //-----------------------------------------------------------------------
	  
	  static void displayExpireMsg()
	  {
		    JDialog d = new JDialog(frame, "נא עדכן גרסה", true);
	        d.setBounds(10, 10, 200 ,30);
	    	d.setLocationRelativeTo(frame);
	        d.setVisible(true);  
	  }
	  
	  //-----------------------------------------------------------------------
  
	  static boolean IsExpireDateOver()
	  {	  
		  /*
		    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   
			Date today = new Date();
		
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,2016);
			cal.set(Calendar.MONTH,9);
			cal.set(Calendar.DATE,1);	
			Date expireDate = cal.getTime();
			
			if(today.after(expireDate) )
			{
				return true;
			}
			*/
			return false;
	  }
	  
	  //-----------------------------------------------------------------------
	  
	  public static Boolean DisplayConfirmDialog(String _title, String _message) 
      {
		  Boolean toReturn = false;
		  
	      int dialogResult = JOptionPane.showConfirmDialog( null, _message, _title, JOptionPane.YES_NO_OPTION);
	      if(dialogResult == JOptionPane.YES_OPTION)
	      {
	    	  toReturn = true;
	      }
		      		    	
		  return toReturn;
	  }
	  
	  //-----------------------------------------------------------------------
	  
	  public static String DisplayInputDialog(String _title, String _message) 
      {
		  Boolean toReturn = false;
		  
		  String retStr =  JOptionPane.showInputDialog(null, _message, _title, JOptionPane.OK_CANCEL_OPTION);	  
		  //JOptionPane.showMessageDialog(null,message);
		  
		  return retStr;
      }
	  
	  //-----------------------------------------------------------------------
  
	  public static String DisplaySelectionDialog(String _title, String _message, ArrayList<String> _values) 
      {		  		  
		  int retStrIndex =  JOptionPane.showOptionDialog(null, _message, _title, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, _values.toArray(), _values.get(0));
				  //InputDialog(null, _message, _title, JOptionPane.OK_CANCEL_OPTION);	  
		  //JOptionPane.showMessageDialog(null,message);
		  
		  return _values.get(retStrIndex);
      }
	  
	//-----------------------------------------------------------------------

}
	
	

