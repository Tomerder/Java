package poker;

import java.util.Vector;

public class Flop {

private Vector<Card> _flop;
	
	public Flop()
	{
		_flop= new Vector<Card>(4); 
	}
	
	
	
	public Vector<Card> get_flop() {
		return _flop;
	}

	public void set_flop(Vector<Card> _flop) {
		this._flop = _flop;
	}
	
	
	
	/*
	private Card card3; 
	private Card card4 ;
	private Card card5 ;

	
	
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
	
	*/

}
