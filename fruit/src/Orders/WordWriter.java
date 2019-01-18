package Orders;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordWriter 
{
	//-----------------------------------------------------------------------------------	
	//FOR WORD OUTPUT
	private static boolean isSecondOrder = false; 
	
	private static final int SPACES_IN_LINE = 125;
	private static final int LINES_IN_PAGE = 42;
	static XWPFDocument document = new XWPFDocument();
		
	static int currentPageLine = 0 ; 
	static XWPFStyles newStyles;
	static XWPFStyle style;

	//-----------------------------------------------------------------------------------	
	
	public static void WriteCustomerOrderToWord(String[] _customerOrderLines, String _dateToPrint, ArrayList<Item> orderItemsArrayList) 
	{							
		checkOverflow(_customerOrderLines.length);   //goto next page or not	
		
		secondOrderToBottom(_customerOrderLines.length);  // second order to the bottom
			
		String[] line1 = _customerOrderLines[0].split("-");
		
		//customer
		XWPFParagraph paragraph1 = document.createParagraph();		
		XWPFRun paragraph1Run1 = paragraph1.createRun();
        paragraph1Run1.setText("לקוח :");
        XWPFRun paragraph1Run2 = paragraph1.createRun();

        String spaces = new String(new char[SPACES_IN_LINE - line1[0].length()]).replace('\0', ' ');
        
        paragraph1Run2.setText(line1[0] + _dateToPrint + spaces);
        
        addLineCount();                //overflow
      
        String customerRemarks = GetCustomerRemarksForWord(line1[0]);
		
        if(line1.length > 1 || !customerRemarks.isEmpty() ){
	        XWPFParagraph paragraph2 = document.createParagraph();
	        XWPFRun paragraph2Run1 = paragraph2.createRun();
	        paragraph2Run1.setText("הערות :");
	        XWPFRun paragraph2Run2 = paragraph2.createRun();
	        if(line1.length > 1){
	        	if(!customerRemarks.isEmpty()){
	        		paragraph2Run2.setText(line1[1] + " , " + customerRemarks);
	        	}else{
	        		paragraph2Run2.setText(line1[1]);
	        	}
        	}else{
        		paragraph2Run2.setText(customerRemarks);
        	}
	        addLineCount();                //overflow
        }
               
        //write items table
        WriteWordCustomerOrderTable(_customerOrderLines, orderItemsArrayList);
             
        gotoHalfPage();
        //drawLineBetweenOrders();                            
	}
	
	//-----------------------------------------------------------------------------------	

	private static void WriteWordCustomerOrderTable(String[] customerOrderLines, ArrayList<Item> orderItemsArrayList) 
	{		
		//write table headers
		 XWPFTable table = document.createTable();
			
		 XWPFTableRow tableRow1 = table.getRow(0);
		
	     tableRow1.getCell(0).setText("הערות                                                                   ");	     
	     tableRow1.addNewTableCell().setText("כמות                  ");
	     tableRow1.addNewTableCell().setText("פריט                                 ");
	     tableRow1.addNewTableCell().setText("מקט        ");
	     tableRow1.addNewTableCell().setText("שורה");
         
	     addLineCount();                //overflow	
	   
	     Collections.sort(orderItemsArrayList);
	      
	     Iterator it = orderItemsArrayList.iterator();
	     int i=0;  
	     while (it.hasNext()) 
	     {    	
	    	 Item curItem = orderItemsArrayList.get(i);
	         	         
	    	 XWPFTableRow tableOneRowTemp = table.createRow();
	    	
		     tableOneRowTemp.getCell(0).setText(curItem.getInputRemark());               //הערות
	    	 tableOneRowTemp.getCell(1).setText(curItem.getInputQty());      		     //כמות
	    	 //tableOneRowTemp.getCell(2).setText(curItem.getName());     				 //שם פריט
	    	 tableOneRowTemp.getCell(2).setText(curItem.getInputName());     				 //שם פריט
	    	 tableOneRowTemp.getCell(3).setText(String.valueOf(curItem.getCat()));          //מקט
	    	 tableOneRowTemp.getCell(4).setText(String.valueOf(i+1));  					 //שורה
	    	 	    	 
	    	 it.next();
	    	 i++;
	    	 addLineCount();                //overflow
	     }
	          
	     table.setStyleID("No Spacing");	
	}
	
	//-----------------------------------------------------------------------------------	
	
	private static String GetCustomerRemarksForWord(String customerName) 
	{	
		String toReturn;
		
		Customer cust = InputsDbHandlers.GetCustomerFromName(customerName);
				
		if (cust == null){
			toReturn = " לקוח לא נמצא בקובץ אקסל ";
			return toReturn;
		}
		
		toReturn = cust.getRemarks();
		String driverNum = cust.getDriverNum();
		if(!driverNum.isEmpty())
		{
			if(toReturn != null && !toReturn.isEmpty())
			{
				toReturn += " - ";				
			}
			toReturn += " שם נהג " + driverNum + " ";						
		}
		
		if(toReturn != null && !toReturn.isEmpty())
		{
			toReturn += " - ";				
		}
		
		toReturn += " מספר לקוח " + cust.getAccount() + " ";	
		
		return toReturn;
	}

	//-----------------------------------------------------------------------------------	
	
	private static void drawLineBetweenOrders() {
		if(!document.createParagraph().isPageBreak()){
			XWPFParagraph paragraph3 = document.createParagraph();
	        XWPFRun paragraph3Run1 = paragraph3.createRun();
	        paragraph3Run1.setText("--------------------------------------------------------------------------------------------------------------------------");    
	        addLineCount();   
		}
	}

	//-----------------------------------------------------------------------------------	

	private static void gotoHalfPage() {
		boolean isNewPage = false;	
		 isSecondOrder = false;
		
		if(currentPageLine > LINES_IN_PAGE / 2){      // if after half -> goto next page
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.addBreak(BreakType.PAGE);
			currentPageLine = 0;
			return;
		}
  
		while(!isNewPage &&  (currentPageLine < LINES_IN_PAGE / 2 ) ){    // add lines until half
			XWPFParagraph paragraphSpace = document.createParagraph();
			XWPFRun paragraphSpaceRun1 = paragraphSpace.createRun();
			paragraphSpaceRun1.setText("");    
	        addLineCount();    
		}
		//seperate between orders
        drawLineBetweenOrders()   ; 
		       
        isSecondOrder = true;
             
	}
			
	//-----------------------------------------------------------------------------------	
	
	private static void secondOrderToBottom(int nextCustomerOrderNumOfLines) 
	{
	    if(isSecondOrder){
			//second order to the bottom 
	        while(currentPageLine + nextCustomerOrderNumOfLines < LINES_IN_PAGE ){
	        	XWPFParagraph paragraph3 = document.createParagraph();
		        XWPFRun paragraph3Run1 = paragraph3.createRun();
		        paragraph3Run1.setText("");    
		        addLineCount();   
	        }
	    }
	}
		
	//-----------------------------------------------------------------------------------	
	
	private static void checkOverflow(int nextCustomerOrderNumOfLines) 
	{
		boolean isNewPage = false;
		
		if(document.createParagraph().isPageBreak()){
			//System.out.println("new page");
			isNewPage = true; 
		}
		
		if ((!isNewPage) && (currentPageLine + nextCustomerOrderNumOfLines + 4 > LINES_IN_PAGE) ){    //וגם אנחנו לא בעמוד חדש כרגע
			
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.addBreak(BreakType.PAGE);
			currentPageLine = 0; 
			isSecondOrder = false;
		}	
		
	}

	//-----------------------------------------------------------------------------------	
	
	private static void addLineCount()
	{
		if(currentPageLine==49){
			currentPageLine = 1;
		}else{
			currentPageLine++;
		}
	}
	
	//-----------------------------------------------------------------------------------
	
	public static void CloseWordFile(String _wordFileToClose) 
	{
		FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(_wordFileToClose);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        try {
            document.write(outStream);
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	//--------------------------------------------------------------------------------

}
