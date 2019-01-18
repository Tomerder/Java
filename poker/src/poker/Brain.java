package poker;

import java.util.Random;
import java.util.Vector;

//import poker.Player.Action;




public class Brain {
	
	
	enum Action { Fold , Call, Check , Raise , ReRaise , AllIn } ;
	enum State { PERFLOP , FLOP , TURN , RIVER } ;
	enum Position { DEALER , SMALL , BIG , BEGIN , MIDDLE , END };
	
	private  Action _toAct;
	
	
//	private State _state;
	
	private static Brain _instance;
	
	public static Brain getInstance()
	{
		if( _instance==null )
		{
			_instance=new Brain();
		}
		return _instance;
	}
	
	
	
	
	
	
	//  מטודה זאת תופעל ע"י החלק שקורא מהמסך ברגע שהשם שלנו יהפוך לאדום - כשתורנו לשחק 
	//  שתקבל כפרמטר את הפעולה ותוציאה לפועל ע"י הדמיית קליק עכבר במקום המתאים במסך Execution  מטודה זאת תחשב ותגריל מבין האופציות את הפעולה הרצויה ותקרא למטודה 
	private void execute( State state , int numOfhandsAginstUs , Hand ourHand , int ourChips , Vector<Player> playersInHand , int pot  , int chipsWePutInPot, Vector<Card> cardsOnTable  , int numOfRaisesBeforeUs , Position myPosition  ,int  bigBlind   , int  sumToCall      )                 
 	{
		// כאן נכניס כל מיני מקרים מיוחדים 

		if( chipsWePutInPot > ourChips)
		{
			
			execution( Action.AllIn  ,0);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/////////   State  תחילה נבדוק באיזה שלב אנחנו ביד
		
		
		if(   (state.equals("PERFLOP"))  && (ourChips > (bigBlind * 15))   )   // אולי לשנות ל 20 -  אם אנחנו לפני הפלופ ויש לנו יותר מ 15  פעמים ביגבלינד
		{
			
			
			// האם העלו לפנינו העלו עלינו או לא העלו בכלל

			
			
			
			if(numOfRaisesBeforeUs==0)    // אם לא העלו לפנינו
			{
			
			// AA  KK  
				
			if ( (ourHand.getCard1().getMispar()==15  && ourHand.getCard2().getMispar()==15) ||   ( ourHand.getCard1().getMispar()==13  && ourHand.getCard2().getMispar()==13  )  )    
		
			{
				Random seed = new Random();
				int cur=seed.nextInt (100 ) ;
				if (cur<50 )
				{
					execution( Action.Check   ,0);
				}
				else
				{
					execution( Action.Raise   , bigBlind*2   );
				}
				
				
			}
			
			
			
			
			
			// AK QQ JJ
			
			
			if  ( ( ourHand.getCard1().getMispar()==13  && ourHand.getCard2().getMispar()==15  )   ||  ( ourHand.getCard1().getMispar()==15  && ourHand.getCard2().getMispar()==13  )  
				|| ( ourHand.getCard1().getMispar()==12  && ourHand.getCard2().getMispar()==12  ) 
				|| ( ourHand.getCard1().getMispar()==11  && ourHand.getCard2().getMispar()==11  )   )
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<50 )
					{
						execution( Action.Raise   ,   bigBlind*3 );
					}
					else
					{
						execution( Action.Raise   , bigBlind*5   );
					}
				
						
				}
						
				
				
			
			// AQ,AJ,A10
			
			
			if  ( ( ourHand.getCard1().getMispar()==10  && ourHand.getCard2().getMispar()==15  )   ||  ( ourHand.getCard1().getMispar()==15  && ourHand.getCard2().getMispar()==10  )  
				|| ( ourHand.getCard1().getMispar()==11  && ourHand.getCard2().getMispar()==15  )   ||  ( ourHand.getCard1().getMispar()==15  && ourHand.getCard2().getMispar()==11  )
				|| ( ourHand.getCard1().getMispar()==12  && ourHand.getCard2().getMispar()==15  )   ||  ( ourHand.getCard1().getMispar()==15  && ourHand.getCard2().getMispar()==12  )    )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<5 )
					{
						execution( Action.Check   ,  0 );
					}
					else if (cur<65 )
					{
						execution( Action.Raise   , bigBlind*3   );
					}
					else 
					{
						execution( Action.Raise   , bigBlind*5   );
					}
						
				}
				
			
			// 10 10 , 9 9 
			
			
			if  ( ( ourHand.getCard1().getMispar()==9  && ourHand.getCard2().getMispar()==9  )   
				|| ( ourHand.getCard1().getMispar()==10  && ourHand.getCard2().getMispar()==10  )   )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<10 )
					{
						execution( Action.Raise   , bigBlind*3);
					}
					else if (cur<40 )
					{
						execution( Action.Raise   , bigBlind*4   );
					}
					else if (cur<70 )
					{
						execution( Action.Raise   , bigBlind*5   );
					}
					else 
					{
						execution( Action.Raise   , bigBlind*7   );
					}
				}
				
				
			
			
			// 88 77 
			
			
			if  ( ( ourHand.getCard1().getMispar()==7  && ourHand.getCard2().getMispar()==7  )   
				|| ( ourHand.getCard1().getMispar()==8  && ourHand.getCard2().getMispar()==8  )   )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<30 )
					{
						execution( Action.Raise   , bigBlind*2);
					}
					else if (cur<60 )
					{
						execution( Action.Raise   , bigBlind*3   );
					}
					else if (cur<90 )
					{
						execution( Action.Raise   , bigBlind*4   );
					}
					else 
					{
						execution( Action.Raise   , bigBlind*5   );
					}
				}
				
				
			
			// 66 55
			
			
			if  ( ( ourHand.getCard1().getMispar()==5  && ourHand.getCard2().getMispar()==5  )   
				|| ( ourHand.getCard1().getMispar()==6  && ourHand.getCard2().getMispar()==6  )   )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<30 )
					{
						execution( Action.Check   , 0);
					}
					else if (cur<70 )
					{
						execution( Action.Raise   , bigBlind*2   );
					}
					else 
					{
						execution( Action.Raise   , bigBlind*3   );
					}
				
				}
			
			
			
			// 44 33 22
			
			
			if  ( ( ourHand.getCard1().getMispar()==2  && ourHand.getCard2().getMispar()==2  )   
				|| ( ourHand.getCard1().getMispar()==3  && ourHand.getCard2().getMispar()==3  )   
				|| ( ourHand.getCard1().getMispar()==4  && ourHand.getCard2().getMispar()==4  )  )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<50 )
					{
						execution( Action.Check   , 0);
					}
					else if (cur<80 )
					{
						execution( Action.Raise   , bigBlind*2   );
					}
					else 
					{
						execution( Action.Raise   , bigBlind*3   );
					}
				
				}
			
			
			
			
			// KQ  KJ
			
			
			if  ( ( ourHand.getCard1().getMispar()==13  && ourHand.getCard2().getMispar()==12  )   ||  ( ourHand.getCard1().getMispar()==12  && ourHand.getCard2().getMispar()==13  )   
				|| ( ourHand.getCard1().getMispar()==13  && ourHand.getCard2().getMispar()==11  )   ||  ( ourHand.getCard1().getMispar()==11  && ourHand.getCard2().getMispar()==13  )    )
				
				{
					
					Random seed = new Random();
					int cur=seed.nextInt (100 ) ;
					if (cur<50 )
					{
						execution( Action.Check   , 0);
					}
					else 
					{
						execution( Action.Raise   , bigBlind*2   );
					}
				
				}
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			}  //  לפני פלופ - סוגר לא העלו לפנינו
			
			
			
			
			
			
			
			
			
			
			
			
		}  //סוגר לפני פלופ
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// ביצוע הפעולה ע"י הדמיית קליק עכבר במקום הרצוי במסך
	void execution(Action choosenAction ,int toRaise)
	{
		
	}
	

}
