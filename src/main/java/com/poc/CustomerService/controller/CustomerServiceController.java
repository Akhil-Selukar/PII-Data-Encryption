package com.poc.CustomerService.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.CustomerService.constant.Constants;
import com.poc.CustomerService.dto.CustomerRequest;
import com.poc.CustomerService.dto.Response;
import com.poc.CustomerService.exception.InvalidDataException;
import com.poc.CustomerService.service.CustomerDetailService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/*
 * Controller to map the APIs related to customer
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerServiceController {

	@Autowired
	CustomerDetailService customerService;
	
	@PostMapping("/addCustomer")
	@ApiOperation(value = "addCustomer")
	public Response addCustomer(@RequestBody CustomerRequest request) throws InvalidDataException {
		
		Response response = null;
		System.out.println("==>> name : "+request.toString());
		log.trace("addCustomer for CustId : {} ", request.getCustId());
		
		if(request.getCustId() == null || request.getCustId().isEmpty()) {
			throw new InvalidDataException(Constants.ERROR_CODE_SERVER_ERROR, "Customer Id is Empty..!!");
		}
		
		response = customerService.createCustomer(request);
		
		return response;
	}
	
	
	@GetMapping("/getCustomer")
	@ApiOperation(value = "getCustomer")
	public CustomerRequest getCustomer(@RequestParam String custId) throws InvalidDataException, ParseException {
		
		CustomerRequest customer = null;
		
		log.trace("getCustomer for customer Id : {} ", custId);
		
		if(custId == null || custId.isEmpty()) {
			throw new InvalidDataException(Constants.ERROR_CODE_SERVER_ERROR, "Customer not found..!!");
		}
		customer = customerService.fetchCustomer(custId);
		
		return customer;
	}
	
	@GetMapping("/UpdateMany")
	@ApiOperation(value = "UpdateMany")
	public Response modifyCity(@RequestParam String custId, @RequestParam String city) throws InvalidDataException, ParseException {
		
		log.trace("getCustomer for customer Id : {} ", custId);
		
		if(custId == null || custId.isEmpty()) {
			throw new InvalidDataException(Constants.ERROR_CODE_SERVER_ERROR, "Customer not found..!!");
		}
		
		Response response = customerService.updateCity(custId, city);
		
		return response;
	}
	
}
