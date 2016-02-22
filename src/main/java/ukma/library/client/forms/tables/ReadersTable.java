package ukma.library.client.forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import ukma.library.server.entity.Book;
import ukma.library.server.entity.User;

public class ReadersTable extends AbstractTableModel{

	private ArrayList<User> allReaders;
	private String[] columnNames = {"id","Ім'я","Телефон","Пароль","Роль"};
	
	public ReadersTable(ArrayList<User> users) {
		allReaders = users;
	}
	
	@Override
	public int getRowCount() {
		return allReaders.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch (arg1){
		case 0 :
	        return allReaders.get(arg0).getId();
	    case 1 :
	        return allReaders.get(arg0).getName();
	    case 2 :
	        return allReaders.get(arg0).getPhone();
	    case 3 :
	        return allReaders.get(arg0).getPassword();
	    case 4 :
	        return allReaders.get(arg0).getRole();
		}
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
				return columnNames[column];
	}
	
}
