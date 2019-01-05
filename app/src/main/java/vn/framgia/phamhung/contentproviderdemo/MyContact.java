package vn.framgia.phamhung.contentproviderdemo;

import android.graphics.Bitmap;
import android.net.Uri;

public class MyContact {
    private String mId;
    private String mName;
    private String mPhone;
    private Bitmap mPhoto;
    private Uri mUri;

    public MyContact() {
    }
    public MyContact(String id, String name, String phone, Bitmap photo, Uri Uri) {
        mId = id;
        mName = name;
        mPhone = phone;
        mPhoto = photo;
        mUri = Uri;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Bitmap getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Bitmap photo) {
        mPhoto = photo;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri Uri) {
        mUri = Uri;
    }
}
