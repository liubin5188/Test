package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.activity.BookDetailsActivity;
import com.example.test.models.Book;
import com.example.test.models.Database;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * The adapter used by RecyclerView to display books.
 *
 */
public class RecyclerBooksAdapter extends RecyclerView.Adapter<RecyclerBooksAdapter.RowHolder> {

    private LayoutInflater inflater;
    private ArrayList<Book> bookList;

    @Inject
    public RecyclerBooksAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RowHolder(inflater.inflate(R.layout.item_book, viewGroup, false));
    }

    public void setAdapterData(ArrayList<Book> bookList){
        this.bookList = bookList;
    }

    @Override
    public void onBindViewHolder(RowHolder rowHolder, int i) {
        rowHolder.bindModel(bookList.get(i));
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        TextView authorTextView;

        public RowHolder(View row) {
            super(row);
            titleTextView = (TextView) row.findViewById(R.id.book_title);
            authorTextView = (TextView) row.findViewById(R.id.book_author);
            row.setOnClickListener(this);
        }

        public void bindModel(Book book) {
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
        }

        @Override
        public void onClick(View v) {
            Book book = Database.ALL_BOOKS.get(getPosition());
            Intent intent = new Intent(v.getContext(), BookDetailsActivity.class);
            intent.putExtra(BookDetailsActivity.TITLE, book.getTitle());
            intent.putExtra(BookDetailsActivity.AUTHOR, book.getAuthor());
            v.getContext().startActivity(intent);
        }
    }
}
