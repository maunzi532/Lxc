package v.gegner;

import feld.*;

import java.awt.*;

public class AR extends GLaser
{
	public AR(double x, double y, double xsv, double ysv, double level)
	{
		super(x, y, xsv, ysv, level);
		speed = 0.2;
		leben = maxL();
		maxspeed = Math.sqrt(Math.sqrt(level));
		size = 2;
	}

	public void denken()
	{
		if(FL.ich.x != x && FL.ich.y != y)
		{
			xs += xdiff() * speed / fact();
			ys += ydiff() * speed / fact();
		}
		double a = Math.sqrt(xs * xs + ys * ys) / maxspeed;
		if(a > 1)
		{
			xs /= a;
			ys /= a;
		}
		super.denken();
	}

	public boolean bewegen()
	{
		if(stun <= 0)
			denken();
		else
		{
			xs = 0;
			ys = 0;
			stun--;
		}
		xDenken();
		x += xs;
		y += ys;
		if(leben <= 0)
		{
			destroy(false);
			return true;
		}
		return false;
	}

	protected void destroy(boolean laserOut)
	{
		if(!laserOut)
			Listen.explode(x, y, 0.1 * level, 60 * Math.sqrt(level), 100, ExplTyp.NICHT_AUF_GEGNER);
	}

	protected double maxL()
	{
		return level;
	}

	public Color farbe()
	{
		return Color.YELLOW;
	}
}