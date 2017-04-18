package siet.com.tell_info;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gokul1827 on 29-03-2017.
 */

public class DHRHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_HOTELNUM = "hotelnum";
   // public static final String KEY_EMAIL = "email";
    private static final String TAG = "DHelper";
    private DHRHelper.DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "Aruna10";
    private static final String SQLITE_TABLE = "Hotel";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_HOTELNUM+ "," +
                  // KEY_EMAIL+ "," +
                    " UNIQUE (" + KEY_CODE +"));";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DHRHelper(Context ctx) {
        this.mCtx1 = ctx;
    }

    public DHRHelper open() throws SQLException {
        mDbHelper = new DHRHelper.DatabaseHelper(mCtx1);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createhotel(String code, String name,
                           String address, String hotelnum) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_HOTELNUM, hotelnum);
       // initialValues.put(KEY_EMAIL, email);
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllHotels() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchHotelByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_HOTELNUM},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_HOTELNUM},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllHotels() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                        KEY_CODE, KEY_NAME,KEY_ADDRESS, KEY_HOTELNUM},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeHotels() {

        createhotel("Annamalai","Annamalai Hotel","STATE BANK ROAD","04222238000");
        createhotel("CAG","Cag Pride","Barathiar Road","04222527777");
        createhotel("HERITAGE","Heritage Inn","Ramnagar","04222231451");
        createhotel("ARADANA","Hotel Aradana","UKKADAM","04222394101");
        createhotel("ASWINI","Hotel Aswini","RAM NAGAR","04222235454");
        createhotel("CITY","Hotel City Tower","Ram Nagar","04222230681");
        createhotel("DIANA","Hotel Diana","RAMNAGAR","04222233366");
        createhotel("JYOTHI","Hotel Jyothi","GEETHA HALL ROAD","04222300077");
        createhotel("KARPAGAM","Hotel Karpagam","RAMNAGAR","04222230052");
        createhotel("LATA","Hotel Lata A/C","GANDHIPURAM","04222497910");
        createhotel("POORNIMA","Hotel Poornima","RAMNAGAR","04222395136");
        createhotel("PRESIDENT","Hotel President Park","N H ROAD","04222392800");
        createhotel("RAJA","Hotel Raja","RAILWAY STATION","04222303495");
        createhotel("RUBY","Hotel Ruby","GEETHA HALL ROAD","04222300271");
        createhotel("GANAPATHY","Hotel Sri Ganapathy","Sasthri Road","04222234814");
        createhotel("SRIRAM","Hotel Sriram Combines","GEETHA HALL ROAD","04222300609");
        createhotel("SUPER","Hotel Super","R S PURAM","04222551727");
        createhotel("SURYA","Hotel Surya International","Race Course Road","04222217751 – 55");
        createhotel("VIJAI","Hotel Vijai Paradise","SAIBABA COLONY","04222452222");
        createhotel("NILGIRIS","Nilgiri's Nest","Avinashi Road","04222217133");
        createhotel("SAS","Sas Residency Hotel (P) Limited","Avinashi Road","04222201234");
        createhotel("ANNAPOORNA","Sree Annapoorna Lodging","Mettuppalayam Road","04222437722");
        createhotel("SUGAM","Sugam Hotels","R.S. Puram","04222555555");
        createhotel("RESIDENCY","The Residency","Avinashi Road","04222201234");
        createhotel("ALANKAR","Hotel Alankar Grandé","Ramnagar","04222238888");
        createhotel("KR","KR Residency","opp.KMCH, Sitra","04222905666");
        createhotel("Gokulam","Gokulam Park","Near Lee Meridien Hotel","04224523030");
        createhotel("Chenthur","Chenthur Park","Avinashi Road, Sitra","9842276111");
        createhotel("Le Meridien","Le Meridien Coimbatore","CexusNagar,Neelambur","04222364343");
        createhotel("Arcadia","The Arcadia","Poongothai nagar, Civil Aerodrome","04224567777");
        createhotel("Orbis","The Orbis","Hope College","9677715900");
        createhotel("Skylite","Skylite Hotels","Madhusudhan Layout,Civil Aerodrome", "8870066656");
        createhotel("SBS","Hotel SBS Grand","Masakalipalayam road, Hope College", "9042276111");
        createhotel("Aloft","Aloft Coimbatore Singanallur","Srinivasa Garden, Uppilipalayam", "04226656000");






    }
}