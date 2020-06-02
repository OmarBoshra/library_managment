package com.android.librarymanagment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.librarymanagment.R;
import com.android.librarymanagment.dataBase;
import com.android.librarymanagment.models.BookModel;
import com.android.librarymanagment.ui.BooksAdapter;

import java.text.ParseException;

public class MainPage extends AppCompatActivity implements BooksAdapter.SelectedUser
{

    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_main);




            userID= getIntent().getStringExtra("user_id");
           String userName= getIntent().getStringExtra("user_name");


        TextView header = findViewById(R.id.header);

        header.setText(new StringBuilder().append("Welcome ").append(userName).toString());

  showingDefaultBooksList();

        // TODO: 5/27/2020  add entry and students
        // TODO: 5/27/2020  show lanuages
        // TODO: 5/27/2020  show publication & editions
        // TODO: 5/27/2020  show format
        // TODO: 5/27/2020  show restrictions

        // TODO: 5/27/2020  make sure to check entry each day to remove books
        // TODO: 5/27/2020  make sure to add triggers after insert and delete
        // TODO: 5/28/2020  add button for lib statistics
        // TODO: 5/28/2020  add button for fines

    }



    private void showingDefaultBooksList() {
        RecyclerView recyclerView = findViewById(R.id.recycleView);

SearchView search = findViewById(R.id.searchView);

        dataBase data = new dataBase(this);


        final BooksAdapter usersAdapter = new BooksAdapter(data.getBooks(), MainPage.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainPage.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainPage.this,DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(usersAdapter);

        ImageButton filterBy = findViewById(R.id.filterBy);

        filterBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filter_dialog();

            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                usersAdapter.getFilter().filter(s);

                return false;
            }
        });


    }

    private void filter_dialog() {

        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.book_filter);
        d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);


        d.setOnDismissListener(new DialogInterface.OnDismissListener() {//for all cancel and dismiss
            @Override
            public void onDismiss(DialogInterface dialog) {

//todo set the filter
            }
        });


        d.show();
    }

    @Override
    public void selectedUser(final BookModel bookModel) {


        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.book_dialog);
        d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

        TextView bookDetails = d.findViewById(R.id.bookDetails) ;


        String book_name = bookModel.getTitle();
        String acquired = bookModel.getAcquired()==0?"purchase":bookModel.getAcquired()==1?"donation":"loan";
        String top_rated = bookModel.getTop_rated()==1?"top rated ⭐ \n":"";


        bookDetails.setText(top_rated+"book name "+book_name+"\n"+"acquired through "+acquired);

//todo check the entries for borrowing or return
//todo check registered users (NOT PRIORITY)

        Button borrow_Or_Return_book = d.findViewById(R.id.borrowOrReturnBook) ;


        final dataBase db = new dataBase(MainPage.this);

        //check the entry table for a user record
        if(db.checkEntryRecord(bookModel.getId(),userID))
            borrow_Or_Return_book.setText("Return Book");
            else
            borrow_Or_Return_book.setText("Borrow Book");


        borrow_Or_Return_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if( ((Button)v).getText().toString().equals("Return Book")) {
    db.returnBook(MainPage.this, bookModel.getId(), userID);
    ((Button) v).setText("Borrow book");

}else {
    try {
        if (db.borrowBook(bookModel.getId(), Integer.parseInt(userID), bookModel.getDuration()) == false)
            Toast.makeText(MainPage.this, "Book isn't for borrowing", Toast.LENGTH_SHORT).show();
        else {

            Toast.makeText(MainPage.this, "Book borrowed successfully", Toast.LENGTH_SHORT).show();
            ((Button) v).setText("Return Book");

        }
    } catch (ParseException e) {
        e.printStackTrace();
    }
}

            }
        });

        d.show();

    }

}
