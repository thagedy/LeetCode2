package com.thagedy.leetcode;

import java.util.*;

/**
 * Created by Kaijia Wei on 2017/5/17.
 */
public class SecondQuestion {
    public static String EQ = "=";
    public static String ELESEPARATOR = ";";
    public static String ARRSEPARATOR = "\n";

    public String changeMapArrayToString(Map<String, String>[] maps) {
        StringBuffer stringBuffer = new StringBuffer();
        if (maps == null || maps.length < 1) {
            return null;
        }
        for (Map<String, String> map : maps) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuffer.append(entry.getKey());
                stringBuffer.append(SecondQuestion.EQ);
                stringBuffer.append(entry.getValue());
                stringBuffer.append(SecondQuestion.ELESEPARATOR);
            }
            //清除最后多余的";"
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(SecondQuestion.ELESEPARATOR));
            stringBuffer.append(SecondQuestion.ARRSEPARATOR);
        }
        return stringBuffer.toString();
    }

    public Map[] StringToMapArray(String mapString) {
        if (mapString == null || "".equals(mapString)){
            return new Map[1];
        }
        //处理字符串
        //// TODO: 2017/5/17 利用正则匹配字符串进行优化
        String[] split = mapString.split(SecondQuestion.ARRSEPARATOR);
        Map<String,String>[] result = new Map[split.length];
        Map<String,String> map ;
        int count = 0;
        for (String str : split){
            map = new HashMap<String, String>();
            String[] split1 = str.split(SecondQuestion.ELESEPARATOR);
            for (String sp : split1){
                String[] split2 = sp.split(SecondQuestion.EQ);
                if (split2==null || split2.length < 1)continue;
                map.put(split2[0],split2.length==2?split2[1]:"");
            }
            result[count++] = map;
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> objectMap1 = new HashMap<String, String>();
        objectMap1.put("c", "3");
        objectMap1.put("d", "4");
        Map<String, String> objectMap = new HashMap<String, String>();
        objectMap.put("a", "1");
        objectMap.put("b", "2");
        Map<String, String> objectMap3 = new HashMap<String, String>();
        objectMap3.put("e", "5");
        Map[] maps = {objectMap, objectMap1, objectMap3};

        SecondQuestion secondQuestion = new SecondQuestion();
        String string = secondQuestion.changeMapArrayToString(maps);
        System.out.println(string);
        Map[] maps1 = secondQuestion.StringToMapArray(string);
        for (Map map : maps1){
            System.out.println(map);
        }
        System.out.println(maps1);
    }
}
