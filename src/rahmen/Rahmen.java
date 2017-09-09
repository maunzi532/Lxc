package rahmen;

import ability.*;
import pause.*;
import feld.*;
import umgebung.*;

import java.awt.*;

public class Rahmen
{
	public static final String[] zeittexte = new String[]
			{"slf: " , "TA: ", "FL|P ", "FS: ", "DS: ", "clr: ", "DA: ", "FA: ", "PA: ", "IM: ", "erg: "};

	public static final String FONT = T.set("Font");
	private static final Color dunkel = new Color(0, 0, 0, 200);

	public static int fw = LFrame.scs.width;
	public static int fh = LFrame.scs.height;

	public static Switcher rSwitcher;
	private static boolean scrTaste;
	public static Switcher lSwitcher;
	private static boolean sclTaste;

	public static void setF()
	{
		fw = ThaCre.fl.fr.getWidth();
		fh = ThaCre.fl.fr.getHeight();
	}

	public static void hintergrund(Graphics2D gd)
	{
		gd.setColor(Color.BLACK);
		gd.fillRect(0, 0, fw, fh);
	}

	public static void rahmen(Graphics2D gd)
	{
		scrLR();
		Ability a = null;
		if(FL.ich != null)
			a = FL.ich.az.a[0];
		Ability a2 = null;
		if(FL.ich != null)
			a2 = FL.ich.az.a[1];
		rahmenOben(gd);
		rahmenUnten(gd, a, a2);
		rahmenLR(gd);
	}

	private static void scrLR()
	{
		if(lSwitcher != null)
		{
			lSwitcher.adjTCH();
			if(ThaCre.ta.useKeys[12] && !sclTaste)
			{
				lSwitcher.next();
				sclTaste = true;
			}
			if(!ThaCre.ta.useKeys[12])
				sclTaste = false;
		}
		if(rSwitcher != null)
		{
			rSwitcher.adjTCH();
			if(ThaCre.ta.useKeys[10] && !scrTaste)
			{
				rSwitcher.next();
				scrTaste = true;
			}
			if(!ThaCre.ta.useKeys[10])
				scrTaste = false;
		}
	}

	private static void rahmenOben(Graphics2D gd)
	{
		gd.setColor(dunkel);
		gd.fill(new Polygon(new int[]{0, tw(0.7), tw(0.65), 0},
				new int[]{th(0.04), th(0.02), th(0.11), th(0.12)}, 4));
		if(!P_Menu.p.upgrades)
		{
			gd.setFont(new Font(FONT, 0, th(0.052)));
			gd.setColor(Color.GREEN);
			if(FL.ich.leben > 0)
				gd.drawString((int) FL.ich.leben + " / " + (int) FL.ich.llv,
						tw(0.33), th(0.09));
			else
				gd.drawString(T.tex("Hin"), tw(0.37), th(0.09));
			gd.setColor(VFeld.zf2);
			gd.drawString(String.valueOf(FL.zeit), tw(0.57), th(0.09));
		}
		gd.drawImage(Grafiken.grafiken.get(T.set("Logo")),
				tw(0.02), th(0.04), tw(610 / 2404d), th(88 / 1351d), null);
		if(!P_Menu.p.upgrades)
		{
			gd.setColor(Color.RED);
			gd.fill(new Polygon(new int[]{tw(0.06), tw(0.645), tw(0.635), tw(0.05)},
					new int[]{th(0.123), th(0.113), th(0.125), th(0.14)}, 4));
			gd.setColor(Color.GREEN);
			gd.fill(new Polygon(new int[]{tw(0.06), tw(0.06) + tw(0.585 * FL.ich.leben / FL.ich.llv),
					tw(0.05) + tw(0.585 * FL.ich.leben / FL.ich.llv), tw(0.05)},
					new int[]{th(0.123), th(0.123) - th(0.01 * FL.ich.leben / FL.ich.llv) - 1,
					th(0.14) - th(0.015 * FL.ich.leben / FL.ich.llv) + 1, th(0.14)}, 4));
		}
	}

