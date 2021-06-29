package za.co.sfy.serviceTest;

// XXX avoid * imports, import specific packages into you code
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import za.co.sfy.services.NetworkClient;
import za.co.sfy.services.NetworkClientInterface;

// XXX This test class does not seem to test anything
class NetworkClientTest {

	NetworkClientInterface nc;
	String test;
	
	@BeforeEach
	void setUp() throws Exception {
		nc = new NetworkClient();
		test = "1#CD#title#2#genre#12#artist1, artist2";
	}

	@Test
	void test() {
		assertEquals(true, nc.writeProtocolToServer(test));
	}

}
