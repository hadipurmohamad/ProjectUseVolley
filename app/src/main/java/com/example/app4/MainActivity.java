package com.example.app4;

import static android.R.color.white;

import static com.example.app4.R.color.green;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.app4.controller.Acontroller;
import com.example.app4.data.Repository;
import com.example.app4.data.Syn;
import com.example.app4.databinding.ActivityMainBinding;
import com.example.app4.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Animator.AnimatorListener {
List<Question>  questionList;
    private ActivityMainBinding binding;
    private Animator fade;
    private Animator rotate;
 private  int cunter =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        questionList=  new Repository().getJsonArrayRequest(questionArrayList -> {
           questionList=questionArrayList;
           updatequestion(cunter);
            updatecunter(cunter);

         });
binding.nextBtn.setOnClickListener(view -> {
    binding.showYourAnswer.setText("");
    binding.question.setTextColor(getResources().getColor(R.color.white));
    cunter = (cunter + 1)%questionList.size();
    Log.d("TAG", "onCreate: "+cunter);
    updatequestion(cunter);
    updatecunter(cunter);
});
binding.previousBtn.setOnClickListener(view -> {
    binding.showYourAnswer.setText("");
    binding.question.setTextColor(getResources().getColor(R.color.white));
    if (cunter>0){
        cunter = (cunter - 1)%questionList.size();
        Log.d("TAG", "onCreate: "+cunter);
        updatequestion(cunter);
        updatecunter(cunter);
    }else{
        Log.d("TAG", "onCreate: "+cunter);
        cunter =questionList.size()-1;
        updatequestion(cunter);
        updatecunter(cunter);
    }

        });
binding.FalseBtn.setOnClickListener(view -> {
    answerquestion(false);
});
binding.trueBtn.setOnClickListener(view -> {
    answerquestion(true);
});

    }


    private void answerquestion(boolean b) {
        if(b == questionList.get(cunter).getAnswer()){
            binding.showYourAnswer.setText("correct");

            binding.showYourAnswer.setTextColor(getResources().getColor(R.color.green));
       fade();
        }else{
            binding.showYourAnswer.setText("incorrect!!");
    binding.showYourAnswer.setTextColor(getResources().getColor(R.color.red));
            rotation();

        }
    }

    private void updatecunter(int cunter) {
        binding.qustioncount.setText("Question :"+(cunter+1)+"/"+questionList.size());
    }

    private void updatequestion(int cunter) {
        binding.question.setText(questionList.get(cunter).getQuestion());
    }




    @SuppressLint("ResourceAsColor")
    private void fade() {
fade= AnimatorInflater.loadAnimator(this,R.animator.first_anim);
fade.setTarget(binding.question);
fade.addListener(this);
fade.start();

    }

private  void rotation(){
    rotate=AnimatorInflater.loadAnimator(this,R.animator.rotation);
    rotate.setTarget(binding.question);
    rotate.addListener(this);
    rotate.start();
}
    @SuppressLint("ResourceAsColor")
    @Override
    public void onAnimationStart(Animator animator) {
        if (animator==rotate) {
        binding.question.setTextColor(getResources().getColor(R.color.red));
        }
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onAnimationEnd(Animator animator) {
        if (animator==fade) {
            binding.question.setTextColor(getResources().getColor(R.color.green));
        }
}



    @SuppressLint("ResourceAsColor")
    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}