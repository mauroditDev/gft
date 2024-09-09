package dev.dit.mauro.gft.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prices")
public class PriceEntity implements Serializable {

	@Id
	private Integer priceId;

	private Integer brandId;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer productId;

	private Integer priority;

	private Double price;

	private String curr;

}
