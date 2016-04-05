package com.jmasters.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class XMLResourceBundleControl extends ResourceBundle.Control {
    private static String XML = "xml";
    private Class resourceBundleClass = null;
    private File resourcePath = null;

    public XMLResourceBundleControl() {
	super();
    }

    public XMLResourceBundleControl(File resourcePath) {
	super();
	this.resourcePath = resourcePath;
    }

    public XMLResourceBundleControl(String className) throws ClassNotFoundException {
	super();
	this.resourceBundleClass = Class.forName(className);
    }

    public XMLResourceBundleControl(Class resourceBundleClass) {
	super();
	this.resourceBundleClass = resourceBundleClass;
    }

    public List<String> getFormats(String baseName) {
	return Collections.singletonList(XML);
    }

    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
	    throws IllegalAccessException, InstantiationException, IOException {

	if ((baseName == null) || (locale == null) || (format == null) || (loader == null)) {
	    throw new NullPointerException();
	}
	ResourceBundle bundle = null;
	if (!format.equals(XML)) {
	    return null;
	}

	String bundleName = toBundleName(baseName, locale);
	final String resourceName = toResourceName(bundleName, format);

	InputStream stream = null;

	URL url = loader.getResource(resourceName);
	if (url != null) {
	    URLConnection connection = url.openConnection();
	    if (connection == null) {
		return null;
	    }
	    if (reload) {
		connection.setUseCaches(false);
	    }
	    stream = connection.getInputStream();
	} else if (resourceBundleClass != null) {
	    stream = resourceBundleClass.getResourceAsStream(resourceName);
	} else if (resourcePath != null) {
	    if (resourcePath.isDirectory() && resourcePath.list(new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
		    if (resourceName.equalsIgnoreCase(name)) {
			return true;
		    } else {
			return false;
		    }
		}
	    }).length == 1) {
		stream = new FileInputStream(new File(resourcePath.getPath() + File.separatorChar + resourceName));
	    }
	} else {
	    return null;
	}
	if (stream == null) {
	    return null;
	}
	BufferedInputStream bis = new BufferedInputStream(stream);
	bundle = new XMLResourceBundle(bis);
	bis.close();

	return bundle;
    }

    public static void main(String args[]) throws ClassNotFoundException {
	ResourceBundle bundle = ResourceBundle.getBundle("Messages", new XMLResourceBundleControl(new File(
		"C:/jprojects/workspace/jmasters-common/src/main/java/com/jmasters/utils")));
	String string = bundle.getString("ok");
	System.out.println("Key: " + string);
    }
}
