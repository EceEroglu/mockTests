package com.isbntools.demo;

public class StockManager {

	private ExternalISBNDataService webservice;
	private ExternalISBNDataService databaseService;

	public void setDatabaseService(ExternalISBNDataService databaseService) {
		this.databaseService = databaseService;
	}

	public void setWebService(ExternalISBNDataService service) {
		this.webservice = service;
	}

	public String getLocatorCode(String isbn) {

		
		Book book = databaseService.lookup(isbn);
		
		if(book == null)  book = webservice.lookup(isbn);

		StringBuilder locator = new StringBuilder();

		locator.append(isbn.substring(isbn.length() - 4));

		locator.append(book.getAuthor().substring(0, 1));

		locator.append(book.getTitle().split(" ").length);

		return locator.toString();

	}

}
