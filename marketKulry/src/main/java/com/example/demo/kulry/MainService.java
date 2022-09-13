package com.example.demo.kulry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class MainService {

	private static String ES_INDEX = "item";
	private static String ES_USER_INDEX = "user";
    // 외부 IP
//    private static String ES_HOST = "54.176.113.75";
    private static String ES_HOST = "172.31.7.97";
    private static int ES_PORT = 9200;
    private static String ES_SCHEME = "http";
    
    public Map<String, Object> getSearch(String cate, String keyword, String type) throws IOException {
		// TODO Auto-generated method stub
		HttpHost host = new HttpHost(ES_HOST, ES_PORT, ES_SCHEME);
		  
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
		            RestClient.builder(
		                    new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));
	    SearchRequest searchRequest = new SearchRequest(ES_INDEX); 
		  
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 

		System.out.println("cate="+cate);
		System.out.println("keyword="+keyword);
		System.out.println("type="+type);
		
		if(type.equals("match")) {
			searchSourceBuilder.query(QueryBuilders.matchQuery(cate, keyword));
		} else {
			searchSourceBuilder.query(QueryBuilders.termQuery(cate, keyword));
		}
	  
		// 크기
//		searchSourceBuilder.size(10);
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHits hits = searchResponse.getHits();
		
		TotalHits totalHits = hits.getTotalHits();
		
		// search total count
		long numHits = totalHits.value;
		
		SearchHit[] searchHits = hits.getHits();
		
		Map<String, Object> retMap = new Gson().fromJson(
			searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
		
//		System.out.println(hitss.get("hits").toString());
		
		restHighLevelClient.close();
		
		return hitss;
	}
    
    public Map<String, Object> userLogin(String id, String password) throws IOException {
		// TODO Auto-generated method stub
		HttpHost host = new HttpHost(ES_HOST, ES_PORT, ES_SCHEME);
		  
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
		            RestClient.builder(
		                    new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));
	    SearchRequest searchRequest = new SearchRequest(ES_USER_INDEX); 
		  
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 

		System.out.println("id="+id);
		System.out.println("password="+password);
		searchSourceBuilder.query(QueryBuilders.multiMatchQuery(id, "user_id", "user_pw"));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHits hits = searchResponse.getHits();
		
		TotalHits totalHits = hits.getTotalHits();
		
		// search total count
		long numHits = totalHits.value;
		
		SearchHit[] searchHits = hits.getHits();
		
		Map<String, Object> retMap = new Gson().fromJson(
			searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		
		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
		
		System.out.println(hitss.get("hits").toString());
		
		restHighLevelClient.close();
		
		return hitss;
	}
    
    public Map<String, Object> termSearch(String index, String cate, String keyword) throws IOException {
		// TODO Auto-generated method stub
		HttpHost host = new HttpHost(ES_HOST, ES_PORT, ES_SCHEME);
		  
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
		            RestClient.builder(
		                    new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));
	    SearchRequest searchRequest = new SearchRequest(index); 
		  
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 

		System.out.println("cate="+cate);
		System.out.println("keyword="+keyword);
		searchSourceBuilder.query(QueryBuilders.termQuery(cate, keyword));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHits hits = searchResponse.getHits();
		
		TotalHits totalHits = hits.getTotalHits();
		
		// search total count
		long numHits = totalHits.value;
		
		SearchHit[] searchHits = hits.getHits();
		
		Map<String, Object> retMap = new Gson().fromJson(
			searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		
		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
		
		System.out.println(hitss.get("hits").toString());
		
		restHighLevelClient.close();
		
		return hitss;
	}
    
    public Map<String, Object> getTabList(String tabUrl) {
    	String apiUrl = tabUrl;

        Map<String, Object> retMap = new HashMap<String, Object>();
        
        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiUrl);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resultValues = in.readLine().replace("[", "").replace("]", "");

            System.out.println(resultValues);
            String [] resultValue = resultValues.split(", ");

//            System.out.println(resultValue[0]);

            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));

            SearchRequest searchRequest = new SearchRequest(ES_INDEX);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termsQuery("_id", resultValue));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits hits = searchResponse.getHits();

            TotalHits totalHits = hits.getTotalHits();
            // the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;

//            System.out.println(numHits);

            // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
//            System.out.println(maxScore);

    		retMap = new Gson().fromJson(
				searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
    		);
    		
            SearchHit[] searchHits = hits.getHits();
//            for (SearchHit hit : searchHits) {
//                // do something with the SearchHit
//                System.out.println(hit);
//            }

            client.close();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
        
        return hitss;
    }
    
    public Map<String, Object> getmeaningList(String tabUrl) {
    	String apiUrl = tabUrl;

        Map<String, Object> retMap = new HashMap<String, Object>();
        
        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiUrl);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String resultValues = in.readLine().replace("[", "").replace("]", "");

            String [] resultValue = resultValues.split(",");

            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));

            SearchRequest searchRequest = new SearchRequest(ES_INDEX);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termsQuery("_id", resultValue));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits hits = searchResponse.getHits();

            TotalHits totalHits = hits.getTotalHits();
            // the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;

