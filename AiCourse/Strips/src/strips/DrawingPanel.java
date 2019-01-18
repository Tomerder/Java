/**
 * 
 * HW 2 
 * Orel shai 052988912
 * Tomer dery  060628914
 * 
 * 
 */
package strips;


import javax.swing.*;
import org.w3c.dom.css.RGBColor;
import java.awt.*;
import java.awt.event.*;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Vector;


/**
 * 
 * @author orel_shai
 */
public class DrawingPanel extends JPanel implements MouseListener , MouseMotionListener
{

	static DrawingPanel	_instance			= null;
	Point				firstPoint			= new Point ( );	// stores the first point ,we will make rectangular with this point and the last point
	BufferedImage		image;
	Graphics2D			g2d;
	int					brushSize			= 3;
	boolean				_drawFurniture		= false;
	boolean				_moveFurniture		= false;
	boolean				_selectFurniture	= false;
	boolean				_rotateClockWize	= false;
	int					_furnitureNumber;
	Point				dimension;


	public void set_rotateClockWize ( boolean clockWize )
	{
		_rotateClockWize = clockWize;
	}


	public void set_moveFurniture ( boolean furniture )
	{
		_moveFurniture = furniture;
	}


	private DrawingPanel ( )
	{
		dimension = new Point ( );
		firstPoint = null;
		// dragPoint = null;
		setPreferredSize ( new Dimension ( 800 , 600 ) );
		addMouseListener ( this );
	}


	static DrawingPanel GetInstance ( )
	{
		if ( _instance == null )
		{
			_instance = new DrawingPanel ( );
		}
		return _instance;
	}


	public void SetBorder ( Color a , Color b )
	{
		_instance.setBorder ( BorderFactory.createEtchedBorder ( Color.black , Color.blue ) );
	}


	public void ClearPaint ( )
	{
		_instance.clearPaint ( );
	}


	public int Read ( int x , int y )
	{
		int currentcolor = image.getRGB ( x , y );
		return currentcolor;
	}


	public void Write ( int x , int y , int _new )
	{
		image.setRGB ( x , y , _new );
		repaint ( );
	}


	public void mouseClicked ( MouseEvent e )
	{
	}


	public void mouseEntered ( MouseEvent e )
	{
	}


	public void mouseExited ( MouseEvent e )
	{
	}


	public void mouseMoved ( MouseEvent e )
	{
	}


	public void mousePressed ( MouseEvent e )
	{
		// saves the first point for the sealed polynom option
		if ( _drawFurniture == true )
		{
			// firstRoom=false;
			double x = e.getX ( );
			int intx = ( int ) Math.floor ( x );
			double y = e.getY ( );
			int inty = ( int ) Math.floor ( y );
			firstPoint = new Point ( intx , inty );
			Rectangle room1size = RoomI._getInstance ( ).get_roomSize ( );
			// dragging = true;
			/**
			 * בדיקה שהנקודה שלחצנו עליה נמצאת בגבולות של החדר הראשון
			 */
			if ( ! room1size.contains ( firstPoint ) )
			{
				errorMsg ( "The furniture that you've selected is not in the first room bounderies" );
				// dragging = false;
				_drawFurniture = false;
			}
			else
			{
				Point room1 = RoomI._getInstance ( ).get_roomSize ( ).getLocation ( );
				int room1hight = room1.y;
				int room1width = room1.x;
				while ( room1hight < inty )
				{
					room1hight = room1hight + 40;
				}
				room1hight = room1hight - 40;
				while ( room1width < intx )
				{
					room1width = room1width + 40;
				}
				room1width = room1width - 40;
				firstPoint.x = room1width;
				firstPoint.y = room1hight;
				Point temp = new Point ( dimension.x * 40 + firstPoint.x , dimension.y * 40 + firstPoint.y );
				draw ( temp );
			}
		}
	}


