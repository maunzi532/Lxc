package v.gegner;

import v.*;
import feld.*;

import java.awt.*;

public class IVIL extends Gegner
{
	private static Color cl = Color.RED.brighter().brighter();

	private int warte = 1;
	private boolean richtung;

	public IVIL(double level)
	{
		super(level);
		warte = (int) (50 / Math.sqrt(Math.sqrt(level)));
		speed = 0.5;
		maxspeed = 1;
		richtung = FL.r.nextBoolean();
	}

	private void spawnLaser()
	{
		Listen.alle.add(new IM(x, y, level));
		warte = (int) (200 / Math.sqrt(Math.sqrt(level)));
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		VFeld.leuchten(gf, 0);
		double xst = xs * 30.0d / Math.sqrt(xs * xs + ys * ys);
		double yst = ys * 30.0d / Math.sqrt(xs * xs + ys * ys);
		Polygon tL = new Polygon(new int[4], new int[4], 4);
		tL.xpoints = new int[]{-(int)xst, -(int)yst, (int)xst, (int)yst};
		tL.ypoints = new int[]{-(int)yst, (int)xst, (int)yst, -(int)xst};
		V.tL(weit, hoch, this, 5, tL);
		gf.fillPolygon(tL);
		super.aussehen(gf, weit, hoch);
	}

	public Color farbe()
	{
		return cl;
	}

	public void denken()
	{
		if(FL.ich.x != x && FL.ich.y != y)
		{
			double dxfs = xdiff() / fact() * speed;
			double dyfs = ydiff() / fact() * speed;
			if(richtung)
			{
				xs += dxfs / 5 - dyfs;
				ys += dxfs + dyfs / 5;
			}
			else
			{
				xs += dyfs + dxfs / 5;
				ys += dyfs / 5 - dxfs;
			}
		}
		if(warte > 0)
			warte--;
		if(warte == 0)
			spawnLaser();
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 30 * level;
	}
}