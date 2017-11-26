package cn.edu.hznu.sqlitelab;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int CONTACT_DIR = 0;
    public static final int CONTACT_ITEM = 1;
    public static final String AUTHORITY = "cn.edu.hznu.sqlitelab.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contact",CONTACT_DIR);
        uriMatcher.addURI(AUTHORITY,"contact/#",CONTACT_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case CONTACT_DIR:
                return "vnd.android.cursor.dir/vnd.cn.edu.hznu.sqlitelab.provider.contact";
            case CONTACT_ITEM:
                return "vnd.android.cursor.item/vnd.cn.edu.hznu.sqlitelab.provider.contact";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case CONTACT_DIR:
            case CONTACT_ITEM:
                long newContactId = db.insert("contact",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/contact/"+newContactId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDatabaseHelper(getContext(),"PhoneBook.db",null,1);
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case CONTACT_DIR:
                cursor = db.query("contact",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACT_ITEM:
                String contactId = uri.getPathSegments().get(1);
                cursor = db.query("contact",projection,"id = ?",new String[]{contactId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
