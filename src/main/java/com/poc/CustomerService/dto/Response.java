package com.poc.CustomerService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "API Response")
public class Response {
	
	@ApiModelProperty(notes = "Response Status")
	private String statusCode;
	
	@ApiModelProperty(notes = "Message")
	private String message;
	
	@ApiModelProperty(notes = "Error code if any")
	private String errorCode;
}
