package poker;

import java.util.Vector;

public class River {
	
	private Vector<Card> _river;
	
	public River()
	{
		_river= new Vector<Card>(5); 
	}
	
	
	
	public Vector<Card> get_river() {
		return _river;
	}

	public void set_river(Vector<Card> _river) {
		this._river = _river;
	}
	
	
	/*
	private Card card3; 
	private Card card4 ;
	private Card card5 ;
	private Card card6 ;
	private Card card7 ;
*/


	
	/*
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
	public Card getCard7() {
		return card7;
	}
	public void setCard7(Card card7) {
		this.card7 = card7;
	}
*/



}
