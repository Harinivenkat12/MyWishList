package writetoandreadfromfile.myandroid.achitu.mywishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import data.dbHandler;
import model.MyWish;
import myandroid.mywishlist.DisplayWishes;
import myandroid.wishlist.WishDetail;

public class MainActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Button submitButton;
    private dbHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.titleEditTextId);
        content = (EditText) findViewById(R.id.wishId);
        submitButton = (Button) findViewById(R.id.submitBtnId);

        dba = new dbHandler(MainActivity.this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }


        });
    }

    private void saveToDB() {
        MyWish wish = new MyWish();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());

        dba.addWishes(wish);
        dba.close();

        //clear

        title.setText("");
        content.setText("");

        Intent i = new Intent(MainActivity.this, DisplayWishes.class);
        startActivity(i);

    }
}

