package com.example.calculator;

import android.hardware.biometrics.BiometricManager;

import java.util.Stack;

public class Logic {
    public static boolean isAction(String action){
        return action.equals("÷") || action.equals("×") || action.equals("-") || action.equals("+");
    }

    public static boolean isEqual(String action){
        return action.equals("=");
    }

    public static boolean isDel(String action){
        return action.equals("del");
    }



    public static boolean isNumber(String number){
        if (number.contains(",")){
            number = number.replace(",", ".");
        }
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException ex){
            return false;
        }

        return true;
    }

    public static String evaluate(Stack<String> stack){
        StringBuffer st = new StringBuffer();
        String[] stacks = new String[stack.size()];
        String temp = "";
        for (int i = stack.size()-1; i >= 0; i--){
            temp = MainActivity.pop();
            if (temp.equals("")){
                break;
            }
            stacks[i] = temp.replace(",", ".");;
        }


        double result = 0;
        int count = 0;
        int number = 0;
        for (int i = 1; i < stacks.length; i += 2){
            switch (stacks[i]){
                case "÷":
                    try {
                        result = Double.parseDouble(stacks[i - 1]) / Double.parseDouble(stacks[i + 1]);
                        stacks[i] = Double.toString(result);
                        count = 0;
                        for (int j = 0; j < stacks.length; j++){
                            if (j == i - 1 || j == i + 1){
                                number++;
                                continue;
                            }
                            stacks[count] = stacks[j];
                            count++;
                        }
                        stacks[stacks.length-number]="";
                        stacks[stacks.length-number+1]="";
                        i = -1;

                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;
                case "×":
                    try {
                        result = Double.parseDouble(stacks[i - 1]) * Double.parseDouble(stacks[i + 1]);
                        stacks[i] = Double.toString(result);
                        count = 0;
                        for (int j = 0; j < stacks.length; j++){
                            if (j == i - 1 || j == i + 1){
                                number++;
                                continue;
                            }
                            stacks[count] = stacks[j];
                            count++;
                        }
                        stacks[stacks.length-number]="";
                        stacks[stacks.length-number+1]="";
                        i = -1;
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;
            }
        }

        for (int i = 1; i < stacks.length; i += 2){
            switch (stacks[i]){
                case "+":
                    try {
                        result = Double.parseDouble(stacks[i - 1]) + Double.parseDouble(stacks[i + 1]);
                        stacks[i] = Double.toString(result);
                        count = 0;
                        for (int j = 0; j < stacks.length; j++){
                            if (j == i - 1 || j == i + 1){
                                number++;
                                continue;
                            }
                            stacks[count] = stacks[j];
                            count++;
                        }
                        stacks[stacks.length-number]="";
                        stacks[stacks.length-number+1]="";
                        i = -1;
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;
                case "-":
                    try {
                        result = Double.parseDouble(stacks[i - 1]) - Double.parseDouble(stacks[i + 1]);
                        stacks[i] = Double.toString(result);
                        count = 0;
                        for (int j = 0; j < stacks.length; j++){
                            if (j == i - 1 || j == i + 1){
                                number++;
                                continue;
                            }
                            stacks[count] = stacks[j];
                            count++;
                        }
                        stacks[stacks.length-number]="";
                        stacks[stacks.length-number+1]="";
                        i = -1;
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;

            }
        }
        return Double.toString(result);
    }
}
