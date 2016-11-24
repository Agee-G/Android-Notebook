package com.bignerdranch.android.notebook;

import java.util.Date;
import java.util.UUID;

/**
 * Created by G__Agee on 2016/11/3.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime(){
        this(UUID.randomUUID());
       // mId=UUID.randomUUID();
        //mDate=new Date();
    }

    public Crime(UUID id){
        mId=id;
        mDate=new Date();
    }
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
    public String getSuspect(){
        return mSuspect;
    }
    public void setSuspect(String suspect){
        mSuspect=suspect;
    }


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
    public boolean getSolved() {
        return mSolved;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    //m开头的变量和声明变量分开了





}
