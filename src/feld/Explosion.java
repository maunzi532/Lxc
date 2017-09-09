package feld;

import v.gegner.*;

class Explosion
{
	public final double x;
	public final double y;
	private final double power;
	public final double radius;
	public int dauer;
	public final int insgDauer;
	public int pausedX;
	public int pausedY;
	public ExplTyp typ;

	public Explosion(double x, double y, double power,
			double radius, int dauer, ExplTyp typ)
	{
		this.x = x;
		this.y = y;
		this.power = power;
		this.radius = radius;
		this.dauer = dauer;
		insgDauer = dauer;
		this.typ = typ;
	}

	public boolean damage()
	{
		double xrad = radius * ((insgDauer - dauer) / (double) insgDauer);
		switch(typ)
		{
			case NICHT_AUF_GEGNER:
				for(int i = 0; i < Listen.alle.ende; i++)
					if(Listen.alle.get(i).isEigener() || Listen.alle.get(i) instanceof GLaser)
						if(Listen.nahe(Listen.alle.get(i), x, y) < xrad + Listen.alle.get(i).size)
							Listen.alle.get(i).leben -= power;
				break;
			case NUR_AUF_FEINDE:
				for(int i = 0; i < Listen.alle.ende; i++)
					if(!Listen.alle.get(i).isEigener())
						if(Listen.nahe(Listen.alle.get(i), x, y) < xrad + Listen.alle.get(i).size)
							Listen.alle.get(i).leben -= power;
				break;
		}
		dauer--;
		return dauer <= 0;
	}
}