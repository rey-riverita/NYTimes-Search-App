package com.codepath.nytimessearch;

import android.widget.CheckBox;

import java.io.Serializable;

/**
 * Created by riverita on 6/22/16.
 */
public class ArticleFilter implements Serializable {

    public String sort;
    public String beginDate;
    public String newsDeskValue;
    public boolean checkBoxArts;
    public boolean checkBoxFashion;
    public boolean checkBoxSports;


    public ArticleFilter() {

    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setNewsDeskValue(String newsDeskValue) {
        this.newsDeskValue = newsDeskValue;
    }


    public String getSort() {
        return sort;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getNewsDeskValue() {
        return newsDeskValue;
    }


    public boolean checkNewsDeskEnabled(CheckBox checkBox){
        if(checkBox.isChecked()){
            return true;
        } else {
            return false;
        }
    }


}
