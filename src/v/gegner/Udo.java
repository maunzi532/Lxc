package v.gegner;

import feld.*;
import v.ver.*;

import java.awt.*;

public class Udo extends Gegner
{
	double force1;
	double force2;
	double force2Max;
	int dmg;

	public Udo(double level)
	{
		super(level);
		speed = 0;
		maxspeed = 0;
		size = 10;
		force1 = 1;
		force2Max = 2;
		force2 = force2Max;
	}

	public void denken()
	{
		if(fact() < size * 20)
		{
			double d = force1 / fact();
			if(FL.ich.x != x)
				FL.ich.xs += xdiff() * d;
			if(FL.ich.y != y)
				FL.ich.ys += ydiff() * d;
		}
		for(int i = 0; i < Listen.laser.ende; i++)
		{
			R_S las = Listen.laser.get(i);
			if(las != null && Math.sqrt((las.x - x) * (las.x - x) + (las.y - y) * (las.y - y)) < size * 20)
			{
				double d = force2 / 100000 * Math.sqrt((las.x - x) * (las.x - x) + (las.y - y) * (las.y - y));
				if(las.x != x)
					las.xs += (las.x - x) * d;
				if(las.y != y)
					las.ys += (las.y - y) * d;
				force2 -= 0.005;
				if(force2 < 0)
					force2 = 0;
				dmg = 5;
			}
		}
		if(dmg > 0)
			dmg--;
		else
		{
			if(force2 < force2Max)
				force2 += 0.1;
			if(force2 > force2Max)
				force2 = force2Max;
		}
	}

	protected Color farbe()
	{
		return Color.YELLOW;
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 20 * level;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		gf.setColor(new Color(240, 200, 0, 100));
		gf.drawOval((int)(weit / 2 + x - VFeld.mZ % (size * 10) * 2),
				(int)(hoch / 2 + y - VFeld.mZ % (size * 10) * 2),
				VFeld.mZ % (int)(size * 10) * 4, VFeld.mZ % (int)(size * 10) * 4);
		gf.setColor(new Color(240, 200, 0, 25));
		gf.drawOval((int)(weit / 2 + x - size * 20), (int)(hoch / 2 + y - size * 20),
				(int)(size * 40), (int)(size * 40));
		super.aussehen(gf, weit, hoch);
	}
}