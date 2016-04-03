package ukma.library.client.forms.tables;

import ukma.library.client.LibraryClient;
import ukma.library.server.LibraryServer;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.Queue;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class QueueTable extends AbstractTableModel{

	private ArrayList<Queue> allQueue;
	private ArrayList<Book> allBooks;
	private String[] columnNames = {"id","Книга","Користувач","Дата"};

	public QueueTable(ArrayList<Queue> allQueue, ArrayList<Book> allBooks) {
		this.allQueue = allQueue;
		this.allBooks = allBooks;
	}
	
	@Override
	public int getRowCount() {
		return allBooks.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch (arg1){
		case 0 :
	        return allQueue.get(arg0).getId();
	    case 1 :
			Book target = null;
			for(Book book: allBooks){
				if(book.getId() == allQueue.get(arg0).getBookId()){
					target = book;
					break;
				}
			}
	        return target == null? target.getTitle() : "";
	    case 2 :
			int userId = allQueue.get(arg0).getUserId();
			String name = "";
			try {
				name = LibraryClient.library.getUserNameById(userId);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return name;
	    case 3 :
	        return allQueue.get(arg0).getDate();
		}
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
				return columnNames[column];
	}

}
