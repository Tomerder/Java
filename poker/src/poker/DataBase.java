package poker;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.media.sound.Toolkit;

import poker.Card.Tzura;






public class DataBase {
	
	private static DataBase _instance;         // single tone
	
	public static DataBase getInstance()
	{
		if( _instance==null )
		{
			_instance=new DataBase();
		}
		return _instance;
	}
	
	
	
	
	Card _2_y=new Card();
	Card _2_l=new Card();
	Card _2_a=new Card();
	Card _2_t=new Card();
	
	Card _3_y=new Card();
	Card _3_l=new Card();
	Card _3_a=new Card();
	Card _3_t=new Card();
	
	Card _4_y=new Card();
	Card _4_l=new Card();
	Card _4_a=new Card();
	Card _4_t=new Card();
	
	Card _5_y=new Card();
	Card _5_l=new Card();
	Card _5_a=new Card();
	Card _5_t=new Card();
	
	Card _6_y=new Card();
	Card _6_l=new Card();
	Card _6_a=new Card();
	Card _6_t=new Card();
	
	Card _7_y=new Card();
	Card _7_l=new Card();
	Card _7_a=new Card();
	Card _7_t=new Card();
	
	Card _8_y=new Card();
	Card _8_l=new Card();
	Card _8_a=new Card();
	Card _8_t=new Card();
	
	Card _9_y=new Card();
	Card _9_l=new Card();
	Card _9_a=new Card();
	Card _9_t=new Card();
	
	Card _10_y=new Card();
	Card _10_l=new Card();
	Card _10_a=new Card();
	Card _10_t=new Card();
	
	Card _J_y=new Card();
	Card _J_l=new Card();
	Card _J_a=new Card();
	Card _J_t=new Card();
	
	Card _Q_y=new Card();
	Card _Q_l=new Card();
	Card _Q_a=new Card();
	Card _Q_t=new Card();
	
	Card _K_y=new Card();
	Card _K_l=new Card();
	Card _K_a=new Card();
	Card _K_t=new Card();
	
	Card _A_y=new Card();
	Card _A_l=new Card();
	Card _A_a=new Card();
	Card _A_t=new Card();
	
	
	
	
	
