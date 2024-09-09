package dev.dit.mauro.gft.service;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.exception.InvalidPriceException;

import java.time.LocalDate;
import java.util.Optional;

public interface PriceService {

	Optional<PriceEntity> getPriceByBrandProductAndDate (Integer brandId, Integer productId, LocalDate date) throws InvalidPriceException;

}
