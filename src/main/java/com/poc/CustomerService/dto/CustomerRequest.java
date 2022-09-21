/**
 * 
 */
package com.poc.CustomerService.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author akhil
 * dto to receive customer request
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel(description = "Customer detail request")
public class CustomerRequest {

	@ApiModelProperty(notes = "Customer Id")
	private String custId;
	
	@ApiModelProperty(notes = "Name")
	private String name;
	
	@ApiModelProperty(notes = "PAN")
	private String pan;
	
	@ApiModelProperty(notes = "Account number")
	private String accNo;
	
	@ApiModelProperty(notes = "DOB")
	private Date dob;
	
	@ApiModelProperty(notes = "Phone no")
	private String phoneNo;
	
	@ApiModelProperty(notes = "City")
	private String city;
}
