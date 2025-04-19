package com.example.assign1;

import static com.example.assign1.eComboState.*;
import static com.example.assign1.eDirection.*;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.*;
import java.util.*;


public class MainActivity extends AppCompatActivity
{

    private static List<Combo> combos;
    private RecyclerView recycler;
    private ComboAdapter adapter;
    private TextView tvCorrectCombos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        combos = this.initializeCombinations();
        this.adapter = new ComboAdapter(combos);
        this.recycler = (RecyclerView) this.findViewById(R.id.recycler);
        this.recycler.setAdapter(this.adapter);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        this.recycler.setLayoutManager(manager);
        this.recycler.addItemDecoration(new DividerItemDecoration(
                this.recycler.getContext(), manager.getOrientation()
        ));

        findViewById(R.id.btnRestart).setOnClickListener(x-> resetCombos());

        this.tvCorrectCombos = findViewById(R.id.tvCorrectCombos);

    }

    private void resetCombos()
    {
        combos.forEach(x -> x.setComboState(NOT_ATTEMPTED));
        Collections.shuffle(combos);
        this.tvCorrectCombos.setText("Correct Combos: 0");
        adapter.notifyDataSetChanged();
    }


    /**
     * Hardcoded list of combinations we will display, can be changed later to be randomized using
     * the directional constants
     * @return List of combinations in a shuffled order
     */
    private List<Combo> initializeCombinations()
    {
        //if combos already exist keep data(win loss background)
        if (combos != null)
        {
            return combos;
        }
        List<Combo> lstReturn = new ArrayList<>();


        //supply stratagems
        lstReturn.add(new Combo("LIFT-850 Jump Pack", Arrays.asList(DOWN, UP, UP, DOWN, UP)));
        lstReturn.add(new Combo("B-1 Supply Pack", Arrays.asList(DOWN, LEFT, DOWN, UP, UP, DOWN)));
        lstReturn.add(new Combo("AX/LAS-5 \"Guard Dog\" Rover", Arrays.asList(DOWN, UP, LEFT, UP, RIGHT, RIGHT)));
        lstReturn.add(new Combo("SH-20 Ballistic Shield Backpack", Arrays.asList(DOWN, LEFT, DOWN, DOWN, UP, LEFT)));
        lstReturn.add(new Combo("SH-32 Shield Generator Pack", Arrays.asList(DOWN, UP, LEFT, RIGHT, LEFT, RIGHT)));
        lstReturn.add(new Combo("AX/AR-23 \"Guard Dog\"", Arrays.asList(DOWN, UP, LEFT, UP, RIGHT, DOWN)));


        //support weapons
        lstReturn.add(new Combo("MG-43 Machine Gun", Arrays.asList(DOWN, LEFT, DOWN, UP, RIGHT)));
        lstReturn.add(new Combo("APW-1 Anti-Materiel Rifle", Arrays.asList(DOWN, LEFT, RIGHT, UP, DOWN)));
        lstReturn.add(new Combo("M-105 Stalwart", Arrays.asList(DOWN, LEFT, DOWN, UP, UP, LEFT)));
        lstReturn.add(new Combo("EAT-17 Expendable Anti-tank", Arrays.asList(DOWN, DOWN, LEFT, UP, RIGHT)));
        lstReturn.add(new Combo("GR-8 Recoilless Rifle", Arrays.asList(DOWN, LEFT, RIGHT, RIGHT, LEFT)));
        lstReturn.add(new Combo("FLAM-40 Flamethrower", Arrays.asList(DOWN, LEFT, UP, DOWN, UP)));
        lstReturn.add(new Combo("AC-8 Autocannon", Arrays.asList(DOWN, LEFT, DOWN, UP, UP, RIGHT)));
        lstReturn.add(new Combo("MG-206 Heavy Machine Gun", Arrays.asList(DOWN, LEFT, UP, DOWN, DOWN)));
        lstReturn.add(new Combo("RS-422 Railgun", Arrays.asList(DOWN, RIGHT, DOWN, UP, LEFT, RIGHT)));
        lstReturn.add(new Combo("FAF-14 SPEAR Launcher", Arrays.asList(DOWN, DOWN, UP, DOWN, DOWN)));
        lstReturn.add(new Combo("GL-21 Grenade Launcher", Arrays.asList(DOWN, LEFT, UP, LEFT, DOWN)));
        lstReturn.add(new Combo("LAS-98 Laser Cannon", Arrays.asList(DOWN, LEFT, DOWN, UP, LEFT)));
        lstReturn.add(new Combo("ARC-3 Arc Thrower", Arrays.asList(DOWN, RIGHT, DOWN, UP, LEFT, LEFT)));
        lstReturn.add(new Combo("LAS-99 Quasar Cannon", Arrays.asList(DOWN, DOWN, UP, LEFT, RIGHT)));
        lstReturn.add(new Combo("RL-77 Airburst Rocket Launcher", Arrays.asList(DOWN, UP, UP, LEFT, RIGHT)));


        //supply other
        lstReturn.add(new Combo("EXO-45 Patriot Exosuit", Arrays.asList(LEFT, DOWN, RIGHT, UP, LEFT, DOWN, DOWN)));


        //mission stratagems
        lstReturn.add(new Combo("Reinforce", Arrays.asList(UP, DOWN, RIGHT, LEFT, UP)));
        lstReturn.add(new Combo("SOS Beacon", Arrays.asList(UP, DOWN, RIGHT, LEFT)));
        lstReturn.add(new Combo("Resupply", Arrays.asList(DOWN, DOWN, UP, RIGHT)));
        lstReturn.add(new Combo("NUX-223 Hellbomb", Arrays.asList(DOWN, UP, LEFT, DOWN, UP, RIGHT, DOWN, UP)));
        lstReturn.add(new Combo("SSSD Delivery", Arrays.asList(DOWN, DOWN, DOWN, UP, UP)));
        lstReturn.add(new Combo("Seismic Probe", Arrays.asList(UP, UP, LEFT, RIGHT, DOWN, DOWN)));
        lstReturn.add(new Combo("Upload Data", Arrays.asList(LEFT, RIGHT, UP, UP, UP)));
        lstReturn.add(new Combo("Eagle Rearm", Arrays.asList(UP, UP, LEFT, UP, RIGHT)));
        lstReturn.add(new Combo("Illumination Flare", Arrays.asList(RIGHT, RIGHT, LEFT, LEFT)));
        lstReturn.add(new Combo("SEAF Artillery", Arrays.asList(RIGHT, UP, UP, DOWN)));
        lstReturn.add(new Combo("Super Earth Flag", Arrays.asList(DOWN, UP, DOWN, UP)));


        //defensive stratagems
        lstReturn.add(new Combo("E/MG-101 HMG Emplacement", Arrays.asList(DOWN, UP, LEFT, RIGHT, RIGHT, LEFT)));
        lstReturn.add(new Combo("FX-12 Shield Generator Relay", Arrays.asList(DOWN, DOWN, LEFT, RIGHT, LEFT, RIGHT)));
        lstReturn.add(new Combo("A/ARC-3 Tesla Tower", Arrays.asList(DOWN, UP, RIGHT, UP, LEFT, RIGHT)));
        lstReturn.add(new Combo("MD-6 Anti-Personnel Minefield", Arrays.asList(DOWN, LEFT, UP, RIGHT)));
        lstReturn.add(new Combo("MD-I4 Incendiary Mines", Arrays.asList(DOWN, LEFT, LEFT, DOWN)));
        lstReturn.add(new Combo("A/MG-43 Machine Gun Sentry", Arrays.asList(DOWN, UP, RIGHT, RIGHT, UP)));
        lstReturn.add(new Combo("A/G-16 Gatling Sentry", Arrays.asList(DOWN, UP, RIGHT, LEFT)));
        lstReturn.add(new Combo("A/M-12 Mortar Sentry", Arrays.asList(DOWN, UP, RIGHT, RIGHT, DOWN)));
        lstReturn.add(new Combo("A/AC-8 Autocannon Sentry", Arrays.asList(DOWN, UP, RIGHT, UP, LEFT, UP)));
        lstReturn.add(new Combo("tA/MLS-4X Rocket Sentry", Arrays.asList(DOWN, UP, RIGHT, RIGHT, LEFT)));
        lstReturn.add(new Combo("A/M-23 EMS Mortar Sentry", Arrays.asList(DOWN, UP, RIGHT, DOWN, RIGHT)));


        //offensive stratagems - orbital
        lstReturn.add(new Combo("Orbital Gatling Barrage", Arrays.asList(RIGHT, DOWN, LEFT, UP, UP)));
        lstReturn.add(new Combo("Orbital Airburst Strike", Arrays.asList(RIGHT, RIGHT, RIGHT)));
        lstReturn.add(new Combo("Orbital 120MM HE Barrage", Arrays.asList(RIGHT, RIGHT, DOWN, LEFT, RIGHT, DOWN)));
        lstReturn.add(new Combo("Orbital 380MM HE Barrage", Arrays.asList(RIGHT, DOWN, UP, UP, LEFT, DOWN, DOWN)));
        lstReturn.add(new Combo("Orbital Walking Barrage", Arrays.asList(RIGHT, DOWN, RIGHT, DOWN, RIGHT, DOWN)));
        lstReturn.add(new Combo("Orbital Laser", Arrays.asList(RIGHT, DOWN, UP, RIGHT, DOWN)));
        lstReturn.add(new Combo("Orbital Railcannon Strike", Arrays.asList(RIGHT, UP, DOWN, DOWN, RIGHT)));
        lstReturn.add(new Combo("Orbital Precision Strike", Arrays.asList(RIGHT, RIGHT, UP)));
        lstReturn.add(new Combo("Orbital Gas Strike", Arrays.asList(RIGHT, RIGHT, DOWN, RIGHT)));
        lstReturn.add(new Combo("Orbital EMS Strike", Arrays.asList(RIGHT, RIGHT, LEFT, DOWN)));
        lstReturn.add(new Combo("Orbital Smoke Strike", Arrays.asList(RIGHT, RIGHT, DOWN, UP)));


        //offensive stratagems - eagle
        lstReturn.add(new Combo("Eagle Strafing Run", Arrays.asList(UP, RIGHT, RIGHT)));
        lstReturn.add(new Combo("Eagle Airstrike", Arrays.asList(UP, RIGHT,DOWN, RIGHT)));
        lstReturn.add(new Combo("Eagle Cluster Bomb", Arrays.asList(UP, RIGHT, DOWN, DOWN, RIGHT)));
        lstReturn.add(new Combo("Eagle Napalm Airstrike", Arrays.asList(UP, RIGHT, DOWN, UP)));
        lstReturn.add(new Combo("Eagle Smoke Strike", Arrays.asList(UP, RIGHT, UP, DOWN)));
        lstReturn.add(new Combo("Eagle 110MM Rocket Pods", Arrays.asList(UP, RIGHT, UP, LEFT)));
        lstReturn.add(new Combo("Eagle 500kg Bomb", Arrays.asList(UP, LEFT, DOWN, DOWN, DOWN)));

        Collections.shuffle(lstReturn);
        return lstReturn;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            final int INDEX = data.getIntExtra("index",-1);
            eComboState result = (eComboState) data.getSerializableExtra("result");

            if (INDEX != -1)
            {
                combos.get(INDEX).setComboState(result);
                adapter.notifyItemChanged(INDEX);
            }
        }

        countSuccessfulCombos();

        //if all combos have been attempted
        if (combos.stream().noneMatch(x -> x.getComboState().equals(NOT_ATTEMPTED)))
        {
            Intent intent = new Intent(this, Victory.class);
            intent.putExtra("totalCombos", combos.size());
            for (int i = 0; i < combos.size(); i++)
            {
                intent.putExtra("combo" + i, combos.get(i));
            }
            startActivity(intent);
        }
    }

    private void countSuccessfulCombos()
    {
        final int WINS = (int) combos.stream().filter(x -> x.getComboState().equals(SUCCESSFUL)).count();
        this.tvCorrectCombos.setText("Correct Combos: " + WINS);
    }

    class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ViewHolder>
    {
        private List<Combo> combos;
        public static final int IMAGE_SIZE = 120;

        public ComboAdapter(List<Combo> combos)
        {
            this.combos = combos;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ComboAdapter.ViewHolder holder, int position)
        {
            Combo combo = combos.get(position);
            holder.tvComboTitle.setText(combo.getTitle());

            holder.arrowLayout.removeAllViews(); // clear so they dont keep staking

            //loop through each arrow that make up a single COMBO and then correctly set its image
            combo.getCombinations().forEach(x -> {

                ImageView arrow = new ImageView(holder.arrowLayout.getContext());

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

                holder.arrowLayout.addView(arrow, IMAGE_SIZE, IMAGE_SIZE);
            });

            switch (combo.getComboState())
            {
                case NOT_ATTEMPTED:
                    holder.backgroundLayout.setBackgroundColor(Color.parseColor("teal"));
                    break;
                case SUCCESSFUL:
                    holder.backgroundLayout.setBackgroundColor(Color.parseColor("green"));
                    break;
                case FAILED:
                    holder.backgroundLayout.setBackgroundColor(Color.parseColor("red"));
                    break;
            }
        }

        @Override
        public int getItemCount()
        {
            return combos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {
            private TextView tvComboTitle;
            private LinearLayout arrowLayout;
            private LinearLayout backgroundLayout;
            public ViewHolder(@NonNull View itemView)
            {
                super(itemView);
                this.tvComboTitle = itemView.findViewById(R.id.tvComboTitle);
                this.arrowLayout = itemView.findViewById(R.id.arrowLayout);
                this.backgroundLayout = itemView.findViewById(R.id.linLayoutCombo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view)
            {
                final int POSITION = getAdapterPosition();
                if (POSITION != RecyclerView.NO_POSITION)
                {
                    Combo combo = combos.get(POSITION);
                    if (combo.getComboState().equals(NOT_ATTEMPTED))
                    {
                        Intent intent = new Intent(view.getContext(), ClickyActivity.class);
                        intent.putExtra("combo", combo);
                        intent.putExtra("index", POSITION);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        }
    }
}