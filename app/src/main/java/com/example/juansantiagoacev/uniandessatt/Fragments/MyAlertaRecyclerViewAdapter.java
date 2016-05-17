package com.example.juansantiagoacev.uniandessatt.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juansantiagoacev.uniandessatt.DTO.Alerta;
import com.example.juansantiagoacev.uniandessatt.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link com.example.juansantiagoacev.uniandessatt.Fragments.AlertaFragment.AlertaListFragmentInteractionListener}.
 */
public class MyAlertaRecyclerViewAdapter extends RecyclerView.Adapter<MyAlertaRecyclerViewAdapter.ViewHolder> {

    private final List<Alerta> mValues;
    private final AlertaFragment.AlertaListFragmentInteractionListener mListener;

    public MyAlertaRecyclerViewAdapter(List<Alerta> alertaList, AlertaFragment.AlertaListFragmentInteractionListener listener) {
        mValues = alertaList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alerta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(position+1+"");
        holder.mContentView.setText(mValues.get(position).toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.AlertaFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void updateDataset(List<Alerta> alertas) {
        mValues.clear();
        mValues.addAll(alertas);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Alerta mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id_alerta);
            mContentView = (TextView) view.findViewById(R.id.content_alerta);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
