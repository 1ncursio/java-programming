package com.company;

public class Post {
    private int id;
    private String title;
    private String description;
    private String author;
//
//    public Post(int id, String title, String description, String author) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.author = author;
//    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
}
