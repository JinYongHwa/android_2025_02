package kr.ac.mjc.ict2007261051;

import java.io.Serializable;

import kr.ac.mjc.ict2007261051.dto.BookDto;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String pub;
    private String thumbUrl;

    public Book(){}

    public Book(BookDto dto){
        this.id=dto.getId();
        this.title=dto.getTitleStatement();
        this.pub=dto.getPublication();
        this.author=dto.getAuthor();
        this.thumbUrl=dto.getThumbnailUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
