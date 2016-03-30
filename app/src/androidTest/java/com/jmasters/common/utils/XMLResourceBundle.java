package com.jmasters.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class XMLResourceBundle extends ResourceBundle {
    private Properties props;

    XMLResourceBundle(InputStream stream) throws IOException {
	props = new Properties();
	props.loadFromXML(stream);
    }

    protected Object handleGetObject(String key) {
	return props.getProperty(key);
    }

    public Enumeration<String> getKeys() {
	Set<String> handleKeys = props.stringPropertyNames();
	return Collections.enumeration(handleKeys);
    }

    /**
     * Retrieve the localized string for the passed key and format it with the
     * passed arguments.
     * 
     * @param key
     *            Key to retrieve string for.
     * @param args
     *            Any string arguments that should be used as values to
     *            parameters found in the localized string.
     * 
     * @return Localized string or error message.
     * 
     * @throws IllegalArgumentException
     *             Thrown if <TT>null</TT> <TT>key</TT> passed.
     */
    public String getString(String key, String[] args) {
	return getString(key, (Object[]) args);
    }

    /**
     * Retrieve the localized string for the passed key and format it with the
     * passed arguments.
     * 
     * @param key
     *            Key to retrieve string for.
     * @param args
     *            Any string arguments that should be used as values to
     *            parameters found in the localized string.
     * 
     * @return Localized string or error message.
     * 
     * @throws IllegalArgumentException
     *             Thrown if <TT>null</TT> <TT>key</TT> passed.
     */
    public String getString(String key, Object[] args) {
	if (key == null) {
	    throw new IllegalArgumentException("key == null");
	}
	if (args == null) {
	    args = new Object[0];
	}
	final String str = getString(key);
	try {
	    return MessageFormat.format(str, args);
	} catch (IllegalArgumentException ex) {
	    String msg = "Error formatting i18 string. Key is '" + key + "'";
	    return msg + ": " + ex.toString();
	}
    }

}
