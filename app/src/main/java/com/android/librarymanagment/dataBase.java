package com.android.librarymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.android.librarymanagment.models.BookInstModel;
import com.android.librarymanagment.models.BookModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public static final String  TABLE_BOOK_INST = "book_inst";
    public static final String BOOK_INST_ID = "id";
    public static final String BOOK_INST_BOOK_ID = "book_id";
    public static final String BOOK_INST_ISBN = "isbn";
    public static final String BOOK_INST_LANG_ID = "lang_id";
    public static final String BOOK_INST_FORMAT_ID = "format_id";
    public static final String BOOK_INST_PUBLICATION_ID = "publication_id";
    public static final String BOOK_INST_EDITION_ID = "edition_id";
    public static final String BOOK_INST_CATEGORY_ID = "category_id";
    public static final String BOOK_INST_COPIES = "copies";


    public static final String  TABLE_AUTHORS = "authors";
    public static final String AUTHORS_ID = "id";
    public static final String AUTHORS_NAME = "name";

    public static final String  TABLE_AUTHORS_BOOKS = "authors_books";
    public static final String AUTHORS_BOOKS_ID = "id";
    public static final String AUTHORS_BOOKS_AUTHORS_ID = "authors_id";
    public static final String AUTHORS_BOOKS_BOOK_ID = "book_id";



    public static final String BooksInst_id_idx = "BooksInst_id_idx";


    public static final String  TABLE_CATEGORIES = "categories";
    public static final String CATEGORIES_ID = "id";
    public static final String CATEGORIES_CATEGORY_NAME = "category_name";
//
    public static final String TABLE_FORMATS = "formats";
    public static final String FORMATS_ID = "id";
    public static final String FORMATS_NAME = "formatname";

    public static final String  TABLE_EDITION = "edition";
    public static final String EDITION_ID = "id";
    public static final String EDITION_EDITION_NUMBER = "edition_number";


    public static final String TABLE_PUBLICATIONS = "publications";
    public static final String PUBLICATIONS_ID = "id";
    public static final String PUBLICATIONS_PUBLICATION_NAME = "publication_name";
//
    public static final String TABLE_LANGUAGES = "languages";
    public static final String LANGUAGES_ID = "id";
    public static final String LANGUAGES_LANG_NAME = "lang_name";


    public static final String  TABLE_ENTRIES = "entries";
    public static final String ENTRIES_ID = "id";
    public static final String ENTRIES_BOOKINST_ID = "bool_instID";
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


    public static final String update_book_copies = "update_book_copies";



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
                    "CREATE TABLE " + TABLE_LIBRARY_STATISTICS + " (" +
                            LIBRARY_STATISTICS_ID + " INTEGER PRIMARY KEY, " +
                            LIBRARY_STATISTICS_BOOKS_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_TOP_RATED_BOOKS + " INTEGER, " +
                            LIBRARY_STATISTICS_STAFF_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_AWARDS_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_COMPUTERS_NUMBER + " INTEGER)";


//            CREATE TRIGGER add_student
//            AFTER INSERT
//            ON[languages]
//            BEGIN
//            insert into categories (category_name) values (new.lang_name );
//            END
//

