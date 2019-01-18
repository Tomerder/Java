package Orders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils 
{
	//-----------------------------------------------------------------------------------	
	
	public static void CreateFolderIfNotExistOnPath(String _CreateAtPath, String _FolderToCreate) 
	{
		try
		{				
			final File dir = new File(_CreateAtPath, _FolderToCreate);
		    if (!dir.exists()) 
		    {
		    	dir.mkdirs();
		    }
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//-----------------------------------------------------------------------------------	
	
	public static String ConvertDateFormat(String _oldDateStr, String _oldDateFormat, String _newDateFormat) 
	{
		String oldDateString = _oldDateStr;
		String newDateString = "";
		try 
		{
			
			SimpleDateFormat sdf = new SimpleDateFormat(_oldDateFormat);
			Date d = sdf.parse(oldDateString);
			sdf.applyPattern(_newDateFormat);
			newDateString = sdf.format(d);
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return newDateString;
	}
	
	//--------------------------------------------------------------------------------
	
	public static void ConvertCsvToTab(String _csvFileToConvert, String _outputTabFile) 
	{
        File file = new File(_csvFileToConvert); 
        List<String> processedLines = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String line; 
            StringBuilder builder; 
            while((line=br.readLine()) != null) {
                builder = new StringBuilder(line); 

                //find number in double quote - assuming there is only one number with double quotes
                int doubleQuoteIndexStart = builder.indexOf("\""); 
                int doubleQuoteIndexLast = builder.lastIndexOf("\""); 

                //for each line, find all indexes of comma
                int index = builder.indexOf(",");

                //previous used to when there is consecutive comma
                int prevIndex = 0; 

                while (index >= 0) {
                    if(index < doubleQuoteIndexStart || index > doubleQuoteIndexLast) {
                        builder.setCharAt(index, '\t'); 
                    }

                    //get next index of comma
                    index = builder.indexOf(",", index + 1);

                    //check for consecutive commas
                    if(index != -1 && (prevIndex +1) == index) {
                        builder.setCharAt(index, ' ');
                        //get next index of comma
                        index = builder.indexOf(",", index + 1);
                    }
                }

                //add the line to list of lines for later storage to file
                processedLines.add(builder.toString());
            }

            //close the output stream
            br.close(); 

            //write all the lines to the file
            File outFile = new File(_outputTabFile);
            PrintWriter writer = new PrintWriter(outFile); 
            for(int i = 0; i < processedLines.size(); i++) {
                writer.println(processedLines.get(i));
            }

            writer.close(); 
        } catch(Exception ex) {
            //handle exception
        }       
	}
	
	//--------------------------------------------------------------------------------

	public static int firstDigitAtString(String str)
	{
		 for(int i=0;i<str.length();i++){
			 char c = str.charAt(i);
			 if(c>='0' && c<='9'){
				 return i;
			 }
		 }
		 return -1;
	}
	
    //--------------------------------------------------------------------------------

	public static Boolean isDigit(Character c)
	{
		 if(c>='0' && c<='9'){
			 return true;
		 }
		 
		 return false;
	}
	
    //--------------------------------------------------------------------------------

	public static Integer GetDigitsFromStr(String _str)
	{
		Integer toRet = 0;
		String toRetStr = "";
		
		for(int i=0;i<_str.length();i++){
			 char c = _str.charAt(i);
			 if(c>='0' && c<='9'){
				 toRetStr += c;
			 }
		 }
		
		try{
			toRet = Integer.parseInt(toRetStr);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			
		return toRet;
	}
	
    //--------------------------------------------------------------------------------

	public static float GetDigitsFloatFromStr(String _str)
	{
		float toRet = 0;
		String toRetStr = "";
		
		for(int i=0;i<_str.length();i++){
			 char c = _str.charAt(i);
			 if( (c>='0' && c<='9') || (c == '.')  ){
				 toRetStr += c;
			 }
		 }
		
		try{
			toRet = Float.parseFloat(toRetStr);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			
		return toRet;
	}
	
    //--------------------------------------------------------------------------------
	
	public static String GetLettersWithoutDotFromStr(String _str)
	{
		String toRetStr = "";
		
		for(int i=0;i<_str.length();i++){
			 char c = _str.charAt(i);
			 if(!(c>='0' && c<='9') && c != '.')// && c != ' ' )
			 {
				 toRetStr += c;
			 }
		 }
		
		return toRetStr;
	}
	
    //--------------------------------------------------------------------------------
	
	public static String setSpaceBetweenQtyAndUom(String str)
	{
		Boolean isNumber=false;
		String toReturn = " " + str;
		
		for(int i=0;i<str.length();i++){
			 char c = str.charAt(i);
			 if(!isNumber){
				 if(c>='0' && c<='9'){
					 isNumber = true;
				 }
			 }else{  // התו הקודם היה מספר
				 if(!(c>='0' && c<='9')){
					 if(c != ' '){    //if no space between qty and uom
						 toReturn = toReturn.substring(0,i) + " " + toReturn.substring(i,toReturn.length());  //add space
						 break;
					 }else{
						 break;
					 }
				 }
			 }
		 }
		 return toReturn;
	}
	
	//--------------------------------------------------------------------------------
		
}