//            System.out.println(numHits);

            // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
//            System.out.println(maxScore);

    		retMap = new Gson().fromJson(
				searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
    		);
    		
            SearchHit[] searchHits = hits.getHits();
//            for (SearchHit hit : searchHits) {
//                // do something with the SearchHit
//                System.out.println(hit);
//            }

            client.close();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
        
        return hitss;
    }
    
    public Map<String, Object> getcateList(String tabUrl){
    	String categoryRcmdApiUrl = tabUrl;

	    HttpURLConnection connection = null;
	    
        Map<String, Object> map = new HashMap<String, Object>();
	    
        System.out.println("@@@ categoryRcmdApiUrlTest @@@");

        try {
            URL url = new URL(categoryRcmdApiUrl);

            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            //서버에서 보낸 응답 데이터 수신 받기
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            JSONObject data = new JSONObject(response.toString());

            Iterator<?> keys = data.keys();



            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));


            while( keys.hasNext() ) {
                String key = (String) keys.next();

                String resultValues = data.get(key).toString().replace("[", "").replace("]", "");

                String [] resultValue = resultValues.toString().split(",");


                SearchRequest searchRequest = new SearchRequest(ES_INDEX);
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query(QueryBuilders.termsQuery("_id", resultValue));
                searchRequest.source(searchSourceBuilder);



                SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

                SearchHits hits = searchResponse.getHits();

        		Map<String, Object> retMap = new Gson().fromJson(
        			searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
        		);
        		
                TotalHits totalHits = hits.getTotalHits();
                // the total number of hits, must be interpreted in the context of totalHits.relation
                long numHits = totalHits.value;

//                System.out.println(numHits);

                // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
                TotalHits.Relation relation = totalHits.relation;
                float maxScore = hits.getMaxScore();
//                System.out.println(maxScore);
                SearchHit[] searchHits = hits.getHits();
//                for (SearchHit hit : searchHits) {
//                    // do something with the SearchHit
//                    System.out.println(hit);
//                }

                map.put(key, retMap.get("hits"));
            }
            
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        
        return map;
    }

    public Map<String, Object> realTimeBest(String tabMode) throws IOException{
    	RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ES_HOST, ES_PORT, ES_SCHEME)));

        SearchRequest searchRequest = new SearchRequest("order");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        searchSourceBuilder.query(QueryBuilders.rangeQuery("buy_dt").gte("now-6h/h").lte("now"));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("realtime").field("item_id.keyword"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        Terms realtime=searchResponse.getAggregations().get("realtime");

        String[] keys = new String[10];

        int i = 0;
        for (Terms.Bucket bucket : realtime.getBuckets()) {

            TopHits topHits=bucket.getAggregations().get("realtime");
            System.out.println(bucket.getKey());

            keys[i] = bucket.getKey().toString();
            i++;

        }
        
        SearchRequest searchRequest2 = new SearchRequest("item");
        SearchSourceBuilder searchSourceBuilder2 = new SearchSourceBuilder();

        if(tabMode.equals("1")){
            searchSourceBuilder2.query(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("buy_dt").gte("now-6h/h").lte("now")));
            searchSourceBuilder2.query(QueryBuilders.termsQuery("_id", keys));
        } else if (tabMode.equals("2")) {
            QueryBuilder tabModeQuery = QueryBuilders.termQuery("is_food","비식품");
            QueryBuilder rangeQuery = QueryBuilders.termsQuery("_id", keys);
            searchSourceBuilder2.query(QueryBuilders.boolQuery().must(rangeQuery).mustNot(tabModeQuery));
        } else if (tabMode.equals("3")) {
            QueryBuilder tabModeQuery = QueryBuilders.termQuery("is_food","식품");
            QueryBuilder rangeQuery = QueryBuilders.termsQuery("_id", keys);
            searchSourceBuilder2.query(QueryBuilders.boolQuery().must(rangeQuery).mustNot(tabModeQuery));
        }
        
        searchRequest2.source(searchSourceBuilder2);

        SearchResponse searchResponse2 = client.search(searchRequest2, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse2.getHits();

		Map<String, Object> retMap = new Gson().fromJson(
			searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");
        
        TotalHits totalHits = hits.getTotalHits();
        long numHits = totalHits.value;

//        System.out.println(numHits);

        TotalHits.Relation relation = totalHits.relation;
        float maxScore = hits.getMaxScore();
//        System.out.println(maxScore);
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            System.out.println(hit);
        }

        client.close();
        
        return hitss;

	}
}