//            final String CREATE_LibraryTRIGGER ="CREATE TRIGGER "+update_book_copies+" AFTER UPDATE ON["+TABLE_BOOK_INST+"BEGIN"


            final String TABLE_CREATE_BOOKS =
                    "CREATE TABLE " + TABLE_BOOKS + " (" +
                            BOOKS_ID + " INTEGER PRIMARY KEY, " +
                            BOOKS_TITLE + " TEXT, " +
                            BOOKS_ACQURIED + " TEXT, " +
                            BOOKS_DURATION + " TEXT, " +
                            BOOKS_TOP_RATED + " INTEGER)";//0,1



            final String TABLE_CREATE_BOOK_INST =
                    "CREATE TABLE "+ TABLE_BOOK_INST +" ("+
                            BOOK_INST_ID + " INTEGER PRIMARY KEY, "+
                            BOOK_INST_BOOK_ID + " INTEGER, "+
                            BOOK_INST_ISBN + " TEXT, "+
                            BOOK_INST_LANG_ID + " INTEGER, "+
                            BOOK_INST_FORMAT_ID + " INTEGER, "+
                            BOOK_INST_PUBLICATION_ID + " INTEGER, "+
                            BOOK_INST_EDITION_ID + " INTEGER, "+
                            BOOK_INST_CATEGORY_ID + " INTEGER, "+
                            BOOK_INST_COPIES + " INTEGER)";


        final String bookInst_id_index = " CREATE INDEX " + BooksInst_id_idx + " ON "+ TABLE_BOOK_INST +"("+BOOK_INST_BOOK_ID+")";


            final String TABLE_CREATE_CATEGORIES =
                    "CREATE TABLE " + TABLE_CATEGORIES + " (" +
                            CATEGORIES_ID + " INTEGER PRIMARY KEY, " +
                            CATEGORIES_CATEGORY_NAME + " TEXT UNIQUE)";



            final String TABLE_CREATE_BOOK_FORMATS =
                    "CREATE TABLE " + TABLE_FORMATS + " (" +
                            FORMATS_ID + " INTEGER PRIMARY KEY, " +
                            FORMATS_NAME + " TEXT UNIQUE)";
//

            final String TABLE_CREATE_AUTHORS =
                    "CREATE TABLE "+ TABLE_AUTHORS +" ("+
                            AUTHORS_ID + " INTEGER PRIMARY KEY, "+
                            AUTHORS_NAME + " TEXT)";

            final String TABLE_CREATE_AUTHORS_BOOKS =
                    "CREATE TABLE "+ TABLE_AUTHORS_BOOKS +" ("+
                            AUTHORS_BOOKS_ID + " INTEGER PRIMARY KEY, "+
                            AUTHORS_BOOKS_AUTHORS_ID + " INTEGER, "+
                            AUTHORS_BOOKS_BOOK_ID + " INTEGER)";

        final String TABLE_CREATE_PUBLICATIONS =
                "CREATE TABLE "+ TABLE_PUBLICATIONS +" ("+
                        PUBLICATIONS_ID + " INTEGER PRIMARY KEY, "+
                        PUBLICATIONS_PUBLICATION_NAME + " TEXT)";


            final String TABLE_CREATE_EDITION =
                    "CREATE TABLE "+ TABLE_EDITION +" ("+
                            EDITION_ID + " INTEGER PRIMARY KEY, "+
                            EDITION_EDITION_NUMBER + " TEXT)";



            final String TABLE_CREATE_LANGUAGES =
                    "CREATE TABLE " + TABLE_LANGUAGES + " (" +
                            LANGUAGES_ID + " INTEGER PRIMARY KEY, " +
                            LANGUAGES_LANG_NAME + " TEXT UNIQUE)";




            final String TABLE_CREATE_ENTRIES =
                    "CREATE TABLE " + TABLE_ENTRIES + " (" +
                            ENTRIES_ID + " INTEGER PRIMARY KEY, " +
                            ENTRIES_BOOKINST_ID + " INTEGER, " +
                            ENTRIES_USER_ID + " INTEGER, " +
                            ENTRIES_BORROW_TIME + " TEXT, " +
                            ENTRIES_RETURN_TIME + " TEXT, " +
                            BOOKS_DURATION + " TEXT)";

            final String TABLE_CREATE_FINES =
                    "CREATE TABLE " + TABLE_FINES + " (" +
                            FINES_ID + " INTEGER PRIMARY KEY, " +
                            FINES_ENTRY_ID + " INTEGER, " +
                            FINES_AMOUNT + " INTEGER)";

            final String TABLE_CREATE_USERS =
                    "CREATE TABLE " + TABLE_USERS + " (" +
                            USERS_ID + " INTEGER PRIMARY KEY, " +
                            USERS_NAME + " TEXT, " +
                            USERS_USER_TYPE + " INTEGER, " +
                            USERS_EMAIL + " TEXT UNIQUE, " +
                            USERS_PASSWORD + " TEXT)";

            final String TABLE_CREATE_RESTRICTIONS_BOOKS =
                    "CREATE TABLE " + TABLE_RESTRICTIONS_BOOKS + " (" +
                            RESTRICTIONS_BOOKS_ID + " INTEGER PRIMARY KEY, " +
                            RESTRICTIONS_BOOKS_BOOK_ID + " INTEGER, " +
                            RESTRICTIONS_BOOKS_RESTRICTION_TYPE + " TEXT, " +
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
            db.execSQL(TABLE_CREATE_BOOK_INST);
        db.execSQL(bookInst_id_index);
            db.execSQL(TABLE_CREATE_AUTHORS);
            db.execSQL(TABLE_CREATE_AUTHORS_BOOKS);

            db.execSQL(TABLE_CREATE_LANGUAGES);
            db.execSQL(TABLE_CREATE_PUBLICATIONS);
            db.execSQL(TABLE_CREATE_EDITION);
            db.execSQL(TABLE_CREATE_BOOK_FORMATS);
            db.execSQL(TABLE_CREATE_CATEGORIES);

            db.execSQL(TABLE_CREATE_RESTRICTIONS_BOOKS);

            db.execSQL(TABLE_CREATE_ENTRIES);
            db.execSQL(TABLE_CREATE_FINES);
            db.execSQL(TABLE_CREATE_USERS);

            //SAMPLE DATA
            //books
            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('mirdad','purchase','7','1')");
            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('treasure island','donation','7','0')");

            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('macbeth','loan','7','0')");

            //BOOK INSTANCES

     //Mirdad
            db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('1','978-3-16-148410','1','1','1','1','1','2')");

                   db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('1','978-3-15-148210','2','1','1','1','1','2')");

