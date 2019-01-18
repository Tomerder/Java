package aiEx61;

import java.util.Random;
import java.util.Vector;
import aiEx6.AlgGen;
import aiEx6.VectorGen;


public class Main
{

	int GA_POPSIZE_NET		=1000	;	// ga population size for the nets
	int GA_POPSIZE_ARRAY	=100	;	// ga population size for the parmution
	double GA_MAXITER	=	16384	;	// maximum iterations
	double GA_ELITRATE	=	0.10f	;	// elitism rate
	double GA_MUTATIONRATE=	0.25f	;	// mutation rate
	int GA_MUTATION	=	25;
	int GA_ARRAY_SIZE =  16		;
	/**
	 * @param args
	 */
	int generation_count;
	Vector<Integer> sortedNumbers=new Vector<Integer>();
	private Vector<ga_struct_net> ga_vector_net=new Vector<ga_struct_net>();
	private Vector<ga_struct_array> ga_vector_array=new Vector<ga_struct_array>();
	
	
	public class ga_struct_net
	{
		Vector<Integer> net	;				// the sorting net
		double fitness;			// its fitness
		
		public ga_struct_net()
		{
			net=new Vector<Integer>(); 
		}
	}
	
	public class ga_struct_array
	{
		Vector<Integer> numbers	;				// the sorting net
		int fitness;			// its fitness
		
		public ga_struct_array()
		{
			numbers=new Vector<Integer> ();
		}
	}
	
	
	
	
	public void initSortedNumbers()
	{
		for(int i = 0 ; i< GA_ARRAY_SIZE ; i++)
			sortedNumbers.add(i);
	}
	
	
	public void init_population_net(Vector<ga_struct_net> population,
			Vector<ga_struct_net> buffer ) 
	{
		Random seed=new Random();
		for (int i=0; i<GA_POPSIZE_NET; i++)
		{
		ga_struct_net citizen=new ga_struct_net();
		citizen.fitness = 0;
		int size = seed.nextInt (39) + 61 ;
		
		
		//create the sorting net like it apperes in the doc files
		for(int k = 0 ; k < size ; k++)
		{
			int index1,index2;
			do{
				index1 = seed.nextInt (GA_ARRAY_SIZE);				
				
				index2 = seed.nextInt (GA_ARRAY_SIZE);	
				
			}while(index1 == index2);
				citizen.net.add(index1);
				citizen.net.add(index2);
		}
		population.add(citizen);
		}
		
	}
	
	
	
	
	

	public void init_population_array(Vector<ga_struct_array> population, 
			Vector<ga_struct_array> buffer ) 
	{
		for (int i=0; i<GA_POPSIZE_ARRAY; i++) 
		{
			Random seed=new Random();
			ga_struct_array citizen=new ga_struct_array(); 
			
			citizen.fitness = 0;
			//bulid an array of sorted numbers 
			Vector<Integer> temp=new Vector<Integer>();
			for(int j = 0 ; j < GA_ARRAY_SIZE ; j++ )
				temp.add(j);
			int index;
			int k = 0 ;
			//create a permution of numbers 
			while(k < GA_ARRAY_SIZE)
			{
				do
				{
					index = seed.nextInt (  GA_ARRAY_SIZE );
					if(temp.elementAt (index) != -1)
					{
						citizen.numbers.add(index);
						temp.insertElementAt ( -1 , index );
						k++;
						break;
					}
				}while(temp.elementAt (index) == -1);
			}
			population.add(citizen);
		}

	}
	
	
	
	

	public double sortUsingNet(Vector<Integer> net,Vector<Integer> numbers)
	{
		Vector<Integer> temp = numbers;
		int first,second,a,b;
		double  bad=0;
		for(int i = 0 ; i < net.size() -1 ;i++)
		{
			//first = net[i];
			//second = net[i+1];
			 if( net.elementAt ( i ) > net.elementAt ( i+1 ) ) { 
					 first = net.elementAt ( i );
					 second = net.elementAt ( i+1 );
				 }else{
					 first = net.elementAt ( i+1 );
					 second = net.elementAt ( i );
				 }

			if(temp.elementAt ( first ) <temp.elementAt ( second ))
			{
				//swap
				int k = temp.elementAt ( first );
				temp.insertElementAt ( temp.elementAt( second)  , first);
				temp.insertElementAt (  k ,temp.elementAt( second)  );
			}
		}
		for(int i = 0 ; i<GA_ARRAY_SIZE ; i++)
		{
			if(temp.elementAt ( i )!= i )
				bad++;
		}
		bad = bad * 6.25;
		return  bad;
	}
	
	
	


