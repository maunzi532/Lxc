package ability;

import feld.*;
import v.ver.*;

public class Teamie_Spawn extends Ability
{
	public Teamie_Spawn()
	{
		super(500);
	}

	protected void aktivieren()
	{
		Listen.alle.add(new Teamie());
	}
}