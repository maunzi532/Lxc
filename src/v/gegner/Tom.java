package v.gegner;

import feld.*;

import java.awt.*;

public class Tom extends Gegner
{
	public Tom(double level)
	{
		super(level);
		speed = Math.sqrt(level) / 10;
		maxspeed = Math.sqrt(level);
		if(maxspeed < 0.1)
			maxspeed = 0.1;
		if(maxspeed > 100)
			maxspeed = 100;
	}

	public Tom(double level, double spx, double spy, double spr)
	{
		super(level, spx, spy, spr);
		speed = Math.sqrt(level) / 10;
		maxspeed = Math.sqrt(level);
		if(maxspeed < 0.1)
			maxspeed = 0.1;
		if(maxspeed > 100)
			maxspeed = 100;
	}

	public void denken()
	{
		xs += (FL.r.nextDouble() * 2 - 1) * speed;
		ys += (FL.r.nextDouble() * 2 - 1) * speed;
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 10 * level;
	}

	public Color farbe()
	{
		return Color.WHITE;
	}
}