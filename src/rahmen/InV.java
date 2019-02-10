package rahmen;

import feld.*;
import umgebung.*;

import java.awt.*;

public class InV
{
	//Gezoomt
	private static int wfz;
	private static int hfz;
	//Abstand des Ziels vom Spieler
	public static double mausx;
	public static double mausy;
	//Im-Feld-Verschiebung
	private static int mx;
	private static int my;
	//Zoom
	private static double zoom = 1;

	public static void mausxy()
	{
		mausx = ThaCre.ta.maus.x * zoom - VFeld.weit / 2 + mx - FL.ich.x;
		mausy = ThaCre.ta.maus.y * zoom - VFeld.hoch / 2 + my - FL.ich.y;
	}

	public static void auf(Graphics2D gd, int w, int h)
	{
		gd.drawImage(VFeld.bild, 0, 0, w, h,
				mx, my, mx + wfz, my + hfz, null);
	}

	public static void setm()
	{
		if(ThaCre.ta.useKeys[16])
			zoom /= 0.875;
		if(ThaCre.ta.useKeys[17])
			zoom *= 0.875;
		wfz = (int)(Rahmen.fw * zoom);
		hfz = (int)(Rahmen.fh * zoom);
		if(VFeld.weit > wfz)
		{
			int fx = 0;
			if(FL.ich != null)
				fx = (int) FL.ich.x;
			mx = fx + (VFeld.weit - wfz) / 2;
			if(mx > VFeld.weit - wfz)
				mx = VFeld.weit - wfz;
			else if(mx < 0)
				mx = 0;
		}
		else
			mx = (VFeld.weit - wfz) / 2;
		if(VFeld.hoch > hfz)
		{
			int fy = 0;
			if(FL.ich != null)
				fy = (int) FL.ich.y;
			my = fy + (VFeld.hoch - hfz) / 2;
			if(my > VFeld.hoch - hfz)
				my = VFeld.hoch - hfz;
			else if(my < 0)
				my = 0;
		}
		else
			my = (VFeld.hoch - hfz) / 2;
	}
}