	private static void rahmenUnten(Graphics2D gd, Ability a, Ability a2)
	{
		gd.setColor(dunkel);
		gd.fill(new Polygon(new int[]{fw, tw(0.3), tw(0.35), fw},
				new int[]{th(0.96), th(0.98), th(0.89), th(0.88)}, 4));
		if(!P_Menu.p.upgrades)
		{
			if(FL.ich.lw != null)
			{
				//Waffe links
				gd.setColor(Color.YELLOW);
				gd.drawString(String.valueOf(FL.ich.lw.getName()),
						tw(0.38), th(0.95));
				//Munition links
				gd.setColor(Color.RED);
				gd.fillRect(tw(0.5), th(0.91), tw(FL.ich.lw.mLeiste() * 0.07), th(0.04));
				//Nachladen links
				gd.setColor(Color.BLUE);
				gd.fillRect(tw(0.5), th(0.91), tw(FL.ich.lw.cldLeiste() * 0.07), th(0.02));
			}
			if(FL.ich.rw != null)
			{
				//Waffe rechts
				gd.setColor(Color.YELLOW);
				gd.drawString(String.valueOf(FL.ich.rw.getName()),
						tw(0.85), th(0.95));
				//Munition rechts
				gd.setColor(Color.RED);
				gd.fillRect(tw(0.75), th(0.91), tw(FL.ich.rw.mLeiste() * 0.07), th(0.04));
				//Nachladen rechts
				gd.setColor(Color.BLUE);
				gd.fillRect(tw(0.75), th(0.91), tw(FL.ich.rw.cldLeiste() * 0.07), th(0.02));
			}
		}
		gd.setColor(Color.GREEN);
		gd.fill(new Polygon(new int[]{tw(0.94), tw(0.355), tw(0.365), tw(0.95)},
				new int[]{th(0.877), th(0.887), th(0.875), th(0.86)}, 4));
		if(a != null && a.cooldown > 0 && a.xdown > 0)
		{
			gd.setColor(Color.YELLOW);
			gd.fill(new Polygon(new int[]{tw(0.94), tw(0.355), tw(0.365), tw(0.95)},
					new int[]{th(0.877), th(0.887), th(0.875), th(0.86)}, 4));
			gd.setColor(Color.RED);
			gd.fill(new Polygon(new int[]{tw(0.94), tw(0.94) - tw(0.585) * a.xdown / a.cooldown,
					tw(0.95) - tw(0.585) * a.xdown / a.cooldown, tw(0.95)},
					new int[]{th(0.877), th(0.877) + th(0.01) * a.xdown / a.cooldown + 1,
							th(0.86) + th(0.015) * a.xdown / a.cooldown, th(0.86)}, 4));
		}
		gd.setColor(Color.GREEN);
		gd.fill(new Polygon(new int[]{tw(0.94), tw(0.355), tw(0.365), tw(0.95)},
				new int[]{th(0.857), th(0.867), th(0.855), th(0.84)}, 4));
		if(a2 != null && a2.cooldown > 0 && a.xdown > 0)
		{
			gd.setColor(Color.YELLOW);
			gd.fill(new Polygon(new int[]{tw(0.94), tw(0.355), tw(0.365), tw(0.95)},
					new int[]{th(0.857), th(0.867), th(0.855), th(0.84)}, 4));
			gd.setColor(Color.RED);
			gd.fill(new Polygon(new int[]{tw(0.94), tw(0.94) - tw(0.585) * a2.xdown / a2.cooldown,
					tw(0.95) - tw(0.585) * a2.xdown / a2.cooldown, tw(0.95)},
					new int[]{th(0.857), th(0.857) + th(0.01) * a2.xdown / a2.cooldown + 1,
							th(0.84) + th(0.015) * a2.xdown / a2.cooldown, th(0.84)}, 4));
		}
	}

	private static void rahmenLR(Graphics2D gd)
	{
		if(lSwitcher != null)
			lSwitcher.draw(gd, tw(0.02), th(0.23), tw(0.1), th(0.54), 1);
		//zeit_check(gd);
		if(rSwitcher != null)
			rSwitcher.draw(gd, tw(0.88), th(0.23), tw(0.1), th(0.54), 1);
	}

	/*private static void zeit_check(Graphics2D gd)
	{
		if(ThaLoop.zeitBlock)
			gd.setColor(Color.DARK_GRAY);
		else
			gd.setColor(Color.BLACK);
		gd.fillRect(20, fh / 2 - 10 * (ThaLoop.dauer.length + 1),
				100, 20 * (ThaLoop.dauer.length + 1));
		gd.setColor(Color.BLUE);
		gd.setFont(new Font(FONT, Font.PLAIN, 12));
		for(int i = 0; i < ThaLoop.dauer.length; i++)
			gd.drawString(zeittexte[i] + String.valueOf(ThaLoop.dauer[i] / 1000), 30,
					fh / 2 - 10 * (ThaLoop.dauer.length + 1) + 30 + 20 * i);
	}*/

	public static int tw(double factor)
	{
		return (int) (fw * factor);
	}

	public static int th(double factor)
	{
		return (int) (fh * factor);
	}
}