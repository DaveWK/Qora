package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class BuildTime
{
	private static String bufgetBuildTime = "";
	private static String bufgetBuildDate = "";
	private static String bufgetBuildDateTime = "";
	private static Date d = null;
	
	public static String getBuildTime(){
		if(bufgetBuildTime.equals(""))
		{
			SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
			f.setTimeZone(TimeZone.getTimeZone("UTC"));
			bufgetBuildTime = f.format(getClassBuildTime());
		}
		return(bufgetBuildTime);	
	}
	
	public static String getBuildDate(){
		if(bufgetBuildDate.equals(""))
		{
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			f.setTimeZone(TimeZone.getTimeZone("UTC"));
			bufgetBuildDate = f.format(getClassBuildTime());
		}
		return bufgetBuildDate;
	}
	
	public static String getBuildDateTime(){
		if(bufgetBuildDateTime.equals(""))
		{
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			f.setTimeZone(TimeZone.getTimeZone("UTC"));
			bufgetBuildDateTime = f.format(getClassBuildTime()); 
		}
		return bufgetBuildDateTime;
	}
	
	private static Date getClassBuildTime() {
	    if(d == null)
	    {
	    	Class<?> currentClass = new Object() {}.getClass().getEnclosingClass();
	    	URL resource = currentClass.getResource(currentClass.getSimpleName() + ".class");
	    	if (resource != null) {
	    		if (resource.getProtocol().equals("file")) {
	    			try {
	    				d = new Date(new File(resource.toURI()).lastModified());
	    			} catch (URISyntaxException ignored) { }
		        } else if (resource.getProtocol().equals("jar")) {
		            String path = resource.getPath();
		            d = new Date( new File(path.substring(5, path.indexOf("!"))).lastModified() );    
		        } else if (resource.getProtocol().equals("zip")) {
		            String path = resource.getPath();
		            File jarFileOnDisk = new File(path.substring(0, path.indexOf("!")));
		            //long jfodLastModifiedLong = jarFileOnDisk.lastModified ();
		            //Date jfodLasModifiedDate = new Date(jfodLastModifiedLong);
		            try(JarFile jf = new JarFile (jarFileOnDisk)) {
		                ZipEntry ze = jf.getEntry (path.substring(path.indexOf("!") + 2));//Skip the ! and the /
		                long zeTimeLong = ze.getTime ();
		                Date zeTimeDate = new Date(zeTimeLong);
		                d = zeTimeDate;
		            } catch (IOException|RuntimeException ignored) { }
		        }
	    	}
	    }
	    return d;
	}
}