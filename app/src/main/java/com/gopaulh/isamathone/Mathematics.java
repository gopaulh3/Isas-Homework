package com.gopaulh.isamathone;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mathematics extends AppCompatActivity {

    LinkedHashSet<Number> TempAnswers = new LinkedHashSet<>();
    ArrayList<Number> finalAnswers = new ArrayList<>(4);
    Button b0,b1,b2,b3;
    TextView sumTv;
    int min = 0, max = 9, a, b;
    Boolean bool;

    public Mathematics(Button b0, Button b1, Button b2, Button b3, TextView sumTv) {
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.sumTv = sumTv;
    }

    public int general(int category, boolean isEasy) {
        int locationOfCorrectAnswer = new Random().nextInt(4);
        TempAnswers.clear();
        finalAnswers.clear();
        bool = false;

        if (category == 5) {
            rounding(isEasy, locationOfCorrectAnswer);
        } else {
            int correctAnswer = 0, x;

            switch (category) {
                case 0: correctAnswer = allOperations(isEasy); break;
                case 1: correctAnswer = addition(isEasy); break;
                case 2: correctAnswer = multiplication(isEasy); break;
                case 3: correctAnswer = subtraction(isEasy); break;
                case 4: correctAnswer = division(isEasy); break;
                default: addition(isEasy);
            }

            while (TempAnswers.size() != 4) {
                if (bool) {
                    x = ((correctAnswer) + (new Random().nextInt((5))));
                } else {
                    x = ((correctAnswer) - (new Random().nextInt((5))));
                }
                if (x != (correctAnswer)) TempAnswers.add(x);
                bool = !bool;
            }

            finalAnswers = new ArrayList<>(TempAnswers);
            finalAnswers.set(locationOfCorrectAnswer, correctAnswer);
        }
            b0.setText(String.valueOf(finalAnswers.get(0)));
            b1.setText(String.valueOf(finalAnswers.get(1)));
            b2.setText(String.valueOf(finalAnswers.get(2)));
            b3.setText(String.valueOf(finalAnswers.get(3)));

        return locationOfCorrectAnswer;
    }


    public void rounding(boolean isEasy, int locationOfCorrectAnswer) {

        DecimalFormat df = new DecimalFormat("0.0000"),df2;
        String decimalPlaces="1dp",decimalPlacesNumber="0.0",decimalToBeRoundedString = df.format(0 + new Random().nextDouble() * (9));
        double x,decimalToBeRoundedDouble = Double.parseDouble(decimalToBeRoundedString),correctAnswer;

        if (!isEasy) {
            switch (new Random().nextInt(3) + 1) {
                case 1:
                    decimalPlaces = "1dp";
                    decimalPlacesNumber = "0.0";
                    break;
                case 2:
                    decimalPlaces = "2dp";
                    decimalPlacesNumber = "0.00";
                    break;
                case 3:
                    decimalPlaces = "3dp";
                    decimalPlacesNumber = "0.000";
                    break;
            }
        }

        sumTv.setText(decimalToBeRoundedString + " to "+decimalPlaces);
        df2 = new DecimalFormat(decimalPlacesNumber);
        correctAnswer = Double.parseDouble(df2.format(decimalToBeRoundedDouble));

        while (TempAnswers.size() != 4) {
            double randDecToDouble = Double.parseDouble(df2.format(0 + new Random().nextDouble() * (1)));
            if (bool) {
                x = ((correctAnswer) + (randDecToDouble));
            } else {
                x = ((correctAnswer) - (randDecToDouble));
            }
            if (x != (correctAnswer)) TempAnswers.add(Double.parseDouble(df2.format(x)));
            bool = !bool;
        }
        finalAnswers = new ArrayList<>(TempAnswers);
        finalAnswers.set(locationOfCorrectAnswer, correctAnswer);
    }

    private int allOperations(boolean isEasy) {
        switch (new Random().nextInt(4)) {
            case 1: return subtraction(isEasy);
            case 2: return multiplication(isEasy);
            case 3: return division(isEasy);
            default: return addition(isEasy);
        }
    }

    private int division(boolean isEasy) {
        if (isEasy) {
            min = 1;
            max = 30;
        }  else {
            min = 1;
            max = 70;
        }
        determineAB(false);
        if (a >= b) {
            sumTv.setText(a + " / " + b);
            return a / b;
        } else {
            sumTv.setText(b + " / " + a);
            return b / a;
        }
    }

    private int multiplication(boolean isEasy) {
        if (isEasy) {
            min = 0;
            max = 6;
        }  else {
            min = 12;
            max = 18;
        }
        determineAB(true);
        sumTv.setText(a + " x " + b);
        return a * b;
    }

    private int subtraction(boolean isEasy) {
        if (isEasy) {
            min = 0;
            max = 30;
        }  else {
            min = 40;
            max = 70;
        }
        determineAB(true);
        if (a > b) {
            sumTv.setText(a + " - " + b);
            return a - b;
        } else {
            sumTv.setText(b + " - " + a);
            return b - a;
        }
    }

    private int addition(boolean isEasy) {
        if (isEasy) {
            min = 0;
            max = 20;
        }  else {
            min = 40;
            max = 60;
        }
        determineAB(true);
        sumTv.setText(a + " + " + b);
        return a + b;
    }

    private void determineAB(boolean notDivision) {
        if (notDivision) {
            a = ThreadLocalRandom.current().nextInt(min, max);
            b = ThreadLocalRandom.current().nextInt(min, max);
        } else {
            do {
                determineAB(true);
            } while (a % b != 0 && b % a != 0);
        }
    }
}


