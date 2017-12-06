package com.example.administrator.zhihulist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        List<String> data = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            data.add("list" + i);
        }

        manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        list.setAdapter(new CommonAdapter<String>(this, R.layout.item, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                if (position > 0 && position % 6 == 0) {
                    holder.setVisible(R.id.title,false);
                    holder.setVisible(R.id.content,false);
                    holder.setVisible(R.id.image,true);
                }else{
                    holder.setVisible(R.id.title,true);
                    holder.setVisible(R.id.content,true);
                    holder.setVisible(R.id.image,false);
                }
            }
        });

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = manager.findFirstVisibleItemPosition();
                int lPos = manager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = manager.findViewByPosition(i);
                    MyImage adImageView = view.findViewById(R.id.image);
                    if (view.findViewById(R.id.image).getVisibility() == View.VISIBLE) {
                        adImageView.setDy(manager.getHeight() - view.getTop());
                    }
                }
            }
        });

    }
}
