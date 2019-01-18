package othello;

import java.util.Vector;


public class Game {
	private int _size=8;
	private int[][] _board=new int[_size][_size];
	
	private boolean _alphaBeta;
	private int _level;
	
	private final static int EMPTY=0;
	private final static int WHITE=1;
	private final static int BLACK=2;
	private final static int INF=Integer.MAX_VALUE;
	private final static int MININF=Integer.MIN_VALUE;
	private final static int MIN=2;
	private final static int MAX=1;
	private int cur_max;
	
	
	
	public Game()
	{
	}

	
	public void newGame()
	{
		initialiseBoard();	
	}
	
	
	public int[][] get_board() {
		return _board;
	}


	public void set_board(int[][] _board) {
		this._board = _board;
	}


	public int get_level() {
		return _level;
	}

	public void set_level(int _level) {
		this._level = _level;
	}

	public boolean is_alphaBeta() {
		return _alphaBeta;
	}

	public void set_alphaBeta(boolean beta) {
		_alphaBeta = beta;
	}
	
	private void initialiseBoard() {
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				_board[i][j]=EMPTY;
			}
		}
		_board[3][3]=WHITE;
		_board[3][4]=BLACK;
		_board[4][3]=BLACK;
		_board[4][4]=WHITE;
	}
	
	
	public Vector<Move>  getLegalMoves(int color,int[][] board){
		Vector<Move> l=new Vector<Move>();
		Move m=new Move();
		int i,j,x,y,w,b;
		boolean in;
		if(color==BLACK){
			b=BLACK;
			w=WHITE;
		}
		else{
			w=BLACK;
			b=WHITE;
		}

		for(i=0;i<_size; i++)
		{
			for(j=0; j<_size; j++)
			{
				if(board[i][j]==EMPTY){
					x=i;
					y=j;
					in=false;
						while((x>0)&&(y>0)&&(board[x-1][y-1]==w)){
							in=true;
							x--;
							y--;
						}
						if((in==true)&&(x>0)&&(y>0)&&(board[x-1][y-1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((x>0)&&(board[x-1][y]==w)){
							in=true;
							x--;
						}
						if((in==true)&&(x>0)&&(board[x-1][y]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((x>0)&&(y<7)&&(board[x-1][y+1]==w)){
							in=true;
							x--;
							y++;
						}
						if((in==true)&&(x>0)&&(y<7)&&(board[x-1][y+1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((y>0)&&(board[x][y-1]==w)){
							in=true;
							y--;
						}
						if((in==true)&&(y>0)&&(board[x][y-1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((y<7)&&(board[x][y+1]==w)){
							in=true;
							y++;
						}
						if((in==true)&&(y<7)&&(board[x][y+1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((x<7)&&(y>0)&&(board[x+1][y-1]==w)){
							in=true;
							x++;
							y--;
						}
						if((in==true)&&(x<7)&&(y>0)&&(board[x+1][y-1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((x<7)&&(board[x+1][y]==w)){
							in=true;
							x++;
						}
						if((in==true)&&(x<7)&&(board[x+1][y]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
					in=false;
					x=i;
					y=j;
						while((x<7)&&(y<7)&&(board[x+1][y+1]==w)){
							in=true;
							x++;
							y++;
						}
						if((in==true)&&(x<7)&&(y<7)&&(board[x+1][y+1]==b)){
							m.i=i;
							m.j=j;
							l.add(new Move(i,j));
							continue;
						}
				}
			
			}
		}
		return l;
	}


	public int[][] updateBoard(int[][] board2 , Move m, int color) {  // אפקט דומינו
		
		int[][]  board3=new int[8][8];
		board3= board2;
		
		
		
		int opponentColor;
		int ourColor;
		if(color==BLACK)
		{
			ourColor=BLACK;
			opponentColor=WHITE;
		}
		else
		{
			ourColor=WHITE;
			opponentColor=BLACK;
		}
		
		board3[m.i][m.j]=ourColor;  // נצבע את המשבצת עצמה 
		
		boolean n,e,s,w,nw,ne,sw,se;
		n=false;
		e=false;
		s=false;
		w=false;
		nw=false;
		ne=false;
		sw=false;
		se=false;
		//  נצבע שחור את כל הלבנים שבין משבצת זו לשחור אחר
		int[][]  boardN=new int[8][8];
		int[][]  boardS=new int[8][8];
		int[][]  boardW=new int[8][8];
		int[][]  boardE=new int[8][8];
		int[][]  boardNE=new int[8][8];
		int[][]  boardNW=new int[8][8];
		int[][]  boardSE=new int[8][8];
		int[][]  boardSW=new int[8][8];
		//n
		int upInd=m.i;
		if(upInd>0)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[upInd-1][m.j]!=opponentColor)
			 {
				 n=false;
			 }
			 else
			 {
				 upInd=upInd-1;
				 while(upInd>0)
				 {
					if(board3[upInd-1][m.j]==opponentColor)
					{
						upInd=upInd-1;
					}
					else if(board3[upInd-1][m.j]==EMPTY)
					{
						n=false;
						break;
					}
					else if(board3[upInd-1][m.j]==ourColor)
					{
						
						n=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(n==true)
		{
			//int[][]  boardN=new int[8][8];
			
			for (int i =upInd ; i <= m.i; i++) {
				boardN[i][m.j]=ourColor;
			}
		}
		
		
		
		
		
		//s
		int downInd=m.i;
		if(downInd<8)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[downInd+1][m.j]!=opponentColor)
			 {
				 s=false;
			 }
			 else
			 {
				 downInd=downInd+1;
				 while(downInd<8)
				 {
					if(board3[downInd+1][m.j]==opponentColor)
					{
						downInd=downInd+1;
					}
					else if(board3[downInd+1][m.j]==EMPTY)
					{
						s=false;
						break;
					}
					else if(board3[downInd+1][m.j]==ourColor)
					{
						
						s=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(s==true)
		{
//			int[][]  boardS=new int[8][8];
			for (int i =downInd ; i >= m.i; i--) {
				boardS[i][m.j]=ourColor;
			}
		}
		
		
		
		//e
		int eInd=m.j;
		if(eInd<8)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[m.i][eInd+1]!=opponentColor)
			 {
				 e=false;
			 }
			 else
			 {
				 eInd=eInd+1;
				 while(eInd<8)
				 {
					if(board3[m.i][eInd+1]==opponentColor)
					{
						eInd=eInd+1;
					}
					else if(board3[m.j][eInd+1]==EMPTY)
					{
						e=false;
						break;
					}
					else if(board3[m.i][eInd+1]==ourColor)
					{
						
						e=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(e==true)
		{
	//		int[][]  boardE=new int[8][8];
			for (int j =eInd ; j >= m.j; j--) {
				boardE[m.i][j]=ourColor;
			}
		}
		
		
		
		
		//w
		int wInd=m.j;
		if(wInd>0)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[m.i][wInd-1]!=opponentColor)
			 {
				 w=false;
			 }
			 else
			 {
				 wInd=wInd-1;
				 while(wInd>0)
				 {
					if(board3[m.i][wInd-1]==opponentColor)
					{
						wInd=wInd-1;
					}
					else if(board3[m.i][wInd-1]==EMPTY)
					{
						w=false;
						break;
					}
					else if(board3[m.i][wInd-1]==ourColor)
					{
						
						w=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(w==true)
		{
		//	int[][]  boardW=new int[8][8];
			for (int j =wInd ; j <= m.j; j++) {
				boardW[m.i][j]=ourColor;
			}
		}
		
		
		
		//nw
		int nwiInd=m.i;
		int nwjInd=m.j;
		
		if(nwiInd>0 && nwjInd>0)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[nwiInd-1][nwjInd-1]!=opponentColor)   
			 {
				 nw=false;
			 }
			 else
			 {
				 nwiInd=nwiInd-1;
				 nwjInd=nwjInd-1;
				 while(nwiInd>0  &&  nwjInd>0 )
				 {
					if(board3[nwiInd-1][nwjInd-1]==opponentColor)
					{
						nwiInd=nwiInd-1;
						nwjInd=nwjInd-1;
					}
					else if(board3[nwiInd-1][nwjInd-1]==EMPTY)
					{
						
						nw=false;
						break;
					}
					else if(board3[nwiInd-1][nwjInd-1]==ourColor)
					{
						
						nw=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(nw==true)
		{
	//		int[][]  boardNW=new int[8][8];
			for (int i =nwiInd , j= nwjInd ; i<= m.i  ; i++ , j++) {
				boardNW[i][j]=ourColor;
			}
		}
		
		
		
		
		//se
		int seiInd=m.i;
		int sejInd=m.j;
		
		if(nwiInd<8 && nwjInd<8)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[nwiInd+1][nwjInd+1]!=opponentColor)   
			 {
				 se=false;
			 }
			 else
			 {
				 seiInd=seiInd+1;
				 sejInd=sejInd+1;
				 while(seiInd<8  &&  sejInd<8 )
				 {
					if(board3[seiInd+1][sejInd+1]==opponentColor)
					{
						seiInd=seiInd+1;
						sejInd=sejInd+1;
					}
					else if(board3[seiInd+1][sejInd+1]==EMPTY)
					{
						
						se=false;
						break;
					}
					else if(board3[seiInd+1][sejInd+1]==ourColor)
					{
						se=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(se==true)
		{
	//		int[][]  boardSE=new int[8][8];
			for (int i =seiInd , j= sejInd ; i>= m.i  ; i-- , j--) {
				boardSE[i][j]=ourColor;
			}
		}
		
		
		
		//ne
		int neiInd=m.i;
		int nejInd=m.j;
		
		if(neiInd>0 && nejInd<8)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[neiInd-1][nejInd+1]!=opponentColor)   
			 {
				 ne=false;
			 }
			 else
			 {
				 neiInd=neiInd-1;
				 nejInd=nejInd+1;
				 while(neiInd>0  &&  nejInd<8 )
				 {
					if(board3[neiInd-1][nejInd+1]==opponentColor)
					{
						neiInd=neiInd-1;
						nejInd=nejInd+1;
					}
					else if(board3[neiInd-1][nejInd+1]==EMPTY)
					{
						
						ne=false;
						break;
					}
					else if(board3[neiInd-1][nejInd+1]==ourColor)
					{
						
						ne=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(ne==true)
		{
	//		int[][]  boardNE=new int[8][8];
			for (int i =neiInd , j= nejInd ; i<= m.i  ; i++ , j--) {
				boardNE[i][j]=ourColor;
			}
		}
		
		
		
		
		//sw
		int swiInd=m.i;
		int swjInd=m.j;
		
		if(swiInd<8 && swjInd>0)//בדיקה שיש לאיפה לעלות
		{
			 if(board3[swiInd+1][swjInd-1]!=opponentColor)   
			 {
				 sw=false;
			 }
			 else
			 {
				 swiInd=swiInd+1;
				 swjInd=swjInd-1;
				 while(swiInd<8  &&  swjInd>0 )
				 {
					if(board3[swiInd+1][swjInd-1]==opponentColor)
					{
						swiInd=swiInd+1;
						swjInd=swjInd-1;
					}
					else if(board3[swiInd+1][swjInd-1]==EMPTY)
					{
						
						sw=false;
						break;
					}
					else if(board3[swiInd+1][swjInd-1]==ourColor)
					{
						
						sw=true;                        //   צריך לסמן בשחור את כל המשבצות הלבנות לכיוון למעלה עד שנגיע לשחור
						break;
					}
				 }
			 }
		}
		
		if(sw==true)
		{
	//		int[][]  boardSW=new int[8][8];
			for (int i =swiInd , j= swjInd ; i>= m.i  ; i-- , j++) {     //////////////////////
				boardSW[i][j]=ourColor;
			}
		}
		
		
		// עכשיו נשלב את כל הלוחות בלוח החדש
		
		for(int i=0; i<8 ; i ++)
			for(int j=0;j<8;j++){
				if(boardN[i][j]==color || boardS[i][j]==color || boardE[i][j]==color || boardW[i][j]==color || boardNW[i][j]==color || boardNE[i][j]==color || boardSE[i][j]==color || boardSW[i][j]==color)
					board3[i][j]=color;
			}
	
				
		
		
		
		
		return board3;
	}

	
	
	
	
	

	public int[][] calculateBestMove() {
		int maxBoardValue=MININF;
		int currBoardValue=0;
		int[][] maxBoard;
		maxBoard= new int[8][8];
		
		Vector<int[][]> boards=new Vector<int[][]>();
		Vector<Move> legalMoves= getLegalMoves(WHITE,_board);
		
		int[][] oldBoard=new int[8][8];
		
		
		for ( int i=0 ; i<legalMoves.size() ; i++)
		{
			oldBoard=_board;   																				//   _board  משתנה כאן ללא צורך 
			boards.add(  updateBoard(   oldBoard ,  legalMoves.elementAt(i)  ,  WHITE) ) ;
		}    //     ????????????????????????כרגע בורדס מכיל את כל הילדים שעליהם אנחנו רוצים לרוץ
		
		for(int i=0;i<boards.size();i++){  // מפעילים את מינימקס על כל לוח מהוקטור של הלוחות של הצעדים האפשריים ברמה אחת למטה
			currBoardValue=minimax( boards.elementAt(i) ,_level-1 ,/*MAX*/MIN );
			if ( currBoardValue>maxBoardValue ){
				maxBoard=boards.elementAt(i);       //  שומרים את הלוח המקסימלי
			}
		}
		
	// 	maxBoard=minimax( _board ,_level ,MAX );  // מקבל כפרמטר שני את הלוח אחרי שביצענו את המהך הרלוונטי
		
		// מחזירים את הלוח המקסימלי
		return maxBoard;
	}


	
	
	
	private int minimax(int[][]  board, int depth  , int type ) {
		
		if (depth==0){
			return(evaluate(board));
		}
		else
		if (type==MAX){  
		
			int maxBoardValue = MININF ; 
			
		
			Vector<Move> legalMoves= getLegalMoves(type,board);
			Vector<int[][]> boards=new Vector<int[][]>();
		
			for ( int i=0 ; i<legalMoves.size() ; i++)
			{
				boards.add(  updateBoard(   board ,  legalMoves.elementAt(i)  ,  type) ) ;
			}    	//  כרגע בורדס מכיל את כל הילדים שעליהם אנחנו רוצים לרוץ
		
		
			int currBoardValue;
			int[][] maxBoard;
			maxBoard= new int[8][8];
		
			for(int  i =0; i<boards.size(); i++ ) {              //   רצים על כל הילדים בבורדס          
				currBoardValue=minimax( boards.elementAt(i) ,_level-1 ,MIN );
				if ( currBoardValue>maxBoardValue ){
					maxBoard=boards.elementAt(i);       //  שומרים את הלוח המקסימלי
				}	 
			}
			
			return maxBoardValue;
		}	
		else {   //  type==min
			int minBoardValue = INF ; 
			
			Vector<Move> legalMoves= getLegalMoves(type,board);
			Vector<int[][]> boards=new Vector<int[][]>();
		
			for ( int i=0 ; i<legalMoves.size() ; i++)
			{
				boards.add(  updateBoard(   board ,  legalMoves.elementAt(i)  ,  type) ) ;
			}    	//  כרגע בורדס מכיל את כל הילדים שעליהם אנחנו רוצים לרוץ
		
		
			int currBoardValue;
			int[][] minBoard;
			minBoard= new int[8][8];
		
			for(int  i =0; i<boards.size(); i++ ) {              //   רצים על כל הילדים בבורדס          
				currBoardValue=minimax( boards.elementAt(i) ,_level-1 ,MAX );
				if ( currBoardValue<minBoardValue ){
					minBoard=boards.elementAt(i);       //  שומרים את הלוח המקסימלי
				}	 
			}	
			
			return minBoardValue;
		}	
	}	
	
		

	


	private int evaluate(int[][] board) {
		return 5 ;

	}
	
	
	
}
		
		
		
	

/*
	public int empty_cells() {
		
		int counter=0;
		for(int i=0;i<_size; i++)
		{
			for(int j=0; j<_size; j++)
			{
				if(_board[i][j]==EMPTY){
					counter++;
				}
			}
		}
		return counter;
	}
	
	*/
	

			
		
		
		
		
		
		

		















