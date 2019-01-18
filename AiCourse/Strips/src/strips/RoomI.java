/**
 * 
 * HW 2 
 * Orel shai 052988912
 * Tomer dery  060628914
 * 
 * 
 */
package strips;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;


public class RoomI
{

	private Rectangle				_roomSize;
	private static RoomI			_instance;
	private int						_furnitureCounter;
	private Vector < Furniture >	_furnituresInRoomBeforeMoving;	// כל הרהיטים שנמצאים בחדר הראשון
	Point							_offset;						// בשביל לדעת איפה מצויירים הדברים
	static final int				_oneUnit	= 40;


	public Vector < Furniture > get_furnituresInRoom ( )
	{
		return _furnituresInRoomBeforeMoving;
	}


	public void set_furnituresInRoom ( Vector < Furniture > inRoom )
	{
		_furnituresInRoomBeforeMoving = inRoom;
	}


	public Rectangle get_roomSize ( )
	{
		return _roomSize;
	}


	private RoomI ( )
	{
		_offset = new Point ( 20 , 20 );
		_roomSize = new Rectangle ( _offset );
		_roomSize.setSize ( 360 , 360 );
		_furnitureCounter = 0;
		_furnituresInRoomBeforeMoving = new Vector < Furniture > ( );
	}


	public static RoomI _getInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new RoomI ( );
		}
		return _instance;
	}


	public int GetUniqueNumber ( )
	{
		_furnitureCounter ++ ;
		return _furnitureCounter;
	}


	public void AddFurniture ( Furniture toAdd )
	{
		_furnituresInRoomBeforeMoving.add ( toAdd );
	}


	public void RemoveFurniture ( Furniture toRemove )
	{
		if ( _furnituresInRoomBeforeMoving.contains ( toRemove ) )
		{
			_furnituresInRoomBeforeMoving.remove ( toRemove );
		}
	}
}
