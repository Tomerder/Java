/**
 * 
 * HW 2 
 * Orel shai 052988912
 * Tomer dery  060628914
 * 
 * 
 */
package strips;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;


public class Furniture
{

	private int			_uniquNumber;	// המספר הייחודי של הרהיט
	private Rectangle	_rect;			// מלבן שמייצג את הצורה של הרהיט


	public Rectangle get_rect ( )
	{
		return _rect;
	}


	public void set_rect ( Rectangle _rect )
	{
		this._rect = _rect;
	}


	public int get_uniquNumber ( )
	{
		return _uniquNumber;
	}


	public void set_uniquNumber ( int number )
	{
		_uniquNumber = number;
	}
}
