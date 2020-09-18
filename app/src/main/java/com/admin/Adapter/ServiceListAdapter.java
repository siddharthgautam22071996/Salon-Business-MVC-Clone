package com.admin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.Model.AllServicesModel;
import com.admin.R;

import java.util.ArrayList;
import java.util.List;


public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.myViewHolder> {

    private List<AllServicesModel.DataBean> availabeDataItem = new ArrayList<>();
    private Activity mContext;
    private int flag;
    Context context;
    String serviceName, serviceId;
    private TextView mServiceName;
    private TextView mServiceId;

    public ServiceListAdapter(Activity mContext, List<AllServicesModel.DataBean> availabeDataItem, int flag) {
        this.mContext = mContext;
        this.availabeDataItem = availabeDataItem;
        this.flag = flag;

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListAdapter.myViewHolder holder, int position) {
        try {
            if (!(availabeDataItem.get(position).getService_id().isEmpty() &&
                    availabeDataItem.get(position).getService_name().isEmpty())) {
                System.out.println("serviceList adapter");
                serviceName = availabeDataItem.get(position).getService_name();
                serviceId = availabeDataItem.get(position).getService_id();
                mServiceId.setText(serviceId);
                mServiceName.setText(serviceName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return availabeDataItem.size();
    }

    @NonNull
    @Override
    public ServiceListAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_inflater, parent, false);

        return new ServiceListAdapter.myViewHolder(v);
    }

    private void initView(@NonNull final View itemView) {
        mServiceName = (TextView) itemView.findViewById(R.id.serviceName);
        mServiceId = (TextView) itemView.findViewById(R.id.serviceId);
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
