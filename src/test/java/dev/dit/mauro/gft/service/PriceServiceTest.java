package dev.dit.mauro.gft.service;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.data.repository.PriceRepository;

import dev.dit.mauro.gft.exception.InvalidPriceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static dev.dit.mauro.gft.utils.TestDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PriceServiceTest {

	private static final int VALID_PID = 999;
	public static final int MID_PRIORITY = 10;
	public static final double VALID_PRICE = 11.11;
	public static final int OUTDATED_PID = 1;
	public static final int HIGH_PRIORITY = 20;
	public static final int LOW_PRIORITY = 5;
	public static final int LOW_PRIO_PID = 2;
	public static final int FUTURE_PID = 3;

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceServiceImpl priceService;

	@Test
	public void givenValidDataWhenFindingPricesThenReturnCorrectPriceFromDb() throws Exception {
		when(priceRepository.findAllByBrandIdAndProductId(BRAND_ID, PRODUCT_ID)).thenReturn(getTestPriceEntityList());

		final PriceEntity priceEntity =
				priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, BASIC_REQUEST_DATE).orElse(null);

		assert priceEntity != null;
		assertEquals(VALID_PID, priceEntity.getPriceId());
		assertEquals(VALID_PRICE, priceEntity.getPrice());
	}

	@Test
	public void givenValidDataOfOutOfDatePriceWhenFindingPricesThenReturnAnEmptyOptional() throws Exception {
		when(priceRepository.findAllByBrandIdAndProductId(BRAND_ID, PRODUCT_ID)).thenReturn(getTestPriceEntityList());
		final Optional<PriceEntity> priceEntity =
				priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, BASIC_REQUEST_DATE.plusYears(5));

		assertTrue(priceEntity.isEmpty());
	}

	@Test
	public void givenValidDataOfNonExistingPriceWhenFindingPricesThenThrowInvalidPriceException() {
		when(priceRepository.findAllByBrandIdAndProductId(BRAND_ID, PRODUCT_ID)).thenReturn(Collections.emptyList());

		final Exception exception =
				assertThrows(InvalidPriceException.class, () -> priceService.getPriceByBrandProductAndDate(BRAND_ID, PRODUCT_ID, BASIC_REQUEST_DATE));

		assertEquals(InvalidPriceException.class, exception.getClass());

	}

	private static List<PriceEntity> getTestPriceEntityList() {
		final PriceEntity validPrice = buildPriceEntity();
		validPrice.setPriceId(VALID_PID);
		validPrice.setPriority(MID_PRIORITY);
		validPrice.setPrice(VALID_PRICE);
		final PriceEntity outdatedPrice = buildPriceEntity(
				OUTDATED_PID,
				BRAND_ID,
				BASIC_START_DATE,
				BASIC_REQUEST_DATE.minusDays(5),
				PRODUCT_ID,
				HIGH_PRIORITY,
				PRICE_AMOUNT,
				PRICE_CURR
		);
		final PriceEntity lowPriorityPrice = buildPriceEntity(
				LOW_PRIO_PID,
				BRAND_ID,
				BASIC_START_DATE,
				BASIC_END_DATE,
				PRODUCT_ID,
				LOW_PRIORITY,
				PRICE_AMOUNT,
				PRICE_CURR
		);
		final PriceEntity futurePrice = buildPriceEntity(
				FUTURE_PID,
				BRAND_ID,
				BASIC_REQUEST_DATE.plusDays(5),
				BASIC_END_DATE,
				PRODUCT_ID,
				HIGH_PRIORITY,
				PRICE_AMOUNT,
				PRICE_CURR
		);		return Arrays.asList(validPrice, outdatedPrice, lowPriorityPrice, futurePrice);
	}

}