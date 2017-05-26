package com.thagedy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kaijia Wei on 2017/5/17.
 */
public class FirstQuestion {
    /*
     在某个特定应用场景中，我们有一个从JSON获取的内容，比如：
      m = { "a": 1, "b": { "c": 2, "d": [3,4] } }
      现在需要把这个层级的结构做展开，只保留一层key/value结构。对于上述输入，需要得到的结构是：
     o = {"a": 1, "b.c": 2, "b.d": [3,4] }
     也就是说，原来需要通过 m["b"]["c"] 访问的值，在展开后可以通过 o["b.c"] 访问。
     请实现这个“层级结构展开”的代码。
     输入：任意JSON（或者map/dict）
     输出：展开后的JSON（或者map/dict）
  */
    public String sepetor = ".";
    public Map<String,Object> formatMap(Map<String,Object> map){
        if (map!=null){
            //结果map
            Map<String, Object> result = new HashMap<String, Object>();
            //循环去处理每个map的实体entry
            MapEntry mapEntry;
            for (Map.Entry<String,Object> entry : map.entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();

                mapEntry = new MapEntry();
                mapEntry.setKey(key);
                mapEntry.setValue(value);

                List<MapEntry> formatEntys = getFormatEnty(mapEntry);
                for (MapEntry formatEnty : formatEntys)
                    result.put(formatEnty.getKey(),formatEnty.getValue());
            }
            return result;
        }
        return null;
    }

    private List<MapEntry> getFormatEnty(MapEntry entry){
        List<MapEntry> results = new ArrayList<MapEntry>();
        MapEntry mapEntry = null;
        String key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof Map){
            Map<String,Object> value1 = (Map<String,Object>) value;
            for (Map.Entry subEntry : value1.entrySet()){
                mapEntry = new MapEntry();
                mapEntry.setKey(key + sepetor + subEntry.getKey() );
                mapEntry.setValue(subEntry.getValue());
                List<MapEntry> formatEntys = getFormatEnty(mapEntry);
                results.addAll(formatEntys);
            }
        }else{
            results.add(entry);
        }
        return results;
    }

    public static void main(String[] args) {
        Map<String,Object> objectMap1 = new HashMap<String, Object>();
        objectMap1.put("c",2);
        objectMap1.put("d",new int[]{3,4});
        Map<String,Object> objectMap = new HashMap<String, Object>();
        objectMap.put("a",1);
        objectMap.put("b",objectMap1);
        Map<String,Object> objectMap3 = new HashMap<String, Object>();
        objectMap3.putAll(objectMap);
        objectMap.put("e",objectMap3);
    }
}

class MapEntry{
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    String key;
    Object value;

}
