package ability;

import feld.*;
import v.ver.*;

public class XNWelle extends Ability
{
	public XNWelle()
	{
		super(800);
	}

	protected void aktivieren()
	{
		for(int i = 0; i < 100 * FL.level; i++)
			Listen.alle.add(new R_S(FL.ich.x, FL.ich.y, FL.r.nextDouble()*5-2.5,
					FL.r.nextDouble()*5-2.5, 5 * FL.level, FL.r.nextDouble()*4+3, 30));
	}
}