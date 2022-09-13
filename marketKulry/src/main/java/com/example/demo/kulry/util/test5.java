package com.example.demo.kulry.util;

import java.io.IOException;

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

public class test5 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("54.176.113.75", 9200, "http")));

        SearchRequest searchRequest = new SearchRequest("order");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String tabMode="1";
        
        
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
        
//        
        searchRequest2.source(searchSourceBuilder2);

        SearchResponse searchResponse2 = client.search(searchRequest2, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse2.getHits();

        TotalHits totalHits = hits.getTotalHits();
        long numHits = totalHits.value;

        System.out.println(numHits);

        TotalHits.Relation relation = totalHits.relation;
        float maxScore = hits.getMaxScore();
        System.out.println(maxScore);
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            System.out.println(hit);
        }


	}

}
