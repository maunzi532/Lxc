package pause;

import rahmen.*;
import pause.tab.*;
import feld.*;

import java.awt.*;

public class P_Menu
{
	public static P_Typ p;
	private static P_Tab tab;
	private static int swTab;
	private static P_Tab next;
	private static boolean nxr;
	private static final int swZeit = 25;

	public static void tick()
	{
		if(next == null)
			/*tab.tick()*/;
		else
		{
			swTab--;
			if(swTab <= 0)
			{
				tab = next;
				next = null;
				tab.aufruf();
			}
		}
		tab.sehen1();
		if(next != null)
			next.sehen1();
	}

	public static void switchTab(P_Tab t, boolean nxxr)
	{
		tab.weg();
		next = t;
		swTab = swZeit;
		nxr = nxxr;
	}

	public static void toggle()
	{
		if(!p.istPause)
			setStatus(P_Typ.ING_PAUSE);
		else
			if(P_Menu.p.upgrades)
			{
				setStatus(P_Typ.NICHT);
				FL.neuStarten();
			}
			else
				setStatus(P_Typ.NICHT);
	}

	public static void setStatus(P_Typ p1)
	{
		p = p1;
		switch(p)
		{
			case NICHT:
				if(swTab <= 0)
					tab.weg();
				tab = null;
				next = null;
				Rahmen.lSwitcher = null;
				Rahmen.rSwitcher = null;
				break;
			case ING_PAUSE:
				tab = new Tab_Analyse();
				tab.aufruf();
				break;
			case ZWISCHEN:
				tab = new Tab_Waffen();
				tab.aufruf();
				break;
			case START:
				tab = new Tab_Waffen();
				tab.aufruf();
				break;
		}
	}

	public static void sichtAuf(Graphics2D gd, int w, int h)
	{
		if(next == null)
			tab.auf(gd, w, h, 0, null);
		else
		{
			tab.auf(gd, w, h, (swZeit - swTab) / (double) swZeit, !nxr);
			next.auf(gd, w, h, swTab / (double) swZeit, nxr);
		}
	}
}