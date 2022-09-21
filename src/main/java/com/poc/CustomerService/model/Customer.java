package com.poc.CustomerService.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel(description = "Customer Detail Request")
@Document(collection = "CUSTOER_DATA")
public class Customer {
	
	@Id
	private ObjectId id;
	
	@ApiModelProperty(notes = "Customer Id")
	private String custId;
	
	@ApiModelProperty(notes = "Name")
	private String name;
	
	@ApiModelProperty(notes = "PAN")
	private String pan;
	
	@ApiModelProperty(notes = "Account Number")
	private String accNo;
	
	@ApiModelProperty(notes = "DOB")
	private String dob;
	
	@ApiModelProperty(notes = "Phone No")
	private String phoneNo;
	
	@ApiModelProperty(notes = "City")
	private String city;

}
