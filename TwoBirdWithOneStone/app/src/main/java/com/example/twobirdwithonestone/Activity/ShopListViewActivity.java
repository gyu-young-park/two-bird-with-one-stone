package com.example.twobirdwithonestone.Activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twobirdwithonestone.R;

import java.util.ArrayList;

public class ShopListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private ShopListViewAdapter mAdapter;
    @Override
    //listshop과 연결
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listshop);
        mListView = (ListView)findViewById(R.id.listView);
        mAdapter = new ShopListViewAdapter();
        mListView.setAdapter(mAdapter);

        //shop_fragment에서 intent로 전달하는 배열 받기
        Intent intent = getIntent();
        //parcelable로 ArrayList 를 받아 addItem
        final ArrayList<ParcelableItems> list = intent.getParcelableArrayListExtra("culture_list");

        for(int i=0;i<list.size();i++){

            //public void addItem(Bitmap image, String title, String coin) {}
            mAdapter.addItem(BitmapFactory.decodeByteArray(list.get(i).image, 0, list.get(i).image.length),list.get(i).name,list.get(i).price,list.get(i).brand);
        }
        //Shoplistview ->> subshoplistview 클릭하는 position마다 조건달아 지정
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ShopListViewActivity.this,SubShopListViewActivity.class);
                ArrayList<ParcelableItems> coffee_list = new ArrayList<ParcelableItems>();
                //받아온 arraylist의 items 객체 내 가져올 항목 add
                coffee_list.add(new ParcelableItems(list.get(i).image,list.get(i).category,list.get(i).name,list.get(i).price,list.get(i).explanation,list.get(i).brand));
                //parcelable로 arraylist 넘기기
                intent.putParcelableArrayListExtra("coffee_list", coffee_list);
                startActivity(intent);
            }

        });
    }
}
