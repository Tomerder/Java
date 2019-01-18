/**
 * Ai EX5
 * Orel shai - 052988912	
 * Tomer dery - 060628914
 */
package aiEx6;

import java.util.Random;
import java.util.Vector;


public class AlgGen 
{
	
	private double _fitness;
	private Vector<Integer> _sortingNetwork;
	private int _numberOfTrueSorting;
	private int _w1;
	private int _w2;
	private int _w3;
	
	public AlgGen(){
		_sortingNetwork=new Vector<Integer>(); 
		_fitness=0;
		_numberOfTrueSorting=0;
		
	/*
		Random seed = new Random();
		_w1= seed.nextInt ( 50 ) +1; 
		_w2= seed.nextInt ( 50 ) +1; 
		_w3= seed.nextInt ( 50 ) +1; 
		*/
		
		
		_w1=10000;  // 35  // 1
		_w2=1;			//35	
		_w3=1;		    //2
		
		

	}
	

	public AlgGen(AlgGen copy){
		_sortingNetwork=new Vector<Integer>(); 
		this._fitness=copy.get_fitness ( );
		this._numberOfTrueSorting=copy.get_numberOfTrueSorting( );
		for ( int i = 0 ; i < copy.get_sortingNetwork ( ).size ( ) ; i ++ )
		{
			_sortingNetwork.add ( copy.get_sortingNetwork ( ).elementAt ( i ) );
			this._w1=copy.get_w1() ;
			this._w2=copy.get_w2() ;
			this._w3=copy.get_w3() ;
		}
	}
	
	public double get_fitness ( )
	{
		return _fitness;
	}

	
	public void set_fitness ( double _fitness )
	{
		this._fitness = _fitness;
	}

	
	public Vector < Integer > get_sortingNetwork ( )
	{
		return _sortingNetwork;
	}

	
	public void set_sortingNetwork ( Vector < Integer > network )
	{
		_sortingNetwork = network;
	}


	public int get_numberOfTrueSorting() {
		return _numberOfTrueSorting;
	}


	public void set_numberOfTrueSorting(int ofTrueSorting) {
		_numberOfTrueSorting = ofTrueSorting;
	}


	public int get_w1() {
		return _w1;
	}


	public void set_w1(int _w1) {
		this._w1 = _w1;
	}


	public int get_w2() {
		return _w2;
	}


	public void set_w2(int _w2) {
		this._w2 = _w2;
	}


	public int get_w3() {
		return _w3;
	}


	public void set_w3(int _w3) {
		this._w3 = _w3;
	}

	
	
}
