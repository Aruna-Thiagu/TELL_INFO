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

public class DSHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_SCHOOLNUM = "schoolnum";
    private static final String TAG = "DBHelper";
    private DSHelper.DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "Aruna8";
    private static final String SQLITE_TABLE = "School";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_SCHOOLNUM+ "," +
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

    public DSHelper(Context ctx) {
        this.mCtx = ctx;
    }

    public DSHelper open() throws SQLException {
        mDbHelper = new DSHelper.DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createschool(String code, String name,
                             String address, String schoolnum) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_SCHOOLNUM, schoolnum);
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllSchools() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchSchoolsByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_SCHOOLNUM},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS,  KEY_SCHOOLNUM},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllSchools() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                        KEY_CODE, KEY_NAME,KEY_ADDRESS,  KEY_SCHOOLNUM},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeSchools() {

        createschool("vivekalaya","Vivekalaya matriculation School" ,"Trichy Road","0422-5391232");
        createschool("LMHSS","Laurel Matric Hr Sec School"," Kavundampalayam"," 0422-2400329");
        createschool("Ramakrishna","Sri Ramakrishna Mission Vidyalaya","Periyanaickenpalayam","0422-692676/2692676/2695451/2695453");
        createschool("Kalaimagal","Kovai Kalaimagal Matriculation School","P N Palayam Road","0422-2562215");
        createschool("NM","National Model Matriculation School","Anna Nagar","0422-2575318");
        createschool("RRMS","Ramakrishna Ruckmaniammal Matriculation School","Ondipudur","0422-2273643");
        createschool("SRSV","Sri Jayendra Saraswathi Vidyalaya Higher Secondary School","Aerodrome Road","0422-2270616");
        createschool("Alvernia","Alvernia Matriculation Higher Secondary School","Ramanathapuram","0422-2316479/2316205");
        createschool("AMPS","Alwyn English Primary School","Gandhipuram","0422-2492595");
        createschool("AVP","Avp Trust National Matric Higher Secondary school","Athupalayam","0422-2477370");
        createschool("Azad","Azad Matriculation School","Kangeyam Road","0422-2423413");
        createschool("BMS","Bharathi Matric School","Thadagam Road","0422-2442187");
        createschool("BVB","Bharatiya Vidya Bhavan Matric HSS","RS Puram","0422-2556351");
        createschool("Brindavan","Brindavan Matric Hr Sec School","Mettupalayam Road","0422-2435811");
        createschool("CMS","C M S Secondary School","Ganapathy","0422-2531050");
        createschool("CSI","C S I Matriculation School","Race Course Road","0422-2212380");
        createschool("Carmel","Carmel Garden Matriculation Higher Secondary School","Puliakulam Road","0422-2220904");
        createschool("CVB","Chavara Vithiya Bhavan Matriculation Higher Secondary School","Bharathiar University PO","0422-2423451");
        createschool("EBN","E.Balakrishna Naidu Matric Hr Sec School","Avaram Palayam Road","0422-2561897");
        createschool("Ganga","Ganga Nagar Matric School","Ramanathapuram","0422-2312992");
        createschool("KVMS","K Venkatesalu Matriculation School","Dr Radhakrishna Road","0422-2524242");
        createschool("kadri","Kadri Mills High School","Ondipudur","0422-2273093");
        createschool("Lisieux","Lisieux Higher Sec School","Alagesan Road","0422-2440537");
        createschool("MDS","M D S Mani Oxford Matriculation School","Uppilipalayam","0422-2591219");
        createschool("MGM","M G M Matriculation School","KNG Pudur","0422-2400420");
        createschool("MSSD","M S S D Higher Secondary School","Vadavalli","0422-2425479");
        createschool("MFS","Mani Feeder School","Lakshmi Mills Colony","0422-2210851");
        createschool("MKN","Mathar Kalvi Nilayam Girls High School","Avinashi Road","0422-2213201");
        createschool("Nehru","Nehru Vidyalaya","R S Puram","0422-2473109");
        createschool("PSGR","P S G R Children School","Damu Nagar","0422-2316850");
        createschool("Perks","Perks Matric Hr Sec School","Uppilipalayam","0422-2574290");
        createschool("Presentation","Presentation Convent High School","Head Post Office Road","0422-2392458");
        createschool("RMHS","Rajalakshmi Mills High School","Singanallur","0422-2574277");
        createschool("RSM","Ramnagar Suburban Matric School","Ramnagar","0422-2231432");
        createschool("SBOA","S B O A Matriculation and Higher Secondary School","Thelugupalayam","0422-2470143");
        createschool("SES","S E S Matriculation School","Mamara Thottam Ganapathy","0422-2533996");
        createschool("SVM","Saradha Vidyalaya Matric School","Velampalayam","0422-2476669");
        createschool("Sarvajana","Sarvajana Higher Secondary School","Peelamedu","0422-2572310");
        createschool("SDA","Seventh Day Adventist Matric School","Kattoor","0422-2231747");
        createschool("KNM","Shri K Krishnaswamy Naidu Memorial High School","Civil Aerodrome Road","0422-2573002");
        createschool("SNM","Sidha Naidu Memorial Matric School","Sidhapudur","0422-2213924");
        createschool("Avinashilingam","Sri Avinashilingam Higher Secondary School For Girls","Bharathi Park Road","0422-2437207");
        createschool("Kikani","Sri Baldevdas Kikani Vidyamandir High School","Sastri Road","0422-2554733");
        createschool("SGN","Sri Gopal Naidu Children School","Maniam Pappammal Street","0422-2565124");
        createschool("SPN","Sri SP Narasimabalu Naidu Memorial High School","Mill Road","0422-2472833");
        createschool("Antony's","St Antonys R C Boys High School","Puliakulam","0422-2315525");
        createschool("AWAS","St Antonys Welfare Assn Stava Nursery School","Indira Nagar","0422-2313269");
        createschool("Francis","St Francis Anglo Indian Girls High School","Trichy Road","0422-2301519");
        createschool("Joseph's","0St Josephs English Medium School","Singanallur","0422-2271367");
        createschool("Marys","St Marys Girls High School","Trichy Road","0422-2213260");
        createschool("Michael's","St Michaels Higher Sec School","Big Bazaar Street","0422-2393759");
        createschool("Philomena","St Philomena High School For Girls","Sowripalayam","0422-2573825");
        createschool("Stanes","Stanes High School","Avinashi Road","0422-2213952");
        createschool("Suburban","Suburban High School","Rajaji Road","0422-2230544");
        createschool("TNGM","Thiyagi N G Ramaswamy Memorial High School","Varadharajapuram","0422-2575677");
        createschool("VVS","Vasavi Vidyalaya school","Vysial Street","0422-2391290");
        createschool("VMS","Veerasamy Mudaliar High School","Veerasamy Mudaliar School Road","0422-2395670");
        createschool("VNS","Vidhya Niketan Matric Hr Secondary School","Race Course","0422-2217466");
        createschool("YWCA","YWCA Matriculation Higher Secondary School","Avinashi Road","0422-2215717/2210278");
        createschool("ALG","ALG Matriculation School","Damunagar","0422-2316750");
        createschool("Manis","Mani Higher Secondary School","P N Palayam","0422-2213406");
        createschool("AHSS","Angappa Higher Secondary school","Goundampalayam","0422-2400042");
        createschool("ASSS","Angappa Senior Secondary School","S. B. Colony","0422-2447532");
        createschool("CIRS","Chinmaya International Residential School","Siruvani Main Road","0422-2613300");
        createschool("HOMS","Hari Om Matriculation School","NGGO Colony","0422-2643435");
        createschool("TARC","T A Ramalingam Chettiar Higher Secondary School","Saibaba Colony","0422-2441791");
        createschool("VVMSS","Vasavi Vidyalaya Matriculation School","Telungupalayam Pirivu","0422-2341287");
    }
}

