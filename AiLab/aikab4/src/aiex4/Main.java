/**
 * Ai EX4
 * Orel shai - 052988912	
 * Tomer dery - 060628914
 */

package aiex4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.omg.CORBA._PolicyStub;


public class Main
{
	
	private Vector<Gen>  _population = new Vector<Gen>() ;   // ��������� ����
	private Vector<Gen>  _nextPopulation = new Vector<Gen>() ;//���� ���

	private int _populationSize=160;
	private int _percentageForMutation=25;
	private int _numberOfIterations=10000;

	private int  _allowNumberOfIterationsWithNoBetterFitnees=_numberOfIterations/10;//���� ��������� ����� ����� ���� ������
	private String _stroy;	
	private HashMap<Character , Character> _key;
	private String _storyPath=new String("c:\\stories.txt");
	private String _encodedStory;

	public static void main(String args[])
	{
		Main m=new Main();
		
		//open the file with the story and copy it to a string
		m._stroy=m.getStory ( m._storyPath );
		//our key
		m._key=new HashMap<Character , Character>() ;
		
		m._key.put ( 'a' , ' ' );
		m._key.put ( 'b' , 'z' );
		m._key.put ( 'c' , 'y' );
		m._key.put ( 'd' , 'x' );
		m._key.put ( 'e' , 'w' );
		m._key.put ( 'f' , 'v' );
		m._key.put ( 'g' , 'u' );
		m._key.put ( 'h' , 't' );
		m._key.put ( 'i' , 's' );
		m._key.put ( 'j' , 'r' );
		m._key.put ( 'k' , 'q' );
		m._key.put ( 'l' , 'p' );
		m._key.put ( 'm' , 'o' );
		m._key.put ( 'n' , 'n' );
		m._key.put ( 'o' , 'm' );
		m._key.put ( 'p' , 'l' );
		m._key.put ( 'q' , 'k' );
		m._key.put ( 'r' , 'j' );
		m._key.put ( 's' , 'i' );
		m._key.put ( 't' , 'h' );
		m._key.put ( 'u' , 'g' );
		m._key.put ( 'v' , 'f' );
		m._key.put ( 'w' , 'e' );
		m._key.put ( 'x' , 'd' );
		m._key.put ( 'y' , 'c' );
		m._key.put ( 'z' , 'b' );
		m._key.put ( ' ' , 'a' );
		
		m._encodedStory=new String();
		////����� �����
		m._encodedStory=m.encodeString ( m._stroy );
		
		
		m._population = new Vector<Gen>() ;   
		m._nextPopulation = new Vector<Gen>() ;
	 
		
		m.initPopulation();  // ����� �������� ������� �� �� ���� ��� �� ����� ���� ������� �� � �� �� �� ��� ���� ���� ���� �������� ������ ����
		
		int numberOfIterationsWithNoBetterFitness=0;//��' ��������� �� ���� ������
		
		double bestLegalFitness=Integer.MAX_VALUE;//������ ����� ��� ���
		int counter=0;
		
		//////////////////////
		
		
		
		
		////////////////////////
		
		
		
		while(numberOfIterationsWithNoBetterFitness < m._allowNumberOfIterationsWithNoBetterFitnees)  //�� ��� �� �������
		{
			//������ �����
			m.fitnessToPopulation();

			/////////sort the population by fitness
			int n =  m.get_populationSize ( );
		    for (int pass=0; pass < n-1; pass++) {  // count how many times
		        // This next loop becomes shorter and shorter
		    	Boolean exchangeFlag=false; 	
		    	for (int i=0; i < n-1; i++) {     
		        	if (m.get_population ( ).elementAt ( i ).get_fitness ( )  >  m.get_population ( ).elementAt ( i+1 ).get_fitness ( )) {
		                // exchange elements
		            //	Gen temp=m.get_population ( ).elementAt ( i );
		            	Gen xi=m.get_population ( ).elementAt ( i );
		            	Gen xi1=m.get_population ( ).elementAt ( i+1 );
		            	m.get_population ( ).remove(i);
		            	m.get_population ( ).add ( i , xi1 );         	
		            	m.get_population ( ).remove(i+1);
		            	m.get_population ( ).add ( i+1 , xi );         	
		            	exchangeFlag=true;
		            }
		        }	
		    	if(!exchangeFlag)
		    		break;              //  �� ����� ��� �� ����� ����� , ������	
		    }
		    
		    
		    
		    // ����� �� ����� ������� ��� ����
		//    double minFitness=5000;
		    double avrageFitness ;
		    double totalFitness=0;
		    for (int i = 0; i< m.get_population().size(); i++) {
			//	if(m.get_population().elementAt(i).get_fitness()<  minFitness )
			//	{
			//		minFitness=m.get_population().elementAt(i).get_fitness();
			//	}
				totalFitness= totalFitness + m.get_population().elementAt(i).get_fitness();
				
			}
		    avrageFitness= totalFitness / m.get_populationSize() ;
		    
		    System.out.println (counter+ " - avrageFitness:" + avrageFitness);
	//	    System.out.println (counter+ " - minfitness:" + minFitness);

		    System.out.println (counter+ " - best fitness:" + m.get_population ( ).elementAt ( 0 ).get_fitness ( ));
		    System.out.println (counter+ " - best key:" + m.get_population ( ).elementAt ( 0 ).get_key ( ));
		    System.out.println (counter + " - story : "  + m.decodStoryToOriginalStory (m.get_population ( ).elementAt ( 0 )) );

		    
		    int numberOftrueLetterMatch=0;
		    
		    
		    HashMap<Character, Character>  bestKey =  m.get_population().elementAt(0).get_key();
		   
		    if (bestKey.get('a') == ' ' )
			{
				numberOftrueLetterMatch++;
			}
			if (bestKey.get('b') == 'z' )
			{
				numberOftrueLetterMatch++;
			}
			if (bestKey.get('c') == 'y' )
			{
				numberOftrueLetterMatch++;
			}
			if (bestKey.get('d') == 'x' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('e') == 'w' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('f') == 'v' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('g') == 'u' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('h') == 't' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('i') == 's' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('j') == 'r' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('k') == 'q' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('l') == 'p' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('m') == 'o' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('n') == 'n' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('o') == 'm' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('p') == 'l' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('q') == 'k' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('r') == 'j' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('s') == 'i' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('t') == 'h' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('u') == 'g' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('v') == 'f' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('w') == 'e' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('x') == 'd' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('y') == 'c' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get('z') == 'b' )
			{
				numberOftrueLetterMatch++;
			}if (bestKey.get(' ') == 'a' )
			{
				numberOftrueLetterMatch++;
			}
			
		   
		    System.out.println (counter + " - numberOftrueLetterMatch : "  + numberOftrueLetterMatch );
		    
		    
		    
	//////////////////////////////////////	    
		    
		    //���� ���� ���
		    m.nextPopulationRolate();
		    /*
		    //������ �����
			m.fitnessToPopulation();
			
			/////////sort the population by fitness
		    n =  m.get_populationSize ( );
		    for (int pass=0; pass < n-1; pass++) {  // count how many times
		        // This next loop becomes shorter and shorter
		    	Boolean exchangeFlag=false; 	
		    	for (int i=0; i < n-1; i++) {     
		        	if (m.get_population ( ).elementAt ( i ).get_fitness ( )  >  m.get_population ( ).elementAt ( i+1 ).get_fitness ( )) {
		                // exchange elements
		            //	Gen temp=m.get_population ( ).elementAt ( i );
		            	Gen xi=m.get_population ( ).elementAt ( i );
		            	Gen xi1=m.get_population ( ).elementAt ( i+1 );
		            	m.get_population ( ).remove(i);
		            	m.get_population ( ).add ( i , xi1 );         	
		            	m.get_population ( ).remove(i+1);
		            	m.get_population ( ).add ( i+1 , xi );         	
		            	exchangeFlag=true;
		            }
		        }	
		    	if(!exchangeFlag)
		    		break;              //  �� ����� ��� �� ����� ����� , ������	
		    }
		    	*/	    
		    /**
			 * ����� ��� ������ ���� ����� ������ ���� ��� ������� ��� ��� �� ����� , ������ ��� ����� ������ ��������� �� ����� �� ���� ������ - ��� �����
			 */
			if(bestLegalFitness > m._population.elementAt ( 0 ).get_fitness ( ))//������ ���� �� ���� ������ ���� ��� ������� �� ���� ������
			{
				bestLegalFitness=m._population.elementAt ( 0 ).get_fitness ( );
				numberOfIterationsWithNoBetterFitness=0;
			}
			else
			{
				numberOfIterationsWithNoBetterFitness++;
				if(numberOfIterationsWithNoBetterFitness>=m._allowNumberOfIterationsWithNoBetterFitnees)
				{
					System.out.println ("The result was converged ");
					break;
				}
			}
		  //  System.out.println (counter+ " - best fitness:" + m.get_population ( ).elementAt ( 0 ).get_fitness ( ));
		 //   System.out.println (counter+ " - best key:" + m.get_population ( ).elementAt ( 0 ).get_key ( ));
		   // System.out.println (counter + "story    :     "  + m.decodStoryToOriginalStory (m.get_population ( ).elementAt ( 0 )) );
		    System.out.println ( );
		    counter++;
		}
		
	
		System.out.println ("finish");
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private double fitnessToGen ( Gen g )
	{
		
		Gen temp=g;
		temp.set_fitness ( 0 );
		String decodedStroy=this.decodStoryToOriginalStory ( temp );
		//����� �� ��������� ��� ���
		
		if(temp.get_key ( ).get ( 'a' )==' ')
		{
			//System.out.println (decodStoryToOriginalStory(temp));
		//	System.out.println ();
		}
		
		if(temp.get_key ( ).get ( 'f' )==' ')
		{
			//System.out.println (decodStoryToOriginalStory(temp));
			//System.out.println ();
		}
		
		
		
		///////////////////////////////////////////////////////
		/////////////////////Word lengths/////////////////////
		///////////////////////////////////////////////////////
		int[] wordLengths=new int[Frequency.getInstance ( ).get_wordLengths ( ).size ( )];
		for ( int j = 0 ; j < wordLengths.length ; j ++ )//������ �����
		{
			wordLengths[j]=0;
		}
		int totalNumberOfWords=0;//����� ����� �������
		int index=0;
		for ( int j = 0 ; j < decodedStroy.length() ; j ++ )
		{
			if(decodedStroy.charAt ( j )==' ')//��� ���� ������� ��� ����
			{
				totalNumberOfWords++;//���� ������ ��� � 1
				int wordLength=j-index;//����� ���� �����
				index=j+1;
				//������ �����
				if(wordLength>=wordLengths.length){
				//	wordLengths[wordLengths.length-1]=wordLengths[wordLengths.length-1]+1;
					temp.set_fitness(temp.get_fitness()  +  100000)  ;   //�� ����� ����� ���� ���� ���� ����
				}
				else
				{
					if(wordLength==10)
					{
						//System.out.println ();
					}
					if(wordLength>0)
					{
						wordLengths[wordLength-1]=wordLengths[wordLength-1]+1;
					}
					
				}
			}
		}
		//����� ���� ����� �������
		totalNumberOfWords++;
		int wordLength=(decodedStroy.length()-1)-index;
		if(wordLength>=wordLengths.length){
			wordLengths[wordLengths.length-1]=wordLengths[wordLengths.length-1]+1;
		}
		else
		{
			if( wordLength!=-1)
			{
				wordLengths[wordLength]=wordLengths[wordLength]+1;
			}
			
		}
		
		
		///////////////����� ������ ��� ���� ������
		HashMap < Integer , Double > wordLengthsFrequency = Frequency.getInstance ( ).get_wordLengths ( );
		for ( int j = 0 ; j < wordLengths.length ; j ++ )
		{
			double w=(double)wordLengths[j];
			double t=(double)totalNumberOfWords;
			double precent=(w / t)*100;  //���� �� ����� ����� ����� ����� ����� ������� ��� �����
				//double difference =Math.pow ( wordLengthsFrequency.get ( j )-precent , 2 );//����� ����� ���� ������ ����� ������ ����� ����� ����� ����� ���� ����� ����� ���������
			//double difference =Math.sqrt (Math.abs ( wordLengthsFrequency.get ( j )-precent  ));//����� ����� ���� ������ ����� ������ ����� ����� ����� ����� ���� ����� ����� ���������
			double difference = Math.abs ( wordLengthsFrequency.get ( j )-precent  ) ;//����� ����� ���� ������ ����� ������ ����� ����� ����� ����� ���� ����� ����� ���������

			temp.set_fitness ( temp.get_fitness ( )+difference  );//������ ������ �� ���
		}
		
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		///////////////////////////////////////////////////////
		/////////////////////Monogram frequencies//////////////
		///////////////////////////////////////////////////////
		int[] lettersFrequency=new int[Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( )];//����� ���� �������� �� ������� �����
		for ( int j = 0 ; j < lettersFrequency.length ; j ++ )//������ �����
		{
			lettersFrequency[j]=0;
		}
		for ( int j = 0 ; j < decodedStroy.length() ; j ++ )//���� �� ����� , ���� �� ��� ���� ������ �� ���� �� ����� �� ���� ��� � 1
		{
			if(decodedStroy.charAt ( j )==' ')//��� ���� ������� ��� ����
			{		
				lettersFrequency[lettersFrequency.length-1]=lettersFrequency[lettersFrequency.length-1]+1;
				temp.set_fitness ( temp.get_fitness ( )- 5   );////////////////////////////////////////////////////////////////������ ������ �� ��� ����� �� ������ ������
			}
			else 
			{
				if(decodedStroy.charAt ( j )=='e' ||  decodedStroy.charAt ( j )=='t' ||  decodedStroy.charAt ( j )=='a' ||  decodedStroy.charAt ( j )=='o' ||  decodedStroy.charAt ( j )=='i' ||  decodedStroy.charAt ( j )=='n'||  decodedStroy.charAt ( j )=='h'||  decodedStroy.charAt ( j )=='s' )    // ����� ������� ������
				{
					temp.set_fitness ( temp.get_fitness ( )- 3   );/////////////////////////////////////////////////////////������ ������ �� ��� ����� �� ������ ������
				}
					lettersFrequency[(int)decodedStroy.charAt ( j )-'a']=lettersFrequency[(int)decodedStroy.charAt ( j )-'a']+1;
			}
		}
		int totalNumberOfLetters=decodedStroy.length();//���� ������� ������ , ����� ����� �������
		
		///////////////����� ������ ��� ������ �������
		HashMap < Character , Double > monoramsFrequency = Frequency.getInstance ( ).get_monogramFrequencies ( );
		
		for ( int j = 0 ; j < lettersFrequency.length-1 ; j ++ )//���� ��� �� ���� ����� ����
		{
			double precent=(double)lettersFrequency[j] / (double)totalNumberOfLetters;  //���� �� ��� ����� ������� ��� �����
			precent=precent*100;
			char currentLetter=(char)('a'+j);//���� ������� �� ������� ��� ���
			
			//double difference =Math.pow ( monoramsFrequency.get ( currentLetter )-precent , 2 );//   ����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ��������� ������ ������
			double difference =Math.abs ( monoramsFrequency.get ( currentLetter )-precent );//   ����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ��������� 
			temp.set_fitness ( temp.get_fitness ( )+difference  *10   );/////////////////////////////////////////////////////////////////////������ ������ �� ���
		}
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////
		/////////////////////bigrams including space//////////////
		///////////////////////////////////////////////////////
		HashMap<String, Integer> twoLettersFrequency=new HashMap<String, Integer>();//����� ��� �������� �� ���� ������� ����� ���� ��� ������� ��� ����� ����� ��� ������� ����
		int totalNumberOfTwoLetters=0;//���� ���� ������� �"������" ������ , ����� ����� �������
		
		HashMap < String , Double > actualTwoLettersFrequency = Frequency.getInstance ( ).get_bigramsIncludingSpace ( );//��� ��������� �������
		
		for ( int j = 0 ; j < decodedStroy.length()-1 ; j ++ )//���� �� ����� , ���� �� ��� ������ ���� ������ �� ���� �� ����� �� ���� ��� ������ � 1
		{
			String currentTwoLetters=new String (String.valueOf ( decodedStroy.charAt ( j )) + String.valueOf ( decodedStroy.charAt ( j+1 ) ));//��� ������� ������� �� ���
			if(actualTwoLettersFrequency.containsKey ( currentTwoLetters ))//�� ���� ��� ��� �� ������ ���� �� ���� ���������
			{
				totalNumberOfTwoLetters++;
				if(twoLettersFrequency.containsKey ( currentTwoLetters ))//�� ���� ��� ��� �� ������ ���� ����� ������ �� ����� ��� ���
				{
					int mone = twoLettersFrequency.get ( currentTwoLetters );//����� �� ����� ������ �� ��� �������
					mone=mone+1;//����� ����� ����
					twoLettersFrequency.put ( currentTwoLetters , mone );//������ ����
				}
				else//�� ���� ��� ��� �� ������ ���� �����
				{
					twoLettersFrequency.put ( currentTwoLetters , 1 );//����� ���� ���� ����
				}
			}
			else
			{
				temp.set_fitness ( temp.get_fitness ( )+ 100 );//////////////////////////////////////////////������ ������ �� ���// �� �� ���� ��� ��� ���� ���� ����

			}
		}

		
		///////////////����� ������ ��� ������ �������
		Set entries = twoLettersFrequency.entrySet();
		Iterator it = entries.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			double precent=(double)(Integer)entry.getValue ( ) / totalNumberOfTwoLetters;  //���� �� ��� ������ ����� ������� ���� �����
			precent=precent*100;
			//double difference =Math.sqrt (Math.abs( actualTwoLettersFrequency.get ( (String)entry.getKey ( ) )-precent   ));//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������
			//double difference =Math.abs ( actualTwoLettersFrequency.get ( (String)entry.getKey ( ) )-precent  );//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������
			double difference = Math.abs( actualTwoLettersFrequency.get ( (String)entry.getKey ( ) )-precent   );//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������

			temp.set_fitness ( temp.get_fitness ( )+difference * 50);//������ ������ �� ���
		}
		
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////
		/////////////////////trigrams including space//////////////
		///////////////////////////////////////////////////////
		HashMap<String, Integer> threeLettersFrequency=new HashMap<String, Integer>();//����� ��� �������� �� ���� ������� ����� ���� ��� ������� ��� ����� ����� ��� ������� ����
		int totalNumberOfThreeLetters=0;//���� ���� ������� �"������" ������ , ����� ����� �������
		
		HashMap < String , Double > actualThreeLettersFrequency = Frequency.getInstance ( ).get_trigramsIncludingSpace ( );//��� ��������� �������
		
		for ( int j = 0 ; j < decodedStroy.length()-2 ; j ++ )//���� �� ����� , ���� �� ��� ������ ���� ������ �� ���� �� ����� �� ���� ��� ������ � 1
		{
			String currentThreeLetters=new String (String.valueOf ( decodedStroy.charAt ( j )) + String.valueOf ( decodedStroy.charAt ( j+1 ) )  +  String.valueOf ( decodedStroy.charAt ( j+2 )));//��� ������� ������� �� ���
			if(actualThreeLettersFrequency.containsKey ( currentThreeLetters ))//�� ���� ��� ��� �� ������ ���� �� ���� ���������
			{
				totalNumberOfThreeLetters++;
				if(threeLettersFrequency.containsKey ( currentThreeLetters ))//�� ���� ��� ��� �� ������ ���� ����� ������ �� ����� ��� ���
				{
					int mone = threeLettersFrequency.get ( currentThreeLetters );//����� �� ����� ������ �� ��� �������
					mone=mone+1;//����� ����� ����
					threeLettersFrequency.put ( currentThreeLetters , mone );//������ ����
				}
				else//�� ���� ��� ��� �� ������ ���� �����
				{
					threeLettersFrequency.put ( currentThreeLetters , 1 );//����� ���� ���� ����
				}
			}
			else
			{
				temp.set_fitness ( temp.get_fitness ( )+ 100 );////////////////////////////////////////������ ������ �� ���// �� �� ���� ��� ��� ���� ���� ����
			}
		}

		
		///////////////����� ������ ��� ������ �������
		Set entries2 = threeLettersFrequency.entrySet();
		Iterator it2 = entries2.iterator();
		while (it2.hasNext()) {
			Map.Entry entry = (Map.Entry) it2.next();
			double precent=(double)(Integer)entry.getValue ( ) / totalNumberOfThreeLetters;  //���� �� ��� ������ ����� ������� ���� �����
			precent=precent*100;
		//	double difference =Math.sqrt (Math.abs( actualThreeLettersFrequency.get ( (String)entry.getKey ( ) )-precent  ));//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������
			//double difference =Math.abs ( actualThreeLettersFrequency.get ( (String)entry.getKey ( ) )-precent  );//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������
			double difference = Math.abs( actualThreeLettersFrequency.get ( (String)entry.getKey ( ) )-precent  ) ;//����� ����� ���� ������ ���� ����� ����� ���� ����� ����� ���������

			temp.set_fitness ( temp.get_fitness ( )+difference*20 );///////////////////////////////////////////////////������ ������ �� ���
		}
		
		if(temp.get_key ( ).get ( 'a' )==' ')
		{
		//	System.out.println (decodStoryToOriginalStory(temp));
		//	System.out.println ();
		}
		
	
		
		
		
		

		//���� �� ������ ������ ��� �� A I
		
		
		for (int j = 3; j < decodedStroy.length(); j++) {
			if(decodedStroy.charAt(j)==' ' &&  decodedStroy.charAt(j-2)==' '  )
			{
				if(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j-1)!='i' )
				{
					temp.set_fitness ( temp.get_fitness ( )+ 5000 );//���� 
				}
				if(decodedStroy.charAt(j-1)=='a' )
				{
					temp.set_fitness ( temp.get_fitness ( )-100 );  ///////////////////////// �����
				}
			}
			
			
		}
		
		
		
		
		
