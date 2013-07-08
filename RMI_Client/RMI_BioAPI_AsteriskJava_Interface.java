/* RMI_BioAPI_AsteriskJava_Interface
 * Interface for RMI_Server & Client Side
 * @author bon
 * @CoAuthor GurpreetSingh
 */

import java.rmi.RemoteException;

public interface RMI_BioAPI_AsteriskJava_Interface extends java.rmi.Remote {

	public void RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName) throws RemoteException;
	
}