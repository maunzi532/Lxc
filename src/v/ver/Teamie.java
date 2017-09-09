package v.ver;

import feld.*;
import rahmen.*;
import v.*;
import v.gegner.*;

import java.awt.*;

public class Teamie extends V
{
	private int warte = 1;

	public Teamie()
	{
		x = FL.ich.x + InV.mausx;
		y = FL.ich.y + InV.mausy;
		leben = maxL();
	}

	public void denken()
	{
		if(warte > 0)
			warte--;
		if(warte == 0)
			spawnLaser();
	}

	public void xDenken()
	{
		checkCollisions(Gegner.class, Listen.gegner, true);
		checkCollisions(GLaser.class, Listen.glaser, true);
	}

	private void spawnLaser()
	{
		Listen.alle.add(new Rakete(x, y, 0, 0, FL.level * 5, 5, 1000, null));
		warte = (int) (100 / Math.sqrt(Math.sqrt(level)));
	}

	public boolean isEigener()
	{
		return true;
	}

	protected double maxL()
	{
		return 20 * FL.level;
	}

	public Color farbe()
	{
		return Color.BLUE;
	}
}