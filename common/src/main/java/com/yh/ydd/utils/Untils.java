package com.yh.ydd.utils;

import android.content.Context;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinDict;
import com.github.promeg.pinyinhelper.PinyinMapDict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Untils {

    private static PinyinDict dict;

    /**
     * 读取raw资源下的文件
     *
     * @param context 在下文
     * @param res     资源id
     * @return 返回资源内容
     */
    public static String getStringFromRaw(Context context, int res) {

        InputStream inputStream = context.getResources().openRawResource(res);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(Objects.requireNonNull(inputStreamReader));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getDishNameInitial(String name) {

        StringBuilder stringBuilder = new StringBuilder();
        if (dict == null) {
            dict = new PinyinMapDict() {
                @Override
                public Map<String, String[]> mapping() {
                    Map<String, String[]> map = new HashMap<>();
                    map.put("腌", new String[]{"YAN"});
                    return map;
                }
            };
            Pinyin.init(Pinyin.newConfig().with(dict));
        }

        String[] strings = Pinyin.toPinyin(name, ".").split("\\.");
        for (String string : strings) {
            stringBuilder.append(String.valueOf(string.charAt(0)).toLowerCase());
        }
        return stringBuilder.toString();
    }
}
