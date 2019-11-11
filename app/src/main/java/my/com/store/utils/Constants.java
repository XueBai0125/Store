package my.com.store.utils;

import android.os.Environment;

import java.io.File;


public class Constants {
    public static final String DBPATH  = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "Store/Db";

    public static final String DBNAME = "Store.db";

    public static final int DBVERSION =2;
}