	private DataBase ()
	{
		initDB();
	}
	
	
	
	
	private void initDB() {

		
		//     אתחול כל הקלפים עם התמונות שלהם
		
		
		//////////  2_y
		
		Image img= new ImageIcon("klafim/2_y.bmp").getImage(); 
		_2_y=new Card();
		_2_y.setMispar(2);
		_2_y.setTzura(Tzura.YAHALOM);
		_2_y.setImg(img);
		
	
		//////////////
		
		//////////  2_l
		
		img= new ImageIcon("klafim/2_l.bmp").getImage(); 
		_2_l=new Card();
		_2_l.setMispar(2);
		_2_l.setTzura(Tzura.LEV);
		_2_l.setImg(img);
		
	
		//////////////
		
		//////////  2_a
		
		img= new ImageIcon("klafim/2_a.bmp").getImage(); 
		_2_a=new Card();
		_2_a.setMispar(2);
		_2_a.setTzura(Tzura.ALE);
		_2_a.setImg(img);
		
	
		//////////////
		
		//////////  2_t
		
		img= new ImageIcon("klafim/2_t.bmp").getImage(); 
		_2_t=new Card();
		_2_t.setMispar(2);
		_2_t.setTzura(Tzura.TILTAN);
		_2_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  3_y
		
		img= new ImageIcon("klafim/3_y.bmp").getImage(); 
		_3_y=new Card();
		_3_y.setMispar(3);
		_3_y.setTzura(Tzura.YAHALOM);
		_3_y.setImg(img);
		
	
		//////////////
		
		//////////  3_l
		
		img= new ImageIcon("klafim/3_l.bmp").getImage(); 
		_3_l=new Card();
		_3_l.setMispar(3);
		_3_l.setTzura(Tzura.LEV);
		_3_l.setImg(img);
		
	
		//////////////
		
		//////////  3_a
		
		img= new ImageIcon("klafim/3_a.bmp").getImage(); 
		_3_a=new Card();
		_3_a.setMispar(3);
		_3_a.setTzura(Tzura.ALE);
		_3_a.setImg(img);
		
	
		//////////////
		
		//////////  3_t
		
		img= new ImageIcon("klafim/3_t.bmp").getImage(); 
		_3_t=new Card();
		_3_t.setMispar(3);
		_3_t.setTzura(Tzura.TILTAN);
		_3_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  4_y
		
		img= new ImageIcon("klafim/4_y.bmp").getImage(); 
		_4_y=new Card();
		_4_y.setMispar(4);
		_4_y.setTzura(Tzura.YAHALOM);
		_4_y.setImg(img);
		
	
		//////////////
		
		//////////  4_l
		
		img= new ImageIcon("klafim/4_l.bmp").getImage(); 
		_4_l=new Card();
		_4_l.setMispar(4);
		_4_l.setTzura(Tzura.LEV);
		_4_l.setImg(img);
		
	
		//////////////
		
		//////////  4_a
		
		img= new ImageIcon("klafim/4_a.bmp").getImage(); 
		_4_a=new Card();
		_4_a.setMispar(4);
		_4_a.setTzura(Tzura.ALE);
		_4_a.setImg(img);
		
	
		//////////////
		
		//////////  4_t
		
		img= new ImageIcon("klafim/4_t.bmp").getImage(); 
		_4_t=new Card();
		_4_t.setMispar(4);
		_4_t.setTzura(Tzura.TILTAN);
		_4_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  5_y
		
		img= new ImageIcon("klafim/5_y.bmp").getImage(); 
		_5_y=new Card();
		_5_y.setMispar(5);
		_5_y.setTzura(Tzura.YAHALOM);
		_5_y.setImg(img);
		
	
		//////////////
		
		//////////  5_l
		
		img= new ImageIcon("klafim/5_l.bmp").getImage(); 
		_5_l=new Card();
		_5_l.setMispar(5);
		_5_l.setTzura(Tzura.LEV);
		_5_l.setImg(img);
		
	
		//////////////
		
		//////////  5_a
		
		img= new ImageIcon("klafim/5_a.bmp").getImage(); 
		_5_a=new Card();
		_5_a.setMispar(5);
		_5_a.setTzura(Tzura.ALE);
		_5_a.setImg(img);
		
	
		//////////////
		
		//////////  5_t
		
		img= new ImageIcon("klafim/5_t.bmp").getImage(); 
		_5_t=new Card();
		_5_t.setMispar(5);
		_5_t.setTzura(Tzura.TILTAN);
		_5_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		
		//////////  6_y
		
		img= new ImageIcon("klafim/6_y.bmp").getImage(); 
		_6_y=new Card();
		_6_y.setMispar(6);
		_6_y.setTzura(Tzura.YAHALOM);
		_6_y.setImg(img);
		
	
		//////////////
		
		//////////  6_l
		
		img= new ImageIcon("klafim/6_l.bmp").getImage(); 
		_6_l=new Card();
		_6_l.setMispar(6);
		_6_l.setTzura(Tzura.LEV);
		_6_l.setImg(img);
		
	
		//////////////
		
		//////////  6_a
		
		img= new ImageIcon("klafim/6_a.bmp").getImage(); 
		_6_a=new Card();
		_6_a.setMispar(6);
		_6_a.setTzura(Tzura.ALE);
		_6_a.setImg(img);
		
	
		//////////////
		
		//////////  6_t
		
		img= new ImageIcon("klafim/6_t.bmp").getImage(); 
		_6_t=new Card();
		_6_t.setMispar(6);
		_6_t.setTzura(Tzura.TILTAN);
		_6_t.setImg(img);
		
	
		//////////////
		
		
		

		
		
		//////////  7_y
		
		img= new ImageIcon("klafim/7_y.bmp").getImage(); 
		_7_y=new Card();
		_7_y.setMispar(7);
		_7_y.setTzura(Tzura.YAHALOM);
		_7_y.setImg(img);
		
	
		//////////////
		
		//////////  7_l
		
		img= new ImageIcon("klafim/7_l.bmp").getImage(); 
		_7_l=new Card();
		_7_l.setMispar(7);
		_7_l.setTzura(Tzura.LEV);
		_7_l.setImg(img);
		
	
		//////////////
		
		//////////  7_a
		
		img= new ImageIcon("klafim/7_a.bmp").getImage(); 
		_7_a=new Card();
		_7_a.setMispar(7);
		_7_a.setTzura(Tzura.ALE);
		_7_a.setImg(img);
		
	
		//////////////
		
		//////////  7_t
		
		img= new ImageIcon("klafim/7_t.bmp").getImage(); 
		_7_t=new Card();
		_7_t.setMispar(7);
		_7_t.setTzura(Tzura.TILTAN);
		_7_t.setImg(img);
		
	
		//////////////
		
	
	
	
	
		//////////  8_y
		
		img= new ImageIcon("klafim/8_y.bmp").getImage(); 
		_8_y=new Card();
		_8_y.setMispar(8);
		_8_y.setTzura(Tzura.YAHALOM);
		_8_y.setImg(img);
		
	
		//////////////
		
		//////////  8_l
		
		img= new ImageIcon("klafim/8_l.bmp").getImage(); 
		_8_l=new Card();
		_8_l.setMispar(8);
		_8_l.setTzura(Tzura.LEV);
		_8_l.setImg(img);
		
	
		//////////////
		
		//////////  8_a
		
		img= new ImageIcon("klafim/8_a.bmp").getImage(); 
		_8_a=new Card();
		_8_a.setMispar(8);
		_8_a.setTzura(Tzura.ALE);
		_8_a.setImg(img);
		
	
		//////////////
		
		//////////  8_t
		
		img= new ImageIcon("klafim/8_t.bmp").getImage(); 
		_8_t=new Card();
		_8_t.setMispar(8);
		_8_t.setTzura(Tzura.TILTAN);
		_8_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  9_y
		
		img= new ImageIcon("klafim/9_y.bmp").getImage(); 
		_9_y=new Card();
		_9_y.setMispar(9);
		_9_y.setTzura(Tzura.YAHALOM);
		_9_y.setImg(img);
		
	
		//////////////
		
		//////////  9_l
		
		img= new ImageIcon("klafim/9_l.bmp").getImage(); 
		_9_l=new Card();
		_9_l.setMispar(9);
		_9_l.setTzura(Tzura.LEV);
		_9_l.setImg(img);
		
	
		//////////////
		
		//////////  9_a
		
		img= new ImageIcon("klafim/9_a.bmp").getImage(); 
		_9_a=new Card();
		_9_a.setMispar(9);
		_9_a.setTzura(Tzura.ALE);
		_9_a.setImg(img);
		
	
		//////////////
		
		//////////  9_t
		
		img= new ImageIcon("klafim/9_t.bmp").getImage(); 
		_9_t=new Card();
		_9_t.setMispar(9);
		_9_t.setTzura(Tzura.TILTAN);
		_9_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  10_y
		
		img= new ImageIcon("klafim/10_y.bmp").getImage(); 
		_10_y=new Card();
		_10_y.setMispar(10);
		_10_y.setTzura(Tzura.YAHALOM);
		_10_y.setImg(img);
		
	
		//////////////
		
		//////////  10_l
		
		img= new ImageIcon("klafim/10_l.bmp").getImage(); 
		_10_l=new Card();
		_10_l.setMispar(10);
		_10_l.setTzura(Tzura.LEV);
		_10_l.setImg(img);
		
	
		//////////////
		
		//////////  10_a
		
		img= new ImageIcon("klafim/10_a.bmp").getImage(); 
		_10_a=new Card();
		_10_a.setMispar(10);
		_10_a.setTzura(Tzura.ALE);
		_10_a.setImg(img);
		
	
		//////////////
		
		//////////  10_t
		
		img= new ImageIcon("klafim/10_t.bmp").getImage(); 
		_10_t=new Card();
		_10_t.setMispar(10);
		_10_t.setTzura(Tzura.TILTAN);
		_10_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		
		//////////  J_y
		
		img= new ImageIcon("klafim/J_y.bmp").getImage(); 
		_J_y=new Card();
		_J_y.setMispar(11);
		_J_y.setTzura(Tzura.YAHALOM);
		_J_y.setImg(img);
		
	
		//////////////
		
		//////////  J_l
		
		img= new ImageIcon("klafim/J_l.bmp").getImage(); 
		_J_l=new Card();
		_J_l.setMispar(11);
		_J_l.setTzura(Tzura.LEV);
		_J_l.setImg(img);
		
	
		//////////////
		
		//////////  J_a
		
		img= new ImageIcon("klafim/J_a.bmp").getImage(); 
		_J_a=new Card();
		_J_a.setMispar(11);
		_J_a.setTzura(Tzura.ALE);
		_J_a.setImg(img);
		
	
		//////////////
		
		//////////  J_t
		
		img= new ImageIcon("klafim/J_t.bmp").getImage(); 
		_J_t=new Card();
		_J_t.setMispar(11);
		_J_t.setTzura(Tzura.TILTAN);
		_J_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		
		//////////  Q_y
		
		img= new ImageIcon("klafim/Q_y.bmp").getImage(); 
		_Q_y=new Card();
		_Q_y.setMispar(12);
		_Q_y.setTzura(Tzura.YAHALOM);
		_Q_y.setImg(img);
		
	
		//////////////
		
		//////////  Q_l
		
		img= new ImageIcon("klafim/Q_l.bmp").getImage(); 
		_Q_l=new Card();
		_Q_l.setMispar(12);
		_Q_l.setTzura(Tzura.LEV);
		_Q_l.setImg(img);
		
	
		//////////////
		
		//////////  Q_a
		
		img= new ImageIcon("klafim/Q_a.bmp").getImage(); 
		_Q_a=new Card();
		_Q_a.setMispar(12);
		_Q_a.setTzura(Tzura.ALE);
		_Q_a.setImg(img);
		
	
		//////////////
		
		//////////  Q_t
		
		img= new ImageIcon("klafim/Q_t.bmp").getImage(); 
		_Q_t=new Card();
		_Q_t.setMispar(12);
		_Q_t.setTzura(Tzura.TILTAN);
		_Q_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		//////////  K_y
		
		img= new ImageIcon("klafim/K_y.bmp").getImage(); 
		_K_y=new Card();
		_K_y.setMispar(13);
		_K_y.setTzura(Tzura.YAHALOM);
		_K_y.setImg(img);
		
	
		//////////////
		
		//////////  K_l
		
		img= new ImageIcon("klafim/K_l.bmp").getImage(); 
		_K_l=new Card();
		_K_l.setMispar(13);
		_K_l.setTzura(Tzura.LEV);
		_K_l.setImg(img);
		
	
		//////////////
		
		//////////  K_a
		
		img= new ImageIcon("klafim/K_a.bmp").getImage(); 
		_K_a=new Card();
		_K_a.setMispar(13);
		_K_a.setTzura(Tzura.ALE);
		_K_a.setImg(img);
		
	
		//////////////
		
		//////////  K_t
		
		img= new ImageIcon("klafim/K_t.bmp").getImage(); 
		_K_t=new Card();
		_K_t.setMispar(13);
		_K_t.setTzura(Tzura.TILTAN);
		_K_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		
		//////////  A_y
			
		img= new ImageIcon("klafim/A_y.bmp").getImage(); 
		_A_y=new Card();
		_A_y.setMispar(15);
		_A_y.setTzura(Tzura.YAHALOM);
		_A_y.setImg(img);
		
	
		//////////////
		
		//////////  A_l
		
		img= new ImageIcon("klafim/A_l.bmp").getImage(); 
		_A_l=new Card();
		_A_l.setMispar(15);
		_A_l.setTzura(Tzura.LEV);
		_A_l.setImg(img);
		
	
		//////////////
		
		//////////  A_a
		
		img= new ImageIcon("klafim/A_a.bmp").getImage(); 
		_A_a=new Card();
		_A_a.setMispar(15);
		_A_a.setTzura(Tzura.ALE);
		_A_a.setImg(img);
		
	
		//////////////
		
		//////////  A_t
		
		img= new ImageIcon("klafim/A_t.bmp").getImage(); 
		_A_t=new Card();
		_A_t.setMispar(15);
		_A_t.setTzura(Tzura.TILTAN);
		_A_t.setImg(img);
		
	
		//////////////
		
		
		
		
		
		
		
		
		//       אתחול כל הספרות - 10 ספרות (כסף בקופה למשל 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
