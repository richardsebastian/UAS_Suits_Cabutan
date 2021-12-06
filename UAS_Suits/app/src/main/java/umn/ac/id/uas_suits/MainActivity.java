package umn.ac.id.uas_suits;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import umn.ac.id.uas_suits.Adapter.BestSuitAdapter;
import umn.ac.id.uas_suits.Adapter.PopularSuitAdapter;
import umn.ac.id.uas_suits.Modal.BestSuit;
import umn.ac.id.uas_suits.Modal.PopularSuit;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, bestRecycler;
    PopularSuitAdapter popularSuitAdapter;
    BestSuitAdapter bestSuitAdapter;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //datepicker order detail


        //dumy to out model class
        List<PopularSuit> popularSuitList = new ArrayList<>();

        popularSuitList.add(new PopularSuit(" Classic Suit's", "$2.00", R.drawable.suit1));
        popularSuitList.add(new PopularSuit(" Blue Suit's", "$1.00", R.drawable.shelby));
        popularSuitList.add(new PopularSuit(" Dark Suit's", "$3.00", R.drawable.suit3));
        popularSuitList.add(new PopularSuit(" Classic Suit's", "$2.00", R.drawable.suit1));
        popularSuitList.add(new PopularSuit(" Blue Suit's", "$1.00", R.drawable.shelby));
        popularSuitList.add(new PopularSuit(" Dark Suit's", "$3.00", R.drawable.suit3));

        setPopularRecycler(popularSuitList);

        List<BestSuit> bestSuitList = new ArrayList<>();
        bestSuitList.add(new BestSuit("Shelby suit's", "$3.00", R.drawable.shelby, "4.5", "zarah"));
        bestSuitList.add(new BestSuit("Skinny suit's", "$3.00", R.drawable.black, "4.5", "HnM"));
        bestSuitList.add(new BestSuit("Batik suit's", "$3.00", R.drawable.jasbatik, "4.5", "pnb"));
        bestSuitList.add(new BestSuit("ninety suit's", "$3.00", R.drawable.ninety, "4.5", "batikeras"));

        setBestRecycler(bestSuitList);
    }

    private void setPopularRecycler(List<PopularSuit> popularSuitList){

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        popularRecycler.setLayoutManager(layoutManager);
        popularSuitAdapter = new PopularSuitAdapter(this, popularSuitList);
        popularRecycler.setAdapter(popularSuitAdapter);
    }

    private void setBestRecycler(List<BestSuit> bestSuitList){

        bestRecycler = findViewById(R.id.best_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        bestRecycler.setLayoutManager(layoutManager);
        bestSuitAdapter = new BestSuitAdapter(this, bestSuitList);
        bestRecycler.setAdapter(bestSuitAdapter);
    }

}
