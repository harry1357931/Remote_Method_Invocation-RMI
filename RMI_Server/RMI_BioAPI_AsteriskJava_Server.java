/* RMI_BioAPI_AsteriskJava_Server
 * RMI: Remote Method Invocation
 * Read 'Read Me' file for instructions on setting up the server 
 * and client side 
 * Description:
 *   1) How it works ? Read Comments above main method.  
 *   2) This file goes in RMI_Server folder
 *   3) This class contains code for RMI_Server side.
 *      Also, this class contain methods which can be offered as a
 *      Service to Clients, Clients can call these methods from remote 
 *      locations. The remote locations can be on the same network or 
 *      it can be on the different network anywhere around the world.  
 *   4) Example. of Service Method: RPC_FileRead(...)...
 *      This method read specific file (Specified by Client) on Server Side
 *      and then send that file through a Socket connection with Client
 *   5) This class file is Eclipse friendly as well as Command Line Friendly.
 *      On Eclipse: 
 *         a) Just specify the default run time argument: 2099
 *            here 2099 is the port number, where RMI_Server listens
 *         b) By default, the registry is started at port 1099
 *         c) By default, The instance of RMI_Server is binded to localhost
 *            and listens at 2099, though you have to specify port number 2099   
 *        
 * @author BonSy
 * @Co_author GurpreetSingh
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.rmi.Naming;

public class RMI_BioAPI_AsteriskJava_Server extends UnicastRemoteObject 
implements RMI_BioAPI_AsteriskJava_Interface {
	private static final long serialVersionUID = 1L;

	// Constructor
	public RMI_BioAPI_AsteriskJava_Server(int port) throws RemoteException
    {
        super(port);
    }
	
	/* (non-Javadoc)
	 * @see RMI_BioAPI_AsteriskJava_Interface#RPC_FileRead(java.lang.String, java.lang.String)
	 */
	@Override
	public void RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName)
			throws RemoteException {
		Socket soc;
    	PrintWriter pw=null;
    	File fileName = new File(srcFileName);
		BufferedReader brf=null;
    	try {
    		brf = new BufferedReader(new InputStreamReader(
    				new FileInputStream(fileName.getAbsolutePath())));
    	}catch ( IOException ioe ) {throw new RuntimeException(ioe);}
    	
    	try {
    		if ( brf == null )
    			throw new RuntimeException("Cannot read from closed file "
					                       + fileName.getAbsolutePath() + ".");
    		
     		try {
     			    soc = new Socket(socket_ip, socket_port);
        			pw=new PrintWriter(soc.getOutputStream(), true);
            		pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
            		pw.println(remote_fileName); //Signaling message about remote file name
            		String line = brf.readLine();
            		int counter=0;
            		while ( line != null){
            			System.out.println(line);
            			pw.println(line);
            			counter++;
            			line = brf.readLine();
            		}
            		pw.println("Done"); //Signaling message to terminate the remote socket server
            		brf.close();
            		soc.close();
     		} catch (UnknownHostException e) {
        			System.err.println("Don't know about host.");
                   System.exit(1);
        			e.printStackTrace();
        		} catch (IOException e) {
        			System.err.println("Couldn't get I/O for the connection to server.");
        			System.exit(1);
        			e.printStackTrace();
        		}    		
    	}catch (Exception e) {throw new RuntimeException(e);}
	}  // method RPC_FileRead()

	/* Main Method
	 * What it does:
	 *   1) Checks for argument which will later be used as port number
	 *   2) Install Security Manager
	 *   3) Starts RMI Registry, and this registry listens at port 1099
	 *   4) Create RMI_BioAPI_AsteriskJava_Server instance and bind this instance to 
	 *      Specified IP (default: localhost), and binds to port number args[0]
	 *   5) Now Server gets started and waits for client requests   
	 * @param args args[0] represents the port number where instance of 
	 *        RMI_BioAPI_AsteriskJava_Server is supposed to listen to Client Requests
	 */
	public static void main(String[] args) throws Exception {
	    if (args.length != 1)
	    {
	            System.out.println
	                ("Syntax - java RMI_BioAPI_AsteriskJava_Server_Package/RMI_BioAPI_AsteriskJava_Server_Impl host_port");
	            System.exit(1);
	    }
			
	    URL oUrl = ClassLoader.getSystemResource("grant.policy");
		System.setProperty("java.security.policy", oUrl.toExternalForm());
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager installed.");
		} else
			System.out.println("Security manager already exists.");
		try { // Starts RMI registry at port 1099
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created. Listening on Port 1099");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}
		
		System.out.println("RmiRegistry listens at port 1099 ");
        System.out.println("AsteriskJava BSP Server is ready to listen on " + args[0]);
        
		// Create an instance of our service server
	    RMI_BioAPI_AsteriskJava_Server svr = new RMI_BioAPI_AsteriskJava_Server(Integer.parseInt(args[0]));
	    Naming.bind("RMI_BioAPI_AsteriskJava", svr); 
	    
	    // default binding: localhost, binds svr with name 'RMI_BioAPI_AsteriskJava' to 
	    // localhost
	    // Naming.bind("rmi://localhost:2099/RMI_BioAPI_AsteriskJava", svr);      
        System.out.println("BioAPI AsteriskJava RMI server starts ... ");   
	
	} // main

}  
