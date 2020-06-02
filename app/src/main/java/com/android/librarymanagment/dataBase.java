package com.android.librarymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.android.librarymanagment.activities.MainPage;
import com.android.librarymanagment.models.BookModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
                    "CREATE TABLE " + TABLE_LIBRARY_STATISTICS + " (" +
                            LIBRARY_STATISTICS_ID + " INTEGER PRIMARY KEY, " +
                            LIBRARY_STATISTICS_BOOKS_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_TOP_RATED_BOOKS + " INTEGER, " +
                            LIBRARY_STATISTICS_STAFF_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_AWARDS_NUMBER + " INTEGER, " +
                            LIBRARY_STATISTICS_COMPUTERS_NUMBER + " INTEGER)";


            final String TABLE_CREATE_BOOKS =
                    "CREATE TABLE " + TABLE_BOOKS + " (" +
                            BOOKS_ID + " INTEGER PRIMARY KEY, " +
                            BOOKS_TITLE + " TEXT, " +
                            BOOKS_ACQURIED + " INTEGER, " +//0,1,2
                            BOOKS_DURATION + " TEXT, " +
                            BOOKS_TOP_RATED + " INTEGER)";//0,1


//        final String books_title_index = " CREATE INDEX " + Books_title_idx + " ON "+ TABLE_BOOKS +"("+BOOKS_TITLE+")";

            final String TABLE_CREATE_CATEGORIES =
                    "CREATE TABLE " + TABLE_CATEGORIES + " (" +
                            CATEGORIES_ID + " INTEGER PRIMARY KEY, " +
                            CATEGORIES_BOOK_ID + " INTEGER, " +
                            CATEGORIES_CATEGORY_NAME + " TEXT)";
//
//
//
            final String TABLE_CREATE_BOOK_FORMATS =
                    "CREATE TABLE " + TABLE_BOOK_FORMATS + " (" +
                            BOOK_FORMATS_ID + " INTEGER PRIMARY KEY, " +
                            BOOK_FORMATS_BOOK_ID + " INTEGER, " +
                            BOOK_FORMATS_FORMAT + " INTEGER)";
//

            final String TABLE_CREATE_AUTHORS =
                    "CREATE TABLE " + TABLE_AUTHORS + " (" +
                            AUTHORS_ID + " INTEGER PRIMARY KEY, " +
                            AUTHORS_BOOK_ID + " INTEGER, " +
                            AUTHORS_NAME + " TEXT)";


//        final String TABLE_CREATE_PUBLICATIONS =
//                "CREATE TABLE "+ TABLE_PUBLICATIONS +" ("+
//                        PUBLICATIONS_ID + " INTEGER PRIMARY KEY, "+
//                        PUBLICATIONS_BOOK_ID + " INTEGER, "+
//                        PUBLICATIONS_PUBLICATION_NAME + " TEXT, "+
//                        PUBLICATIONS_PUBLICATION_NUMBER + " TEXT)";
//
            final String TABLE_CREATE_LANGUAGES =
                    "CREATE TABLE " + TABLE_LANGUAGES + " (" +
                            LANGUAGES_ID + " INTEGER PRIMARY KEY, " +
                            LANGUAGES_BOOK_ID + " INTEGER, " +
                            LANGUAGES_LANG_NAME + " TEXT, " +
                            LANGUAGES_COPY_NUMBER + " INTEGER)";


            final String TABLE_CREATE_ENTRIES =
                    "CREATE TABLE " + TABLE_ENTRIES + " (" +
                            ENTRIES_ID + " INTEGER PRIMARY KEY, " +
                            ENTRIES_BOOK_ID + " INTEGER, " +
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
                            RESTRICTIONS_BOOKS_RESTRICTION_TYPE + " INTEGER, " +
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
            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('mirdad',1,'7','1')");
            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('treasure island',2,'7','0')");

            db.execSQL("INSERT INTO   books   (  title  ,  acquired  , duration  ,  top_rated  ) Values ('macbeth',0,'7','0')");

            //registration

            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('1',0,'')");

            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('2',1,'2020-6-20')");


            db.execSQL("INSERT INTO   restrictions_books   (  book_id  ,  restriction_type  , deadline ) Values ('3',1,'2020-5-20')");


            //Users
            db.execSQL("INSERT INTO   users   (  name  ,  user_type  , email  ,  password  ) Values ('omar',0,'osarious2@gmail.com','123456')");

            //library
            db.execSQL("INSERT INTO   library_statistics   (  books_number  ,  top_rated_books  , staff_number  ,  awards_number ,computers_number ) Values ('3',1,'5','10','6')");


        }




    public int []  libraryDetails(){
        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+LIBRARY_STATISTICS_BOOKS_NUMBER+","+LIBRARY_STATISTICS_TOP_RATED_BOOKS+","+LIBRARY_STATISTICS_STAFF_NUMBER+","+LIBRARY_STATISTICS_AWARDS_NUMBER+","+LIBRARY_STATISTICS_COMPUTERS_NUMBER+" FROM " + dataBase.TABLE_LIBRARY_STATISTICS, null);

        cursor.moveToFirst();

        int booksNumber=cursor.getInt(0);
        int topRated=cursor.getInt(1);
        int staffNumber=cursor.getInt(2);
        int awardsNumber=cursor.getInt(3);
        int computersNumber=cursor.getInt(4);



        return new int []{booksNumber,topRated,staffNumber,awardsNumber,computersNumber};

    }


    public long register(String email,String password,String user_name,int user_type){


        SQLiteDatabase db =getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(this.USERS_NAME, user_name);
        values.put(this.USERS_USER_TYPE,user_type);
        values.put(this.USERS_EMAIL, email);
        values.put(this.USERS_PASSWORD,password );

        db.insert(this.TABLE_USERS, null, values);

        long numRows = DatabaseUtils.queryNumEntries(db, USERS_ID);


        db.close();

        return numRows;
    }

    public String [] signIn(String email,String password){


        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+USERS_ID+","+USERS_NAME+" FROM " + dataBase.TABLE_USERS+ " WHERE "+USERS_EMAIL+"=? AND "+USERS_PASSWORD+"=?",new String [] {email, password});


        if(cursor.getCount()==1){
            cursor.moveToFirst();

            String user_id =cursor.getString(0);
            String name =cursor.getString(1);
            cursor.close();
            close();
           return  new String[] {user_id,name};
        }else
            return new String[]{};

    }

