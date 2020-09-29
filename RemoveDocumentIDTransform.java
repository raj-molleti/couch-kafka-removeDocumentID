package com.couchbase.connect.kafka.deleteOldDocumentID;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.CallableAdder.*;

/**
 * Remove Document ID Transform.
 *
 *transforms=removeDocumentID
 *
 * transforms.removeDocumentID.type=com.couchbase.connect.kafka.deleteOldDocumentID.RemoveDocumentIDTransform
 */
public class RemoveDocumentIDTransform<R extends ConnectRecord<R>> implements Transformation<R> {
	
	//private static final String OP_CONFIG = "op";
	int i = 0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveDocumentIDTransform.class);

	
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
		System.out.println("====inside apply =======");
		ExecutorService executor = Executors.newFixedThreadPool(10);
        List <Future<Integer>> list = new ArrayList<Future<Integer>>();
        
        Future<ConnectRecord> future = executor.submit(new Record(record));
        ConnectRecord<R> connect =null;
        try {
        	connect =future.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			System.out.println("====catch apply E1======="+e1);
		} catch (ExecutionException e2) {
			// TODO Auto-generated catch block
			System.out.println("====catch apply E2======="+e2);
		}
        return (R) connect;
        
     }

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConfigDef config() {
		return CONFIG_DEF;
	}

}