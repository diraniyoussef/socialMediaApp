package com.dirani.youtubeapi.Recycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dirani.youtubeapi.MainActivity;
import com.dirani.youtubeapi.R;
import java.util.ArrayList;

public class RecyclerMain extends AppCompatActivity {
    ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);
        createExampleList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

    }

    public void insertItem(int position) {
        if( position > mExampleList.size() ) {
            Toast.makeText(this, "The position you entered is not contiguous !", Toast.LENGTH_LONG).show();
            return;
        }
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android, "New Item At Position" + position, "This is added by user"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if( position >= mExampleList.size() ) {
            Toast.makeText(this, "The position you entered is not contiguous !", Toast.LENGTH_LONG).show();
            return;
        }
        mExampleList.remove(position);
        mRecyclerView.removeViewAt(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ExampleAdapter(mExampleList);
        mAdapter.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mExampleList.get(position).changeText1("clicked !");
                mAdapter.notifyItemChanged(position);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

    }

}