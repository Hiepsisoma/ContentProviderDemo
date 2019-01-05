package vn.framgia.phamhung.contentproviderdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyContact> mMyContacts;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mListener ;
    public interface OnItemClickListener {
        void onCall(String phoneNumber);
    }
    public MyAdapter(List<MyContact> myContacts,OnItemClickListener listener) {
        mMyContacts = myContacts;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = mLayoutInflater.inflate(R.layout.item_contact,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMyContacts.get(i),mListener);
    }

    @Override
    public int getItemCount() {
        return (mMyContacts != null ? mMyContacts.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageContact, mImagePhone, mImageStar;
        private TextView mName, mPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageContact = itemView.findViewById(R.id.image_contact);
            mName = itemView.findViewById(R.id.text_name);
            mPhone = itemView.findViewById(R.id.text_phone);
            mImagePhone = itemView.findViewById(R.id.image_phone);
            mImageStar = itemView.findViewById(R.id.image_star);
        }

        public void bindData(final MyContact myContact, final OnItemClickListener listener) {
            mName.setText(myContact.getName());
            mPhone.setText(myContact.getPhone());
            if(myContact.getPhoto()!=null)
            Glide.with(itemView.getContext())
                    .load(myContact.getPhoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mImageContact);
            mImagePhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCall(myContact.getPhone());
                }
            });
        }
    }
}
