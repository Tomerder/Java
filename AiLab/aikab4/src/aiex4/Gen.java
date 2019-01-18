/**
 * Ai EX4
 * Orel shai - 052988912	
 * Tomer dery - 060628914
 */

package aiex4;

import java.util.HashMap;


// מערך בגודל מספר הקודקודים בגרף , המכיל את הצבע של כל קודקוד
// גן = צביעה כלשהי חוקית או לא של הגרף שקיבלנו כקלט
public class Gen
{

	private HashMap<Character , Character>	_key;
	private double	_fitness;


	public Gen ( )
	{
		_key = new HashMap<Character , Character>();
	}

	
	public void charToMap(char[] chars)
	{
		for ( int i = 0 ; i < chars.length-1 ; i ++ )
		{
			_key.put ( (char)('a'+i)  ,  chars[i]  );
		}
		_key.put (  ' '  , chars[26] );
	}

	public double get_fitness ( )
	{
		return _fitness;
	}


	public void set_fitness ( double _fitness )
	{
		this._fitness = _fitness;
	}


	public HashMap < Character , Character > get_key ( )
	{
		return _key;
	}


	public void set_key ( HashMap<Character , Character> _key )
	{
		this._key = _key;
	}


	public Gen ( Gen toCopy )
	{
		this._fitness = toCopy.get_fitness ( );
		this._key=new HashMap(toCopy._key);
	}
}
