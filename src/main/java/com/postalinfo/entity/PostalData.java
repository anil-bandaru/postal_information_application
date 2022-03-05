package com.postalinfo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Simple javaBean domain object representing/mapping postal_data table
 */
@Entity
@Table(name = "postal_data")
@Data
public class PostalData implements Serializable {
	
	private static final long serialVersionUID = 3436476966775519918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected long id;

	@Column(name = "suburbname")
	@NotBlank(message = "Suburb name is mandatory")
	protected String suburbname;
	
	@Column(name = "postalcode")
	@NotBlank(message = "Postal code is mandatory")
	protected String postalcode;
	
}
