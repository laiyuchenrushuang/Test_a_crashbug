package com.example.laiji.test_a_crash;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ly on 2019/4/29.
 */

public class HttpResultUtil {
    /**
     * 转换结果字符串为对象
     */
    public static ArrayList getArrayList(String json) {
        ArrayList arrayList = new ArrayList();
        if (json != null && !json.equals("")) {
            arrayList = fromJson(json, new TypeToken<ArrayList<Map>>() {
            }.getType());
        }
        return arrayList;
    }

    public static ArrayList getLists(String json) {
        ArrayList arrayList = new ArrayList();
        if (json != null && !json.equals("")) {
            arrayList = fromJson(json, new TypeToken<ArrayList>() {
            }.getType());
        }
        return arrayList;
    }

    public static Map  getMap(String json) {
        Map map = new HashMap();
        if (json != null) {
            map = fromJson(json, Map.class);
        }
        return map;
    }

    public static List<Integer> getlist(String json) {
        List<Integer> list = new ArrayList<>();
        if (json != null) {
            list = fromJson(json, new TypeToken<List<Integer>>(){
            }.getType());
        }
            return list;
        }

    /**
     * 转换参数对象为字符串
     */
    public static  String toJsonString(Object obj) {
        String strUTF8 = null;
        try {
            if (obj.getClass() == Map.class || obj.getClass() == HashMap.class || obj.getClass() == LinkedTreeMap.class)
                strUTF8 = toJson((Map) obj);
            if (obj.getClass() == List.class || obj.getClass() == ArrayList.class || obj.getClass() == ArrayList.class || obj.getClass() == LinkedList.class)
                strUTF8 = toJson((List) obj);
            if (obj.getClass() == String.class) {
                strUTF8 = toJson((String) obj);
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        return strUTF8;
    }

    public static String toJson(Object src) {
        Gson gson = new Gson();
        String json = gson.toJson(src);
        return json;
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }
}
