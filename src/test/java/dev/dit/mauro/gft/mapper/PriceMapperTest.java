package dev.dit.mauro.gft.mapper;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.utils.TestDataHelper;
import org.junit.jupiter.api.Test;
import org.openapitools.model.Price;

import static org.junit.jupiter.api.Assertions.*;

class PriceMapperTest {

	@Test
	public void givenValidPriceEntityWhenEntityToModelIsCalledThenAnEquivalentModelShouldBeReturned() {
		final PriceEntity originalPriceEntity = TestDataHelper.buildPriceEntity();
		final Price convertedPrice = PriceMapper.entityToModel(originalPriceEntity);

		assertEquals(originalPriceEntity.getPriceId(), convertedPrice.getPriceId().intValue());
		assertEquals(originalPriceEntity.getProductId(), convertedPrice.getProductId().intValue());
		assertEquals(originalPriceEntity.getBrandId(), convertedPrice.getBrandId().intValue());
		assertEquals(originalPriceEntity.getEndDate().toString(), convertedPrice.getActiveDates().getEndDate());
		assertEquals(originalPriceEntity.getStartDate().toString(), convertedPrice.getActiveDates().getStartDate());
		assertEquals(originalPriceEntity.getCurr(), convertedPrice.getPriceValue().getCurrency());
		assertEquals(originalPriceEntity.getPrice(), convertedPrice.getPriceValue().getAmount().doubleValue());

	}

	@Test
	public void givenNullPriceEntityWhenEntityToModelIsCalledThenNullValueShouldBeReturned() {
		final Price convertedPrice = PriceMapper.entityToModel(null);

		assertNull(convertedPrice);
	}

	@Test
	public void givenInvalidPriceEntityWhenEntityToModelIsCalledThenExceptionShouldBeThrown() {
		final PriceEntity originalPriceEntity = TestDataHelper.buildPriceEntity();
		originalPriceEntity.setPriceId(null);

		final Exception exception = assertThrows(Exception.class, ()->PriceMapper.entityToModel(originalPriceEntity));
		assertTrue(exception.getMessage().contains("invalid PriceEntity"));
	}

}