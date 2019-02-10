package v.ver;

import feld.*;
import v.gegner.*;
import v.*;

import java.awt.*;

public class Rakete extends R_S
{
	private double schaden;
	private boolean bereits;
	private V ziel;

	public Rakete(double x, double y, double xsv, double ysv, double l, double sp, double abw, V ziel)
	{
		super(x, y, xsv, ysv, 1, sp, abw);
		schaden = l;
		size = 3;
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
					if(!(g instanceof Nex) && Listen.nahe(this, g) < 70)
					{
						verfolgen(g);
						found = true;
						bereits = true;
						break;
					}
				}
			if(!found && bereits)
				leben = 0;
		}
		else if(ziel.leben > 0)
			verfolgen(ziel);
		else
			ziel = null;
		double a = Math.sqrt(xs * xs + ys * ys) / maxspeed;
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

	public void destroy(boolean laserOut)
	{
		if(!laserOut)
			Listen.explode(x, y, schaden * 0.02, 100, 50, ExplTyp.NUR_AUF_FEINDE);
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		double xst = xs * (6 + (VFeld.mZ % 20 - (VFeld.mZ + 10) % 20) / 15f) / Math.sqrt(xs * xs + ys * ys);
		double yst = ys * (6 - (VFeld.mZ % 20 - (VFeld.mZ + 10) % 20) / 15f) / Math.sqrt(xs * xs + ys * ys);
		Polygon tL = new Polygon(new int[4], new int[4], 4);
		tL.xpoints = new int[]{(int) (-xst * 1.6), (int) (-yst - xst * 2), 0, (int) (yst - xst * 2)};
		tL.ypoints = new int[]{(int) (-yst * 1.6), (int) (xst - yst * 2), 0, (int) (-xst - yst * 2)};
		V.tL(weit, hoch, this, 3, tL);
		super.aussehen(gf, tL);
	}

	public Color farbe()
	{
		if(ziel == null)
			return Color.RED;
		else
			return Color.ORANGE;
	}
}