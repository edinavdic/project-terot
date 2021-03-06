package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.project_terot.terotfirstgame.models.Pantherai;

import java.util.ArrayList;


public class CustomAdapterSelection extends RecyclerView.Adapter<CustomAdapterSelection.ViewHolder> {

    public interface OnAdapterClickListener {
        void onItemClick(int pickCode);
    }
    private OnAdapterClickListener mListener;

    private Context context;
    private ArrayList<Pantherai> pantheraiArrayList;
    Toast toast;

    public CustomAdapterSelection(Context context, ArrayList<Pantherai> pantheraiArrayList, OnAdapterClickListener mListener) {
        this.mListener = mListener;
        this.context = context;
        this.pantheraiArrayList = pantheraiArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item_panth, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(pantheraiArrayList.get(position));

        Pantherai pu = pantheraiArrayList.get(position);
        String spritePath = pu.getDrawablePngPath();
        String buttonColorPath = "button_" + pu.getPantheraiColor().toString().toLowerCase();
        holder.pImageViewSprite.setImageResource(getImageId(context, spritePath));
        holder.pImageViewColor.setImageResource(getImageId(context, buttonColorPath));
        switch (pu.getPantheraiColor()){
            case RED: holder.pType.setText("Taiger"); break;
            case BLUE: holder.pType.setText("Snow Leopaird"); break;
            case GREEN: holder.pType.setText("Jaguair"); break;
            case ORANGE: holder.pType.setText("Leopaird"); break;
            case YELLOW: holder.pType.setText("Laion"); break;
            default: break;
        }
        holder.pStats.setText("STR: " + pu.getStrength() + "\nDEX: " + pu.getDexterity() + "\nINT: " + pu.getIntelligence());

    }

    @Override
    public int getItemCount() {
        return pantheraiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView pImageViewSprite;
        public ImageView pImageViewColor;
        public TextView pType;
        public TextView pStats;

        public ViewHolder(final View itemView) {
            super(itemView);

            pType = (TextView) itemView.findViewById(R.id.textViewType);
            pStats = (TextView) itemView.findViewById(R.id.textViewStats);
            pImageViewColor = (ImageView) itemView.findViewById(R.id.imageViewColor);
            pImageViewSprite = (ImageView) itemView.findViewById(R.id.imageViewSprite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pantherai cpu = (Pantherai) view.getTag();
                    mListener.onItemClick(cpu.getPantheraiColor().ordinal());
                    if(toast != null)
                        toast.cancel();
                    toast = Toast.makeText(view.getContext(), "PickedCol: " + cpu.getPantheraiColor().toString() + " PickedNum: " + cpu.getPantheraiColor().ordinal(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0,0);
                    toast.show();
                }
            });

        }


    }
}
