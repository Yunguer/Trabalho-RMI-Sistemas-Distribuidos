package PubSub;

import java.io.Serializable;

public class Cell implements Serializable{

    private static final long serialVersionUID = 7526442295522776847L;

    private String message = "";

    public void set(String msg){
        message = msg;
    }

    public String get(){
        return message;
    }
}