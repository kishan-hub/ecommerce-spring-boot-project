package com.kishan.major.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String name;

	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",referencedColumnName = "category_id")
	protected  Category category;

	protected double price;

	protected double weight;

	protected String description;

	protected String imageName;
}
