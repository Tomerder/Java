package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;




public class Compailer {
	
	public HashMap<String,Vector<String>> _pointersForAllStructs=null;//����� ��� ���� ����� �����
	public HashMap<String,Vector<String>> _typesOfPoinetrsForAllStructs=null;//����� ��� ���� ����� �����

	//����� ����� �2 �������� ���� �� ������ ����� ���
	private Vector<String> _variablesName=null;//���� �� ����� ����� ������ ����� �� ��2
	private Vector<String> _variablesType=null;//��� �� ����� ����� ������ ����� �� ��2

	private String _parsedCFile,_outputCFile;
	private String _tillMainWithCounters;
	private functionsToAdd _func;
	private static Compailer _instance=null;
	private String _inputFileName;
	private String _outputFileName;
	
	
	public HashMap<String, Vector<String>> get_pointersForAllStructs() {
		return _pointersForAllStructs;
	}



	public void set_pointersForAllStructs(
			HashMap<String, Vector<String>> forAllStructs) {
		_pointersForAllStructs = forAllStructs;
	}



	public String getinputFileName() {
		return _inputFileName;
	}



	public void setinputFileName(String name) {
		_inputFileName = name;
	}



	public static Compailer getInstance()
	{
		if(_instance==null)
		{
			_instance=new Compailer();
		}
		return _instance;
	}
	
	
	
