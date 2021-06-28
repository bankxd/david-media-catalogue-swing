package za.co.sfy.services;

public interface NetworkClientInterface {
	
	// XXX please keep your code consistent, the interface ClientServiceInterface does not have its methods explicitly defined as public
	public void runClient();

	public boolean writeProtocolToServer(String protocol);

	public String readProtocolFromServer();

}
