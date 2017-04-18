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

public class DataHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_COLLEGENUM = "collegenum";
    public static final String KEY_EMAIL = "email";
    private static final String TAG = "DataHelper";
    private DataHelper.DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "Aruna1";
    private static final String SQLITE_TABLE = "College";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NAME + "," +
                    KEY_ADDRESS + "," +
                    KEY_COLLEGENUM+ "," +
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

    public DataHelper(Context ctx) {
        this.mCtx1 = ctx;
    }

    public DataHelper open() throws SQLException {
        mDbHelper = new DataHelper.DatabaseHelper(mCtx1);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createcollege(String code, String name,
                              String address, String collegenum, String email) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_ADDRESS, address);
        initialValues.put(KEY_COLLEGENUM, collegenum);
        initialValues.put(KEY_EMAIL, email);
        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllcollege() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchCollegeByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_COLLEGENUM, KEY_EMAIL},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ID,
                            KEY_CODE, KEY_NAME, KEY_ADDRESS, KEY_COLLEGENUM, KEY_EMAIL},
                    KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllCollege() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,
                        KEY_CODE, KEY_NAME,KEY_ADDRESS, KEY_COLLEGENUM, KEY_EMAIL},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeCollege() {

        createcollege("SNMV","SNMV COLLEGE OF ARTS AND SCIENCE","Malumichampatti","04222610894/04222610895/9842203080","www.snmv.ac.in");
        createcollege("RSCAS","RATHINAVEL SUBRAMANIAM COLLEGE OF ARTS AND SCIENCE","Sulur","04222687603/04222687480/04222687421/9443329872/9363159490","www.rvscas.ac.in");
        createcollege("NGP","DR. NGP ARTS AND SCIENCE COLLEGE","Kalapatti","04222369252/04222369100/04222628944","www.drngpasc.ac.in");
        createcollege("LNV","LAKSHMI NARAYANAN VISALAKSHI COLLEGE OF ARTS & SCIENCE FOR WOMEN","Podanur","04222655247/04222495328/9894151971","");
        createcollege("VLB","VLB JANAKIAMMAL COLLEGE OF ARTS AND SCIENCE","Kovaipudur","04222605162/04222605163","www.vlbjcas.ac.in");
        createcollege("CSI","CSI BISHOP APPASAMY COLLEGE OF ARTS AND SCIENCE","Race Course","04222221840/04224279302/04224279303","www.csibacas.org");
        createcollege("MASC","MAHARAJA ARTS AND SCIENCE COLLEGE","Neelambur","04222360873/04222360874/04222360875","www.maharaja.in");
        createcollege("PSGR","PSGR KRISHNAMAL COLLEGE FOR WOMEN","Peelamedu","04222572222/04224295959","www.psgrkc.ac.in");
        createcollege("SMS","SMS COLLEGE OF ARTS & SCIENCE","Perur","04222349988/04222348877","www.cherancolleges.org");
        createcollege("CMS","CMS COLLEGE OF SCIENCE AND COMMERCE","Ganapathy","04226534004/04222667158","www.cmscbe.com");
        createcollege("CBM","CBM COLLEGE","Kovaipudur","04222607259","www.cbmcollege.com");
        createcollege("SANKARA","SANKARA COLLEGE OF SCIENCE AND COMMERCE","Saravanampatti","04222665934/04222665686/04224313500/9843099535/ 9894059338","www.sankara.ac.in");
        createcollege("KASC","KONGUNADU ARTS AND SCIENCE COLLEGE","","04222642095/04222642236/04222646588","www.kongunaducollege.ac.in");
        createcollege("GRDCS","GR DAMODARAN COLLEGE OF SCIENCE","Sitra","04222572719/04222576557/04222591863","www.grd.org");
        createcollege("HINDUSTAN","HINDUSTHAN COLLEGE OF ARTS AND SCIENCE","NavaIndia","04224440555/04224440556/04224440557/9843133333/ 9943344433","www.hindusthan.net");
        createcollege("TSA","THAVATHIRU SANTHALINGA ADIGALLAR ARTS AND SCIENCE","Perur","04222607995/9894761125","www.sivasiva.in");
        createcollege("NEHRU","NEHRU ARTS AND SCIENCE COLLEGE","Kuniyamuthur","04222252673/04222252671/04222252672","www.nehrucolleges.net");
        createcollege("KSG","KSG COLLEGEOF ARTS AND SCIENCE","Varadharajapuram","04222574913/04222577136/8148277777/8148377777/  8148477777","www.ksgcollege.com");
        createcollege("TASC","TEXCITY ARTS AND SCIENCE COLLEGE","Madukarai","04222622622/04222623622","www.texcityinstitutions.com");
        createcollege("NC","NIRMALA COLLEGE FOR WOMEN","Red Fields","","www.nirmalacollegeonline.ac.in");
        createcollege("SKASC","SRI KRISHNA ARTS AND SCIENCE COLLEGE","Kuniyamuthur","04222678400/04222678401/04222678402","www.skasc.ac.in");
        createcollege("PSG","PSG COLLEGE OF ARTS AND SCIENCE","Sitra","04224397901/04224303300","www.psgcas.ac.in");
        createcollege("PPGIT","PPG INSTITUTE OF TECHNOLOGY","Saravanampatti","04222667555/9843042230","www.ppg.edu.in");
        createcollege("INFO","INFO INSTITUTE OF ENGINEERING","Kovilpalayam","04222363700/04222363701/04222363702","www.infoengg.com");
        createcollege("SKVI","SRI KRISHNA AND VLB INSTITUTIONS","Kovaipudur","04222604545/04222604548/04222604549","www.vlbkrishna.edu.in");
        createcollege("CMS","CMS COLLEGE OF ENGINEERING & TECHNOLOGY","Othakkalmandapam","04222636053/04222636055/04222636050/9688194333/9688294333/9688394333","www.cmscet.com");
        createcollege("KCT","KUMARAGURU COLLEGE OF TECHNOLOGY","Chinnavedampatti","04222661100/04222661121"," www.kct.ac.in");
        createcollege("TCE","TAMIL NADU COLLEGE OF ENGINEERING","Karumathampatti","04213200244/04212332588","www.tnce.in");
        createcollege("KATHIR","KATHIR COLLEGE OF ENGINEERING","Neelambur","04226554778/04222203737/9842256778/9566533666","www.kathir.ac.in");
        createcollege("SNSCT","SNS COLLEGE OF TECHNOLOGY","Saravanampatti","04222666264/04226543407","www.snsct.org");
        createcollege("REC","RANGANATHAN ENGINEERING COLLEGE","Thondamuthur","04222619100/9500981245/9500981246/9488749930","www.reccbe.ac.in");
        createcollege("AVINASHILINGAM","AVINASHILINGAM - FACULTY OF ENGINEERING COLLEGE","Thadagam Road","04222658932/04222440241/04222435550","www.avinuty.ac.in");
        createcollege("PARK","PARK COLLEGE OF ENGINEERING AND TECHNOLOGY","04222911100/004222911200/04222334899","Kaniyur","www.pcet.ac.in");
        createcollege("AMRITA","AMRITA INSTITUTE OF TECHNOLOGY AND SCIENCE","Ettimadai","04222685000","www.amrita.edu");
        createcollege("HCET","HINDUSTHAN COLLEGE OF ENGINEERING AND TECHNOLOGY","Othakkalmandapam","04222930216/9943915566","www.hindusthan.net");
        createcollege("AKSHAYA","AKSHAYA COLLEGE OF ENGINEERING & TECHNOLOGY","Kinathukadavu","04259242570/094259242571/04259242572","www.acetcbe.edu.in");
        createcollege("SSIET","SRI SHAKTHI INSTITUTE OF ENGINEERING AND TECHNOLOGY","Chinniyampalayam","04226450891/04226450892/04226450893","www.siet.ac.in");
        createcollege("SVSEC","SVS ENGINEERING COLLEGE","Arasampalayam","04222619300/04222619301/04222619302/9047019993/ 9047019996","www.svsce.edu.in");
        createcollege("DAIT","DHAANISH AHMED INSTITUTE OF TECHNOLOGY","K.G.Chavadi","04227172060/04227172061/8344986000/9176786000","www.dhaanish.com");
        createcollege("CIT","COIMBATORE INSTITUTE OF TECHNOLOGY","Avinashi Road","04222574071/04222574072","www.cit.edu.in");
        createcollege("ADITHYA","ADITHYA INSTITUTE OF TECHNOLOGY","Kurumbapalayam","04222654504/9789456709/ 7639523116","www.adithyatech.com");
        createcollege("KIT","KALAIGNAR KARUNANIDHI INSTITUTE OF TECHNOLOGY","Kannampalayam","04222367890/04222579222/9965590076/9965590056/9965590062","www.kitcbe.com");
        createcollege("KALAIVAANI","KALAIVANI COLLEGE OF TECHNOLOGY","Madukarai","04222621001/04222621003/04222621005/8508528000","");
        createcollege("PSGCT","PSG COLLEGE OF TECHNOLOGY","Peelamedu","04223933250/04223933251/04223933252","www.psgtech.edu");
        createcollege("CMSCET","CMS COLLEGE OF ENGINEERING & TECHNOLOGY","Othakkalmandapam","04222636053/04222636055/04222636050/9688194333/9688294333/ 9688394333","www.cmscet.com");
        createcollege("NGPIT","DR.N.G.P. INSTITUTE OF TECHNOLOGY","Kalapatti","04222369105/9442853333","www.drngpit.ac.in");
        createcollege("GCT","GOVT COLLEGE OF TECHNOLOGY","Thadagam Road","04222432221","www.gct.ac.in");
        createcollege("KCE","KARPAGAM COLLEGE OF ENGINEERING","Othakkalmandapam","04222619047","www.kce.ac.in");
        createcollege("SRIT","SRI RAMAKRISHNA INSTITUTE OF TECHNOLOGY","Pachapalayam","04222605577","www.srit.org");
        createcollege("KARUNYA","KARUNYA INSTITUTE OF TECHNOLOGY","Karunya Nagar","04222614300","www.karunya.edu");
        createcollege("NEHRU","NEHRU COLLEGE OF AERONAUTICS & APPLIED SCIENCES","Kuniyamuthur","04222252671/04222252672/04222252673","www.nehrucolleges.org.in");
        createcollege("SSK","SSK COLLEGE OF ENGINEERING & TECHNOLOGY","Navakkarai","04222656740/04222656750","www.sskcollege.com");
        createcollege("CSI","C.S.I.BISHOP APPASAMY COLLEGE OF EDUCATION","Avinashi Road","04226523129","www.csibaced.co.in");
        createcollege("BCE","BHARATHI COLLEGE OF EDUCATION","Somayampalayam","04226531097/04222647065/8220059605","");
        createcollege("GRD","DR.G.R.DAMODARAN COLLEGE OF EDUCATION","Sengodagoundanpudur","04222360204/04222360032/9842221428/9842221416/  9842221457","www.grd.org/grdce");
        createcollege("GRG","G.R.GOVINDARAJULU COLLEGE OF EDUCATION","Maddampalayam","04254272754","www.grgce.org");
        createcollege("NGPCE","DR.NGP COLLEGE OF EDUCATION","Kalapatti","04222369401","www.drngpeducation.ac.in");
        createcollege("SNSCE","DR.S.N.S.COLLEGE OF EDUCATION","Saravanampatti","04222666646/9843086670/ 9843086671","www.drsnsce.edu.in");
        createcollege("RVSCE","RVS COLLEGE OF EDUCATION","Sulur","04222681123/04222681124/9843686389","www.rvscoe.ac.in");
        createcollege("NARIS","NARIS COLLEGE OF EDUCATION","NGGO Colony","04222642277/04222909479/9786578655/9786578654","www.nairsce.org");
        createcollege("GLCC","GOVERNMENT LAW COLLEGE COIMBATORE","Bharathiyar University Post","04222422454","www.glccbe.ac.in");
        createcollege("RVSHC","R.V.S.HOMEOPATHIC MEDICAL COLLEGE","Kannampalayam","04222681123","www.rvssiddha.ac.in");
        createcollege("SRDC","SRI RAMAKRISHNA DENTAL COLLEGE","Avarampalayam","04222560381/9894074488","");
        createcollege("NHMC","NETHRA HOMEOPATHY MEDICAL COLLEGE","Gnanambikai Mills","04222647657/9443521125/ 9789719709","");
        createcollege("PSGIMSR","PSG INSTITUTE OF MEDICAL SCIENCES AND RESEARCH","Peelamedu","04222570170/2598822/    4345802/04222573833","www.psgimsr.ac.in");
        createcollege("KGMSR","K.GOVINDHASAMY MEDICAL TRUST","K.G.Arts College Road","04222212121/04222212129/04222222222/04222219191","www.kghospital.com");



    }
}
