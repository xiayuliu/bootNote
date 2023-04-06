package com.xia.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UtilCore {

    //获取当前时间
    public static String getNewDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyHHddssSSS");
        return simpleDateFormat.format(date);
    }

    //生成随机数
    public static String getRandom(int i){

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
        if (i<=0){
            return "";
        }
        Random random = new Random();
        String ran="";
        for (int j=0;j<i;j++){
            ran+=random.nextInt(9);
        }
        return ran;
    }

    //组合生成tokenId
    public static String getToKenID(int i){
        return getNewDate()+getRandom(i);
    }

}
