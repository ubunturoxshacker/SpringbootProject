package com.project.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class MongoDbDao { 

 private static MongoDbDao mgdb=new MongoDbDao();
 private Map vals;
 private static MongoClient mongoClient=new MongoClient("localhost",27017);

 
	
  public Map<String,String> dbreturn(){
	  
	   DB db=mongoClient.getDB("mydb"); 
	   DBCollection collection=db.getCollection("test");
	   System.out.println("Connected now getting collection");
	   
	   BasicDBObject andQuery =new BasicDBObject();
	   List<BasicDBObject> obj=new ArrayList<BasicDBObject>();
	   
	   obj.add(new BasicDBObject("Username","Sankalp Kalia"));
	   obj.add(new BasicDBObject("Password","123"));
	   andQuery.put("$and", obj);
	   
	   @SuppressWarnings("unused")
	   DBCursor cursor=collection.find(andQuery);
	   while(cursor.hasNext()){
		 DBObject resultObj=cursor.next();
	     vals= resultObj.toMap();
		 
	    
	   }
	   return vals;   
  }
  public static MongoDbDao getMongoDbDao(){
	  
	return mgdb;
  }
  
}
