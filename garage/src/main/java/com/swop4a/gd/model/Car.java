package com.swop4a.gd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "garage")
@ToString(exclude = "garage")
public class Car {

	@Id
	private Long id;
	private String make;
	private String model;
	private String color;
	private boolean isRunning;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "garage_id", nullable = false)
	private Garage garage;
}
