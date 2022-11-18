package com.ward.photogram.util;

public class Script {

    // 클라이언트가 응답을 받을 때 효과적
    public static String back(String msg){
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        sb.append("<script>");
        return sb.toString();
        // 예외가 발생했을 때 사용자에게 보여줄 스크립트를 구현합니다.
        //해당 클래스의 back함수를 호출하면 메세지를 담은 경고창을 띄우고 다시 돌아가게 됩니다.
    }
}
