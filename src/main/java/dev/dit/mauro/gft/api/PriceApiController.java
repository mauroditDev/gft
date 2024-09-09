package dev.dit.mauro.gft.api;

import org.openapitools.model.Price;
import org.openapitools.api.PriceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PriceApiController implements PriceApi {

	@Override
	public ResponseEntity<Price> getPrice(Integer brandId,Integer productId,String date) {
		return null;
	}

}
