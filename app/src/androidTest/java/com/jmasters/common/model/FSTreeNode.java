package com.jmasters.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jmasters.common.utils.LogUtils;
import com.jmasters.common.utils.StringUtils;

public class FSTreeNode extends TreeNode<FileInfo> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long size;

    private String note;

    public FSTreeNode() {
	super();
    }

    public FSTreeNode(FileInfo value, FSTreeNode parent) {
	super(value, parent);
    }

    public void setNote(String note) {
	this.note = note;
    }

    public String getNote() {
	return note;
    }

    @Override
    public String toString() {
	Map<String, String> debugData = new LinkedHashMap<String, String>();
	debugData.put("FileInfo", getValue().toString());
	if (!StringUtils.isNullOrEmpty(getNote())) {
	    debugData.put("Note", getNote());
	}
	return LogUtils.toLogString(debugData, false);
    }

    public void setSize(long size) {
	this.size = size;
    }

    public long getSize() {
	return size;
    }

}
