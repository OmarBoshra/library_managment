package com.android.librarymanagment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.librarymanagment.R;
import com.android.librarymanagment.dataBase;
import com.android.librarymanagment.models.BookInstModel;
import com.android.librarymanagment.models.BookModel;
import com.android.librarymanagment.ui.BooksAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage extends AppCompatActivity implements BooksAdapter.SelectedUser
{

    String userID;
    String user_type;
    int [] choices;

    Map<String, Integer> Langlist ;
    Map<String, Integer> formatlist;
    Map<String, Integer> publicationlist;
    Map<String, Integer>editionlist ;
    Map<String, Integer> categorylist ;

    BooksAdapter usersAdapter;
    private List<BookModel> bookModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_main);




            userID= getIntent().getStringExtra("user_id");
        user_type= getIntent().getStringExtra("user_type");
           String userName= getIntent().getStringExtra("user_name");


        TextView header = findViewById(R.id.header);

        header.setText(new StringBuilder().append("Welcome ").append(userName).toString());

  showingDefaultBooksList();

        // TODO: 5/27/2020  add entry and students


        // TODO: 5/27/2020  make sure to check entry each day to remove books
        // TODO: 5/27/2020  make sure to add triggers after insert and delete
        // TODO: 5/28/2020  add button for fines


        if(user_type.equals("1")) {//librarian


            librarianDialog();

        }

    }


    private void librarianDialog(){

        ImageButton addBook = findViewById(R.id.addBook);
        addBook.setVisibility(View.VISIBLE);


        addBook.setOnClickListener(new View.OnClickListener() {

           long insertedBookId=-1;

            @Override
            public void onClick(View v) {


                final Dialog d = new Dialog(MainPage.this);
                d.setContentView(R.layout.book_insertion_dialog);
                d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);


         final EditText addBook_name=d.findViewById(R.id.addBook_name);
         final EditText add_aquired=d.findViewById(R.id.add_aquired);
         final EditText add_duration=d.findViewById(R.id.add_duration);
         final CheckBox top_rated=d.findViewById(R.id.add_toprated);
         final EditText add_language=d.findViewById(R.id.add_language);
         final EditText add_format=d.findViewById(R.id.add_format);
         final EditText add_publication=d.findViewById(R.id.add_publication);
         final EditText add_edition=d.findViewById(R.id.add_edition);
         final EditText add_category=d.findViewById(R.id.add_category);
         final EditText add_isbn=d.findViewById(R.id.add_isbn);
         final EditText add_authors=d.findViewById(R.id.add_authors);
         final EditText add_copies=d.findViewById(R.id.add_copies);

         final RadioButton add_restriction_nb=d.findViewById(R.id.add_restriction1);
         final RadioButton add_restriction_deadline=d.findViewById(R.id.add_restriction2);
         final EditText add_deadline=d.findViewById(R.id.add_deadline);


                Button newBook = d.findViewById(R.id.newBook);

                newBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        insertedBookId=-1;

                  addBook_name.setText("");
                  add_aquired.setText("");
                  add_duration.setText("");
                  top_rated.setChecked(false);
                  add_language.setText("");
                  add_format.setText("");
                  add_publication.setText("");
                  add_edition.setText("");
                  add_category.setText("");
                  add_isbn.setText("");
                  add_authors.setText("");
                  add_copies.setText("");
                        add_restriction_nb.setChecked(false);
                        add_restriction_deadline.setChecked(false);
                  add_deadline.setText("");



                    }
                });

                Button submit = d.findViewById(R.id.save);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

int restriction=-1;
                        if(add_restriction_nb.isChecked())
                            restriction=0;
                        if(add_restriction_deadline.isChecked())
                            restriction=1;

                        dataBase db = new dataBase(MainPage.this);

                        String title;
                        String acquired;
                        String duration;
                        int topRated =top_rated.isChecked()?1:0;

                        long result=db.insertBook(insertedBookId,title=addBook_name.getText().toString(),acquired=add_aquired.getText().toString(),duration=add_duration.getText().toString(),topRated,add_authors.getText().toString(),add_isbn.getText().toString(),add_language.getText().toString(),add_format.getText().toString(),add_publication.getText().toString(),add_edition.getText().toString(),add_category.getText().toString(),add_copies.getText().toString(),restriction,add_deadline.getText().toString());


