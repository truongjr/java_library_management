/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Reader {
    private final int readerID;
    private String readerName;
    private String readerGender;
    private String email;
    private String dayOfBirth;
    private String phoneNumber;
    private boolean isActive;

    public Reader(int readerID, String readerName, String readerGender, String email, String dayOfBirth, String phoneNumber, boolean isActive) {
        this.readerID = readerID;
        this.readerName = readerName;
        this.readerGender = readerGender;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }
    
    public int getReaderID() {
        return readerID;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderGender() {
        return readerGender;
    }

    public void setReaderGender(String readerGender) {
        this.readerGender = readerGender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
