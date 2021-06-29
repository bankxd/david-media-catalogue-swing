package za.co.sfy.dataAccessTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import za.co.sfy.domain.CD;
import za.co.sfy.domain.DVD;
import za.co.sfy.domain.MediaType;

class CatalogueResourceTest {

	MediaType mtcd;
	MediaType mtdvd;
	List<String> artists;
	List<MediaType> outscd;
	List<MediaType> outsdvd;

	@BeforeEach
	void setUp() throws Exception {
		artists = new ArrayList<>();
		artists.add("artist1");
		artists.add("artist2");
		mtcd = new CD(new CD(), "title", 1, "genre", 1, artists);
		mtdvd = new DVD(new DVD(), "title", 1, "genre", "leadactor", "leadactress");
		outscd = new ArrayList<>();
		outscd.add(mtcd);
		outscd.add(mtcd);
		outsdvd = new ArrayList<>();
		outsdvd.add(mtdvd); 
		outsdvd.add(mtdvd);
	}

//	@Test
//	void test1() {
//		assertEquals(true, new CatalogueResource().create(mtcd));
//	} //PASSED
//
//	@Test
//	void test2() {
//		assertEquals(true, new CatalogueResource().create(mtdvd));
//	} //PASSED
	
//	@Test
//	void test3() {
//		assertEquals(outscd, new CatalogueResource().retrieveAllOfType(mtcd));
//	} //PASSED
//	
//	@Test
//	void test4() {
//		assertEquals(outsdvd, new CatalogueResource().retrieveAllOfType(mtdvd));
//	} //PASSED
	
//	@Test
//	void test5() {
//		assertEquals(true, new CatalogueResource().delete(mtcd));
//	} //PASSED
	
//	@Test
//	void test6() {
//		assertEquals(true, new CatalogueResource().delete(mtdvd));
//	} //PASSED
}
