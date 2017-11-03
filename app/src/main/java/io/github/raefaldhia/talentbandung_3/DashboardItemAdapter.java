package io.github.raefaldhia.talentbandung_3;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by raefaldhia on 11/1/17.
 */


public class DashboardItemAdapter extends RecyclerView.Adapter<DashboardItemAdapter.ViewHolder> {
    private List<DashboardItem> itemList;

    public DashboardItemAdapter(List<DashboardItem> items){
        this.itemList = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameView;
        TextView emailView;

        public ViewHolder(View view) {
            super(view);

            usernameView = (TextView) view.findViewById(R.id.usernameView);
            emailView = (TextView) view.findViewById(R.id.emailView);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DashboardItem item = itemList.get(position);

        holder.usernameView.setText(item.getUsername());
        holder.emailView.setText(item.getEmail());
    }
}