	public boolean printArrayUsingNet(Vector<Integer> net,Vector<Integer> numbers)
	{
		Vector<Integer> temp = numbers;
		boolean result = true; 
		int first,second,bad=0,a,b;
		for(int i = 0 ; i < net.size() -1 ;i++)
		{
			first = net.elementAt ( i );
			second = net.elementAt ( i+1 );
			 if( net.elementAt ( i ) > net.elementAt ( i+1 ) ) { 
					 a = net.elementAt ( i );;
					 b = net.elementAt ( i+1 );;
				 }else{
					 a = net.elementAt ( i+1 );
					 b = net.elementAt ( i );;
				 }

			if(temp.elementAt ( a ) < temp.elementAt ( b ))
			{
				//swap
				int k = temp.elementAt ( a );
				temp.insertElementAt ( temp.elementAt ( b ) ,a );
				temp.insertElementAt ( k , temp.elementAt ( b ) );
			}
		}
		for(int i = 0 ; i<GA_ARRAY_SIZE ; i++)
		{
			if(i != temp.elementAt ( i ))
				result = false; 
		}
	
		return result; 
	}

	
	

	public void srinkNet(Vector<ga_struct_net> population_net)
	{
		for (int i = 0 ; i < GA_POPSIZE_NET ; i++)
		{
			Vector <Integer> temp=new Vector <Integer>();
			for(int k = 0 ; k < population_net.elementAt ( i ).net.size() - 4 ; k+=4)
			{		
				if(population_net.elementAt ( i ).net.elementAt ( k ) == population_net.elementAt ( i ).net.elementAt ( k+2 ) && population_net.elementAt ( i ).net.elementAt ( k+1 ) == population_net.elementAt ( i ).net.elementAt ( k+3 ))
				{
					temp.add(population_net.elementAt ( i ).net.elementAt ( k ));
					temp.add(population_net.elementAt ( i ).net.elementAt ( k+1 ));
				}
				
				
				
				if(population_net.elementAt ( i ).net.elementAt ( k ) != population_net.elementAt ( i ).net.elementAt ( k+2 ) && population_net.elementAt ( i ).net.elementAt ( k+1 ) != population_net.elementAt ( i ).net.elementAt ( k+3 ))
				{
					temp.add(population_net.elementAt ( i ).net.elementAt ( k ));
					temp.add(population_net.elementAt ( i ).net.elementAt ( k+1 ));
					temp.add(population_net.elementAt ( i ).net.elementAt ( k+2 ));
					temp.add(population_net.elementAt ( i ).net.elementAt ( k+3 ));
				}
			}
			int tempSize = temp.size();
			if(tempSize > 121 && tempSize < 201 && (tempSize%2) == 0 )
			{
				population_net.elementAt ( i ).net = temp;
			}
		}
	}
	
	
	
	


