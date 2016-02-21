package ukma.library.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.NamingException;

import ukma.library.server.entity.Book;
import ukma.library.server.service.LibraryService;

/* головна сторінка
 * логін форма
 * */
public class LibraryClient {

	public static final String SERVER_NAME = "Server";
	
public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry("localhost", 8888);
		
		final LibraryService library = (LibraryService) registry.lookup(SERVER_NAME);
		
		Book titleBook = library.myTest();
		
		//System.out.println(titleBook.test);
		
	}
	
}
