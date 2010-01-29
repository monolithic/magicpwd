/**
 * 
 */
package com.magicpwd._mail;

import javax.mail.Folder;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Amon
 * 
 */
public class NodeMdl extends DefaultMutableTreeNode {
	protected Folder folder = null;

	public NodeMdl(Folder what) {
		folder = what;
	}

	public boolean isLeaf() {
		return false;
	}

	public String toString() {
		return folder.getName();
	}
}
