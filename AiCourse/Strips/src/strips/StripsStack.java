/**
 * 
 * HW 2 
 * Orel shai 052988912
 * Tomer dery  060628914
 * 
 * 
 */
package strips;


import java.util.Vector;


/**
 * רשימה של כל המצבים והפעולות
 * 
 * @author Orel_Shai
 * 
 */
public class StripsStack
{

	private Vector < String >	_stack;
	private static StripsStack		_instance;


	private StripsStack ( )
	{
		_stack = new Vector < String > ( );
	}


	public static StripsStack getInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new StripsStack( );
		}
		return _instance;
	}


	public void addToStack ( String toadd )
	{
		_stack.add ( toadd );
		System.out.println (toadd + '\n');
	}


	public String pop ( )
	{
		return _stack.remove ( _stack.size ( ) - 1 );
	}


	public Vector < String > getStack ( )
	{
		return _stack;
	}
}
