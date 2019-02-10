package v.gegner;

import java.awt.*;

public class NexX extends GLaser
{
	public NexX(double x, double y, double xsv, double ysv, double level)
	{
		super(x, y, xsv, ysv, level);
		size = 2;
		speed = Math.sqrt(level) / 2 + 3;
		if(speed > 100)
			speed = 100;
		maxspeed = speed;
		double a = Math.sqrt(xsv * xsv + ysv * ysv) / speed;
		xs = xsv / a;
		ys = ysv / a;
	}

	protected double maxL()
	{
		return level * 6;
	}

	public Color farbe()
	{
		return Color.GREEN;
	}
}