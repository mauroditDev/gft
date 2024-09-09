package dev.dit.mauro.gft.data.repository;

import dev.dit.mauro.gft.data.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

	List<PriceEntity> findAllByBrandIdAndProductId (final Integer brandId, final Integer productId);

}
