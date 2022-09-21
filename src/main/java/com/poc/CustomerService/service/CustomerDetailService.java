package com.poc.CustomerService.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.CustomerService.dao.CustomerDao;
import com.poc.CustomerService.dto.CustomerRequest;
import com.poc.CustomerService.dto.Response;

@Service
public class CustomerDetailService {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:MM:SS.SSSX");

	@Autowired
	CustomerDao custDao;

	public Response createCustomer(CustomerRequest request) {

		Response response = custDao.createCustomer(request);
		return response;
	}

	public CustomerRequest fetchCustomer(String custId) throws ParseException {

		CustomerRequest customer = custDao.findCustomer(custId);
		return customer;

	}

	public Response updateCity(String custId, String city) throws ParseException {

		return custDao.updateCity(custId, city);
	}

}