	public void calc_fitness_net(Vector<ga_struct_net> population_net,Vector<ga_struct_array> population_array)
	{	
		//int tsize = target.size();
		srinkNet(population_net);
		float fitness;
			for (int i=0; i < GA_POPSIZE_NET ; i++) 
			{
				fitness = 0;
				for (int j=0; j < GA_POPSIZE_ARRAY ; j++) 
				{	
					fitness += sortUsingNet(population_net.elementAt ( i ).net,population_array.elementAt ( j ).numbers);
				}
				fitness = fitness *100000;
				if(fitness != 0 )
					population_net.elementAt ( i ).fitness = (fitness/ GA_POPSIZE_ARRAY) + (population_net.elementAt ( i ).net.size() - 122 );
				if (fitness == 0 && population_net.elementAt ( i ).net.size() == 122)
					population_net.elementAt ( i ).fitness = 0;


			}	
	}
	
	
	
	
	public void calc_fitness_array(Vector<ga_struct_net> population_net,Vector<ga_struct_array> population_array)
	{	
		//int tsize = target.size();
		int fitness;
			for (int i=0; i < GA_POPSIZE_ARRAY ; i++) 
			{
				fitness = 0;
				for (int j=0; j < GA_POPSIZE_NET ; j++) 
				{
					fitness += sortUsingNet(population_net.elementAt ( j ).net,population_array.elementAt ( i ).numbers);
				}
				population_array.elementAt ( i ).fitness = (fitness/ GA_POPSIZE_ARRAY) ;//+ (population_net[i].net.size() - 61 );
			}	
	}
	
	
	public void sort_by_fitness_net(Vector<ga_struct_net> population)
	{
		int n=population.size ( );
		for ( int pass = 0 ; pass < n - 1 ; pass ++ )
		{ // count how many times
			// This next loop becomes shorter and shorter
			Boolean exchangeFlag = false;
			for ( int i = 0 ; i < n - 1 ; i ++ )
			{
				if ( population.elementAt ( i ).fitness > population.elementAt ( i + 1 ).fitness )
				{
					// exchange elements
					// Gen temp=m.get_population ( ).elementAt ( i );
					 ga_struct_net xi = population.elementAt ( i );
					 ga_struct_net xi1 = population.elementAt ( i + 1 );
					 population.remove ( i );
					 population.add ( i , xi1 );
					 population.remove ( i + 1 );
					 population.add ( i + 1 , xi );
					exchangeFlag = true;
				}
			}
			if ( ! exchangeFlag )
				break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
		}
	}
	
	
	
	public void sort_by_fitness_array(Vector<ga_struct_array> population)
	{
		int n = population.size ( );
		for ( int pass = 0 ; pass < n - 1 ; pass ++ )
		{ // count how many times
			// This next loop becomes shorter and shorter
			Boolean exchangeFlag = false;
			for ( int i = 0 ; i < n - 1 ; i ++ )
			{
				if ( population.elementAt ( i ).fitness < population.elementAt ( i + 1 ).fitness )
				{
					// exchange elements
					// Gen temp=m.get_population ( ).elementAt ( i );
					 ga_struct_array xi = population.elementAt ( i );
					 ga_struct_array xi1 = population.elementAt ( i + 1 );
					 population.remove ( i );
					 population.add ( i , xi1 );
					 population.remove ( i + 1 );
					 population.add ( i + 1 , xi );
					exchangeFlag = true;
				}
			}
			if ( ! exchangeFlag )
				break; // אם סיבוב שלם לא התבצע חילוף , סיימנו
		}
	}
	
	
	
	public void elitism_net(Vector<ga_struct_net> population, 
			Vector<ga_struct_net> buffer, int esize )
	{
		for (int i=0; i<esize; i++) {
			buffer.elementAt ( i ).net = population.elementAt ( i ).net;
			buffer.elementAt ( i ).fitness = population.elementAt ( i ).fitness;
		}
	}

	
	
	
	public void elitism_array(Vector<ga_struct_array> population, 
			Vector<ga_struct_array> buffer, int esize )
	{
		for (int i=0; i<esize; i++) {
			buffer.elementAt ( i ).numbers = population.elementAt ( i ).numbers;
			buffer.elementAt ( i ).fitness = population.elementAt ( i ).fitness;
		}
	}
	
	
	

	public void mutate(ga_struct_net member)
	{
		Random seed=new Random();
		int tsize = member.net.size();
		int ipos = seed.nextInt (  tsize );
		int delta = seed.nextInt (  GA_ARRAY_SIZE ); 

		member.net.insertElementAt ( delta , ipos );
	}
	
	
	public void mutate(ga_struct_array member)
	{
		Random seed=new Random();
		int tsize = member.numbers.size();
		int ipos1 = seed.nextInt (  tsize );
		int ipos2 = seed.nextInt (  tsize );
		/////////////swap///////////////
		int temp = member.numbers.elementAt ( ipos1 );
		
		member.numbers.insertElementAt ( member.numbers.elementAt ( ipos2 ) , ipos1 );
		member.numbers.insertElementAt ( temp , member.numbers.elementAt ( ipos2 ) );
	}
	
	
	
