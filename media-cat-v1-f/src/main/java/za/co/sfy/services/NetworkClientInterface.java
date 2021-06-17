package za.co.sfy.services;

public interface NetworkClientInterface {
	
	public void runClient();

	public boolean writeProtocolToServer(String protocol);

	public String readProtocolFromServer();

}
