package com.gafis.xj.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDBConfig {
	
	@Value("${mongo_host}")  
    private String mongoHost;
	@Value("${mongo_port}")  
    private Integer mongoPort;
	@Value("${mongo_username}")  
    private String mongoUsername;
	@Value("${mongo_password}")  
    private String mongoPassword;
	@Value("${mongo_database}")  
    private String mongoDatabase;
    
    
	@Bean
    public MongoTemplate getMongoTemplate(){
        try {
                MongoClientOptions.Builder build = MongoClientOptions.builder();
                build.connectionsPerHost(50);//最大连接数
                build.maxWaitTime(1000*60*2); //一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间
                MongoClientOptions mongoClientOptions =  build.build();
                ServerAddress sa = new ServerAddress(mongoHost,mongoPort);
                List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
                mongoCredentialList.add(MongoCredential.createCredential(mongoUsername,mongoDatabase,mongoPassword.toCharArray()));
                MongoClient mongoClient = new MongoClient(sa,mongoCredentialList,mongoClientOptions);
                MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient,mongoDatabase);
                MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
                return mongoTemplate;
            }catch (Exception e){}
        return null;
    }
	
	public static void main(String[] args) {
		 // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
      
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("xj");
        System.out.println(mongoDatabase.getCodecRegistry());
	}
	
}
