package Orders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.xmlbeans.XmlException;

public class Main 
{
	private static String dateToPrint;
	
	//PATHS AND FILES
	private static String path;
	
	private static String INPUT_CSV_CUSTOMERS;
	private static String INPUT_CSV_ITEMS; 
	
	private static String INPUT_FILE;
	private static String OUTPUT_WORD_FILE;
	private static String OUTPUT_CSV_FILE;
	private static String OUTPUT_TXT_FILE;	
	private static final String OUTPUT_FOLDER = "Output\\";

	/*-----------------------------------------------------------------------------------*/
	
	public static void main(String[] args) throws IOException, ParseException, XmlException , RuntimeException
	{
		try
		{	
			SetFilesLocations();
			
			InputsDbHandlers.GetItemsFromCsv(INPUT_CSV_ITEMS);
			InputsDbHandlers.GetCustomersFromCsv(INPUT_CSV_CUSTOMERS);
			
			CsvWriter.CreateCsv();
			
			ReadInputFile();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	//-----------------------------------------------------------------------------------	
	
	private static void SetFilesLocations()
	{
		path = GUI.pathSelected;
		dateToPrint = GUI.dateSelected;
		
		//inputs
		INPUT_CSV_CUSTOMERS = path + "customers.csv";
		INPUT_CSV_ITEMS = path + "items.csv";
		
		INPUT_FILE = path + "input.txt";
		
		//outputs
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		String dateTimeForFile = dateFormat.format(date);
		
		OUTPUT_WORD_FILE = path + OUTPUT_FOLDER + "Word_" + dateTimeForFile + ".docx";
		
		OUTPUT_CSV_FILE = path + OUTPUT_FOLDER + "Csv_" + dateTimeForFile + ".csv";	
		OUTPUT_TXT_FILE = path + OUTPUT_FOLDER + "Tab_" + dateTimeForFile + ".txt";	
		
		//create output folder
		String curPath = GUI.GetPathSelected();		
		Utils.CreateFolderIfNotExistOnPath(curPath, OUTPUT_FOLDER);
	}

	//-----------------------------------------------------------------------------------	
	
	private static void ReadInputFile() throws IOException, XmlException 
	{		
		BufferedReader br = null;	
		br = new BufferedReader(new FileReader(INPUT_FILE));
		
		String customerOrder = null;
	    try 
	    {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        Boolean isEmpty = true;
	        
	        int orderNumber = 1;
	        
	        //go through the input file (all customer orders)
	        while (line != null) 
	        {	     
	        	//get to next customer order
	        	while (line != null && !line.isEmpty()) 
	        	{
	        		isEmpty = false;
		        	sb.append(line);
		            sb.append('\n');
		            line = br.readLine();
	        	}
	        	if(!isEmpty)
	        	{
		        	customerOrder = sb.toString();
		        	String[] customerOrderLines = customerOrder.split("\n");
		        	
		        	//Check if customer legal 
		        	StringBuffer custNameRef = new StringBuffer();
		        	Boolean isCustLegal = CheckIfCustomerLegal(customerOrderLines, custNameRef);
		        	Boolean isSkipCustOrder = false;
		        	if(!isCustLegal)
		        	{
		        		StringBuffer newCustName = new StringBuffer();
		        		isSkipCustOrder = DisplayCustNotLegalWindow(custNameRef.toString(), newCustName);
		        	
		        		//skip order if user choose to 
			        	if(isSkipCustOrder)
			        	{
			        		//to the next customer order
				        	isEmpty = true;
				        	sb = new StringBuilder();
				        	line = br.readLine();  
				        	
			        		//skip order
			        		continue;
			        	}
			        	else
			        	{
			        		//replace customer
			        		InputFileParser.ReplaceCustNameInOrder(customerOrderLines, newCustName.toString()); 
			        	}
		        	}
		        			
		        	//order items
		    		ArrayList<Item> orderItemsArrayList = InputFileParser.GetCustomerOrderItems(customerOrderLines);
		        	
		        	//output to WORD
		        	WordWriter.WriteCustomerOrderToWord(customerOrderLines, dateToPrint, orderItemsArrayList);
		        	
		        	//output to CSV
		        	CsvWriter.WriteCustomerOrderToCsv(customerOrderLines, orderNumber, orderItemsArrayList);
		        	orderNumber++;
	        	}
	        	
	        	//to the next customer order
	        	isEmpty = true;
	        	sb = new StringBuilder();
	        	line = br.readLine();  
	        }       
	    } 
	    finally 
	    {
	        br.close();
	    }
		    
	    WordWriter.CloseWordFile(OUTPUT_WORD_FILE);
	    
	    CsvWriter.CloseOutputCsvFile(OUTPUT_CSV_FILE);
	    Utils.ConvertCsvToTab(OUTPUT_CSV_FILE, OUTPUT_TXT_FILE);		
	}

	//--------------------------------------------------------------------------------
	
    private static Boolean DisplayCustNotLegalWindow(String _custName, StringBuffer _custNameToReplace) 
    {    	
    	String enterCustTitle = "לקוח לא קיים";
    	String enterCustMsg = _custName + " - " + "לקוח לא קיים, הכנס שם לקוח :";
    	
		String custNameToReplace = GUI.DisplayInputDialog(enterCustTitle, enterCustMsg);
    	
		Boolean isSkipOrder = false;
		
		//display skip dialog if user canceled or entered illegal cust name
		Boolean isDisplaySkipDialog = (custNameToReplace == null) || (custNameToReplace.isEmpty()) || (InputsDbHandlers.GetCustomerFromName(custNameToReplace) == null) ;
		if(isDisplaySkipDialog)
		{
			//if cust name still not legal : original input cust name will be returned
			_custNameToReplace.append(_custName);
			
			String skipTitle = "לקוח לא קיים";
			String skipMsg = _custName + " - " + " האם תרצה לדלג על הזמנת לקוח ?";
			isSkipOrder = GUI.DisplayConfirmDialog(skipTitle ,skipMsg);
		}	
		else
		{
			//if name is legal : corrected cust name will be returned 
			_custNameToReplace.append(custNameToReplace);
		}
		
		return isSkipOrder;
	}

	//--------------------------------------------------------------------------------
	
	private static Boolean CheckIfCustomerLegal(String[] _customerOrderLines, StringBuffer _custNameRef) 
	{
    	String _custName = InputFileParser.GetCustNameFromCustOrder(_customerOrderLines);
    	Customer cust = InputsDbHandlers.GetCustomerFromName(_custName);
    	
    	//set the return value for _custNameRef
    	_custNameRef.append(_custName);
    	
    	if(cust == null)
    	{
    		return false;
    	}
    	
    	return true;
	}
	
    //--------------------------------------------------------------------------------
	
}





