/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class BookType {
    private final String bookTypeID;
    private String bookTypeName;

    public BookType(String bookTypeID, String bookTypeName) {
        this.bookTypeID = bookTypeID;
        this.bookTypeName = bookTypeName;
    }

    public String getBookTypeID() {
        return bookTypeID;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

}
