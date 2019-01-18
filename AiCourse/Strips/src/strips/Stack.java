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
public class Stack
{

	private Vector < String >	_stack;
	private static Stack		_instance;


	private Stack ( )
	{
		_stack = new Vector < String > ( );
	}


	public static Stack getInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new Stack ( );
		}
		return _instance;
	}


	public void addToStack ( String toadd )
	{
		_stack.add ( toadd + '\n' );
	}


	public void pop ( )
	{
		_stack.remove ( _stack.size ( ) - 1 );
	}


	public Vector < String > getStack ( )
	{
		return _stack;
	}
}