	public void mouseReleased ( MouseEvent e )
	{
		// if ( _drawFurniture == true )
		// {
		// double x = e.getX ( );
		// int intx = ( int ) Math.floor ( x );
		// double y = e.getY ( );
		// int inty = ( int ) Math.floor ( y );
		// Point p = new Point ( intx , inty );
		// draw ( p );
		// // dragging = false;
		// }
		if ( _selectFurniture )
		{
			double x = e.getX ( );
			int intx = ( int ) Math.floor ( x );
			double y = e.getY ( );
			int inty = ( int ) Math.floor ( y );
			Point selectFur = new Point ( intx , inty );
			_selectFurniture = false;
			_moveFurniture = true;
			/**
			 * בדיקה שהמשתמש בחר ברהיט שנמצא בחדר הראשון
			 */
			boolean furIsInTheRoom = false;
			Vector < Furniture > furInRoom = RoomI._getInstance ( ).get_furnituresInRoom ( );
			for ( int i = 0 ; i < furInRoom.size ( ) ; i ++ )
			{
				Furniture temp = furInRoom.get ( i );
				if ( temp.get_rect ( ).contains ( selectFur ) )
				{
					_furnitureNumber = temp.get_uniquNumber ( );
					furIsInTheRoom = true;
				}
			}
			// אם הרהיט לא בחדר
			if ( ! furIsInTheRoom )
			{
				errorMsg ( "The furniture that you've selected is not in the first room or not exsists at all" );
				_selectFurniture = false;
				_moveFurniture = false;
			}
		}
		else if ( _moveFurniture )
		{
			Rectangle room2size = RoomII._getInstance ( ).get_roomSize ( );
			double x = e.getX ( );
			int intx = ( int ) Math.floor ( x );
			double y = e.getY ( );
			int inty = ( int ) Math.floor ( y );
			Point room2 = RoomII._getInstance ( ).get_roomSize ( ).getLocation ( );
			int room2hight = room2.y;
			int room2width = room2.x;
			while ( room2hight < inty )
			{
				room2hight = room2hight + 40;
			}
			room2hight = room2hight - 40;
			while ( room2width < intx )
			{
				room2width = room2width + 40;
			}
			room2width = room2width - 40;
			Point p = new Point ( room2width , room2hight );
			draw ( p );
		}
	}


	public void mouseDragged ( MouseEvent e )
	{
	}


	public void draw ( Point p )// draws the line from one point to another
	{
		if ( _drawFurniture )
		{
			_drawFurniture = false;
			drawFurniture ( p );
		}
		if ( _moveFurniture )
		{
			_moveFurniture = false;
			moveFurniture ( p );
		}
	}


	public void setPaintColor ( Color color )
	{
		g2d.setColor ( color );
	}


	public void clearPaint ( )
	{
		g2d.setColor ( Color.white );
		g2d.fillRect ( 0 , 0 , getWidth ( ) , getHeight ( ) );
		repaint ( );
		g2d.setColor ( Color.black );
		firstPoint = new Point ( );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawRect ( 20 , 20 , 360 , 360 );
		// repaint ( );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawRect ( 380 , 220 , 400 , 160 );
		// repaint ( );
		g2d.setColor ( Color.white );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawLine ( 380 , 260 , 380 , 340 );
		g2d.setColor ( Color.black );
		_drawFurniture = false;
		_moveFurniture = false;
		_selectFurniture = false;
		_rotateClockWize = false;
	}


	public void increaseBrushSize ( )
	{
		brushSize = brushSize + 2;
	}


	public void decreaseBrushSize ( )
	{
		brushSize = brushSize - 2;
		if ( brushSize <= 0 )
			brushSize = 1;
	}


	public boolean is_drawFurniture ( )
	{
		return _drawFurniture;
	}


	public void set_drawFurniture ( boolean furniture )
	{
		_drawFurniture = furniture;
	}


