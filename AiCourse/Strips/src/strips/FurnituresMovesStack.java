package strips;





import java.util.Vector;


/**
 * רשימה של כל המצבים והפעולות
 * 
 * @author Orel_Shai
 * 
 */
public class FurnituresMovesStack
{

	private Vector < Furniture >	_stack;
	private static FurnituresMovesStack		_instance;


	private FurnituresMovesStack ( )
	{
		_stack = new Vector < Furniture > ( );
	}


	public static FurnituresMovesStack getInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new FurnituresMovesStack ( );
		}
		return _instance;
	}


	public void addToStack ( Furniture toadd )
	{
		_stack.add ( toadd   );
	}


	public Furniture pop ( )
	{
		Furniture toReturn=new Furniture();
		toReturn=(_stack.remove ( _stack.size ( ) - 1 ));
		
		return toReturn;
	}


	public Vector < Furniture > getStack ( )
	{
		return _stack;
	}
}
