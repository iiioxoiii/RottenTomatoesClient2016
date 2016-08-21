package org.ecaib.rottentomatoesclient2016;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private int critics_score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getCritics_score() {
        return critics_score;
    }

    public void setCritics_score(int critics_score) {
        this.critics_score = critics_score;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", synopsis='" + synopsis + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", critics_score=" + critics_score +
                '}';
    }
}
