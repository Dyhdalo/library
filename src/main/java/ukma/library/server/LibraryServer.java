package ukma.library.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LibraryServer {

	private static final String SERVER_NAME = "Server";
	 
    private String  port;
    public LibraryServer(String port)    
    {
        this.port = port;
    }
	
	public void runServer() throws IllegalArgumentException, NotBoundException, RemoteException 
    {
    	/*ManageBook helloImpl = new ManageBookImpl();
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(port, 10));
        registry.rebind(SERVER_NAME, helloImpl);*/
    }
	
    public static void main(String[] args) throws RemoteException,IllegalArgumentException, NotBoundException {
		
    	LibraryServer server = new LibraryServer("8888");
        server.runServer();
        System.out.println("Server started on port 8888");
	
	}
	
}
