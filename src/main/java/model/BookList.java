/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class BookList {
    private final String bookTypeID;
    private final String bookListID;
    private int bookStatus;

    public BookList(String bookTypeID, String bookListID, int bookStatus) {
        this.bookTypeID = bookTypeID;
        this.bookListID = bookListID;
        this.bookStatus = bookStatus;
    }

    public String getBookTypeID() {
        return bookTypeID;
    }

    public String getBookListID() {
        return bookListID;
    }


    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }
    
    
}
