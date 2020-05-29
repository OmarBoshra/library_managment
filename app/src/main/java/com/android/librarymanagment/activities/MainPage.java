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
import android.widget.TextView;

import com.android.librarymanagment.R;
import com.android.librarymanagment.dataBase;
import com.android.librarymanagment.models.BookModel;
import com.android.librarymanagment.ui.BooksAdapter;

public class MainPage extends AppCompatActivity implements BooksAdapter.SelectedUser
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_main);

  showingDefaultBooksList();

  buttons();

        // TODO: 5/27/2020  add entry and students
        // TODO: 5/27/2020  show lanuages
        // TODO: 5/27/2020  show publication & editions
        // TODO: 5/27/2020  show format
        // TODO: 5/27/2020  show restrictions

        // TODO: 5/27/2020  make sure to check entry each day to remove books
        // TODO: 5/27/2020  make sure to add triggers after insert and delete
        // TODO: 5/28/2020  add button for lib statistics
        // TODO: 5/28/2020  add button for fines
        /*SELECT Date('now','+1 month','-1 day')

        *   data data = new data(Main.this);
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + data.Books, null);
        *  ContentValues values = new ContentValues();

            values.put(data.trash, "T");
            db.insert(data.Books, null, values);
            *             db.update(data.Table3, null, values);
            *             db.delete(data.Table3, null, values);

            *        cursor = db.query(data.Books, new String[]{"MAX(" + data.serial + ")"}, null, null, null, null, null);

            *
 cursor=db.rawQuery("SELECT id2 ,X,Y" + " FROM " + data.Books + " WHERE id2=? AND V=?" ,new String [] {H,J,K});
            cursor.moveToFirst();
            if(cursor.getCount()>0){
            *
            * cursor.close();
db.close();
*
*
* CREATE TRIGGER if not exists add_student
   AFTER INSERT
 ON[student]
   for each row
     BEGIN
        insert into library values (2 , new.sid );
        insert into canteen values (3 , new.sid);
     END;
     *
     *
     * CREATE VIEW view_name AS
    SELECT A.time AS Start, B.time AS Stop
    FROM time A, time B
    WHERE A.booksId+1=B.booksId
        AND A.bool=1
        AND B.bool=0
        * */
    }

    private void buttons() {

        Button Register = findViewById(R.id.register);

    }

    private void showingDefaultBooksList() {
        RecyclerView recyclerView = findViewById(R.id.recycleView);


        dataBase data = new dataBase(this);


        BooksAdapter usersAdapter = new BooksAdapter(data.getBooks(), MainPage.this);

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
    public void selectedUser(BookModel bookModel) {


        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.book_dialog);
        d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

        TextView bookDetails = d.findViewById(R.id.bookDetails) ;



        String book_name = bookModel.getTitle();
        String acquired = bookModel.getAcquired()==0?"purchase":bookModel.getAcquired()==1?"donation":"loan";
        String top_rated = bookModel.getTop_rated()==1?"⭐":"";


        bookDetails.setText(top_rated.isEmpty()?"":"top_rated "+top_rated+"\n"+"book_name "+book_name+"\n"+"acquired through "+acquired);



        Button borrow_book = d.findViewById(R.id.borrowBook) ;

        borrow_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO SEND TO BORROW METHOD


            }
        });

        d.show();

    }

}
