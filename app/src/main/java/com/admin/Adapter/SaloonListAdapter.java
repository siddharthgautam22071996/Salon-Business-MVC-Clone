package com.admin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.Model.AllSaloonModel;
import com.admin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SaloonListAdapter extends RecyclerView.Adapter<SaloonListAdapter.myViewHolder> implements View.OnClickListener {

    private List<AllSaloonModel.DataBean> availabeDataItem = new ArrayList<>();
    private Activity mContext;
    private int flag;
    Context context;
    String businessName, businessContactPersonName, businessAddress, businessMobNo;
    private CircleImageView mBusinessImage;
    private TextView mBusinessName;
    private TextView mContactPersonName;
    private TextView mBusinessAddress;
    private ImageButton mCallBtn;

    public SaloonListAdapter(Activity mContext, List<AllSaloonModel.DataBean> availabeDataItem, int flag) {
        this.mContext = mContext;
        this.availabeDataItem = availabeDataItem;
        this.flag = flag;

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        try {
            if (!(availabeDataItem.get(position).getBusiness_Name().isEmpty() &&
                    availabeDataItem.get(position).getAddress1().isEmpty() &&
                    availabeDataItem.get(position).getContact_Person().isEmpty())) {
                businessName = availabeDataItem.get(position).getBusiness_Name();
                businessContactPersonName = availabeDataItem.get(position).getContact_Person();
                businessAddress = availabeDataItem.get(position).getAddress1();
                mBusinessName.setText(businessName);
                mBusinessAddress.setText(businessAddress);
                mContactPersonName.setText(businessContactPersonName);
            }
//        Picasso.with(context).load("http://03607017cee9.ngrok.io/women_tracker"+availabeDataItem.get(position).getProfile_photo()).into(holder.mProfilePic);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return availabeDataItem.size();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_inflater, parent, false);

        return new myViewHolder(v);
    }

    private void initView(@NonNull final View itemView) {
        mBusinessImage = (CircleImageView) itemView.findViewById(R.id.businessImage);
        mBusinessName = (TextView) itemView.findViewById(R.id.businessName);
        mContactPersonName = (TextView) itemView.findViewById(R.id.contactPersonName);
        mBusinessAddress = (TextView) itemView.findViewById(R.id.businessAddress);
        mCallBtn = (ImageButton) itemView.findViewById(R.id.callBtn);
        mCallBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callBtn:
                // TODO 20/09/17
                break;
            default:
                break;
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            initView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}

