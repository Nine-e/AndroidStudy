package cn.edu.hznu.labaddressclient;

/**
 * Created by del on 2017/11/26.
 */

public class PhoneNumber {
    private String name;
    private String number;

    public PhoneNumber(String name,String number){
        this.name = name;
        this.number = number;
    }
    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
}

