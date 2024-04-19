package com.autoplay.controller;

import com.autoplay.service.SolrSearch;
import com.google.gson.Gson;

import static spark.Spark.*;
import com.autoplay.model.*;

import java.util.List;

public class AutoPlayController {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();

        redirect.post("/autoplay", "/");

        post("/search", (request, response) -> {
            // Extract the JSON string from the request body
            String body = request.body();
            // Deserialize the JSON to a Java object
            Query query = gson.fromJson(body, Query.class);
            String description = query.getDescription();
            boolean explicit = query.isExplicit();
            boolean popular = query.isPopular();

            // Print out for debugging
            System.out.println("Description: " + description);
            System.out.println("Explicit: " + explicit);
            System.out.println("Popular: " + popular);

            // Query Solr using search service
            List<TrackInfo> playlist = SolrSearch.search("explicit:false");

            // Print playlist for debugging
            for (TrackInfo track : playlist) {
                System.out.println(track);
            }

            // Respond with some JSON
            response.type("application/json");
            return gson.toJson(playlist);
        });
    }

    /**
     * Get the Heroku assigned port number.
     * @return  Heroku assigned port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
