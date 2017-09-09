package bewaffnung.upgrade;

public class QLUpgrade extends Upgrade
{
	int acost;
	int qcost;
	double awert;
	double qwert;

	public QLUpgrade(int acost, int qcost, double awert, double qwert)
	{
		this.acost = acost;
		this.qcost = qcost;
		this.awert = awert;
		this.qwert = qwert;
	}

	public double wal(int lv)
	{
		return awert + lv * qwert;
	}

	public int upgradek(int lv)
	{
		return acost + lv * lv * qcost;
	}
}