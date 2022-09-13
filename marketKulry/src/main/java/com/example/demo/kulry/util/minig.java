package com.example.demo.kulry.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class minig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub3
	    String userId = "kurlyholic";
	    String tabMode = "1"; // 1 : 전체 , 2 : 푸드컬리 , 3 : 리빙컬리
	    String itemId = "1896730766"; // 1 : 전체 , 2 : 푸드컬리 , 3 : 리빙컬리
	    String flaskIp = "54.176.113.75";
	    String flaskPort = "5000";
	    String itemRcmdApiUrl = "http://"+flaskIp+":"+flaskPort+"/item_rcmd/"+userId+"/"+tabMode;
	    String categoryRcmdApiUrl = "http://"+flaskIp+":"+flaskPort+"/category_rcmd/"+userId+"/"+tabMode;
	    String rebuyRcmdApiUrl = "http://"+flaskIp+":"+flaskPort+"/rebuy_rcmd";
	    String meaningRcmdApiUrl = "http://"+flaskIp+":"+flaskPort+"/meaning_rcmd";
	    String updateViewApiUrl = "http://"+flaskIp+":"+flaskPort+"/update_view/"+userId+"/"+itemId;

	    HttpURLConnection connection = null;
	    
        System.out.println("@@@ meaningRcmdApiUrlTest @@@");

        try {
            URL url = new URL(meaningRcmdApiUrl);

            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String resultValues = in.readLine().replace("[", "").replace("]", "");

            String [] resultValue = resultValues.split(",");

            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("54.176.113.75", 9200, "http")));

            SearchRequest searchRequest = new SearchRequest("item");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termsQuery("_id", resultValue));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits hits = searchResponse.getHits();

            TotalHits totalHits = hits.getTotalHits();
            // the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;

            System.out.println(numHits);

            // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
            System.out.println(maxScore);
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                // do something with the SearchHit
                System.out.println(hit);
            }

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
	}
}
