package com.swop4a.gd.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "garage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Garage {

	@Id
	@GeneratedValue
	private Long id;
	private int capacity;
	private String title;

	@OneToMany(mappedBy = "garage", fetch = FetchType.EAGER)
	private List<Car> cars;
}
