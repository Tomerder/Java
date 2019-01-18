package Orders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.douglasjose.tech.csv.Csv;
import com.douglasjose.tech.csv.CsvFactory;

public class CsvWriter 
{
	static private Csv outputCsv;
	
	static final int NUM_OF_CSV_COLOMNS = 11;
	
	//-----------------------------------------------------------------------------------	
	
	public static void CreateCsv()
	{
		outputCsv = CsvFactory.createOfficeCsv();
		//write CSV header
		WriteCsvHeader(outputCsv);	
	}
	//-----------------------------------------------------------------------------------	
	
	private static void WriteCsvHeader(Csv outputCsv) 
	{
		String[] headerFields =  {  
									"מס' הזמנה",
									"תאריך (הזמנה ואספקה)",
									"קוד לקוח",
									"שם לקוח",
									"קוד פריט",
									"תיאור פריט",
									"כמות",
									"אריזה (קג, יחידה, ארגז)",
									"מחיר מיוחד",
									"הערה",
									"הערת לקוח"
								};
		
		CsvWriter.WriteCsvLine(outputCsv, headerFields);	
	}
	
	//-----------------------------------------------------------------------------------	

	public static void WriteCustomerOrderToCsv(String[] _customerOrderLines, int _orderNumber, ArrayList<Item> orderItemsArrayList)
	{			
		String[] fields = new String[NUM_OF_CSV_COLOMNS];
		
		//***Field0 : orderNumber 
		fields[0] = Integer.toString(_orderNumber);
		
		//***Field1 :date
		String dateStr = GUI.getDateSelected();
		String dateStrFormated = Utils.ConvertDateFormat(dateStr, "dd/MM/yyyy", "yyyy-MM-dd");  
		fields[1] = dateStrFormated;
		
		//customer
		String customerName = InputFileParser.GetCustNameFromCustOrder(_customerOrderLines); 
		Customer cust = InputsDbHandlers.GetCustomerFromName(customerName);
		
		//***Field2 :customer code
		String custCode = "99999";
		String custRemarks = "";
		
		if(cust != null)
		{
			custCode = cust.getAccount();
			//custRemarks = cust.getRemarks();
			custRemarks = InputFileParser.GetCustRemarkFromCustOrder(_customerOrderLines);
		}

		fields[2] = custCode;
		
		//***Field3 :customer name
		fields[3] = customerName;
		
		//***Field10 : Customer remark
		fields[10] = custRemarks;
		
			
		for (Item item : orderItemsArrayList) 
		{
			//***Field4 : Item code
			fields[4] = Integer.toString(item.getCat());
			
			//***Field5 : Item name
			fields[5] = item.getInputName();
			
			//***Field6 : Item qty
			//fields[6] = item.getInputQty();
			fields[6] = Float.toString(item.getInputQtyNumber());
			
			//***Field7 : Item uom
			fields[7] = item.getInputUom();
						
			//***Field8 : Item special price
			float specialPrice = item.getInputSpecialPrice();
			if(specialPrice != 0)
			{
				fields[8] = Float.toString(specialPrice);
			}
			else
			{
				fields[8] = "";
			}
			
			//***Field9 : Item remark
			fields[9] = item.getInputRemark();
						
			//Write line to CSV
			CsvWriter.WriteCsvLine(outputCsv, fields);
		}	
	}
		
	//-----------------------------------------------------------------------------------	
	
	static int csvLine = 0; 
	
	public static void WriteCsvLine(Csv _outputCsv, String[] _fields) 
	{
		int fieldNum = 0;
		
		for(String field : _fields)
		{
			_outputCsv.add(csvLine, fieldNum, field);
			fieldNum++;
		}     
		
		csvLine++;
	}

	//-----------------------------------------------------------------------------------	

	public static void CloseOutputCsvFile(String _outputCsvFile)
	{			
		//save to file
		FileOutputStream outStream = null;
	    try {
	    	outStream = new FileOutputStream(_outputCsvFile);
	    	outputCsv.store(outStream);
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }  
	}
	
	//-----------------------------------------------------------------------------------	

}
