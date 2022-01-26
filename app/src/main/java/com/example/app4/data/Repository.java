package com.example.app4.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.app4.controller.Acontroller;
import com.example.app4.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

  public  ArrayList<Question> questionList= new ArrayList<>();
      public  String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    public  List<Question>  getJsonArrayRequest(final Syn callback){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {

            for (int i = 0; i < response.length(); i++) {
                try {
                Question question = new Question(response.getJSONArray(i).getString(0),response.getJSONArray(i).getBoolean(1));
                questionList.add(question);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
           if(null!=callback) callback.finishprocess(questionList);
        }, error -> Log.d("Err", "onResponse: "+error.toString()));
        Acontroller.getInstance().AddRequest(jsonArrayRequest);
        return  questionList;
    }


}
