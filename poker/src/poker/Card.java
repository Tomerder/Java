package poker;

import java.awt.Image;


	
public class Card {

	public enum Tzura { LEV , YAHALOM , TILTAN , ALE } ; 
	//enum Mispar { 2 ,3 , 4, 5 ,6,7 ,8,9,10 , J ,Q , K ,A  } ; 
	
	private Tzura _tzura ; 
	
	private int _mispar ;  //   J=11  Q=12   K=13  A = 15
	
	private Image _img;  //  ברגע שיש תמונה של קלף שנקלוט מהמסך נפענח איזה קלף זה ואיזו צורה לפי מאגר תמונות הקלפים שיש לנו  

	
	
	
	
	
	
	
	
	
	
	
	
	public Tzura getTzura() {
		return _tzura;
	}

	public void setTzura(Tzura tzura) {
		this._tzura = tzura;
	}

	public int getMispar() {
		return _mispar;
	}

	public void setMispar(int mispar) {
		this._mispar = mispar;
	}

	public Image getImg() {
		return _img;
	}

	public void setImg(Image img) {
		this._img = img;
	}
}
