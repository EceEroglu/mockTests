package com.isbntools.demo;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockManagementTests {

	ExternalISBNDataService testWebService;
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;

	@BeforeEach
	public void setup() {

		testWebService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager.setDatabaseService(testDatabaseService);
	}

	@Test
	void testCanGetACorrectLocatorCode() {

		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));		
		when(testDatabaseService.lookup(anyString())).thenReturn(null);

	

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);

	}

	@Test

	void databaseIsUsedIfDataIsPresent() {


		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());

	}

	@Test

	void webserviceIsUsedIfDataIsNotPresentInDatabase() {

	

		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));


		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService).lookup("0140177396");

	}

}
