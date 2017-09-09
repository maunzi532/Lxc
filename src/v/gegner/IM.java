package v.gegner;

import feld.*;

import java.awt.*;

public class IM extends GLaser
{
	public IM(double x, double y, double level)
	{
		super(x, y, 0, 0, level);
		speed = 0;
		leben = maxL();
		maxspeed = 0;
		size = 2;
	}

	public boolean bewegen()
	{
		if(stun <= 0)
			denken();
		else
			stun--;
		xDenken();
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
			Listen.explode(x, y, 0.5 * Math.sqrt(level), 90, 50, ExplTyp.NICHT_AUF_GEGNER);
	}

	protected double maxL()
	{
		return 3 * level;
	}

	public Color farbe()
	{
		return Color.YELLOW;
	}
}