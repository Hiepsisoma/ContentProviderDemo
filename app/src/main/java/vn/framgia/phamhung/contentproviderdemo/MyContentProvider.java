package vn.framgia.phamhung.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyContentProvider{
    private List<MyContact> mMyContacts;
    private ContentResolver mContentResolver;
    private Cursor mCursor;
    public List<MyContact> getContact(Context context){
        mMyContacts = new ArrayList<>();
        mContentResolver = context.getContentResolver();
        mCursor = mContentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,null,null,null);
        if(mCursor.getCount()>0) {
            while (mCursor.moveToNext()){
                String id = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
                if(mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0){
                    Cursor cursorInfo = mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id)));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    Bitmap photo = null;
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream);
                    }
                    while (cursorInfo.moveToNext()) {
                        MyContact myContact = new MyContact();
                        myContact.setId(id);
                        myContact.setName(cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                        myContact.setPhone(cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        myContact.setPhoto(photo);
                        myContact.setUri(pURI);
                        mMyContacts.add(myContact);
                    }
                    cursorInfo.close();
                }
            }
        }
        return mMyContacts;
    }
}
