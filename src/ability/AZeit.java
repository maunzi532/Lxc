package ability;

public class AZeit
{
	public Ability[] a;

	public AZeit(Class<Ability>[] c)
	{
		a = new Ability[c.length];
		for(int i = 0; i < c.length; i++)
			try{a[i] = c[i].newInstance();
			}catch(Exception e){}
	}

	public void aktivier(int no)
	{
		a[no].aktivier();
	}

	public void cld()
	{
		for(int i = 0; i < a.length; i++)
			a[i].coolDown();
	}
}