if(result>-1){


    Toast.makeText(MainPage.this, "Insert success", Toast.LENGTH_SHORT).show();


    if(insertedBookId==-1)
        bookModel.add(new BookModel(insertedBookId,title,acquired,duration,topRated));

    insertedBookId=result;

    usersAdapter.notifyDataSetChanged();

}else
    Toast.makeText(MainPage.this, "please change the ISBN number", Toast.LENGTH_SHORT).show();

                    }
                });



                d.show();

            }
        });


    }

    private void showingDefaultBooksList() {
        RecyclerView recyclerView = findViewById(R.id.recycleView);

SearchView search = findViewById(R.id.searchView);

        dataBase data = new dataBase(this);


        usersAdapter = new BooksAdapter(bookModel=data.getBooks(), MainPage.this);

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

        final dataBase db = new dataBase(MainPage.this);


        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.book_dialog);
        d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);

        TextView bookDetails = d.findViewById(R.id.bookDetails);

        final long bookId = bookModel.getId();


        String book_name = bookModel.getTitle();

        String acquired = bookModel.getAcquired();
        String top_rated = bookModel.getTop_rated() == 1 ? "top rated ⭐ \n" : "";


        bookDetails.setText(top_rated + "book name " + book_name + "\n" + "acquired through " + acquired, TextView.BufferType.SPANNABLE);

