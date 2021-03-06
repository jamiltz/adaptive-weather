package com.raywenderlich.adaptiveweather;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/*
 * Copyright (c) 2017 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    // 1.
    interface OnItemClickListener {
        void onItemClick(Location location);
    };

    private List<Location> mDataset;
    private Context mContext;
    private String selectedLocation;
    private OnItemClickListener mListener;

    // 2.
    LocationAdapter(Context context, List<Location> dataset, OnItemClickListener listener) {
        mContext = context;
        mDataset = dataset;
        mListener = listener;
    }

    // 3.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    // 4.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_selectable_list_item, parent, false);

        return new ViewHolder(view);
    }

    // 5.
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).getName());
        holder.title.setTextSize(22);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(mDataset.get(position));
                selectedLocation = mDataset.get(position).getName();
                notifyDataSetChanged();
            }
        });
        if (mDataset.get(position).getName().equals(selectedLocation)) {
            int backgroundColor = mContext.getResources().getColor(R.color.color_primary_dark);
            holder.itemView.setBackgroundColor(backgroundColor);
            holder.title.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.title.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    String getSelectedLocation() {
        return selectedLocation;
    }

    void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }
}
