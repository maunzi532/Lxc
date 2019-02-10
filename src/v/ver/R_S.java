package v.ver;

import v.*;
import feld.*;

import java.awt.*;

public class R_S extends V
{
	private double maxL;

	public R_S(double x, double y, double xsv, double ysv, double schaden,
			double geschw, double abw)
	{
		leben = schaden;
		maxL = schaden;
		speed = geschw;
		maxspeed = speed;
		this.x = x;
		this.y = y;
		double a = Math.sqrt(xsv*xsv+ysv*ysv)/speed;
		if(a != 0)
		{
			xs = xsv / a;
			ys = ysv / a;
		}
		xs += (FL.r.nextDouble() - 0.5) / 50 * abw;
		ys += (FL.r.nextDouble() - 0.5) / 50 * abw;
		size = 2;
		Listen.laser.add(this);
	}

	protected void denken(){}

	public void xDenken(){}

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
		if(leben <= 0)
		{
			destroy(false);
			return true;
		}
		return false;
	}

	private boolean checkRand()
	{
		return x > VFeld.weit / 2 || x < -VFeld.weit / 2 || y > VFeld.hoch / 2 || y < -VFeld.hoch / 2;
	}

	public boolean isEigener()
	{
		return true;
	}

	protected double maxL()
	{
		return maxL;
	}

	public Color farbe()
	{
		return Color.RED;
	}
}