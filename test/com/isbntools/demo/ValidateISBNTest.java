package com.isbntools.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkAValidISBN() {

		// write a method
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue(result, "first value");

		result = validator.checkISBN("0140177396");
		assertTrue(result, "seconda value");

	}

	@Test
	void checkAnInvalidShortDigitISBN() {

		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);

	}

	@Test
	void checkAnInvalidLongDigitISBN() {

		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853267336");
		assertFalse(result);

	}

	@Test

	void nineDigitISBNAreNotAllowed() {

		ValidateISBN validator = new ValidateISBN();

		assertThrows(NumberFormatException.class, () -> {
			validator.checkISBN("123456789");
		});

	}

	@Test

	void nonNumericISBNsAreNotAllowed() {

		ValidateISBN validator = new ValidateISBN();

		assertThrows(NumberFormatException.class, () -> {
			validator.checkISBN("helloworld");
		});

	}

	@Test

	void TenDigitISBNNumbersEndingInAnXAreValid() {

		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}

	@Test

	void checkValid13DigitISBN() {

		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853260087");
		assertTrue(result, "first value");

		result = validator.checkISBN("9781853267338");
		assertTrue(result, "seconda value");
	}

}
