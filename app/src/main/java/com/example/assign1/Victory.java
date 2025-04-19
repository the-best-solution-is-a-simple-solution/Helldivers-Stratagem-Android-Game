package com.example.assign1;

import static com.example.assign1.eComboState.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Victory extends AppCompatActivity
{
    private TextView tvScoreSummary;
    private ImageButton btnPlayAgain;
    private List<Combo> combosList;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        this.tvScoreSummary = this.findViewById(R.id.tvScoreSummary);
        this.combosList = new ArrayList<>();

        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null)
        {
            final int TOTAL_COMBOS = bundle.getInt("totalCombos");

            for (int i = 0; i < TOTAL_COMBOS; i++)
            {
                combosList.add((Combo) bundle.getSerializable("combo" + i));
            }

        this.tvScoreSummary.setText(String.format("You have successfully completed %d combos out of a total of %d combos.",
                        this.combosList.stream().filter(x -> x.getComboState().equals(SUCCESSFUL)).count(),
                        this.combosList.size()));
        }

        this.btnPlayAgain = this.findViewById(R.id.btnPlayAgain);

        this.btnPlayAgain.setOnClickListener(x -> this.finish());

    }
}