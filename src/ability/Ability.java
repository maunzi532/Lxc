package ability;

import java.util.*;

public abstract class Ability
{
	public static final ArrayList<Ability> all = new ArrayList<>();
	static
	{
		all.add(new XNWelle());
		all.add(new Teamie_Spawn());
		all.add(new Warp());
	}

	public int cooldown;
	public int xdown;

	Ability(int cld)
	{
		cooldown = cld;
	}

	public void aktivier()
	{
		if(xdown <= 0)
		{
			aktivieren();
			xdown = cooldown;
		}
	}

	public void coolDown()
	{
		if(xdown > 0)
			xdown--;
	}

	protected abstract void aktivieren();
}