		// ���� �� ����� ���� ���� �� �������� 
		
		for (int j = 3; j < decodedStroy.length(); j++) {
			if(decodedStroy.charAt(j-2)==' '    )
			{
				if( (decodedStroy.charAt(j-1)!='t'  &&  decodedStroy.charAt(j )!='h' )  ||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='n' ) ||  
						(decodedStroy.charAt(j-1)!='t'  &&  decodedStroy.charAt(j )!='o' ) || 
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='e' ) ||  
						(decodedStroy.charAt(j-1)!='o'  &&  decodedStroy.charAt(j )!='f' ) ||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!='n' )    || 
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='i' )  ||  
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='a' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='h' )    ||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='e' )|| 
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='y'  &&  decodedStroy.charAt(j )!='o' ) ||
						(decodedStroy.charAt(j-1)!='n'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='i' )    ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='h' )|| 
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!='t' ) ||
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='r'  &&  decodedStroy.charAt(j )!='e' )  ||  
						(decodedStroy.charAt(j-1)!='o'  &&  decodedStroy.charAt(j )!='n' )    ||  
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='a' )|| 
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='l' )||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='o' ) ||
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='s' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='e' )    ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='e' )|| 
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='u' ) ||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!=' ' )  ||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!=' ' )||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='n' ) ||  
						(decodedStroy.charAt(j-1)!='t'  &&  decodedStroy.charAt(j )!='o' ) || 
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='e' ) ||  
						(decodedStroy.charAt(j-1)!='o'  &&  decodedStroy.charAt(j )!='f' ) ||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!='n' )    || 
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='i' )  ||  
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='a' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='h' )    ||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='e' )|| 
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='y'  &&  decodedStroy.charAt(j )!='o' ) ||
						(decodedStroy.charAt(j-1)!='n'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='i' )    ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='h' )|| 
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!='t' ) ||
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='r'  &&  decodedStroy.charAt(j )!='e' )  ||  
						(decodedStroy.charAt(j-1)!='o'  &&  decodedStroy.charAt(j )!='n' )    ||  
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='a' )|| 
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='l' )||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='o' ) ||
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='s' )  ||  
						(decodedStroy.charAt(j-1)!='w'  &&  decodedStroy.charAt(j )!='e' )    ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='e' )|| 
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='u' ) ||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!=' ' )  ||  
						(decodedStroy.charAt(j-1)!='i'  &&  decodedStroy.charAt(j )!=' ' ))
				{
					temp.set_fitness ( temp.get_fitness ( )+ 1 );//���� 
				}
				
				else if( (decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='e' )  ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='t' ) ||  
						(decodedStroy.charAt(j-1)!='d'  &&  decodedStroy.charAt(j )!='o' ) || 
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='t' ) ||  
						(decodedStroy.charAt(j-1)!='l'  &&  decodedStroy.charAt(j )!='i' ) ||  
						(decodedStroy.charAt(j-1)!='d'  &&  decodedStroy.charAt(j )!='e' )    || 
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='r' )  ||  
						(decodedStroy.charAt(j-1)!='h'  &&  decodedStroy.charAt(j )!='o' )  ||  
						(decodedStroy.charAt(j-1)!='d'  &&  decodedStroy.charAt(j )!='i' )    ||  
						(decodedStroy.charAt(j-1)!='l'  &&  decodedStroy.charAt(j )!='o' )|| 
						(decodedStroy.charAt(j-1)!='l'  &&  decodedStroy.charAt(j )!='e' )||  
						(decodedStroy.charAt(j-1)!='a'  &&  decodedStroy.charAt(j )!='r' ) ||
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!=' ' )  ||  
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='a' )  ||  
						(decodedStroy.charAt(j-1)!='p'  &&  decodedStroy.charAt(j )!='a' )    ||  
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='i' )|| 
						(decodedStroy.charAt(j-1)!='g'  &&  decodedStroy.charAt(j )!='o' )||  
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='y' ) ||
						(decodedStroy.charAt(j-1)!='s'  &&  decodedStroy.charAt(j )!='u' )  ||  
						(decodedStroy.charAt(j-1)!='d'  &&  decodedStroy.charAt(j )!='a' )  ||  
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='i' )    ||  
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='h' )|| 
						(decodedStroy.charAt(j-1)!='l'  &&  decodedStroy.charAt(j )!='a' )||  
						(decodedStroy.charAt(j-1)!='p'  &&  decodedStroy.charAt(j )!='e' ) ||
						(decodedStroy.charAt(j-1)!='e'  &&  decodedStroy.charAt(j )!='x' )  ||  
						(decodedStroy.charAt(j-1)!='f'  &&  decodedStroy.charAt(j )!='e' )  ||  
						(decodedStroy.charAt(j-1)!='p'  &&  decodedStroy.charAt(j )!='o' )    ||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='y' )|| 
						(decodedStroy.charAt(j-1)!='m'  &&  decodedStroy.charAt(j )!='i' )||  
						(decodedStroy.charAt(j-1)!='u'  &&  decodedStroy.charAt(j )!='p' ) ||  
						(decodedStroy.charAt(j-1)!='g'  &&  decodedStroy.charAt(j )!='r' )  ||  
						(decodedStroy.charAt(j-1)!='n'  &&  decodedStroy.charAt(j )!='e' ) ||
						(decodedStroy.charAt(j-1)!='o'  &&  decodedStroy.charAt(j )!='u' ) ||
						(decodedStroy.charAt(j-1)!='u'  &&  decodedStroy.charAt(j )!='n' )  ||  
						(decodedStroy.charAt(j-1)!='c'  &&  decodedStroy.charAt(j )!='r' )  ||  
						(decodedStroy.charAt(j-1)!='e'  &&  decodedStroy.charAt(j )!='v' )    ||  
						(decodedStroy.charAt(j-1)!='t'  &&  decodedStroy.charAt(j )!='r' )|| 
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='r' )||  
						(decodedStroy.charAt(j-1)!='b'  &&  decodedStroy.charAt(j )!='a' ) ||  
						(decodedStroy.charAt(j-1)!='t'  &&  decodedStroy.charAt(j )!='a' )    )
				{
					temp.set_fitness ( temp.get_fitness ( )+ 100 );// ���� ���� �� ���� �� ������� ������ �������� 
				}
			}
			
		}
		
		
		//  ����� �� ������� ������
		for (int j = 3; j < decodedStroy.length(); j++) {
			if(decodedStroy.charAt(j-2)==' '    )
			{
				if( (decodedStroy.charAt(j-1)=='t'  &&  decodedStroy.charAt(j )=='h' )  )
				{
					temp.set_fitness ( temp.get_fitness ( )-  10   );//������ ������ �� ��� ����� �� ������ ������
				}
				else if(		(decodedStroy.charAt(j-1)=='a'  &&  decodedStroy.charAt(j )=='n' ) ||  
						(decodedStroy.charAt(j-1)=='t'  &&  decodedStroy.charAt(j )=='o' ) || 
						(decodedStroy.charAt(j-1)=='h'  &&  decodedStroy.charAt(j )=='e' )  )
				{
					temp.set_fitness ( temp.get_fitness ( )-5 );//����� 
				}
			}
			
		}
		
		
		
		
