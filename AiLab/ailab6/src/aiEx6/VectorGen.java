package aiEx6;

import java.util.Vector;


public class VectorGen
{
	private double _fitness;
	private Vector<Integer> _numbers;
	private Vector<Integer> _sortedNumbers;//הווקטור הממויין - לשם השוואה
	
	
	public double get_fitness ( )
	{
		return _fitness;
	}

	public VectorGen(VectorGen copy)
	{
		_numbers=new Vector<Integer>();
		_sortedNumbers=new Vector<Integer>();
		this._fitness=copy.get_fitness ( );
		
		for ( int i = 0 ; i < copy.get_numbers ( ).size ( ) ; i ++ )
		{
			_numbers.add ( copy.get_numbers ( ).elementAt ( i ) );
		}
		
		for ( int i = 0 ; i < copy.get_sortedNumbers ( ).size ( ) ; i ++ )
		{
			_sortedNumbers.add ( copy.get_sortedNumbers ( ).elementAt ( i ) );
		}
	}
	public void set_fitness ( double _fitness )
	{
		this._fitness = _fitness;
	}

	
	public Vector < Integer > get_numbers ( )
	{
		return _numbers;
	}

	
	public void set_numbers ( Vector < Integer > _numbers )
	{
		this._numbers = _numbers;
	}

	public VectorGen()
	{
		_numbers=null;
		_numbers=new Vector<Integer>();
		_sortedNumbers=new Vector<Integer>();
	}


	
	public Vector < Integer > get_sortedNumbers ( )
	{
		return _sortedNumbers;
	}


	
	public void set_sortedNumbers ( Vector < Integer > numbers )
	{
		_sortedNumbers = numbers;
	}
	
}
