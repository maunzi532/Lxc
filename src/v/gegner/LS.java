package v.gegner;

import v.*;

import java.awt.*;

public class LS extends GLaser
{
	private Lena besitzer;

	public LS(double x, double y, double xsv, double ysv, double level, Lena besitzer)
	{
		super(x, y, xsv, ysv, level);
		size = 10;
		speed = Math.sqrt(level) + 5;
		maxspeed = speed;
		double a = Math.sqrt(xsv*xsv+ysv*ysv)/speed;
		xs = xsv / a;
		ys = ysv / a;
		this.besitzer = besitzer;
	}

	public boolean bewegen()
	{
		if(stun <= 0)
		{
			denken();
			x += xs;
			y += ys;
		}
		else
			stun--;
		xDenken();
		if(checkRand())
		{
			destroy(true);
			return true;
		}
		if(besitzer != null)
		{
			if(besitzer.destroySchild)
			{
				xs = besitzer.xs;
				ys = besitzer.ys;
				besitzer = null;
				super.bewegen();
			}
			else
			{
				x = besitzer.x + besitzer.xs;
				y = besitzer.y + besitzer.ys;
				if(leben <= 0)
				{
					destroy(false);
					return true;
				}
			}
		}
		else
			super.bewegen();
		return false;
	}

	protected Color farbe()
	{
		return Color.PINK;
	}

	protected double maxL()
	{
		return level * 7;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		double xst;
		double yst;
		if(besitzer != null)
		{
			double d = 6.0d / Math.sqrt(besitzer.xs * besitzer.xs + besitzer.ys * besitzer.ys);
			xst = besitzer.xs * d;
			yst = besitzer.ys * d;
		}
		else
		{
			double d = 6.0d / Math.sqrt(xs * xs + ys * ys);
			xst = xs * d;
			yst = ys * d;
		}
		Polygon tL = new Polygon(new int[4], new int[4], 4);
		tL.xpoints = new int[]{(int) (xst * 3), (int) (-yst * 4 - xst * 1),
				(int) (xst * 1), (int) (yst * 4 - xst * 1)};
		tL.ypoints = new int[]{(int) (yst * 3), (int) (xst * 4 - yst * 1),
				(int) (yst * 1), (int) (-xst * 4 - yst * 1)};
		V.tL(weit, hoch, this, 10, tL);
		super.aussehen(gf, tL);
	}
}