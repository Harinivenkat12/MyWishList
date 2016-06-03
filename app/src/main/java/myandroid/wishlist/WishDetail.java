package myandroid.wishlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import writetoandreadfromfile.myandroid.achitu.mywishlist.R;

public class WishDetail extends AppCompatActivity {

    private TextView title, date, context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wish_detail);

        title = (TextView) findViewById(R.id.wishHeadingId);
        date = (TextView) findViewById(R.id.dateCreatedId);
        context = (TextView) findViewById(R.id.textContentId);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            title.setText(extras.getString("title"));
            date.setText("Created : " + extras.getString("date"));
            context.setText("\"" + extras.getString("context") + "\"");

        }

    }

}