	protected void paintComponent ( Graphics g )
	{
		super.paintComponent ( g );
		if ( image == null )
		{
			image = ( BufferedImage ) createImage ( getWidth ( ) , getHeight ( ) );
			g2d = ( Graphics2D ) image.getGraphics ( );
			g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
			g2d.setColor ( Color.white );
			g2d.fillRect ( 0 , 0 , getWidth ( ) , getHeight ( ) );
			g2d.setColor ( Color.black );
			/**
			 * painting the 2 rooms and the door
			 * 
			 */
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawRect ( 20 , 20 , 360 , 360 );
			// repaint ( );
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawRect ( 380 , 220 , 400 , 160 );
			// repaint ( );
			g2d.setColor ( Color.white );
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawLine ( 380 , 260 , 380 , 340 );
			g2d.setColor ( Color.black );
			// repaint ( );
		}
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawRect ( 20 , 20 , 360 , 360 );
		// repaint ( );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawRect ( 380 , 220 , 400 , 160 );
		// repaint ( );
		g2d.setColor ( Color.white );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawLine ( 380 , 260 , 380 , 340 );
		g2d.setColor ( Color.black );
		g.drawImage ( image , 0 , 0 , this );
	}


	public boolean is_selectFurniture ( )
	{
		return _selectFurniture;
	}


	public void set_selectFurniture ( boolean furniture )
	{
		_selectFurniture = furniture;
	}


	public void errorMsg ( String msg )
	{
		JFrame frame = new JFrame ( "Error" );// open a frame that indicates the the point pressed on is not inside the polygon
		JLabel label = new JLabel ( msg , JLabel.CENTER );
		frame.add ( label );
		frame.setDefaultCloseOperation ( JFrame.HIDE_ON_CLOSE );
		frame.setSize ( 650 , 300 ); // width=350, height=200
		frame.setAlwaysOnTop ( true );
		frame.setVisible ( true ); // Display the frame
	}


