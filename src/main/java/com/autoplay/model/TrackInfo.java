package com.autoplay.model;

import org.apache.solr.common.SolrDocument;

public class TrackInfo {
    private String name;
    private String artist;
    private long durationMs;
    private String formattedDuration;
    private int popularity;
    private boolean explicit;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private String type;
    private String spotifyId;
    private String uri;
    private String trackHref;
    private String analysisUrl;
    private int timeSignature;

    public TrackInfo(SolrDocument doc) {
        this.name = (String) doc.getFieldValue("name");
        this.artist = (String) doc.getFieldValue("artist");
        this.durationMs = (Long) doc.getFieldValue("duration_ms");
        this.formattedDuration = formatDuration();
        this.popularity = (Integer) doc.getFieldValue("popularity");
        this.explicit = (Boolean) doc.getFieldValue("explicit");
        this.danceability = convertToDouble(doc.getFieldValue("danceability"));
        this.energy = convertToDouble(doc.getFieldValue("energy"));
        this.key = (Integer) doc.getFieldValue("key");
        this.loudness = convertToDouble(doc.getFieldValue("loudness"));
        this.mode = (Integer) doc.getFieldValue("mode");
        this.speechiness = convertToDouble(doc.getFieldValue("speechiness"));
        this.acousticness = convertToDouble(doc.getFieldValue("acousticness"));
        this.instrumentalness = convertToDouble(doc.getFieldValue("instrumentalness"));
        this.liveness = convertToDouble(doc.getFieldValue("liveness"));
        this.valence = convertToDouble(doc.getFieldValue("valence"));
        this.tempo = convertToDouble(doc.getFieldValue("tempo"));
        this.type = (String) doc.getFieldValue("type");
        this.spotifyId = (String) doc.getFieldValue("id");
        this.uri = (String) doc.getFieldValue("uri");
        this.trackHref = (String) doc.getFieldValue("track_href");
        this.analysisUrl = (String) doc.getFieldValue("analysis_url");
        this.timeSignature = (Integer) doc.getFieldValue("time_signature");
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public String getFormattedDuration() {
        return formattedDuration;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public double getDanceability() {
        return danceability;
    }

    public double getEnergy() {
        return energy;
    }

    public int getKey() {
        return key;
    }

    public double getLoudness() {
        return loudness;
    }

    public int getMode() {
        return mode;
    }

    public double getSpeechiness() {
        return speechiness;
    }

    public double getAcousticness() {
        return acousticness;
    }

    public double getInstrumentalness() {
        return instrumentalness;
    }

    public double getLiveness() {
        return liveness;
    }

    public double getValence() {
        return valence;
    }

    public double getTempo() {
        return tempo;
    }

    public String getType() {
        return type;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getUri() {
        return uri;
    }

    public String getTrackHref() {
        return trackHref;
    }

    public String getAnalysisUrl() {
        return analysisUrl;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    // Optional override for toString method for debugging purposes
    @Override
    public String toString() {
        return "TrackInfo{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", durationMs=" + durationMs +
                ", popularity=" + popularity +
                ", explicit=" + explicit +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", key=" + key +
                ", loudness=" + loudness +
                ", mode=" + mode +
                ", speechiness=" + speechiness +
                ", acousticness=" + acousticness +
                ", instrumentalness=" + instrumentalness +
                ", liveness=" + liveness +
                ", valence=" + valence +
                ", tempo=" + tempo +
                ", type='" + type + '\'' +
                ", spotifyId='" + spotifyId + '\'' +
                ", uri='" + uri + '\'' +
                ", trackHref='" + trackHref + '\'' +
                ", analysisUrl='" + analysisUrl + '\'' +
                ", timeSignature=" + timeSignature +
                '}';
    }

    private static double convertToDouble(Object value) {
        if (value != null) {
            return ((Number) value).doubleValue();
        }
        return 0.0; // Default value if null
    }

    // Convert duration from milliseconds to "mm:ss" format
    public String formatDuration() {
        int seconds = (int) (durationMs / 1000) % 60 ;
        int minutes = (int) ((durationMs / (1000*60)) % 60);
        return String.format("%d:%02d", minutes, seconds);
    }
}

