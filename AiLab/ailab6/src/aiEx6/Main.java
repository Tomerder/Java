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

	private Vector < AlgGen > _algPopulation	= new Vector < AlgGen > ( );			// ��������� ���� �� ����������
	private Vector < AlgGen > _algNextPopulation	= new Vector < AlgGen > ( );			// ���� ���
	
	private Vector < VectorGen > _vectorPopulation	= new Vector < VectorGen > ( );			//  ��������� ���� �� ��������� �� �������
	private Vector < VectorGen > _vectorNextPopulation	= new Vector < VectorGen > ( );			// ���� ���
	
	private int	_algpopulationSize	=250;
	private int	_vectorpopulationSize	=250;
	private int	_percentageForMutation	= 25;
	private int	_numberOfIterations	= 10000;
	private int	_allowNumberOfIterationsWithNoBetterFitnees	= _numberOfIterations  ;			// ���� ��������� ����� ����� ���� ������
	
	private int MAX_NUMBER_OF_PAIRS=120;//����� �������� �� �� ����� ������� ����� ���������
	private int MIN_NUMBER_OF_PAIRS=30;
	
	private int _vectorSize=16;//����� �� ������� �� ������� ������ �� ���
	private int MAX_INT_SIZE=100;//����� ����� ����� ������ - ����� ���� �� ������ ��� ����� ��� ��� ����� ���� ���� ������
	private int best=0;
	
	private boolean _memeticImprovmentFlag=true;
	
	private int COUNTER_OF_ITERATIONS_WITHOUT_VECTORPOPULATION_IMPROVEMENT= 2  ;//�� 5 �������� ���� �� �������� ���������
	
	
	
	public static void main(String[] args) {
		Main main=new Main();
	}
	
	public Main()
	{
		execute();
	}

	
	private void execute() {
		double bestLegalFitness=Integer.MAX_VALUE;//������ ����� ��� ���
		int numberOfIterationsWithNoBetterFitness=0;//��' ��������� �� ���� ������
		int counter=0;
		
		initPopulationAlg ( );
		initPopulationVector ( );

		
		  
		 //��� �� ���� ����  
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
		
		

		while ( numberOfIterationsWithNoBetterFitness < _allowNumberOfIterationsWithNoBetterFitnees ) // �� ��� �� �������
		{
			System.out.println ();
			for ( int i = 0 ; i < _algpopulationSize ; i ++ )//���� ������ ��� �� � 2 ����������
			{
				algFitness(_algPopulation.elementAt ( i ));
			}
			for ( int i = 0 ; i < _vectorpopulationSize ; i ++ )//���� ������ ��� �� � 2 ����������
			{
				vectorFitness(_vectorPopulation.elementAt ( i ));
			}
			 

			/**
			 * ���� �� ��������� ���������
			   ����� ���� �� ���
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
					break; // �� ����� ��� �� ����� ����� , ������
			}
			
			
			/**
			 * ���� �� ��������� �����������
				����� ���� �� ���
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
					break; // �� ����� ��� �� ����� ����� , ������
			}
			
			
			/**
			 * ������� ���������� ����������� �� ��������� ��� ��� ������� - ������ ����� 0
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
			// ���� ���� ���
			algNextPopulation( );
			
			
			// �� �� ����� �������� ������ ���� ���
			if ( counter % COUNTER_OF_ITERATIONS_WITHOUT_VECTORPOPULATION_IMPROVEMENT  == 0 )   //  ��������� �������� ����� �� �� �������� - 5 
				vectorNextPopulation( );
			
/////////////////////////////////////////////////////////////////////////////////////			

			/**
			 * ����� ��� ������ ���� ����� ������ ���� ��� ������� ��� ��� �� ����� , ������ ��� ����� ������ ��������� �� ����� �� ���� ������ - ��� �����
			 */
			if ( bestLegalFitness > _algPopulation.elementAt ( 0 ).get_fitness ( ) )// ������ ���� �� ���� ������ ���� ��� ������� �� ���� ������
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
	 * ���� ����� ��� �������� �� ��� �� ������� �� ���������� �� �� ��������� �� ������� �������� �� ��������� �����
	 * ������� ��� ����� �� ��� � ��������� ����� ����� ����.
	 * ���� �������� ���� ���������:
	 * ���� ��������� ���������� ����� ����� , ���� ��������� ��� ������ ����� - ��� ������ �� ��� �����
	 * @param currentGen
	 */
	
	//����� ���� ��� ��� - ���� ���� �� ���� ������ ���� ����� ++ ���� �� ���� ��� ���� ����� �����
	private void algFitness ( AlgGen currentGen)
	{
		//int numbersOfMissedNumbers=0;
		int numberOfSortedGens=0;//���� ��������� �������� ����� - ����� ���� �� ���� ���
	
		int totalDistancesBetweenMisses=0;
		
		for ( int i = 0 ; i < _vectorPopulation.size ( ) ; i ++ )//���� �� �� ���������
		{
			Vector < Integer > currentVector=new Vector<Integer>();
			for ( int j = 0 ; j < _vectorPopulation.elementAt ( i ).get_numbers ( ).size ( ) ; j ++ )
			{
				currentVector.add ( _vectorPopulation.elementAt ( i ).get_numbers ( ).elementAt ( j ) );
			}
			//Vector < Integer > currentVector = (Vector < Integer >)_vectorPopulation.elementAt ( i ).get_numbers ( ).clone ( );//������� ������ �����
			Vector < Integer > sortedVector = _vectorPopulation.elementAt ( i ).get_sortedNumbers ( );//������� �������
			
			for ( int j = 0 ; j < currentGen.get_sortingNetwork ( ).size ( ) ; j=j+2 )//���� �� ���� ���� ������� �� ����� - ��� �� ���
			{
				//2 ��������� ��� ���� �����
				int ind1=currentGen.get_sortingNetwork ( ).elementAt ( j );
				int ind2=currentGen.get_sortingNetwork ( ).elementAt ( j+1 );
				
				//2 ������ ����������
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
			
			//����� ��� ��������� ���� �� �������
			boolean success=true;//��� ������ �����
			//numbersOfMissedNumbers=0;//��' ������� ��� ����� ��� �������� �������
			for ( int j = 0 ; j < currentVector.size ( ) ; j ++ )
			{
				if(!currentVector.elementAt ( j ).equals ( sortedVector.elementAt ( j ) ))
				{
				//	numbersOfMissedNumbers++;
					//���� ������ �� ��  ����� ��' ������� ���� ����� ������� ������� ��� ������ ��� ������� �������
					int j2 = 0;
					for ( j2 = 0; j2 < sortedVector.size()  ; j2++) {
						if (currentVector.elementAt ( j ).equals ( sortedVector.elementAt ( j2 ) ) ) 
						{
							break;    // ���� �� ������ ����� ������ ������� 
						}
					}
					totalDistancesBetweenMisses = totalDistancesBetweenMisses +  ( Math.abs (  j   - j2 )) ;  //  ����� �� ����� ��� ��������� �� ������� ����� � 2 ��������
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
		currentGen.set_fitness (( _vectorPopulation.size ( )  -   numberOfSortedGens) *1000       /*+ numbersOfMissedNumbers * currentGen.get_w2()*/        + totalDistancesBetweenMisses  /* *10 */  +   currentGen.get_sortingNetwork ( ).size ( ) *  50  );//���� �����
																																														///////////
		////////////////////////////////////////////////////////   ������ �� ��� ������� �� ������ ����� -  ����� ���� �� ���
	}

	
	
	
	/**
	 * ���� ����� ��� ������ �� ��� ���� �� �� ����������� ������ ��� �������� ����� ����� ����� ����
	 * @param currentGen
	 */
	private void vectorFitness ( VectorGen currentGen)
	{
		int numberOfNotSortedGens=0;//���� ����������� ��� ������� ����� �� ������� ��� - ��� ���� �� ���� ����� �� ���� ���
		for ( int i = 0 ; i < _algPopulation.size ( ) ; i ++ )//���� �� �� �����������
		{
			//Vector<Integer> copy=(Vector<Integer>)currentGen.get_numbers ( ).clone ( );//���� �� ������� ������ �����
			
			Vector < Integer > copy=new Vector<Integer>();
			for ( int j = 0 ; j < currentGen.get_numbers ( ).size ( ) ; j ++ )
			{
				copy.add ( currentGen.get_numbers ( ).elementAt ( j ) );
			}

			
			AlgGen temp = _algPopulation.elementAt ( i );
			for ( int j = 0 ; j < temp.get_sortingNetwork ( ).size ( ) ; j=j+2 )//���� �� ���� ���� ������� �� ����� - ��� �� ���
			{
				//2 ��������� ��� ���� �����
				int ind1=temp.get_sortingNetwork ( ).elementAt ( j );
				int ind2=temp.get_sortingNetwork ( ).elementAt ( j+1 );
				
				//2 ������ ����������
				Integer value1=copy.elementAt ( ind1 );
				Integer value2=copy.elementAt ( ind2 );
				
				//SWITCH
				copy.set ( ind1 , value2 );
				copy.set ( ind2 , value1 );
			}
			//����� ��� ��������� ���� �� �������
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
		currentGen.set_fitness ( numberOfNotSortedGens*10 );//���� �����
		
	}
	
	
	
	
	
	
	/**
	 * ����� ��������� �������� �� ���������
	 * ���� �� ��  ����� 16 ������ �� ����� ��� 0-100
	 * ����� ��� ��� ��� ������ ���� ���� ���� ������ �� ����
	 */
	private void  initPopulationVector()
	{
		Random seed = new Random();
		for ( int i = 0 ; i < _vectorpopulationSize  ; i ++ )//����� �� ���� �����
		{
			VectorGen gen = new VectorGen();
			
			for ( int j = 0 ; j < _vectorSize ; j ++ )
			{
				int newNumber=seed.nextInt ( MAX_INT_SIZE );
				(gen.get_numbers ( )).add ( newNumber );
			}
			
			//gen.set_sortedNumbers ( (Vector<Integer>)gen.get_numbers ( ).clone ( ) ); //���� �� ���� �� ������� ������ ���� ������ - �� ����� ���� ����� �����
			for ( int j = 0 ; j < gen.get_numbers ( ).size ( ) ; j ++ )
			{
				gen.get_sortedNumbers ( ).add ( gen.get_numbers ( ).elementAt ( j ) );
			}
			
			//����� ������� �������
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
					break; // �� ����� ��� �� ����� ����� , ������
			}
			
			_vectorPopulation.add ( gen );
		}
	}
	
	
	
	
	
	/**
	 * ����� ��������� �������� �� �����������
	 */
	private void initPopulationAlg() {
		Random seed = new Random();
		for ( int i = 0 ; i < _algpopulationSize    -1  ; i ++ )//����� �� ���� �����
		{
			AlgGen gen = new AlgGen();
			int numberOfParis=seed.nextInt ( MAX_NUMBER_OF_PAIRS-MIN_NUMBER_OF_PAIRS+1 )+MIN_NUMBER_OF_PAIRS;//����� ���� ��� 16-100 ������ �� ���� ������ ��������� ��
			
			for ( int j = 0 ; j < numberOfParis ; j ++ )
			{
				//���� ��� �2 ��������� �� ���� �����
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
	 * ����� �� ���������
	 * ���� 2 ����� , ���� ������ ������ ����� 1 �� ������� ������ 2 ��������
	 */
	private void vectorNextPopulation()
	{
		_vectorNextPopulation = new Vector < VectorGen > ( );// ������ ������� �� ����� ���� ������ �� �� ��������� ����
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
				// ���� 2 ����� �50 ������� ������ ������� ������� ���� ������ ����� ������
				int i1 = seed.nextInt ( ( int ) Math.floor ( _vectorpopulationSize / 2 ) );// ///////////////////////////////////////////////
				int i2 = seed.nextInt ( ( int ) Math.floor ( _vectorpopulationSize / 2 ) );// //////////////////////////////////////////////
				// 2 ����� �������
				VectorGen genP1 = _vectorPopulation.elementAt ( i1 );
				VectorGen genP2 = _vectorPopulation.elementAt ( i2 );
				
				int index=seed.nextInt ( _vectorSize );//������� ����� �����
				
				Vector<Integer> newVector=new Vector<Integer> ();
				for ( int j = 0 ; j < index ; j ++ )
				{
					newVector.add ( genP1.get_numbers ( ).elementAt ( j ) );
				}
				
				for ( int j = index ; j < _vectorSize ; j ++ )
				{
					newVector.add ( genP2.get_numbers ( ).elementAt ( j ) );
				}
				
				//����
				//Vector<Integer> sorted=(Vector<Integer>)newVector.clone ( );
				Vector<Integer> sorted=new Vector<Integer>();
				
				for ( int j = 0 ; j < newVector.size ( ) ; j ++ )
				{
					sorted.add ( newVector.elementAt ( j ) );
				}
				
				//����� ������� �������
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
						break; // �� ����� ��� �� ����� ����� , ������
				}
				
				parent1Fitness=genP1.get_fitness ( );
				parent2Fitness=genP2.get_fitness ( );
				newGen.set_numbers ( newVector );
				newGen.set_sortedNumbers ( sorted );
				vectorFitness ( newGen );
				newGenfitness=newGen.get_fitness ( );
			}
			while ( newGenfitness < parent1Fitness && newGenfitness < parent2Fitness &&  numberOftries<2); // �� ���� ���� ��� ���� ������ �� ��� ������ ����� �� ���� ���
			
			
			
			VectorGen newGen2 = new VectorGen ( newGen );//������ ���� �� ��� ����
			// /����� ��� ����� ������ �� ����� 5 �������
			// �������� �� 25%
			int percent = seed.nextInt ( 100 );
			Boolean fitnessImproved = false;
			if ( percent < _percentageForMutation ) // �� ����� ����� ������ ����� ������ ������ ����� �� ������ �� ���
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
		// ����� �� ��������� ���� ����� ��������� ������
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
	 * ����� ����� �������� 2 �����
	 * ��� ����� �� ������� �� 2 ������ ���  ��� ���� ��� ����� ��� ������ ��� ��� ���� ����� ���� - �� ��� ���� ����� ������� 1 ��� �� ����� ������� 2
	 * ��� ����� �� ���� ���� - ����� ��� ����� ����� �� ����� ����� �����
	 * ��� ����� ������ �� ��� ��� ������� 1 ���� ������� 2�� ����� �� ����� �� ����
	 */
	private void algNextPopulation ( ) // mate
	{
		_algNextPopulation = new Vector < AlgGen > ( );// ������ ������� �� ����� ���� ������ �� �� ��������� ����
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
			
			
			
		
			
			
			
			// ���� ����� ����� �� ��� �� ����� ���� ����� ����� �������
			do
			{
				afterMemeticImp=MemeticImprovment ( afterMemeticImp );
				
			}while( _memeticImprovmentFlag  );
			
			// ����� ����� 
			int sortingBefore;
			do
			{
			//	double fitnessBefore=afterMemeticImp.get_fitness();
				sortingBefore=afterMemeticImp.get_numberOfTrueSorting();
				afterMemeticImp=AddNewPair(afterMemeticImp);      // ����� ��� 
				 //���� �� ��� ����� ���� �� ���� �������� ������  ��� ����� ����� �� �� ��������     ��� ����� �� ������� ���� ������
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
				// ���� 2 ����� �50 ������� ������ ������� ������� ���� ������ ����� ������
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
				
				// 2 ����� �������
				AlgGen genP1 = _algPopulation.elementAt ( i1 );
				AlgGen genP2 = _algPopulation.elementAt ( i2 );
			//	AlgGen genP3 = _algPopulation.elementAt ( i3 );
			//	AlgGen genP4 = _algPopulation.elementAt ( i4 );

				
			//	int max12=Math.max ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );//����� ���� ������ ��� ���� ����
			//	int max34=Math.max ( genP3.get_sortingNetwork ( ).size ( ) , genP4.get_sortingNetwork ( ).size ( ) );//����� ���� ������ ��� ���� ����
				int max=Math.max (genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );
		//		int min12=Math.min ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ) );
		//		int min34=Math.min ( genP4.get_sortingNetwork ( ).size ( ) , genP3.get_sortingNetwork ( ).size ( ) );
				int min=Math.min ( genP1.get_sortingNetwork ( ).size ( ) , genP2.get_sortingNetwork ( ).size ( ));
				
				int newVectorSize=1;//���� ������� ���� - ����� ���� ����� ����
				
				while(newVectorSize%2!=0  ||   newVectorSize==0  ||  newVectorSize<16)
				{
					newVectorSize=seed.nextInt ( max-MIN_NUMBER_OF_PAIRS+1 )+MIN_NUMBER_OF_PAIRS;
				}

				int halfIndex=seed.nextInt (newVectorSize );//������� ����� ����� ������ ������ ��� ������ ����
				
				while(halfIndex%2!=0  || halfIndex<2 )
				{
					halfIndex=seed.nextInt (newVectorSize );
				}
				
				int parent=seed.nextInt (2 );//����� ������ ������ �� �����
				
				
				if(parent==0)
				{
					if(genP1.get_sortingNetwork ( ).size ( )>halfIndex)//�� �� ����� ������ ����� �����
					{
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						if(genP2.get_sortingNetwork ( ).size ( )>=newVectorSize-halfIndex)//�� ����� ���� �� ����� ����� ����� �� �����
						//������ �� �� ������ �� ���� ����
						{
							for ( int j = 0 ; j < newVectorSize-halfIndex ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
						else//��� ����� ���� ����� ����� �� ����� �� �� ����� ���� ������ �� ����� �� ����� ������
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
					else//��� ����� ������ ����� �����
					{
						//����� �� �� ����� ������ ��� �� ���� ������ ����
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
					if(genP2.get_sortingNetwork ( ).size ( )>halfIndex)//�� �� ����� ���� ����� �����
					{
						for ( int j = 0 ; j < halfIndex ; j=j+2 )
						{
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j ) );
							newGen.get_sortingNetwork ( ).add ( genP2.get_sortingNetwork ( ).elementAt ( j+1 ) );
						}
						if(genP1.get_sortingNetwork ( ).size ( )>=newVectorSize-halfIndex)//�� ����� ������ �� ����� ����� ����� �� �����
						//������ �� �� ������ �� ���� ����
						{
							for ( int j = 0 ; j < newVectorSize-halfIndex ; j=j+2 )
							{
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j ) );
								newGen.get_sortingNetwork ( ).add ( genP1.get_sortingNetwork ( ).elementAt ( j+1 ) );
							}
						}
						else//��� ����� ������ ����� ����� �� ����� �� �� ����� ������ ������ �� ����� �� ����� ����
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
					else//��� ����� ������ ����� �����
					{
						//����� �� �� ����� ������ ��� �� ���� ������ ����
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
			while (  ( newGenfitness > parent1Fitness || newGenfitness > parent2Fitness    )   && numberOftries<20 ); // �� ���� ���� ��� ���� ������ �� ��� ������ ����� �� ���� ���
			
			numberOftries=0;
			AlgGen newGen2 = new AlgGen ( newGen );//������ ���� �� ��� ����
			// /����� ��� ����� ������ �� ����� 5 �������
			// �������� �� 25%
			int percent = seed.nextInt ( 100 );
			Boolean fitnessImproved = false;
			if ( percent < _percentageForMutation ) // �� ����� ����� ������ ����� ������ ������ ����� �� ������ �� ���
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
		
			
			//   ////////////////////////////////////////////////////////�� �� ������� �� ����� ����� ����� ��� ����
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
				
				
				
				
				
				// ���� ����� ����� �� ��� �� ����� ���� ����� ����� �������
				do
				{
					memetic=MemeticImprovment ( memetic );
					
				}while( _memeticImprovmentFlag  );
			
			
				// ����� ����� 
				int sortingBefore;
				do
				{
				//	double fitnessBefore=afterMemeticImp.get_fitness();
					sortingBefore=memetic.get_numberOfTrueSorting();
					memetic=AddNewPair(memetic);      // ����� ��� 
					 //���� �� ��� ����� ���� �� ���� �������� ������  ��� ����� ����� �� �� ��������     ��� ����� �� ������� ���� ������
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
			 * ����� ��� ����� �� ��� ������ ���� ����� ����� ������ �� ���
			 */
		}
		// ����� �� ��������� ���� ����� ��������� ������
		_algPopulation = new Vector < AlgGen > ( );
	
		
		
		for ( int i = 0 ; i < _algNextPopulation.size ( ) ; i ++ )
		{
			AlgGen newGen = new AlgGen ( _algNextPopulation.elementAt ( i ) );
			_algPopulation.add ( newGen );
		}
	}


	/**
	 * ����� �� ����� ������ ��� �� ����� �� ������
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
			if(copy.get_fitness ( )<newGen2.get_fitness ( ))//����� ��� ������ ���� ���
			{
				if(copy.get_numberOfTrueSorting ( )>=newGen2.get_numberOfTrueSorting ( ))//����� ��� ����� ������� ����� ����� �� ���� ���� ��������
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
		
		
		// �� ����� ����� ������ 
		_memeticImprovmentFlag=false;
		return newGen2;
	}
	
	
	
	
	
	
	
	/**
	 * ����� �� ����� ������ ��� �� ����� �� ������
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
			if(copy.get_fitness ( )<newGen2.get_fitness ( ))//����� ��� ������ ���� ���
			{
				if(copy.get_numberOfTrueSorting ( )>=newGen2.get_numberOfTrueSorting ( ))//����� ��� ����� ������� ����� ����� �� ���� ���� ��������
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
	 * ������ ������� - ���� ������ �������� ������ �� ����� ��� ����� ��� �������
	 * @param newgen
	 * @return
	 */
	private VectorGen vectorMutate ( VectorGen newgen )
	{
		Random seed = new Random ( );
		
		int index=seed.nextInt ( _vectorSize );
		int newNumber=seed.nextInt ( MAX_INT_SIZE );
		
		newgen.get_numbers ( ).set ( index , newNumber );//���� �� �������
		
		Vector < Integer > toSort =new Vector<Integer>(); 
		for ( int i = 0 ; i < newgen.get_numbers ( ).size ( ) ; i ++ )
		{
			toSort.add ( newgen.get_numbers ( ).elementAt ( i )  );//����� �� ������� ���� ������
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
				break; // �� ����� ��� �� ����� ����� , ������
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
		
		while(!success)//�� ��� �� ������ ������ �����
		{
			ind1=0;
			ind2=0;

			int vind1=0;
			int vind2=0;
			int vind3=0;
			int vind4=0;
			while(ind1==ind2  ||   ind1%2!=0  ||   ind2%2!=0  || vind1==vind4  ||  vind2==vind3 )//���� �� 2 ��������� ������ ��� ���� ����� ��� ���� �������� ������ �� �� ������ ���� ���
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
