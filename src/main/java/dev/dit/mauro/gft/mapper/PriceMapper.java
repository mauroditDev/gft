package dev.dit.mauro.gft.mapper;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.exception.InvalidPriceException;
import org.openapitools.model.ActiveDates;
import org.openapitools.model.Price;
import org.openapitools.model.PriceValue;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class PriceMapper {

	public static Price entityToModel(final PriceEntity entity) throws InvalidPriceException {
		final Price model;

		if (entity != null) {
			validatePriceEntity(entity);
			model = new Price();
			model.setPriceId(BigDecimal.valueOf(entity.getPriceId()));
			model.setBrandId(BigDecimal.valueOf(entity.getBrandId()));
			model.setProductId(BigDecimal.valueOf(entity.getProductId()));
			model.setPriceValue(getPriceValue(entity));
			model.setActiveDates(getActiveDates(entity));
		} else {
			model = null;
		}

		return model;
	}

	private static void validatePriceEntity(final PriceEntity entity) throws InvalidPriceException {

		if(
				entity.getPriceId() == null ||
				entity.getBrandId() == null ||
				entity.getProductId() == null ||
				entity.getPrice() == null ||
				entity.getCurr() == null ||
				entity.getStartDate() == null ||
				entity.getEndDate() == null
		) {
			throw new InvalidPriceException();
		}

	}

	private static ActiveDates getActiveDates(final PriceEntity entity) {
		final ActiveDates activeDates = new ActiveDates();
		activeDates.endDate(entity.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		activeDates.startDate(entity.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		return activeDates;
	}

	private static PriceValue getPriceValue(final PriceEntity entity) {
		final PriceValue priceValue = new PriceValue();
		priceValue.setAmount(BigDecimal.valueOf(entity.getPrice()));
		priceValue.setCurrency(entity.getCurr());
		return priceValue;
	}

}