	public Compailer(){}
	
	
	public void execute()
	{
		_parsedCFile=new String();  // ������� ����� ���� �� ���� ���� 
		
		_outputCFile = new String();   //  ���� ���� ����� �� �� �������� ������� �����  �����
		
		
		
		String fileName=_inputFileName;
		
		OpenFile(fileName);
		_variablesName=new Vector<String>();
		_variablesType=new Vector<String>();
		
		_pointersForAllStructs=new HashMap<String,Vector<String>>();
		_typesOfPoinetrsForAllStructs=new HashMap<String,Vector<String>>();
		createDataBases(_parsedCFile) ;  // ���� �� ���� ���� ����� �� 2 ���� ������� ������� ���- 
											//�� �� ��������� �������� ������ 
											// ��� �������� ������ ������� �� ������� ����
	
	
		

		try {
			createOutputFile(_outputCFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
///////////////////////////////////////////////////////////////////////////////////  ���� ���� ������ ���� ������� 	
	
	/**
	 * Copy the input file into a File object
	 */
	public void OpenFile(String _fileName) {
		
		File fileToOpen=new File(_fileName);
		
		 if(fileToOpen.getName()!=null   &&   (  (fileToOpen.getName().endsWith(".C"))||(fileToOpen.getName().endsWith(".c"))   )) // checks if the file name is not null  and the file 
			 //type is a "C" file
    	 {
    		 try 
    		 {
				ConvertFile2String(fileToOpen);
			 } 
    		 catch (IOException e1)
    		 {
				e1.printStackTrace();
			 }
    		 
    	 }
		 else
		 {
			 try {
				throw new Exception("The file is not a c file");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
	}
	
	
	/**
	 * Open the file and copy is content into a single String object
	 */
	public void ConvertFile2String(File f) throws IOException
	{
	       StringBuffer fileData = new StringBuffer(1000);
	       BufferedReader reader = new BufferedReader(new FileReader(f));
	       char[] buf = new char[1024];
	       int numRead=0;
	       while((numRead=reader.read(buf)) != -1){
	           String readData = String.valueOf(buf, 0, numRead);
	           fileData.append(readData);
	           buf = new char[1024];
	       }
	       reader.close();
	       _parsedCFile= fileData.toString();
	       _parsedCFile=_parsedCFile.replaceAll("\r\n", "\n");//earse all the start new line from the string
	       
	       
	       while(_parsedCFile.indexOf(String.valueOf("\t"))!=-1)
	       {
	    	   _parsedCFile=_parsedCFile.replaceAll("\t", " ");//2 ������ ������ ����� ���

	       }
	       
	       
	       //����� �� �� ������� �������� ��������
	       while(_parsedCFile.indexOf(String.valueOf("  "))!=-1)
	       {
	    	   _parsedCFile=_parsedCFile.replaceAll("  ", " ");//2 ������ ������ ����� ���

	       }
	       
	       int begin;
	     //����� �� �� ����� �������� ��������
	       while(   (begin=_parsedCFile.indexOf(String.valueOf("/*")))  !=-1)
	       {
	    	   int sof=_parsedCFile.indexOf(String.valueOf("*/"));
	    	   String toDel=(String) _parsedCFile.subSequence(begin, sof);
	    	   _parsedCFile.replaceAll(toDel, "");
	       }
	       
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////



	public void createDataBases(String _parsedCFile){
		Vector<String> pointersNames=new Vector<String>();
		Vector<String> pointersTypes=new Vector<String>();
		
	
		int ind=0;
		String t1=new String();
		String t2=new String();
		
		//initialize temp with a line
		ind=_parsedCFile.indexOf(String.valueOf("void main("));
		int ind2=0;
		ind2=_parsedCFile.indexOf(String.valueOf("void main ("));//����� ��� ���� ��� ���� ����� ����
		
		if(ind<ind2)
		{
			ind=ind2;
		}

		t1=_parsedCFile.substring(0, ind);//�� �����
		
		t2=_parsedCFile.substring(ind, _parsedCFile.length());//������
		
		
		
		_tillMainWithCounters=t1;//����� �� ��������� ��������
		_tillMainWithCounters=addCounters(_tillMainWithCounters);
		
		
		
		
		
		
		while(	(ind=t1.indexOf(String.valueOf("struct")))!=-1   )//�� ��� �� ��� �������
		{
			
			t1=t1.substring(ind-8, t1.length());  // ������ �� �� ���� �� ����� ������� ����� ������ 
			String structName;
			
			if(t1.substring(0, 7).equals("typedef"))   
			{
				int indStructEnd=t1.indexOf(String.valueOf('}'));//��� ������
				if(t1.charAt(indStructEnd+1)==' ')
				{
					String tmp=t1.substring(indStructEnd+1 , t1.length());
					int i=0;
					while(   (tmp.charAt(i)!=' ')  &&   (tmp.charAt(i)!=';')  )
					{
						i++;
					}
					structName=t1.substring(indStructEnd+2 ,i);
				}
				else
				{
					String tmp=new String();
					tmp=t1.substring(indStructEnd , t1.length());
					int i=0;
					while(   (tmp.charAt(i)!=' ')  &&   (tmp.charAt(i)!=';')  )
					{
						i++;
					}
					structName=tmp.substring(1 ,i);
				}
				
			}
			
			else//if there is not typedef
			{
			//	t1=_parsedCFile.substring(ind , t1.length());//����� �� ����� �����
				
			//	int i=0;
			//	while(t1.charAt(i)==' ')//���� ��� ������
		//		{
		//			i++;
	//			}
				
				int spaceind=t1.indexOf(String.valueOf(' '));//���� �� ������� ��� ���� ���
				int spaceInd2=t1.indexOf(String.valueOf('{'));//���� �� ������� ��� ���� ���
				int actualInd;          // ������� �� ��� ��� �� ������
				
				if(spaceind>spaceInd2)
				{
					actualInd=spaceInd2;
				}
				else
				{
					actualInd=spaceind;
				}
				
				
				
				structName=t1.substring(ind ,actualInd);//����� �� ��� 
			}
			
			pointersNames=new Vector<String>();//���� �� ���� ���������� ������
			pointersTypes=new Vector<String>();
			
			int first=t1.indexOf( String.valueOf('{'));
			int second=t1.indexOf( String.valueOf('}'));
			
			String structBudy=t1.substring(first,second);//������ �� ���� ������ �}
			//��� }
			
			String anotherStructBody=new String(structBudy);
			int indOfStar;
			int indBeforeStar=0;
			
			while( (indBeforeStar=anotherStructBody.indexOf('*'))!=-1)
			{
				pointersTypes.add( getPreivousWordBeforeInd(anotherStructBody,indBeforeStar));
				anotherStructBody=anotherStructBody.substring(indBeforeStar+1,anotherStructBody.length());
			}
			
			while( (indOfStar=structBudy.indexOf('*'))!=-1)
			{
				pointersNames.add( getNextWordAfterInd(structBudy,indOfStar));
				structBudy=structBudy.substring(indOfStar+1,structBudy.length());
			}
			if(pointersNames.size()!=0)
			{
				_pointersForAllStructs.put(structName, pointersNames);
				_typesOfPoinetrsForAllStructs.put(structName, pointersTypes);
			}
			else
			{
				_pointersForAllStructs.put(structName, null);
				_typesOfPoinetrsForAllStructs.put(structName, null);
			}
			
			
			t1=t1.substring(second+1, t1.length());
		}
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		//��� ��� �� ���� �������
		//�� ���� ����� ����� �������,���� ����� �� �������� �� ������� ���� (�� �� 2) ���� �� ����� ���� ���� ���� ��� �� �� ������� �� �������� ���� ���� �����
		
		/**
		 * ����� �� ���� ���
		 */
		Iterator<Entry<String,Vector<String>>> it = _pointersForAllStructs.entrySet().iterator();//������� ����� �� ���� ������� �� ��������
		int currentStructInd=0;
		int fromIndex=0;
		
		while ( it.hasNext() )//�� ��� �� ��� ���� �� �������
		{
			fromIndex=0;
			//�� ��� ������� �� �� ������ ��� , ������ �� �� ������� ������� ���� ������� ��� ����� ��� ��� ������� ���� � ���
			Map.Entry<String,Vector<String>> entry = it.next();
			String structName=entry.getKey();//���� �� �� ������
			String structNameBeforeChange=structName;
			
			
			//because this two options are valid : P* a  and  P *a   we will search for both options and will chose the one that come first
			String structName2=structName + " *";
			structName=structName+"*";
			int indstar=t2.indexOf(structName, fromIndex);
			int indwithspace=t2.indexOf(structName2, fromIndex);
			
			/**
			if( (indstar>indwithspace)   &&  ( indwithspace!=-1))
			{
				structName=structName2;
				indstar=indwithspace;
			}
			else if((indwithspace>indstar)   &&  ( indstar!=-1)){
				structName=structName2;
				indstar=indwithspace;
			}*/
			
			if(indstar==-1)
			{
				indstar=indwithspace;
				structName=structName2;
			}
			else if((indstar>indwithspace)   &&  ( indwithspace!=-1))
			{
				structName=structName2;
				indstar=indwithspace;
			}
			//���� ��� ������ �� ����� ������ �� �� ������ �� ����� ��� ����� �� ����� �� ��
			int checkIfItMalloc=0;
			checkIfItMalloc=indstar+structName.length();
			
		//	if( t2.charAt(checkIfItMalloc)!=')'  &&  t2.charAt(checkIfItMalloc+1)!=')' )
		//	{
			
				while ( (currentStructInd=t2.indexOf(structName, fromIndex))!=-1)//�� ��� �� ��� ������ �� ������� �� ��� ������
				{
					indstar=t2.indexOf(structName, fromIndex);
					indwithspace=t2.indexOf(structName2, fromIndex);
					if( (indstar>indwithspace)   &&  ( indwithspace!=-1))
					{
						structName=structName2;
						indstar=indwithspace;
					}
					checkIfItMalloc=0;
					checkIfItMalloc=indstar+structName.length();
					if( t2.charAt(checkIfItMalloc)!=')'  &&  t2.charAt(checkIfItMalloc+1)!=')' ){
						
						fromIndex=currentStructInd+entry.getKey().length();//��� ������� ��� ���� ���� ������ ������ ����
						
						int findsemicolon= t2.indexOf(";", fromIndex);//���� ���� ���� �� ������
						int findstar= t2.indexOf("*", fromIndex);
					
						//���� �� �� ������ ������ ����� ������� ������ �� ���� ����� �����
						String varName=t2.substring(findstar+1, findsemicolon);
						varName=varName.replaceAll(" ", "");//����� ������
						_variablesName.add(varName);
						_variablesType.add(structNameBeforeChange);
						
						
						/**
						 * 
						 */
						//  ����� ������ ����� �� ������ ��� �������� �� ������ ������� ���� ��� �����
						//  ������ ���� �� ���� ���� ����� ������ ���� 
						//  pa->b    :     B 
						/**
						 * 
						 */
						
						
						
						
						//�� ����� ������ ������ �� ������ ��� �� ���� ���
						String checkIfTheVarAllReadyIntitialize=t2.substring(findstar, findsemicolon);
						if( checkIfTheVarAllReadyIntitialize.indexOf("=null")==-1  &&   checkIfTheVarAllReadyIntitialize.indexOf("= null")==-1)
						{
							
							String begin=t2.substring(0, findsemicolon);//������� �� ��2 �� ������� ���� �������
							String end=t2.substring(findsemicolon+1);
							
							end="=NULL;" + end;//����� �� �� ������ ����
							t2=begin+end;
						}
					
				}
					else
					{
						fromIndex=currentStructInd+entry.getKey().length();//
					}
			}
			
			
		}
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		/**
		 * ourNewOgen
		 */
		currentStructInd=0;
		fromIndex=0;
		
		for(int i=0 ; i<_variablesName.size() ; i++)//���� �� �� ���� ����� �����
		{
			String structName1=_variablesName.elementAt(i);//���� �� �� ����� �����			
			
			//������ ����� 2 ��������
			//������ �� 2 ��������� ���� ��� ����� ������ �������� �� ������ ����� ��� ������ ����� ������ �����
			//������ ���� �� ����� ����->����� �����
			//p1=    p1 =
			String structName2=structName1 + "=";
			structName1=structName1 + " =";

			//�� ��� ������ �� ����� ����� ����� ������ �����
			while(t2.indexOf(_variablesName.elementAt(i), fromIndex)!=-1)
			{
				//������ �� 2 ��������� ������ �� ��� ������� ����� �����
				int index1=t2.indexOf(structName1, fromIndex);
				int index2=t2.indexOf(structName2, fromIndex);
				
				
				int minIndex=index1;//������� ����
				String choosenString=structName1;//������� ������ ������� ���� ���
				
				
				if(index1==-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				else if(index1>index2   &&   index2!=-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				fromIndex=minIndex;

				int semicolon=t2.indexOf(";", minIndex);//���� ���� ����� ����� ��� ���� ����� �� ������
				
				String wholeLine=t2.substring(minIndex, semicolon);//����� ���� ��������
				
				//���� ���� ���� ������ �� ����� ����
			//	if(wholeLine.indexOf("malloc")!=-1  && wholeLine.indexOf(_variablesType.elementAt(i))!=-1)
				if(wholeLine.indexOf("malloc")!=-1 )
				{
					//����� �� ������ ��� ������ ���� ������ �� ��������
					int ptahSograim=wholeLine.indexOf("(");
					int kohav=wholeLine.indexOf("*");
					String shemStruct=wholeLine.substring(ptahSograim+1,kohav);
					shemStruct.trim();
					
					
					//������ �� ����� ���� ������� ���� ����� ����(����� �������� ����
					//���� �� ��� �� 2 ������ �� ����� , ��� ����� �� ������ ����� ���� ��� ���� �����
					String begin=t2.substring(0, minIndex-1);
					String end=t2.substring(semicolon+1);
					
					//��2 �� ����� ����
				//	t2=begin+ "ourNewOgen(" + _variablesName.elementAt(i) + "," + _variablesType.elementAt(i) + ");"+"\r\n" + end;
					t2=begin+ "ourNewOgen" + shemStruct + "(&(" + _variablesName.elementAt(i)  + "));"+"\r\n" + end;
					fromIndex=begin.length()+wholeLine.length();
				}
				else
				{
					fromIndex=fromIndex+choosenString.length();
				}
			}
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////		
		
		/**
		 * ourNewP
		 */
		currentStructInd=0;
		fromIndex=0;
		
		for(int i=0 ; i<_variablesName.size() ; i++)//���� �� �� ���� ����� �����
		{
			String structName1=_variablesName.elementAt(i);//���� �� �� ����� �����			
			
			//������ ����� 4 ��������
			//������ �� 4 ��������� ���� ��� ����� ������ �������� �� ������ ����� �� �����
			//p1->=    p1-> =     p1 ->=     p1 -> =
			String structName2=structName1 + " ->";
			structName1=structName1 + "->";
			
			
			//�� ��� ������ ������ �� ����� ����� ������ ���� ����� ������ �����
		//	while(t2.indexOf(_variablesName.elementAt(i), fromIndex)!=-1)
			while((t2.indexOf(structName1, fromIndex)!=-1)  ||   (t2.indexOf(structName2, fromIndex)!=-1))
			{
				//������ �� 2 ��������� ������ �� ��� ������� ����� �����
				int index1=t2.indexOf(structName1, fromIndex);
				int index2=t2.indexOf(structName2, fromIndex);

				
				int minIndex=index1;//������� ����
				String choosenString=structName1;//������� ������ ������� ���� ���
				
				//���� �� ������� ���� �������� ������ ��
				if(index1==-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				else if(index1>index2   &&   index2!=-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				
				fromIndex=minIndex;

				int semicolon=t2.indexOf(";", minIndex);//���� ���� ����� ����� ��� ���� ����� �� ������
				
				String wholeLine=t2.substring(minIndex, semicolon);
				
				//���� ���� ���� ������ �� ����� ����� ������ ����
			//	if(wholeLine.indexOf("malloc")!=-1  && wholeLine.indexOf(_variablesType.elementAt(i))!=-1)
				if(wholeLine.indexOf("malloc")!=-1)
				{
					
					//������ �� ����� ���� ������� ���� ����� ����(����� �������� ����
					//���� �� ��� �� 2 ������ �� ����� , ��� ����� �� ������ ����� ���� ��� ���� �����
					String begin=t2.substring(0, minIndex-1);
					String end=t2.substring(semicolon+1);
					
					//����� �� ������ ������ ��� ����� �����
					int privateBegin=wholeLine.indexOf(">");
					int privateEnd=wholeLine.indexOf("=");
					//String privateName=wholeLine.substring(privateBegin+1, privateEnd);
					//privateName=privateName.trim();//Returns a copy of the string, with leading and trailing whitespace omitted.
					
					
					String parameter=wholeLine.substring(0, privateEnd);
					
					
					
					//����� ��� ������ ������
					int structBegin=wholeLine.indexOf("(");
					int structEnd=wholeLine.indexOf("*");
					String structName=wholeLine.substring(structBegin+1, structEnd);
				//	structName=structName.replaceAll("*", "");//����� �� ������� ����
					structName=structName.trim();//Returns a copy of the string, with leading and trailing whitespace omitted.
					
					
					//��2 �� ����� ����
			//		t2=begin+ "ourNewP(" +_variablesName.elementAt(i)+ "->" + privateName + "," + structName + ");"+"\r\n" + end;
					t2=begin+ "ourNewP"  +  structName  +  "(&("+parameter+"));"+"\r\n" + end;
					String lineweinsert=new String("ourNewP" + structName + "(&(" +parameter+"));");
					fromIndex=begin.length()+lineweinsert.length();
				}
				else	
				{
					fromIndex=fromIndex+choosenString.length();
				}
			}
		}

		
		
//////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/**
		 * changeOgenPointer 
		 */
		currentStructInd=0;
		fromIndex=0;
		
		for(int i=0 ; i<_variablesName.size() ; i++)//���� �� �� ���� ����� �����
		{
			String structName1=_variablesName.elementAt(i);//���� �� �� ����� �����			
			
			//������ ����� 4 ��������
			//������ ��42 ��������� ���� ��� ����� ������ �������� �� ����� �� ����� ���� ��� ������ �����
			//p1=&    p1= &     p1 =&     p1 = &
			String structName2=structName1 + "= &";
			String structName3=structName1 + " =&";
			String structName4=structName1 + " = &";
			structName1=structName1 + "=&";

			//�� ��� ������ �� ����� ����� ����� ������ �����
	//		while(t2.indexOf(_variablesName.elementAt(i), fromIndex)!=-1)
			while(t2.indexOf(structName1, fromIndex)!=-1   ||   t2.indexOf(structName2, fromIndex)!=-1    ||   t2.indexOf(structName3, fromIndex)!=-1   ||    t2.indexOf(structName4, fromIndex)!=-1)
			{
				//������ �� 2 ��������� ������ �� ��� ������� ����� �����
				int index1=t2.indexOf(structName1, fromIndex);
				int index2=t2.indexOf(structName2, fromIndex);
				int index3=t2.indexOf(structName3, fromIndex);
				int index4=t2.indexOf(structName4, fromIndex);
				
				int minIndex=index1;//������� ����
				String choosenString=structName1;//������� ������ ������� ���� ���
				
				
				if(index1==-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				else if(index1>index2   &&   index2!=-1)
				{
					minIndex=index2;
					choosenString=structName2;
				}
				else if(minIndex>index3  &&   index3!=-1)
				{
					minIndex=index3;
					choosenString=structName3;
				}
				else if(minIndex>index4  &&   index4!=-1)
				{
					minIndex=index4;
					choosenString=structName4;
				}
				
				fromIndex=minIndex;

				int semicolon=t2.indexOf(";", minIndex);//���� ���� ����� ����� ��� ���� ����� �� ������
				
				String wholeLine=t2.substring(minIndex, semicolon);
				
				//������ �� ����� ���� ������� ���� ����� ����(����� �������� ����
				//���� �� ��� �� 2 ������ �� ����� , ��� ����� �� ������ ����� ���� ��� ���� �����
				String begin=t2.substring(0, minIndex-1);
				String end=t2.substring(semicolon+1);
				
				
				//����� �� ������ ������
				int privateBegin=wholeLine.indexOf("(");
				int privateEnd=wholeLine.indexOf(")");
				String privateName=wholeLine.substring(privateBegin+1, privateEnd);
				privateName=privateName.trim();//Returns a copy of the string, with leading and trailing whitespace omitted.
				
				int structindex=_variablesName.indexOf(_variablesName.elementAt(i));
				String structType=_variablesType.elementAt(structindex);
				
				
				//��2 �� ����� ����
				t2=begin+ "changeOgen" + structType +  "(&" + _variablesName.elementAt(i) + ",&(" +   privateName   + "));"+"\r\n" + end;

				fromIndex=begin.length()+wholeLine.length();
			}
		}
		
		
		
		
		
		
		_func=new functionsToAdd(structNames());
		//����� �� �� ��������� ���� ���� �����
		
		
		
		_tillMainWithCounters+=(_func.getDeclerationsString());		// ����� �� ������ �� ���������

		
		
		_tillMainWithCounters+=(_func.get_decreaseAndCheckFunction());
		_tillMainWithCounters+=_func.get_changeOgenFunction();
		_tillMainWithCounters+=(_func.get_ourNewOgenFunction());
		_tillMainWithCounters+=(_func.get_ourNewPFunction());
		
		
		
		
		_outputCFile=new String();
		_outputCFile=_tillMainWithCounters+t2;
		
		
		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//copy the c program with our modifications to a new file
	private void createOutputFile(String file) throws IOException {
		FileWriter fw = new FileWriter(new File(_outputFileName));
		fw.write(file);
		fw.flush();
		fw.close();
	
	}


//////////////////////////////////////////////////////////////////////////////////////////////////

	private String addCounters(String t1) {
		Vector<String> temp=new Vector<String>();
		int ind=0;
		while(	(ind=t1.indexOf("struct",ind))!=-1   )//�� ��� �� ��� �������
		{
			int structVarDec=t1.indexOf(String.valueOf("{"),ind);
			
			String begin=t1.substring(0, structVarDec+1);
			String end=t1.substring(structVarDec+1);
			
			String counter1="\r\n" + "int counter1;"+"\r\n";
			String counter2="int counter2;";
			
			t1=begin+counter1+counter2+end;
			ind=ind + 5;//will start the next search from the next struct
		}
		
		return t1;

		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////


	public String getNextWordAfterInd(String t , int ind)
	{
		String structName;
		if(t.charAt(ind+1)==' ')
		{
			String tmp=t.substring(ind+2 , t.length());
			int i=0;
			while(   (tmp.charAt(i)!=' ')  &&   (tmp.charAt(i)!=';')  )
			{
				i++;
			}
			structName=t.substring(ind+2 ,i+ind+2);
		}
		else
		{
			String tmp=t.substring(ind , t.length());
			int i=0;
			while(   (tmp.charAt(i)!=' ')  &&   (tmp.charAt(i)!=';')  )
			{
				i++;
			}
			structName=t.substring(ind+1 ,i);
		}
		
		return structName;
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	// ���� �� ����� ���� ������� ������ ������
	public String getPreivousWordBeforeInd(String t , int ind)
	{
		String structName;
		if(t.charAt(ind-1)==' ')
		{
		//	String tmp=t.substring(0 ,ind-2);
			int i=-2;
			while(   (t.charAt(ind+i)!=' ') )      //&&   (tmp.charAt(i)!=';')  )          
			{
				i--;
			}
			structName=t.substring(ind+i+1 ,ind-1 );
		}
		else
		{
		//	String tmp=t.substring(ind , t.length());
			int i=-1;
			while(   (t.charAt(ind+i)!=' ')  &&  (t.charAt(ind+i)!='\n')  &&  (t.charAt(ind+i)!='\r')    &&  (t.charAt(ind+i)!='\t')  &&  (t.charAt(ind+i)!='{') ) //&&   (tmp.charAt(i)!=';')  )
			{
				i--;
			}
			structName=t.substring(ind+i+1 ,ind );
		}
		
		return structName;
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	/**
	 * ����� ������ �� ���� 
	 * @return
	 */
	public Vector<String> structNames(){
		Iterator<Entry<String,Vector<String>>> it = _pointersForAllStructs.entrySet().iterator();//������� ����� �� ���� ������� �� ��������
		Vector<String> structNames=new Vector<String>();
		
		while ( it.hasNext() )//�� ��� �� ��� ���� �� �������
		{
			//�� ��� ������� �� �� ������ ��� , ������ �� �� ������� ������� ���� ������� ��� ����� ��� ��� ������� ���� � ���
			Map.Entry<String,Vector<String>> entry = it.next();
			String structName=entry.getKey();//
			structNames.add(structName);
		}
		return structNames;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	public String get_outputFileName() {
		return _outputFileName;
	}



	public void set_outputFileName(String fileName) {
		_outputFileName = fileName;
	}



	public HashMap<String, Vector<String>> get_typesOfPoinetrsForAllStructs() {
		return _typesOfPoinetrsForAllStructs;
	}



	public void set_typesOfPoinetrsForAllStructs(
			HashMap<String, Vector<String>> ofPoinetrsForAllStructs) {
		_typesOfPoinetrsForAllStructs = ofPoinetrsForAllStructs;
	}
	
}


























