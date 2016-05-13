package com.example.juansantiagoacev.uniandessatt.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juansantiagoacev.uniandessatt.APIService;
import com.example.juansantiagoacev.uniandessatt.DTO.Evento;
import com.example.juansantiagoacev.uniandessatt.Helpers.UserHelper;
import com.example.juansantiagoacev.uniandessatt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link EventoListFragmentInteractionListener}
 * interface.
 */
public class EventoFragment extends Fragment {

    private MyEventoRecyclerViewAdapter myEventoRecyclerViewAdapter;
    private List<Evento> eventoList = new ArrayList();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private EventoListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventoFragment() {
    }

    @SuppressWarnings("unused")
    public static EventoFragment newInstance(int columnCount) {
        EventoFragment fragment = new EventoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evento_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myEventoRecyclerViewAdapter = new MyEventoRecyclerViewAdapter(eventoList, mListener);
            recyclerView.setAdapter(myEventoRecyclerViewAdapter);
            getEventos();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventoListFragmentInteractionListener) {
            mListener = (EventoListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement EventoListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getEventos() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getString(R.string.login_progress));
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uniandes-satt.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Evento>> call;
        call = service.loadEventos(UserHelper.getCurrentUser().getAccessToken());
        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if(response.isSuccess()) {
                    Log.d("SUCCESS", response.body().toString());
                    eventoList = response.body();
                    myEventoRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    Log.d("UNSUCCESS", response.code() + " - " + response.message().toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface EventoListFragmentInteractionListener {
        void onEventoListFragmentInteraction(Evento item);
    }
}
