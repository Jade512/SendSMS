package com.example.sendsms;

public class Sms{
    private String _id;
    private String _address;
    private String _msg;
    private String _readState; //"0" for have not read sms and "1" for have read sms
    private String _time;
    private String _type;

    public String getId(){
        return _id;
    }
    public String getAddress(){
        return _address;
    }
    public String getMsg(){
        return _msg;
    }
    public String getReadState(){
        return _readState;
    }
    public String getTime(){
        return _time;
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    public void setId(String id){
        _id = id;
    }
    public void setAddress(String address){
        _address = address;
    }
    public void setMsg(String msg){
        _msg = msg;
    }
    public void setReadState(String readState){
        _readState = readState;
    }
    public void setTime(String time){
        _time = time;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "_id='" + _id + '\'' +
                ", _address='" + _address + '\'' +
                ", _msg='" + _msg + '\'' +
                ", _readState='" + _readState + '\'' +
                ", _time='" + _time + '\'' +
                ", _type='" + _type + '\'' +
                '}';
    }
}