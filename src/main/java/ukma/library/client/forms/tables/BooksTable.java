package ukma.library.client.forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import ukma.library.server.entity.Book;

public class BooksTable extends AbstractTableModel{

	private ArrayList<Book> allBooks;
	private String[] columnNames = {"id","Назва","Автор","Видавництво","Рік"};
	
	public BooksTable(ArrayList<Book> book) {
		allBooks = book;
	}
	
	@Override
	public int getRowCount() {
		return allBooks.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch (arg1){
		case 0 :
	        return allBooks.get(arg0).getId();
	    case 1 :
	        return allBooks.get(arg0).getTitle();
	    case 2 :
	        return allBooks.get(arg0).getAuthor();
	    case 3 :
	        return allBooks.get(arg0).getEdition();
	    case 4 :
	        return allBooks.get(arg0).getYear();
		}
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
				return columnNames[column];
	}

}
