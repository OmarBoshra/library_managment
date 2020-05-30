package com.android.librarymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.librarymanagment.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class dataBase extends SQLiteOpenHelper {



    public static final int version = 1;
    public static final String database_name = "library management";

    public static final String  TABLE_LIBRARY_STATISTICS = "library_statistics";
    public static final String LIBRARY_STATISTICS_ID = "id";
    public static final String LIBRARY_STATISTICS_BOOKS_NUMBER = "books_number";
    public static final String LIBRARY_STATISTICS_TOP_RATED_BOOKS = "top_rated_books";
    public static final String LIBRARY_STATISTICS_STAFF_NUMBER = "staff_number";
    public static final String LIBRARY_STATISTICS_AWARDS_NUMBER = "awards_number";
    public static final String LIBRARY_STATISTICS_COMPUTERS_NUMBER = "computers_number";

    public static final String  TABLE_BOOKS = "books";
    public static final String BOOKS_ID = "id";
    public static final String BOOKS_TITLE = "title";
    public static final String BOOKS_ACQURIED = "acquired";
    public static final String BOOKS_DURATION = "duration";
    public static final String BOOKS_TOP_RATED = "top_rated";



    public static final String TABLE_AUTHORS = "authors";
    public static final String AUTHORS_ID = "id";
    public static final String AUTHORS_BOOK_ID = "book_id";
    public static final String AUTHORS_NAME = "name";

//    public static final String Books_title_idx = "Books_title_idx";

    public static final String  TABLE_CATEGORIES = "categories";
    public static final String CATEGORIES_ID = "id";
    public static final String CATEGORIES_BOOK_ID = "book_id";
    public static final String CATEGORIES_CATEGORY_NAME = "category_name";
//
    public static final String  TABLE_BOOK_FORMATS = "book_formats";
    public static final String BOOK_FORMATS_ID = "id";
    public static final String BOOK_FORMATS_BOOK_ID = "book_id";
    public static final String BOOK_FORMATS_FORMAT = "format";


//    public static final String TABLE_PUBLICATIONS = "publications";
//    public static final String PUBLICATIONS_ID = "id";
//    public static final String PUBLICATIONS_BOOK_ID = "book_id";
//    public static final String PUBLICATIONS_PUBLICATION_NAME = "publication_name";
//    public static final String PUBLICATIONS_PUBLICATION_NUMBER = "publication_number";
//
    public static final String TABLE_LANGUAGES = "languages";
    public static final String LANGUAGES_ID = "id";
    public static final String LANGUAGES_BOOK_ID = "book_id";
    public static final String LANGUAGES_LANG_NAME = "lang_name";
    public static final String LANGUAGES_COPY_NUMBER = "copy_number";


    public static final String  TABLE_ENTRIES = "entries";
    public static final String ENTRIES_ID = "id";
    public static final String ENTRIES_BOOK_ID = "book_id";
    public static final String ENTRIES_USER_ID = "user_id";
    public static final String ENTRIES_BORROW_TIME = "borrow_time";
    public static final String ENTRIES_RETURN_TIME = "return_time";
    public static final String ENTRIES_BOOK_DURATION = "duration";

    public static final String  TABLE_FINES = "fines";
    public static final String FINES_ID = "id";
    public static final String FINES_ENTRY_ID = "entri_id";
    public static final String FINES_AMOUNT = "amount";

    public static final String  TABLE_USERS = "users";
        public static final String USERS_ID = "id";
        public static final String USERS_NAME = "name";
        public static final String USERS_USER_TYPE = "user_type";
        public static final String USERS_EMAIL = "email";
        public static final String USERS_PASSWORD = "password";


    public static final String  TABLE_RESTRICTIONS_BOOKS = "restrictions_books";
    public static final String RESTRICTIONS_BOOKS_ID = "id";
    public static final String RESTRICTIONS_BOOKS_BOOK_ID = "book_id";
    public static final String RESTRICTIONS_BOOKS_RESTRICTION_TYPE = "restriction_type";
    public static final String RESTRICTIONS_BOOKS_DEADLINE = "deadline";



    public dataBase(Context context) {
            super(context, database_name, null, version);

        }

        //https://www.tutorialspoint.com/sqlite/sqlite_date_time.htm
//    https://www.sqlite.org/datatypes.html
//
//
//    https://www.sqlite.org/different.html
//    queries
//    https://www.sqlite.org/lang.html

        @Override
        public void onCreate(SQLiteDatabase db) {



       final String TABLE_CREATE_LIBRARY_STATISTICS =
                "CREATE TABLE "+ TABLE_LIBRARY_STATISTICS +" ("+
                        LIBRARY_STATISTICS_ID + " INTEGER PRIMARY KEY, "+
                        LIBRARY_STATISTICS_BOOKS_NUMBER + " INTEGER, "+
                        LIBRARY_STATISTICS_TOP_RATED_BOOKS + " INTEGER, "+
                        LIBRARY_STATISTICS_STAFF_NUMBER + " INTEGER, "+
                        LIBRARY_STATISTICS_AWARDS_NUMBER + " INTEGER, "+
                        LIBRARY_STATISTICS_COMPUTERS_NUMBER + " INTEGER)";


            final String TABLE_CREATE_BOOKS =
                    "CREATE TABLE "+ TABLE_BOOKS +" ("+
                            BOOKS_ID + " INTEGER PRIMARY KEY, "+
                            BOOKS_TITLE + " TEXT, "+
                            BOOKS_ACQURIED + " INTEGER, "+//0,1,2
                            BOOKS_DURATION + " TEXT, "+
                            BOOKS_TOP_RATED + " INTEGER)";//0,1


//        final String books_title_index = " CREATE INDEX " + Books_title_idx + " ON "+ TABLE_BOOKS +"("+BOOKS_TITLE+")";

        final String TABLE_CREATE_CATEGORIES =
                "CREATE TABLE "+ TABLE_CATEGORIES +" ("+
                        CATEGORIES_ID + " INTEGER PRIMARY KEY, "+
                        CATEGORIES_BOOK_ID + " INTEGER, "+
                        CATEGORIES_CATEGORY_NAME + " TEXT)";
//
//
//
        final String TABLE_CREATE_BOOK_FORMATS =
                "CREATE TABLE "+ TABLE_BOOK_FORMATS +" ("+
                        BOOK_FORMATS_ID + " INTEGER PRIMARY KEY, "+
                        BOOK_FORMATS_BOOK_ID + " INTEGER, "+
                        BOOK_FORMATS_FORMAT + " INTEGER)";
//

            final String TABLE_CREATE_AUTHORS =
                    "CREATE TABLE "+ TABLE_AUTHORS +" ("+
                            AUTHORS_ID + " INTEGER PRIMARY KEY, "+
                            AUTHORS_BOOK_ID + " INTEGER, "+
                            AUTHORS_NAME + " TEXT)";


//        final String TABLE_CREATE_PUBLICATIONS =
//                "CREATE TABLE "+ TABLE_PUBLICATIONS +" ("+
//                        PUBLICATIONS_ID + " INTEGER PRIMARY KEY, "+
//                        PUBLICATIONS_BOOK_ID + " INTEGER, "+
//                        PUBLICATIONS_PUBLICATION_NAME + " TEXT, "+
//                        PUBLICATIONS_PUBLICATION_NUMBER + " TEXT)";
//
        final String TABLE_CREATE_LANGUAGES =
                "CREATE TABLE "+ TABLE_LANGUAGES +" ("+
                        LANGUAGES_ID + " INTEGER PRIMARY KEY, "+
                        LANGUAGES_BOOK_ID + " INTEGER, "+
                        LANGUAGES_LANG_NAME + " TEXT, "+
                        LANGUAGES_COPY_NUMBER + " INTEGER)";



            final String TABLE_CREATE_ENTRIES =
                    "CREATE TABLE "+ TABLE_ENTRIES +" ("+
                            ENTRIES_ID + " INTEGER PRIMARY KEY, "+
                            ENTRIES_BOOK_ID + " INTEGER, "+
                            ENTRIES_USER_ID + " INTEGER, "+
                            ENTRIES_BORROW_TIME + " TEXT, "+
                            ENTRIES_RETURN_TIME + " TEXT, "+
                            BOOKS_DURATION + " TEXT)";

            final String TABLE_CREATE_FINES =
                    "CREATE TABLE "+ TABLE_FINES +" ("+
                            FINES_ID + " INTEGER PRIMARY KEY, "+
                            FINES_ENTRY_ID + " INTEGER, "+
                            FINES_AMOUNT + " INTEGER)";

           final String TABLE_CREATE_USERS =
                    "CREATE TABLE "+ TABLE_USERS +" ("+
                            USERS_ID + " INTEGER PRIMARY KEY, "+
                            USERS_NAME + " TEXT, "+
                            USERS_USER_TYPE + " INTEGER, "+
                            USERS_EMAIL + " TEXT UNIQUE, "+
                            USERS_PASSWORD + " TEXT)";

            final String TABLE_CREATE_RESTRICTIONS_BOOKS =
                    "CREATE TABLE "+ TABLE_RESTRICTIONS_BOOKS +" ("+
                            RESTRICTIONS_BOOKS_ID + " INTEGER PRIMARY KEY, "+
                            RESTRICTIONS_BOOKS_BOOK_ID + " INTEGER, "+
                            RESTRICTIONS_BOOKS_RESTRICTION_TYPE + " INTEGER, "+
                            RESTRICTIONS_BOOKS_DEADLINE + " TEXT)";

//        final String t3 = "CREATE VIEW " + Books_view + "AS SELECT" +
//                serial2 + " TEXT " + ")";


        /*CREATE VIEW view_name AS
    SELECT A.time AS Start, B.time AS Stop
    FROM time A, time B
    WHERE A.booksId+1=B.booksId
        AND A.bool=1
        AND B.bool=0*/

//        final String t3 = "CREATE TABLE " + Table3 + "(" +
//                serial2 + " TEXT " + ")";

        db.execSQL(TABLE_CREATE_LIBRARY_STATISTICS);
        db.execSQL(TABLE_CREATE_BOOKS);
//        db.execSQL(books_title_index);
        db.execSQL(TABLE_CREATE_AUTHORS);
        db.execSQL(TABLE_CREATE_BOOK_FORMATS);
        db.execSQL(TABLE_CREATE_CATEGORIES);
        db.execSQL(TABLE_CREATE_LANGUAGES);
        db.execSQL(TABLE_CREATE_RESTRICTIONS_BOOKS);
//        db.execSQL(TABLE_CREATE_PUBLICATIONS);
        db.execSQL(TABLE_CREATE_ENTRIES);
        db.execSQL(TABLE_CREATE_FINES);
        db.execSQL(TABLE_CREATE_USERS);

            //SAMPLE DATA
            //books
            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('mirdad',1,'01-03','1')");

            //Users
            db.execSQL("INSERT INTO   users   (  name  ,  user_type  , email  ,  password  ) Values ('omar',0,'osarious2@gmail.com','123456')");

        }

// TODO: GENERAL

    public void register(String email,String password,String user_name,int user_type){


        SQLiteDatabase db =getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(this.USERS_NAME, user_name);
        values.put(this.USERS_EMAIL, email);
        values.put(this.USERS_PASSWORD,password );
        values.put(this.USERS_USER_TYPE,user_type );

        db.insert(this.TABLE_ENTRIES, null, values);

        db.close();

    }

    public String signIn(String email,String password){


        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+USERS_NAME+" FROM " + dataBase.TABLE_USERS+ " WHERE "+USERS_EMAIL+"=? AND "+USERS_PASSWORD+"=?",new String [] {email, password});



        if(cursor.getCount()==1){
            cursor.moveToFirst();

            String name =cursor.getString(0);
            cursor.close();
            close();
           return  name;
        }else
            return "";

    }

