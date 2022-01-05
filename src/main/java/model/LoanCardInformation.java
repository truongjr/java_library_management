/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class LoanCardInformation {
    private final String loanCardID;
    private final int readerID;
    private int numberOfBookBorrowed;
    private String dayBorrowed;

    public LoanCardInformation(String loanCardID, int readerID, int numberOfBookBorrowed, String dayBorrowed) {
        this.loanCardID = loanCardID;
        this.readerID = readerID;
        this.numberOfBookBorrowed = numberOfBookBorrowed;
        this.dayBorrowed = dayBorrowed;
    }

    public String getLoanCardID() {
        return loanCardID;
    }

    public int getReaderID() {
        return readerID;
    }

    public int getNumberOfBookBorrowed() {
        return numberOfBookBorrowed;
    }

    public void setNumberOfBookBorrowed(int numberOfBookBorrowed) {
        this.numberOfBookBorrowed = numberOfBookBorrowed;
    }

    public String getDayBorrowed() {
        return dayBorrowed;
    }

    public void setDayBorrowed(String dayBorrowed) {
        this.dayBorrowed = dayBorrowed;
    }
}
