package com.example.juansantiagoacev.uniandessatt.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juansantiagoacev.uniandessatt.DTO.Evento;
import com.example.juansantiagoacev.uniandessatt.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Evento} and makes a call to the
 * specified {@link com.example.juansantiagoacev.uniandessatt.Fragments.EventoFragment.EventoListFragmentInteractionListener}.
 */
public class MyEventoRecyclerViewAdapter extends RecyclerView.Adapter<MyEventoRecyclerViewAdapter.ViewHolder> {

    private final List<Evento> mValues;
    private final EventoFragment.EventoListFragmentInteractionListener mListener;

    public MyEventoRecyclerViewAdapter(List<Evento> items, EventoFragment.EventoListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_evento, parent, false);
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
                    mListener.onEventoListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void updateDataset(List<Evento> eventos) {
        mValues.clear();
        mValues.addAll(eventos);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setFilter(List<Evento> eventosFilter) {
        mValues.clear();
        mValues.addAll(eventosFilter);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Evento mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id_evento);
            mContentView = (TextView) view.findViewById(R.id.content_evento);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
