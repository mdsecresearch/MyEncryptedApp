package com.mdsec.myencryptedapp;
import java.io.File;
import com.mdsec.myencryptedapp.DeviceIdentity;
import net.sqlcipher.database.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private void InitializeSQLCipher() {
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("encrypted.db");
        databaseFile.mkdirs();
        databaseFile.delete();
        try {
        	String id = new DeviceIdentity(this).generateDeviceIdentifier();
        	//Log.d("MyEncryptedApp", "id = " + id);
	        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, id, null);
	        database.execSQL("create table t1(a, b)");
	        database.execSQL("insert into t1(a, b) values(?, ?)", new Object[]{"one for the money","two for the show"});
	        Log.d("MyEncryptedApp", "Successfully inserted in to database");
        }
        catch(Exception e)
        {
        	Log.d("MyEncryptedApp", "Error: " + e.getMessage());
        }
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeSQLCipher();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