//METHODS

    public List<BookModel> getBooks() {


        List<BookModel> bookModelList = new ArrayList<>();

        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + dataBase.TABLE_BOOKS, null);

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



    public boolean checkEntryRecord(int bookId,String userId){

        Cursor cursor = getWritableDatabase().rawQuery("SELECT "+ENTRIES_BOOK_ID+","+ENTRIES_USER_ID+" FROM " + dataBase.TABLE_ENTRIES+" WHERE "+ENTRIES_BOOK_ID+"=? AND "+ENTRIES_USER_ID+"=?",new String [] {String.valueOf(bookId), String.valueOf(userId)});


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

    public void returnBook(Context context,int bookId, String userId)  {

//trigger CREATE TRIGGER update_customer_address UPDATE OF address ON customers
//  BEGIN
//    UPDATE orders SET address = new.address WHERE customer_name = old.name;
//  END;

        SQLiteDatabase db =getWritableDatabase();

        Cursor date=db.rawQuery("SELECT date('now')",null);

        date.moveToFirst();

        String todaysDate=date.getString(0);

        date.close();

        Cursor restrictionCursor=db.rawQuery("SELECT "+ENTRIES_BORROW_TIME+"," +ENTRIES_BOOK_DURATION+" FROM "+ dataBase.TABLE_ENTRIES+" WHERE "+ENTRIES_BOOK_ID+"=? AND "+ENTRIES_USER_ID+"=?",new String [] {String.valueOf(bookId),userId});


        // check if the time was exceeded\

        restrictionCursor.moveToFirst();

        String borrowTime=restrictionCursor.getString(0);
        String bookDuration=restrictionCursor.getString(1);
        restrictionCursor.close();

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

            db.update(this.TABLE_ENTRIES, values, ENTRIES_BOOK_ID+"=? AND "+ENTRIES_USER_ID+"=?", new String[]{String.valueOf(bookId), userId});

            values.clear();

            //ADD FINE

            values.put(this.FINES_ENTRY_ID, bookId);
            values.put(this.FINES_AMOUNT, userId);


            db.insert(this.TABLE_FINES, null, values);

            Toast.makeText(context, "Return successful fined 5 euros", Toast.LENGTH_LONG).show();

        }else {


            //REMOVE ENTRY

            db.delete(this.TABLE_ENTRIES, ENTRIES_BOOK_ID + "=? AND " + ENTRIES_USER_ID+"=?", new String[]{String.valueOf(bookId), userId});

            Toast.makeText(context, "Return successful", Toast.LENGTH_SHORT).show();

        }

        restrictionCursor.close();
        close();


    }
    public boolean borrowBook(int bookId,int userId,String bookDuration) throws ParseException {

    SQLiteDatabase db =getWritableDatabase();

        Cursor date=db.rawQuery("SELECT date('now')",null);
        date.moveToFirst();

        String todaysDate=date.getString(0);

        //check entry table first then restrictions

        Cursor restrictionCursor=db.rawQuery("SELECT "+RESTRICTIONS_BOOKS_RESTRICTION_TYPE+"," +RESTRICTIONS_BOOKS_DEADLINE+" FROM "+ dataBase.TABLE_RESTRICTIONS_BOOKS+" WHERE "+RESTRICTIONS_BOOKS_BOOK_ID+"=?",new String [] {String.valueOf(bookId)});

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

    values.put(this.ENTRIES_BOOK_ID, bookId);
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