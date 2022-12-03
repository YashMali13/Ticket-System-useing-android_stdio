package com.example.project_tr;

public class model
{
    String name,source, destination, date, contact;

    public model( String name, String source, String destination, String date, String contact )
    {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getEmail() {
        return contact;
    }
    public void setEmail(String contact) {
        this.contact = contact;
    }

}

