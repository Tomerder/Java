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


public class RoomII
{

	private Rectangle				_roomSize;
	private static RoomII			_instance;
	private Vector < Furniture >	_furnituresInRoom;		// כל הרהיטים שנמצאים בחדר הראשון
	private Point					_doorCoordibate1;
	private Point					_doorCoordibate2;		// שתי משתנים אלו מייצגים את שתי האינדקסים שביניהם יש את הדלת
	private int						_distanceBetweenDoor;
	private Point					_offset;


	public Vector < Furniture > get_furnituresInRoom ( )
	{
		return _furnituresInRoom;
	}


	public Point get_doorCoordibate1 ( )
	{
		return _doorCoordibate1;
	}


	public void set_doorCoordibate1 ( Point coordibate1 )
	{
		_doorCoordibate1 = coordibate1;
	}


	public Point get_doorCoordibate2 ( )
	{
		return _doorCoordibate2;
	}


	public void set_doorCoordibate2 ( Point coordibate2 )
	{
		_doorCoordibate2 = coordibate2;
	}


	public Rectangle get_roomSize ( )
	{
		return _roomSize;
	}


	private RoomII ( )
	{
		_offset = new Point ( 380 , 220 );
		_roomSize = new Rectangle ( _offset );
		_roomSize.setSize ( 400 , 160 );
		_furnituresInRoom = new Vector < Furniture > ( );
		_doorCoordibate1 = new Point ( 380 , 260 );
		_doorCoordibate2 = new Point ( 380 , 340 );
		_distanceBetweenDoor = 80;
	}


	public static RoomII _getInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new RoomII ( );
		}
		return _instance;
	}


	public void AddFurniture ( Furniture toAdd )
	{
		_furnituresInRoom.add ( toAdd );
	}


	public int get_distanceBetweenDoor ( )
	{
		return _distanceBetweenDoor;
	}


	
	public void set_furnituresInRoom ( Vector < Furniture > inRoom )
	{
		_furnituresInRoom = inRoom;
	}
}
