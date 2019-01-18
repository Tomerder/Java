/**
 * Computer Graphics
 * Project number 2
 * Orel Shai - ID 052988912 shaiorel@gmail.com
 * Tomer Dery - ID 060628914 tomerder@gmail.com
 */


package othello;

/**
 * Project 2 in Computer Graphics
 * Orel shai-052988912
 * Tomer dery-060628914
 */
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

//import net.java.games.jogl.*;

/**
 *
 * @author  orel_shai
 */
public class a extends java.awt.Frame implements ActionListener{
	
	private JButton _startNewGame;
    private JButton _exit;
    private JCheckBox _alphaBeta;
    private JLabel _level;
    private java.awt.TextArea textArea1;
    private JApplet canvas1;//the current canvas
    private JComboBox _comboBox;
    private Game _game;
    private int[][] _currentBoard;
    private final static int EMPTY=0;
	private final static int WHITE=1;
	private final static int BLACK=2;
	private JButton[][] _boxes;
	private int _playerTurn=1;
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new a().setVisible(true);
            }
        });
    }
    
    
    public a()
    {
    	super ("Project 2 in Computer Graphics");	
        initComponents();
    }
    

    /**
     * Creates the GUI interface
     */
    private void initComponents() 
    {
    //	GLCapabilities capabilities = new GLCapabilities();
 		canvas1 = new JApplet();
 		_alphaBeta = new JCheckBox();
 		_startNewGame = new JButton();
        _exit = new JButton();
        _level =new  JLabel();
        textArea1 = new java.awt.TextArea();
        textArea1.setEditable(false);
        _comboBox = new JComboBox();
  
        _alphaBeta.setLabel("With alpha_beta");
        _alphaBeta.addActionListener(this);

        _startNewGame.setLabel("Start New Game");
        _startNewGame.addActionListener(this);

        _exit.setLabel("Exit");
        _exit .addActionListener(this);
        
         _level.setText("Choose Level 1-4 in the drop down list:");
         
         String[] levels=new String[4];
         levels[0]="easy=depth 3";
         levels[1]="medium=depth 4";
         levels[2]="hard=depth 5";
         levels[3]="expert=depth 6";
         
         _game=new Game();
         textArea1.setText("Choose the level of the game and with or without Alpha-beta algorithem and Click on the Start New Game button");
        
        _comboBox.setModel(new javax.swing.DefaultComboBoxModel(levels));
        _comboBox.addActionListener(this);

         
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);        
        
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1441, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(_startNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                
                .addComponent(_alphaBeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(636, 636, 636)
                
                )
            .addGroup(layout.createSequentialGroup()
                .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(_startNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)   
                        .addComponent(_alphaBeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE,/* 854*/ 500, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        
        //Add to window
        _boxes=new JButton[8][8];
        GridLayout LAYOUT_STYLE = new GridLayout(8,8);
        canvas1.getContentPane().setLayout(LAYOUT_STYLE);
        for (int  i= 0;  i<_boxes.length ; i++) {
        	for (int  j= 0;  j<_boxes.length ; j++) {
        		_boxes[i][j]=new JButton();
            	canvas1.getContentPane().add(_boxes[i][j]);
            	_boxes[i][j].addActionListener(this);
            	_boxes[i][j].setBackground(Color.lightGray);
    		
        	}
        }
            pack();
    }
    

                       
    
   
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object objPressed=e.getSource();
		if(objPressed.equals(_exit))
		{
			System.exit(0);
		}
		
		else if(objPressed.equals(_startNewGame))
		{			
			textArea1.setText("You are the Black , Its your turn");
			_game.newGame();
			_game.set_alphaBeta(_alphaBeta.isSelected());
			_game.set_level(_comboBox.getSelectedIndex()+3);//+3 = the depth
			_currentBoard=_game.get_board();
			
			updateGUI();
		}
		else{  // בודק עם לחצו על אחד הכפתורים בלוח
		
		//if(_playerTurn==1)
		
			for (int i = 0; i < _boxes.length; i++) {
				for (int j = 0; j < _boxes.length; j++) {
					if(_boxes[i][j].equals(objPressed))
					{
						_currentBoard=_game.get_board();
						
						if(_playerTurn==0)
						{
							textArea1.setText("Its not your turn"); 
							return;
						}
						
						
						if(_currentBoard[i][j]!=EMPTY)
						{
							textArea1.setText("This square is already filled"); 
							return;
						}
						else
						{
							boolean legalMove=false;
							Move m=new Move(i,j);
							Vector<Move> legalMoves=_game.getLegalMoves(BLACK,_game.get_board());
							for (int k = 0; k < legalMoves.size(); k++) {
								if(legalMoves.elementAt(k).equals(m))   //   עושה שינוי מתאים בלוח
								{
									_game.set_board(_game.updateBoard(_game.get_board(),m,BLACK));
									updateGUI();
									legalMove=true;
									
									Vector<Move> legalComputerMoves=_game.getLegalMoves(WHITE,_game.get_board());  // בודק אם יש מהלכי משחק למחשב
									if(legalComputerMoves.size()!=0)
										_playerTurn=0;
									else
										textArea1.setText("Computer does not have legal moves - play another turn");
								
									
									
								}
							}
							
							if(!legalMove)
							{
								textArea1.setText("this move is not legal move");
								return;
							}
							legalMove=false;
						}
							
					}
				}
			}
		
		}          //else
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
/*	while*/ if(_playerTurn==0){
			{
				//המחשב מבצע את הצעד שלו ובודק האם למתשמש יש צעד שהוא יכול לבצע
				//אם אין לו צעד המחשב ממשיך לשחק עוד תור
			
				System.out.println("computer turn");                         ///     עד לכאן הכל בסדר 
				//Move newBoard=new Move();
				
			//	int[][]  newBoard=new int[8][8];
			 
				_game.set_board(_game.calculateBestMove());   //  מעדכן את הלוח באפשרות הטובה ביותר
				//_game.set_board(_game.updateBoard(_game.get_board(),computerMove,WHITE));
				updateGUI();
				Vector<Move> legalMoves=_game.getLegalMoves(BLACK,_game.get_board());
				if(legalMoves.size()!=0)
					_playerTurn=1;
				else
					textArea1.setText("You have no legal move - computer has another turn");
			
			
			}
		}
	}	
	

		
	
	private void updateGUI()
	{
		_currentBoard=_game.get_board();
		
		for (int i = 0; i < _currentBoard.length; i++) {
			for (int j = 0; j < _currentBoard.length; j++) {
				if(_currentBoard[i][j]==EMPTY)
				{
					_boxes[i][j].setBackground(Color.LIGHT_GRAY);
				}
				else if(_currentBoard[i][j]==WHITE)
				{
					_boxes[i][j].setBackground(Color.WHITE);
				}
				else if(_currentBoard[i][j]==BLACK)
				{
					_boxes[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}
	
	
  
}




























