package com.example.demo.kulry.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsageCostLoggerApplicationTests {
   

//    private static String ES_INDEX = "statistical_korea_online_price_test";
//	private static String ES_INDEX = "item";
//	private static String ES_INDEX = "user";
	private static String ES_INDEX = "jjim";
    // 외부 IP
    private static String ES_HOST = "54.176.113.75";
//    private static String ES_HOST = "1.225.38.51";
//    private static String ES_HOST = "192.168.1.78";
//    private static int ES_PORT = 9287;
    private static int ES_PORT = 9200;
    private static String ES_SCHEME = "http";
   
    private IndexRequest indexRequest;
    
    public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HttpHost host = new HttpHost(ES_HOST, ES_PORT, ES_SCHEME);
		  
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
		            RestClient.builder(
		                    new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));
	    SearchRequest searchRequest = new SearchRequest(ES_INDEX); 
		  
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 

		searchSourceBuilder.query(QueryBuilders.matchQuery("user_id", "kurlyholic"));
//		searchSourceBuilder.query(QueryBuilders.termsQuery("item_id", "82713855564", "28520672497"));
//		searchSourceBuilder.query(QueryBuilders.multiMatchQuery("kurlyholic", "user_id", "user_pw"));
		// 크기
//		searchSourceBuilder.from((2-1)*10);
//		searchSourceBuilder.size(10);
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		System.out.println("list >> "+searchResponse);
		SearchHits hits = searchResponse.getHits();
		
		TotalHits totalHits = hits.getTotalHits();
		
		// search total count
		long numHits = totalHits.value;
		System.out.println("numHits="+numHits);
		
		SearchHit[] searchHits = hits.getHits();
		System.out.println("searchHits="+searchHits.toString());
		Map<String, Object> retMap = new Gson().fromJson(
				searchHits.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		
//		System.out.println(retMap.toString());	
//		System.out.println(retMap.get("hits").toString());
		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");

		System.out.println(hitss.toString());
		System.out.println();
		System.out.println();
		System.out.println(hitss.get("hits").toString());
		
		List<Object> list = (List<Object>) hitss.get("hits");
		Map<String, Object> hitsss = (Map<String, Object>) list.get(0);
		Map<String, Object> source = (Map<String, Object>) hitsss.get("_source");
		
		System.out.println(source.get("item_id").toString());
		
		restHighLevelClient.close();
	}
}