package dev.dit.mauro.gft.utils;

import dev.dit.mauro.gft.data.entity.PriceEntity;

import java.time.LocalDate;

public class TestDataHelper {
	public final static Integer BRAND_ID = 0;
	public final static Integer PRODUCT_ID = 0;
	public final static String REQUEST_DATE_STRING = "2024-09-07 00:00:00";
	public final static Integer PRICE_ID = 0;
	public final static Double PRICE_AMOUNT = 15.25;
	public final static String PRICE_CURR = "EUR";
	public final static LocalDate BASIC_START_DATE = LocalDate.parse("2024-01-01 00:00:00");
	public final static LocalDate BASIC_END_DATE = LocalDate.parse("2024-12-31 23:59:59");
	public final static LocalDate BASIC_REQUEST_DATE = LocalDate.parse(REQUEST_DATE_STRING);


	public static PriceEntity buildPriceEntity() {
		return buildPriceEntity(
				PRICE_ID,
				BRAND_ID,
				BASIC_START_DATE,
				BASIC_END_DATE,
				PRODUCT_ID,
				0,
				PRICE_AMOUNT,
				PRICE_CURR
		);
	}

	public static PriceEntity buildPriceEntity(
			final Integer priceId,
			final Integer brandId,
			final LocalDate startDate,
			final LocalDate endDate,
			final Integer productId,
			final Integer priority,
			final Double price,
			final String curr
	) {
		return new PriceEntity(
			priceId,
			brandId,
			startDate,
			endDate,
			productId,
			priority,
			price,
			curr
		);
	}
}
