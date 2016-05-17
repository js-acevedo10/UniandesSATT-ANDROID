package com.example.juansantiagoacev.uniandessatt.Fragments;

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
import com.example.juansantiagoacev.uniandessatt.DTO.Alerta;
import com.example.juansantiagoacev.uniandessatt.R;
import com.example.juansantiagoacev.uniandessatt.Helpers.UserHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link AlertaListFragmentInteractionListener}
 * interface.
 */
public class AlertaFragment extends Fragment {

    private List<Alerta> alertaList = new ArrayList();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private AlertaListFragmentInteractionListener mListener;
    private MyAlertaRecyclerViewAdapter myAlertaRecyclerViewAdapter = new MyAlertaRecyclerViewAdapter(alertaList, mListener);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlertaFragment() {
    }

    @SuppressWarnings("unused")
    public static AlertaFragment newInstance(int columnCount) {
        AlertaFragment fragment = new AlertaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAlertas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alerta_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myAlertaRecyclerViewAdapter = new MyAlertaRecyclerViewAdapter(alertaList, mListener);
            recyclerView.setAdapter(myAlertaRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AlertaListFragmentInteractionListener) {
            mListener = (AlertaListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AlertaListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getAlertas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uniandes-satt.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Alerta>> call;
        call = service.loadAlertas(UserHelper.getCurrentUser().getAccessToken());
        call.enqueue(new Callback<List<Alerta>>() {
            @Override
            public void onResponse(Call<List<Alerta>> call, Response<List<Alerta>> response) {
                if(response.isSuccess()) {
                    Log.d("ALERTAS SUCCESS", response.body().toString());
                    alertaList = response.body();
                    myAlertaRecyclerViewAdapter.updateDataset(alertaList);
                } else {
                    Log.d("ALERTAS UNSUCCESS", response.code() + " - " + response.message().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Alerta>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface AlertaListFragmentInteractionListener {
        void AlertaFragmentInteraction(Alerta item);
    }
}