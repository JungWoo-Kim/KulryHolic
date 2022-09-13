package com.example.demo.kulry.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String itemRcmdApiUrl = "http://54.176.113.75:5000/item_rcmd/kurlyholic/1";

        HttpURLConnection connection = null;
        try {
            URL url = new URL(itemRcmdApiUrl);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resultValues = in.readLine().replace("[", "").replace("]", "");

            System.out.println(resultValues);
            String [] resultValue = resultValues.split(", ");

            System.out.println(resultValue[0]);

            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("54.176.113.75", 9200, "http")));


            //1896730766, 15885635
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

    		Map<String, Object> retMap = new Gson().fromJson(
    				searchResponse.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
    		);
    		
            // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();
            System.out.println(maxScore);
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                // do something with the SearchHit
                System.out.println(hit);
            }

    		Map<String, Object> hitss = (Map<String, Object>) retMap.get("hits");

    		System.out.println(hitss.toString());
    		System.out.println();
    		System.out.println();
    		System.out.println(hitss.get("hits").toString());
    		
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
