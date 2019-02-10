package v.gegner;

import feld.*;

import java.awt.*;

public class Nex extends Gegner
{
	private static Color cl = Color.GREEN.darker();

	private int warte = 1;

	public Nex(double level)
	{
		super(level);
		maxspeed = level;
		if(maxspeed < 0.1)
			maxspeed = 0.1;
		if(maxspeed > 100)
			maxspeed = 100;
	}

	public void denken()
	{
		if(warte > 0)
			warte--;
		xs += (FL.r.nextDouble() * 2 - 1) * speed;
		ys += (FL.r.nextDouble() * 2 - 1) * speed;
		if(warte == 0)
			spawnLaser();
	}

	protected double maxL()
	{
		return 4 * level;
	}

	private void spawnLaser()
	{
		Listen.alle.add(new NexX(x, y, xdiff(), ydiff(), level));
		warte = (int) (150 / Math.sqrt(level));
	}

	public boolean isEigener()
	{
		return false;
	}

	public Color farbe()
	{
		return cl;
	}
}