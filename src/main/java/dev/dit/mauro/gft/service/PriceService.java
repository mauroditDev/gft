package dev.dit.mauro.gft.service;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.exception.InvalidPriceException;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

	Optional<PriceEntity> getPriceByBrandProductAndDate (Integer brandId, Integer productId, LocalDateTime date) throws InvalidPriceException;

}
