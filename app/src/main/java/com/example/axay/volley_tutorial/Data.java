package com.example.axay.volley_tutorial;

/**
 * Created by axay on 20/07/17.
 */

public class Data {


    private String Head;
    private String content;


    public Data(String head, String content) {
        Head = head;
        this.content = content;
    }


    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
