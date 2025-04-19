package com.example.assign1;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ViewHolder>
{
    private List<Combo> combos;
    public static final int IMAGE_SIZE = 100;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Combo combo = combos.get(position);
        holder.tvComboTitle.setText(combo.getTitle());

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


        /*
           TODO
            this is to set the background color of the combos, depending on whether the user has
            previously failed or successfully completed them, or not attempted yet
        */
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
                Intent intent = new Intent(view.getContext(), ClickyActivity.class);
                intent.putExtra("combo", combo);
                view.getContext().startActivity(intent);
            }
        }
    }
}
