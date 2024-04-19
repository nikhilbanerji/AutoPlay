package com.autoplay.service;

import com.autoplay.model.TrackInfo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import java.util.ArrayList;
import java.util.List;

public class SolrSearch {
    public static List<TrackInfo> search(String query) {
        List<TrackInfo> resultsList = new ArrayList<>();
        try (SolrClient solrClient = SolrClientFactory.createSolrClient()) {
            SolrQuery solrQuery = new SolrQuery(query);
            solrQuery.setStart(0);
            // Return first twenty documents
            solrQuery.setRows(20);

            QueryResponse response = solrClient.query(solrQuery);
            for (SolrDocument document : response.getResults()) {
                TrackInfo track = new TrackInfo(document);
                resultsList.add(track);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsList;
    }
}

