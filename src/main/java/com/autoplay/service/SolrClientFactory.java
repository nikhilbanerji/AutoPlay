package com.autoplay.service;

import org.apache.solr.client.solrj.impl.CloudSolrClient;

public class SolrClientFactory {
    public static final String zkHost = "127.0.0.1:9983";

    public static CloudSolrClient createSolrClient() {
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder();
        builder.withZkHost(zkHost);
        CloudSolrClient solrClient = builder.build();
        solrClient.setDefaultCollection("songs");
        solrClient.connect();
        return solrClient;
    }
}
