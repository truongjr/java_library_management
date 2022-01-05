/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class BookTitle {
    private final String bookTitleID;
    private String boolTitleName;
    private final String bookTypeID;
    private String author;
    private int bookNumber; //so luong cua danh muc sach
    private String publishingCompany;
    private int publishingYear;

    public BookTitle(String bookTitleID, String boolTitleName, String bookTypeID, String author, int bookNumber, String publishingCompany, int publishingYear) {
        this.bookTitleID = bookTitleID;
        this.boolTitleName = boolTitleName;
        this.bookTypeID = bookTypeID;
        this.author = author;
        this.bookNumber = bookNumber;
        this.publishingCompany = publishingCompany;
        this.publishingYear = publishingYear;
    }

    public String getBookTitleID() {
        return bookTitleID;
    }

    public String getBoolTitleName() {
        return boolTitleName;
    }

    public void setBoolTitleName(String boolTitleName) {
        this.boolTitleName = boolTitleName;
    }

    public String getBookTypeID() {
        return bookTypeID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
}
