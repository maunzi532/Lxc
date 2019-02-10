package v.gegner;

import feld.*;

import java.awt.*;

public class Anne extends Gegner
{
	private static Color cl = Color.RED.brighter();

	private int warte = 1;

	public Anne(double level)
	{
		super(level);
		warte = (int) (100 / Math.sqrt(Math.sqrt(level)));
		maxspeed = 0;
		speed = 0;
	}

	public void denken()
	{
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
		return 50 * level;
	}

	private void spawnLaser()
	{
		Listen.alle.add(new AR(x, y, xdiff(), ydiff(), level));
		warte = (int) (500 / Math.sqrt(Math.sqrt(level)));
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		VFeld.leuchten(gf, 0);
		gf.fillOval((int)(weit / 2 + x - 25), (int)(hoch / 2 + y - 25), 50, 50);
		super.aussehen(gf, weit, hoch);
	}

	public Color farbe()
	{
		return cl;
	}
}