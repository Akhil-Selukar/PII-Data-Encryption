package com.poc.CustomerService.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

public class BeforeSaveEventListener extends AbstractMongoEventListener<Object> {
	
//	@Value("#{'${pi.data.list}'.split(',')}")
	private List<String> fieldsToEncrypt;
	
//	----------------
	@Value("#{${pi.data}}")
	Map<String, List<String>> piDataDetails;
	
//	----------------
	
	@Override
	public void onBeforeSave(BeforeSaveEvent<Object> event) {
		
		Document eventObject = event.getDocument();
		System.out.println("====>>>> "+event.getCollectionName());
//		--------------
		List<String> collections = new ArrayList<>(piDataDetails.keySet());
		
		if(collections.contains(event.getCollectionName())) {
			fieldsToEncrypt = new ArrayList<>(piDataDetails.get(event.getCollectionName()));
			for(String key : eventObject.keySet()) {
				if(fieldsToEncrypt.contains(key)) {
					eventObject.put(key, SecurityConfiguration.encrypt(eventObject.get(key).toString()));
				}
			}
		}
//		--------------
//		for(String key : eventObject.keySet()) {
//			if(fieldsToEncrypt.contains(key)) {
//				eventObject.put(key, SecurityConfiguration.encrypt(eventObject.get(key).toString()));
//			}
//		}
		super.onBeforeSave(event);
	}
}
