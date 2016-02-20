package ukma.library.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ukma.library.server.entity.Book;

public interface LibraryService extends Remote{
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://eu-cdbr-azure-west-d.cloudapp.net:3306/acsm_b775c39c99325aa?"
            + "user=b964a4c4dda69d&password=bc9ee508";
	
	public Book myTest() throws RemoteException;
	
}
