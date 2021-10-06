package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ItemAdapter> mList;
    private Context mContext;
    private int mSelectedItem = 0;
    private boolean mSelected ;
    public ListAdapter(List<ItemAdapter> list, Context context){
        super();
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_custome, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ItemAdapter itemAdapter = mList.get(position);
        if(mSelectedItem==position)
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#47CA4C"));
        else
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        ((ViewHolder) viewHolder).mImg.setImageResource(itemAdapter.getImage());
        ((ViewHolder) viewHolder).mImgName.setText(itemAdapter.getText());
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                // Return false if scrolled to the bounds and allow focus to move off the list
                if (mSelected){
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                            return tryMoveSelection(lm, 1);
                        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                            return tryMoveSelection(lm, -1);
                        }
                    }
                }
                return false;
            }
        });
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int nextSelectItem = mSelectedItem + direction;
        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (nextSelectItem >= 0 && nextSelectItem < getItemCount()) {
            notifyItemChanged(mSelectedItem);
            mSelectedItem = nextSelectItem;
            notifyItemChanged(mSelectedItem);
            lm.scrollToPosition(mSelectedItem);
            return true;
        }

        return false;
    }

    public void setSelected(boolean selected){
        mSelected = selected;
        if ( mSelected == false)
        mSelectedItem = -1;
        else
            mSelectedItem= 0;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImg;
        public TextView mImgName;
        public ViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img_item);
            mImgName = (TextView)itemView.findViewById(R.id.img_name);
        }
    }
}
