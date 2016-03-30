package com.jmasters.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends Serializable> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private TreeNode<T> parent = null;
    private List<TreeNode<T>> children = new ArrayList<TreeNode<T>>();
    private T value = null;

    public TreeNode() {
    }

    public TreeNode(T value, TreeNode<T> parent) {
	this.value = value;
	this.parent = parent;
    }

    public void setParent(TreeNode<T> parent) {
	this.parent = parent;
    }

    public TreeNode<T> getParent() {
	return parent;
    }

    public void setValue(T value) {
	this.value = value;
    }

    public T getValue() {
	return value;
    }

    public void setChildren(List<TreeNode<T>> children) {
	this.children = children;
    }

    public List<TreeNode<T>> getChildren() {
	return children;
    }

}
