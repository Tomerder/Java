package Orders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.douglasjose.tech.csv.Csv;
import com.douglasjose.tech.csv.CsvFactory;

public class InputsDbHandlers 
{
	//---------------------------------------------------------------------------------
		
	//DB
	public static HashMap<String, Item> itemsMap1 = new HashMap<String, Item>();
	public static HashMap<String, Item> itemsMap2 = new HashMap<String, Item>();
	public static HashMap<String, Item> itemsMap3 = new HashMap<String, Item>();
	public static HashMap<String, Item> itemsMap4 = new HashMap<String, Item>();
	
	public static HashMap<String, Customer> customersMap = new HashMap<String, Customer>();
	public static HashMap<String, Customer> customersMap2 = new HashMap<String, Customer>();
	
	//---------------------------------------------------------------------------------

	public static void GetItemsFromCsv(String _inputCsvFile) throws IOException , Exception
	{		
		Csv csv = CsvFactory.createOfficeCsv();
		InputStream is = null;
		
		is = new FileInputStream(_inputCsvFile);	
	
		csv.load(is);
		
		//String content;
		//itemsData = new String[csv.getColumns()][csv.getRows()];
		for (int i = 1; i < csv.getRows(); i++) {
		    for (int j = 0; j < csv.getColumns(); j++) {
		    	int cat = Integer.parseInt(csv.get(i, 0));
		    	String name = csv.get(i, 1);
		    	String uom = csv.get(i, 2);
		    	String name2 = "";
		    	String name3 = "";
		    	String name4 = "";
		    	try{
			    	name2 = csv.get(i, 3);
			    	name3 = csv.get(i, 4);
			    	name4 = csv.get(i, 5);
		    	}catch(Exception e){
		    		//do nothing - protection
		    	}
		    	
		    	Item item = new Item(cat, name, uom, name2, name3, name4);
		    	itemsMap1.put(item.getName().trim(),item);
		    	itemsMap2.put(item.getName2().trim(),item);
		    	itemsMap3.put(item.getName3().trim(),item);
		    	itemsMap4.put(item.getName4().trim(),item);
		    }
		   // System.out.println();
		}
		System.out.println();
	}

	//---------------------------------------------------------------------------------
	
	public static void GetCustomersFromCsv(String _inputCsvFile) throws IOException 
	{
		Csv csv = CsvFactory.createOfficeCsv();
		InputStream is = null;
		
		is = new FileInputStream(_inputCsvFile);	
	
		csv.load(is);
				
		for (int i = 1; i < csv.getRows(); i++) 
		{
		    for (int j = 0; j < csv.getColumns(); j++) 
		    {
		    	Customer cust = new Customer(csv.get(i, 0),csv.get(i, 1),csv.get(i, 2),csv.get(i, 3), csv.get(i, 4));
		    	customersMap.put(cust.getName2(),cust);
		    	customersMap2.put(cust.getName(),cust);
		    }		   
		}
		
	}
	
	//---------------------------------------------------------------------------------	

	public static Customer GetCustomerFromName(String _custName)
	{
		Customer cust = customersMap.get(_custName.trim());
		
		if (cust == null){
			cust = customersMap2.get(_custName.trim());
		}
		
		return cust;
	}
	
	//---------------------------------------------------------------------------------	

	public static Item GetItemFromName(String _itemInputName)
	{
		String trimedItemName = _itemInputName.trim();
		
		Item itemMap1 = InputsDbHandlers.itemsMap1.get(trimedItemName);
		Item itemMap2 = InputsDbHandlers.itemsMap2.get(trimedItemName);
		Item itemMap3 = InputsDbHandlers.itemsMap3.get(trimedItemName);
		Item itemMap4 = InputsDbHandlers.itemsMap4.get(trimedItemName);
		
		Item itemToret = null;
		
		if( itemMap1!= null)
		{
			itemToret = new Item(itemMap1.getCat() , itemMap1.getName() , itemMap1.getUom() , itemMap1.getName2(), _itemInputName);
		}
		else if(itemMap2 != null)
		{ 
			itemToret = new Item(itemMap2.getCat() , itemMap2.getName() , itemMap2.getUom() , itemMap2.getName2(), _itemInputName );
		}
		else if(itemMap3 != null)
		{ 
			itemToret = new Item(itemMap3.getCat() , itemMap3.getName() , itemMap3.getUom() , itemMap3.getName2(), _itemInputName );
		}
		else if(itemMap4 != null)
		{ 
			itemToret = new Item(itemMap4.getCat() , itemMap4.getName() , itemMap4.getUom() , itemMap4.getName2(), _itemInputName );
		}

		return itemToret;
	}

	//-----------------------------------------------------------------------------------	
		
}
