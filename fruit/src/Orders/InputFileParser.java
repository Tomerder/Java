package Orders;

import java.util.ArrayList;
import java.util.Arrays;

public class InputFileParser 
{
	//-----------------------------------------------------------------------------------	

	static final int CUST_NAME_INDEX = 0;
	static final int CUST_REMARK_INDEX = 1;
	
	static final int ITEM_QTY_AND_UOM_INDEX = 0;
	static final int ITEM_SPECIAL_PRICE_INDEX = 1;
	static final int ITEM_REMARK_INDEX = 2;
	static final ArrayList<String> UOM_LEGAL_VALUES_ARR = new ArrayList<>(Arrays.asList(
								"ארגז",
								"קג"	,				
								"יח",
								"נספק",
								"שק",
								"שקית",
								"שרוול"));
							
	//-----------------------------------------------------------------------------------	
	
	public static String GetCustNameFromCustOrder(String[] customerOrderLines)
	{
		String[] line1 = customerOrderLines[0].split("-");
		
		String custName = line1[CUST_NAME_INDEX];
		
		return custName;		
	}
	
	//-----------------------------------------------------------------------------------	
	
	public static void ReplaceCustNameInOrder(String[] customerOrderLines, String _newCustName)
	{
		String[] line1 = customerOrderLines[0].split("-");
		
		line1[CUST_NAME_INDEX] = _newCustName;
			
		customerOrderLines[0] = line1[CUST_NAME_INDEX];
		try
		{
			customerOrderLines[0] += " - " + line1[CUST_REMARK_INDEX];
		}
		catch(Exception e)
		{
			
		}
	}
	
	//-----------------------------------------------------------------------------------	
	
	public static String GetCustRemarkFromCustOrder(String[] customerOrderLines)
	{
		String[] line1 = customerOrderLines[0].split("-");
		
		String custRemark = "";
		
		try
		{
			custRemark = line1[CUST_REMARK_INDEX];
		}
		catch(Exception e)
		{
			
		}
		
		return custRemark;		
	}
	
	//-----------------------------------------------------------------------------------	
	
	public static ArrayList<Item> GetCustomerOrderItems(String[] customerOrderLines) 
	{
		ArrayList<Item> orderItemsArrayList = new ArrayList<Item>();
			
		for (int i =1; i<customerOrderLines.length;i++)
		{
			String[] splitItemRemark = customerOrderLines[i].split("-");
			String inputRemark = "";
			//get item remark
			try
			{
				inputRemark = splitItemRemark[ITEM_REMARK_INDEX];                            		   //table column 5 
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			//get special price
			float specialPrice = 0;
			try
			{
				String specialPriceStr = splitItemRemark[ITEM_SPECIAL_PRICE_INDEX];
				specialPrice = Utils.GetDigitsFloatFromStr(specialPriceStr);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
					
			String inputItemName;
			String inputItemQty;
			String uom = "";
			float qty = 0; 
			
			String itemQtyAndUom = splitItemRemark[ITEM_QTY_AND_UOM_INDEX];
			
			int indexOfNumber = Utils.firstDigitAtString(itemQtyAndUom);
			if (indexOfNumber==-1)
			{
				inputItemName= itemQtyAndUom;
				inputItemQty = "";
			}
			else
			{
				inputItemName = itemQtyAndUom.substring(0,indexOfNumber);       //for chain to csv
				inputItemQty = itemQtyAndUom.substring(indexOfNumber, itemQtyAndUom.length());  //table column 4 
				inputItemQty = Utils.setSpaceBetweenQtyAndUom(inputItemQty);												
				qty = GetQtyFromInputItemQty(inputItemQty);
				uom = GetUomFromInputItemQty(inputItemQty);				
				uom = uom.trim();
				
				//check uom is leagal
				if(!IsUomLeagal(uom))
				{
					String title = "יחידת מידה לא תקינה";
					String message = inputItemName + " : " + uom + ", " + title + "בחר בבקשה יחידת מידה ";
					uom = GUI.DisplaySelectionDialog(title, message, UOM_LEGAL_VALUES_ARR);
				}
				
			}
			
			//----- item -------
			Item item = null;
			
			//until get legal item name or cancel form user (item 999999)
			String correctedItemName = inputItemName;
			while(true)
			{
				item = InputsDbHandlers.GetItemFromName(correctedItemName);
				
				//if item does not exist , ask user for correction
				if(item == null)
				{
					//get correct item name from user
					String title = "פריט לא קיים";
					String message = inputItemName + " : " + title + " - " + "הכנס שם פריט מתוקן" + " : ";
					correctedItemName = GUI.DisplayInputDialog(title, message);
									
					if(correctedItemName == null || correctedItemName.isEmpty())
					{
						//add dummy item cat 9999999
						item = new Item(999999 , inputItemName , "" , inputItemName , inputItemName);
						break;
					}
				}
				else
				{
					//item is legal
					break;
				}
			}
						
			//update :inputItemQty , inputRemark , uom 	
			item.setInputQty(inputItemQty);
			item.setInputRemark(inputRemark);
			item.setInputUom(uom);
				
			item.setInputQtyNumber(qty);
			item.setInputSpecialPrice(specialPrice);
			
			//add to order list of items
			orderItemsArrayList.add(item);			
		}
		
		return orderItemsArrayList;
	}
	
	//-----------------------------------------------------------------------------------		
	
	private static boolean IsUomLeagal(String uom) 
	{
		if(UOM_LEGAL_VALUES_ARR.contains(uom) )
		{
			return true;	
		}
			
		return false;
	}

	//-----------------------------------------------------------------------------------		
	
	private static float GetQtyFromInputItemQty(String inputItemQty) 
	{
		float qty = Utils.GetDigitsFloatFromStr(inputItemQty);
		
		return qty;
	}

	//-----------------------------------------------------------------------------------	

	
	private static String GetUomFromInputItemQty(String inputItemQty) 
	{
		String uom = Utils.GetLettersWithoutDotFromStr(inputItemQty);
						
		return uom;
	}
		
	//-----------------------------------------------------------------------------------	

}
