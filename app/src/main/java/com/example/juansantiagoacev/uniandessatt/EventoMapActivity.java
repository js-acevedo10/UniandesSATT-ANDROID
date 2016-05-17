package com.example.juansantiagoacev.uniandessatt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juansantiagoacev.uniandessatt.DTO.Evento;
import com.example.juansantiagoacev.uniandessatt.Helpers.UserHelper;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class EventoMapActivity extends AppCompatActivity {

    private TextView evento_text_lat_dynamic, evento_text_lng_dynamic, evento_text_dis_dynamic;
    private ImageView evento_imagen_map;
    private Evento selectedEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_map);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        prepareLayout();
    }

    public void prepareLayout() {
        selectedEvento = UserHelper.getSelectedEvento();
        evento_text_lat_dynamic = (TextView) findViewById(R.id.evento_text_lat_dynamic);
        evento_text_lat_dynamic.setText(selectedEvento.getLat()+"");
        evento_text_lng_dynamic = (TextView) findViewById(R.id.evento_text_lng_dynamic);
        evento_text_lng_dynamic.setText(selectedEvento.getLng()+"");
        evento_text_dis_dynamic = (TextView) findViewById(R.id.evento_text_dis_dynamic);
        evento_text_dis_dynamic.setText(selectedEvento.getDistancia()+"m");
        evento_imagen_map = (ImageView) findViewById(R.id.evento_imagen_map);
        String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" +
                selectedEvento.getLat() + "," + selectedEvento.getLng() +
                "&zoom=6&size=1000x1000&maptype=roadmap" +
                "&markers=color:red%7Clabel:C%7C" +
                selectedEvento.getLat() +
                "," +
                selectedEvento.getLng();
        Picasso.with(getApplicationContext())
                .load(mapUrl)
                .fit()
                .into(evento_imagen_map);
    }
}