// ���� �� ��� ���� ���� �� �������� 
		
		for (int j = 3; j < decodedStroy.length(); j++) {
			if(decodedStroy.charAt(j )==' '    )
			{
				if( (decodedStroy.charAt(j-2)!='h'  &&  decodedStroy.charAt(j-1 )!='e' )  ||  
						(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='d' ) ||  
						(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='d' ) || 
						(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='g' ) ||  
						(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='r' ) ||  
						(decodedStroy.charAt(j-2)!='t'  &&  decodedStroy.charAt(j-1 )!='o' )    || 
						(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='t' )  ||  
						(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='f' )  ||  
						(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='s' )    ||  
						(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='s' )|| 
						(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='n' )||  
						(decodedStroy.charAt(j-2)!='r'  &&  decodedStroy.charAt(j-1 )!='e' ) ||
						(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='n' )  ||  
						(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='n' )  ||  
						(decodedStroy.charAt(j-2)!='l'  &&  decodedStroy.charAt(j-1 )!='l' )    ||  
						(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='s' )|| 
						(decodedStroy.charAt(j-2)!='l'  &&  decodedStroy.charAt(j-1 )!='y' )||  
						(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='r' )||
						(decodedStroy.charAt(j-2)!='m'  &&  decodedStroy.charAt(j-1 )!='e' )  ||  
						(decodedStroy.charAt(j-2)!='u'  &&  decodedStroy.charAt(j-1 )!='t' )  ||  
						(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='t' )    ||  
						(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='u' )|| 
						(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='n' )||  
						(decodedStroy.charAt(j-2)!='t'  &&  decodedStroy.charAt(j-1 )!='h' )||
						(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='d' )  ||  
						(decodedStroy.charAt(j-2)!='v'  &&  decodedStroy.charAt(j-1 )!='e' )  ||  
						(decodedStroy.charAt(j-2)!='s'  &&  decodedStroy.charAt(j-1 )!='t' )    ||  
						(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='t' )|| 
						(decodedStroy.charAt(j-2)!='l'  &&  decodedStroy.charAt(j-1 )!='e' )||  
						(decodedStroy.charAt(j-2)!='l'  &&  decodedStroy.charAt(j-1 )!='d' )||
						(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='d' )  ||  
						(decodedStroy.charAt(j-2)!='c'  &&  decodedStroy.charAt(j-1 )!='h' )  ||  
						(decodedStroy.charAt(j-2)!='c'  &&  decodedStroy.charAt(j-1 )!='e' )    ||  
						(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='t' )|| 
						(decodedStroy.charAt(j-2)!='s'  &&  decodedStroy.charAt(j-1 )!='e' )||  
						(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='e' )||  
						(decodedStroy.charAt(j-2)!=' '  &&  decodedStroy.charAt(j-1 )!='i' )||  
						(decodedStroy.charAt(j-2)!=' '  &&  decodedStroy.charAt(j-1 )!='a' )  ||  
						(decodedStroy.charAt(j-2)!=' '  &&  decodedStroy.charAt(j-1 )!='s' ) 
					 
						
				)
				{
					temp.set_fitness ( temp.get_fitness ( )+ 1 );//���� 
				}
				else if((decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='w' )  ||  
							(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='y' ) ||  
							(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='m' ) || 
							(decodedStroy.charAt(j-2)!='r'  &&  decodedStroy.charAt(j-1 )!='y' ) ||  
							(decodedStroy.charAt(j-2)!='h'  &&  decodedStroy.charAt(j-1 )!='t' ) ||  
							(decodedStroy.charAt(j-2)!='r'  &&  decodedStroy.charAt(j-1 )!='s' )    || 
							(decodedStroy.charAt(j-2)!='s'  &&  decodedStroy.charAt(j-1 )!='s' )  ||  
							(decodedStroy.charAt(j-2)!='o'  &&  decodedStroy.charAt(j-1 )!='m' )  ||  
							(decodedStroy.charAt(j-2)!='t'  &&  decodedStroy.charAt(j-1 )!='e' )    ||  
							(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='y' )|| 
							(decodedStroy.charAt(j-2)!='b'  &&  decodedStroy.charAt(j-1 )!='e' )||  
							(decodedStroy.charAt(j-2)!='u'  &&  decodedStroy.charAt(j-1 )!='r' ) ||
							(decodedStroy.charAt(j-2)!='m'  &&  decodedStroy.charAt(j-1 )!='y' )  ||  
							(decodedStroy.charAt(j-2)!='t'  &&  decodedStroy.charAt(j-1 )!='y' )  ||  
							(decodedStroy.charAt(j-2)!='t'  &&  decodedStroy.charAt(j-1 )!='s' )    ||  
							(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='t' )|| 
							(decodedStroy.charAt(j-2)!='s'  &&  decodedStroy.charAt(j-1 )!='o' )||  
							(decodedStroy.charAt(j-2)!='k'  &&  decodedStroy.charAt(j-1 )!='e' )||
							(decodedStroy.charAt(j-2)!='d'  &&  decodedStroy.charAt(j-1 )!='e' )  ||  
							(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='l' )  ||  
							(decodedStroy.charAt(j-2)!='i'  &&  decodedStroy.charAt(j-1 )!='r' )    ||  
							(decodedStroy.charAt(j-2)!='l'  &&  decodedStroy.charAt(j-1 )!='f' )|| 
							(decodedStroy.charAt(j-2)!='u'  &&  decodedStroy.charAt(j-1 )!='s' )||  
							(decodedStroy.charAt(j-2)!='d'  &&  decodedStroy.charAt(j-1 )!='s' )||
							(decodedStroy.charAt(j-2)!='h'  &&  decodedStroy.charAt(j-1 )!='o' )  ||  
							(decodedStroy.charAt(j-2)!='a'  &&  decodedStroy.charAt(j-1 )!='r' )  ||  
							(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='s' )    ||  
							(decodedStroy.charAt(j-2)!='e'  &&  decodedStroy.charAt(j-1 )!='e' )   ||  
							(decodedStroy.charAt(j-2)!='n'  &&  decodedStroy.charAt(j-1 )!='o' )    ||  
							(decodedStroy.charAt(j-2)!='r'  &&  decodedStroy.charAt(j-1 )!='d' )|| 
							(decodedStroy.charAt(j-2)!='w'  &&  decodedStroy.charAt(j-1 )!='n' )||  
							(decodedStroy.charAt(j-2)!='g'  &&  decodedStroy.charAt(j-1 )!='e' )||
							(decodedStroy.charAt(j-2)!='c'  &&  decodedStroy.charAt(j-1 )!='k' )  ||  
							(decodedStroy.charAt(j-2)!='d'  &&  decodedStroy.charAt(j-1 )!='o' )   )  
							 
				{
					temp.set_fitness ( temp.get_fitness ( )+ 100 );// ���� ���� �� ���� �� ������� ������ ������� 
				}
				
				
			}
		}
		
		
		// ����� �� ������ ������
		for (int  j = 3; j < decodedStroy.length(); j++) {
			if(decodedStroy.charAt(j )==' '    )
			{
				if( (decodedStroy.charAt(j-2) =='h'  &&  decodedStroy.charAt(j-1 )=='e' ) )
				{
					temp.set_fitness ( temp.get_fitness ( )- 10   );//������ ������ �� ��� ����� �� ������ ������
				}
				else if(		(decodedStroy.charAt(j-2)=='e'  &&  decodedStroy.charAt(j-1 )=='d' ) ||  
						(decodedStroy.charAt(j-2)=='n'  &&  decodedStroy.charAt(j-1 )=='d' ) || 
						(decodedStroy.charAt(j-2)=='n'  &&  decodedStroy.charAt(j-1 )=='g' )   
				)
				{
					temp.set_fitness ( temp.get_fitness ( )- 5 );//�����
				}
			}
		
	}
	
	
		
		
		
		
		
		double fitnessOfGen=temp.get_fitness ( );
		
		return fitnessOfGen;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void fitnessToPopulation ( )
	{

		for ( int i = 0 ; i < this._populationSize ; i ++ )
		{
			
			Gen temp=this._population.elementAt ( i );
			temp.set_fitness ( 0 );
			String decodedStroy=this.decodStoryToOriginalStory ( temp );
			//����� �� ��������� ��� ���
			
			if(temp.get_key ( ).get ( 'a' )==' ')
			{
				//System.out.println (decodStoryToOriginalStory(temp));
			//	System.out.println ();
			}
			
		 
			 
			
			temp.set_fitness(fitnessToGen(temp));
			
			
			
			
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 *  ����� �� ������ ������ ��� ����� �� ���
	 * @param gen
	 * @return
	 */
	private String decodStoryToOriginalStory(Gen gen)
	{
		HashMap < Character , Character > key = gen.get_key ( );
		String decodedStory=new String(this._encodedStory);
		
		char [ ] storyChar = decodedStory.toCharArray ( );
		for ( int i = 0 ; i < storyChar.length ; i ++ )
		{
			if(Character.isLetter ( storyChar[i] ) )
			{
				storyChar[i]=key.get ( storyChar[i] );
			}
			else
			{
				storyChar[i]=key.get ( ' ' );
			}
		}
		decodedStory=new String(storyChar);
		
		///�� ��� �� ������ ������
		return decodedStory;
		
	}




	private void initPopulation ( )  // ����� �������� ������ ������� 
	{
		Random seed = new Random();
		for ( int i = 0 ; i < _populationSize ; i ++ )//������ �� �� ����' �������� �� ��' ������ ����� ��� �� ���� �� ������� ��� �� �� ������ ��� ��� ����� �� ������ ������
		{
			Gen gen = new Gen();
			
		//	if(i==0)
			{////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//		gen.set_key(get_key( ));    ///  ����� ����� ����� ��� �����
		//		break;
			}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			boolean[]  per=new boolean[this._key.size ( )];
			char[] key=new char[this._key.size ( )];
			
			for ( int j = 0 ; j < per.length ; j ++ )
			{
				per[j]=false;
			}
			
			//������ �� ���� ��� ������ �� ��� ������� ���� ������� ��� ��� �� ���� ���� ���� �� ���� �� ���
			//�� �� ���� �� �� ���� ��� ����� �� ���� ������ ����� �� ��� ���� ��� ����� ���� ��� ���� ������ ����� ����
			for(int j=0 ; j<per.length ; j++)
			{
				
				int cur=seed.nextInt (this._key.size ( ) ) ;
				while (per[cur]!=false )
				{
					cur=seed.nextInt (this._key.size ( ) ) ;
				}
		 		if(cur==26)
		 		{
		 			key[j]=' ';
		 		}
		 		else
		 		{
		 			key[j]=( char ) (cur+'a');
		 		}
				per[cur] =true ; 		
			}
			if(key[0]==' ')
			{
				//System.out.println ();
			}
			gen.charToMap ( key );
			_population.add ( gen );
		
		}	
	}
	
	
	
	
	
	private String getStory ( String path )// ���� ������ ������� �� ���� )
	{
		File file=new File(path);
		
		StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        char[] buf = new char[1024];
        int numRead=0;
        try
		{
			while((numRead=reader.read(buf)) != -1){
			    String readData = String.valueOf(buf, 0, numRead);
			    fileData.append(readData);
			    buf = new char[1024];
			}
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
		{
			reader.close();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileContent= fileData.toString();
		fileContent=fileContent.replace ( "\n" , "" );
		fileContent=fileContent.replace ( "\r" , "" );
		
		
		//����� ����� �� ���� ����� �� ������ �������
		//�� ���� ������ �� �� ���� ���� ���� ��� ����� ������� �� �������
		for ( int i = 0 ; i < fileContent.length() ; i ++ )
		{
			if(!Character.isLetter ( fileContent.charAt ( i ) ) )//��� ��� ������ ��� �� ���
			{
				if(fileContent.charAt ( i ) !=' ')//�� ��� ��� �� �� ����
				{
//					String begin=fileContent.substring ( 0 , i );//�� ������ ������� �� ���� �����
//					String end=fileContent.substring ( i+1 );//��� �������
//					fileContent=begin+end;
					fileContent=fileContent.replace ( fileContent.charAt ( i ) , ' ' );
					i=0;
				}
			}
		}
		fileContent=fileContent.replace ( "  " , " " );
		//������ ������ ������ ������� �����
		fileContent=fileContent.toLowerCase ( );
		
		return fileContent;
	}
	
	
	
	private String encodeString(String story)
	{
			    
	    HashMap < Character , Character > key = this._key;
		String codedStory=new String(story);
		
		char [ ] storyChar = codedStory.toCharArray ( );
		for ( int i = 0 ; i < storyChar.length ; i ++ )
		{
			try{
				if(Character.isLetter ( storyChar[i] ) )
				{
					if(!key.containsKey ( storyChar[i] ))
					{
						//System.out.println (storyChar[i]);
					}
					storyChar[i]=key.get ( storyChar[i] );
				}
				else//�� �� �� ��� �� ����
				{
					storyChar[i]=key.get ( ' ' );
				}
			}
			catch(Exception e)
			{
				//System.out.println ();
			}
		}

		codedStory=new String(storyChar);
	    return codedStory;
	}
	
	
	

	private void nextPopulationRolate ( )     //  mate
	{
		_nextPopulation = new Vector<Gen>() ;//������ ������� �� ����� ���� ������ �� �� ��������� ����
		
		for ( int i = 0 ; i < _populationSize/10 ; i ++ )//elitisem
		{
			_nextPopulation.add ( _population.elementAt ( i ) );
		}
		
		Random seed = new Random();
		
		
		
		
		for ( int i = (_populationSize/10) ; i < _populationSize ; i ++ )
		{
			
			double newGenfitness;
			double parent1Fitness;
			double parent2Fitness;
			Gen newGen = new Gen();
			do{
				
				 
				//���� 2 ����� �50 ������� ������ ������� ������� ���� ������ ����� ������
				int index=seed.nextInt(Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( ));
				int i1=seed.nextInt(( int ) Math.floor( _populationSize/2 ) );/////////////////////////////////////////////////
				int i2=seed.nextInt(( int ) Math.floor( _populationSize/2 ) );////////////////////////////////////////////////
				
				
			/*	
				//������ �� �� ��������
				double totalFitness=0;//������ �� �� �������� 
				for ( int j = 0 ; j < _population.size() ; j ++ )
				{
					totalFitness=totalFitness+_population.elementAt ( j ).get_fitness ( );
				}
				
				int[] rolate=new int[( int ) Math.ceil( totalFitness )];
				
				//������ ���� ����� �� ������ ������� ���� �� ���� ����� ���� ���� ���� ����� �� ������ ����� ��� �� ������� ���
				int counter=0;
				for (int j=0; j<_populationSize;j++)
				{
					for (int g=0;g< Math.floor(_population.elementAt ( j ).get_fitness ( ));g++)
					{
						rolate[counter]=j;
						counter++;
					}
				} 
				int i1=0;
				int i2=0;
				while(i1==i2)//������ �� 2 ��������� ������ ������ ��������� �� ������ 
				{
					int index1=seed.nextInt(( int ) Math.floor( totalFitness ) );
					int index2=seed.nextInt(( int ) Math.floor( totalFitness ) );
					i1 = rolate[index1];   
					i2 = rolate[index2];   
				}
				*/
				//2 ����� �������
			//	Gen newGen = new Gen();
				Gen genP1=_population.elementAt ( i1 );
				Gen genP2=_population.elementAt ( i2 );
				
	
				//���� ����� ���� �� ������
				char exchangeLetter='a';//�����
				if(index==Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( )-1)//�� ���� ����� ���� �� ������ ��� ����
				{
					exchangeLetter=' ';
				}
				else
				{
					exchangeLetter=(char)(index + 'a');
				}
				
				//����� �������� ���� �"� ����� ���� ����
				char char1=genP1.get_key ( ).get ( exchangeLetter );//���� ������ ����� ��� ����� ������
				char char2=genP2.get_key ( ).get ( exchangeLetter );//���� ������ ����� ��� ����� ����
	
				HashMap < Character , Character > keyOfChild = new HashMap < Character , Character >(genP1.get_key ( ));//����� �� ����
				char key='a';
				if(char1!=char2)//������ �� ����� ��� ���� ��� 2 ������
				{
					Set entries2 =keyOfChild.entrySet();//���� �� ���� �� ����� ������ ������ �� �� ����� �� char2 
					Iterator it2 = entries2.iterator();
					while (it2.hasNext()) {
						Map.Entry entry = (Map.Entry) it2.next();
						if(((Character)entry.getValue ( ))==char2)
						{
							key=(Character)entry.getKey ( );
						}
					}
					
					//����� ��������
					keyOfChild.put ( exchangeLetter , char2 );
					keyOfChild.put ( key , char1 );	
				}
				
				
				
				newGen.set_key ( keyOfChild );    //  ���� �����
				newGenfitness=fitnessToGen(newGen);
				parent1Fitness=fitnessToGen(genP1);
				parent2Fitness=fitnessToGen(genP2);
				
			}while( newGenfitness> parent1Fitness     ||  newGenfitness > parent2Fitness    ) ;    // �� ���� ���� ��� ���� ������
			
				
			
			Gen newGen2=new Gen();
			
			///����� ��� ����� ������ �� ����� 5 �������
			//�������� �� 25%
			int percent=seed.nextInt ( 100 );
			
			Boolean fitnessImproved=false;
			
			if(percent<_percentageForMutation)  // �� ����� ����� ������ ����� ������ ������ ����� �� ������ �� ���
			{
				int counterOfMutationTries=0;
			//	Boolean mutateSuccess=false;
				fitnessImproved=false;
				do{
					counterOfMutationTries++;
					newGen2=Mutate(newGen);
					newGen2.set_fitness(fitnessToGen(newGen2)) ;
					if (counterOfMutationTries >15  )
					{
						break;
					}
					if (newGen.get_fitness() >=  newGen2.get_fitness() )
					{
						fitnessImproved=true;
					}
				}while( ( newGen.get_fitness() <=  newGen2.get_fitness() ));
				
				
			}
			
			if(fitnessImproved){
				_nextPopulation.add ( newGen2 );
			}
			else
			{
				_nextPopulation.add ( newGen );
			}	
			
		}
		
		
		
		//����� �� ��������� ���� ����� ��������� ������
		_population= new Vector<Gen>() ;
		for ( int i = 0 ; i < _nextPopulation.size() ; i ++ )
		{
			Gen newGen=new Gen(_nextPopulation.elementAt ( i ));
			_population.add ( newGen );
		}
	}
	


/**
 * ���� 2 ������ ��������� ������ ������
 * @param newgen
 */
	private Gen Mutate ( Gen newgen )
	{
		HashMap < Character , Character > map = newgen.get_key ( );
		Random seed = new Random();
		
		Gen temp=new Gen();
		temp.set_fitness(newgen.get_fitness());
		temp.set_key(newgen.get_key()) ;  
		
		int ind1=seed.nextInt ( Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( ));
		int ind2=seed.nextInt ( Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( ));
		
		//����� 2 ������� ������ �� �������� ����
		char key1='a';
		if(ind1==Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( )-1)//����� ���� ����
		{
			key1=' ';
		}
		else
		{
			key1=(char)('a' + ind1);
		}
		
		char key2='a';
		if(ind2==Frequency.getInstance ( ).get_monogramFrequencies ( ).size ( )-1)
		{
			key2=' ';
		}
		else
		{
			key2=(char)('a' + ind2);
		}
		
		if(key1!=key2)//�� ������� �����
		{
			char val1=map.get ( key1 );
			char val2=map.get ( key2 );
			
			map.put ( key1 , val2 );
			map.put ( key2 , val1 );
		}
		temp.set_key ( map );
		
		return temp;
	}





	public Vector < Gen > get_nextPopulation ( )
	{
		return _nextPopulation;
	}





	
	public void set_nextPopulation ( Vector < Gen > population )
	{
		_nextPopulation = population;
	}





	public Vector < Gen > get_population ( )
	{
		return _population;
	}




	
	public void set_population ( Vector < Gen > _population )
	{
		this._population = _population;
	}




	
	public int get_populationSize ( )
	{
		return _populationSize;
	}




	
	public void set_populationSize ( int size )
	{
		_populationSize = size;
	}





















	public HashMap<Character, Character> get_key() {
		return _key;
	}





















	public void set_key(HashMap<Character, Character> _key) {
		this._key = _key;
	}

	
}


