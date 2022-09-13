package com.example.demo.kulry.util;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class test3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("54.176.113.75", 9200, "http")));

        SearchRequest searchRequest = new SearchRequest("item");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("item_id", "itemId"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();


        IndexRequest indexRequest = new IndexRequest("item")
                .id("userId"+searchHits[0].getSourceAsMap().get("item_id"))
                .source("user_id", "userId",
                        "item_id", searchHits[0].getSourceAsMap().get("item_id"),
                        "item_name", searchHits[0].getSourceAsMap().get("name"),
                        "jjim_dt", new Date(),
                        "category", searchHits[0].getSourceAsMap().get("category"),
                        "reduced_price", searchHits[0].getSourceAsMap().get("reduced_price")
                        );

        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println(indexResponse.getResult().toString());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(e.getMessage());

            }
        });

        client.close();
	}

}
