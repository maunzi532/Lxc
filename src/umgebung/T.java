package umgebung;

import java.io.*;
import java.util.*;

public class T
{
	private static Properties lang = new Properties();
	private static Properties settings = new Properties();

	static
	{
		try
		{
			settings.load(new FileReader(new File(System.getProperty("user.dir") +
					File.separator + "Lxt" + File.separator + "Set")));
		}catch(IOException e)
		{
			e.printStackTrace();
			settings.setProperty("Logo", "Logo.png");
			settings.setProperty("Daten", "Werkdaten");
			settings.setProperty("Run", "bin");
			settings.setProperty("Wellen", "wellen");
			settings.setProperty("BlockZeit", "30");
			settings.setProperty("TA", "TAKeys");
		}
		try
		{
			lang.load(new FileReader(new File(System.getProperty("user.dir") +
					File.separator + "Lxt" + File.separator + "Text")));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String tex(String z)
	{
		String a = lang.getProperty(z);
		if(a == null)
			return z;
		return a;
	}

	public static String set(String z)
	{
		String a = settings.getProperty(z);
		if(a == null)
			return z;
		return a;
	}
}