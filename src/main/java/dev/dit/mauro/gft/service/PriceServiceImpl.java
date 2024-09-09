package dev.dit.mauro.gft.service;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import dev.dit.mauro.gft.data.repository.PriceRepository;
import dev.dit.mauro.gft.exception.InvalidPriceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

	private PriceRepository priceRepository;

	@Override
	public Optional<PriceEntity> getPriceByBrandProductAndDate(final Integer brandId, final Integer productId, final LocalDateTime date) throws InvalidPriceException {

		final List<PriceEntity> pricesForProduct = priceRepository.findAllByBrandIdAndProductId(brandId, productId);

		if(pricesForProduct.isEmpty()){
			throw new InvalidPriceException();
		}

		return pricesForProduct.stream().filter(priceEntity -> filterByDate(date, priceEntity)).max(Comparator.comparing(PriceEntity::getPriority));
	}

	private static boolean filterByDate(final LocalDateTime date, final PriceEntity priceEntity) {
		return priceEntity.getEndDate().isAfter(date) && priceEntity.getStartDate().isBefore(date);
	}
}