	private void drawFurniture ( Point p )
	{
		double x = p.getX ( );
		int intx = ( int ) Math.floor ( x );
		double y = p.getY ( );
		int inty = ( int ) Math.floor ( y );
		double fx = firstPoint.getX ( );
		int intfx = ( int ) Math.floor ( fx );
		double fy = firstPoint.getY ( );
		int intfy = ( int ) Math.floor ( fy );
		/**
		 * לבדוק האם בכלל יש מקום לרהיט הזה האם הרהיא מתנגש עם רהיטים אחרים והאם הוא בגבולות החדר
		 */
		int xMax = Math.max ( intfx , intx );
		int xMin = Math.min ( intfx , intx );
		int yMax = Math.max ( intfy , inty );
		int yMin = Math.min ( intfy , inty );
		Furniture newFur = new Furniture ( );
		newFur.set_uniquNumber ( RoomI._getInstance ( ).GetUniqueNumber ( ) );
		Rectangle newRectFur = new Rectangle ( new Point ( xMin , yMin ) , new Dimension ( xMax - xMin , yMax - yMin ) );
		newFur.set_rect ( newRectFur );
		/**
		 * בדיקה שהרהיט לא יצא מגבולות החדר
		 */
		Rectangle room1size = RoomI._getInstance ( ).get_roomSize ( );
		int counter = 0;// +1 for inside the room +1 for not intersect with other furnitures
		if ( room1size.contains ( newRectFur ) )
		{
			counter ++ ;
		}
		/**
		 * בדיקה שהרהיט לא מתנגש עם רהיטים אחרים
		 */
		counter ++ ;
		Vector < Furniture > furInRoom = RoomI._getInstance ( ).get_furnituresInRoom ( );
		for ( int i = 0 ; i < furInRoom.size ( ) ; i ++ )
		{
			Furniture temp = furInRoom.get ( i );
			if ( temp.get_rect ( ).intersects ( newRectFur ) )
			{
				counter -- ;
			}
		}
		_drawFurniture = false;
		if ( counter == 2 )
		{
			RoomI._getInstance ( ).AddFurniture ( newFur ); // מוסיף את הרהיט לוקטור של הרהיטים
			g2d.setColor ( Color.GREEN );
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawRect ( xMin , yMin , xMax - xMin , yMax - yMin );
			g2d.setColor ( Color.black );
			repaint ( );
		}
		else
		{
			errorMsg ( "The furniture that you've inserts is not in the first room bounderies or is intersecting with another furniture" );
		}
	}


//מצייר רהיט בצבע שהוא מקבל ובמקום שהוא מקבל	
	public void drawFurniture2 ( Rectangle rect1 , Color c)
	{
	 

		
		Rectangle toPaint = rect1;
		g2d.setColor (  c );
		g2d.setStroke ( new BasicStroke ( brushSize ) );
		g2d.drawRect ( ( int ) toPaint.getMinX ( ) , ( int ) toPaint.getMinY ( ) , ( int ) ( toPaint.getMaxX ( ) - toPaint.getMinX ( ) ) , ( int ) ( toPaint.getMaxY ( ) - toPaint.getMinY ( ) ) );
		g2d.setColor ( Color.black );
		repaint ( );
		
		
		
		// צובע חדרים מחדש
		/*
		Rectangle room2size = RoomII._getInstance ( ).get_roomSize ( );
		double x = e.getX ( );
		int intx = ( int ) Math.floor ( x );
		double y = e.getY ( );
		int inty = ( int ) Math.floor ( y );
		Point room2 = RoomII._getInstance ( ).get_roomSize ( ).getLocation ( );
		int room2hight = room2.y;
		int room2width = room2.x;
		while ( room2hight < inty )
		{
			room2hight = room2hight + 40;
		}
		room2hight = room2hight - 40;
		while ( room2width < intx )
		{
			room2width = room2width + 40;
		}
		room2width = room2width - 40;
		Point p = new Point ( room2width , room2hight );
		draw ( p );
		
		
		
		
		Rectangle room1size = RoomI._getInstance ( ).get_roomSize ( );
		x = e.getX ( );
		 intx = ( int ) Math.floor ( x );
		 y = e.getY ( );
		int inty = ( int ) Math.floor ( y );
		Point room2 = RoomII._getInstance ( ).get_roomSize ( ).getLocation ( );
		int room2hight = room2.y;
		int room2width = room2.x;
		while ( room2hight < inty )
		{
			room2hight = room2hight + 40;
		}
		room2hight = room2hight - 40;
		while ( room2width < intx )
		{
			room2width = room2width + 40;
		}
		room2width = room2width - 40;
		Point p = new Point ( room2width , room2hight );
		draw ( p );
		
		*/
	}
	
	
	
	
	
	
	