	public void mate_net(Vector<ga_struct_net> population, Vector<ga_struct_net> buffer)
	{
		Random seed=new Random();
		int esize = ( int ) ( GA_POPSIZE_NET * GA_ELITRATE );
		int i1, i2;

		elitism_net(population, buffer, esize);

		// Mate the rest
		for (int i=esize; i<GA_POPSIZE_NET; i++) 
		{
			i1 = seed.nextInt ( GA_POPSIZE_NET/2);
			i2 = seed.nextInt ( GA_POPSIZE_NET/2 );

			//take half of tne net from one parent and another half from the second parent

			int halfParent1 = population.elementAt ( i1 ).net.size() / 2;
			if( halfParent1 % 2 !=0 )
				halfParent1=halfParent1 +1;
			int halfParent2 = population.elementAt ( i2).net.size() / 2;
			if( halfParent2 % 2 !=0 )
				halfParent2=halfParent2 +1;

			int iPos = seed.nextInt ( halfParent1 - 1);
			for(int j = iPos ; j < halfParent1 + iPos ; j++)
			{
				buffer.elementAt ( i).net.add(population.elementAt ( i1 ).net.elementAt ( j ));
			}

			iPos =seed.nextInt ( halfParent2 - 1);
			for(int j = iPos ; j < halfParent2 + iPos  ; j++)
			{
				buffer.elementAt ( i ).net.add(population.elementAt ( i2 ).net.elementAt ( j ));
			}

			if (seed.nextInt (GA_MUTATION ) < GA_MUTATION) mutate(buffer.elementAt ( i ));
		}
	}
	
	
	
	
	

	void mate_array(Vector<ga_struct_array> population, Vector<ga_struct_array> buffer)
	{
		Random seed=new Random();
		int esize = ( int ) ( GA_POPSIZE_ARRAY * GA_ELITRATE );
		int i1, i2;

		elitism_array(population, buffer, esize);

		// Mate the rest
		for (int i=esize; i<GA_POPSIZE_ARRAY; i++) 
		{
			i1 =seed.nextInt ( GA_POPSIZE_ARRAY /2);
			i2 = seed.nextInt ( GA_POPSIZE_ARRAY /2);
			
			buffer.elementAt ( i ).numbers.clear ( );
			for(int k = 0 ; k < GA_ARRAY_SIZE; k++)
				buffer.elementAt ( i ).numbers.add(-1);
			//buffer[i].numbers.resize(GA_ARRAY_SIZE);
			Vector<Integer> temp = sortedNumbers;
			//CrossOver
			int j = 0;
			for(j = 0 ; j<GA_ARRAY_SIZE / 2  ;j++)
			{
				int num,index = 0;
				do
				{
					index = seed.nextInt ( GA_ARRAY_SIZE);
					num = population.elementAt ( i1 ).numbers.elementAt ( index );
					if(temp.elementAt ( num ) != -1)
					{	
						temp.insertElementAt ( -1 , num );
						break;
					}
					
				}while(temp.elementAt ( num ) == -1);
				buffer.elementAt ( i ).numbers.insertElementAt ( num , index );
			}
			int k = 0;
			for( ; j<GA_ARRAY_SIZE  ;j++)
			{
				int num,index = 0;
				do
				{
					index =seed.nextInt (  GA_ARRAY_SIZE );
					num = population.elementAt ( i2 ).numbers.elementAt ( index );
					if(temp.elementAt ( num ) != -1)
					{	
						temp.insertElementAt ( -1 , num );
						break;
					}
					
				}while(temp.elementAt ( num ) == -1 );

				
				while(buffer.elementAt ( i ).numbers.elementAt ( k ) != -1)
					k++;

				buffer.elementAt ( i ).numbers.insertElementAt ( num , k );
			}
			if (seed.nextInt (GA_MUTATION ) < GA_MUTATION) mutate(buffer.elementAt ( i ));
		}

	}
	
	
	
