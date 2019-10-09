package com.df.smallwater.smallwater.utils;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.bean.Gonggao;
import com.df.smallwater.smallwater.bean.Homework;
import com.df.smallwater.smallwater.bean.Richang;
import com.df.smallwater.smallwater.bean.Xinwen;
import com.df.smallwater.smallwater.bean.Youeryuan;
import com.df.smallwater.smallwater.bean.Zhuangkuang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DF on 2018/5/31.
 */

public class JsonUtil {
    /**
     * 将json数组解析出来,生成自定义数据的数组
     *
     * @param data 包含用户自定义数据的json
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public static List<Youeryuan.YoueryuanpicBean> Json2Youeryuanpic(String data) throws JSONException {
        List<Youeryuan.YoueryuanpicBean> items = new ArrayList<>();
        if (data == null || data.equals(""))

            return items;

        JSONArray array = new JSONArray(data);
        JSONObject object = null;
        Youeryuan.YoueryuanpicBean item = null;
        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            int id = (int) object.get("id");
            String title = (String) object.get("title");
            String pic = (String) object.get("pic");
            int youeryuanid = (int) object.get("youeryuanid");
            item = new Youeryuan.YoueryuanpicBean();
            item.setId(id);
            item.setPic(pic);
            item.setTitle(title);
            item.setYoueryuanid(youeryuanid);
            items.add(item);
        }
        return items;
    }


    /**
     * 将json数组解析出来,生成自定义数据的数组
     *
     * @param data 包含用户自定义数据的json
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public static List<Xinwen.XinwenlistBean> Json2xinwen(String data) throws JSONException {
        List<Xinwen.XinwenlistBean> items = new ArrayList<>();
        if (data == null || data.equals("")) return items;

        JSONArray array = new JSONArray(data);
        JSONObject object = null;
        Xinwen.XinwenlistBean item = null;
        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            int id = (int) object.get("id");
            String content = (String) object.get("content");
            String time = (String) object.get("time");
            int youeryuanid = (int) object.get("youeryuanid");
            String title = (String) object.get("title");
            String pic = (String) object.get("pic");
            item = new Xinwen.XinwenlistBean();
            item.setId(id);
            item.setPic(pic);
            item.setTitle(title);
            item.setYoueryuanid(youeryuanid);
            item.setContent(content);
            item.setTime(time);
            items.add(item);
        }
        return items;
    }


    /**
     * 将json数组解析出来,生成自定义数据的数组
     *
     * @param data 包含用户自定义数据的json
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public static List<Gonggao.GonggaolistBean> Json2gonggao(String data) throws JSONException {
        List<Gonggao.GonggaolistBean> items = new ArrayList<>();
        if (data == null || data.equals("")) return items;

        JSONArray array = new JSONArray(data);
        JSONObject object = null;
        Gonggao.GonggaolistBean item = null;
        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            int id = (int) object.get("id");
            String content = (String) object.get("content");
            String time = (String) object.get("time");
            int youeryuanid = (int) object.get("youeryuanid");
            item = new Gonggao.GonggaolistBean();
            item.setId(id);
            item.setContent(content);
            item.setTime(time);
            item.setYoueryuanid(youeryuanid);
            items.add(item);
        }
        return items;
    }


    /**
     * 将json数组解析出来,生成自定义数据的数组
     *
     * @param data 包含用户自定义数据的json
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public static List<Richang.RichanglistBean> Json2richang(String data) throws JSONException {
        List<Richang.RichanglistBean> items = new ArrayList<>();
        if (data == null || data.equals("")) return items;

        JSONArray array = new JSONArray(data);
        JSONObject object = null;
        Richang.RichanglistBean item = null;
        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            int id = (int) object.get("id");
            String title = (String) object.get("title");
            String time = (String) object.get("time");
            int banjiid = (int) object.get("banjiid");
            String content = (String) object.get("content");
            String pic = (String) object.get("pic");
            item = new Richang.RichanglistBean();
            item.setId(id);
            item.setPic(pic);
            item.setTitle(title);
            item.setBanjiid(banjiid);
            item.setTime(time);
            item.setContent(content);
            items.add(item);
        }
        return items;
    }


    /**
     * 将json数组解析出来,生成自定义数据的数组
     *
     * @param data 包含用户自定义数据的json
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public static List<Zhuangkuang.ZhuangkuanglistBean> Json2zhuangkuang(String data) throws JSONException {
        List<Zhuangkuang.ZhuangkuanglistBean> items = new ArrayList<>();
        if (data == null || data.equals("")) return items;

        JSONArray array = new JSONArray(data);
        JSONObject object = null;
        Zhuangkuang.ZhuangkuanglistBean item = null;
        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            int id = (int) object.get("id");
            String content = (String) object.get("content");
            String time = (String) object.get("time");
            String username = (String) object.get("username");
            item = new Zhuangkuang.ZhuangkuanglistBean();
            item.setId(id);
            item.setContent(content);
            item.setTime(time);
            item.setUsername(username);
            items.add(item);
        }
        return items;
    }


}
