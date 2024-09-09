package dev.dit.mauro.gft.utils;

import dev.dit.mauro.gft.data.entity.PriceEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDataHelper {

	public final static Integer BRAND_ID = 0;
	public final static Integer PRODUCT_ID = 0;
	public final static String REQUEST_DATE_STRING = "2024-09-07T12:23:09";
	public final static Integer PRICE_ID = 0;
	public final static Double PRICE_AMOUNT = 15.25;
	public final static String PRICE_CURR = "EUR";
	public final static LocalDateTime BASIC_START_DATE =
			LocalDateTime.parse("2024-01-01T00:00:01", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	public final static LocalDateTime BASIC_END_DATE =
			LocalDateTime.parse("2024-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	public final static LocalDateTime BASIC_REQUEST_DATE =
			LocalDateTime.parse(REQUEST_DATE_STRING, DateTimeFormatter.ISO_LOCAL_DATE_TIME);


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
			final LocalDateTime startDate,
			final LocalDateTime endDate,
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
