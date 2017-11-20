package cn.edu.hznu.sqlitelab;

/**
 * Created by del on 2017/11/16.
 */

public class PhoneNumber {
    private boolean isCheck;
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
    public void setIsCheck(boolean isCheck){
        this.isCheck = isCheck;
    }
    public boolean getIsCheck(){
        return isCheck;
    }
}
