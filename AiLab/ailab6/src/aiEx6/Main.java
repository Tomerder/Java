/**
 * Ai EX6
 * Orel shai - 052988912	
 * Tomer dery - 060628914
 */

package aiEx6;


import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Main {

	private Vector < AlgGen > _algPopulation	= new Vector < AlgGen > ( );			// האוכלוסיה שלנו של האלגורתמים
	private Vector < AlgGen > _algNextPopulation	= new Vector < AlgGen > ( );			// הדור הבא
	
	private Vector < VectorGen > _vectorPopulation	= new Vector < VectorGen > ( );			//  האוכלוסיה שלנו של הווקטורים עם המספרים
	private Vector < VectorGen > _vectorNextPopulation	= new Vector < VectorGen > ( );			// הדור הבא
	
	private int	_algpopulationSize	=250;
	private int	_vectorpopulationSize	=250;
	private int	_percentageForMutation	= 25;
	private int	_numberOfIterations	= 10000;
	private int	_allowNumberOfIterationsWithNoBetterFitnees	= _numberOfIterations  ;			// מספר האיטרציות שמותר שיהיה אותו הפיטנס
	
	private int MAX_NUMBER_OF_PAIRS=120;//הגודל המקסימלי של של זוגות שיכולים להיות באלגוריתם
	private int MIN_NUMBER_OF_PAIRS=30;
	
	private int _vectorSize=16;//הגודל של הווקטור של המספרים שנגריל כל פעם
	private int MAX_INT_SIZE=100;//המספר הגדול ביותר שנגריל - בחרנו מספר זה בכוונה כדי לאפשר מצב שבו יוגרל אותו מספר פעמיים
	private int best=0;
	
	private boolean _memeticImprovmentFlag=true;
	
	private int COUNTER_OF_ITERATIONS_WITHOUT_VECTORPOPULATION_IMPROVEMENT= 2  ;//כל 5 איטרציות נפתח את אוכלוסית הווקטורים
	
	
	
	public static void main(String[] args) {
		Main main=new Main();
	}
	
	public Main()
	{
		execute();
	}

	
	private void execute() {
		double bestLegalFitness=Integer.MAX_VALUE;//הפיטנס החוקי הכי טוב
		int numberOfIterationsWithNoBetterFitness=0;//מס' האיטרציות עם אותו הפיטנס
		int counter=0;
		
		initPopulationAlg ( );
		initPopulationVector ( );

		
		  
		 //הגן של הבבל סורט  
		for ( int q = 0 ; q < 1 ; q ++ )
		{
			AlgGen ourgen=new AlgGen();
			Vector<Integer> _sortingNetwork=new Vector<Integer> ();
				for ( int i = 0 ; i < _vectorSize ; i ++ )
				{
					for ( int j = 0 ; j < _vectorSize-i-1 ; j ++ )
					{
						_sortingNetwork.add ( j );
						_sortingNetwork.add ( j+1 );
					}
				}
			ourgen.set_sortingNetwork ( _sortingNetwork );
			_algPopulation.add ( ourgen );
		}
		
		

		while ( numberOfIterationsWithNoBetterFitness < _allowNumberOfIterationsWithNoBetterFitnees ) // כל עוד לא התכנסנו
		{
			System.out.println ();
			for ( int i = 0 ; i < _algpopulationSize ; i ++ )//ניתן פיטנסס לכל גן מ 2 האוכלוסיות
			{
				algFitness(_algPopulation.elementAt ( i ));
			}
			for ( int i = 0 ; i < _vectorpopulationSize ; i ++ )//ניתן פיטנסס לכל גן מ 2 האוכלוסיות
			{
				vectorFitness(_vectorPopulation.elementAt ( i ));
			}
			 

			/**
			 * מיון של אוכלוסיית הווקטורים
			   פיטנס גבוה זה טוב
			 */
			// ///////sort the population by fitness
			int n = _vectorpopulationSize;
			for ( int pass = 0 ; pass < n - 1 ; pass ++ )
			{ // count how many times
				// This next loop becomes shorter and shorter
				Boolean exchangeFlag = false;
				for ( int i = 0 ; i < n - 1 ; i ++ )
				{
					if ( _vectorPopulation.elementAt ( i ).get_fitness ( ) < _vectorPopulation.elementAt ( i + 1 ).get_fitness ( ) )
					{
						// exchange elements
						// Gen temp=m.get_population ( ).elementAt ( i );
						 VectorGen xi = _vectorPopulation.elementAt ( i );
						 VectorGen xi1 = _vectorPopulation.elementAt ( i + 1 );
						_vectorPopulation.remove ( i );
						_vectorPopulation.add ( i , xi1 );
						_vectorPopulation.remove ( i + 1 );
						_vectorPopulation.add ( i + 1 , xi );
						exchangeFlag = true;
					}
				}
				if ( ! exchangeFlag )
					break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
			}
			
			
			/**
			 * מיון של אוכלוסיית האלגוריתמים
				פיטנס נמוך זה טוב
			 */
			// ///////sort the population by fitness
			for ( int pass = 0 ; pass < n - 1 ; pass ++ )
			{ // count how many times
				// This next loop becomes shorter and shorter
				Boolean exchangeFlag = false;
				for ( int i = 0 ; i < n - 1 ; i ++ )
				{
					if ( _algPopulation.elementAt ( i ).get_fitness ( ) > _algPopulation.elementAt ( i + 1 ).get_fitness ( ) )
					{
						// exchange elements
						// Gen temp=m.get_population ( ).elementAt ( i );
						 AlgGen xi = _algPopulation.elementAt ( i );
						 AlgGen xi1 = _algPopulation.elementAt ( i + 1 );
						 _algPopulation.remove ( i );
						 _algPopulation.add ( i , xi1 );
						 _algPopulation.remove ( i + 1 );
						 _algPopulation.add ( i + 1 , xi );
						exchangeFlag = true;
					}
				}
				if ( ! exchangeFlag )
					break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
			}
			
			
			/**
			 * האינדקס באוכלוסיית האלגוריתמים של האלגוריתם הכי טוב בינתיים - בשאיפה ינדקס 0
			 */
			int maxSorting=0;
			int maxIndex=0;
			for (int i = 0; i < _algPopulation.size(); i++) {
				if(_algPopulation.elementAt ( i ).get_numberOfTrueSorting()  >  maxSorting )
				{
					maxSorting=_algPopulation.elementAt ( i ).get_numberOfTrueSorting();
					maxIndex=i;
				}
					
					
			}
			
			
			
			
			System.out.println ( counter + " - best algorithem fitness is:" + _algPopulation.elementAt ( 0 ).get_fitness ( )  );
			//			System.out.println ( counter + " - Number Of Most Successful Sorting Is at position : " + maxIndex  + "  - >  "   + _algPopulation.elementAt ( maxIndex ).get_numberOfTrueSorting() 
			//					+   "          w1: "+ _algPopulation.elementAt ( maxIndex ).get_w1()  +  "  w2:   "+ _algPopulation.elementAt ( maxIndex ).get_w2() +  "  w3: "+ _algPopulation.elementAt ( maxIndex ).get_w3() );
			
			
			System.out.println ( counter + " - Number Of Most Successful Sorting Is at position : " + maxIndex  + "  - >  "   + _algPopulation.elementAt ( maxIndex ).get_numberOfTrueSorting() );
			
			System.out.println ( counter + " - Number Of Successful Sorting of 0 : " + _algPopulation.elementAt ( 0 ).get_numberOfTrueSorting()  + "              - length of sorting : " + _algPopulation.elementAt ( 0 ).get_sortingNetwork ( ).size ( )/2       + "               - sorting network  : " + _algPopulation.elementAt ( 0 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 1 : " + _algPopulation.elementAt ( 1 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 1 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 1 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 2 : " + _algPopulation.elementAt ( 2 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 2 ).get_sortingNetwork ( ).size ( )/2     + "               - sorting network  : " + _algPopulation.elementAt ( 2 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 3 : " + _algPopulation.elementAt ( 3 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 3 ).get_sortingNetwork ( ).size ( )/2     + "               - sorting network  : " + _algPopulation.elementAt ( 3 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 4 : " + _algPopulation.elementAt ( 4 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 4 ).get_sortingNetwork ( ).size ( )/2     + "               - sorting network  : " + _algPopulation.elementAt ( 4 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 5 : " + _algPopulation.elementAt ( 5 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 5 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 5 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 10 : " + _algPopulation.elementAt ( 10 ).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 10 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 10 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 11 : " + _algPopulation.elementAt ( 11 ).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 11 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 11 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 12 : " + _algPopulation.elementAt ( 12).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 12 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 12 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 13 : " + _algPopulation.elementAt ( 13 ).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 13 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 13 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 14 : " + _algPopulation.elementAt ( 14 ).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 14 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 14 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting 0f 15 : " + _algPopulation.elementAt ( 15 ).get_numberOfTrueSorting()   + "             - length of sorting : " + _algPopulation.elementAt ( 15 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 15 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 25: " + _algPopulation.elementAt ( 25 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 25 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 25 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 26: " + _algPopulation.elementAt ( 26 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 26 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 26 ).get_sortingNetwork ( ).toString());
			System.out.println ( counter + " - Number Of Successful Sorting of 50: " + _algPopulation.elementAt ( 50 ).get_numberOfTrueSorting()   + "              - length of sorting : " + _algPopulation.elementAt ( 50 ).get_sortingNetwork ( ).size ( )/2    + "               - sorting network  : " + _algPopulation.elementAt ( 50 ).get_sortingNetwork ( ).toString());
			System.out.println (" "  );
	//////////////////////////////////////////////////////////////////////////////		
			// מעבר לדור הבא
			algNextPopulation( );
			
			
			// כל קי דורות הוקטורים יתפתחו לשלב הבא
			if ( counter % COUNTER_OF_ITERATIONS_WITHOUT_VECTORPOPULATION_IMPROVEMENT  == 0 )   //  אוכלוסיית הוקטורים תשתפר כל קי איטרציות - 5 
				vectorNextPopulation( );
			
/////////////////////////////////////////////////////////////////////////////////////			

			/**
			 * בדיקה האם הפיטנס הטוב ביותר הנוכחי יותר טוב מהפיטנס הכי טוב עד עכשיו , ובנוסף האם חרגנו מהמספר המקסימאלי של חזרות עם אותו הפיטנס - בלי שיפור
			 */
			if ( bestLegalFitness > _algPopulation.elementAt ( 0 ).get_fitness ( ) )// הפיטנס שהיה עד לדור הנוכחי פחות טוב מהפיטנס של הדור הנוכחי
			{
				bestLegalFitness = _algPopulation.elementAt ( 0 ).get_fitness ( );
				numberOfIterationsWithNoBetterFitness = 0;
			}
			else
			{
				numberOfIterationsWithNoBetterFitness ++ ;
				if ( numberOfIterationsWithNoBetterFitness >= _allowNumberOfIterationsWithNoBetterFitnees )
				{
					System.out.println ( "The result was converged " );
					break;
				}
			}
			
			for ( int i = 0 ; i < _algPopulation.size ( ) ; i ++ )
			{
				if(_algPopulation.elementAt ( i ).get_sortingNetwork ( ).size ( )/2<63   &&   _algPopulation.elementAt ( i ).get_numberOfTrueSorting ( )==250 )
				{
					System.out.println ('\n'  +  '\n'   +  '\n'   +   '\n'+  '\n'   +  '\n'   +   '\n');
					System.out.println (_algPopulation.elementAt ( i ).get_sortingNetwork ( ).size ( )/2);
					break;
				}
			}
			
			counter ++ ;
		}
		System.out.println ( "finish" );
		
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * נותן פיטנס לכל אלגוריתם על ידי כך שעוברים על האוכלוסייה של כל הווקטורים עם המספרים ומפעילים את האלגוריתם אליהם
	 * ובודקים האם בסופו של דבר ה האלגוריתם הצליח למיין אותם.
	 * מספר מאפיינים לטיב האלגוריתם:
	 * מספר הווקטורים שהאלגרותים הצליח למיין , מבין הווקטורים שלא הצלחנו למיין - כמה מספרים לא היו במקום
	 * @param currentGen
	 */
	
	//פיטנס נמוך הוא טוב - ננקד עבור כל מספר בוקטור לאחר המיון ++ עבור כל מספר קטן ממנו שנמצא אחריו
	private void algFitness ( AlgGen currentGen)
	{
		//int numbersOfMissedNumbers=0;
		int numberOfSortedGens=0;//מספר הווקטורים שמצליחים למיין - גבווה יותר זה יותר טוב
	
		int totalDistancesBetweenMisses=0;
		
		for ( int i = 0 ; i < _vectorPopulation.size ( ) ; i ++ )//מעבר על כל הווקטורים
		{
			Vector < Integer > currentVector=new Vector<Integer>();
			for ( int j = 0 ; j < _vectorPopulation.elementAt ( i ).get_numbers ( ).size ( ) ; j ++ )
			{
				currentVector.add ( _vectorPopulation.elementAt ( i ).get_numbers ( ).elementAt ( j ) );
			}
			//Vector < Integer > currentVector = (Vector < Integer >)_vectorPopulation.elementAt ( i ).get_numbers ( ).clone ( );//הווקטור שמנסים למיין
			Vector < Integer > sortedVector = _vectorPopulation.elementAt ( i ).get_sortedNumbers ( );//הווקטור הממויין
			
			for ( int j = 0 ; j < currentGen.get_sortingNetwork ( ).size ( ) ; j=j+2 )//מעבר על הרשת מיון בקפיצות של שתיים - צמד כל פעם
			{
				//2 האינדקסים שעל פיהם נחליף
				int ind1=currentGen.get_sortingNetwork ( ).elementAt ( j );
				int ind2=currentGen.get_sortingNetwork ( ).elementAt ( j+1 );
				
				//2 הערכים שבאינדקסים
				Integer value1=currentVector.elementAt ( ind1 );
				Integer value2=currentVector.elementAt ( ind2 );
				
				
				//SWITCH
				if( ind1 < ind2)
				{
					if(value1>value2)
					{
						currentVector.set ( ind1 , value2 );
						currentVector.set ( ind2 , value1 );
					}
				}
				else if( ind2 < ind1)
				{
					if(value2>value1)
					{
						currentVector.set ( ind1 , value2 );
						currentVector.set ( ind2 , value1 );
					}
				}
			}
			
			//בדיקה האם האלגוריתם מיין את הווקטור
			boolean success=true;//האם הצלחנו למיין
			//numbersOfMissedNumbers=0;//מס' המספרים שלא במקום לפי האלגויתם הממויין
			for ( int j = 0 ; j < currentVector.size ( ) ; j ++ )
			{
				if(!currentVector.elementAt ( j ).equals ( sortedVector.elementAt ( j ) ))
				{
				//	numbersOfMissedNumbers++;
					//הצאה לשיפור של שי  מציאת מס' המקומות שבין המספר שבודקים בווקטור הלא ממויין לזה בווקטור הממויין
					int j2 = 0;
					for ( j2 = 0; j2 < sortedVector.size()  ; j2++) {
						if (currentVector.elementAt ( j ).equals ( sortedVector.elementAt ( j2 ) ) ) 
						{
							break;    // נמצא את אינדקס האיבר בוקטור בממויין 
						}
					}
					totalDistancesBetweenMisses = totalDistancesBetweenMisses +  ( Math.abs (  j   - j2 )) ;  //  נוסיף את המרחק בין האינדקסים של האיברים הזהים ב 2 הוקטורים
					//totalDistancesBetweenMisses = totalDistancesBetweenMisses +  1;
				
					success=false;
				}
			}
			if(success)
			{
				numberOfSortedGens++;
			}
		}

		currentGen.set_numberOfTrueSorting(numberOfSortedGens);																															///////////		
		currentGen.set_fitness (( _vectorPopulation.size ( )  -   numberOfSortedGens) *1000       /*+ numbersOfMissedNumbers * currentGen.get_w2()*/        + totalDistancesBetweenMisses  /* *10 */  +   currentGen.get_sortingNetwork ( ).size ( ) *  50  );//נותן פיטנס
																																														///////////
		////////////////////////////////////////////////////////   הפיטנס זה כמה וקטורים לא הצלחנו למיין -  פיטנס נמוך זה טוב
	}

	
	
	
	/**
	 * נותן פיטנס לכל ווקטור על ידי מעבר על כל האלגוריתמים ובדיקה האם אלגוריתם כלשהו הצליח למיין אותו
	 * @param currentGen
	 */
	private void vectorFitness ( VectorGen currentGen)
	{
		int numberOfNotSortedGens=0;//מספר האלגוריתמים שלא מצליחים למיין את הווקטור הזה - ככל שערך זה יותר גבוהה זה יותר טוב
		for ( int i = 0 ; i < _algPopulation.size ( ) ; i ++ )//מעבר על כל האלגוריתמים
		{
			//Vector<Integer> copy=(Vector<Integer>)currentGen.get_numbers ( ).clone ( );//העתק של הווקטור שרוצים למיין
			
			Vector < Integer > copy=new Vector<Integer>();
			for ( int j = 0 ; j < currentGen.get_numbers ( ).size ( ) ; j ++ )
			{
				copy.add ( currentGen.get_numbers ( ).elementAt ( j ) );
			}

			
			AlgGen temp = _algPopulation.elementAt ( i );
			for ( int j = 0 ; j < temp.get_sortingNetwork ( ).size ( ) ; j=j+2 )//מעבר על הרשת מיון בקפיצות של שתיים - צמד כל פעם
			{
				//2 האינדקסים שעל פיהם נחליף
				int ind1=temp.get_sortingNetwork ( ).elementAt ( j );
				int ind2=temp.get_sortingNetwork ( ).elementAt ( j+1 );
				
				//2 הערכים שבאינדקסים
				Integer value1=copy.elementAt ( ind1 );
				Integer value2=copy.elementAt ( ind2 );
				
				//SWITCH
				copy.set ( ind1 , value2 );
				copy.set ( ind2 , value1 );
			}
			//בדיקה האם האלגוריתם מיין את הווקטור
			for ( int j = 0 ; j < copy.size ( ) ; j ++ )
			{
				if(copy.size()<=j || currentGen.get_sortedNumbers ( ).size ( )<=j  )
				{
					System.out.println ();
				}
				if(!copy.elementAt ( j ).equals ( currentGen.get_sortedNumbers ( ).elementAt ( j ) ))
				{
					numberOfNotSortedGens++;
					break;
				}
			}
		}
		currentGen.set_fitness ( numberOfNotSortedGens*10 );//נותן פיטנס
		
	}
	
	
	
	
	
	
	/**
	 * יצירת האוכלוסיה הראשונית של הווקטורים
	 * עבור כל גן  נגריל 16 מספרים עם ערכים בין 0-100
	 * נאפשר מצב שבו בגן מסויים אותו מספר נבחר פעמיים או יותר
	 */
	private void  initPopulationVector()
	{
		Random seed = new Random();
		for ( int i = 0 ; i < _vectorpopulationSize  ; i ++ )//יצירת אן גנים חדשים
		{
			VectorGen gen = new VectorGen();
			
			for ( int j = 0 ; j < _vectorSize ; j ++ )
			{
				int newNumber=seed.nextInt ( MAX_INT_SIZE );
				(gen.get_numbers ( )).add ( newNumber );
			}
			
			//gen.set_sortedNumbers ( (Vector<Integer>)gen.get_numbers ( ).clone ( ) ); //השמה של עותק של הווקטור המקורי והלא ממויין - את העותק החדש אנחנו נמיין
			for ( int j = 0 ; j < gen.get_numbers ( ).size ( ) ; j ++ )
			{
				gen.get_sortedNumbers ( ).add ( gen.get_numbers ( ).elementAt ( j ) );
			}
			
			//מציאת הווקטור הממויין
			// ///////sort the population by fitness
			int n = _vectorSize;
			for ( int pass = 0 ; pass < n - 1 ; pass ++ )
			{ // count how many times
				// This next loop becomes shorter and shorter
				Boolean exchangeFlag = false;
				for ( int j = 0 ; j < n - 1 ; j ++ )
				{
					if ( gen.get_sortedNumbers ( ).elementAt ( j )  > gen.get_sortedNumbers ( ).elementAt ( j+1 ) )
					{
						// exchange elements
						// Gen temp=m.get_population ( ).elementAt ( i );
						Integer xi = gen.get_sortedNumbers ( ).elementAt ( j );
						Integer xi1 = gen.get_sortedNumbers ( ).elementAt ( j + 1 );
						gen.get_sortedNumbers ( ).remove ( j );
						gen.get_sortedNumbers ( ).add ( j , xi1 );
						gen.get_sortedNumbers ( ).remove ( j + 1 );
						gen.get_sortedNumbers ( ).add ( j + 1 , xi );
						exchangeFlag = true;
					}
				}
				if ( ! exchangeFlag )
					break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
			}
			
			_vectorPopulation.add ( gen );
		}
	}
	
	
	
	
	
	/**
	 * יצירת האוכלוסיה הראשונית של האלגוריתמים
	 */
	private void initPopulationAlg() {
		Random seed = new Random();
		for ( int i = 0 ; i < _algpopulationSize    -1  ; i ++ )//יצירת אן גנים חדשים
		{
			AlgGen gen = new AlgGen();
			int numberOfParis=seed.nextInt ( MAX_NUMBER_OF_PAIRS-MIN_NUMBER_OF_PAIRS+1 )+MIN_NUMBER_OF_PAIRS;//מגריל מספר בין 16-100 שמהווה את מספר הצמדים באלגוריתם זה
			
			for ( int j = 0 ; j < numberOfParis ; j ++ )
			{
				//נדאג לכך ש2 האינדקסים לא יהיו שווים
				// 5<->5

				int ind1=0;
				int ind2=0;
				
				while(ind1==ind2) 
				{
					ind1=seed.nextInt ( _vectorSize );
					ind2=seed.nextInt ( _vectorSize );
				}
				(gen.get_sortingNetwork ( )).add ( ind1 );
				(gen.get_sortingNetwork ( )).add ( ind2 );
				
			}
			_algPopulation.add ( gen );
		}
	}
	
	
	
	
	
	/**
	 * זיווג של הווקטורים
	 * נבחר 2 הורים , נבחר אינדקס ונעתיק מהורה 1 עד האינדקס ומהורה 2 מהאינדקס
	 */
	private void vectorNextPopulation()
	{
		_vectorNextPopulation = new Vector < VectorGen > ( );// איתחול הווקטור כך שיכיל בסוף התהליך רק את האוכלוסיה הבאה
		for ( int i = 0 ; i < _vectorpopulationSize / 10 ; i ++ )// elitisem
		{
			_vectorNextPopulation.add ( _vectorPopulation.elementAt ( i ) );
		}
		Random seed = new Random ( );
		for ( int i = ( _vectorpopulationSize / 10 ) ; i < _vectorpopulationSize ; i ++ )
		{
			double newGenfitness;
			double parent1Fitness;
			double parent2Fitness;
			VectorGen newGen = new VectorGen ( );
			int numberOftries=0;
			do
			{
				numberOftries++;
				// נבחר 2 הורים מ50 האחוזים הטוביפ הנותרים ואינדקס בתוך הצביעה שנאחד ביניהם
				int i1 = seed.nextInt ( ( int ) Math.floor ( _vectorpopulationSize / 2 ) );// ///////////////////////////////////////////////
				int i2 = seed.nextInt ( ( int ) Math.floor ( _vectorpopulationSize / 2 ) );// //////////////////////////////////////////////
				// 2 הגנים שהגרלנו
				VectorGen genP1 = _vectorPopulation.elementAt ( i1 );
				VectorGen genP2 = _vectorPopulation.elementAt ( i2 );
				
				int index=seed.nextInt ( _vectorSize );//האינדקס שלפיו נעתיק
				
				Vector<Integer> newVector=new Vector<Integer> ();
				for ( int j = 0 ; j < index ; j ++ )
				{
					newVector.add ( genP1.get_numbers ( ).elementAt ( j ) );
				}
				
				for ( int j = index ; j < _vectorSize ; j ++ )
				{
					newVector.add ( genP2.get_numbers ( ).elementAt ( j ) );
				}
				
				//מיון
				//Vector<Integer> sorted=(Vector<Integer>)newVector.clone ( );
				Vector<Integer> sorted=new Vector<Integer>();
				
				for ( int j = 0 ; j < newVector.size ( ) ; j ++ )
				{
					sorted.add ( newVector.elementAt ( j ) );
				}
				
				//מציאת הווקטור הממויין
				// ///////sort the population by fitness
				int n = _vectorSize;
				for ( int pass = 0 ; pass < n - 1 ; pass ++ )
				{ // count how many times
					// This next loop becomes shorter and shorter
					Boolean exchangeFlag = false;
					for ( int j = 0 ; j < n - 1 ; j ++ )
					{
						if ( sorted.elementAt ( j )  > sorted.elementAt ( j+1 ) )
						{
							// exchange elements
							// Gen temp=m.get_population ( ).elementAt ( i );
							Integer xi = sorted.elementAt ( j );
							Integer xi1 = sorted.elementAt ( j + 1 );
							sorted.remove ( j );
							sorted.add ( j , xi1 );
							sorted.remove ( j + 1 );
							sorted.add ( j + 1 , xi );
							exchangeFlag = true;
						}
					}
					if ( ! exchangeFlag )
						break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
				}
				
				parent1Fitness=genP1.get_fitness ( );
				parent2Fitness=genP2.get_fitness ( );
				newGen.set_numbers ( newVector );
				newGen.set_sortedNumbers ( sorted );
				vectorFitness ( newGen );
				newGenfitness=newGen.get_fitness ( );
			}
			while ( newGenfitness < parent1Fitness && newGenfitness < parent2Fitness &&  numberOftries<2); // עד שהבן יהיה טוב יותר מהוריו או שלא הצלחנו ליצור בן יותר טוב
			
			
			
			VectorGen newGen2 = new VectorGen ( newGen );//יוצרים עותק של הגן החדש
			// /הגרלה האם עושים מוטציה עם סלקשן 5 נסיונות
			// בהסתברות של 25%
			int percent = seed.nextInt ( 100 );
			Boolean fitnessImproved = false;
			if ( percent < _percentageForMutation ) // אם הוגרל לעבור מוטציה נעבור מוטציה שבהכרח משפרת את הפיטנס של הגן
			{
				int counterOfMutationTries = 0;
				fitnessImproved = false;
				do
				{
					counterOfMutationTries ++ ;
					newGen2 = vectorMutate ( newGen2 );
					vectorFitness ( newGen2 ) ;
					if ( counterOfMutationTries > 2 )
					{
						break;
					}
					if ( newGen.get_fitness ( ) <= newGen2.get_fitness ( ) )
					{
						fitnessImproved = true;
					}
				}
				while ( ( newGen.get_fitness ( ) >= newGen2.get_fitness ( ) ) );
			}
			if ( fitnessImproved )
			{
				_vectorNextPopulation.add ( newGen2 );
			}
			else
			{
				_vectorNextPopulation.add ( newGen );
			}
		}
		// העתקה של האוכלוסיה הבאה במקום האוכלוסיה הנוכית
		_vectorPopulation = new Vector < VectorGen > ( );
		for ( int i = 0 ; i < _vectorNextPopulation.size ( ) ; i ++ )
		{
			VectorGen newGen = new VectorGen ( _vectorNextPopulation.elementAt ( i ) );
			_vectorPopulation.add ( newGen );
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * algorithms mate
	 * 
	 * ניבחר באופן רנדומאלי 2 הורים
	 * כעת נעבור על הווקטור של 2 ההורים וכל  פעם ניקח זוג מהורה אחד ונבדוק האם הוא נמצא בהורה השני - אם הוא נמצא נוסיף לווקטור 1 ואם לא נוסיף לווקטור 2
	 * כעת נגריל את גודל הילד - שיהיה לכל היותר הגודל של ההורה הגדול ביותר
	 * כעת נעבור ונעתיק כל פעם זוג מווקטור 1 וזוג מווקטור 2עד שנמצה את הגודל של הילד
	 */
	private void algNextPopulation ( ) // mate
	{
		_algNextPopulation = new Vector < AlgGen > ( );// איתחול הווקטור כך שיכיל בסוף התהליך רק את האוכלוסיה הבאה
		for ( int i = 0 ; i < _algpopulationSize / 5 ; i ++ )// elitisem
		{
			
			AlgGen beforMemeticImprovement = _algPopulation.elementAt ( i );
			AlgGen afterMemeticImp=MemeticImprovment ( beforMemeticImprovement );		
			 
			
			/*	
			AlgGen afterMemeticImp=MemeticImprovment ( beforMemeticImprovement );
		
			for (int j = 0; j < 5; j++) 
			{
				afterMemeticImp=MemeticImprovment ( afterMemeticImp );///////////////////////////////////////////
				if (afterMemeticImp.get_numberOfTrueSorting()< _vectorpopulationSize )
				{
					afterMemeticImp=AddNewPair(afterMemeticImp);  
				}
			}
		*/
			
			
			
		
			
			
			
			// נבצע הורדת צמתים כל עוד יש שיפור מבלי לפגוע במספר המיונים
			do
			{
				afterMemeticImp=MemeticImprovment ( afterMemeticImp );
				
			}while( _memeticImprovmentFlag  );
			
			// הוספת זוגות 
			int sortingBefore;
			do
			{
			//	double fitnessBefore=afterMemeticImp.get_fitness();
				sortingBefore=afterMemeticImp.get_numberOfTrueSorting();
				afterMemeticImp=AddNewPair(afterMemeticImp);      // הוספת זוג 
				 //נבצע כל עוד המהלך שיפר את מספר הוקטורים שמיינו  ולא מיינו עדיין את כל הוקטורים     ולא עברנו את מקסימום מספר הזוגות
			}while( afterMemeticImp.get_numberOfTrueSorting() > sortingBefore  && afterMemeticImp.get_numberOfTrueSorting()< _vectorpopulationSize &&   afterMemeticImp.get_sortingNetwork().size()/2 < MAX_NUMBER_OF_PAIRS    );     
			
			_algNextPopulation.add ( afterMemeticImp );
		//	_algNextPopulation.add(_algPopulation.elementAt ( i ));
		}
	
		
		Random seed = new Random ( );
		for ( int i = ( _algpopulationSize / 5 ) ; i < _algpopulationSize ; i ++ )
		{
			double newGenfitness;
			double parent1Fitness;
			double parent2Fitness;
			AlgGen newGen = new AlgGen ( );
			int numberOftries=0;
			do
			{
				newGen = new AlgGen ( );
				numberOftries++;
				// נבחר 2 הורים מ50 האחוזים הטוביפ הנותרים ואינדקס בתוך הצביעה שנאחד ביניהם
				int i1 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// ///////////////////////////////////////////////
				int i2 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// //////////////////////////////////////////////
				//int i3 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// ///////////////////////////////////////////////
				//int i4 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// //////////////////////////////////////////////
				
				while(i1==i2  /*||  i1==i3  ||  i1==i4  ||i2==i4  ||  i2==i3  ||  i3==i4*/)
				{
					i1 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// ///////////////////////////////////////////////
					i2 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// //////////////////////////////////////////////
				//	i3 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// ///////////////////////////////////////////////
				//	i4 = seed.nextInt ( ( int ) Math.floor ( _algpopulationSize / 2 ) );// //////////////////////////////////////////////
				}
				
				// 2 הגנים שהגרלנו
				AlgGen genP1 = _algPopulation.elementAt ( i1 );
				AlgGen genP2 = _algPopulation.elementAt ( i2 );
			//	AlgGen genP3 = _algPopulation.elementAt ( i3 );
			//	AlgGen genP4 = _algPopulation.elementAt ( i4 );

				
			//	int max12=Math.max ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );//בדיקה איזה ווקטור הוא יותר גדול
			//	int max34=Math.max ( genP3.get_sortingNetwork ( ).size ( ) , genP4.get_sortingNetwork ( ).size ( ) );//בדיקה איזה ווקטור הוא יותר גדול
				int max=Math.max (genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );
		//		int min12=Math.min ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );
		//		int min34=Math.min ( genP4.get_sortingNetwork ( ).size ( ) , genP3.get_sortingNetwork ( ).size ( ) );
				int min=Math.min ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ));
				
				int newVectorSize=1;//גודל הווקטור החדש - נוודה שהוא בגודל זוגי
				
				while(newVectorSize%2!=0  ||   newVectorSize==0  ||  newVectorSize<16)
				{
					newVectorSize=seed.nextInt ( max-MIN_NUMBER_OF_PAIRS+1 )+MIN_NUMBER_OF_PAIRS;
				}

				int halfIndex=seed.nextInt (newVectorSize );//האינדקס שלפיו נעתיק מההורה הראשון ואז מההורה השני
				
				while(halfIndex%2!=0  || halfIndex<2 )
				{
					halfIndex=seed.nextInt (newVectorSize );
				}
				
				int parent=seed.nextInt (2 );//נתחיל מההורה הראשון או מהשני
				
				
				if(parent==0)
				{
					if(genP1.get_sortingNetwork ( ).size ( )>halfIndex)//אם יש להורה הראשון מספיק זוגות
					{
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						if(genP2.get_sortingNetwork ( ).size ( )>=newVectorSize-halfIndex)//אם להורה השני יש מספיק זוגות בשביל מה שנשאר
						//להמשיך פה את ההעתקה של החלק השני
						{
							for ( int j = 0 ; j < newVectorSize-halfIndex ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
						else//אין להורה השני מספיק זוגות אז נעתיק את כל ההורה השני ונשלים עם ההמשך של ההורה הראשון
						{
							int parent2size=genP2.get_sortingNetwork ( ).size ( );
							int remainingSize=newVectorSize-halfIndex-parent2size;
							for ( int j = halfIndex ; j < halfIndex+remainingSize ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
							for ( int j = 0 ; j < genP2.get_sortingNetwork ( ).size ( ) ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
					}
					else//אין להורה הראשון מספיק זוגות
					{
						//נעתיק את כל ההורה הראשון ואת מה שחסר מההורה השני
						halfIndex=genP1.get_sortingNetwork ( ).size ( );
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						for ( int j = halfIndex; j < newVectorSize ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
					}
				}
				else
				{
					if(genP2.get_sortingNetwork ( ).size ( )>halfIndex)//אם יש להורה השני מספיק זוגות
					{
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						if(genP1.get_sortingNetwork ( ).size ( )>=newVectorSize-halfIndex)//אם להורה הראשון יש מספיק זוגות בשביל מה שנשאר
						//להמשיך פה את ההעתקה של החלק השני
						{
							for ( int j = 0 ; j < newVectorSize-halfIndex ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
						else//אין להורה הרשאון מספיק זוגות אז נעתיק את כל ההורה הראשון ונשלים עם ההמשך של ההורה השני
						{
							int parent1size=genP1.get_sortingNetwork ( ).size ( );
							int remainingSize=newVectorSize-halfIndex-parent1size;
							for ( int j = halfIndex ; j < halfIndex+remainingSize ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
							for ( int j = 0 ; j < genP1.get_sortingNetwork ( ).size ( ) ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
					}
					else//אין להורה הראשון מספיק זוגות
					{
						//נעתיק את כל ההורה הראשון ואת מה שחסר מההורה השני
						halfIndex=genP2.get_sortingNetwork ( ).size ( );
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						for ( int j = halfIndex; j < newVectorSize ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
					}
				}
				
				
					
				parent1Fitness=genP1.get_fitness ( );
				parent2Fitness=genP2.get_fitness ( );
				algFitness(newGen);
				newGenfitness=newGen.get_fitness ( );
			}
			while (  ( newGenfitness > parent1Fitness || newGenfitness > parent2Fitness    )   && numberOftries<20 ); // עד שהבן יהיה טוב יותר מהוריו או שלא הצלחנו ליצור בן יותר טוב
			
			numberOftries=0;
			AlgGen newGen2 = new AlgGen ( newGen );//יוצרים עותק של הגן החדש
			// /הגרלה האם עושים מוטציה עם סלקשן 5 נסיונות
			// בהסתברות של 25%
			int percent = seed.nextInt ( 100 );
			Boolean fitnessImproved = false;
			if ( percent < _percentageForMutation ) // אם הוגרל לעבור מוטציה נעבור מוטציה שבהכרח משפרת את הפיטנס של הגן
			{
				int counterOfMutationTries = 0;
				fitnessImproved = false;
				do
				{
					newGen2 = new AlgGen ( newGen );
					counterOfMutationTries ++ ;

					newGen2 = algMutate ( newGen2 );
					algFitness ( newGen2 ) ;
					if ( counterOfMutationTries > 5 )
					{
						break;
					}
					if ( newGen.get_fitness ( ) > newGen2.get_fitness ( ) )
					{
						fitnessImproved = true;
					}
				}
				while (  newGen.get_fitness ( ) < newGen2.get_fitness ( )  && numberOftries<2 );
			}
			
			AlgGen memetic=null;
		
			
			//   ////////////////////////////////////////////////////////גם אם המוטציה לא שיפרה עדיין נשתמש בגן שיצא
			//if ( fitnessImproved )
		//	{
				memetic=newGen2;
				memetic=MemeticImprovment(newGen2);
			
				/*
				for (int j = 0; j < 5 ; j++) {
					memetic=MemeticImprovment(memetic);
					if (memetic.get_numberOfTrueSorting()< _vectorpopulationSize )
					{
						memetic=AddNewPair(memetic);
					}
				}
				*/
				
				
				
				
				
				// נבצע הורדת צמתים כל עוד יש שיפור מבלי לפגוע במספר המיונים
				do
				{
					memetic=MemeticImprovment ( memetic );
					
				}while( _memeticImprovmentFlag  );
			
			
				// הוספת זוגות 
				int sortingBefore;
				do
				{
				//	double fitnessBefore=afterMemeticImp.get_fitness();
					sortingBefore=memetic.get_numberOfTrueSorting();
					memetic=AddNewPair(memetic);      // הוספת זוג 
					 //נבצע כל עוד המהלך שיפר את מספר הוקטורים שמיינו  ולא מיינו עדיין את כל הוקטורים     ולא עברנו את מקסימום מספר הזוגות
				}while( memetic.get_numberOfTrueSorting() > sortingBefore  && memetic.get_numberOfTrueSorting()< _vectorpopulationSize &&   memetic.get_sortingNetwork().size()/2 < MAX_NUMBER_OF_PAIRS    );     
				
				
				
				_algNextPopulation.add ( memetic );
	//		}
	//		else
	//		{
	//			memetic=newGen;
	//			memetic=MemeticImprovment(newGen);
	//			memetic=AddNewPair(memetic);
	//			_algNextPopulation.add ( memetic );
	//		}
			
			/**
			 * בדיקה האם הורדה של זוג מסויים ברשת המיון תורמת לפיטנס של הגן
			 */
		}
		// העתקה של האוכלוסיה הבאה במקום האוכלוסיה הנוכית
		_algPopulation = new Vector < AlgGen > ( );
	
		
		
		for ( int i = 0 ; i < _algNextPopulation.size ( ) ; i ++ )
		{
			AlgGen newGen = new AlgGen ( _algNextPopulation.elementAt ( i ) );
			_algPopulation.add ( newGen );
		}
	}


	/**
	 * הורדה של זוגות ובדיקה איך זה משפיע על הפיטנס
	 * @param newGen2
	 */
	private AlgGen MemeticImprovment ( AlgGen newGen2 )
	{
		Random seed = new Random();
		boolean stop=false;
		_memeticImprovmentFlag=true;
		
		int numberOfTries=0;
		int size=newGen2.get_sortingNetwork ( ).size ( );
		AlgGen copy=new AlgGen(newGen2);
		
		while(!stop  &&  numberOfTries<size)
		{
			copy=new AlgGen(newGen2);
			numberOfTries++;
			int index=1;
			while(index%2!=0)
			{
				index=seed.nextInt ( newGen2.get_sortingNetwork ( ).size ( ));
			}
			//remove the couple - this is the way that it should be written !!!  index  index    not   index   index+1 
			copy.get_sortingNetwork ( ).remove ( index );
			copy.get_sortingNetwork ( ).remove ( index );
			algFitness ( copy );
			if(copy.get_fitness ( )<newGen2.get_fitness ( ))//בדיקה האם הפיטנס יותר טוב
			{
				if(copy.get_numberOfTrueSorting ( )>=newGen2.get_numberOfTrueSorting ( ))//בדיקה האם בנוסף מצליחים למיין לפחות את אותו מספר ווקטורים
				{
					newGen2=copy;
					stop=true;
				}
			}
		}
		
		if(stop)
		{
			return copy;
		}
		
		
		// לא התבצע שיפור בפיטנס 
		_memeticImprovmentFlag=false;
		return newGen2;
	}
	
	
	
	
	
	
	
	/**
	 * הורדה של זוגות ובדיקה איך זה משפיע על הפיטנס
	 * @param newGen2
	 */
	private AlgGen AddNewPair ( AlgGen newGen2 )
	{
		Random seed = new Random();
		boolean stop=false;
		
		
		int numberOfTries=0;
		int size=newGen2.get_sortingNetwork ( ).size ( );
		AlgGen copy=new AlgGen(newGen2);
		
		while(!stop  &&  numberOfTries<size)
		{
			copy=new AlgGen(newGen2);
			numberOfTries++;
			int index=1;
			while(index%2!=0)
			{
				index=seed.nextInt ( newGen2.get_sortingNetwork ( ).size ( ));
			}
			
			int num1=0;
			int num2=0;
			while(num1==num2)
			{
				num1=seed.nextInt(_vectorSize);
				num2=seed.nextInt(_vectorSize);
			}
			
			//insert the couple - this is the way that it should be written !!!  index  index    not   index   index+1 
			copy.get_sortingNetwork ( ).insertElementAt(num1, index);
			copy.get_sortingNetwork ( ).insertElementAt(num2, index);

			algFitness ( copy );
			if(copy.get_fitness ( )<newGen2.get_fitness ( ))//בדיקה האם הפיטנס יותר טוב
			{
				if(copy.get_numberOfTrueSorting ( )>=newGen2.get_numberOfTrueSorting ( ))//בדיקה האם בנוסף מצליחים למיין לפחות את אותו מספר ווקטורים
				{
					newGen2=copy;
					stop=true;
				}
			}
		}
		
		if(stop)
		{
			return copy;
		}
		
		return newGen2;
	}
	
	
	
//	private void MemeticImprovment ( AlgGen newGen2 )
//	{
//		Random seed = new Random();
//	//	boolean stop=false;
//		
//		
//		int numberOfTries=newGen2.get_sortingNetwork ( ).size ( );
//		
//		while(numberOfTries>0)
//		{
//			AlgGen copy=new AlgGen(newGen2);
//			numberOfTries--;
//			int index=1;
//			while(index%2!=0)
//			{
//				index=seed.nextInt ( newGen2.get_sortingNetwork ( ).size ( ));
//			}
//			//remove the couple - this is the way that it should be written !!!  index  index    not   index   index+1 
//			copy.get_sortingNetwork ( ).remove ( index );
//			copy.get_sortingNetwork ( ).remove ( index );
//			algFitness ( copy );
//			if(copy.get_fitness ( )<newGen2.get_fitness ( ))
//			{
//				newGen2=copy;
//			}
//			else
//			{
//				copy=newGen2;
//			}
//		}
//		
//	}
	
	

	/**
	 * מוטציה לווקטור - בוחר אינדקס רנדומאלי ומחליף את המספר שבו למספר אחר רנדומלי
	 * @param newgen
	 * @return
	 */
	private VectorGen vectorMutate ( VectorGen newgen )
	{
		Random seed = new Random ( );
		
		int index=seed.nextInt ( _vectorSize );
		int newNumber=seed.nextInt ( MAX_INT_SIZE );
		
		newgen.get_numbers ( ).set ( index , newNumber );//מבצע את המוטציה
		
		Vector < Integer > toSort =new Vector<Integer>(); 
		for ( int i = 0 ; i < newgen.get_numbers ( ).size ( ) ; i ++ )
		{
			toSort.add ( newgen.get_numbers ( ).elementAt ( i )  );//ממיין את הווקטור לאחר השינוי
		}
		
		
		int n = _vectorSize;
		for ( int pass = 0 ; pass < n - 1 ; pass ++ )
		{ // count how many times
			// This next loop becomes shorter and shorter
			Boolean exchangeFlag = false;
			for ( int j = 0 ; j < n - 1 ; j ++ )
			{
				if ( toSort.elementAt ( j )  > toSort.elementAt ( j+1 ) )
				{
					// exchange elements
					Integer xi = toSort.elementAt ( j );
					Integer xi1 = toSort.elementAt ( j + 1 );
					toSort.remove ( j );
					toSort.add ( j , xi1 );
					toSort.remove ( j + 1 );
					toSort.add ( j + 1 , xi );
					exchangeFlag = true;
				}
			}
			if ( ! exchangeFlag )
				break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
		}
		
		newgen.set_sortedNumbers ( toSort );
		
		
		return newgen;
	}
	
	
	
	private AlgGen algMutate ( AlgGen newgen )
	{
		Random seed = new Random ( );
		
		int ind1=0;
		int ind2=0;
		boolean success=false;
		
		while(!success)//כל עוד לא ביצענו מוטציה חוקית
		{
			ind1=0;
			ind2=0;

			int vind1=0;
			int vind2=0;
			int vind3=0;
			int vind4=0;
			while(ind1==ind2  ||   ind1%2!=0  ||   ind2%2!=0  || vind1==vind4  ||  vind2==vind3 )//מוצא את 2 האינדקסים ומוודא שהם יהיו שונים אחד משני ושיתחלקו בשתיים כי כל אינדקס מבטא זוג
			{
				ind1=seed.nextInt ( _vectorSize);
				ind2=seed.nextInt ( _vectorSize);
				vind1=newgen.get_sortingNetwork ( ).elementAt ( ind1 );
				vind2=newgen.get_sortingNetwork ( ).elementAt ( ind1+1 );
				vind3=newgen.get_sortingNetwork ( ).elementAt ( ind2 );
				vind4=newgen.get_sortingNetwork ( ).elementAt ( ind2+1 );
				
			}
			success=true;
			newgen.get_sortingNetwork ( ).set ( ind1+1 , vind4 );
			newgen.get_sortingNetwork ( ).set ( ind2+1 , vind2 );
		}
		return newgen;
	}

}
