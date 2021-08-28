package com.gopaulh.isamathone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStuff();
    }
   private void initStuff() {
       getSupportActionBar().setDisplayOptions(androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM);
       getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        String[] s = {"All operations", "Addition", "Multiplication", "Subtraction", "Division", "Rounding", "Algebra"};
        Integer[] i = {R.drawable.allopsd, R.drawable.addd, R.drawable.multb, R.drawable.sube, R.drawable.div, R.drawable.rda,R.drawable.alc};
        RecyclerView recyclerView = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, Arrays.asList(s), Arrays.asList(i));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(myAdapter);
    }
}