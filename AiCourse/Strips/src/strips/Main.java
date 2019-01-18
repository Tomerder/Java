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
import strips.DrawingPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class Main extends JPanel implements ActionListener , MouseListener , MouseMotionListener
{

	JPanel					_southPanel;
	JPanel					_northPanel;
	JButton					_insertFurniture;
	JButton					_strips;
	JButton					_moveFurnitures;
	JCheckBox				_rotateClockWize;
	TextArea				textArea;
	boolean					_stopStrips	= false;		// if ==true then there will not be any recursion calling
	Vector < Furniture >	_currentStateOfFarnitures;	// ������ ������ �� �������
	Vector < Furniture >	_wantedStateOfFarnitures;	// ������ ����� �� �������
	String					textInTextarea;
	JLabel					_widthText;
	JTextField				_widthSize;
	JLabel					_hightText;
	JTextField				_hightSize;
	JButton					_stepByStep;
	JButton					_dummy1;
	JButton					_dummy2;
	JButton					_dummy3;

	private String			_top;

	public Main ( )
	{
		_currentStateOfFarnitures = RoomII._getInstance ( ).get_furnituresInRoom ( );
		_wantedStateOfFarnitures = RoomI._getInstance ( ).get_furnituresInRoom ( );
		_southPanel = new JPanel ( );
		_southPanel.setLayout ( new GridLayout ( 0 , 4 , 2 , 2 ) );// set the south layout of the buttons inside the panel
		_northPanel = new JPanel ( );
		_northPanel.setLayout ( new GridLayout ( 0 , 1 , 2 , 2 ) );// set the north layout of the buttons inside the panel
		_rotateClockWize = new JCheckBox ( );
		_rotateClockWize.setText ( "Rotate top left corner by 90 degrees" );
		_rotateClockWize.addActionListener ( this );
		_southPanel.add ( _rotateClockWize );
		textArea = new TextArea ( );
		textArea.setEditable ( false );
		_northPanel.add ( textArea );
		_insertFurniture = addButton ( Color.black );
		_moveFurnitures = addButton ( Color.black );
		_strips = addButton ( Color.black );
		_stepByStep = addButton ( Color.black );
		_stepByStep.setText ( "Press to see next step" );
		_dummy1= addButton ( Color.black );
		_dummy2= addButton ( Color.black );
		_dummy3= addButton ( Color.black );

		_strips.setText ( "Start the Strips algorithm" );
		_insertFurniture.setText ( "Insert a Furniture" );
		_moveFurnitures.setText ( "Move Furnitures" );
		setPreferredSize ( new Dimension ( 800 , 700 ) );//
		_widthText = new JLabel ( "Insert width:" );
		_widthSize = new JTextField ( );
		_widthSize.setEnabled ( true );
		_hightText = new JLabel ( "Insert hight:" );
		_hightSize = new JTextField ( );
		_hightSize.setEnabled ( true );
		_widthSize.addActionListener ( this );
		_hightSize.addActionListener ( this );
		_southPanel.add ( _widthText );
		_southPanel.add ( _widthSize );
		_southPanel.add ( _hightText );
		_southPanel.add ( _hightSize );
		addMouseListener ( this );
		addMouseMotionListener ( this );
		setLayout ( new BorderLayout ( ) );
		textArea.setText ( "Welcome to the Strips program."+'\n' +'\n' 
				+" Inorder to insert furnitures please fill in the choosen lengths in the Length and Width fields" + '\n' + '\n'
		+  "And click on the Insert a Furniture button and click on the desired location in the first room(the left room)." + '\n'+ '\n'+
		 " Inorder to move a furniture click on the Move a Furniture button and then click inside the furniture that you want to move"
		+'\n' +'\n' + "and the click on the desired location in the second room. "
		+'\n'  +'\n' + "If you want to  move the furniture from the first room to the second room in a rotate way check\\uncheck the Rotate to left corner button"+
		'\n' + '\n' + "Click on the Start the strips algorithm to late the algorithm run on its own or click on the " +'\n'   +'\n'  + " Press to see next step button each time" +
			+'\n'  +'\n' +	" you want to conitnue to the next step"  +'\n' +'\n');
		
		add ( "Center" , DrawingPanel.GetInstance ( ) );
		add ( "South" , _southPanel );
		add ( "North" , _northPanel );
	}


	private JButton addButton ( Color color )// initialize the button
	{
		JButton button = new JButton ( );
		_southPanel.add ( button );
		button.addActionListener ( this );// this=frame
		return button;
	}


	public static void main ( String [ ] args )
	{
		JFrame frame = new JFrame ( );
		Main dlp = new Main ( );
		frame.add ( dlp );
		frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		frame.pack ( );
		frame.setVisible ( true );
		frame.setResizable ( true );
	}


	@Override
	public void actionPerformed ( ActionEvent arg0 )
	{
		Object pressedOn = arg0.getSource ( );
		int hight2 = 0;
		int width2 = 0;
		if ( pressedOn.equals ( _insertFurniture ) )
		{
			boolean dimentionsInserted = false;
			if ( ! _hightSize.getText ( ).isEmpty ( ) && ! _widthSize.getText ( ).isEmpty ( ) )
			{
				hight2 = Integer.valueOf ( _hightSize.getText ( ) );
				width2 = Integer.valueOf ( _widthSize.getText ( ) );
				DrawingPanel.GetInstance ( ).setDimension ( new Point ( hight2 , width2 ) );
				DrawingPanel.GetInstance ( ).set_drawFurniture ( true );
			}
			else
			{
				DrawingPanel.GetInstance ( ).errorMsg ( "First enter furniture Dimentions Please" );
			}
		}
		else if ( pressedOn.equals ( _moveFurnitures ) )
		{
			DrawingPanel.GetInstance ( ).set_selectFurniture ( true );
		}
		else if ( pressedOn.equals ( _rotateClockWize ) )
		{
			DrawingPanel.GetInstance ( ).set_rotateClockWize ( _rotateClockWize.isSelected ( ) );
		}
		else if ( pressedOn.equals ( _strips ) )
		{
			textArea.setText ( "" );
			/**
			 * ���� �� ������� ����� ����� ��� ������ ����
			 */
			Furniture temp = new Furniture ( );
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )
			{
				for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) - 1 ; i ++ )
				{
					if ( _currentStateOfFarnitures.elementAt ( i ).get_rect ( ).getX ( ) > _currentStateOfFarnitures.elementAt ( i + 1 ).get_rect ( ).getX ( ) )
					{
						temp = _currentStateOfFarnitures.elementAt ( i + 1 );
						_currentStateOfFarnitures.set ( i + 1 , _currentStateOfFarnitures.elementAt ( i ) );
						_currentStateOfFarnitures.set ( i , temp );
					}
				}
			}
			/**
			 * ����� ��� ������� ������ ����� ����
			 */
			boolean allFurnituresCrossThrowDoor = true;
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )
			{
				int hight = _currentStateOfFarnitures.get ( j ).get_rect ( ).height;
				int width = _currentStateOfFarnitures.get ( j ).get_rect ( ).width;
				int distanceBetweenDoor = RoomII._getInstance ( ).get_distanceBetweenDoor ( );
				if ( hight > distanceBetweenDoor && width > distanceBetweenDoor )
				{
					/**
					 * ����� ����� ���� �������� �� ������
					 */
					allFurnituresCrossThrowDoor = false;
					DrawingPanel.GetInstance ( ).errorMsg ( "Furniture Number : " + _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) + " is to big to cross throw the door" );
					break;
				}
			}
			if ( allFurnituresCrossThrowDoor )
			{
				strips ( );
			}
			System.out.println ( "over" );
		}
		else if ( pressedOn.equals ( _stepByStep ) )
		{
			textArea.setText ( "" );

			/**
			 * ���� �� ������� ����� ����� ��� ������ ����
			 */
			Furniture temp = new Furniture ( );
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )
			{
				for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) - 1 ; i ++ )
				{
					if ( _currentStateOfFarnitures.elementAt ( i ).get_rect ( ).getX ( ) > _currentStateOfFarnitures.elementAt ( i + 1 ).get_rect ( ).getX ( ) )
					{
						temp = _currentStateOfFarnitures.elementAt ( i + 1 );
						_currentStateOfFarnitures.set ( i + 1 , _currentStateOfFarnitures.elementAt ( i ) );
						_currentStateOfFarnitures.set ( i , temp );
					}
				}
			}
			/**
			 * ����� ��� ������� ������ ����� ����
			 */
			boolean allFurnituresCrossThrowDoor = true;
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )
			{
				int hight = _currentStateOfFarnitures.get ( j ).get_rect ( ).height;
				int width = _currentStateOfFarnitures.get ( j ).get_rect ( ).width;
				int distanceBetweenDoor = RoomII._getInstance ( ).get_distanceBetweenDoor ( );
				if ( hight > distanceBetweenDoor && width > distanceBetweenDoor )
				{
					/**
					 * ����� ����� ���� �������� �� ������
					 */
					allFurnituresCrossThrowDoor = false;
					DrawingPanel.GetInstance ( ).errorMsg ( "Furniture Number : " + _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) + " is to big to cross throw the door" );
					break;
				}
			}
			if ( allFurnituresCrossThrowDoor )
			{
				strips2 ( );
			}
		}
	}


	private boolean strips ( )
	{
		/**
		 * ���� �����
		 */
		// �� �� ������� ������� �� ������ ������ �� ������� �������� �� ������ ������ �� ������� ����� �� ������
		Furniture currentFurniture = null;
		boolean finish = true;
		for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
		{
			for ( int j = 0 ; j < _wantedStateOfFarnitures.size ( ) ; j ++ )
			{
				if ( _currentStateOfFarnitures.get ( i ).get_uniquNumber ( ) == _wantedStateOfFarnitures.get ( j ).get_uniquNumber ( ) )
				{
					if ( ! ( _currentStateOfFarnitures.get ( i ).get_rect ( ).equals ( _wantedStateOfFarnitures.get ( j ).get_rect ( ) ) ) ) // �� ����� ������
					{
						currentFurniture = _currentStateOfFarnitures.get ( i ); // ����� ��� ���� ������
						finish = false;
						i = _currentStateOfFarnitures.size ( );
						j = _wantedStateOfFarnitures.size ( );
					}
				}
			}
		}
		if ( finish )
		{
			System.out.println ( "finish successful" );
			textArea.append ( "finish successful" );

			return true;
		}
		// �� ������� ���� ����� ����� �� ���� ����� ������ ������ ��� ���� ������
		if ( StripsStack.getInstance ( ).getStack ( ).size ( ) == 0 )
		{
			StripsStack.getInstance ( ).getStack ( ).add ( "A " + currentFurniture.get_uniquNumber ( ) );
		}
		// ��� ���� �� ���� ���� �������
		_top = StripsStack.getInstance ( ).pop ( ); // elementAt ( StripsStack.getInstance ( ).getStack ( ).size ( )-1 ); // ��� �������
		if ( _top.charAt ( 0 ) == 'A' ) // �� ���� ������� ���� ���� ����
		{
			return nextStep ( );
		}
		else if ( _top.charAt ( 0 ) == 'B' ) // �� ���� ������� ���� ���� ���� ��� ���� ����
		{
			// ����� �� ������ ������ ������ �� ����� �����
			// ����� �� ���� �� ������ ������
			// ������ ����� ����� ������� ������� ��� ����� ����� ���� �� ������ �� ������
			String [ ] dividedString = _top.split ( " " );
			int furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
			String direction = dividedString [ 2 ];
			Furniture curFurniture = null;
			for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
			{
				if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber ) // ���� �� ����� ����� ����� ����
				{
					curFurniture = _currentStateOfFarnitures.elementAt ( i );
					if ( direction.equals ( "left" ) )
					{
						moveLeft ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   right" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    right" + '\n' );
					}
					if ( direction.equals ( "right" ) )
					{
						moveRight ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   left" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    left" + '\n' );
					}
					if ( direction.equals ( "up" ) )
					{
						moveUp ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   down" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    down" + '\n' );
					}
					if ( direction.equals ( "down" ) )
					{
						moveDown ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   up" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    up" + '\n' );
					}
					if ( direction.equals ( "rotate+" ) )
					{
						moveRotateClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   CounterClockwise" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    CounterClockwise" + '\n' );
					}
					if ( direction.equals ( "rotate-" ) )
					{
						moveRotateCounterClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves    Clockwise" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    Clockwise" + '\n' );
					}
				}
			}
			for ( int j = 0 ; j < _wantedStateOfFarnitures.size ( ) ; j ++ )
			{
				if ( _wantedStateOfFarnitures.get ( j ).get_uniquNumber ( ) == furnitureNumber )
				{
					if ( ( curFurniture.get_rect ( ).equals ( _wantedStateOfFarnitures.get ( j ).get_rect ( ) ) ) ) // �� ����� ������ ���� strips()
					{
						if ( strips ( ) == false )
						{
							return false;
						}
						else
						{
							return true;
						}
					}
					else
					{
						return nextStep ( );
					}
				}
			}
		}
		else if ( _top.charAt ( 0 ) == 'C' ) // �� ���� ������� ���� ���� ���� ��� ���� ����
		{
			// ����� �� ������ ������ ������ �� ����� �����
			// ����� �� ���� �� ������ ������
			// ������ ����� ����� ������� ������� ��� ����� ����� ���� �� ������ �� ������
			String [ ] dividedString = _top.split ( " " );
			int furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
			String direction = dividedString [ 2 ];
			Furniture curFurniture = null;
			for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
			{
				if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber ) // ���� �� ����� ����� ����� ����
				{
					curFurniture = _currentStateOfFarnitures.elementAt ( i );
					if ( direction.equals ( "left" ) )
					{
						moveLeft ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   right" );
					}
					if ( direction.equals ( "right" ) )
					{
						moveRight ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   left" );
					}
					if ( direction.equals ( "up" ) )
					{
						moveUp ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   down" );
					}
					if ( direction.equals ( "down" ) )
					{
						moveDown ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   up" );
					}
					if ( direction.equals ( "rotate+" ) )
					{
						moveRotateClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   CounterClockwise" );
					}
					if ( direction.equals ( "rotate-" ) )
					{
						moveRotateCounterClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves    Clockwise" );
					}
				}
			}
			if ( strips ( ) == false )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}


	private boolean strips2 ( )
	{
		// ����� �� ������� ���� ������ �����
		for ( int i = 0 ; i < _wantedStateOfFarnitures.size ( ) ; i ++ )
		{
			DrawingPanel.GetInstance ( ).drawFurniture2 ( _wantedStateOfFarnitures.elementAt ( i ).get_rect ( ) , Color.RED );
		}
		/**
		 * ���� �����
		 */
		// �� �� ������� ������� �� ������ ������ �� ������� �������� �� ������ ������ �� ������� ����� �� ������
		Furniture currentFurniture = null;
		boolean finish = true;
		for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
		{
			for ( int j = 0 ; j < _wantedStateOfFarnitures.size ( ) ; j ++ )
			{
				if ( _currentStateOfFarnitures.get ( i ).get_uniquNumber ( ) == _wantedStateOfFarnitures.get ( j ).get_uniquNumber ( ) )
				{
					if ( ! ( _currentStateOfFarnitures.get ( i ).get_rect ( ).equals ( _wantedStateOfFarnitures.get ( j ).get_rect ( ) ) ) ) // �� ����� �� ������
					{
						currentFurniture = _currentStateOfFarnitures.get ( i ); // ����� ��� ���� ������
						finish = false;
						i = _currentStateOfFarnitures.size ( );
						j = _wantedStateOfFarnitures.size ( );
					}
					else
					// �� ����� ������ ���� �����
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( _currentStateOfFarnitures.get ( i ).get_rect ( ) , Color.blue );
					}
				}
			}
		}
		if ( finish )
		{
			textArea.append ( "finish successful" );

			System.out.println ( "finish successful" );
			return true;
		}
		// �� ������� ���� ����� ����� �� ���� ����� ������ ������ ��� ���� ������
		if ( StripsStack.getInstance ( ).getStack ( ).size ( ) == 0 )
		{
			StripsStack.getInstance ( ).getStack ( ).add ( "A " + currentFurniture.get_uniquNumber ( ) );
		}
		// ��� ���� �� ���� ���� �������
		_top = StripsStack.getInstance ( ).pop ( ); // elementAt ( StripsStack.getInstance ( ).getStack ( ).size ( )-1 ); // ��� �������
		if ( _top.charAt ( 0 ) == 'A' ) // �� ���� ������� ���� ���� ����
		{
			nextStep2 ( );
		}
		else if ( _top.charAt ( 0 ) == 'B' ) // �� ���� ������� ���� ���� ���� ��� ���� ����
		{
			// ����� �� ������ ������ ������ �� ����� �����
			// ����� �� ���� �� ������ ������
			// ������ ����� ����� ������� ������� ��� ����� ����� ���� �� ������ �� ������
			String [ ] dividedString = _top.split ( " " );
			int furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
			String direction = dividedString [ 2 ];
			Furniture curFurniture = null;
			for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
			{
				if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber ) // ���� �� ����� ����� ����� ����
				{
					curFurniture = _currentStateOfFarnitures.elementAt ( i );
					if ( direction.equals ( "left" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveLeft ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   right" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    right" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "right" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveRight ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   left" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    left" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "up" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveUp ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   down" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    down" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "down" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveDown ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   up" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    up" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "rotate+" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveRotateClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   CounterClockwise" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    CounterClockwise" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "rotate-" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveRotateCounterClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves    Clockwise" );
						textArea.append ( "Furniture number " + furnitureNumber + " moves    Clockwise" + '\n' );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
				}
			}
			for ( int j = 0 ; j < _wantedStateOfFarnitures.size ( ) ; j ++ )
			{
				if ( _wantedStateOfFarnitures.get ( j ).get_uniquNumber ( ) == furnitureNumber )
				{
					if ( ( curFurniture.get_rect ( ).equals ( _wantedStateOfFarnitures.get ( j ).get_rect ( ) ) ) ) // �� ����� ������ ���� strips()
					{
					}
					else
					{
						nextStep2 ( );
					}
				}
			}
		}
		else if ( _top.charAt ( 0 ) == 'C' ) // �� ���� ������� ���� ���� ���� ��� ���� ����
		{
			// ����� �� ������ ������ ������ �� ����� �����
			// ����� �� ���� �� ������ ������
			// ������ ����� ����� ������� ������� ��� ����� ����� ���� �� ������ �� ������
			String [ ] dividedString = _top.split ( " " );
			int furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
			String direction = dividedString [ 2 ];
			Furniture curFurniture = null;
			for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
			{
				if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber ) // ���� �� ����� ����� ����� ����
				{
					curFurniture = _currentStateOfFarnitures.elementAt ( i );
					if ( direction.equals ( "left" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // ����� �����
						moveLeft ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   right" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "right" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // 
						moveRight ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   left" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "up" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // 
						moveUp ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   down" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "down" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // 
						moveDown ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   up" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "rotate+" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // 
						moveRotateClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves   CounterClockwise" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
					if ( direction.equals ( "rotate-" ) )
					{
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.white ); // 
						moveRotateCounterClockwise ( curFurniture , _currentStateOfFarnitures );
						Stack.getInstance ( ).addToStack ( "Furniture number " + furnitureNumber + " moves    Clockwise" );
						DrawingPanel.GetInstance ( ).drawFurniture2 ( curFurniture.get_rect ( ) , Color.GREEN ); // ���� ������ ����
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * actually move the furniture acoording to the method name 
	 * @param currentFurniture
	 * @param stateOfFarnitures
	 */
	private void moveRotateCounterClockwise ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Point leftCorner = new Point ( currentFurniture.get_rect ( ).x - currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height - currentFurniture.get_rect ( ).width );
		currentFurniture.get_rect ( ).setLocation ( leftCorner );
		currentFurniture.get_rect ( ).setSize ( new Dimension ( currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).width ) );
	}


	private void moveRotateClockwise ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Point leftCorner = new Point ( currentFurniture.get_rect ( ).x + currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height - currentFurniture.get_rect ( ).width );
		currentFurniture.get_rect ( ).setLocation ( leftCorner );
		currentFurniture.get_rect ( ).setSize ( new Dimension ( currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).width ) );
	}


	private void moveDown ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 40;
	}


	private void moveUp ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 40;
	}


	private void moveRight ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x + 40;
	}


	private void moveLeft ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x - 40;
	}


	/**
	 * finds out what is the next step 
	 * @return
	 */
	private boolean nextStep ( )
	{
		boolean wemoved = false;
		// currentFurniture ����� �� ����� ����
		Furniture currentFurniture = null;
		int furnitureNumber;
		String [ ] dividedString = _top.split ( " " );
		furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
		for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
		{
			if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber )
			{
				currentFurniture = _currentStateOfFarnitures.elementAt ( i ); // ����� ����� �������
			}
		}
		String [ ] moves = bestMove ( currentFurniture , _currentStateOfFarnitures ); // ����� �� ������ �������
		Furniture badFurniture1 = null;
		Furniture badFurniture2 = null;
		Furniture badFurniture3 = null;
		// ���� ������� �� �� ��� ����� �����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 2 ] != null )
		{
			String currentMove = moves [ 2 ];
			if ( currentMove.equals ( "rotate" ) )
			{
				if ( checkRotateCounterClockWize ( currentFurniture , _currentStateOfFarnitures ) == 2 )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " rotate-" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else if ( checkRotateClockWize ( currentFurniture , _currentStateOfFarnitures ) == 1 )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " rotate+" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{ // �� ���� ������� �� �� ��� ��� ����� �����
					badFurniture1 = findFurnitureInWayOfRotating ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// ���� ���� ����� �� �����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 0 ] != null )
		{
			String cuurentMove = moves [ 0 ];
			if ( cuurentMove.equals ( "left" ) )
			{
				if ( checkLeft ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " left" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					badFurniture2 = findFurnitureInWayOfLeft ( currentFurniture , _currentStateOfFarnitures );
				}
			}
			else if ( cuurentMove.equals ( "right" ) )
			{
				if ( checkRight ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " right" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					badFurniture2 = findFurnitureInWayOfRight ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// ���� ���� ����� �� ����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 1 ] != null )
		{
			String cuurentMove = moves [ 1 ];
			if ( cuurentMove.equals ( "up" ) )
			{
				if ( checkUp ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " up" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					badFurniture3 = findFurnitureInWayOfUp ( currentFurniture , _currentStateOfFarnitures );
				}
			}
			else if ( cuurentMove.equals ( "down" ) )
			{
				if ( checkDown ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " down" );
					wemoved = true;
					if ( strips ( ) == false )
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					badFurniture3 = findFurnitureInWayOfDown ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// �� ������ ���� ��� ����� ����
		if ( ! wemoved )
		{
			int max1 = 0;
			int max2 = 0;
			int max3 = 0;
			int movesLeft1 = 0;
			int movesRight1 = 0;
			int movesUp1 = 0;
			int movesDown1 = 0;
			int movesLeft2 = 0;
			int movesRight2 = 0;
			int movesUp2 = 0;
			int movesDown2 = 0;
			int movesLeft3 = 0;
			int movesRight3 = 0;
			int movesUp3 = 0;
			int movesDown3 = 0;
			String move1 = new String ( );
			String move2 = new String ( );
			String move3 = new String ( );
			if ( badFurniture1 != null )
			{
				movesLeft1 = numOfMovesLeft ( badFurniture1 , _currentStateOfFarnitures );
				movesRight1 = numOfMovesRight ( badFurniture1 , _currentStateOfFarnitures );
				movesUp1 = numOfMovesUp ( badFurniture1 , _currentStateOfFarnitures );
				movesDown1 = numOfMovesDown ( badFurniture1 , _currentStateOfFarnitures );
				max1 = Math.max ( movesLeft1 , movesRight1 );
				max1 = Math.max ( max1 , movesUp1 );
				max1 = Math.max ( max1 , movesDown1 );
				if ( movesLeft1 == max1 )
				{
					move1 = "left";
				}
				else if ( movesRight1 == max1 )
				{
					move1 = "right";
				}
				else if ( movesUp1 == max1 )
				{
					move1 = "up";
				}
				else if ( movesDown1 == max1 )
				{
					move1 = "down";
				}
			}
			if ( badFurniture2 != null )
			{
				movesLeft2 = numOfMovesLeft ( badFurniture2 , _currentStateOfFarnitures );
				movesRight2 = numOfMovesRight ( badFurniture2 , _currentStateOfFarnitures );
				movesUp2 = numOfMovesUp ( badFurniture2 , _currentStateOfFarnitures );
				movesDown2 = numOfMovesDown ( badFurniture2 , _currentStateOfFarnitures );
				max2 = Math.max ( movesLeft2 , movesRight2 );
				max2 = Math.max ( max2 , movesUp2 );
				max2 = Math.max ( max2 , movesDown2 );
				if ( movesLeft2 == max2 )
				{
					move2 = "left";
				}
				else if ( movesRight2 == max2 )
				{
					move2 = "right";
				}
				else if ( movesUp2 == max2 )
				{
					move2 = "up";
				}
				else if ( movesDown2 == max2 )
				{
					move2 = "down";
				}
			}
			if ( badFurniture3 != null )
			{
				movesLeft3 = numOfMovesLeft ( badFurniture3 , _currentStateOfFarnitures );
				movesRight3 = numOfMovesRight ( badFurniture3 , _currentStateOfFarnitures );
				movesUp3 = numOfMovesUp ( badFurniture3 , _currentStateOfFarnitures );
				movesDown3 = numOfMovesDown ( badFurniture3 , _currentStateOfFarnitures );
				max3 = Math.max ( movesLeft3 , movesRight3 );
				max3 = Math.max ( max3 , movesUp3 );
				max3 = Math.max ( max3 , movesDown3 );
				if ( movesLeft3 == max3 )
				{
					move3 = "left";
				}
				else if ( movesRight3 == max3 )
				{
					move3 = "right";
				}
				else if ( movesUp3 == max3 )
				{
					move3 = "up";
				}
				else if ( movesDown3 == max3 )
				{
					move3 = "down";
				}
			}
			// ���� �� ������� 1,2,3 �� ����� ��������� �� ������ �� ��� �������� ������ ���
			Furniture badestFurniture = null;
			int max = 0;
			int index = 0;
			if ( max1 > max2 )
			{
				index = 1;
				max = max1;
				badestFurniture = badFurniture1;
			}
			else
			{
				index = 2;
				max = max2;
				badestFurniture = badFurniture2;
			}
			if ( max3 > Math.max ( max1 , max2 ) )
			{
				index = 3;
				max = max3;
				badestFurniture = badFurniture3;
			}
			// ����� ������� �� ���� ����� ��� ����� ���� �� ������ �� ��� ����� ������ ��� ���� ��� ���� �� �����
			// ����� max ��� ����� �������
			// "C 2 LEFT " ������
			String direction = new String ( );
			if ( index == 1 )
			{
				direction = move1;
			}
			else if ( index == 2 )
			{
				direction = move2;
			}
			else
			{
				direction = move3;
			}
			StripsStack.getInstance ( ).addToStack ( "A " + currentFurniture.get_uniquNumber ( ) ); // ����� �������� �� ����
			for ( int i = 1 ; i < max ; i ++ )
			{
				StripsStack.getInstance ( ).addToStack ( "C " + badestFurniture.get_uniquNumber ( ) + " " + direction );
			}
			// ����� �� ����� ��� ������ ���� ������ ��� ����� �� �����
			for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
			{
				if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == badestFurniture.get_uniquNumber ( ) )
					_currentStateOfFarnitures.remove ( i ); // ����� ������ ������
			}
			_currentStateOfFarnitures.add ( badestFurniture ); // ����� ���� ������
			if ( strips ( ) == false )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}


	private boolean nextStep2 ( )
	{
		boolean wemoved = false;
		// currentFurniture ����� �� ����� ����
		Furniture currentFurniture = null;
		int furnitureNumber;
		String [ ] dividedString = _top.split ( " " );
		furnitureNumber = Integer.valueOf ( dividedString [ 1 ] );
		for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
		{
			if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == furnitureNumber )
			{
				currentFurniture = _currentStateOfFarnitures.elementAt ( i ); // ����� ����� �������
			}
		}
		String [ ] moves = bestMove ( currentFurniture , _currentStateOfFarnitures ); // ����� �� ������ �������
		Furniture badFurniture1 = null;
		Furniture badFurniture2 = null;
		Furniture badFurniture3 = null;
		// ���� ������� �� �� ��� ����� �����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 2 ] != null )
		{
			String currentMove = moves [ 2 ];
			if ( currentMove.equals ( "rotate" ) )
			{
				if ( checkRotateCounterClockWize ( currentFurniture , _currentStateOfFarnitures ) == 2 )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " rotate-" );
					wemoved = true;
				}
				else if ( checkRotateClockWize ( currentFurniture , _currentStateOfFarnitures ) == 1 && wemoved == false )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " rotate+" );
					wemoved = true;
				}
				else
				{ // �� ���� ������� �� �� ��� ��� ����� �����
					badFurniture1 = findFurnitureInWayOfRotating ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// ���� ���� ����� �� �����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 0 ] != null && ! wemoved )
		{
			String cuurentMove = moves [ 0 ];
			if ( cuurentMove.equals ( "left" ) )
			{
				if ( checkLeft ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " left" );
					wemoved = true;
				}
				else
				{
					badFurniture2 = findFurnitureInWayOfLeft ( currentFurniture , _currentStateOfFarnitures );
				}
			}
			else if ( cuurentMove.equals ( "right" ) )
			{
				if ( checkRight ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " right" );
					wemoved = true;
				}
				else
				{
					badFurniture2 = findFurnitureInWayOfRight ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// ���� ���� ����� �� ����
		// strips() �� ���� ����� ������� �����
		if ( moves [ 1 ] != null && ! wemoved )
		{
			String cuurentMove = moves [ 1 ];
			if ( cuurentMove.equals ( "up" ) )
			{
				if ( checkUp ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " up" );
					wemoved = true;
				}
				else
				{
					badFurniture3 = findFurnitureInWayOfUp ( currentFurniture , _currentStateOfFarnitures );
				}
			}
			else if ( cuurentMove.equals ( "down" ) )
			{
				if ( checkDown ( currentFurniture , _currentStateOfFarnitures ) == true )
				{
					StripsStack.getInstance ( ).addToStack ( "B " + currentFurniture.get_uniquNumber ( ) + " down" );
					wemoved = true;
				}
				else
				{
					badFurniture3 = findFurnitureInWayOfDown ( currentFurniture , _currentStateOfFarnitures );
				}
			}
		}
		// �� ������ ���� ��� ����� ����
		if ( ! wemoved )
		{
			int max1 = 0;
			int max2 = 0;
			int max3 = 0;
			int movesLeft1 = 0;
			;
			int movesRight1 = 0;
			;
			int movesUp1 = 0;
			;
			int movesDown1 = 0;
			;
			int movesLeft2 = 0;
			;
			int movesRight2 = 0;
			;
			int movesUp2 = 0;
			;
			int movesDown2 = 0;
			;
			int movesLeft3 = 0;
			;
			int movesRight3 = 0;
			;
			int movesUp3 = 0;
			;
			int movesDown3 = 0;
			String move1 = new String ( );
			String move2 = new String ( );
			String move3 = new String ( );
			if ( badFurniture1 != null )
			{
				movesLeft1 = numOfMovesLeft ( badFurniture1 , _currentStateOfFarnitures );
				movesRight1 = numOfMovesRight ( badFurniture1 , _currentStateOfFarnitures );
				movesUp1 = numOfMovesUp ( badFurniture1 , _currentStateOfFarnitures );
				movesDown1 = numOfMovesDown ( badFurniture1 , _currentStateOfFarnitures );
				max1 = Math.max ( movesLeft1 , movesRight1 );
				max1 = Math.max ( max1 , movesUp1 );
				max1 = Math.max ( max1 , movesDown1 );
				if ( movesLeft1 == max1 )
				{
					move1 = "left";
				}
				else if ( movesRight1 == max1 )
				{
					move1 = "right";
				}
				else if ( movesUp1 == max1 )
				{
					move1 = "up";
				}
				else if ( movesDown1 == max1 )
				{
					move1 = "down";
				}
			}
			if ( badFurniture2 != null )
			{
				movesLeft2 = numOfMovesLeft ( badFurniture2 , _currentStateOfFarnitures );
				movesRight2 = numOfMovesRight ( badFurniture2 , _currentStateOfFarnitures );
				movesUp2 = numOfMovesUp ( badFurniture2 , _currentStateOfFarnitures );
				movesDown2 = numOfMovesDown ( badFurniture2 , _currentStateOfFarnitures );
				max2 = Math.max ( movesLeft2 , movesRight2 );
				max2 = Math.max ( max2 , movesUp2 );
				max2 = Math.max ( max2 , movesDown2 );
				if ( movesLeft2 == max2 )
				{
					move2 = "left";
				}
				else if ( movesRight2 == max2 )
				{
					move2 = "right";
				}
				else if ( movesUp2 == max2 )
				{
					move2 = "up";
				}
				else if ( movesDown2 == max2 )
				{
					move2 = "down";
				}
			}
			if ( badFurniture3 != null )
			{
				movesLeft3 = numOfMovesLeft ( badFurniture3 , _currentStateOfFarnitures );
				movesRight3 = numOfMovesRight ( badFurniture3 , _currentStateOfFarnitures );
				movesUp3 = numOfMovesUp ( badFurniture3 , _currentStateOfFarnitures );
				movesDown3 = numOfMovesDown ( badFurniture3 , _currentStateOfFarnitures );
				max3 = Math.max ( movesLeft3 , movesRight3 );
				max3 = Math.max ( max3 , movesUp3 );
				max3 = Math.max ( max3 , movesDown3 );
				if ( movesLeft3 == max3 )
				{
					move3 = "left";
				}
				else if ( movesRight3 == max3 )
				{
					move3 = "right";
				}
				else if ( movesUp3 == max3 )
				{
					move3 = "up";
				}
				else if ( movesDown3 == max3 )
				{
					move3 = "down";
				}
			}
			// ���� �� ������� 1,2,3 �� ����� ��������� �� ������ �� ��� �������� ������ ���
			Furniture badestFurniture = null;
			int max = 0;
			int index = 0;
			if ( max1 > max2 )
			{
				index = 1;
				max = max1;
				badestFurniture = badFurniture1;
			}
			else
			{
				index = 2;
				max = max2;
				badestFurniture = badFurniture2;
			}
			if ( max3 > Math.max ( max1 , max2 ) )
			{
				index = 3;
				max = max3;
				badestFurniture = badFurniture3;
			}
			// ����� ������� �� ���� ����� ��� ����� ���� �� ������ �� ��� ����� ������ ��� ���� ��� ���� �� �����
			// ����� max ��� ����� �������
			// "C 2 LEFT " ������
			String direction = new String ( );
			if ( index == 1 )
			{
				direction = move1;
			}
			else if ( index == 2 )
			{
				direction = move2;
			}
			else
			{
				direction = move3;
			}
			StripsStack.getInstance ( ).addToStack ( "A " + currentFurniture.get_uniquNumber ( ) ); // ����� �������� �� ����
			for ( int i = 1 ; i < max ; i ++ )
			{
				StripsStack.getInstance ( ).addToStack ( "C " + badestFurniture.get_uniquNumber ( ) + " " + direction );
			}
			// ����� �� ����� ��� ������ ���� ������ ��� ����� �� �����
			if ( badestFurniture != null )
			{
				for ( int i = 0 ; i < _currentStateOfFarnitures.size ( ) ; i ++ )
				{
					if ( _currentStateOfFarnitures.elementAt ( i ).get_uniquNumber ( ) == badestFurniture.get_uniquNumber ( ) )
						_currentStateOfFarnitures.remove ( i ); // ����� ������ ������
				}
				_currentStateOfFarnitures.add ( badestFurniture ); // ����� ���� ������
			}
		}
		return false;
	}


	/**
	 * how many continuous times could we moves up until we hit a furniture or a wall
	 * @param badFurniture1
	 * @param stateOfFarnitures
	 * @return
	 */
	private int numOfMovesUp ( Furniture badFurniture1 , Vector < Furniture > stateOfFarnitures )
	{
		int counter = - 1;
		boolean intersect = false;
		int y = badFurniture1.get_rect ( ).y;
		while ( ! intersect )
		{
			counter ++ ;
			badFurniture1.get_rect ( ).y = badFurniture1.get_rect ( ).y - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
			{
				if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != badFurniture1.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( badFurniture1.get_rect ( ) ) )
				{
					intersect = true;
					break;
				}
			}
			// ����� ��� ������ �������� �� ���� ��� �� ��� ����
			if ( ! intersect )
			{
				if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
				if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
			}
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		badFurniture1.get_rect ( ).y = y; // ����� �����
		return counter;
	}


	private int numOfMovesDown ( Furniture badFurniture1 , Vector < Furniture > stateOfFarnitures )
	{
		int counter = - 1;
		boolean intersect = false;
		int y = badFurniture1.get_rect ( ).y;
		while ( ! intersect )
		{
			counter ++ ;
			badFurniture1.get_rect ( ).y = badFurniture1.get_rect ( ).y + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
			{
				if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != badFurniture1.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( badFurniture1.get_rect ( ) ) )
				{
					intersect = true;
					break;
				}
			}
			// ����� ��� ������ �������� �� ���� ��� �� ��� ����
			if ( ! intersect )
			{
				if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
				if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
			}
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		badFurniture1.get_rect ( ).y = y; // ����� �����
		return counter;
	}


	private int numOfMovesRight ( Furniture badFurniture1 , Vector < Furniture > stateOfFarnitures )
	{
		int counter = - 1;
		boolean intersect = false;
		int x = badFurniture1.get_rect ( ).x;
		while ( ! intersect )
		{
			counter ++ ;
			badFurniture1.get_rect ( ).x = badFurniture1.get_rect ( ).x + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
			{
				if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != badFurniture1.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( badFurniture1.get_rect ( ) ) )
				{
					intersect = true;
					break;
				}
			}
			// ����� ��� ������ �������� �� ���� ��� �� ��� ����
			if ( ! intersect )
			{
				if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).x ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).x ) && ( badFurniture1.get_rect ( ).x + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).x ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
				if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).x ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).x ) && ( badFurniture1.get_rect ( ).x + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).x ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
			}
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		badFurniture1.get_rect ( ).x = x; // ����� �����
		return counter;
	}


	private int numOfMovesLeft ( Furniture badFurniture1 , Vector < Furniture > stateOfFarnitures )
	{
		int counter = - 1;
		boolean intersect = false;
		int x = badFurniture1.get_rect ( ).x;
		while ( ! intersect )
		{
			counter ++ ;
			badFurniture1.get_rect ( ).x = badFurniture1.get_rect ( ).x - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
			for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
			{
				if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != badFurniture1.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( badFurniture1.get_rect ( ) ) )
				{
					intersect = true;
					break;
				}
			}
			// ����� ��� ������ �������� �� ���� ��� �� ��� ����
			if ( ! intersect )
			{
				if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
				if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
				{
					if ( ( ( badFurniture1.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( badFurniture1.get_rect ( ).y + badFurniture1.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
					{
						intersect = false;
					}
					else
					{
						intersect = true;
					}
				}
			}
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( badFurniture1.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		badFurniture1.get_rect ( ).x = x; // ����� �����
		return counter;
	}


	/**
	 * finds the furniture that interupt the current furniture moving down
	 * @param currentFurniture
	 * @param stateOfFarnitures
	 * @return
	 */
	private Furniture findFurnitureInWayOfDown ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture badFurniture = null;
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				badFurniture = _currentStateOfFarnitures.get ( j );
				break;
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 40;
		return badFurniture;
	}


	private Furniture findFurnitureInWayOfUp ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture badFurniture = null;
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				badFurniture = _currentStateOfFarnitures.get ( j );
				break;
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 40;
		return badFurniture;
	}


	private Furniture findFurnitureInWayOfRight ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture badFurniture = null;
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				badFurniture = _currentStateOfFarnitures.get ( j );
				break;
			}
		}
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x - 40;
		return badFurniture;
	}


	private Furniture findFurnitureInWayOfLeft ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture badFurniture = null;
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				badFurniture = _currentStateOfFarnitures.get ( j );
				break;
			}
		}
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x + 40;
		return badFurniture;
	}


	private Furniture findFurnitureInWayOfRotating ( Furniture tempFurniture , Vector < Furniture > stateOfFarnitures )
	{
		/**
		 * ���� �� �� ���� ������ �� ����� �����
		 */
		Furniture gibui = new Furniture ( );
		gibui.set_uniquNumber ( tempFurniture.get_uniquNumber ( ) );
		gibui.set_rect ( new Rectangle ( new Point ( tempFurniture.get_rect ( ).getLocation ( ) ) , new Dimension ( tempFurniture.get_rect ( ).getSize ( ) ) ) );
		/**
		 * ����� �� ����� �� ����� ����� ������ ����� ������ ��� ����� ��������� �� ������ �� ����� ������� ��� ����� �� ���� �� ��� ������� �� �� ������
		 */
		int max = Math.max ( tempFurniture.get_rect ( ).width , tempFurniture.get_rect ( ).height );
		int min = Math.min ( tempFurniture.get_rect ( ).width , tempFurniture.get_rect ( ).height );
		Point leftCorner = new Point ( tempFurniture.get_rect ( ).x + tempFurniture.get_rect ( ).width , tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height - tempFurniture.get_rect ( ).width );
		Rectangle rectToBeSquare = new Rectangle ( leftCorner , new Dimension ( max , max ) );
		Furniture rotatedFurniture = new Furniture ( );
		rotatedFurniture.set_uniquNumber ( tempFurniture.get_uniquNumber ( ) );
		rotatedFurniture.set_rect ( rectToBeSquare );
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != rotatedFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( rotatedFurniture.get_rect ( ) ) )
			{
				return _currentStateOfFarnitures.get ( j );
			}
		}
		rotatedFurniture.get_rect ( ).setSize ( tempFurniture.get_rect ( ).height , tempFurniture.get_rect ( ).width );
		// ����� ��� ���� ������ ������ �������� �� ����
		/**
		 * ���� �� �� ���� ������ ��� ����� �����
		 */
		gibui = new Furniture ( );
		gibui.set_uniquNumber ( tempFurniture.get_uniquNumber ( ) );
		gibui.set_rect ( new Rectangle ( new Point ( tempFurniture.get_rect ( ).getLocation ( ) ) , new Dimension ( tempFurniture.get_rect ( ).getSize ( ) ) ) );
		/**
		 * ����� �� ����� ��� ����� ����� ������ ����� ������ ��� ����� ��������� �� ������ �� ����� ������� ��� ����� �� ���� �� ��� ������� �� �� ������
		 */
		max = Math.max ( tempFurniture.get_rect ( ).width , tempFurniture.get_rect ( ).height );
		min = Math.min ( tempFurniture.get_rect ( ).width , tempFurniture.get_rect ( ).height );
		leftCorner = new Point ( tempFurniture.get_rect ( ).x - tempFurniture.get_rect ( ).height , tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height - tempFurniture.get_rect ( ).width );
		rectToBeSquare = new Rectangle ( leftCorner , new Dimension ( max , max ) );
		rotatedFurniture = new Furniture ( );
		rotatedFurniture.set_uniquNumber ( tempFurniture.get_uniquNumber ( ) );
		rotatedFurniture.set_rect ( rectToBeSquare );
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != rotatedFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( rotatedFurniture.get_rect ( ) ) )
			{
				return _currentStateOfFarnitures.get ( j );
			}
		}
		rotatedFurniture.get_rect ( ).setSize ( tempFurniture.get_rect ( ).height , tempFurniture.get_rect ( ).width );
		return null;
	}



	 /**
	  * check if the currentFurniture could move down
	  * @param currentFurniture
	  * @param stateOfFarnitures
	  * @return
	  */
	private boolean checkDown ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 39;
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 1;
		if ( ! intersect )
		{
			return true;
		}
		return false;
	}


	private boolean checkUp ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 39;
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).y = currentFurniture.get_rect ( ).y + 1;
		if ( ! intersect )
		{
			return true;
		}
		return false;
	}


	private boolean checkRight ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x + 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != currentFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( currentFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x - 39;
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( currentFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( currentFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		currentFurniture.get_rect ( ).x = currentFurniture.get_rect ( ).x - 1;
		if ( ! intersect )
		{
			return true;
		}
		return false;
	}


	private boolean checkLeft ( Furniture tempFurniture , Vector < Furniture > stateOfFarnitures )
	{
		tempFurniture.get_rect ( ).x = tempFurniture.get_rect ( ).x - 40;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != tempFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( tempFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( tempFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( tempFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( tempFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( tempFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( tempFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( tempFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		// ���� ����� ���� ������� �� 40 ��� ������ ���� ����� �� ���� ���� ������ �� 39 ��� ����� ��� ����� �� ���� ����� ����� �� �1 �����
		tempFurniture.get_rect ( ).x = tempFurniture.get_rect ( ).x + 39;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( tempFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( tempFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( tempFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( tempFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( tempFurniture.get_rect ( ) ) ) )
			{
				if ( ( ( tempFurniture.get_rect ( ).y ) >= RoomII._getInstance ( ).get_doorCoordibate1 ( ).y ) && ( tempFurniture.get_rect ( ).y + tempFurniture.get_rect ( ).height ) <= ( RoomII._getInstance ( ).get_doorCoordibate2 ( ).y ) )
				{
					intersect = false;
				}
				else
				{
					intersect = true;
				}
			}
		}
		tempFurniture.get_rect ( ).x = tempFurniture.get_rect ( ).x + 1;// ���� �� ������� ��� ��� ����� ��� ���� ��� �� ������ ��� �� �� ���� ����
		if ( ! intersect )
		{
			return true;
		}
		return false;
	}


	/*
	 * �� ���� ������� �� ����� ����� ����� 1 �� �� ����� ��� ����� ����� ����� 2 �� �� ���� ������� ���� ����� 0
	 */
	private int checkRotateClockWize ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture gibui = new Furniture ( );
		gibui.set_uniquNumber ( currentFurniture.get_uniquNumber ( ) );
		gibui.set_rect ( new Rectangle ( new Point ( currentFurniture.get_rect ( ).getLocation ( ) ) , new Dimension ( currentFurniture.get_rect ( ).getSize ( ) ) ) );
		/**
		 * ����� �� ����� �� ����� ����� ������ ����� ������ ��� ����� ��������� �� ������ �� ����� ������� ��� ����� �� ���� �� ��� ������� �� �� ������
		 */
		int max = Math.max ( currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).height );
		int min = Math.min ( currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).height );
		Point leftCorner = new Point ( currentFurniture.get_rect ( ).x + currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height - currentFurniture.get_rect ( ).width );
		Rectangle rectToBeSquare = new Rectangle ( leftCorner , new Dimension ( max , max ) );
		Furniture rotatedFurniture = new Furniture ( );
		rotatedFurniture.set_uniquNumber ( currentFurniture.get_uniquNumber ( ) );
		rotatedFurniture.set_rect ( rectToBeSquare );
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != rotatedFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( rotatedFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		rotatedFurniture.get_rect ( ).setSize ( currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).width );
		// ����� ��� ���� ������ ������ �������� �� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) // �� ����� ������ �� ���� ������ ��� �� ������ ��
					&& ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
			/**
			 * ���� ����� ��� 2 ������ : 1)���� ������� ��� ���� �� �����
			 */
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		/**
		 * �� ���� ������ ���� �� ����� ������ ����� ���� �� ����� ����� ���� ����� ��� ������ �� ���� ����
		 */
		if ( RoomI._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) )
		{
			rotatedFurniture.get_rect ( ).x = rotatedFurniture.get_rect ( ).x - 1;
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		if ( ! intersect ) // ���� ���� �� ���� ����� �� ����� �� ����� �����
		{
			return 1;
		}
		return 0;
	}


	private int checkRotateCounterClockWize ( Furniture currentFurniture , Vector < Furniture > stateOfFarnitures )
	{
		Furniture gibui = new Furniture ( );
		gibui.set_uniquNumber ( currentFurniture.get_uniquNumber ( ) );
		gibui.set_rect ( new Rectangle ( new Point ( currentFurniture.get_rect ( ).getLocation ( ) ) , new Dimension ( currentFurniture.get_rect ( ).getSize ( ) ) ) );
		/**
		 * ����� �� ����� ��� ����� ����� ������ ����� ������ ��� ����� ��������� �� ������ �� ����� ������� ��� ����� �� ���� �� ��� ������� �� �� ������
		 */
		int max = Math.max ( currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).height );
		int min = Math.min ( currentFurniture.get_rect ( ).width , currentFurniture.get_rect ( ).height );
		Point leftCorner = new Point ( currentFurniture.get_rect ( ).x - currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).y + currentFurniture.get_rect ( ).height - currentFurniture.get_rect ( ).width );
		Rectangle rectToBeSquare = new Rectangle ( leftCorner , new Dimension ( max , max ) );
		Furniture rotatedFurniture = new Furniture ( );
		rotatedFurniture.set_uniquNumber ( currentFurniture.get_uniquNumber ( ) );
		rotatedFurniture.set_rect ( rectToBeSquare );
		boolean intersect = false;
		for ( int j = 0 ; j < _currentStateOfFarnitures.size ( ) ; j ++ )// ����� ��� ������� �� ������ �����
		{
			if ( _currentStateOfFarnitures.get ( j ).get_uniquNumber ( ) != rotatedFurniture.get_uniquNumber ( ) && _currentStateOfFarnitures.get ( j ).get_rect ( ).intersects ( rotatedFurniture.get_rect ( ) ) )
			{
				intersect = true;
				break;
			}
		}
		rotatedFurniture.get_rect ( ).setSize ( currentFurniture.get_rect ( ).height , currentFurniture.get_rect ( ).width );
		// ����� ��� ������ �������� �� ���� ��� �� ��� ����
		if ( ! intersect )
		{
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) && ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
			if ( ( RoomII._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
			/**
			 * ���� ����� ��� 2 ������ : 1)���� ������� ��� ���� �� �����
			 */
			if ( ( ! RoomI._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) && ( ! RoomII._getInstance ( ).get_roomSize ( ).contains ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		/**
		 * �� ���� ���� ���� �� ����� ������ ����� ���� ��� ����� ����� ���� ����� ��� ������ �� ���� ������
		 */
		if ( RoomII._getInstance ( ).get_roomSize ( ).contains ( currentFurniture.get_rect ( ) ) )
		{
			rotatedFurniture.get_rect ( ).x = rotatedFurniture.get_rect ( ).x + 1;
			if ( ( RoomI._getInstance ( ).get_roomSize ( ).intersects ( rotatedFurniture.get_rect ( ) ) ) )
			{
				intersect = true;
			}
		}
		if ( ! intersect )
		{
			return 2;
		}
		return 0;
	}


	/**
	 * huristic method - returns the best moves that the current furniture may do
	 * @param furnitureToBeMoved
	 * @param currentstate
	 * @return
	 */
	private String [ ] bestMove ( Furniture furnitureToBeMoved , Vector < Furniture > currentstate )
	{
		// ����� �� ������ ����� ����� ��� ���� ��� ���� �����
		// �� ���� 2 �� ���� ������ ���� ��� ���� 1 �� ������ ���� ����� ���
		// ������ �������� ����� ����� ����� �� ������
		// ����� �� ������ �� ����� ������ �� ��� ���� ������ �� ����
		String results[] = new String [ 3 ];
		results [ 0 ] = null;
		results [ 1 ] = null;
		results [ 2 ] = null;
		Rectangle position = furnitureToBeMoved.get_rect ( );
		if ( RoomI._getInstance ( ).get_roomSize ( ).contains ( position ) )
		{
			// ����� ���� ������
			for ( int i = 0 ; i < _wantedStateOfFarnitures.size ( ) ; i ++ )
			{ // ����� ����� ��������
				if ( _wantedStateOfFarnitures.get ( i ).get_uniquNumber ( ) == furnitureToBeMoved.get_uniquNumber ( ) ) // ����� ����� ���� �� ����� ������ �� ���� ������ �� ������� - �����
				{
					int xtarget = _wantedStateOfFarnitures.get ( i ).get_rect ( ).x;
					int ytarget = _wantedStateOfFarnitures.get ( i ).get_rect ( ).y;
					int xcur = furnitureToBeMoved.get_rect ( ).x;
					int ycur = furnitureToBeMoved.get_rect ( ).y;
					// ����� �� ����� ����� �� �����
					if ( xtarget > xcur )
					{
						results [ 0 ] = new String ( "right" );
					}
					else if ( xtarget < xcur )
					{
						results [ 0 ] = new String ( "left" );
					}
					if ( ytarget > ycur )
					{
						results [ 1 ] = new String ( "down" );
					}
					else if ( ytarget < ycur )
					{
						results [ 1 ] = new String ( "up" );
					}
					if ( _wantedStateOfFarnitures.get ( i ).get_rect ( ).height != furnitureToBeMoved.get_rect ( ).height || _wantedStateOfFarnitures.get ( i ).get_rect ( ).width != furnitureToBeMoved.get_rect ( ).width )
					{
						results [ 2 ] = new String ( "rotate" );
					}
				}
			}
		}
		else if ( RoomII._getInstance ( ).get_roomSize ( ).contains ( position ) )
		{
			// ����� ���� ���� ����� ���� ���� ���� �� ����
			Point door1 = RoomII._getInstance ( ).get_doorCoordibate1 ( );
			Point door2 = RoomII._getInstance ( ).get_doorCoordibate2 ( );
			int xcur = furnitureToBeMoved.get_rect ( ).x;
			int ycur = furnitureToBeMoved.get_rect ( ).y;
			int xdoor1 = door1.x;
			int ydoor1 = door1.y;
			int xdoor2 = door2.x;
			int ydoor2 = door2.y;
			if ( xcur >= xdoor1 )
			{
				results [ 0 ] = new String ( "left" );
			}
			if ( ycur + furnitureToBeMoved.get_rect ( ).height > ydoor2 )
			{
				results [ 1 ] = new String ( "up" );
			}
			else if ( ycur < ydoor1 )
			{
				results [ 1 ] = new String ( "down" );
			}
			int yhight = door2.y - door1.y;
			int curhight = furnitureToBeMoved.get_rect ( ).getSize ( ).height;
			if ( curhight > yhight )
			{
				results [ 2 ] = new String ( "rotate" );
			}
		}
		else
		// ����� ��� ��� ����� ������ ����� ���� �� ����
		{
			int xcur = furnitureToBeMoved.get_rect ( ).x;
			int ycur = furnitureToBeMoved.get_rect ( ).y;
			Point door1 = RoomII._getInstance ( ).get_doorCoordibate1 ( );
			Point door2 = RoomII._getInstance ( ).get_doorCoordibate2 ( );
			int ydoor2 = door2.y;
			int ydoor1 = door1.y;
			results [ 0 ] = new String ( "left" );
			if ( ycur + furnitureToBeMoved.get_rect ( ).height > ydoor2 )
			{
				results [ 1 ] = new String ( "up" );
			}
			else if ( ycur < ydoor1 )
			{
				results [ 1 ] = new String ( "down" );
			}
		}
		return results;
	}


	@Override
	public void mouseClicked ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseExited ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseReleased ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseDragged ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseMoved ( MouseEvent arg0 )
	{
		// TODO Auto-generated method stub
	}
}
