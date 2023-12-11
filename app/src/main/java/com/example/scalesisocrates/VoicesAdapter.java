package com.example.scalesisocrates;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.List;

public class VoicesAdapter<T> extends ArrayAdapter<T> implements AdapterView.OnItemSelectedListener {

    private final ArrayAdapter<String> categoriesAdapter;
    private final Spinner spinnerCategories;

    public VoicesAdapter(@NonNull Context context, int resource, @NonNull List<T> objects,
                         ArrayAdapter<String> categoriesAdapter, Spinner spinnerCategories) {
        super(context, resource, objects);
        this.categoriesAdapter = categoriesAdapter;
        this.spinnerCategories = spinnerCategories;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        MainActivity.voice = parent.getItemAtPosition(position).toString();

        setCategories();

        switch ( MainActivity.voice ) {
            case StringConstants.Voices.andr:
            case StringConstants.Voices.gyna: {
                int i = spinnerCategories.getSelectedItemPosition();
                if ( i == 4 ) spinnerCategories.setSelection(3, false);
                spinnerCategories.setSelection(4, true);
                break;
            }
            case StringConstants.Voices.paid: {
                int i = spinnerCategories.getSelectedItemPosition();
                if ( i == 1 ) spinnerCategories.setSelection(0, false);
                spinnerCategories.setSelection(1, true);
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setCategories() {

        categoriesAdapter.clear();
        switch ( MainActivity.voice ) {
            case StringConstants.Voices.andr: {
                categoriesAdapter.add(StringConstants.Andrikes.bathPxam);
                categoriesAdapter.add(StringConstants.Andrikes.bathXam);
                categoriesAdapter.add(StringConstants.Andrikes.bathMes);
                categoriesAdapter.add(StringConstants.Andrikes.barXam);
                categoriesAdapter.add(StringConstants.Andrikes.barMes);
                categoriesAdapter.add(StringConstants.Andrikes.barYps);
                categoriesAdapter.add(StringConstants.Andrikes.oksXam);
                categoriesAdapter.add(StringConstants.Andrikes.oksMes);
                categoriesAdapter.add(StringConstants.Andrikes.oksYps);
                categoriesAdapter.add(StringConstants.Andrikes.oksPyps);
                categoriesAdapter.notifyDataSetChanged();
                break;
            }
            case StringConstants.Voices.gyna: {
                categoriesAdapter.add(StringConstants.Gynaikeies.altoPxam);
                categoriesAdapter.add(StringConstants.Gynaikeies.altoXam);
                categoriesAdapter.add(StringConstants.Gynaikeies.altoMes);
                categoriesAdapter.add(StringConstants.Gynaikeies.mesXam);
                categoriesAdapter.add(StringConstants.Gynaikeies.mesMes);
                categoriesAdapter.add(StringConstants.Gynaikeies.mesYps);
                categoriesAdapter.add(StringConstants.Gynaikeies.ypsMes);
                categoriesAdapter.add(StringConstants.Gynaikeies.ypsYps);
                categoriesAdapter.add(StringConstants.Gynaikeies.ypsPyps);
                categoriesAdapter.notifyDataSetChanged();
                break;
            }
            case StringConstants.Voices.paid: {
                categoriesAdapter.add(StringConstants.Paidikes.ypsXam);
                categoriesAdapter.add(StringConstants.Paidikes.ypsMes);
                categoriesAdapter.add(StringConstants.Paidikes.ypsYps);
                categoriesAdapter.add(StringConstants.Paidikes.ypsPyps);
                categoriesAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

}
