package com.orcunozsen.mylogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TouristPlacesActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private LinearLayout cityListLayout;

    public TouristPlacesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        cityListLayout = findViewById(R.id.cityListLayout);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString().toLowerCase();
                filterCities(searchText);
            }
        });
    }

    private void filterCities(String searchText) {
        for (int i = 0; i < cityListLayout.getChildCount(); i++) {
            View cityView = cityListLayout.getChildAt(i);
            if (cityView instanceof LinearLayout) {
                LinearLayout cityLayout = (LinearLayout) cityView;
                TextView cityNameTextView = (TextView) cityLayout.getChildAt(0);
                String cityName = cityNameTextView.getText().toString().toLowerCase();
                if (cityName.contains(searchText)) {
                    cityLayout.setVisibility(View.VISIBLE);
                } else {
                    cityLayout.setVisibility(View.GONE);
                }
            }
        }
    }
}