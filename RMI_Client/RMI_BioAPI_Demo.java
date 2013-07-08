/* RMI_BioAPI_Demo
 * 1) Check ReadMe file to setup the Server & Client Side.     
 * @author BonSy
 * @CoAuthor GurpreetSingh
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class RMI_BioAPI_Demo {

	private String socket_listener_ip = "localhost";   // socket_listener_ip of socket server at DSP machine
    
	//*** This section is for multi-threading initialization
    private final class RequestThread extends Thread {
        private String option;
        private String AsteriskJava_IP;
        private String Service_UID;
        private String remote_AsteriskSrcFilename;
        private String socket_listener_ip;
        private int socket_port;
        private String local_fileName;

        RequestThread(final String option, final String AsteriskJava_IP, final String Service_UID, final String remote_AsteriskSrcFilename, final String socket_listener_ip, final int socket_port, final String local_fileName)       {
            this.option = option;
            this.AsteriskJava_IP = AsteriskJava_IP;
            this.Service_UID = Service_UID;
            this.remote_AsteriskSrcFilename = remote_AsteriskSrcFilename;
            this.socket_listener_ip = socket_listener_ip;
            this.socket_port = socket_port;
            this.local_fileName = local_fileName;
            this.start();
        }

        public void run() {
            if (option.equals("socket")) {
                    try {
                            initialize_socket(socket_port);
                    } catch (Exception e) {
                            System.out.println("Error on initializing socket server");
                    }
            }
            if (option.equals("AsteriskJava")) {
        		new RMI_BioAPI_AsteriskJava_Client(AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename, socket_listener_ip, socket_port, local_fileName);	        		
            }
            // Runs the above operations simultaneously as multi-threads
        }

    }   //RequestThread

	private PrintWriter pw, pwt;
	private BufferedReader br;	
	private Socket socket;
	private ServerSocket serverSocket = null;
	// private int port = 1688;
	
	public void initialize_socket_stream_buffer() {
        try{
        	socket=serverSocket.accept();
        	System.out.println("connected");
        	br=new  BufferedReader(new InputStreamReader(socket.getInputStream()));
        	pw = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e) {e.printStackTrace();}
	}
	
	public void socket_stream_buffer_close () {
    	pw.close();
    	try {
    		br.close();
    	} catch(IOException e) {e.printStackTrace();}
	}
    
	public void socket_listener () {
		// Still Pending to Complete this
		// This method supposed to Convert data stream coming from RMI_Server
		// to Text File
	}
	
	public void initialize_socket(int port) {
		try {
            serverSocket = new ServerSocket(port);
            String addr=serverSocket.getInetAddress().toString();
            String addrr=serverSocket.getLocalSocketAddress().toString();
            System.out.println("IP address: "+addr);
            System.out.println("socket address(IP address: port): "+addrr);

            initialize_socket_stream_buffer();
            socket_listener();
            serverSocket.close();		
		} catch (IOException e) {
            System.err.println("Could not listen on port: "+port);
            System.exit(-1);
        }            
	}

	public RMI_BioAPI_Demo(String local_fileName, int socket_port, 
			String AsteriskJava_IP, String Service_UID,
			String remote_AsteriskSrcFilename) throws RemoteException {
        new RequestThread("socket", "N/A", "N/A", "N/A", socket_listener_ip, socket_port, "N/A");
        new RequestThread("AsteriskJava", AsteriskJava_IP, Service_UID, remote_AsteriskSrcFilename, socket_listener_ip, socket_port, local_fileName);
	}

	public static void main(String[] args) throws Exception {
	    if (args.length != 5)
	    {
	            System.out.println
	                ("Syntax - java RMI_BioAPI_Demo <local_Filename> <host_port> <Remote_AsteriskJava_IP> <service_UID> <remote_source_Filename>");
	            System.exit(1);
	    }

	    // Create an instance of our service server ...
	    RMI_BioAPI_Demo demo_instance = new RMI_BioAPI_Demo(args[0], Integer.parseInt(args[1]), args[2], args[3], args[4]);
	}  // main

} 
