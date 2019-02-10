package rahmen;

import feld.*;
import pause.*;
import umgebung.*;

public class ThaLoop
{
	private static boolean pauseKl;
	private static long[] dauer = new long[Rahmen.zeittexte.length];
	//private static long davor = System.nanoTime();
	private static boolean zeitBlock;
	private static int zbD;
	private static boolean nexp;
	private static long over;

	public static void tha(LFrame fl, TastenAnalyse ta)
	{
		FL.start();
		LSound.lV();
		LSound.lS();
		Rahmen.setF();
		long blockZeit = Long.parseLong(T.set("BlockZeit"));
		while(true)
		{
			if(!ThaCre.fl.fr.hasFocus() && !P_Menu.p.istPause)
				P_Menu.setStatus(P_Typ.ING_PAUSE);
			long sta = System.currentTimeMillis();
			ThaCre.ta.move();
			Rahmen.setF();
			InV.setm();
			if(P_Menu.p.istPause)
				P_Menu.tick();
			else
				FL.bewegen();
			if(!zeitBlock)
			{
				Rahmen.hintergrund(fl.gd);
				VFeld.seheheh(nexp);
				InV.auf(fl.gd, Rahmen.fw, Rahmen.fh);
				Rahmen.rahmen(fl.gd);
				if(P_Menu.p.istPause)
					P_Menu.sichtAuf(fl.gd, Rahmen.fw, Rahmen.fh);
				fl.imgAufPl();
			}
			dauer[dauer.length - 1] = 0;
			for(int i = 0; i < dauer.length - 1; i++)
				dauer[dauer.length - 1] += dauer[i];
			if(ta.useKeys[6])
				System.exit(0);
			if(ta.useKeys[11] && !pauseKl)
				P_Menu.toggle();
			pauseKl = ta.useKeys[11];
			long z = System.currentTimeMillis();
			if(z < sta + blockZeit - over)
			{
				sleep(blockZeit - (z - sta) - over);
				zeitBlock = false;
				if(zbD == 0)
					nexp = false;
				zbD = 0;
				over = 0;
			}
			else if(!P_Menu.p.istPause)
			{
				zeitBlock = true;
				zbD++;
				if(zbD > 1)
					nexp = true;
				if(zbD > 5)
				{
					zeitBlock = false;
					zbD = 0;
				}
				over = z - sta - blockZeit;
			}
		}
	}

	/*private static void takeTime(int no)
	{
		dauer[no] = System.nanoTime() - davor;
		davor = System.nanoTime();
	}*/

	private static void sleep(long wieLange)
	{
		try{Thread.sleep(wieLange);}
		catch(Exception e){}
	}
}