//todo check registered users (NOT PRIORITY)

        Button borrow_Or_Return_book = d.findViewById(R.id.borrowOrReturnBook);

        selections(d, db, bookId);


        final TextView copies = d.findViewById(R.id.copies);

        TextView authors = d.findViewById(R.id.authors);

        List<String> authorsList = db.getAuthors(String.valueOf(bookModel.getId()));

        StringBuffer authorsGroup =new StringBuffer();

        authorsGroup.append(" Author(s) : ");


        for (String author :authorsList){

            authorsGroup.append(author +",");


    }

        authors.setText(authorsGroup.toString());

        borrow_Or_Return_book.setOnClickListener(new View.OnClickListener() {

            List<BookInstModel> bookInstances = db.getBookInstance(String.valueOf(bookModel.getId()));

            int bookInstId;
            int copyNum;

            @Override
            public void onClick(View v) {

                if(((Button) v).getText().toString().equals("Submit")) {

                    List<BookInstModel> mFinalListItem = new ArrayList<>();


                    for (BookInstModel instance : bookInstances) {
                        if (instance.getBOOK_INST_LANG_ID() == choices[0]&&instance.getBOOK_INST_FORMAT_ID() == choices[1]&&instance.getBOOK_INST_PUBLICATION_ID() == choices[2]&&instance.getBOOK_INST_EDITION_ID() == choices[3]&&instance.getBOOK_INST_CATEGORY_ID() == choices[4]) {

                            copyNum=instance.getBOOK_INST_COPIES();

                            copies.setText(String.valueOf("Number of Copies : " + copyNum));


                            if (db.checkEntryRecord(bookInstId=instance.getBOOK_INST_ID(), userID))
                                ((Button) v).setText("Return Book");
                            else
                                ((Button) v).setText("Borrow Book");


                            break;

                        }

                    }

                }else {


                    //todo check weather return or copy


                    if (((Button) v).getText().toString().equals("Return Book")) {
                        db.returnBook(MainPage.this, bookInstId, userID);
                        ((Button) v).setText("Borrow book");

                        copyNum++;
                        copies.setText("Number of Copies : " +copyNum );

                        db.updateNumberOfCopies(bookInstId,copyNum,true);

                    }else{
    try {
        if (copyNum==0||db.borrowBook(bookInstId, Integer.parseInt(userID), bookModel.getDuration()) == false)
            Toast.makeText(MainPage.this, "Book isn't for borrowing", Toast.LENGTH_SHORT).show();
        else {

            Toast.makeText(MainPage.this, "Book borrowed successfully", Toast.LENGTH_SHORT).show();
            ((Button) v).setText("Return Book");
            copyNum--;
            copies.setText("Number of Copies : " +copyNum );
            db.updateNumberOfCopies(bookInstId,copyNum, false);


            //todo decrease copy number and update libstatistics

        }
    } catch (ParseException e) {
        e.printStackTrace();
    }
}



                }
            }
        });

        d.show();

    }

    private void selections(Dialog d, final dataBase db, final long bookId) {

//todo declare inside


        Spinner langChoice = d.findViewById(R.id.languages) ;
        Spinner formatsChoice = d.findViewById(R.id.formats) ;

        Spinner publicationsChoice = d.findViewById(R.id.publications) ;
        Spinner editionsChoice = d.findViewById(R.id.editions) ;
        Spinner categoriesChoice = d.findViewById(R.id.categories) ;




final boolean [] touches = new boolean []{false,false,false,false,false};

choices = new int []{0,0,0,0,0};

//lang,format,publication,edition,category

        Langlist = new HashMap<>();
        publicationlist =  new HashMap<>();
        editionlist =  new HashMap<>();
        categorylist =  new HashMap<>();

        langChoice.setOnTouchListener(new View.OnTouchListener() {


            List<String> languages=new ArrayList<>();


            @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(touches[0]==false) {
                            Langlist =  db.getBookLanguages(String.valueOf(bookId));


                            languages= new ArrayList<String>(Langlist.keySet());

                            ArrayAdapter<String> langArrayAdapter = new ArrayAdapter<String>(MainPage.this, android.R.layout.simple_spinner_item,languages);

                            ((AppCompatSpinner)v).setAdapter(langArrayAdapter);

                            touches[0]=true;
                        }

                return false;
            }
        });


        langChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if(touches[0]){


                    choices[0]=  Langlist.get(adapterView.getItemAtPosition(position).toString());


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        formatsChoice.setOnTouchListener(new View.OnTouchListener() {


            List<String> formats=new ArrayList<>();


            @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(touches[1]==false) {
                            formatlist =  db.getBookFormats(String.valueOf(bookId));


                            formats= new ArrayList<String>(formatlist.keySet());

                            ArrayAdapter<String> formatArrayAdapter = new ArrayAdapter<String>(MainPage.this, android.R.layout.simple_spinner_item,formats);

                            ((AppCompatSpinner)v).setAdapter(formatArrayAdapter);

                            touches[1]=true;
                        }

                return false;
            }
        });


        formatsChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if(touches[1]){


                    choices[1]=  formatlist.get(adapterView.getItemAtPosition(position).toString());


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        publicationsChoice.setOnTouchListener(new View.OnTouchListener() {


            List<String> publications=new ArrayList<>();


            @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(touches[2]==false) {
                            publicationlist =  db.getBookPublications(String.valueOf(bookId));


                            publications= new ArrayList<String>(publicationlist.keySet());

                            ArrayAdapter<String> pubArrayAdapter = new ArrayAdapter<String>(MainPage.this, android.R.layout.simple_spinner_item,publications);

                            ((AppCompatSpinner)v).setAdapter(pubArrayAdapter);

                            touches[2]=true;
                        }

                return false;
            }
        });


        publicationsChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if(touches[2]){


                    choices[2]=  publicationlist.get(adapterView.getItemAtPosition(position).toString());


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        editionsChoice.setOnTouchListener(new View.OnTouchListener() {


            List<String> editions=new ArrayList<>();


            @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(touches[3]==false) {
                            editionlist =  db.getBookEditions(String.valueOf(bookId));


                            editions= new ArrayList<String>(editionlist.keySet());

                            ArrayAdapter<String> editionArrayAdapter = new ArrayAdapter<String>(MainPage.this, android.R.layout.simple_spinner_item,editions);

                            ((AppCompatSpinner)v).setAdapter(editionArrayAdapter);

                            touches[3]=true;
                        }

                return false;
            }
        });


        editionsChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if(touches[3]){


                    choices[3]=  editionlist.get(adapterView.getItemAtPosition(position).toString());


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        categoriesChoice.setOnTouchListener(new View.OnTouchListener() {


            List<String> categories=new ArrayList<>();


            @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if(touches[4]==false) {
                            categorylist =  db.getBookCategories(String.valueOf(bookId));


                            categories= new ArrayList<String>(categorylist.keySet());

                            ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(MainPage.this, android.R.layout.simple_spinner_item,categories);

                            ((AppCompatSpinner)v).setAdapter(categoryArrayAdapter);

                            touches[4]=true;
                        }

                return false;
            }
        });


        categoriesChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if(touches[4]){


                    choices[4]=  categorylist.get(adapterView.getItemAtPosition(position).toString());


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

}
