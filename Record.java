package com.test.CallableAdder;

import java.time.Instant;
import java.util.concurrent.Callable;

import org.apache.kafka.connect.connector.ConnectRecord;

public class Record implements Callable<ConnectRecord> {

	int i =0;
	
	ConnectRecord record;
	
	public Record(ConnectRecord record) {
		
		this.record = record;
		System.out.println("====inside Record constructor====");
	}

	@Override
	public ConnectRecord call() {
		System.out.println("==connectRecord : " + record);
		
		i++;
		System.out.println("In Run method : " + i);
		
		Instant instant = Instant.now();
		Long timeStampMillis = instant.toEpochMilli();
		
		System.out.println("=====Inside CustomRemoveDocumentID::: apply()===record.value()==="+record.value());
		
		return record.newRecord(record.topic(), record.kafkaPartition(), null, timeStampMillis, null, record.value(), record.timestamp());
			
	}

}
