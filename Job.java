package com.example.trznica_cibalae;

public class Job {
    private String title;
    private String description;
    private String cijena;
    private String kontakt;

    public Job() {}
    public Job(String title, String description, String cijena, String kontakt) {
        this.title = title;
        this.description = description;
        this.cijena = cijena;
        this.kontakt = kontakt;

    }

    public String getKontakt() {
        return this.kontakt;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return title;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
