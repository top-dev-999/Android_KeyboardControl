package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView mRecycleview, mRecycleview1;
    private List<ItemAdapter> mList = new ArrayList<>();
    private List<ItemAdapter> mList1 = new ArrayList<>();
    private ListAdapter mAdapter, mAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addList();
        adapter();
    }
    private void init(){
        mRecycleview = findViewById(R.id.listView);
        mRecycleview1 = findViewById(R.id.listView1);
    }
    private void addList() {
        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.image1);
        itemAdapter.setText("Flower 1");
        mList.add(itemAdapter);
        ItemAdapter itemAdapter1 = new ItemAdapter();
        itemAdapter1.setImage(R.drawable.image2);
        itemAdapter1.setText("Flower 2");
        mList.add(itemAdapter1);
        ItemAdapter itemAdapter2 = new ItemAdapter();
        itemAdapter2.setImage(R.drawable.image3);
        itemAdapter2.setText("Flower 3");
        mList.add(itemAdapter2);
        ItemAdapter itemAdapter3 = new ItemAdapter();
        itemAdapter3.setText("Flower 4");
        itemAdapter3.setImage(R.drawable.image4);
        mList.add(itemAdapter3);
        ItemAdapter itemAdapter4 = new ItemAdapter();
        itemAdapter4.setText("Flower 5");
        itemAdapter4.setImage(R.drawable.image5);
        mList.add(itemAdapter4);
        ItemAdapter itemAdapter5 = new ItemAdapter();
        itemAdapter5.setText("Flower 6");
        itemAdapter5.setImage(R.drawable.image6);
        mList.add(itemAdapter5);
        ItemAdapter itemAdapter6 = new ItemAdapter();
        itemAdapter6.setText("Flower 7");
        itemAdapter6.setImage(R.drawable.image7);
        mList.add(itemAdapter6);
        ItemAdapter itemAdapter7 = new ItemAdapter();
        itemAdapter7.setText("Flower 8");
        itemAdapter7.setImage(R.drawable.image8);
        mList1.add(itemAdapter7);
        ItemAdapter itemAdapter8 = new ItemAdapter();
        itemAdapter8.setText("Flower 9");
        itemAdapter8.setImage(R.drawable.image9);
        mList1.add(itemAdapter8);
        ItemAdapter itemAdapter9 = new ItemAdapter();
        itemAdapter9.setText("Flower 10");
        itemAdapter9.setImage(R.drawable.image10);
        mList1.add(itemAdapter9);
        mList1.add(itemAdapter);
    }
    private void adapter(){
        mAdapter = new ListAdapter(mList, this);
        mAdapter.setSelected(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter1 = new ListAdapter(mList1, this);
        mAdapter1.setSelected(false);
        mRecycleview1.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview1.setAdapter(mAdapter1);
        mAdapter1.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mAdapter.setSelected(true);
                mAdapter.notifyDataSetChanged();
                mAdapter1.setSelected(false);
                mAdapter1.notifyDataSetChanged();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mAdapter1.setSelected(true);
                mAdapter1.notifyDataSetChanged();
                mAdapter.setSelected(false);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                return super.onKeyUp(keyCode, event);
        }
        return false;
    }
}