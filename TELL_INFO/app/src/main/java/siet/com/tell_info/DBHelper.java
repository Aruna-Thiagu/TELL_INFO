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

public class DBHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_HOSPITALNUM = "hospitalnum";
    public static final String KEY_CELLNUM = "cellnum";
    public static final String KEY_EMAIL = "email";
    private static final String TAG = "DBHelper";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "Aruna";
    private static final String SQLITE_TABLE = "Hospital";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_HOSPITALNUM+ "," +
                    KEY_CELLNUM+ "," +
                    KEY_EMAIL+ "," +
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

    public DBHelper(Context ctx) {
        this.mCtx = ctx;
    }

    public DBHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createHospital(String code, String name,
                               String address, String hospitalnum, String cellnum, String email) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_HOSPITALNUM, hospitalnum);
        initialValues.put(KEY_CELLNUM, cellnum);
        initialValues.put(KEY_EMAIL, email);
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllhospital() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchHospitalByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_HOSPITALNUM, KEY_CELLNUM, KEY_EMAIL},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_HOSPITALNUM, KEY_CELLNUM, KEY_EMAIL},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllHospital() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                        KEY_CODE, KEY_NAME,KEY_ADDRESS, KEY_HOSPITALNUM, KEY_CELLNUM, KEY_EMAIL},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeHospitals() {
        createHospital("KMCH", "Kovai Medical Center And Hospital", "3209, Avanashi Road,Coimbatore-641014", "+91-422-4323800/3083800","04224324433/ 4324433/ 8220283322", "getwell@kmchhospitals.com");
        createHospital("Lotus", "Lotus Eye Hospital and Institute ", "770/12 Avinashi Road,Civil Aerodrome post,Coimbatore–641014", "0422-4229999","0422-4229900","asstmtmgroperation.lotusplm@gmail.com/ counsellinglotus@gmail.com/patientcare.am@lotuseye.org/ counselling.plm@lotuseye.org");
        createHospital("LOTUS", "Lotus Eye Hospital and Institute ", "155B,East Periyasamy Road,Near Chinthamani,North Coimbatore–641002", "0422–4239999","0422-4239900", "rspcounselling@gmail.com/ eyelotusoperations@gmail.com");
        createHospital("KMCH CITY", "KMCH City Center", "No.18,Vivekananda Road,Ram Nagar, Coimbatore-641009", "0422-4378720","0422-2232511", "getwell@kmchhospitals.com");
        createHospital("KMCH SULUR", "KMCH Sulur Hospital", "242 b,Trichy Road,Sulur, Coimbatore-641402", "0422-2682940","0422-2682941,2682942", "getwell@kmchhospitals.com");
        createHospital("N.G", "N.G.HOSPITALS PVT.LTD", "577,Trichy Road,(Near B-5 Police Station),Singanallur,Coimbatore–641005.", "0422-2595963","9865755568/9865755561", " info@nghospitals.in");
        createHospital("PSG", "PSG Hospitals", "Peelamedu,Coimbatore-641004", "0422-2570170 (7 lines)/2598822 (10 lines) ","9952149911 ", "psghospitals@yahoo.co.in");
        createHospital("KURINJI", "KURINJI HOSPITAL", "522/3,UDAYAMPALAYAM ROAD,(Near NAVA INDIA Signal,behind EB Station)SOWRIPALAYAM POST,COIMBATORE–28", "0422-4327777/4327575/2562744/2562766","9361694436", " kurinjihospital@gmail.com/ itkurinjihospital@gmail.com");
        createHospital("GEETHASREE", "GEETHASREE HOSPITALS", "No103 Poonthottam Nagar, Near Kalapatti Pirivu,Behind ICICI Bank,Sathy Road, Saravanampatti,Coimbatore.", "0422-2666325","9840477776", "geethasreehospitals@gmail.com/info@geethasreehospitals.com");
        createHospital("Child Trust", "Coimbatore Child Trust Hospital Pvt Ltd", "111,Nanjappa Nagar Trichy Road,In between Singanallur to Ramanathapuram Rajalakshmi Mills Stop,Singanallur, Coimbatore-641005", "0422-2576111, 2576222","9842811198", "ccth.cbe@gmail.com");
        createHospital("Kuppuswamy Naidu", "G.Kuppuswamy Naidu Memorial Hospital", "P.B. No. 6327, Nethaji Road,Pappanaickenpalayam,Coimbatore-641037", "0422-2245000","", "gknmh@vsnl.com");
        createHospital("Ramakrishna", "Sri Ramakrishna Hospital", " No:395,Sarojini Naidu Road, Sidhapudur,Coimbatore-641044", "0422-4500000","9842285100", "sriramakrishnahospital@snrsonstrust.org");
        createHospital("Saraswathi", "SARASWATHI HOSPITAL", "152 A,153, Trichy Road, Chinthamanipudur,Coimbatore-641103", "0422-6633000/6532432/6532433","9944225133 ", "saraswathihospital@gmail.com");
        createHospital("KG", "KG Hospital", "5,Government Arts College Road,Coimbatore 641018", "0422-2212121/2219191","", "drgb@kggroup.com/www.kghospital.com");
        createHospital("JOSEPH", "JOSEPH HOSPITAL", "1st Cross,Thirumagal Nagar,Peelamedu Pudur,Coimbatore-641004", " 0422-2562345","9843010449", "info@josephhospital.net");
        createHospital("GR", "GR Hospital", " Udayampalayam, Coimbatore-641028", "0422-2315717","", "");
        createHospital("Abirami", "Sree Abirami Hospital (P) Ltd", "33,Madukkarai Road, Sundarapuram,Coimbatore-24.", "0422-2466666","", "");
        createHospital("Ashwin", "Ashwin Hospital", "1,Near G P Theatre,Alamu Nagar,Sathy Main Road,Gandhipuram,Coimbatore – 641012", "0422-2525252/4389966"," 9787730400", "ashwinhospital@yahoo.com/ashwinhospitalcbe@gmail.com ");
        createHospital("Ortho-One", "Ortho-One Orthopaedic Speciality Centre", "657, Trichy Road, Singanallur,Coimbatore-641005", "0422-4055100/2317118/2317119 ","", " info@ortho-one.in/ortho.one@gmail.com ");


    }
}
