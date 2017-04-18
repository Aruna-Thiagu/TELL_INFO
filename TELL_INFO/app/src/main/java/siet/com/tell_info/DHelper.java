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

public class DHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CABNUM = "cabnum";
    public static final String KEY_EMAIL = "email";
    private static final String TAG = "DHelper";
    private DHelper.DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "Aruna3";
    private static final String SQLITE_TABLE = "School";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_CABNUM+ "," +
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

    public DHelper(Context ctx) {
        this.mCtx1 = ctx;
    }

    public DHelper open() throws SQLException {
        mDbHelper = new DHelper.DatabaseHelper(mCtx1);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCabs(String code, String name,
                           String address, String cabnum, String email) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_CABNUM, cabnum);
        initialValues.put(KEY_EMAIL, email);
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllCabs() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchCabsByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_CABNUM, KEY_EMAIL},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_CABNUM, KEY_EMAIL},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllCabs() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                        KEY_CODE, KEY_NAME,KEY_ADDRESS, KEY_CABNUM, KEY_EMAIL},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeCabs() {

        createCabs("Fast track", "Fast track call taxi ", "3, puthiyavar nagar, avarampalayam road, near ramakrishna Kalyana mandapam New siddhapudhur,coimbatore-641044", " 04222200000", "info@fasttrackcalltaxi.in");
        createCabs("JB", "JB Cabs", "39 Venkata Krishna Road (w), R S Puram,Coimbatore-641002", "9442102095/04222432095/9842002095", " jbcabs.coimbatore@gmail.com ");
        createCabs("Coimbatore Taxi", "Royal Travels", " 12/36 - D1,, Airport Road,, Civil Aerodrome Post, SITRA,Coimbatore-641014", "0422-2593545/0422-2593546/7373812345/9842335457/ 9942435457", "coimbatoretaxi@gmail.com/support@coimbatoretaxi.com/bala@coimbatoretaxi.com");
        createCabs("J4U", "J4U Tours & Travels", "Old no 12/7D, New No 1 ,TVS Nagar (OPP SVT SPINNING MILL), Thadagam Road,, (Near CSI Church),coimbatore-641025", "8056221191/9894401072/  04222406550", "");
        createCabs("Coimbatore City Taxi ","KM Tours & Travels ","#11, Gk Subramaniam Nagar, Hare Krishna Illam, Alagu Nagar, Civil Aerodrome Post, Peelamedu,coimbatore-641014","9865277668/9842320991/  9842599777","balukm111@gmail.com/info@coimbatorecitytaxi.com");
        createCabs("SouthIndiaPackages","Nation Tours & Travels","1/3-16 Annanagar Avinashi Road,coimbatore-641062","9994566158/8883373661 ","info@nationtourstravels.com");
        createCabs("Kannan","Kannan Tours & Travels Pvt Ltd","1273,TrichyRoad,Coimbatore-641018"," : 04222302280/2301281/4393291/4393281","contact@kannantours.com/reservations@kannantours.com");
        createCabs("Amman","Amman Call Taxi","50,Thudiyalur Road, Near Indian Oil Petrol Bunk,Saravanam Patti,Coimbatore-641006","9944027744","");
        createCabs("South Zone","South Zone Taxi ","No. 49, Kasthuribai 4th Street, Ganapathy, Near Vinayak Apartments,Coimbatore-641006","04226060604/04226060605","southzonetaxi@gmail.com ");
        createCabs("Gounder","Gounder Cabs","Shop No-G-11, K.S Tower, 84, Thoddarayan Koil Main St, Kattoor, Ram Nagar, Coimbatore-641009","04222235328","");
        createCabs("Connect Meter","Connect Meter Taxi","No 167,Siva sakthi Colony,New Siddhapudur,Near GP Signal,Coimbatore-641044","04224999599","");
        createCabs("Friends","Friends Cab"," Near Canara Bank, Sirumugai,Coimbatore-641302","","");
        createCabs("Best","Best Call Taxi","527, First Floor, D B Road, RS Puram,Coimbatore-641002","","");
        createCabs("Amman","Amman cabs","Irugur main road,ondipudur,Coimbatore-641016","9843659888","");
        createCabs("Jayam","Jayam Call Taxi"," 1-177,T N H B,Neethajipuram East,Neelikonampalayam Post,Coimbatore-641033","9443793283","");
        createCabs("Capital","Capital Call Taxi","164,N.S.R.Road,SaiBaba Colony,Coimbatore-641011","04222454444","");
        createCabs("Taxi","Taxi Taxi","374,100 FeetRd,Gandipuram,Coimbatore-641012"," 04224050607","");
        createCabs("Arrow Cabs","Arrow Cabs Rental India Pvt Ltd","no.2-93/1,mahaliamman street,kurompalayam,mg puthur post,sullur prive,Coimbatore-641406","8190000101","");
        createCabs("60603030 ","60603030 ","Vadavalli,Coimbatore","04226060303","");
        createCabs("First Track","First Track Taxi Stand","87/22A,Avinashi Rd,Indira Nagar,Civil Aerodrome Post,Peelamedu,Coimbatore-641014","9944733555","");
        createCabs("Kovai","Kovai Call Taxi","56,Ramalingam Road(East),Near Getit Infotech,Poo Market,Coimbatore- 641002","04222473100","");
        createCabs("Abi","Abi Call Taxi","333, D.B Road,RS Puram,Coimbatore-641001","042247474747/2474747/2575757","info@abicalltaxi.com");
        createCabs("LOAD","LOAD TAXI","BAGAVATHI LOAD TAXI TRANSPORT,44,S.P.Kannuswamy Gounder Street,Sanganoor Main Road,Coimbatore-641027","04222333033/9788611845/9750444511/9750444522/9750444533/9750444577/9750444588/9750444599","info@loadtaxi.in/ cbe@loadtaxi.in");
        createCabs("Zolo","Zolo Cab ","7/12, Vishnunagar, Naickanoor, no 4 Veerapandi(po),Coimbatore-641019","8489910100 ","admin@ekzilla.com/http://www.ekzilla.com");
        createCabs("SIVA","SIVA TRAVELS & TAXI","37/1,3 Cross St,Ganapathy,Bharathi Nagar,Saibaba Colony,Coimbatore-641006","04222512121/04222512120/9842644400/9942644400/9944644400","call@sivataxi.com");
        createCabs("SMART","SMART TAXI"," Door No. 9,, Venkatasamy Rd, C.K.Colony, B.K.R Nagar,New Siddhapudur,Coimbatore-641044","04224567567","");
        createCabs("Sitraa","Sitraa Call Taxi","206, Avinashi Road, Hope College, Avinashi Road, Near Centre Bank ATM, Peelamedu, Coimbatore-641014"," 098422 41213","");
        createCabs("Ola","Ola Cabs"," Gowtham Arcade, 208, W TV Swamy Rd, R.S. Puram, Coimbatore-641002",""," security@olacabs.com/ media@olacabs.com");
        createCabs("Taxi","Taxi Stand","Near Gandhi Park, Gandhi Park, R.S. Puram, Coimbatore-641002","","");
        createCabs("CoimbatoreTaxi","Arrow Travels India Pvt Ltd"," #22-B 3rd ganeshlayout near Ganapathi Bus stand,Coimbatore-641006","8682070707/8682848586/9025410102/04224356586 ","booking@arrowcabs.in");
        createCabs("Thevar","Thevar Cabs"," No.1681, UKK Complex, Trichy Road, Ramanathapuram, Opposite All India Radio,Coimbatore-641045","9659432051/9003438371","thevarcabs@yahoo.com");
        createCabs("iTAXI","Call Taxi Coimbatore-iTAXI ","9B Sathyamangalam Road, Ramakrishna Mill Bus Stop,Ganapathy Post,Coimbatore-641006","8015080150","info@itaxiindia.com/admin@itaxi.com");
        createCabs("First Track","First Track Taxi Stand"," 87/22A, Avinashi Rd, Indira Nagar, Civil Aerodrome Post, Peelamedu, Coimbatore-641014","99447 33555","");
        createCabs("Divya","Divya call taxi"," Anna Nagar, Peelamedu, Coimbatore-641004","9944562233","");
        createCabs("Sk","Sk Cabs","61/102, VKK Menon Road, New Siddhapudur, Coimbatore-641044"," 04224221787","");
        createCabs("Bee Yes ","Bee Yes Travels Coimbatore Ooty Taxi","Do/No 3/17 Sundram Illam, St.Joseph Nagar , Kavundampalayam, Coimbatore-641030","9843049975/9443082345","coimbatoreootytaxi@gmail.com");
        createCabs("Travels","Travels and Call Taxi","27, Gopal Swamy Koil Street, Gopal Swamy Koil Street, Ganapathi, Coimbatore-641006"," 04222330044","");
        createCabs("GREENWAY ","GREENWAY TAXII","10/76, MAYURA COMPLEX 1ST FLOOR, METTUPALAYAM ROAD, NGGO COLONY GATE, Coimbatore-641017","04224007700/7867097284","");
        createCabs("Outstation Taxi","Aasai Tours And Travels ","14, sasthri road, 2nd street, maruthamalai main road,, Coimbatore-641041","9042222111/9965493746"," aasaicabs007@gmail.com");

    }
}
