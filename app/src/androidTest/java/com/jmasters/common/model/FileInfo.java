package com.jmasters.common.model;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jmasters.common.utils.DateUtils;
import com.jmasters.common.utils.LogUtils;

public class FileInfo implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private transient File file;
	private String name;
	private String path;
	private boolean isDirectory;
	private long size = -1;
	private boolean isReadable;
	private boolean isWritable;
	private boolean isExecutable;
	private boolean isHidden;
	private long lastModified;

	public FileInfo() {
		super();
	}

	public FileInfo(File file) {
		super();
		this.setFile(file);
		this.setPath(file.getPath());
		this.setDirectory(file.isDirectory());
		if (file.isFile()) {
			this.setSize(file.length());
		}
		this.setName(file.getName());
		this.setReadable(file.canRead());
		this.setWritable(file.canWrite());
		// this.setExecutable(file.canExecute());
		this.setHidden(file.isHidden());
		this.setLastModified(file.lastModified());

	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setFile(boolean isfile) {
		this.isDirectory = !isfile;
	}

	public boolean isFile() {
		return !isDirectory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSize() {
		return size;
	}

	public void setReadable(boolean isReadable) {
		this.isReadable = isReadable;
	}

	public boolean isReadable() {
		return isReadable;
	}

	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}

	public boolean isWritable() {
		return isWritable;
	}

	public void setExecutable(boolean isExecutable) {
		this.isExecutable = isExecutable;
	}

	public boolean isExecutable() {
		return isExecutable;
	}

	@Override
	public String toString() {
		Map<String, String> debugData = new LinkedHashMap<String, String>();
		debugData.put("Name", getName());
		debugData.put("Path", getPath());
		debugData.put("isDirectory", String.valueOf(isDirectory()));
		if (isFile()) {
			debugData.put("size", String.valueOf(getSize()));
		}
		debugData.put("isReadable", String.valueOf(isReadable()));
		debugData.put("isWritable", String.valueOf(isWritable()));
		debugData.put("isExecutable", String.valueOf(isExecutable()));
		debugData.put("isHidden", String.valueOf(isHidden()));
		debugData.put("lastModified", DateUtils.dateToXmlString(new Date(getLastModified())));
		return LogUtils.toLogString(debugData, false);
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}
}
