package dev.dit.mauro.gft.api;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.data.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.api.PriceApi;
import org.openapitools.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static dev.dit.mauro.gft.utils.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PriceApiTestIT {

	@Autowired
	private PriceApi priceApiController;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void happyPathIntegrationTest() {
		final PriceEntity priceEntity = buildPriceEntity();
		priceRepository.saveAndFlush(priceEntity);

		final Price price = priceApiController.getPrice(BRAND_ID, PRICE_ID, REQUEST_DATE_STRING).getBody();

		assertEquals(priceEntity.getPriceId(), price.getPriceId().intValue());
		assertEquals(priceEntity.getPrice(), price.getPriceValue().getAmount().doubleValue());
		assertEquals(priceEntity.getCurr(), price.getPriceValue().getCurrency());
		assertTrue(priceEntity.getStartDate().isBefore(LocalDate.parse(price.getActiveDates().getEndDate())));
		assertTrue(priceEntity.getEndDate().isAfter(LocalDate.parse(price.getActiveDates().getStartDate())));
	}

}