	private void moveFurniture ( Point p )
	{
		double x = p.getX ( );
		int intx = ( int ) Math.floor ( x );
		double y = p.getY ( );
		int inty = ( int ) Math.floor ( y );
		Furniture temp = null;
		Vector < Furniture > furInRoom = RoomI._getInstance ( ).get_furnituresInRoom ( );
		for ( int i = 0 ; i < furInRoom.size ( ) ; i ++ )
		{
			temp = furInRoom.get ( i );
			if ( temp.get_uniquNumber ( ) == _furnitureNumber )
			{
				break;
			}
		}
		Rectangle newRect = null;
		int counter = 0;
		if ( _rotateClockWize )
		{
			Rectangle oldRect = temp.get_rect ( );
			Dimension oldDimension = oldRect.getSize ( );
			// because we rotate the furniture by 90 degrees , the width becomes the height and vice versa
			Dimension newDimension = new Dimension ( ( int ) oldDimension.getHeight ( ) , ( int ) oldDimension.getWidth ( ) );
			Point clockWize = new Point ( intx , inty );
			newRect = new Rectangle ( clockWize , newDimension );
			/**
			 * בדיקה שהרהיט לא יצא מגבולות החדר
			 */
			Rectangle room2size = RoomII._getInstance ( ).get_roomSize ( );
			counter = 0;// +1 for inside the room +1 for not intersect with other furnitures
			if ( room2size.contains ( newRect ) )
			{
				counter ++ ;
			}
			/**
			 * בדיקה שהרהיט לא מתנגש עם רהיטים אחרים
			 */
			counter ++ ;
			Vector < Furniture > furInRoom2 = RoomII._getInstance ( ).get_furnituresInRoom ( );
			for ( int i = 0 ; i < furInRoom2.size ( ) ; i ++ )
			{
				Furniture temp2 = furInRoom2.get ( i );
				if ( temp2.get_rect ( ).intersects ( newRect ) )
				{
					counter -- ;
				}
			}
			// _rotateClockWize = false;
			if ( counter == 2 )
			{
				Furniture newFur = new Furniture ( );
				newFur.set_uniquNumber ( _furnitureNumber );
				newFur.set_rect ( newRect );
				RoomII._getInstance ( ).AddFurniture ( newFur );
			}
			else
			{
				errorMsg ( "The furniture that you want to move is not in the second room bounderies or is intersecting with another furniture" );
			}
		}
		else
		{
			Rectangle oldRect = temp.get_rect ( );
			Dimension oldDimension = oldRect.getSize ( );
			// because we rotate the furniture by 90 degrees , the width becomes the height and vice versa
			newRect = new Rectangle ( new Point ( intx , inty ) , oldDimension );
			/**
			 * בדיקה שהרהיט לא יצא מגבולות החדר
			 */
			Rectangle room2size = RoomII._getInstance ( ).get_roomSize ( );
			counter = 0;// +1 for inside the room +1 for not intersect with other furnitures
			if ( room2size.contains ( newRect ) )
			{
				counter ++ ;
			}
			/**
			 * בדיקה שהרהיט לא מתנגש עם רהיטים אחרים
			 */
			counter ++ ;
			Vector < Furniture > furInRoom2 = RoomII._getInstance ( ).get_furnituresInRoom ( );
			for ( int i = 0 ; i < furInRoom2.size ( ) ; i ++ )
			{
				Furniture temp2 = furInRoom2.get ( i );
				if ( temp2.get_rect ( ).intersects ( newRect ) )
				{
					counter -- ;
				}
			}
			if ( counter == 2 )
			{
				Furniture newFur = new Furniture ( );
				newFur.set_uniquNumber ( _furnitureNumber );
				newFur.set_rect ( newRect );
				RoomII._getInstance ( ).AddFurniture ( newFur );
			}
			else
			{
				errorMsg ( "The furniture that you want to move is not in the second room bounderies or is intersecting with another furniture" );
			}
		}
		if ( counter == 2 )
		{
			/**
			 * remove the furniture from the first room
			 */
			Rectangle toDelRec = temp.get_rect ( );
			g2d.setColor ( Color.white );
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawRect ( ( int ) toDelRec.getMinX ( ) , ( int ) toDelRec.getMinY ( ) , ( int ) ( toDelRec.getMaxX ( ) - toDelRec.getMinX ( ) ) , ( int ) ( toDelRec.getMaxY ( ) - toDelRec.getMinY ( ) ) );
			g2d.setColor ( Color.black );
			repaint ( );
			// furInRoom.remove ( temp );
			RoomI._getInstance ( ).set_furnituresInRoom ( furInRoom );
			/**
			 * remove the furniture from the first room
			 */
			g2d.setColor ( Color.GREEN );
			_drawFurniture = false;
			g2d.setStroke ( new BasicStroke ( brushSize ) );
			g2d.drawRect ( ( int ) newRect.getMinX ( ) , ( int ) newRect.getMinY ( ) , ( int ) ( newRect.getMaxX ( ) - newRect.getMinX ( ) ) , ( int ) ( newRect.getMaxY ( ) - newRect.getMinY ( ) ) );
			g2d.setColor ( Color.black );
			repaint ( );
			_moveFurniture = false;
		}
	}


	public Point getDimension ( )
	{
		return dimension;
	}


	public void setDimension ( Point dimension )
	{
		this.dimension = dimension;
	}
}