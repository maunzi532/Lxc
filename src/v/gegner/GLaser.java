package v.gegner;

import feld.*;
import v.*;
import v.ver.*;

public abstract class GLaser extends V
{
	public GLaser(double x, double y, double xsv, double ysv, double level)
	{
		this.level = level;
		speed = 1;
		leben = maxL();
		this.x = x;
		this.y = y;
		double a = Math.sqrt(xsv*xsv+ysv*ysv)/speed;
		xs = xsv/a;
		ys = ysv/a;
		Listen.glaser.add(this);
	}

	public void denken(){}

	public void xDenken()
	{
		checkCollisions(R_S.class, Listen.laser, true);
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
		if(leben <= 0)
		{
			destroy(false);
			return true;
		}
		return false;
	}

	boolean checkRand()
	{
		return x > VFeld.weit / 2 || x < -VFeld.weit / 2 || y > VFeld.hoch / 2 || y < -VFeld.hoch / 2;
	}

	public boolean isEigener()
	{
		return false;
	}
}