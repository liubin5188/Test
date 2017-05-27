package com.example.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by liubin on 2017/5/25.
 */

public class BookDetailsActivity extends AppCompatActivity {
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        TextView bookTitle = (TextView) findViewById(R.id.book_title);
        TextView bookAuthor = (TextView) findViewById(R.id.book_author);

        bookTitle.setText(getIntent().getStringExtra(TITLE));
        bookAuthor.setText(getIntent().getStringExtra(AUTHOR));
    }
}
