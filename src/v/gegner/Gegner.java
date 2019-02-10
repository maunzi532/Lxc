package v.gegner;

import feld.*;
import v.*;
import v.ver.*;

public abstract class Gegner extends V
{
	public Gegner(double level)
	{
		this.level = level;
		do{
			x = FL.r.nextInt(VFeld.weit) - VFeld.weit / 2;
			y = FL.r.nextInt(VFeld.hoch) - VFeld.hoch / 2;
		}while(factq() < FL.abstand * FL.abstand);
		leben = maxL();
		Listen.gegner.add(this);
	}

	public Gegner(double level, double spx, double spy, double spr)
	{
		this.level = level;
		x = spx;
		y = spy;
		if(spr > 0)
		{
			double xx = FL.r.nextDouble() - 0.5;
			double yy = FL.r.nextDouble() - 0.5;
			if(xx != 0 && yy != 0)
			{
				double a = Math.sqrt(xx * xx + yy * yy);
				double b = Math.sqrt(FL.r.nextDouble());
				xx = xx / a * b;
				yy = yy / a * b;
				x += xx * spr;
				y += yy * spr;
			}
		}
		leben = maxL();
		Listen.gegner.add(this);
	}

	public void xDenken()
	{
		checkCollisions(R_S.class, Listen.laser, true);
	}
}