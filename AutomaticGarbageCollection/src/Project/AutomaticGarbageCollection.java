/*
 * AutomaticGarbageCollection.java
 *
 * Created on 17 אוקטובר 2008, 14:07
 */

package Project;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author  Orel_Shai
 */
public class AutomaticGarbageCollection extends JFrame implements ActionListener {


    private JLabel cFilePathText;
    private JLabel cOutputFileText;
    private JButton execute;
    private JLabel messageField;
    private JButton sourceFile;
    private JButton targetFile;
    private JFileChooser _filechooser;
    private JButton _exit;
        
    
    public AutomaticGarbageCollection() {
        initComponents();
    }

    private void initComponents() {

    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	this.setTitle("   C languagge - Automatic Garbage Collection");
    	this.setResizable(true);
    	 
    	 
        messageField = new javax.swing.JLabel();
        cFilePathText = new javax.swing.JLabel();
        sourceFile = new javax.swing.JButton();
        targetFile = new javax.swing.JButton();
        cOutputFileText = new javax.swing.JLabel();
        execute = new javax.swing.JButton();
        _exit= new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageField.setText("Pleae click on the Open source file button and select your c file");

        cFilePathText.setText(null);

        sourceFile.setText("Open source file");

        targetFile.setText("Open target file");

        cOutputFileText.setText(null);

        execute.setText("Execute");
        
        sourceFile.addActionListener(this);
        targetFile.addActionListener(this);
        execute.addActionListener(this);
        
        _exit.setText("Exit");
        _exit.addActionListener(this);
        
      //creates a grid layout
		this.setLayout(new GridLayout());
		java.awt.Container cp=getContentPane();
		
		cp.setLayout(new GridLayout(4,2));
		
		cp.add(messageField);
		cp.add(new JLabel());
		cp.add(sourceFile);
		cp.add(cFilePathText);
		cp.add(targetFile);
		cp.add(cOutputFileText);
		cp.add(execute);
		cp.add(_exit);

		pack();

        
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutomaticGarbageCollection().setVisible(true);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object pressedOn=arg0.getSource();
		
		if(pressedOn.equals(sourceFile))
		{
			_filechooser = new JFileChooser();
	        _filechooser.setCurrentDirectory(new File("."));
	        _filechooser.setFileFilter(new FileFilter() {
	    	    public boolean accept(File f) {
	    	    return f.getName().toLowerCase().endsWith(".c")
	    	         || f.isDirectory();
	    	    }
	   	        public String getDescription() {
	       	          return "c Files";
	   	        }
     	     });
             int r =_filechooser.showOpenDialog(this);       
             if (r == JFileChooser.APPROVE_OPTION    )
             {
            	 File selfile=_filechooser.getSelectedFile();
            	
            	 if(selfile.getName()!=null   &&   selfile.getName().contains(".c"))
            	 {
            		 cFilePathText.setText(selfile.getPath());
            		 if(cOutputFileText.getText()==null)
            		 {
            			 messageField.setText("Pleae click on the Open target file button and select where to output c file will be");
            		 }
            		 else 
            		 {
            			 messageField.setText("Pleae click on the Execute button to convert the file");
            		 }
            	 }
            	 else
            	 {
            		 JFrame frame = new JFrame("Exception");//open a frame that indicates the the point pressed on is not inside the polygon
                     JLabel label = new JLabel("The file you've choosen is not a C file , please click on the open file button again and choose a C file", JLabel.CENTER);
                   
                     frame.add(label);

                     frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                     frame.setSize(650, 300); // width=350, height=200
                     frame.setAlwaysOnTop(true);

                     frame.setVisible(true); // Display the frame
	             }
             }
		}
		
		else if(pressedOn.equals(targetFile))
		{
			_filechooser = new JFileChooser();
	        _filechooser.setCurrentDirectory(new File("."));
	        _filechooser.setFileFilter(new FileFilter() {
	    	    public boolean accept(File f) {
	    	    return f.getName().toLowerCase().endsWith(".c")
	    	         || f.isDirectory();
	    	    }
	   	        public String getDescription() {
	       	          return "c Files";
	   	        }
     	     });
             int r =_filechooser.showOpenDialog(this);       
             if (r == JFileChooser.APPROVE_OPTION    )
             {
            	 File selfile=_filechooser.getSelectedFile();
            	
            	 if(selfile.getName()!=null   &&   selfile.getName().contains(".c"))
            	 {
            		 cOutputFileText.setText(selfile.getPath());
            		 if(cFilePathText.getText()==null)
            		 {
            			 messageField.setText("Pleae click on the Open source file button and select your c file");
            		 }
            		 else 
            		 {
            			 messageField.setText("Pleae click on the Execute button to convert the file");
            		 }
            	 }
            	 else
            	 {
            		 JFrame frame = new JFrame("Exception");//open a frame that indicates the the point pressed on is not inside the polygon
                     JLabel label = new JLabel("The file you've choosen is not a C file , please click on the open file button again and choose a C file", JLabel.CENTER);
                   
                     frame.add(label);

                     frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                     frame.setSize(650, 300); // width=350, height=200
                     frame.setAlwaysOnTop(true);

                     frame.setVisible(true); // Display the frame
	             }
             }
		}

		else if(pressedOn.equals(execute))
		{
			if(cOutputFileText.getText()!=null  &&   cFilePathText.getText()!=null)
			{
				Compailer.getInstance().setinputFileName(cFilePathText.getText());
				Compailer.getInstance().set_outputFileName(cOutputFileText.getText());
				Compailer.getInstance().execute();
			}
			else
			{
				if(cOutputFileText.getText()==null){
					JFrame frame = new JFrame("Exception");//open a frame that indicates the the point pressed on is not inside the polygon
	                JLabel label = new JLabel("You have not choosen your c output file", JLabel.CENTER);
	              
	                frame.add(label);
	
	                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	                frame.setSize(650, 300); // width=350, height=200
	                frame.setAlwaysOnTop(true);
	
	                frame.setVisible(true); // Display the frame
				}
				else
				{
					JFrame frame = new JFrame("Exception");//open a frame that indicates the the point pressed on is not inside the polygon
	                JLabel label = new JLabel("You have not choosen your c input file", JLabel.CENTER);
	              
	                frame.add(label);
	
	                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	                frame.setSize(650, 300); // width=350, height=200
	                frame.setAlwaysOnTop(true);
	
	                frame.setVisible(true); // Display the frame
				}
			}
		}
		

		else if(pressedOn.equals(_exit))
		{
			System.exit(0);
		}
		
		
	}

}
