package com.gopaulh.isamathone;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreStorage extends AppCompatActivity {

    SharedPreferences sp;
    int category;
    boolean isEasy;
    String bestScoreLiteralText;
    float bestScore;
    TextView bestScoreTextView;

    public ScoreStorage(SharedPreferences sp, int category, TextView bestScoreTextView, boolean isEasy) {
        this.sp = sp;
        this.category = category;
        this.bestScoreTextView = bestScoreTextView;
        this.isEasy = isEasy;
    }

    public void initialData() {
        if (isEasy) {
            switch (category) {
                case 0: bestScore = sp.getFloat("BestAllOperationsScoreEasy", 0); break;
                case 1: bestScore = sp.getFloat("BestAdditionScoreEasy", 0); break;
                case 2: bestScore = sp.getFloat("BestMultiplicationScoreEasy", 0); break;
                case 3: bestScore = sp.getFloat("BestSubtractionScoreEasy", 0);break;
                case 4: bestScore = sp.getFloat("BestDivisionScoreEasy", 0); break;
                case 5: bestScore = sp.getFloat("BestRoundingScoreEasy", 0); break;
                case 6: bestScore = sp.getFloat("BestAlgebraScoreEasy", 0); break;
                default: Log.i("Info", "Why are there no categories?");
            }
            bestScoreTextView.setText("Best Score (Easy) : "+bestScore);
        } else {
            switch (category) {
                case 0: bestScore = sp.getFloat("BestAllOperationsScoreHard", 0); break;
                case 1: bestScore = sp.getFloat("BestAdditionScoreHard", 0); break;
                case 2: bestScore = sp.getFloat("BestMultiplicationScoreHard", 0); break;
                case 3: bestScore = sp.getFloat("BestSubtractionScoreHard", 0); break;
                case 4: bestScore = sp.getFloat("BestDivisionScoreHard", 0); break;
                case 5: bestScore = sp.getFloat("BestRoundingScoreHard", 0); break;
                case 6: bestScore = sp.getFloat("BestAlgebraScoreHard", 0); break;
                default: Log.i("Info", "Why are there no categories?");
            }
            bestScoreTextView.setText("Best Score (Hard) : "+bestScore);
        }
    }

    public void checkIfUpdateScores(float score) {
        if (isEasy) {
            switch (category) {
                case 0: bestScoreLiteralText = "BestAllOperationsScoreEasy"; break;
                case 1: bestScoreLiteralText = "BestAdditionScoreEasy"; break;
                case 2: bestScoreLiteralText = "BestMultiplicationScoreEasy"; break;
                case 3: bestScoreLiteralText = "BestSubtractionScoreEasy"; break;
                case 4: bestScoreLiteralText = "BestDivisionScoreEasy"; break;
                case 5: bestScoreLiteralText = "BestRoundingScoreEasy"; break;
                case 6: bestScoreLiteralText = "BestAlgebraScoreEasy"; break;
            }
        } else {
            switch (category) {
                case 0: bestScoreLiteralText = "BestAllOperationsScoreHard"; break;
                case 1: bestScoreLiteralText = "BestAdditionScoreHard"; break;
                case 2: bestScoreLiteralText = "BestMultiplicationScoreHard"; break;
                case 3: bestScoreLiteralText = "BestSubtractionScoreHard"; break;
                case 4: bestScoreLiteralText = "BestDivisionScoreHard"; break;
                case 5: bestScoreLiteralText = "BestRoundingScoreHard"; break;
                case 6: bestScoreLiteralText = "BestAlgebraScoreHard"; break;
            }
        }

        if (score > sp.getFloat(bestScoreLiteralText,0)) {
            sp.edit().putFloat(bestScoreLiteralText, score).apply();
        }
    }
}