package de.philipilihp.pub.model;

public enum BeerTrademark {

    LEFFE("Leffe Blonde"),
    GRIMBERGEN("Grimbergen Double"),
    KASTEEL("Kasteel Triple");

    private String trademark;

    BeerTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String trademark() {
        return trademark;
    }

}


