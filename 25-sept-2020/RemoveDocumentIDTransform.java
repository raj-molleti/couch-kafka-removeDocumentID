package com.couchbase.connect.kafka.deleteOldDocumentID;

import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;


/**
 * Remove Document ID Transform.
 *
 *transforms=removeDocumentID
 *
 * transforms.removeDocumentID.type=com.couchbase.connect.kafka.deleteOldDocumentID.RemoveDocumentIDTransform
 */
public class RemoveDocumentIDTransform<R extends ConnectRecord<R>> implements Transformation<R>   {
	
	private static final ConfigDef CONFIG_DEF = new ConfigDef()
		      .define(null,
		          ConfigDef.Type.BOOLEAN,
		          true,
		          null,
		          ConfigDef.Importance.HIGH,
		          "The Custom Remove is used to remove current Document ID and insert new Document ID ");

	  
	@Override
	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		//apply(record);
	}

	@Override
	public R apply(R record) {	
					
		return  record.newRecord(record.topic(), record.kafkaPartition(), null, record.timestamp(), null, record.value(), record.timestamp());		
	}

	@Override
	public void close() {
		
	}

	@Override
	public ConfigDef config() {
		return CONFIG_DEF;
	}

}
