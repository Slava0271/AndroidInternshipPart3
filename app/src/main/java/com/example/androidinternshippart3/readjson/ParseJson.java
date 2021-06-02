package com.example.androidinternshippart3.readjson;

import android.content.Context;

import java.util.ArrayList;

public class ParseJson {
    static String[] parseJson(Context context, String filename) {
        ReadJson readJSON = new ReadJson();
        return readJSON.getJsonDataFromAsset(context, filename).split(",");
    }

    public static ArrayList<ObjectJSON> getObjectsJSON(Context context, String filename) {
        ArrayList<ObjectJSON> objectJSONS = new ArrayList<>();
        String[] parsedQuestions = parseJson(context, filename);

        for (int i = 0; i < parsedQuestions.length; i++) {
            String[] parse = parsedQuestions[i].split(":");
            parse[0] = parse[0].replaceAll("\"", "");
            parse[1] = parse[1].replaceAll("\"", "");
            objectJSONS.add(new ObjectJSON(parse[0], parse[1]));
        }
        return objectJSONS;
    }
}