	public void checkSort(Vector<ga_struct_net> population_sort_net , Vector<ga_struct_array> population_sort_array)
	{
		boolean result = true; 
		Random seed=new Random();
		for (int i=0; i< 100; i++) 
		{
			ga_struct_array citizen=new ga_struct_array ();
			
			citizen.fitness = 0;
			//bulid an array of sorted numbers 
			Vector<Integer> temp=new Vector<Integer>();
			for(int j = 0 ; j < GA_ARRAY_SIZE ; j++ )
				temp.add(j);
			int index;
			int k = 0 ;
			//create a permution of numbers 
			while(k < GA_ARRAY_SIZE)
			{
				do
				{
					index = seed.nextInt (  GA_ARRAY_SIZE );
					if(temp.elementAt ( index ) != -1)
					{
						citizen.numbers.add(index);
						temp.insertElementAt ( -1 , index );
						k++;
						break;
					}
				}while(temp.elementAt ( index ) == -1);
			}
			population_sort_array.add(citizen);
		}
		
	}

	
	public static void main ( String [ ] args )
	{
		Main m=new Main();
		m.execute ( );
	}
	


	public void swap_net(Vector<ga_struct_net> population,Vector<ga_struct_net> buffer)
	{
		Vector<ga_struct_net> temp = population; 
		population = buffer; 
		buffer = temp; 
	}

	public void swap_array(Vector<ga_struct_array> population, Vector<ga_struct_array>buffer)
	{ 
		Vector<ga_struct_array> temp = population; 
		population = buffer; 
		buffer = temp; 
	}


	
	public void execute()
	{
		Vector<ga_struct_net> pop_alpha_net=new Vector<ga_struct_net>(), pop_beta_net=new Vector<ga_struct_net>();
		Vector<ga_struct_array> pop_alpha_array=new Vector<ga_struct_array> (), pop_beta_array=new Vector<ga_struct_array> (),pop_alpha_array_sort=new Vector<ga_struct_array> ();
		Vector<ga_struct_net> population_net=new Vector<ga_struct_net>(), buffer_net=new Vector<ga_struct_net>();
		Vector<ga_struct_array> population_array=new Vector<ga_struct_array> (), buffer_array=new Vector<ga_struct_array> (),population_array_sort=new Vector<ga_struct_array> ();


		initSortedNumbers();

		init_population_array(pop_alpha_array, pop_beta_array);
		init_population_net(pop_alpha_net, pop_beta_net);
		population_net = pop_alpha_net;
		buffer_net = pop_beta_net;
		population_array = pop_alpha_array;
		buffer_array = pop_beta_array;
		population_array_sort = pop_alpha_array_sort;

		generation_count = 0;
		for (int i=0; i<GA_MAXITER; i++) {
			calc_fitness_net(population_net,population_array);		// calculate fitness
			calc_fitness_array(population_net,population_array);		// calculate fitness

			sort_by_fitness_net(population_net);	// sort them
			sort_by_fitness_array(population_array);	// sort them

			
			System.out.println ("best :"  +  population_net.elementAt ( 0 ).fitness + '\n');
			printArrayUsingNet(population_net.elementAt ( 0 ).net,population_array.elementAt ( 0).numbers);

			if ( population_net.elementAt ( 0 ).fitness == 0 )
			{
				for(int k = 0 ; k < GA_POPSIZE_ARRAY ; k++)
				{
					printArrayUsingNet(population_net.elementAt ( 0 ).net,population_array.elementAt ( k ).numbers);
				}

				checkSort(population_net,population_array_sort);
				break;
			}

			mate_net(population_net, buffer_net);		// mate the population together
			swap_net(population_net, buffer_net);		// swap buffers
			

			mate_array(population_array, buffer_array);		// mate the population together
			swap_array(population_array, buffer_array);		// swap buffers

			
			
		}

	}

}