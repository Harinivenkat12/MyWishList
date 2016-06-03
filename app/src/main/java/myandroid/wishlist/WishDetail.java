package myandroid.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.dbHandler;
import myandroid.mywishlist.DisplayWishes;
import writetoandreadfromfile.myandroid.achitu.mywishlist.R;

public class WishDetail extends AppCompatActivity {

    private TextView title, date, context;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wish_detail);

        title = (TextView) findViewById(R.id.wishHeadingId);
        date = (TextView) findViewById(R.id.dateCreatedId);
        context = (TextView) findViewById(R.id.textContentId);

        deleteButton=(Button)findViewById(R.id.deleteButton);



        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            title.setText(extras.getString("title"));
            date.setText("Created : " + extras.getString("date"));
            context.setText("\"" + extras.getString("context") + "\"");

            final int id= extras.getInt("id");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHandler db = new dbHandler(getApplicationContext());
                    db.deleteWish(id);

                    Toast.makeText(getApplicationContext(),"Wish deleted!" ,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(), DisplayWishes.class));
                }
            });

        }

    }

}
