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
import com.example.juansantiagoacev.uniandessatt.DTO.Sensor;
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
 * Activities containing this fragment MUST implement the {@link SensorListFragmentInteractionListener}
 * interface.
 */
public class SensorFragment extends Fragment {

    private MySensorRecyclerViewAdapter mySensorRecyclerViewAdapter;
    private List<Sensor> sensorList = new ArrayList();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private SensorListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorFragment() {
    }

    @SuppressWarnings("unused")
    public static SensorFragment newInstance(int columnCount) {
        SensorFragment fragment = new SensorFragment();
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
        View view = inflater.inflate(R.layout.fragment_sensor_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mySensorRecyclerViewAdapter = new MySensorRecyclerViewAdapter(sensorList, mListener);
            recyclerView.setAdapter(mySensorRecyclerViewAdapter);
            getSensores();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SensorListFragmentInteractionListener) {
            mListener = (SensorListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SensorListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getSensores() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uniandes-satt.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Sensor>> call;
        call = service.loadSensores(UserHelper.getCurrentUser().getAccessToken());
        call.enqueue(new Callback<List<Sensor>>() {
            @Override
            public void onResponse(Call<List<Sensor>> call, Response<List<Sensor>> response) {
                if(response.isSuccess()) {
                    Log.d("SUCCESS", response.body().toString());
                    sensorList = response.body();
                    mySensorRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    Log.d("UNSUCCESS", response.code() + " - " + response.message().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Sensor>> call, Throwable t) {
                t.printStackTrace();
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
    public interface SensorListFragmentInteractionListener {
        void onSensorListFragmentInteraction(Sensor item);
    }
}