package myandroid.mywishlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.dbHandler;
import model.MyWish;


import myandroid.wishlist.WishDetail;
import writetoandreadfromfile.myandroid.achitu.mywishlist.R;

public class DisplayWishes extends AppCompatActivity {
    private dbHandler dba;
    private ArrayList<MyWish> myWish = new ArrayList<>();
    private WishAdapter wishAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wishes);

        listView = (ListView) findViewById(R.id.displayWishesId);
        refreshData();

    }

    private void refreshData() {

        myWish.clear();
        dba = new dbHandler(getApplicationContext());

        ArrayList<MyWish> wish = dba.getWishes();

        for (int i = 0; i < wish.size(); i++) {

            String title = wish.get(i).getTitle();
            String content = wish.get(i).getContent();
            String recordDate = wish.get(i).getRecordDate();

            MyWish wishObj = new MyWish();
            wishObj.setContent(content);
            wishObj.setRecordDate(recordDate);
            wishObj.setTitle(title);

            myWish.add(wishObj);

        }

        dba.close();

        //setup adapter

        wishAdapter = new WishAdapter(DisplayWishes.this, R.layout.content_wish_row, myWish);
        listView.setAdapter(wishAdapter);

        wishAdapter.notifyDataSetChanged();

    }

    public class WishAdapter extends ArrayAdapter<MyWish> {

        Activity activity;
        int layoutResource;
        ArrayList<MyWish> mData = new ArrayList<>();

        public WishAdapter(Activity act, int resource, ArrayList<MyWish> data) {
            super(act, resource, data);

            activity = act;
            layoutResource = resource;
            mData = data;

            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public MyWish getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getPosition(MyWish item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null || convertView.getTag() == null) {

                LayoutInflater inflater = LayoutInflater.from(activity);
                convertView=inflater.inflate(layoutResource, null);

                holder = new ViewHolder();
                holder.mTitle = (TextView) convertView.findViewById(R.id.wishTitleId);
                holder.mDate = (TextView) convertView.findViewById(R.id.dateId);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.myWishb = getItem(position);

            holder.mTitle.setText(holder.myWishb.getTitle());
            holder.mDate.setText(holder.myWishb.getRecordDate());


            final ViewHolder finalHolder=holder;
            holder.mTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text= finalHolder.myWishb.getContent().toString();
                    String dateText= finalHolder.myWishb.getRecordDate().toString();
                    String title= finalHolder.myWishb.getTitle().toString();

                    Intent i= new Intent(DisplayWishes.this, WishDetail.class);
                    i.putExtra("context",text);
                    i.putExtra("date",dateText);
                    i.putExtra("title",title);

                    startActivity(i);
                }
            });


            return convertView;
        }

        public class ViewHolder {

            MyWish myWishb;
            TextView mTitle;
            TextView mContent;
            TextView mDate;
        }
    }

}
