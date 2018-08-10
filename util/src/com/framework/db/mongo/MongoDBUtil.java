package com.framework.db.mongo;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * *
 * 类名称：		MongoDBUtil.java 
 * 类描述：   		mongodb数据库的连接
 * 创建人：		
 * 创建时间：		2015-9-9下午3:03:47 
 * 修改人：		liuxing
 * 修改时间：		2015-9-9下午3:03:47 
 * 修改备注：   		参考示例：http://www.cnblogs.com/phinecos/archive/2012/12/27/2836020.html
 * @version
 */
public class MongoDBUtil {
	//private static Logger logger = Logger.getLogger(MongoDBUtil.class);
	private Mongo mongo = null;
	private DB mongoDb;

	private static MongoDBUtil instance;

	private MongoDBUtil() {
		super();
		initData();
	}
	
	public static synchronized MongoDBUtil getInstance() {
		if (instance == null) {
			instance = new MongoDBUtil();
		}
		return instance;
	}
	
	private void initData() {
		try {
			mongo = new Mongo( "192.168.1.111" , 27017 );
			mongoDb = mongo.getDB( "test" );
		} catch (UnknownHostException e) {
			//logger.error("initData() of MongoDBUtil error: ", e);
		} catch (MongoException e) {
			//logger.error("initData() of MongoDBUtil error: ", e);
		}
	}

	/**
	 * 获得MongoDb 数据库实例
	 * @return
	 */
	public DB getMongoDb() {
		return mongoDb;
	}
	
	public static void main(String[] args) {
		//连接上数据库----然后连接上其中的某一个表
		DBCollection mongoDbCollection = new MongoDBUtil().getMongoDb().getCollection( "person" );
		query(mongoDbCollection , 26 ) ;
		insert(mongoDbCollection) ;		//新增完成后查询一次
		query(mongoDbCollection , 26) ;
		//update(mongoDbCollection) ;		//修改完成后查询一次
		query(mongoDbCollection ,27 ) ;
		//remove(mongoDbCollection) ;		//删除完成后查询一次
		query(mongoDbCollection ,27 ) ;
	}
	
	/***
	 * 查询操作
	 * @param mongoDbCollection
	 */
	public static void query(DBCollection mongoDbCollection ,int age ) {
		// 查询条件的参数设定
		DBObject dbObjectQueryParam = new BasicDBObject();
		// dbObjectQueryParam.put("name", "test");
		dbObjectQueryParam.put("age", age );
		// 执行查询操作
		DBCursor dC = mongoDbCollection.find(dbObjectQueryParam);
		if (dC != null) {
			while (dC.hasNext()) {
				DBObject dbObject = dC.next();
				Object objectId = (ObjectId) dbObject.get("_id");
				System.out.println("查出数据的_id：" + objectId + "，整条数据的json:"
						+ dbObject.toString());
			}
		}
		// 得到查询的信息
	}
	
	/***
	 * 查询操作
	 * @param mongoDbCollection
	 */
	public static void insert(DBCollection mongoDbCollection) {
		long t1 = System.currentTimeMillis() ;
		// 查询条件的参数设定
		for(int i=0;i<100000;i++){
			DBObject dbObjectQueryParam = new BasicDBObject();
			dbObjectQueryParam.put("name", "liuxing");
			dbObjectQueryParam.put("age", i);
		// 执行查询操作
			WriteResult writeResult = mongoDbCollection.insert( dbObjectQueryParam ) ;if (null != writeResult) {
	            if (writeResult.getN() < 1) {
	            	System.out.println( "mongodb 插入数据成功." );
	            }
	        }
		}
		System.out.println( System.currentTimeMillis() - t1 );
		// 得到查询的信息
	}
	
	/***
	 * 查询操作
	 * @param mongoDbCollection
	 */
	public static void update(DBCollection mongoDbCollection) {
		// 查询条件的参数设定
		DBObject dbObjectQueryParam = new BasicDBObject();
		dbObjectQueryParam.put("name", "liuxing");
		dbObjectQueryParam.put("age", 26);
		// 执行查询操作
		DBCursor dC = mongoDbCollection.find(dbObjectQueryParam);
		if (dC != null) {
			while (dC.hasNext()) {
				DBObject dbObject = dC.next();
				dbObjectQueryParam.put("age", 27);
				WriteResult writeResult = mongoDbCollection.update(dbObject, dbObjectQueryParam  ) ;
				if (null != writeResult) {
		            if (writeResult.getN() > 0) {
		            	System.out.println( "mongodb 修改数据成功." );
		            }
		        }
            }
        }
		// 得到查询的信息
	}

	/***
	 * 查询操作
	 * @param mongoDbCollection
	 */
	public static void remove(DBCollection mongoDbCollection) {
		// 查询条件的参数设定
		DBObject dbObjectQueryParam = new BasicDBObject();
		dbObjectQueryParam.put("name", "liuxing");
		dbObjectQueryParam.put("age", 27);
		// 执行查询操作
		WriteResult writeResult = mongoDbCollection.remove( dbObjectQueryParam ) ;
		if (null != writeResult) {
            if (writeResult.getN() > 0) {
            	System.out.println( "mongodb 删除数据成功." );
            }
        }
		// 得到查询的信息
	}

}
