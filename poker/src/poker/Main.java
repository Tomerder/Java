package poker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import quicktime.app.event.MouseButtonListener;

 











//   TRY
public class Main {

	
	
	
	// ���� ����� ��� ����� ����� ��� ��� ����� ����� ���� ����

	
	private static BufferedImage _firstCard;
	
	
	//private static MouseButtonListener mouse;
	
	
	

	public static void main ( String [ ] args ) throws AWTException, IOException, InterruptedException
	{
		
		
		DataBase.getInstance()._10_a.getMispar();     // ����� ����� �� ����� ������
		while (  true )	
		{
			
			Thread.sleep(100000);
			
			
			// ���� �� ���� ����� - ����� ����� �� ����
			
			
			Robot robot = new Robot();


			_firstCard = robot.createScreenCapture(new Rectangle(346,223    , 27 ,  45  ));

			ImageIO.write(_firstCard, "JPG", new File("firstCard.jpg"));


			
			// ����� ���� ������ ����� ��� ��� ������ ����� ������
			
			
			
	//		for(int i=0; i<   _firstCard.getHeight() - DataBase.getInstance()._A_l.getImg(). )
	//      {              
	//    		_firstCard.getSubimage(x, y, w, h)
			
			
	//   	}
			
			
			
			
		}

		
		
		
	}
	
}
