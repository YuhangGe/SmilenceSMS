package smilence.sms.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
	private static final String DB_NAME="smilencesms.db";
	private static int DB_VERSION=1;
	private static final String TABLE_SMS="sms";
	private static final String CREATE_TABLE_SMS = "create table smsTable (id integer primary key autoincrement, threadid integer not null, date text not null, phone text not null, name text not null, body text not null, deleted integer not null, read integer not null, type integer not null, ruleid integer not null);";
	
	private SQLiteDatabase db;
	private MyDbHelper dbHelper;
	private final Context context;
	public MyDBAdapter(Context ctx){
		context=ctx;
		dbHelper=new MyDbHelper(context,DB_NAME,null,DB_VERSION);
	}
	public MyDBAdapter open() throws SQLException{
		db=dbHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		db.close();
	}
	private static class MyDbHelper extends SQLiteOpenHelper{

		public MyDbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_SMS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS sms;");
			this.onCreate(db);
		}
		
	}
}
