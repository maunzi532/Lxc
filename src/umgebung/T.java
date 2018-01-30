package umgebung;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
import jdk.internal.util.xml.impl.*;

public class T
{
	private static Properties lang = new Properties();
	private static Properties settings = new Properties();
	private static boolean jar;
	private static File jarLocation;
	private static ZipFile zf;

	public static void init()
	{
		URL url = T.class.getResource("T.class");
		jar = url.getProtocol().equals("jar");
		String dir = "";
		if(jar)
		{
			String jarname = url.getPath().substring(url.getPath().indexOf(":") + 1, url.getPath().indexOf("!"));
			jarLocation = new File(jarname).getParentFile();
			try
			{
				zf = new ZipFile(jarname, Charset.forName("UTF-8"));
			}catch(IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		else
			jarLocation = new File(dir);
		try
		{
			if(jar)
				settings.load(new ReaderUTF8(zf.getInputStream(zf.getEntry("Lxt/Set"))));
			else
				settings.load(new FileReader(new File(jarLocation +
						"Lxt" + File.separator + "Set")));
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
			if(jar)
				lang.load(new ReaderUTF8(zf.getInputStream(zf.getEntry("Lxt/Text"))));
			else
				lang.load(new FileReader(new File(jarLocation +
						"Lxt" + File.separator + "Text")));
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

	public static String liesDatei(String name) throws IOException
	{
		if(jar)
		{
			InputStream is = zf.getInputStream(zf.getEntry(name));
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) != -1)
			{
				result.write(buffer, 0, length);
			}
			return result.toString(StandardCharsets.UTF_8.name());
		}
		else
			return new String(Files.readAllBytes(new File(name).toPath()), Charset.forName("UTF-8"));
	}
}