//Treasure Island
            db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('2','978-3-16-143410','1','1','1','1','1','2')");

                   db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('2','978-3-15-141210','1','1','1','1','1','2')");


//Macbeth
            db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('3','978-3-16-153410','2','1','1','1','1','2')");

                   db.execSQL(" INSERT INTO   "+TABLE_BOOK_INST+"   (  "+BOOK_INST_BOOK_ID+"  ,  "+BOOK_INST_ISBN+"  , "+BOOK_INST_LANG_ID+"  ,  "+BOOK_INST_FORMAT_ID+",  "+BOOK_INST_PUBLICATION_ID+",  "+BOOK_INST_EDITION_ID+" ,  "+BOOK_INST_CATEGORY_ID+",  "+BOOK_INST_COPIES+"  ) Values ('3','978-3-15-155210','1','1','1','1','1','2')");


            //AUTHORS
            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS+"   ("+AUTHORS_NAME+"  ) Values ('Roger')");

      db.execSQL(" INSERT INTO   "+TABLE_AUTHORS+"   ("+AUTHORS_NAME+"  ) Values ('Holmes')");


            //AUTHORS_BOOKS

            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('2','1')");
            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('1','1')");

            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('2','2')");
            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('1','2')");

            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('2','3')");
            db.execSQL(" INSERT INTO   "+TABLE_AUTHORS_BOOKS+"   ( "+AUTHORS_BOOKS_AUTHORS_ID+"  , "+AUTHORS_BOOKS_BOOK_ID+" ) Values ('1','3')");

            //REGISTRATION

            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('1',0,'')");

            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('2',1,'2020-6-20')");


            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('3',1,'2020-5-20')");


            //USERS
            db.execSQL("INSERT INTO   users   (  name  ,  user_type  , email  ,  password  ) Values ('omar',0,'osarious2@gmail.com','123456')");

            //LIBRARY
            db.execSQL("INSERT INTO   library_statistics   (  books_number  ,  top_rated_books  , staff_number  ,  awards_number ,computers_number ) Values (3,1,5,10,6)");

            //LANGUAGES

            db.execSQL(" INSERT INTO   "+TABLE_LANGUAGES+"   (  "+LANGUAGES_LANG_NAME+"  ) Values ('English')");
            db.execSQL(" INSERT INTO   "+TABLE_LANGUAGES+"   (  "+LANGUAGES_LANG_NAME+"  ) Values ('German')");


     //FORMATS

            db.execSQL(" INSERT INTO   "+TABLE_FORMATS+"   (  "+ FORMATS_NAME +"  ) Values ('Kindle')");
            db.execSQL(" INSERT INTO   "+TABLE_FORMATS+"   (  "+ FORMATS_NAME +"  ) Values ('Audio')");



     //PUBLICATIONS

            db.execSQL(" INSERT INTO   "+TABLE_PUBLICATIONS+"   (  "+ PUBLICATIONS_PUBLICATION_NAME +"  ) Values ('First')");
            db.execSQL(" INSERT INTO   "+TABLE_PUBLICATIONS+"   (  "+ PUBLICATIONS_PUBLICATION_NAME +"  ) Values ('Second')");


    //EDITIONS

            db.execSQL(" INSERT INTO   "+TABLE_EDITION+"   (  "+ EDITION_EDITION_NUMBER +"  ) Values ('1.3')");
            db.execSQL(" INSERT INTO   "+TABLE_EDITION+"   (  "+ EDITION_EDITION_NUMBER +"  ) Values ('2')");



    //CATEGORY

            db.execSQL(" INSERT INTO   "+TABLE_CATEGORIES+"   (  "+ CATEGORIES_CATEGORY_NAME +"  ) Values ('Folk')");
            db.execSQL(" INSERT INTO   "+TABLE_CATEGORIES+"   (  "+ CATEGORIES_CATEGORY_NAME +"  ) Values ('Drama')");




        }




    public int []  libraryDetails(){
        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+LIBRARY_STATISTICS_BOOKS_NUMBER+","+LIBRARY_STATISTICS_TOP_RATED_BOOKS+","+LIBRARY_STATISTICS_STAFF_NUMBER+","+LIBRARY_STATISTICS_AWARDS_NUMBER+","+LIBRARY_STATISTICS_COMPUTERS_NUMBER+" FROM " + dataBase.TABLE_LIBRARY_STATISTICS, null);

        cursor.moveToFirst();

        int booksNumber=cursor.getInt(0);
        int topRated=cursor.getInt(1);
        int staffNumber=cursor.getInt(2);
        int awardsNumber=cursor.getInt(3);
        int computersNumber=cursor.getInt(4);

        cursor.close();
        close();

        return new int []{booksNumber,topRated,staffNumber,awardsNumber,computersNumber};

    }


    public void updateNumberOfCopies(int bookInstId, int copies, boolean b){

        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(this.BOOK_INST_COPIES, copies);

        db.update(this.TABLE_BOOK_INST, values, BOOK_INST_ID +"=?", new String[]{String.valueOf(bookInstId)});


        values.clear();

// for lib statistics

        Cursor totalCopiescursor = db.rawQuery(" SELECT "+LIBRARY_STATISTICS_BOOKS_NUMBER+" FROM " + dataBase.TABLE_LIBRARY_STATISTICS,null);

        totalCopiescursor.moveToFirst();

       int totalCopies= totalCopiescursor.getInt(0);

        values.put(this.LIBRARY_STATISTICS_BOOKS_NUMBER, b?++totalCopies:--totalCopies);

        db.update(this.TABLE_LIBRARY_STATISTICS, values, null,null);

        values.clear();
        totalCopiescursor.close();

        close();
    }



    public List<String>  getAuthors(String bookId){



        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+AUTHORS_NAME+" FROM " + dataBase.TABLE_AUTHORS+" INNER JOIN "+TABLE_AUTHORS_BOOKS+" ON "+TABLE_AUTHORS+"."+AUTHORS_ID+"="+TABLE_AUTHORS_BOOKS+"."+AUTHORS_BOOKS_AUTHORS_ID+" WHERE "+TABLE_AUTHORS_BOOKS+"."+AUTHORS_BOOKS_BOOK_ID+"=?", new String [] {bookId});


        List<String> authors = new ArrayList<>();

        if(cursor.moveToFirst()){

            do{

                authors.add(cursor.getString(0));


            }while (cursor.moveToNext());


        }


        cursor.close();
        close();

        return authors;
    }



    public  Map<String,Integer> getBookLanguages(String bookId){



        Map<String,Integer> Langlist =  new HashMap<>();


        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+LANGUAGES_LANG_NAME+","+TABLE_LANGUAGES+"."+LANGUAGES_ID+" FROM " + dataBase.TABLE_BOOK_INST+" INNER JOIN "+TABLE_LANGUAGES+" ON "+BOOK_INST_LANG_ID+"="+TABLE_LANGUAGES+"."+LANGUAGES_ID+" WHERE "+TABLE_BOOK_INST+"."+BOOK_INST_BOOK_ID+"=? GROUP BY "+LANGUAGES_LANG_NAME, new String [] {bookId});




        if(cursor.moveToFirst()) {

            do {

                Langlist.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        close();

        return  Langlist;
    }



    public  Map<String,Integer> getBookFormats(String bookId){



        Map<String,Integer> formatList =  new HashMap<>();


        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+TABLE_FORMATS+"."+ FORMATS_NAME +","+TABLE_FORMATS+"."+ FORMATS_ID +" FROM " + dataBase.TABLE_BOOK_INST+" INNER JOIN "+TABLE_FORMATS+" ON "+TABLE_BOOK_INST+"."+BOOK_INST_FORMAT_ID+"="+TABLE_FORMATS+"."+ FORMATS_ID +" WHERE "+TABLE_BOOK_INST+"."+BOOK_INST_BOOK_ID+"=? GROUP BY "+TABLE_FORMATS+"."+ FORMATS_NAME, new String [] {bookId});




        if(cursor.moveToFirst()) {

            do {

                formatList.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        close();

        return  formatList;
    }


    public  Map<String,Integer> getBookPublications(String bookId){



        Map<String,Integer> PubList =  new HashMap<>();


        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+PUBLICATIONS_PUBLICATION_NAME+","+TABLE_PUBLICATIONS+"."+PUBLICATIONS_ID+" FROM " + dataBase.TABLE_BOOK_INST+" INNER JOIN "+TABLE_PUBLICATIONS+" ON "+BOOK_INST_PUBLICATION_ID+"="+TABLE_PUBLICATIONS+"."+PUBLICATIONS_ID+" WHERE "+BOOK_INST_BOOK_ID+"=? GROUP BY "+PUBLICATIONS_PUBLICATION_NAME, new String [] {bookId});




        if(cursor.moveToFirst()) {

            do {

                PubList.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        close();

        return  PubList;
    }

    public  Map<String,Integer> getBookEditions(String bookId){



        Map<String,Integer> EditionList =  new HashMap<>();


        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+EDITION_EDITION_NUMBER+","+TABLE_EDITION+"."+EDITION_ID+" FROM " + dataBase.TABLE_BOOK_INST+" INNER JOIN "+TABLE_EDITION+" ON "+BOOK_INST_EDITION_ID+"="+TABLE_EDITION+"."+EDITION_ID+" WHERE "+BOOK_INST_BOOK_ID+"=? GROUP BY "+EDITION_EDITION_NUMBER, new String [] {bookId});




        if(cursor.moveToFirst()) {

            do {

                EditionList.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        close();

        return  EditionList;
    }

    public  Map<String,Integer> getBookCategories(String bookId){



        Map<String,Integer> CategoryList =  new HashMap<>();


        Cursor cursor = getWritableDatabase().rawQuery(" SELECT "+CATEGORIES_CATEGORY_NAME+","+TABLE_CATEGORIES+"."+CATEGORIES_ID+" FROM " + dataBase.TABLE_BOOK_INST+" INNER JOIN "+TABLE_CATEGORIES+" ON "+BOOK_INST_CATEGORY_ID+"="+TABLE_CATEGORIES+"."+CATEGORIES_ID+" WHERE "+BOOK_INST_BOOK_ID+"=? GROUP BY "+CATEGORIES_CATEGORY_NAME, new String [] {bookId});




        if(cursor.moveToFirst()) {

            do {

                CategoryList.put(cursor.getString(0),cursor.getInt(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        close();

        return  CategoryList;
    }




    public List<BookInstModel> getBookInstance(String bookId){

        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+BOOK_INST_ID+","+BOOK_INST_LANG_ID+" ,"+BOOK_INST_FORMAT_ID+" ,"+BOOK_INST_PUBLICATION_ID+" ,"+BOOK_INST_EDITION_ID+" ,"+BOOK_INST_CATEGORY_ID+","+BOOK_INST_COPIES+" FROM " + dataBase.TABLE_BOOK_INST+" WHERE "+BOOK_INST_BOOK_ID+"=?", new String [] {bookId});

        List<BookInstModel> bookInstModels = new ArrayList<>();


        if(cursor.moveToFirst()){

            do{


                bookInstModels.add(new BookInstModel(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6)));


            }while (cursor.moveToNext());



            cursor.close();
            close();

        }


return bookInstModels;

    }

    public long register(String email,String password,String user_name,int user_type){


        SQLiteDatabase db =getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(this.USERS_NAME, user_name);
        values.put(this.USERS_USER_TYPE,user_type);
        values.put(this.USERS_EMAIL, email);
        values.put(this.USERS_PASSWORD,password );

        long insertedUserId =   db.insert(this.TABLE_USERS, null, values);


        db.close();

        return insertedUserId;
    }

    public String [] signIn(String email,String password){


        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+USERS_ID+","+USERS_NAME+","+USERS_USER_TYPE+" FROM " + dataBase.TABLE_USERS+ " WHERE "+USERS_EMAIL+"=? AND "+USERS_PASSWORD+"=?",new String [] {email, password});


        if(cursor.getCount()==1){
            cursor.moveToFirst();

            String user_id =cursor.getString(0);
            String name =cursor.getString(1);
            String user_type =cursor.getString(2);
            cursor.close();
            close();
           return  new String[] {user_id,name,user_type};
        }else
            return new String[]{};

    }

//METHODS

    public List<BookModel> getBooks() {


        List<BookModel> bookModelList = new ArrayList<>();

        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+BOOKS_ID+","+BOOKS_TITLE+","+BOOKS_ACQURIED+","+BOOKS_DURATION+","+BOOKS_TOP_RATED+" FROM " + dataBase.TABLE_BOOKS, null);

        cursor.getColumnCount();

        if(cursor.moveToFirst()){

            do{


                bookModelList.add(new BookModel(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));


            }while (cursor.moveToNext());

            cursor.close();
            close();

        }

        return bookModelList;
    }

    //TODO FOR STUDENTS



    public boolean checkEntryRecord(int bookInstId,String userId){

        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+ ENTRIES_BOOKINST_ID +","+ENTRIES_USER_ID+" FROM " + dataBase.TABLE_ENTRIES+" WHERE "+ ENTRIES_BOOKINST_ID +"=? AND "+ENTRIES_USER_ID+"=?",new String [] {String.valueOf(bookInstId), String.valueOf(userId)});


        if(cursor.getCount()>0) {
            cursor.close();
            close();
            return true;
        } else {
            cursor.close();
            close();

            return false;
        }

    }

    public void returnBook(Context context,int bookInstId, String userId)  {

//trigger CREATE TRIGGER update_customer_address UPDATE OF address ON customers
//  BEGIN
//    UPDATE orders SET address = new.address WHERE customer_name = old.name;
//  END;

        SQLiteDatabase db =getWritableDatabase();

        Cursor date=db.rawQuery("SELECT date('now')",null);

        date.moveToFirst();

        String todaysDate=date.getString(0);

        date.close();

        Cursor entrycheck=db.rawQuery("SELECT "+ENTRIES_BORROW_TIME+"," +ENTRIES_BOOK_DURATION+" FROM "+ dataBase.TABLE_ENTRIES+" WHERE "+ ENTRIES_BOOKINST_ID +"=? AND "+ENTRIES_USER_ID+"=?",new String [] {String.valueOf(bookInstId),userId});


        // check if the time was exceeded\

        entrycheck.moveToFirst();

        String borrowTime=entrycheck.getString(0);
        String bookDuration=entrycheck.getString(1);
        entrycheck.close();

        SimpleDateFormat finalDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        Date borrow_date = null;
        Date todays_Date=null;
        try {
            borrow_date = finalDateFormat.parse(borrowTime);
            todays_Date = finalDateFormat.parse(todaysDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar calendar=(Calendar.getInstance());

        calendar.setTime(borrow_date);

        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(bookDuration));

        ContentValues values = new ContentValues();

        if(calendar.getTimeInMillis()<=(todays_Date.getTime())){


//ADD RETURN

            values.put(this.ENTRIES_RETURN_TIME, todaysDate);

            db.update(this.TABLE_ENTRIES, values, ENTRIES_BOOKINST_ID +"=? AND "+ENTRIES_USER_ID+"=?", new String[]{String.valueOf(bookInstId), userId});

            values.clear();

            //ADD FINE

            values.put(this.FINES_ENTRY_ID, bookInstId);
            values.put(this.FINES_AMOUNT, userId);


            db.insert(this.TABLE_FINES, null, values);

            Toast.makeText(context, "Return successful fined 5 euros", Toast.LENGTH_LONG).show();

        }else {


            //REMOVE ENTRY

            db.delete(this.TABLE_ENTRIES, ENTRIES_BOOKINST_ID + "=? AND " + ENTRIES_USER_ID+"=?", new String[]{String.valueOf(bookInstId), userId});

            Toast.makeText(context, "Return successful", Toast.LENGTH_SHORT).show();

        }

        entrycheck.close();
        close();


    }
    public boolean borrowBook(int bookInstId,int userId,String bookDuration) throws ParseException {

    SQLiteDatabase db =getWritableDatabase();

        Cursor date=db.rawQuery("SELECT date('now')",null);
        date.moveToFirst();

        String todaysDate=date.getString(0);

        //check entry table first then restrictions

        Cursor restrictionCursor=db.rawQuery("SELECT "+RESTRICTIONS_BOOKS_RESTRICTION_TYPE+"," +RESTRICTIONS_BOOKS_DEADLINE+" FROM "+ dataBase.TABLE_RESTRICTIONS_BOOKS+" WHERE "+RESTRICTIONS_BOOKS_BOOK_ID+"=?",new String [] {String.valueOf(bookInstId)});

        if(restrictionCursor.getCount()>0){
            restrictionCursor.moveToFirst();

            if(restrictionCursor.getInt(0)==0)// not for borrowing
                return false;
            else{// there is a valid dead line and it will be subtracted from todays date

               String bookDeadLine= restrictionCursor.getString(1);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Date todays_Date = dateFormat.parse(todaysDate);
                Date Book_deadline = dateFormat.parse(bookDeadLine);


                long diff = Book_deadline.getTime()-todays_Date.getTime();


                long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);


                bookDuration= String.valueOf(diffInDays);

                restrictionCursor.close();

            }



        }



        ContentValues values = new ContentValues();

    values.put(this.ENTRIES_BOOKINST_ID, bookInstId);
    values.put(this.ENTRIES_USER_ID, userId);
    values.put(this.ENTRIES_BORROW_TIME, todaysDate);
    values.put(this.ENTRIES_BOOK_DURATION, bookDuration);

    db.insert(this.TABLE_ENTRIES, null, values);

        date.close();
        db.close();

        return true;
}




    //TODO FOR LIBRARIAN



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " +TABLE_LIBRARY_STATISTICS);

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMATS);
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


    public long insertBook(long insertedBookId, String bookTitle, String aquired, String duration, int topRated, String author, String isbn, String language, String format, String publicationName, String edition, String category, String copies, int restriction, String deadline) {



        SQLiteDatabase db =getWritableDatabase();



        Cursor isbnCheck=db.rawQuery("SELECT "+BOOK_INST_ISBN+" FROM "+ dataBase.TABLE_BOOK_INST+" WHERE "+BOOK_INST_ISBN+"=?",new String [] {String.valueOf(isbn)});

if(isbnCheck.getCount()>0){

    isbnCheck.close();
    return -1;
}else {
    ContentValues values = new ContentValues();

if(insertedBookId==-1) {
//adding in books table
    values.put(this.BOOKS_TITLE, bookTitle);
    values.put(this.BOOKS_ACQURIED, aquired);
    values.put(this.BOOKS_DURATION, duration);
    values.put(this.BOOKS_TOP_RATED, topRated);
    insertedBookId = db.insert(this.TABLE_BOOKS, null, values);
    values.clear();

    values.put(this.AUTHORS_NAME, author);
    long authorId = db.replace(this.TABLE_AUTHORS, null, values);
    values.clear();


    values.put(this.AUTHORS_BOOKS_AUTHORS_ID, author);
    values.put(this.AUTHORS_BOOKS_BOOK_ID, insertedBookId);
    db.insert(this.TABLE_AUTHORS_BOOKS, null, values);
    values.clear();

if(restriction>-1) {

    values.put(this.RESTRICTIONS_BOOKS_BOOK_ID, insertedBookId);
    values.put(this.RESTRICTIONS_BOOKS_RESTRICTION_TYPE, restriction);
    values.put(this.RESTRICTIONS_BOOKS_DEADLINE, deadline);
    db.insert(this.TABLE_RESTRICTIONS_BOOKS, null, values);
    values.clear();
}

}


    values.put(this.LANGUAGES_LANG_NAME, language);
    long langId = db.replace(this.TABLE_LANGUAGES, null, values);
    values.clear();


    values.put(this.FORMATS_NAME, language);
    long formatId = db.replace(this.TABLE_FORMATS, null, values);
    values.clear();


    values.put(this.PUBLICATIONS_PUBLICATION_NAME, publicationName);
    long pubId = db.replace(this.TABLE_PUBLICATIONS, null, values);
    values.clear();

    values.put(this.EDITION_EDITION_NUMBER, edition);
    long editionId = db.replace(this.TABLE_EDITION, null, values);
    values.clear();


    values.put(this.CATEGORIES_CATEGORY_NAME, category);
    long categoryId = db.replace(this.TABLE_CATEGORIES, null, values);
    values.clear();


    values.put(this.BOOK_INST_BOOK_ID, insertedBookId);
    values.put(this.BOOK_INST_ISBN, isbn);
    values.put(this.BOOK_INST_LANG_ID, langId);
    values.put(this.BOOK_INST_FORMAT_ID, formatId);
    values.put(this.BOOK_INST_PUBLICATION_ID, pubId);
    values.put(this.BOOK_INST_EDITION_ID, editionId);
    values.put(this.BOOK_INST_CATEGORY_ID, categoryId);
    values.put(this.BOOK_INST_COPIES, copies);
    db.insert(this.TABLE_BOOK_INST, null, values);

    isbnCheck.close();
    close();

    return insertedBookId;
}

    }
}