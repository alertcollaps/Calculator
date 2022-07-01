package com.example.calculator;

import android.animation.StateListAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    final int MIN_SHIFT = 300;
    public static MainActivity mn;
    public static ViewFlipper flipper = null;
    public static float fromPosition;
    private static Stack<String> act = new Stack<>();
    TextView enter;
    TextView result;
    Button1 one;
    Button1 two;
    Button1 three;
    Button1 four;
    Button1 five;
    Button1 six;
    Button1 seven;
    Button1 eight;
    Button1 nine;
    Button1 zero;
    Button1 dot;
    Button1 backspace;
    Button1 divide;
    Button1 multiplicate;
    Button1 minus;
    Button1 plus;
    Button1 equal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int layouts[] = new int[]{R.layout.first, R.layout.second};
        for (int layout : layouts) {
            flipper.addView(inflater.inflate(layout, null));
        }

        mn = this;


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button1 btn = (Button1) findViewById(view.getId());
                String str = btn.getText().toString();
                if (Logic.isAction(str) && Logic.isAction(peek())){
                    delEnter();
                    pop();
                }
                if (Logic.isAction(str) && Logic.isNumber(peek())){
                    push(str);
                    addEnter(str);
                    return;
                }
                if (Logic.isNumber(peek()+str) && !Logic.isAction(peek())){
                    if (peek().equals("0") && !str.equals(",") && Logic.isNumber(str)){
                        delEnter();
                        pop();
                    }
                    String temp = pop();
                    push(temp+str);
                    addEnter(str);
                    return;
                }
                if (Logic.isAction(peek()) && Logic.isNumber(str)){
                    push(str);
                    addEnter(str);
                    return;
                }
                if (Logic.isEqual(str)){
                    String res = Logic.evaluate(act);
                    setResult(res);
                    setEnter("");
                }

                if (Logic.isDel(str)){
                    act = new Stack<>();
                    setResult("");
                    setEnter("");
                }
            }
        };

        init(onClickListener);



    }



    private String peek(){
        try{
            return act.peek();
        }catch (RuntimeException ex){
            return "";
        }
    }

    private void push(String str){
        try{
            act.push(str);
        }catch (RuntimeException ignored){

        }
    }
    public static String pop(){
        try{
            return act.pop();
        }catch (RuntimeException ex){
            return "";
        }
    }

    private void addEnter(String str){
        enter.setText(enter.getText()+str);
    }

    private void delEnter(){
        String str = enter.getText().toString();
        enter.setText(str.substring(0, str.length()-1));
    }

    private void setEnter(String str){
        enter.setText(str);
    }

    private void setResult(String str){
        result.setText(str);
    }


    public void init(View.OnClickListener onClickListener){
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        zero=findViewById(R.id.zero);
        dot=findViewById(R.id.dot);
        backspace=findViewById(R.id.backspace);
        divide=findViewById(R.id.divide);
        multiplicate=findViewById(R.id.multiplicate);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
        equal=findViewById(R.id.equal);
        enter = findViewById(R.id.enter);
        result = findViewById(R.id.result);

        one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
        five.setOnClickListener(onClickListener);
        six.setOnClickListener(onClickListener);
        seven.setOnClickListener(onClickListener);
        eight.setOnClickListener(onClickListener);
        nine.setOnClickListener(onClickListener);
        zero.setOnClickListener(onClickListener);
        dot.setOnClickListener(onClickListener);
        backspace.setOnClickListener(onClickListener);
        divide.setOnClickListener(onClickListener);
        multiplicate.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);
        equal.setOnClickListener(onClickListener);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition > toPosition+ MIN_SHIFT)
                {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
                    flipper.showNext();
                }
                else if (fromPosition + MIN_SHIFT < toPosition)
                {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
                    flipper.showPrevious();
                }
            default:
                break;
        }
        return true;
    }
}