package com.poc.CustomerService.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;

public class AfterLoadEventListener extends AbstractMongoEventListener<Object> {
	
//	@Value("#{'${pi.data.list}'.split(',')}")
	List<String> fieldsToDecrypt;
	
//	--------------------------
	@Value("#{${pi.data}}")
	Map<String, List<String>> piDataDetails;
		
//	--------------------------
	
	@Override
	public void onAfterLoad(AfterLoadEvent<Object> event) {
		
		Document eventObject = event.getDocument();
	
//		-------------------------
		System.out.println("====>>>> "+event.getCollectionName());
		List<String> collections = new ArrayList<>(piDataDetails.keySet());
		fieldsToDecrypt = new ArrayList<>(piDataDetails.get(event.getCollectionName()));
		System.out.println("Map collections ==>> "+collections);
		System.out.println("Map Values ==>> "+fieldsToDecrypt);
//		System.out.println("Map values ==>> "+piFields);
//		-------------------------
		if(collections.contains(event.getCollectionName())) {
			fieldsToDecrypt = new ArrayList<>(piDataDetails.get(event.getCollectionName()));
			for(String key : eventObject.keySet()) {
				if(fieldsToDecrypt.contains(key)) {
					eventObject.put(key, SecurityConfiguration.decrypt(eventObject.get(key).toString()));
				}
			}
		}
//		========
		
//		for(String key : eventObject.keySet()) {
//			if(fieldsToDecrypt.contains(key)) {
//				eventObject.put(key, SecurityConfiguration.decrypt(eventObject.get(key).toString()));
//			}
//		}
		super.onAfterLoad(event);
	}
}
