package umgebung;

import javax.imageio.stream.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

public class Grafiken
{
	public static final HashMap<String, Image> grafiken = new HashMap<>();
	
	public static void ladeInnen(String[] namen)
	{
		File fl = ThaCre.theFile();
		if(fl.isDirectory())
		try{
			for(String n : namen)
			{
				FileImageInputStream is = new FileImageInputStream(new File(fl.toString() + File.separator + n));
				final ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] bf = new byte[8192];
				int len;
				len = is.read(bf);
				while (len > 0)
				{
					os.write(bf, 0, len);
					len = is.read(bf);
				}
				is.close();
				grafiken.put(n, Toolkit.getDefaultToolkit().createImage(os.toByteArray()));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		else
		try{
			JarFile fj = new JarFile(fl);
			for(String n : namen)
			{
				ZipEntry entry = fj.getEntry(n);
				InputStream is = fj.getInputStream(entry);
				final ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] bf = new byte[8192];
				int len;
				len = is.read(bf);
				while (len > 0)
				{
					os.write(bf, 0, len);
					len = is.read(bf);
				}
				is.close();
				grafiken.put(n, Toolkit.getDefaultToolkit().createImage(os.toByteArray()));
			}
			fj.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}