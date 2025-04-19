package com.example.assign1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.assign1.MainActivity.ComboAdapter.IMAGE_SIZE;
import static com.example.assign1.eDirection.*;
import static com.example.assign1.eComboState.*;

public class ClickyActivity extends AppCompatActivity
{
    private ImageView btnUp, btnDown, btnLeft, btnRight;
    private int nIndex;
    private List<ImageView> lstDisplayedCombos;
    private Combo comboSentOver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        this.comboSentOver = (Combo) this.getIntent().getSerializableExtra("combo");

        if (this.comboSentOver != null)
        {
            displayDirections(this.comboSentOver.getCombinations());
        }
        TextView tvClickyTitle = findViewById(R.id.tvClickyTitle);
        tvClickyTitle.setText(this.comboSentOver.getTitle());

        nIndex = 0;
        this.btnUp = this.findViewById(R.id.btnUp);
        this.btnDown = this.findViewById(R.id.btnDown);
        this.btnLeft = this.findViewById(R.id.btnLeft);
        this.btnRight = this.findViewById(R.id.btnRight);

        btnUp.setOnClickListener(x -> onArrowPressed(UP, nIndex++));
        btnDown.setOnClickListener(x -> onArrowPressed(DOWN, nIndex++));
        btnLeft.setOnClickListener(x -> onArrowPressed(LEFT, nIndex++));
        btnRight.setOnClickListener(x -> onArrowPressed(RIGHT, nIndex++));

    }

    private void displayDirections(List<eDirection> eDirections)
    {
        lstDisplayedCombos = new ArrayList<>();
        LinearLayout layout = findViewById(R.id.arrowDisplayLayout);


        eDirections.forEach(x -> {
            ImageView arrow = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(IMAGE_SIZE, IMAGE_SIZE);
            arrow.setLayoutParams(params);
            switch (x)
            {
                case UP:
                    arrow.setImageResource(R.drawable.up);
                    break;
                case DOWN:
                    arrow.setImageResource(R.drawable.down);
                    break;
                case LEFT:
                    arrow.setImageResource(R.drawable.left);
                    break;
                case RIGHT:
                    arrow.setImageResource(R.drawable.right);
                    break;
            }
            lstDisplayedCombos.add(arrow);
            layout.addView(arrow);
        });
    }


    /**
     * After an arrow is pressed for the first time, this should replace the current index's image
     * with either a yellow star for a correct arrow press, and grey stay for incorrect arrow press
     * @param eDirection passed in the current button pressed
     * @param index current step that we on in the current combo attempt
     */
    public void onArrowPressed(eDirection eDirection, int index)
    {
        //this is to handle the app crashing if the user presses buttons too fast, it would
        //otherwise allow for an additional button press and cause a crash
        if (index >= this.comboSentOver.getCombinations().size())
        {
            return;
        }

        ImageView currComboStep = lstDisplayedCombos.get(index);
        if (this.comboSentOver.getCombinations().get(index).equals(eDirection))
        {
            currComboStep.setImageResource(android.R.drawable.btn_star_big_on);
            currComboStep.setTag(SUCCESSFUL);
        }
        else
        {
            currComboStep.setImageResource(android.R.drawable.btn_star_big_off);
            currComboStep.setTag(FAILED);
        }

        //checks if all inputs for arrows are complete, should only fire after final combo button
        // is pressed
        if (index >= this.comboSentOver.getCombinations().size()-1)
        {
            determineSuccess();
        }
    }


    /**
     * determine if the user has successfully completed the game i.e if all gold stars
     * will cycle through all combos the user has submitted and check if success tag was set on all
     */
    public void determineSuccess()
    {
        boolean bUserSuccess = this.lstDisplayedCombos.stream().allMatch(x -> x.getTag().equals(SUCCESSFUL));

        //Toast.makeText(this, bUserSuccess + "", Toast.LENGTH_LONG).show();

        Intent data = new Intent();
        data.putExtra("index", getIntent().getIntExtra("index", -1));
        data.putExtra("result", bUserSuccess ? SUCCESSFUL : FAILED);
        setResult(RESULT_OK, data);
        finish();

    }

}