package dev.dit.mauro.gft.api;

import dev.dit.mauro.gft.exception.InvalidPriceException;
import dev.dit.mauro.gft.service.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openapitools.model.Price;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.util.Optional;

import static dev.dit.mauro.gft.utils.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceApiControllerTest {

	@Mock
	private PriceService priceService;

	@InjectMocks
	private PriceApiController priceApiController;

	@Test
	void givenValidDataWhenCallingGetPriceThenShouldReturnAValidPrice() throws InvalidPriceException {
		LocalDate localDate = BASIC_REQUEST_DATE;

		when(priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, localDate)).thenReturn(Optional.of(buildPriceEntity()));
		Price price = priceApiController.getPrice(BRAND_ID, PRODUCT_ID, REQUEST_DATE_STRING).getBody();

		assertEquals(PRICE_ID, price.getPriceId().intValue());
		assertEquals(PRICE_AMOUNT, price.getPriceValue().getAmount().doubleValue());
		assertEquals(PRICE_CURR, price.getPriceValue().getCurrency());
		assertTrue(localDate.isBefore(LocalDate.parse(price.getActiveDates().getEndDate())));
		assertTrue(localDate.isAfter(LocalDate.parse(price.getActiveDates().getStartDate())));

	}

	@Test
	void givenNoPriceFoundWhenCallingGetPriceThenShouldReturn404Error() throws InvalidPriceException {
		when(priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, BASIC_REQUEST_DATE)).thenReturn(Optional.empty());

		final HttpStatusCode httpStatusCode = priceApiController.getPrice(BRAND_ID, PRODUCT_ID, REQUEST_DATE_STRING).getStatusCode();

		assertEquals(HttpStatus.NOT_FOUND, httpStatusCode);
	}

	@Test
	void givenInvalidPriceExceptionWhenCallingGetPriceThenShouldReturnASpecific500Error() throws InvalidPriceException {
		when(priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, BASIC_REQUEST_DATE)).thenThrow(new InvalidPriceException());

		final HttpStatusCode httpStatusCode = priceApiController.getPrice(BRAND_ID, PRODUCT_ID, REQUEST_DATE_STRING).getStatusCode();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, httpStatusCode);
	}



}