package com.example.scalesisocrates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.List;

public class CategoriesAdapter<T> extends ArrayAdapter<T> implements AdapterView.OnItemSelectedListener {

    private final EditText moria;
    private final EditText klimaka;

    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull List<T> objects, EditText moria, EditText klimaka) {
        super(context, resource, objects);
        this.moria = moria;
        this.klimaka = klimaka;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String category = parent.getItemAtPosition(position).toString();

        int kli = Integer.parseInt(klimaka.getText().toString());

        double mtonos = MainActivity.ktonos*kli;
        double mtonio = MainActivity.ktonio*kli;

        switch ( MainActivity.voice ){
            case StringConstants.Voices.andr: {
                switch ( category ) {
                    case StringConstants.Andrikes.bathPxam:{
                        moria.setText(Integer.toString(-(int)Math.round(3*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Andrikes.bathXam:{
                        moria.setText(Integer.toString(-(int)Math.round(2*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Andrikes.bathMes:{
                        moria.setText(Integer.toString(-(int)Math.round(mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Andrikes.barXam:{
                        moria.setText(Integer.toString(-(int)Math.round(mtonos)));
                        break;
                    }
                    case StringConstants.Andrikes.barMes:{
                        moria.setText(Integer.toString(0));
                        break;
                    }
                    case StringConstants.Andrikes.barYps:{
                        moria.setText(Integer.toString((int)Math.round(mtonos)));
                        break;
                    }
                    case StringConstants.Andrikes.oksXam:{
                        moria.setText(Integer.toString((int)Math.round(2*mtonos)));
                        break;
                    }
                    case StringConstants.Andrikes.oksMes:{
                        moria.setText(Integer.toString((int)Math.round(2*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Andrikes.oksYps:{
                        moria.setText(Integer.toString((int)Math.round(3*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Andrikes.oksPyps:{
                        moria.setText(Integer.toString((int)Math.round(4*mtonos+mtonio)));
                        break;
                    }
                }
                break;
            }
            case StringConstants.Voices.gyna: {
                switch ( category ) {
                    case StringConstants.Gynaikeies.altoPxam :{
                        moria.setText(Integer.toString((int)Math.round(3*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Gynaikeies.altoXam :{
                        moria.setText(Integer.toString((int)Math.round(4*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Gynaikeies.altoMes :{
                        moria.setText(Integer.toString((int)Math.round(4*mtonos+2*mtonio)));
                        break;
                    }
                    case StringConstants.Gynaikeies.mesXam :{
                        moria.setText(Integer.toString(kli));
                        break;
                    }
                    case StringConstants.Gynaikeies.mesMes :{
                        moria.setText(Integer.toString(kli+(int)Math.round(mtonos)));
                        break;
                    }
                    case StringConstants.Gynaikeies.mesYps :{
                        moria.setText(Integer.toString(kli+(int)Math.round(2*mtonos)));
                        break;
                    }
                    case StringConstants.Gynaikeies.ypsMes :{
                        moria.setText(Integer.toString(kli+(int)Math.round(2*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Gynaikeies.ypsYps :{
                        moria.setText(Integer.toString(kli+(int)Math.round(3*mtonos+mtonio)));
                        break;
                    }
                    case StringConstants.Gynaikeies.ypsPyps :{
                        moria.setText(Integer.toString(kli+(int)Math.round(4*mtonos+mtonio)));
                        break;
                    }
                }
                break;
            }
            case StringConstants.Voices.paid: {
                switch ( category ){
                    case StringConstants.Paidikes.ypsXam :{
                        moria.setText(Integer.toString(kli));
                        break;
                    }
                    case StringConstants.Paidikes.ypsMes :{
                        moria.setText(Integer.toString(kli+(int)Math.round(mtonos)));
                        break;
                    }
                    case StringConstants.Paidikes.ypsYps :{
                        moria.setText(Integer.toString(kli+(int)Math.round(2*mtonos)));
                        break;
                    }
                    case StringConstants.Paidikes.ypsPyps :{
                        moria.setText(Integer.toString(kli+(int)Math.round(2*mtonos+mtonio)));
                        break;
                    }
                }
                break;
            }

        }

        MainActivity ma = (MainActivity) getContext();
        SharedPreferences sharedPref = ma.getPreferences(Context.MODE_PRIVATE);
        double b = Double.longBitsToDouble(sharedPref.getLong(MainActivity.genos+" βαση", Double.doubleToRawLongBits(130.37)));
        int mor = Integer.parseInt(moria.getText().toString());
        if ( mor > 0 )
            b = b * Math.pow(2.0,((double)mor)/kli);
        else if ( mor < 0 )
            b = b / Math.pow(2.0,((double)-mor)/kli);
        ma.restartIfToneWasPlayedOrRebase(b);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