//METHODS

    public List<BookModel> getBooks() {


        List<BookModel> bookModelList = new ArrayList<>();

        Cursor cursor = getWritableDatabase().rawQuery("select * from " + dataBase.TABLE_BOOKS, null);

        cursor.getColumnCount();

        if(cursor.moveToFirst()){

            do{


                bookModelList.add(new BookModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4)));


            }while (cursor.moveToNext());

            cursor.close();
            close();

        }

        return bookModelList;
    }

    //TODO FOR STUDENTS

    public void borrowBook(int bookId,int userId,String bookDuration){

    SQLiteDatabase db =getWritableDatabase();
    ContentValues values = new ContentValues();



    values.put(this.ENTRIES_BOOK_ID, bookId);
    values.put(this.ENTRIES_USER_ID, userId);
    values.put(this.ENTRIES_BORROW_TIME, String.valueOf(db.compileStatement("SELECT date('now')")));
    values.put(this.ENTRIES_BOOK_DURATION, bookDuration);

    db.insert(this.TABLE_ENTRIES, null, values);

        db.close();
}




    //TODO FOR LIBRARIAN



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " +TABLE_LIBRARY_STATISTICS);

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_BOOK_FORMATS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_LANGUAGES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_AUTHORS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_RESTRICTIONS_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_FINES);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
//        db.execSQL("DROP TABLE IF EXISTS " +TABLE_PUBLICATIONS);
//        db.execSQL("DROP TABLE IF EXISTS " +Books_title_idx);



        onCreate(db);
    }


}