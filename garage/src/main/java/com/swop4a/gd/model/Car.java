package com.swop4a.gd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private Long id;
	private String make;
	private String model;
	private String color;
	private Date creationDay;
	private Long mileage;
	private boolean isRunning;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "garage_id", nullable = false)
	private Garage garage;
}
