package client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.NamingException;


/* головна сторінка
 * логін форма
 * */
public class LibraryClient {

	public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry("localhost", 8888);
		
		// ....
		
	}
	
}
