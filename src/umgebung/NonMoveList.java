package umgebung;

public class NonMoveList<T>
{
	private static int sizePerX = 100;
	public Object[] arr;
	public int ende;
	public int anz;

	public NonMoveList()
	{
		arr = new Object[sizePerX];
	}

	public T get(int v)
	{
		return (T) arr[v];
	}

	public int add(T v)
	{
		if(ende >= arr.length)
		{
			Object[] arr1 = arr;
			arr = new Object[arr1.length + sizePerX];
			System.arraycopy(arr1, 0, arr, 0, arr1.length);
		}
		arr[ende] = v;
		ende++;
		anz++;
		return ende - 1;
	}

	public void remove(T v)
	{
		for(int i = 0; i < ende; i++)
			if(arr[i] == v)
			{
				arr[i] = null;
				anz--;
				return;
			}
	}

	public void remove(int v)
	{
		arr[v] = null;
		anz--;
	}

	public boolean notcontains(T v)
	{
		for(int i = 0; i < ende; i++)
			if(arr[i] == v)
				return false;
		return true;
	}

	public void sammeln()
	{
		Object[] arr1 = new Object[ende];
		int k = 0;
		for(int i = 0; i < ende; i++)
			if(arr[i] != null)
			{
				arr1[k] = arr[i];
				k++;
			}
		arr = new Object[k + sizePerX];
		System.arraycopy(arr1, 0, arr, 0, k);
		ende = k;
	}
}