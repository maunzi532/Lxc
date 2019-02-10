package v.ver;

import feld.*;
import v.gegner.*;
import v.*;

import java.awt.*;

public class SGL extends R_S
{
	private double inxs;
	private double inys;
	private double inix;
	private double iniy;
	private V ziel;

	public SGL(double x, double y, double xsv, double ysv, double l, double sp, double abw, V ziel)
	{
		super(x, y, xsv, ysv, l, sp, abw);
		size = 3;
		double a = Math.sqrt(xs*xs+ys*ys)/speed;
		inxs = xs/a;
		inys = ys/a;
		inix = x;
		iniy = y;
		this.ziel = ziel;
	}

	protected void denken()
	{
		if(ziel == null)
		{
			boolean found = false;
			for(Object ex : Listen.gegner.arr)
				if(ex != null)
				{
					Gegner g = (Gegner) ex;
					if(!(g instanceof Nex) && Listen.nahe(this, g) < Listen.nahe(this, inix, iniy) / 8)
					{
						verfolgen(g);
						found = true;
						break;
					}
				}
			if(!found)
			{
				xs = inxs;
				ys = inys;
			}
		}
		else if(ziel.leben > 0)
			verfolgen(ziel);
		else
			ziel = null;
		double a = Math.sqrt((xs*xs+ys*ys))/maxspeed;
		if(a > 1)
		{
			xs /= a;
			ys /= a;
		}
	}

	private void verfolgen(V g)
	{
		if(g.x != x && g.y != y)
		{
			double d = speed / Math.sqrt((g.x - x) * (g.x - x) + (g.y - y) * (g.y - y));
			xs += (g.x - x) * d;
			ys += (g.y - y) * d;
		}
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		double xst = xs * 6.0d / Math.sqrt(xs * xs + ys * ys);
		double yst = ys * 6.0d / Math.sqrt(xs * xs + ys * ys);
		Polygon tL = new Polygon(new int[4], new int[4], 4);
		tL.xpoints = new int[]{(int) (-xst * 3), (int) (-yst - xst * 4), 0, (int) (yst - xst * 4)};
		tL.ypoints = new int[]{(int) (-yst * 3), (int) (xst - yst * 4), 0, (int) (-xst - yst * 4)};
		V.tL(weit, hoch, this, 3, tL);
		super.aussehen(gf, tL);
	}

	public Color farbe()
	{
		return Color.WHITE;
	}
}