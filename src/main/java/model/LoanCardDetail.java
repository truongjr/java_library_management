/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class LoanCardDetail {
    private final String loanCardID;
    private final String bookTitleID;
    private String dayPay;

    public LoanCardDetail(String loanCardID, String bookTitleID, String dayPay) {
        this.loanCardID = loanCardID;
        this.bookTitleID = bookTitleID;
        this.dayPay = dayPay;
    }

    public String getLoanCardID() {
        return loanCardID;
    }

    public String getBookTitleID() {
        return bookTitleID;
    }

    public String getDayPay() {
        return dayPay;
    }

    public void setDayPay(String dayPay) {
        this.dayPay = dayPay;
    }
}
