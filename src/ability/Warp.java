package ability;

import rahmen.*;
import feld.*;

public class Warp extends Ability
{
	public Warp()
	{
		super(10);
	}

	protected void aktivieren()
	{
		FL.ich.x += InV.mausx;
		FL.ich.y += InV.mausy;
		FL.ich.stun = 50;
	}
}