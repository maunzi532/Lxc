package bewaffnung.upgrade;

public class EndUpgrade extends Upgrade
{
	int acost;
	int lcost;
	double awert;
	double limit;
	int erh;

	public EndUpgrade(int acost, int lcost, double awert, double limit, int erh)
	{
		this.acost = acost;
		this.lcost = lcost;
		this.awert = awert;
		this.limit = limit;
		this.erh = erh;
	}

	public double wal(int lv)
	{
		return limit - (limit - awert) * erh / (lv + erh);
	}

	public int upgradek(int lv)
	{
		return acost + lv * lcost;
	}
}