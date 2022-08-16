package com.example.calculator;

import androidx.core.content.res.TypedArrayUtils;

import java.util.Arrays;
import java.util.Stack;

public class Logic {
    public static boolean isAction(String action){
        return action.equals("÷") || action.equals("×") || action.equals("-") || action.equals("+") || action.equals("mod") || action.equals("^");
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


    public static int mod(int a, int b, int N){
        int r = 1;
        while (b != 0){
            if (b % 2 == 1){
                r = r * a;
            }
            b = b / 2;
            a = a * a % N;
            r = r % N;
        }

       return r;
    }

    public static String evaluate(Stack<String> stack){
        StringBuffer st = new StringBuffer();
        String[] stacks = new String[stack.size()];
        String temp = "";
        for (int i = stack.size()-1; i >= 0; i--){
            temp = stack.pop();
            if (temp.equals("")){
                break;
            }
            stacks[i] = temp.replace(",", ".");;
        }


        double result = 0;
        int count = 0;
        int number = 0;
        for (int i = 1; i < stacks.length; i += 2){
            switch (stacks[i]) {
                case "mod":
                    try {
                        Stack<String> stMas = new Stack<String>();
                        for (int j = 0; j < i; j++){
                            stMas.push(stacks[j]);
                        }
                        evaluate(stMas);

                        int size = stMas.size();
                        String[] stMMas = new String[size];
                        for (int j = size-1; j >= 0; j--){
                            temp = stMas.pop();
                            if (temp.equals("")){
                                break;
                            }
                            stMMas[j] = temp;;
                        }

                        int a = Integer.parseInt(stMMas[0]);
                        int N = Integer.parseInt(stacks[i+1]);
                        if (size == 3){
                            if (stMMas[1].equals("^")){

                                int b = Integer.parseInt(stMMas[2]);

                                result = mod(a, b, N);
                            }
                        }
                        if (size == 1){
                            result = a % N;
                        }

                        stacks = Arrays.copyOfRange(stacks, i+1, stacks.length);
                        stacks[0] = Double.toString(result);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;

            }
        }

        for (int i = 1; i < stacks.length; i += 2){
            switch (stacks[i]){

                case "÷":
                    try {
                        number = 0;
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
                        stacks = Arrays.copyOfRange(stacks, 0, stacks.length - number);
                        i = -1;

                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;
                case "×":
                    try {
                        number = 0;
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
                        stacks = Arrays.copyOfRange(stacks, 0, stacks.length - number);
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
                        number = 0;
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
                        stacks = Arrays.copyOfRange(stacks, 0, stacks.length - number);
                        i = -1;
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;
                case "-":
                    try {
                        number = 0;
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
                        stacks = Arrays.copyOfRange(stacks, 0, stacks.length - number);
                        i = -1;
                    } catch (NumberFormatException ex){
                        ex.printStackTrace();
                        return "Error";
                    }
                    break;

            }
        }
        for (String i : stacks){
            stack.push(i);
        }

        return stack.peek();
    }
}
