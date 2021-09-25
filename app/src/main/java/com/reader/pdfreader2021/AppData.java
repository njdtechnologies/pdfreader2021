package com.reader.pdfreader2021;

public class AppData {
    private int category;
    private int id;
    private String logo;
    private String name;
    private String pakage;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPakage() {
        return this.pakage;
    }

    public void setPakage(String str) {
        this.pakage = str;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }
}
