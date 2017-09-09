package umgebung;

import java.nio.charset.*;
import java.nio.file.*;
import rahmen.*;

import java.io.*;

public class ThaCre
{
	private static final String ORDNER = T.set("Daten");
	public static LFrame fl;
	public static TastenAnalyse ta;
	public static String arg0;
	public static String arg1;

	public static void main(String[] args)
	{
		if(args.length > 0)
			arg0 = args[0];
		if(args.length > 1)
			arg1 = args[1];
		Grafiken.ladeInnen(new String[]{T.set("Logo")});
		fl = new LFrame();
		fl.init();
		ta = new TastenAnalyse();
		try{
			ta.feedMoves(liesDatei("Lxt" + File.separator + "TAKeys"));
		}catch(IOException e){e.printStackTrace();}
		ta.addToFrame(fl.fr);
		fl.fr.setTitle("Lxc");
		ThaLoop.tha(fl, ta);
	}

	public static File theFile()
	{
		try{
			File a = new File(Grafiken.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			if(a.getName().equals(T.set("Run")))
				return new File(ORDNER);
			else
				return a;
		}catch(Exception e)
		{
			return new File(ORDNER);
		}
	}

	public static String liesDatei(String name) throws IOException
	{
		return new String(Files.readAllBytes(new File(name).toPath()), Charset.forName("UTF-8"));
	}
}