package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ItemEntity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ReadingType1;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ReadingType2;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ReadingType3;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/30.
 */

public class JsonUtils {
    public static List<Map<String,Object>> getReadDataFromJson(String json){
        List<Map<String,Object>>list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray_datas = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray_datas.length(); i++) {
                JSONObject jsonObject_data = jsonArray_datas.getJSONObject(i);
                Map<String,Object>map = new HashMap<>();
                map.put("date",jsonObject_data.getString("date"));
                List<Map<String,Object>>list_items = new ArrayList<>();
                List<Map<String,Object>>list_types = new ArrayList<>();
                JSONArray jsonArray_items = jsonObject_data.getJSONArray("items");
                for (int j = 0; j < jsonArray_items.length(); j++) {
                    JSONObject jsonObject_item = jsonArray_items.getJSONObject(j);
                    int type = jsonObject_item.getInt("type");
                    //Log.i("TAG00","第"+i+j+"的type:"+type);
                    Gson gson = new Gson();
                    Map<String,Object>map_types = new HashMap<>();
                    map_types.put("type",type+"");
                    list_types.add(map_types);
                    Map<String,Object>map_item = new HashMap<>();
                    switch (type){
                        case 1:
                            ReadingType1 readingType1 = gson.fromJson(jsonArray_items.getString(j),ReadingType1.class);
                            map_item.put("item",readingType1);
                            break;
                        case 2:
                            ReadingType2 readingType2 = gson.fromJson(jsonArray_items.getString(j),ReadingType2.class);
                            map_item.put("item",readingType2);
                            break;
                        case 3:
                            ReadingType3 readingType3 = gson.fromJson(jsonArray_items.getString(j),ReadingType3.class);
                            map_item.put("item",readingType3);
                            break;
                    }
                    list_items.add(map_item);
                }
                map.put("types",list_types);
                map.put("items",list_items);
                list.add(map);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ItemEntity> getItemEntitys(String json){
        List<Map<String,Object>> list_all = JsonUtils.getReadDataFromJson(json);
        List<ItemEntity>list_item = new ArrayList<>();
        for (int i = 0; i < list_all.size(); i++) {
            String date = list_all.get(i).get("date").toString();
            List<Map<String,Object>> list_types = (List<Map<String, Object>>) list_all.get(i).get("types");
            List<Map<String,Object>>items_list = (List<Map<String, Object>>) list_all.get(i).get("items");
            for (int j = 0; j < list_types.size(); j++) {
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setmTitle(date);
                String type = list_types.get(j).get("type").toString();
                if ("1".equals(type)){
                    ReadingType1 readingType1 = (ReadingType1) items_list.get(j).get("item");
                    itemEntity.setmContent(readingType1.getContent().getHp_title());
                    itemEntity.setmText2(readingType1.getContent().getAuthor().get(0).getUser_name());
                    itemEntity.setmText3("　　" + readingType1.getContent().getGuide_word());
                    itemEntity.setType(readingType1.getType());
                }else if("2".equals(type)){
                    ReadingType2 readingType2 = (ReadingType2) items_list.get(j).get("item");
                    itemEntity.setmContent(readingType2.getContent().getTitle());
                    itemEntity.setmText2(readingType2.getContent().getAuthor().getUser_name());
                    itemEntity.setmText3("　　" + readingType2.getContent().getExcerpt());
                    itemEntity.setType(readingType2.getType());
                }else if("3".equals(type)){
                    ReadingType3 readingType3 = (ReadingType3) items_list.get(j).get("item");
                    itemEntity.setmContent(readingType3.getContent().getQuestion_title());
                    itemEntity.setmText2(readingType3.getContent().getAnswer_title());
                    itemEntity.setmText3("　　" + readingType3.getContent().getAnswer_content());
                    itemEntity.setType(readingType3.getType());
                }
                list_item.add(itemEntity);
            }
        }
        return list_item;
    }

}
