package com.autoplay.model;

public class TrackInfo {
    private String name;
    private String artist;
    private long durationMs;
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

    // Constructor
    public TrackInfo() {
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
}

