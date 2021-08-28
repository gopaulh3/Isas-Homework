package com.gopaulh.isamathone;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class Algebra extends AppCompatActivity {
    String[] letters = {"x", "y", "z"};
    ArrayList<String> answers = new ArrayList<>(4);
    LinkedHashSet<String> answers2 = new LinkedHashSet<>();
    String finalStr = "",x,fe,se,te,ft="",fst,fs,operation;
    int locationOfCorrectAnswer;
    Button b0,b1,b2,b3;
    TextView sumTV;
    boolean bool;

    public Algebra(Button b0, Button b1, Button b2, Button b3, TextView sumTV) {
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.sumTV = sumTV;
    }

    public int whichOps(boolean isEasy) {
        bool = false;
        operation = " + ";
        locationOfCorrectAnswer = new Random().nextInt(4);
        ft = ""; fst = "";

        fe = new Random().nextInt(10) + 1 + letters[new Random().nextInt(3)];
        se = new Random().nextInt(10) + 1 + letters[new Random().nextInt(3)];
        te = new Random().nextInt(10) + 1 + letters[new Random().nextInt(3)];
        answers.clear();
        answers2.clear();
        finalStr = fe + operation + se + operation + te;

        if (!isEasy) {
            operation = " - ";
            finalStr = fe + operation + se + operation + te;
            se = "-"+se;
            te = "-"+te;
        }

        sumTV.setText(finalStr);
        fs = checkWhether(fe,se,te,isEasy);

        if (fs.isEmpty()) {
            ft = checkWhether(fe,te,se,isEasy);
        } else {
            fst = checkWhether(fs,te,"",isEasy);
        }
        if (ft.isEmpty() && fst.isEmpty()) {
            checkWhether(se,te,fe,isEasy);
        }

        while (answers2.size() != 4) {
            if (bool) {
                x = finalStr.replace(finalStr.substring(finalStr.length() - 1), letters[new Random().nextInt(3)]);
            } else {
                x = ((new Random().nextInt(15) + 1) + letters[new Random().nextInt(3)] + operation +
                        (new Random().nextInt(15) + 1) + letters[new Random().nextInt(3)]);
            }
            if (!x.equals(finalStr)) answers2.add(x);
            bool = !bool;
        }
        answers = new ArrayList<>(answers2);
        answers.set(locationOfCorrectAnswer, finalStr);

        b0.setText(answers.get(0));
        b1.setText(answers.get(1));
        b2.setText(answers.get(2));
        b3.setText(answers.get(3));

        return locationOfCorrectAnswer;

    }

    private String checkWhether(String fe, String se, String te, boolean isEasy) {
        if (isEasy) {
            return checkAdd(fe,se,te);
        } else {
            return checkSubtract(fe,se,te);
        }
    }

    public String checkAdd(String s1, String s2, String s3) {
        String addString = "";
        if (s1.substring(s1.length() - 1).equals(s2.substring(s2.length() - 1))) {
            addString = Integer.parseInt(s1.substring(0, s1.length() - 1)) + Integer.parseInt(s2.substring(0, s2.length() - 1)) + s2.substring(s2.length() - 1);
            finalStr = s3.isEmpty() ? addString : addString + " + " + s3;
        }
        return addString;
    }

    public String checkSubtract(String s1, String s2, String s3) {
        String addString = "";
        if (s1.substring(s1.length() - 1).equals(s2.substring(s2.length() - 1))) {
            int s = Integer.parseInt(s1.substring(0, s1.length() - 1)) + Integer.parseInt(s2.substring(0, s2.length() - 1));
            addString = s == 0 ? "" : s + s2.substring(s2.length() - 1);

            if (s3.isEmpty()) {
                finalStr = addString;
            } else {
                if (Integer.parseInt(s3.substring(0, s3.length() - 1))<0) {
                    finalStr = addString + " - " + s3.replace("-","");
                } else if (s<0) {
                    finalStr = s3 + " - " + addString.replace("-","");
                } else {
                    finalStr = addString + " + " + s3;
                }
            }
        }
        return addString;
    }
}
