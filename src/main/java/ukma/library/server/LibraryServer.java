package ukma.library.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ukma.library.server.service.LibraryService;
import ukma.library.server.service.LibraryServiceImpl;

public class LibraryServer {

	private static final String SERVER_NAME = "Server";
	 
    private String  port;
    public LibraryServer(String port)    
    {
        this.port = port;
    }
	
	public void runServer() throws IllegalArgumentException, NotBoundException, RemoteException 
    {
		LibraryService libraryImpl = new LibraryServiceImpl();
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(port, 10));
        registry.rebind(SERVER_NAME, libraryImpl);
    }
	
    public static void main(String[] args) throws RemoteException,IllegalArgumentException, NotBoundException {
		
    	LibraryServer server = new LibraryServer("8888");
        server.runServer();
        System.out.println("Server started on port 8888");
	
	}
	
}
