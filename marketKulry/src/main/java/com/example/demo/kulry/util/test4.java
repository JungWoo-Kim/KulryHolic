package com.example.demo.kulry.util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class test4 {

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


        DeleteByQueryRequest request =
                new DeleteByQueryRequest("jjim");

        request.setQuery(new TermQueryBuilder("item_id", "itemId"));

        ActionListener listener = new ActionListener<BulkByScrollResponse>() {
            @Override
            public void onResponse(BulkByScrollResponse bulkResponse) {
                System.out.println(bulkResponse.getDeleted());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(e.getMessage());

            }
        };

        client.deleteByQueryAsync(request, RequestOptions.DEFAULT, listener);

        client.close();
	}

}
