package dev.dit.mauro.gft.api;

import dev.dit.mauro.gft.exception.InvalidPriceException;
import dev.dit.mauro.gft.mapper.PriceMapper;
import dev.dit.mauro.gft.service.PriceService;
import lombok.AllArgsConstructor;
import org.openapitools.model.Price;
import org.openapitools.api.PriceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
public class PriceApiController implements PriceApi {

	private PriceService priceService;

	@Override
	public ResponseEntity<Price> getPrice(Integer brandId,Integer productId,String date) {

		final LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		ResponseEntity<Price> response;

		try {
			final Price result = PriceMapper.entityToModel(priceService.getPriceByBrandProductAndDate(brandId,productId, localDateTime).orElse(null));
			if (result == null) {
				response = ResponseEntity.notFound().build();
			} else {
				response = ResponseEntity.ok(result);
			}
		} catch (InvalidPriceException exception) {
			response = ResponseEntity.internalServerError().build();
		}

		return response;
	}

}
