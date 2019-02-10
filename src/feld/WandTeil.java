package feld;

public class WandTeil
{
	public final double x;
	public final double y;
	public final double xw;
	public final double yw;

	public WandTeil(double x, double y, double xw, double yw)
	{
		this.x = x;
		this.y = y;
		this.xw = xw;
		this.yw = yw;
	}

	public void damage()
	{
		for(int i = 0; i < Listen.laser.ende; i++)
			if(Math.abs(x - Listen.laser.get(i).x) < Math.abs(xw + Listen.laser.get(i).size/2) &&
					Math.abs(y - Listen.laser.get(i).y) < Math.abs(yw + Listen.laser.get(i).size/2))
				Listen.laser.get(i).leben = -1;
		for(int i = 0; i < Listen.glaser.ende; i++)
			if(Math.abs(x - Listen.glaser.get(i).x) < Math.abs(xw + Listen.glaser.get(i).size/2) &&
					Math.abs(y - Listen.glaser.get(i).y) < Math.abs(yw + Listen.glaser.get(i).size/2))
				Listen.glaser.get(i).leben = -1;
		if(Math.abs(x - FL.ich.x) < Math.abs(xw + FL.ich.size/2) &&
				Math.abs(y - FL.ich.y) < Math.abs(yw + FL.ich.size/2))
			FL.ich.leben--;
	}
}