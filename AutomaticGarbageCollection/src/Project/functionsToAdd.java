package Project;

import java.util.Vector;

import Project.Compailer;

public class functionsToAdd {

	Vector<String> _ourNewOgen;
	Vector<String> _ourNewP;
	Vector<String> _changeOgen;
	Vector<String> _decreaseAndCheck;
	String _ourNewOgenFunction;
	String _ourNewPFunction;
	String _changeOgenFunction;
	String _decreaseAndCheckFunction;
	
	
	String  declerationsString;
	Vector<String> declerations;
	
	
	
	public functionsToAdd(Vector<String> structsNames)
	{
		_ourNewOgenFunction=new String("");//איתחול
		_ourNewPFunction=new String("");//איתחול
		_changeOgenFunction=new String("");//איתחול
		_decreaseAndCheckFunction=new String("");//איתחול
		
		
		declerationsString=new String("");//איתחול
		
		declerations=new Vector<String>();//איתחול
		
		
		/**
		 * יצירת 4 וקטורים , וקטור לכל פונקציה והכנסת שורות הוקטור לפונקציה המתאימה
		 * בכל מקום שיש צורך להכניס את שם הסטרקט המתאים תופיע המילה
		 * InsertStructName
		 */
		_ourNewOgen=new Vector<String>();
		_ourNewOgen.add("void ourNewOgenInsertStructName( InsertStructName**  pointer  ) {"+"\r\n");
		declerations.add("void ourNewOgenInsertStructName( InsertStructName**  pointer  ) ;"+"\r\n");
		_ourNewOgen.add("	if ( *pointer== NULL) {"+"\r\n");
		_ourNewOgen.add("		(*pointer) = ( InsertStructName*) malloc( sizeof( InsertStructName ));"+"\r\n");
		_ourNewOgen.add("		(*pointer)->counter1 = 1;"+"\r\n");
		_ourNewOgen.add("		(*pointer)->counter2 = 0;"+"\r\n");
		_ourNewOgen.add("###");	 // אתחול כל הפוינטרים המוכלים ל נאל
		_ourNewOgen.add("		}"+"\r\n");	
		_ourNewOgen.add("	else {"+"\r\n");
		_ourNewOgen.add("		(*pointer)->counter1-- ;"+"\r\n");											
		_ourNewOgen.add("		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {"+"\r\n");
/**/	_ourNewOgen.add("???");   
		_ourNewOgen.add("			free((*pointer) );"+"\r\n");
		_ourNewOgen.add("			(*pointer)=NULL;"+"\r\n");
		_ourNewOgen.add("		}"+"\r\n");
		_ourNewOgen.add("		(*pointer) = ( InsertStructName*) malloc( sizeof( InsertStructName ));"+"\r\n");
		_ourNewOgen.add("		(*pointer)->counter1 = 1;"+"\r\n");
		_ourNewOgen.add("		(*pointer)->counter2 = 0;"+"\r\n");
		_ourNewOgen.add("	}"+"\r\n");
		_ourNewOgen.add("}"+"\r\n");
		
		
		_ourNewP=new Vector<String>();
		_ourNewP.add("void ourNewPInsertStructName( InsertStructName**  pointer  ) {"+"\r\n");
		declerations.add("void ourNewPInsertStructName( InsertStructName**  pointer  ) ;"+"\r\n");
		_ourNewP.add("	if ( (*pointer)== NULL) {"+"\r\n");	
		_ourNewP.add("		(*pointer) = ( InsertStructName*) malloc( sizeof( InsertStructName ));"+"\r\n");		
		_ourNewP.add("		(*pointer)->counter1 = 0;"+"\r\n");
		_ourNewP.add("		(*pointer)->counter2 = 1;"+"\r\n");
		_ourNewP.add("###");	 // אתחול כל הפוינטרים המוכלים ל נאל
		_ourNewP.add("		}"+"\r\n");			
		_ourNewP.add("	else {"+"\r\n");
		_ourNewP.add("		(*pointer)->counter2-- ;"+"\r\n");											
		_ourNewP.add("		if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {"+"\r\n");	
/**/    _ourNewP.add("???");     
		_ourNewP.add("			free((*pointer) );"+"\r\n");
		_ourNewP.add("			(*pointer)=NULL;"+"\r\n");
		_ourNewP.add("		}"+"\r\n");
		_ourNewP.add("		(*pointer) = ( InsertStructName*) malloc( sizeof( InsertStructName ));"+"\r\n");
		_ourNewP.add("		(*pointer)->counter1 = 0;"+"\r\n");
		_ourNewP.add("		(*pointer)->counter2 = 1;"+"\r\n");
		_ourNewP.add("	}"+"\r\n");
		_ourNewP.add("}"+"\r\n");
		
		
		_changeOgen=new Vector<String>();
		_changeOgen.add("void changeOgenInsertStructName( InsertStructName**  ogenPointer  ,  InsertStructName**  newPlaceToPoint  )  { "+"\r\n");	
		declerations.add("void changeOgenInsertStructName( InsertStructName**  ogenPointer  ,  InsertStructName**  newPlaceToPoint  )  ; "+"\r\n");
		_changeOgen.add("	if ( (*ogenPointer)==NULL ) {"+"\r\n");	
		_changeOgen.add("		(*ogenPointer)= *newPlaceToPoint;"+"\r\n");	
		_changeOgen.add("		(*ogenPointer)->counter1++;"+"\r\n");
//								_changeOgen.add("###");	 // אתחול כל הפוינטרים המוכלים ל נאל
		_changeOgen.add("		}"+"\r\n");			
		_changeOgen.add("	else {"+"\r\n");
		_changeOgen.add("		(*ogenPointer)->counter1-- ;"+"\r\n");											
		_changeOgen.add("		if( ((*ogenPointer)->counter1==0) && ((*ogenPointer)->counter2==0) )  {"+"\r\n");	
/**/	_changeOgen.add("???");       
		_changeOgen.add("			free( (*ogenPointer) );"+"\r\n");
		_changeOgen.add("			(*ogenPointer)=NULL;"+"\r\n");
		_changeOgen.add("		}"+"\r\n");
		_changeOgen.add("		(*ogenPointer)= *newPlaceToPoint;"+"\r\n");
		_changeOgen.add("		(*ogenPointer)->counter1++;"+"\r\n");
		_changeOgen.add("	}"+"\r\n");
		_changeOgen.add("}"+"\r\n");
		
		
		
		
		
		_decreaseAndCheck=new Vector<String>();
		_decreaseAndCheck.add("void decreaseAndCheckInsertStructName(InsertStructName**  pointer ) {"+"\r\n");	
		declerations.add("void decreaseAndCheckInsertStructName(InsertStructName**  pointer ) ;"+"\r\n");	
		_decreaseAndCheck.add("	(*pointer)->counter2--;"+"\r\n");		
		_decreaseAndCheck.add("	if( ((*pointer)->counter1==0) && ((*pointer)->counter2==0) )  {"+"\r\n");	
/**/	_decreaseAndCheck.add("???");  
		_decreaseAndCheck.add("		free( (*pointer) );"+"\r\n");
		_decreaseAndCheck.add("		(*pointer)=NULL;"+"\r\n");
		_decreaseAndCheck.add("	}"+"\r\n");			
		_decreaseAndCheck.add("}"+"\r\n");
		
		
		
		createDeclerations(structsNames);
		
		
		createourNewOgenFunction(structsNames);
		createNewPFunction(structsNames);
		createChangeOgenFunction(structsNames);
		createDecreaseAndCheckFunction(structsNames);
		
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	private void createDeclerations(Vector<String> structsNames){
		
		//עובר על כל השמות של הסטרקטים שיש
		for(int i=0; i<structsNames.size() ; i++)
		{
			//עובר שורה שורה על כל השורות של הפונקציה
			for(int j=0 ; j<declerations.size()  ; j++)
			{
				String currentLine=new String();
				currentLine=declerations.elementAt(j);//השורה הנוכחית שבודקים
				int index=0;//מוצא את האינדקס שבו מתחילה המילה שצריך להחליף
				
				if(currentLine.indexOf("InsertStructName")!=-1)
				{
					String word=new String("InsertStructName");
					String start=new String();
					String end=new String();
					String newline=new String(currentLine);
					while((index=newline.indexOf("InsertStructName"))!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
					{							
						start=newline.substring(0,index);
						end=newline.substring(index+word.length());
						//currentLine.replaceAll("InsertStructName", structsNames.elementAt(i));
						newline=start + structsNames.elementAt(i) + end;
						
					}
					declerationsString=declerationsString+newline;
				}
			}
		}
		
	
		declerationsString=declerationsString+"\n\n\n";
		
		
		
		
		
	}

	


	/**
	 * יוצר סטרינג של הפונקציה עם כל הפרמוטציות של הסטרקטים 
	 * @param structsNames
	 */
	private void createourNewOgenFunction(Vector<String> structsNames){
		//עובר על כל השמות של הסטרקטים שיש
		for(int i=0; i<structsNames.size() ; i++)
		{
			//עובר שורה שורה על כל השורות של הפונקציה
			for(int j=0 ; j<_ourNewOgen.size()  ; j++)
			{
				String currentLine=new String();
				currentLine=_ourNewOgen.elementAt(j);//השורה הנוכחית שבודקים
				int index=0;//מוצא את האינדקס שבו מתחילה המילה שצריך להחליף
				
				if(currentLine.indexOf("InsertStructName")!=-1)
				{
					String word=new String("InsertStructName");
					String start=new String();
					String end=new String();
					String newline=new String(currentLine);
					while((index=newline.indexOf("InsertStructName"))!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
					{							
						start=newline.substring(0,index);
						end=newline.substring(index+word.length());
						//currentLine.replaceAll("InsertStructName", structsNames.elementAt(i));
						newline=start + structsNames.elementAt(i) + end;
						
					}
					_ourNewOgenFunction=_ourNewOgenFunction+newline;
				}
			
				else if ( (currentLine.indexOf("###")!=-1)    &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)) 
				{
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
				//		String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
	/**/				_ourNewOgenFunction=_ourNewOgenFunction+"			(*pointer)->"  +temp+  "=NULL;"  +"\r\n";					
					}
					
					
				}
				
				else if ( (currentLine.indexOf("###")!=-1)    &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)) 
				{
				}
			
				else if ( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)  
						&&    (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size()==1  )
					    &&  (  (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("int")
					    		||   (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("char")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("real")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("float")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("double")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("short")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("long")
					    		)  )
				{
					
			//  ריק 
					
					
				}
				
			
				
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
				{
					//והאם לסטרקט שאנו מטפלים בו ישנם משתנים פנימיים.בסטרקט צ'אר שבדוגמא שלנו אין משתנים פנימיים ולכן אסור להיכנס לפה
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
						String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
	/**/				_ourNewOgenFunction=_ourNewOgenFunction+("			decreaseAndCheck") +   pointerType  + "(&((*pointer)->"  +temp+  "));"  +"\r\n";					
					}
					
				}
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
				// במקרה שהסטרקט שבו אנו מטפלים אינו מכיל פוינטרים 
				{
				//  לא נרשום כלום במקום הסימני שאלה
				}
				
				else 
				{
					_ourNewOgenFunction=_ourNewOgenFunction+currentLine;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * יוצר סטרינג של הפונקציה עם כל הפרמוטציות של הסטרקטים 
	 * @param structsNames
	 */
	private void createNewPFunction(Vector<String> structsNames){
		//עובר על כל השמות של הסטרקטים שיש
		for(int i=0; i<structsNames.size() ; i++)
		{
			//עובר שורה שורה על כל השורות של הפונקציה
			for(int j=0 ; j<_ourNewP.size()  ; j++)
			{
				String currentLine=new String();
				currentLine=_ourNewP.elementAt(j);//השורה הנוכחית שבודקים
				int index=0;//מוצא את האינדקס שבו מתחילה המילה שצריך להחליף
				
				if( currentLine.indexOf("InsertStructName")!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
				{
					String word=new String("InsertStructName");
					String start=new String();
					String end=new String();
					String newline=new String(currentLine);
					
					while((index=newline.indexOf("InsertStructName"))!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
					{							
						start=newline.substring(0,index);
						end=newline.substring(index+word.length());
						//currentLine.replaceAll("InsertStructName", structsNames.elementAt(i));
						newline=start + structsNames.elementAt(i) + end;
						
					}
					_ourNewPFunction=_ourNewPFunction+newline;
				}
				
				else if ( (currentLine.indexOf("###")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)) 
				{
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
				//		String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
	/**/				_ourNewPFunction=_ourNewPFunction+"			(*pointer)->"  +temp+  "=NULL;"  +"\r\n";					
					}
					
					
				}
				
				else if ( (currentLine.indexOf("###")!=-1)    &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)) 
				{
				}
				
				else if ( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)  
						&&    (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size()==1  )
					    &&  (  (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("int")
					    		||   (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("char")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("real")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("float")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("double")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("short")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("long")
					    		)  )
				{
					
			//  ריק 
					
					
				}
				
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
				{
					//והאם לסטרקט שאנו מטפלים בו ישנם משתנים פנימיים.בסטרקט צ'אר שבדוגמא שלנו אין משתנים פנימיים ולכן אסור להיכנס לפה
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
						String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
						//_ourNewPFunction=_ourNewPFunction+("			decreaseAndCheck(pointer->"+temp+");"+"\r\n");
						_ourNewPFunction=_ourNewPFunction+("			decreaseAndCheck") +  pointerType + "(&((*pointer)->"  +temp+  "));"  +"\r\n";					
					}
					
				}
						
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
					// במקרה שהסטרקט שבו אנו מטפלים אינו מכיל פוינטרים 
					{
					//  לא נרשום כלום במקום הסימני שאלה
					}
					
				
				else
				{
					_ourNewPFunction=_ourNewPFunction+currentLine;
				}
			}
		}
	}
	
	
	
	
	
	
	
	

	/**
	 * יוצר סטרינג של הפונקציה עם כל הפרמוטציות של הסטרקטים 
	 * @param structsNames
	 */
	private void createChangeOgenFunction(Vector<String> structsNames) {
		//עובר על כל השמות של הסטרקטים שיש
		for(int i=0; i<structsNames.size() ; i++)
		{
			//עובר שורה שורה על כל השורות של הפונקציה
			for(int j=0 ; j<_changeOgen.size()  ; j++)
			{
				String currentLine=new String();
				currentLine=_changeOgen.elementAt(j);//השורה הנוכחית שבודקים
				int index=0;//מוצא את האינדקס שבו מתחילה המילה שצריך להחליף
				
				if( currentLine.indexOf("InsertStructName")!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
				{
					String word=new String("InsertStructName");
					String start=new String();
					String end=new String();
					String newline=new String(currentLine);
					
					while((index=newline.indexOf("InsertStructName"))!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
					{							
						start=newline.substring(0,index);
						end=newline.substring(index+word.length());
						//currentLine.replaceAll("InsertStructName", structsNames.elementAt(i));
						newline=start + structsNames.elementAt(i) + end;
						
					}
					_changeOgenFunction=_changeOgenFunction+newline;
				}
				
				
				else if ( (currentLine.indexOf("###")!=-1)    &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)) 
				{
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
					//	String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
	/**/				_changeOgenFunction=_changeOgenFunction+"			(*ogenPointer)->"  +temp+  "=NULL;"  +"\r\n";					
					}
					
					
				}
				
				else if ( (currentLine.indexOf("###")!=-1)    &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)) 
				{
				}
				
				
				else if ( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)  
						&&    (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size()==1  )
					    &&  (  (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("int")
					    		||   (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("char")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("real")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("float")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("double")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("short")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("long")
					    		)  )
				{
					
			//  ריק 
					
					
				}
				
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
				{
					//והאם לסטרקט שאנו מטפלים בו ישנם משתנים פנימיים.בסטרקט צ'אר שבדוגמא שלנו אין משתנים פנימיים ולכן אסור להיכנס לפה
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
						String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
					//	_changeOgenFunction=_changeOgenFunction+("			decreaseAndCheck(ogenPointer->"+temp+");"+"\r\n");
						_changeOgenFunction=_changeOgenFunction+("			decreaseAndCheck") +   pointerType  + "(&((*ogenPointer)->"  +temp+  "));"  +"\r\n";					
					}
					
				}
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
					// במקרה שהסטרקט שבו אנו מטפלים אינו מכיל פוינטרים 
					{
					//  לא נרשום כלום במקום הסימני שאלה
					}
					
				
				else
				{
					_changeOgenFunction=_changeOgenFunction+currentLine;
				}
			}
		}
	}
	
	
	
	
	
	/**
	 * יוצר סטרינג של הפונקציה עם כל הפרמוטציות של הסטרקטים 
	 * @param structsNames
	 */
	private void createDecreaseAndCheckFunction(Vector<String> structsNames) {
		//עובר על כל השמות של הסטרקטים שיש
		for(int i=0; i<structsNames.size() ; i++)
		{
			//עובר שורה שורה על כל השורות של הפונקציה
			for(int j=0 ; j<_decreaseAndCheck.size()  ; j++)
			{
				String currentLine=new String();
				currentLine=_decreaseAndCheck.elementAt(j);//השורה הנוכחית שבודקים
				int index=0;//מוצא את האינדקס שבו מתחילה המילה שצריך להחליף
				
				if( currentLine.indexOf("InsertStructName")!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
				{
					String word=new String("InsertStructName");
					String start=new String();
					String end=new String();
					String newline=new String(currentLine);
					
					while((index=newline.indexOf("InsertStructName"))!=-1)//האם בשורה זו צריך להכניס את השם של הסטרקט הנוכחי
					{							
						start=newline.substring(0,index);
						end=newline.substring(index+word.length());
						//currentLine.replaceAll("InsertStructName", structsNames.elementAt(i));
						newline=start + structsNames.elementAt(i) + end;
						
					}
					_decreaseAndCheckFunction=_decreaseAndCheckFunction+newline;
				}
				
				
				else if ( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)  
						&&    (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size()==1  )
					    &&  (  (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("int")
					    		||   (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("char")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("real")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("float")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("double")
					    		||    (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("short")
					    		||     (Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(0)).equals("long")
					    		)  )
				{
					
			//  ריק 
					
					
				}
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))!=null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
				{
					//והאם לסטרקט שאנו מטפלים בו ישנם משתנים פנימיים.בסטרקט צ'אר שבדוגמא שלנו אין משתנים פנימיים ולכן אסור להיכנס לפה
					for( int t=0; t < Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).size() ; t++ )   // לולאה שעוברת על  הוקטור המתאים לסטרקט ומכיל את הפוינטרים שמוכלים בו(בתור מחרוזות 
					{	
						String temp=Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
						String pointerType=Compailer.getInstance().get_typesOfPoinetrsForAllStructs().get(structsNames.elementAt(i)).elementAt(t);
					//	_decreaseAndCheckFunction=_decreaseAndCheckFunction+("			decreaseAndCheck(pointer->"+temp+");"+"\r\n");
						_decreaseAndCheckFunction=_decreaseAndCheckFunction+("			decreaseAndCheck") +   pointerType  + "(&((*pointer)->"  +temp+  "));"  +"\r\n";					
					}
					
				}
				
				else if( (currentLine.indexOf("???")!=-1)   &&   (Compailer.getInstance().get_pointersForAllStructs().get(structsNames.elementAt(i))==null)   )//האם בשורה זו צריך להכניס את הקריאה לפונקציה שמפחיתה מונה ובודקת אם יש לזרוק לפח , עבור כל פוינטר שמוכל בסטרקט
					// במקרה שהסטרקט שבו אנו מטפלים אינו מכיל פוינטרים 
					{
					//  לא נרשום כלום במקום הסימני שאלה
					}
					
				
				else
				{
					_decreaseAndCheckFunction=_decreaseAndCheckFunction+currentLine;
				}
			}
		}
	}



	public String get_ourNewOgenFunction() {
		return _ourNewOgenFunction;
	}



	public String get_ourNewPFunction() {
		return _ourNewPFunction;
	}



	public String get_changeOgenFunction() {
		return _changeOgenFunction;
	}



	public String get_decreaseAndCheckFunction() {
		return _decreaseAndCheckFunction;
	}


	public String getDeclerationsString() {
		return declerationsString;
	}
	
}
