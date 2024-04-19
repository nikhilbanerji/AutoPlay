package com.autoplay.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SolrSearch {
    public static void search(String query) {
        try (SolrClient solrClient = SolrClientFactory.createSolrClient()) {
            SolrQuery solrQuery = new SolrQuery(query);
//            solrQuery.setQuery("*:*");
            solrQuery.setStart(0);
            solrQuery.setRows(10);

            QueryResponse response = solrClient.query(solrQuery);
            response.getResults().forEach(document -> System.out.println(document));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

