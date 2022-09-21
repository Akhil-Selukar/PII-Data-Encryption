package com.poc.CustomerService.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.poc.CustomerService.dto.CustomerRequest;
import com.poc.CustomerService.dto.Response;
import com.poc.CustomerService.model.Customer;

@Repository
public class CustomerDao {

	@Autowired
	MongoClient mongoClient;
	
	@Autowired
	MongoTemplate mongoTemplate;

	private String databaseName = "SampleDB";

	private String collectionName = "CUSTOER_DATA";
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

	public Response createCustomer(CustomerRequest request) {

//		MongoDatabase db = mongoClient.getDatabase(databaseName);
//		MongoCollection<Document> coll = db.getCollection(collectionName);
//
//		Document document = new Document();
//		document.append("custId", request.getCustId());
//		document.append("name", request.getName());
//		document.append("pan", request.getPan());
//		document.append("accNo", request.getAccNo());
//		document.append("dob", request.getDob());
//		document.append("phoneNo", request.getPhoneNo());
//		document.append("city", request.getCity());
//
//		InsertOneResult result = coll.insertOne(document);
		
//		Response response = new Response();
//		response.setStatusCode("200");
//		response.setMessage("Saved successfully..!!");
		
		
//		------
		Customer cust = new Customer();
		cust.setCustId(request.getCustId());
		cust.setName(request.getName());
		cust.setPan(request.getPan());
		cust.setAccNo(request.getAccNo());
		cust.setDob(formatter.format(request.getDob()));
		cust.setPhoneNo(request.getPhoneNo());
		cust.setCity(request.getCity());
		
		Response response = new Response();
		Customer custObjectTemp = mongoTemplate.insert(cust);
		
		if(custObjectTemp != null) {
			response.setStatusCode("200");
			response.setMessage("Saved successfully..!!");
		}else {
			response.setStatusCode("500");
			response.setMessage("something went wrong..!!");
		}
//		------

		return response;
	}

	public CustomerRequest findCustomer(String custId) throws ParseException {

//		MongoDatabase db = mongoClient.getDatabase(databaseName);
//		MongoCollection<Document> coll = db.getCollection(collectionName);
//
//		Document incoming_query = new Document("custId", custId);
//
//		AggregateIterable<Document> custObject = coll.aggregate(Arrays.asList(Aggregates.match(incoming_query),
//				Aggregates.project(Projections.fields(Projections.excludeId()))));
//
//		System.out.println("Result ==>> " + custObject.toString());
		
//		Document first = custObject.first();
//		System.out.println("First ==>>" + first);
//		CustomerRequest customer = new CustomerRequest();
//
//		customer.setCustId(first.getString("custId"));
//		customer.setName(first.getString("name"));
//		customer.setPan(first.getString("pan"));
//		customer.setAccNo(first.getString("accNo"));
//		customer.setDob(first.getDate("dob"));
//		customer.setPhoneNo(first.getString("phoneNo"));
//		customer.setCity(first.getString("city"));
		
//		------
		Criteria criteria = Criteria.where("custId").is(custId);
		Aggregation agg = newAggregation(match(criteria));
		Aggregation agg1 = newAggregation(match(criteria), project("custid","name","pan","city"));
		
		AggregationResults<Customer> custObject = mongoTemplate.aggregate(agg, Customer.class, Customer.class);
		System.out.println("Template result = >>>>> "+custObject.toString());
		
		AggregationResults<Customer> custObject1 = mongoTemplate.aggregate(agg1, Customer.class, Customer.class);
		
		System.out.println(custObject1.getMappedResults().get(0).toString());
		
		CustomerRequest customer = new CustomerRequest();
		
		customer.setCustId(custObject.getMappedResults().get(0).getCustId());
		customer.setName(custObject.getMappedResults().get(0).getName());
		customer.setPan(custObject.getMappedResults().get(0).getPan());
		customer.setAccNo(custObject.getMappedResults().get(0).getAccNo());
		customer.setDob(formatter.parse(custObject.getMappedResults().get(0).getDob()));
		customer.setPhoneNo(custObject.getMappedResults().get(0).getPhoneNo());
		customer.setCity(custObject.getMappedResults().get(0).getCity());
//		------
		
		return customer;
	}

	
	public Response updateCity(String custid, String city) throws ParseException {
		
		CustomerRequest customer = findCustomer(custid);
		
		Response response = new Response();
		response.setStatusCode("200");
		
		
//		MongoDatabase db = mongoClient.getDatabase(databaseName);
//		MongoCollection<Document> coll = db.getCollection(collectionName);
//		
//		UpdateResult result = coll.updateMany(Filters.eq("name",customer.getName()), 
//				Updates.combine(Updates.set("city", city)));
//		
//		if(result.getMatchedCount() > 0) {
//			response.setMessage(result.getMatchedCount() + " Records updated successfully..!!");
//		}else {
//			response.setMessage("No records to update..!!");
//		}
		
//		-----------
		
		if(customer != null) {
			Criteria criteria1 = Criteria.where("name").is(customer.getName());
			Aggregation agg1 = newAggregation(match(criteria1));
			
			Update update = new Update();
			update.set("city", city);
			UpdateResult result = mongoTemplate.updateMulti(Query.query(criteria1),update, Customer.class);
		
			if(result.getMatchedCount() > 0) {
				response.setMessage(result.getMatchedCount() + " Records updated successfully..!!");
			}else {
				response.setMessage("No records to update..!!");
			}
		}else {
			response.setMessage("No records to update..!!");
		}
		
		return response;	
	}
	
}
