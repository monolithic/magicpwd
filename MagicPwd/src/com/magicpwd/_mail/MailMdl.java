/**
 * 
 */
package com.magicpwd._mail;

import javax.mail.Folder;
import javax.mail.Message;
import javax.swing.table.AbstractTableModel;

/**
 * @author Administrator
 * 
 */
public class MailMdl extends AbstractTableModel {
	private Message[] messages;

	public MailMdl() {
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return messages != null ? messages.length : 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (messages == null || rowIndex < 0 || rowIndex >= messages.length) {
			return "";
		}
		Message message = messages[rowIndex];
		switch (columnIndex) {
		case 0:
			return null;
		case 1:
			return "";
		case 2:
			return "";
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "状态";
		case 1:
			return "标题";
		case 2:
			return "时间";
		default:
			return "";
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public boolean loadMsg(Folder folder) {
		try {
			if (folder == null) {
				return false;
			}
			if ((folder.getType() & Folder.HOLDS_MESSAGES) == 0) {
				return false;
			}
			if (!folder.isOpen()) {
				folder.open(Folder.READ_WRITE);
			}
			messages = folder.getMessages();
			folder.close(false);
			return true;
		} catch (Exception exp) {
			return false;
		}
	}
}
