package poker;

import java.util.Vector;

public class Turn {
	
	

	private Vector<Card> _turn;
	
	public Turn()
	{
		_turn= new Vector<Card>(4); 
	}
	
	
	
	public Vector<Card> get_turn() {
		return _turn;
	}

	public void set_turn(Vector<Card> _turn) {
		this._turn = _turn;
	}
	
	
	
	/*
		
		private Card card3; 
		private Card card4 ;
		private Card card5 ;
		private Card card6 ;

		
		
		public Card getCard3() {
			return card3;
		}
		public void setCard3(Card card3) {
			this.card3 = card3;
		}
		public Card getCard4() {
			return card4;
		}
		public void setCard4(Card card4) {
			this.card4 = card4;
		}
		public Card getCard5() {
			return card5;
		}
		public void setCard5(Card card5) {
			this.card5 = card5;
		}
		public Card getCard6() {
			return card6;
		}
		public void setCard6(Card card6) {
			this.card6 = card6;
		}

	*/


}
