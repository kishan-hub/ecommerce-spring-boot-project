package com.kishan.major.dto;

import lombok.Data;

@Data
public class ProductDTO {
	protected Long id;
	protected String name;
	protected  int categoryId;
	protected double price;
	protected double weight;
	protected String description;
	protected String imageName;
	
	public ProductDTO() {}
	
}
