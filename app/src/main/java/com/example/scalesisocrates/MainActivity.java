package com.example.scalesisocrates;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnClickListener {

    private Spinner spinnerGenoi = null;
    public static String genos = null;

    private Spinner spinnerCategories = null;
    private CategoriesAdapter<String> categoriesAdapter = null;
    public static List<String> categories = null;

    private EditText bash = null;
    private Button sta = null;

    private Spinner spinnerVoices = null;
    private VoicesAdapter<String> voicesAdapter = null;
    public static String voice = null;

    private static final TriangularWave tw = new TriangularWave();

    private Button buttonAdi = null;
    private Button buttonAga = null;
    private Button buttonAbou = null;
    private Button buttonApa = null;
    private Button buttonAnh = null;
    private Button buttonZw = null;
    private Button buttonKe = null;
    private Button buttonDi = null;
    private Button buttonGa = null;
    private Button buttonBou = null;
    private Button buttonPa = null;
    private Button buttonNh = null;
    private Button buttonKzw = null;
    private Button buttonKke = null;
    private Button buttonKdi = null;
    private int lastPlayedTone = 0;

    private EditText klimaka = null;
    private EditText zwnh = null;
    private EditText kezw = null;
    private EditText dike = null;
    private EditText gadi = null;
    private EditText bouga = null;
    private EditText pabou = null;
    private EditText nhpa = null;
    private TextWatcher textWatcher = null;

    private Button kate = null;
    public EditText moria = null;
    private Button anev = null;
    public static final double ktonos = Math.log(9.0 / 8.0) / Math.log(2.0);
    public static final double ktonio = Math.log(256.0 / 243.0) / Math.log(2.0);

    private Button plyn = null;
    private EditText volume = null;
    private Button syn = null;
    private static final float maxVolume = AudioTrack.getMaxVolume();

    private Button apoth = null;
    private Button epan = null;
    private Button anap = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        setButtonsAndListeners();
        setPrefs();
        setWidthsAndHeights();
    }

    @Override
    public void onBackPressed() {
        tw.stop();
        super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    private void setButtonsAndListeners() {

        spinnerCategories = findViewById(R.id.spinnerCategories);
        spinnerVoices = findViewById(R.id.spinnerVoices);

        bash = findViewById(R.id.ΒΑΣΗ);
        sta = findViewById(R.id.ΣΤΑ);
        spinnerGenoi = findViewById(R.id.ΓΕΝΟΣ);

        kate = findViewById(R.id.kat);
        moria = findViewById(R.id.moria);
        anev = findViewById(R.id.ane);

        buttonAdi = findViewById(R.id.ΑΔΙ);
        buttonAga = findViewById(R.id.ΑΓΑ);
        buttonAbou = findViewById(R.id.ΑΒΟΥ);
        buttonApa = findViewById(R.id.ΑΠΑ);
        buttonAnh = findViewById(R.id.ΑΝΗ);
        buttonZw = findViewById(R.id.ΖΩ);
        buttonKe = findViewById(R.id.ΚΕ);
        buttonDi = findViewById(R.id.ΔΙ);
        buttonGa = findViewById(R.id.ΓΑ);
        buttonBou = findViewById(R.id.ΒΟΥ);
        buttonPa = findViewById(R.id.ΠΑ);
        buttonNh = findViewById(R.id.ΝΗ);
        buttonKzw = findViewById(R.id.ΚΖΩ);
        buttonKke = findViewById(R.id.ΚΚΕ);
        buttonKdi = findViewById(R.id.ΚΔΙ);

        klimaka = findViewById(R.id.klimaka);
        zwnh = findViewById(R.id.zwnh);
        kezw = findViewById(R.id.kezw);
        dike = findViewById(R.id.dike);
        gadi = findViewById(R.id.gadi);
        bouga = findViewById(R.id.bouga);
        pabou = findViewById(R.id.pabou);
        nhpa = findViewById(R.id.nhpa);

        plyn = findViewById(R.id.ply);
        volume = findViewById(R.id.volume);
        syn = findViewById(R.id.syn);

        apoth = findViewById(R.id.ΑΠΟ);
        epan = findViewById(R.id.ΕΠΑ);
        anap = findViewById(R.id.ΑΝΑ);

        buttonAdi.setOnClickListener(this);
        buttonAga.setOnClickListener(this);
        buttonAbou.setOnClickListener(this);
        buttonApa.setOnClickListener(this);
        buttonAnh.setOnClickListener(this);
        buttonZw.setOnClickListener(this);
        buttonKe.setOnClickListener(this);
        buttonDi.setOnClickListener(this);
        buttonGa.setOnClickListener(this);
        buttonBou.setOnClickListener(this);
        buttonPa.setOnClickListener(this);
        buttonNh.setOnClickListener(this);
        buttonKzw.setOnClickListener(this);
        buttonKke.setOnClickListener(this);
        buttonKdi.setOnClickListener(this);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.parseInt(zwnh.getText().toString());
                    i += Integer.parseInt(kezw.getText().toString());
                    i += Integer.parseInt(dike.getText().toString());
                    i += Integer.parseInt(gadi.getText().toString());
                    i += Integer.parseInt(bouga.getText().toString());
                    i += Integer.parseInt(pabou.getText().toString());
                    i += Integer.parseInt(nhpa.getText().toString());
                    int kli = Integer.parseInt(klimaka.getText().toString());
                    int mo = Integer.parseInt(moria.getText().toString());
                    klimaka.setText(Integer.toString(i));

                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    double b = Double.longBitsToDouble(sharedPref.getLong(genos + " βαση", Double.doubleToRawLongBits(130.37)));

                    if (mo > 0) {
                        int mor = (int) Math.round((mo / (double) kli) * i);
                        b = b * Math.pow(2.0, ((double) mor) / i);
                        moria.setText(Integer.toString(mor));// συμβαση οχι πολυ ακριβης υπολογισμος
                    } else if (mo < 0) {
                        int mor = (int) Math.round((mo / (double) kli) * i);
                        b = b / Math.pow(2.0, ((double) -mor) / i);
                        moria.setText(Integer.toString(mor));// συμβαση οχι πολυ ακριβης υπολογισμος
                    }
                    bash.setText(Double.toString(b));//restartIfToneWasPlayedOrRebase(b) doesn't sound good
                } catch (NumberFormatException ignored) {

                }
            }
        };

        zwnh.addTextChangedListener(textWatcher);
        kezw.addTextChangedListener(textWatcher);
        dike.addTextChangedListener(textWatcher);
        gadi.addTextChangedListener(textWatcher);
        bouga.addTextChangedListener(textWatcher);
        pabou.addTextChangedListener(textWatcher);
        nhpa.addTextChangedListener(textWatcher);

        /*klimaka.setOnEditorActionListener((v, actionId, event) -> {
            if ( actionId == EditorInfo.IME_ACTION_DONE  ) {
                try {
                    int kli = Integer.parseInt(klimaka.getText().toString());
                    int gd = Integer.parseInt(gadi.getText().toString());
                    int bg = Integer.parseInt(bouga.getText().toString());
                    int pb = Integer.parseInt(pabou.getText().toString());
                    int np = Integer.parseInt(nhpa.getText().toString());
                    int mo = Integer.parseInt(moria.getText().toString());

                    int i = gd+2*(bg+pb+np);

                    int m = (int)Math.round( ( mo / (double)i ) * kli );
                    moria.setText(Integer.toString(m));
                    int g = (int)Math.round( ( gd / (double)i ) * kli );
                    gadi.setText(Integer.toString(g));
                    int b = (int)Math.round( ( bg / (double)i ) * kli );
                    bouga.setText(Integer.toString(b));
                    int p = (int)Math.round( ( pb / (double)i ) * kli );
                    pabou.setText(Integer.toString(p));
                    int n = (int)Math.round( ( np / (double)i ) * kli );
                    nhpa.setText(Integer.toString(n));

                } catch (NumberFormatException ignored) {

                }
                return false;
            }
            return true;
        });*/

        sta.setOnClickListener(this);

        kate.setOnClickListener(this);
        kate.setOnTouchListener(new View.OnTouchListener() {
            long then = 0;

            @SuppressLint({"SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = System.currentTimeMillis();
                    v.performClick();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    long mil = System.currentTimeMillis() - then;
                    if (mil > 200) {
                        try {
                            int mor = Integer.parseInt(moria.getText().toString());
                            int kli = Integer.parseInt(klimaka.getText().toString());
                            mor -= mil / 20;
                            switch (voice) {
                                case StringConstants.Voices.andr: {
                                    mor = changeCategoriesMen(mor, kli);
                                    break;
                                }
                                case StringConstants.Voices.gyna: {
                                    mor = changeCategoriesWomen(mor, kli);
                                    break;
                                }
                                case StringConstants.Voices.paid: {
                                    mor = changeCategoriesChildren(mor, kli);
                                    break;
                                }
                            }
                            double b = getBashPlusMinusMoria(mor);
                            restartIfToneWasPlayedOrRebase(b);
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
                return true;
            }
        });

        anev.setOnClickListener(this);
        anev.setOnTouchListener(new View.OnTouchListener() {
            long then = 0;

            @SuppressLint({"SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = System.currentTimeMillis();
                    v.performClick();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    long mil = System.currentTimeMillis() - then;
                    if (mil > 200) {
                        try {
                            int mor = Integer.parseInt(moria.getText().toString());
                            int kli = Integer.parseInt(klimaka.getText().toString());
                            mor += mil / 20;
                            switch (voice) {
                                case StringConstants.Voices.andr: {
                                    mor = changeCategoriesMen(mor, kli);
                                    break;
                                }
                                case StringConstants.Voices.gyna: {
                                    mor = changeCategoriesWomen(mor, kli);
                                    break;
                                }
                                case StringConstants.Voices.paid: {
                                    mor = changeCategoriesChildren(mor, kli);
                                    break;
                                }
                            }
                            double b = getBashPlusMinusMoria(mor);
                            restartIfToneWasPlayedOrRebase(b);
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
                return true;
            }
        });


        plyn.setOnClickListener(this);
        plyn.setOnTouchListener(new View.OnTouchListener() {
            long then = 0;

            @SuppressLint({"SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = System.currentTimeMillis();
                    v.performClick();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    long mil = System.currentTimeMillis() - then;
                    if (mil > 200) {
                        try {
                            int vol = Integer.parseInt(volume.getText().toString());
                            if (vol >= mil / 30) vol -= mil / 30;
                            else vol = 0;
                            volume.setText(Integer.toString(vol));
                            tw.setVolume(vol / 100.0f * maxVolume);
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
                return true;
            }
        });

        syn.setOnClickListener(this);
        syn.setOnTouchListener(new View.OnTouchListener() {
            long then = 0;

            @SuppressLint({"SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    then = System.currentTimeMillis();
                    v.performClick();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    long mil = System.currentTimeMillis() - then;
                    if (mil > 200) {
                        try {
                            int vol = Integer.parseInt(volume.getText().toString());
                            if (vol <= 100 - (mil / 30)) vol += mil / 30;
                            else vol = 100;
                            volume.setText(Integer.toString(vol));
                            tw.setVolume(vol / 100.0f * maxVolume);
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
                return true;
            }
        });

        apoth.setOnClickListener(this);
        epan.setOnClickListener(this);
        anap.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void restartIfToneWasPlayedOrRebase(double b) {
        if (lastPlayedTone == R.id.ΑΔΙ) {
            buttonAdi.performClick();
        } else if (lastPlayedTone == R.id.ΑΓΑ) {
            buttonAga.performClick();
        } else if (lastPlayedTone == R.id.ΑΒΟΥ) {
            buttonAbou.performClick();
        } else if (lastPlayedTone == R.id.ΑΠΑ) {
            buttonApa.performClick();
        } else if (lastPlayedTone == R.id.ΑΝΗ) {
            buttonAnh.performClick();
        } else if (lastPlayedTone == R.id.ΖΩ) {
            buttonZw.performClick();
        } else if (lastPlayedTone == R.id.ΚΕ) {
            buttonKe.performClick();
        } else if (lastPlayedTone == R.id.ΔΙ) {
            buttonDi.performClick();
        } else if (lastPlayedTone == R.id.ΓΑ) {
            buttonGa.performClick();
        } else if (lastPlayedTone == R.id.ΒΟΥ) {
            buttonBou.performClick();
        } else if (lastPlayedTone == R.id.ΠΑ) {
            buttonPa.performClick();
        } else if (lastPlayedTone == R.id.ΝΗ) {
            buttonNh.performClick();
        } else if (lastPlayedTone == R.id.ΚΖΩ) {
            buttonKzw.performClick();
        } else if (lastPlayedTone == R.id.ΚΚΕ) {
            buttonKke.performClick();
        } else if (lastPlayedTone == R.id.ΚΔΙ) {
            buttonKdi.performClick();
        } else {
            bash.setText(Double.toString(b));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        try {
            int vId = v.getId();
            if (vId == R.id.ΑΔΙ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 3;
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString()) * 2;
                        i += Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 3;
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΑΓΑ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        b = 2.0 * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString()) * 2;
                        i += Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΑΒΟΥ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString()) * 2;
                        i += Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΑΠΑ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt:
                    case StringConstants.Genoi.sklh: {
                        b = 2.0 * b;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΑΝΗ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        b = 2.0 * b;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΖΩ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam:
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        b = b * 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΚΕ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam:
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΔΙ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam:
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΓΑ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam:
                    case StringConstants.Genoi.pldt: {
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΒΟΥ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΠΑ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 3;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΝΗ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(bouga.getText().toString()) * 2;
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΚΖΩ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString()) * 2;
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.barp: {
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΚΚΕ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam: {
                        int i = Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString()) * 2;
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(kezw.getText().toString());
                        i += Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΚΔΙ) {
                lastPlayedTone = vId;
                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                switch (genos) {
                    case StringConstants.Genoi.diat:
                    case StringConstants.Genoi.xrwm: {
                        int i = Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.enam:
                    case StringConstants.Genoi.pldt: {
                        int i = Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.sklh:
                    case StringConstants.Genoi.atet:
                    case StringConstants.Genoi.plbt: {
                        int i = Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                    case StringConstants.Genoi.bare:
                    case StringConstants.Genoi.bara:
                    case StringConstants.Genoi.bard:
                    case StringConstants.Genoi.bart:
                    case StringConstants.Genoi.barp: {
                        int i = Integer.parseInt(dike.getText().toString());
                        i += Integer.parseInt(gadi.getText().toString());
                        i += Integer.parseInt(bouga.getText().toString());
                        i += Integer.parseInt(pabou.getText().toString());
                        i += Integer.parseInt(nhpa.getText().toString());
                        b = Math.pow(2.0, ((double) i) / Integer.parseInt(klimaka.getText().toString())) * b / 2.0;
                        break;
                    }
                }
                tw.stop();
                bash.setText(Double.toString(b));
                tw.play(b);
            } else if (vId == R.id.ΣΤΑ) {
                tw.stop();
                lastPlayedTone = 0;
                int mor = Integer.parseInt(moria.getText().toString());
                bash.setText(Double.toString(getBashPlusMinusMoria(mor)));
            } else if (vId == R.id.kat) {
                int mor = Integer.parseInt(moria.getText().toString());
                int kli = Integer.parseInt(klimaka.getText().toString());
                mor--;
                switch (voice) {
                    case StringConstants.Voices.andr: {
                        mor = changeCategoriesMen(mor, kli);
                        break;
                    }
                    case StringConstants.Voices.gyna: {
                        mor = changeCategoriesWomen(mor, kli);
                        break;
                    }
                    case StringConstants.Voices.paid: {
                        mor = changeCategoriesChildren(mor, kli);
                        break;
                    }
                }
                double b = getBashPlusMinusMoria(mor);
                restartIfToneWasPlayedOrRebase(b);
            } else if (vId == R.id.ane) {
                int mor = Integer.parseInt(moria.getText().toString());
                int kli = Integer.parseInt(klimaka.getText().toString());
                mor++;
                switch (voice) {
                    case StringConstants.Voices.andr: {
                        mor = changeCategoriesMen(mor, kli);
                        break;
                    }
                    case StringConstants.Voices.gyna: {
                        mor = changeCategoriesWomen(mor, kli);
                        break;
                    }
                    case StringConstants.Voices.paid: {
                        mor = changeCategoriesChildren(mor, kli);
                        break;
                    }
                }
                double b = getBashPlusMinusMoria(mor);
                restartIfToneWasPlayedOrRebase(b);
            } else if (vId == R.id.ply) {
                int vol = Integer.parseInt(volume.getText().toString());
                if (vol > 0) vol--;
                volume.setText(Integer.toString(vol));
                tw.setVolume(vol / 100.0f * maxVolume);
            } else if (vId == R.id.syn) {
                int vol = Integer.parseInt(volume.getText().toString());
                if (vol < 100) vol++;
                volume.setText(Integer.toString(vol));
                tw.setVolume(vol / 100.0f * maxVolume);
            } else if (vId == R.id.ΑΠΟ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.save);
                builder.setPositiveButton(R.string.ok, (dialog, id) -> savePrefs());
                builder.setNegativeButton(R.string.cancel, (dialog, id) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (vId == R.id.ΕΠΑ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.reset);
                builder.setPositiveButton(R.string.ok, (dialog, id) -> resetPrefs());
                builder.setNegativeButton(R.string.cancel, (dialog, id) -> {

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (vId == R.id.ΑΝΑ) {
                tw.stop();
                lastPlayedTone = 0;

                int mor = Integer.parseInt(moria.getText().toString());
                double b = getBashPlusMinusMoria(mor);
                bash.setText(Double.toString(b));

                tw.playDiapaswn(b,
                        nhpa.getText().toString(),
                        pabou.getText().toString(),
                        bouga.getText().toString(),
                        gadi.getText().toString(),
                        dike.getText().toString(),
                        kezw.getText().toString(),
                        klimaka.getText().toString());
            }
        } catch (NumberFormatException ignored) {

        }
    }

    private double getBashPlusMinusMoria(int mor) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        double b = Double.longBitsToDouble(sharedPref.getLong(genos + " βαση", Double.doubleToRawLongBits(130.37)));
        int kli = Integer.parseInt(klimaka.getText().toString());
        if (mor > 0)
            b = b * Math.pow(2.0, ((double) mor) / kli);
        else if (mor < 0)
            b = b / Math.pow(2.0, ((double) -mor) / kli);
        return b;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        tw.stop();
        lastPlayedTone = 0;

        genos = parent.getItemAtPosition(position).toString();

        switch (genos) {
            case StringConstants.Genoi.diat:
            case StringConstants.Genoi.xrwm:
                nhpa.setHint("νη-πα");
                pabou.setHint("πα-βου");
                bouga.setHint("βου-γα");
                gadi.setHint("γα-δι");
                dike.setHint("δι-κε");
                kezw.setHint("κε-ζω");
                zwnh.setHint("ζω-νη");
                break;
            case StringConstants.Genoi.enam:
                nhpa.setHint("γα-δι");
                pabou.setHint("δι-κε");
                bouga.setHint("κε-ζω");
                gadi.setHint("ζω-νη");
                dike.setHint("νη-πα");
                kezw.setHint("πα-βου");
                zwnh.setHint("βου-γα");
                break;
            case StringConstants.Genoi.sklh:
                nhpa.setHint("πα-βου");
                pabou.setHint("βου-γα");
                bouga.setHint("γα-δι");
                gadi.setHint("δι-κε");
                dike.setHint("κε-ζω");
                kezw.setHint("ζω-νη");
                zwnh.setHint("νη-πα");
                break;
            case StringConstants.Genoi.pldt:
                nhpa.setHint("γα-δι");
                pabou.setHint("δι-κε");
                bouga.setHint("κε-ζω");
                gadi.setHint("");
                dike.setHint("");
                kezw.setHint("");
                zwnh.setHint("");
                break;
            case StringConstants.Genoi.atet:
            case StringConstants.Genoi.plbt:
                nhpa.setHint("πα-βου");
                pabou.setHint("βου-γα");
                bouga.setHint("γα-δι");
                gadi.setHint("δι-κε");
                dike.setHint("");
                kezw.setHint("");
                zwnh.setHint("");
                break;
            case StringConstants.Genoi.bare:
            case StringConstants.Genoi.bart:
            case StringConstants.Genoi.bard:
            case StringConstants.Genoi.bara:
            case StringConstants.Genoi.barp:
                nhpa.setHint("ζω-νη");
                pabou.setHint("νη-πα");
                bouga.setHint("πα-βου");
                gadi.setHint("βου-γα");
                dike.setHint("γα-δι");
                kezw.setHint("δι-κε");
                zwnh.setHint("κε-ζω");
                break;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            switch (genos) {
                case StringConstants.Genoi.diat:
                case StringConstants.Genoi.xrwm:
                    nhpa.setTooltipText("νη-πα");
                    pabou.setTooltipText("πα-βου");
                    bouga.setTooltipText("βου-γα");
                    gadi.setTooltipText("γα-δι");
                    dike.setTooltipText("δι-κε");
                    kezw.setTooltipText("κε-ζω");
                    zwnh.setTooltipText("ζω-νη");
                    break;
                case StringConstants.Genoi.enam:
                    nhpa.setTooltipText("γα-δι");
                    pabou.setTooltipText("δι-κε");
                    bouga.setTooltipText("κε-ζω");
                    gadi.setTooltipText("ζω-νη");
                    dike.setTooltipText("νη-πα");
                    kezw.setTooltipText("πα-βου");
                    zwnh.setTooltipText("βου-γα");
                    break;
                case StringConstants.Genoi.sklh:
                    nhpa.setTooltipText("πα-βου");
                    pabou.setTooltipText("βου-γα");
                    bouga.setTooltipText("γα-δι");
                    gadi.setTooltipText("δι-κε");
                    dike.setTooltipText("κε-ζω");
                    kezw.setTooltipText("ζω-νη");
                    zwnh.setTooltipText("νη-πα");
                    break;
                case StringConstants.Genoi.pldt:
                    nhpa.setTooltipText("γα-δι");
                    pabou.setTooltipText("δι-κε");
                    bouga.setTooltipText("κε-ζω");
                    gadi.setTooltipText("");
                    dike.setTooltipText("");
                    kezw.setTooltipText("");
                    zwnh.setTooltipText("");
                    break;
                case StringConstants.Genoi.atet:
                case StringConstants.Genoi.plbt:
                    nhpa.setTooltipText("πα-βου");
                    pabou.setTooltipText("βου-γα");
                    bouga.setTooltipText("γα-δι");
                    gadi.setTooltipText("δι-κε");
                    dike.setTooltipText("");
                    kezw.setTooltipText("");
                    zwnh.setTooltipText("");
                    break;
                case StringConstants.Genoi.bare:
                case StringConstants.Genoi.bart:
                case StringConstants.Genoi.bard:
                case StringConstants.Genoi.bara:
                case StringConstants.Genoi.barp:
                    nhpa.setTooltipText("ζω-νη");
                    pabou.setTooltipText("νη-πα");
                    bouga.setTooltipText("πα-βου");
                    gadi.setTooltipText("βου-γα");
                    dike.setTooltipText("γα-δι");
                    kezw.setTooltipText("δι-κε");
                    zwnh.setTooltipText("κε-ζω");
                    break;
            }
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        double b = Double.longBitsToDouble(sharedPref.getLong(genos + " βαση", Double.doubleToRawLongBits(130.37)));
        int mor = sharedPref.getInt(genos + " μορια", 0);
        int kli = sharedPref.getInt(genos + " κλιμακα", 1171);
        voice = sharedPref.getString(genos + " φωνη", StringConstants.Voices.andr);

        spinnerVoices.setOnItemSelectedListener(null);
        switch (voice) {
            case StringConstants.Voices.andr: {
                spinnerVoices.setSelection(0, false);
                voicesAdapter.setCategories();
                mor = changeCategoriesMen(mor, kli);
                break;
            }
            case StringConstants.Voices.gyna: {
                spinnerVoices.setSelection(1, false);
                voicesAdapter.setCategories();
                mor = changeCategoriesWomen(mor, kli);
                break;
            }
            default: {
                spinnerVoices.setSelection(2, false);
                voicesAdapter.setCategories();
                mor = changeCategoriesChildren(mor, kli);
                break;
            }
        }
        spinnerVoices.setOnItemSelectedListener(voicesAdapter);
        if (mor > 0)
            b = b * Math.pow(2.0, ((double) mor) / kli);
        else if (mor < 0)
            b = b / Math.pow(2.0, ((double) -mor) / kli);
        bash.setText(Double.toString(b));

        klimaka.setText(Integer.toString(kli));

        zwnh.removeTextChangedListener(textWatcher);
        zwnh.setText(Integer.toString(sharedPref.getInt(genos + " ζωνη", 130)));
        zwnh.addTextChangedListener(textWatcher);
        kezw.removeTextChangedListener(textWatcher);
        kezw.setText(Integer.toString(sharedPref.getInt(genos + " κεζω", 157)));
        kezw.addTextChangedListener(textWatcher);
        dike.removeTextChangedListener(textWatcher);
        dike.setText(Integer.toString(sharedPref.getInt(genos + " δικε", 199)));
        dike.addTextChangedListener(textWatcher);
        gadi.removeTextChangedListener(textWatcher);
        gadi.setText(Integer.toString(sharedPref.getInt(genos + " γαδι", 203)));
        gadi.addTextChangedListener(textWatcher);
        bouga.removeTextChangedListener(textWatcher);
        bouga.setText(Integer.toString(sharedPref.getInt(genos + " βουγα", 130)));
        bouga.addTextChangedListener(textWatcher);
        pabou.removeTextChangedListener(textWatcher);
        pabou.setText(Integer.toString(sharedPref.getInt(genos + " παβου", 157)));
        pabou.addTextChangedListener(textWatcher);
        nhpa.removeTextChangedListener(textWatcher);
        nhpa.setText(Integer.toString(sharedPref.getInt(genos + " νηπα", 199)));
        nhpa.addTextChangedListener(textWatcher);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("genos", genos);
        editor.apply();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + genos, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("SetTextI18n")
    private void setPrefs() {

        List<String> genoi = new ArrayList<>();
        genoi.add(StringConstants.Genoi.diat);
        genoi.add(StringConstants.Genoi.enam);
        genoi.add(StringConstants.Genoi.xrwm);
        genoi.add(StringConstants.Genoi.sklh);
        genoi.add(StringConstants.Genoi.pldt);
        genoi.add(StringConstants.Genoi.atet);
        genoi.add(StringConstants.Genoi.plbt);
        genoi.add(StringConstants.Genoi.bare);
        genoi.add(StringConstants.Genoi.bara);
        genoi.add(StringConstants.Genoi.bard);
        genoi.add(StringConstants.Genoi.bart);
        genoi.add(StringConstants.Genoi.barp);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        //sharedPref.edit().clear().apply();

        if (!sharedPref.getBoolean("firstTime", false)) {

            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putInt("volume", 33);

            genos = StringConstants.Genoi.diat;
            editor.putString("genos", genos);

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(130.37));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 199);
            editor.putInt(genos + " παβου", 157);
            editor.putInt(genos + " βουγα", 130);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 199);
            editor.putInt(genos + " κεζω", 157);
            editor.putInt(genos + " ζωνη", 130);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.enam;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(173.83));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 199);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 88);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 199);
            editor.putInt(genos + " κεζω", 199);
            editor.putInt(genos + " ζωνη", 88);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.xrwm;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(130.37));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 130);
            editor.putInt(genos + " παβου", 226);
            editor.putInt(genos + " βουγα", 130);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 130);
            editor.putInt(genos + " κεζω", 226);
            editor.putInt(genos + " ζωνη", 130);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.sklh;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(146.67));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 88);
            editor.putInt(genos + " παβου", 329);
            editor.putInt(genos + " βουγα", 69);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 88);
            editor.putInt(genos + " κεζω", 329);
            editor.putInt(genos + " ζωνη", 69);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.pldt;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(173.83));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 199);
            editor.putInt(genos + " παβου", 157);
            editor.putInt(genos + " βουγα", 130);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 157);
            editor.putInt(genos + " κεζω", 130);
            editor.putInt(genos + " ζωνη", 199);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.atet;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(146.67));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 157);
            editor.putInt(genos + " παβου", 130);
            editor.putInt(genos + " βουγα", 199);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 157);
            editor.putInt(genos + " κεζω", 130);
            editor.putInt(genos + " ζωνη", 199);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.plbt;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(146.67));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 88);
            editor.putInt(genos + " παβου", 329);
            editor.putInt(genos + " βουγα", 69);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 88);
            editor.putInt(genos + " κεζω", 329);
            editor.putInt(genos + " ζωνη", 69);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.bare;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(115.88));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 199);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 88);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 199);
            editor.putInt(genos + " κεζω", 199);
            editor.putInt(genos + " ζωνη", 88);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.bara;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(120.71));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 130);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 157);
            editor.putInt(genos + " γαδι", 130);
            editor.putInt(genos + " δικε", 199);
            editor.putInt(genos + " κεζω", 157);
            editor.putInt(genos + " ζωνη", 199);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.bard;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(120.71));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 130);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 157);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 130);
            editor.putInt(genos + " κεζω", 268);
            editor.putInt(genos + " ζωνη", 88);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.bart;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(120.71));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 130);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 157);
            editor.putInt(genos + " γαδι", 199);
            editor.putInt(genos + " δικε", 130);
            editor.putInt(genos + " κεζω", 199);
            editor.putInt(genos + " ζωνη", 157);
            editor.putInt(genos + " κλιμακα", 1171);

            genos = StringConstants.Genoi.barp;

            editor.putString(genos + " φωνη", StringConstants.Voices.andr);
            editor.putLong(genos + " βαση", Double.doubleToRawLongBits(120.71));
            editor.putInt(genos + " μορια", 0);
            editor.putInt(genos + " νηπα", 130);
            editor.putInt(genos + " παβου", 199);
            editor.putInt(genos + " βουγα", 157);
            editor.putInt(genos + " γαδι", 130);
            editor.putInt(genos + " δικε", 199);
            editor.putInt(genos + " κεζω", 199);
            editor.putInt(genos + " ζωνη", 157);
            editor.putInt(genos + " κλιμακα", 1171);


            editor.putBoolean("firstTime", true);

            editor.apply();
        }

        genos = sharedPref.getString("genos", StringConstants.Genoi.diat);

        int vol = sharedPref.getInt("volume", 33);
        volume.setText(Integer.toString(vol));
        tw.setVolume(vol / 100.0f * maxVolume);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genoi);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenoi.setAdapter(dataAdapter);
        spinnerGenoi.setOnItemSelectedListener(this);

        List<String> voices = new ArrayList<>();
        voices.add(StringConstants.Voices.andr);
        voices.add(StringConstants.Voices.gyna);
        voices.add(StringConstants.Voices.paid);

        categories = new ArrayList<>();

        categoriesAdapter = new CategoriesAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories, moria, klimaka);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesAdapter);
        spinnerCategories.setOnItemSelectedListener(categoriesAdapter);

        voicesAdapter = new VoicesAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, voices, categoriesAdapter, spinnerCategories);
        voicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVoices.setAdapter(voicesAdapter);

        voice = sharedPref.getString(genos + " φωνη", StringConstants.Voices.andr);

        spinnerVoices.setOnItemSelectedListener(null);
        switch (voice) {
            case StringConstants.Voices.andr: {
                spinnerVoices.setSelection(0, false);
                break;
            }
            case StringConstants.Voices.gyna: {
                spinnerVoices.setSelection(1, false);
                break;
            }
            default: {
                spinnerVoices.setSelection(2, false);
                break;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (genos.equals(genoi.get(i))) {
                spinnerGenoi.setSelection(i);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private int changeCategoriesMen(int mor, int kli) {

        spinnerCategories.setOnItemSelectedListener(null);

        if (mor <= -kli) {
            spinnerCategories.setSelection(0, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(-kli));
            return -kli;
        } else if (mor >= kli) {
            spinnerCategories.setSelection(9, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(kli));
            return kli;
        }

        double mtonos = ktonos * kli;
        double mtonio = ktonio * kli;

        if (mor <= -(int) Math.round(3 * mtonos + mtonio)) {
            spinnerCategories.setSelection(0, false);
        } else if (mor <= -(int) Math.round(2 * mtonos + mtonio)) {
            spinnerCategories.setSelection(1, false);
        } else if (mor <= -(int) Math.round(mtonos + mtonio)) {
            spinnerCategories.setSelection(2, false);
        } else if (mor <= -(int) Math.round(mtonos)) {
            spinnerCategories.setSelection(3, false);
        } else if (mor <= 0) {
            spinnerCategories.setSelection(4, false);
        } else if (mor <= (int) Math.round(mtonos)) {
            spinnerCategories.setSelection(5, false);
        } else if (mor <= (int) Math.round(2 * mtonos)) {
            spinnerCategories.setSelection(6, false);
        } else if (mor <= (int) Math.round(2 * mtonos + mtonio)) {
            spinnerCategories.setSelection(7, false);
        } else if (mor <= (int) Math.round(3 * mtonos + mtonio)) {
            spinnerCategories.setSelection(8, false);
        } else {
            spinnerCategories.setSelection(9, false);
        }

        spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
        moria.setText(Integer.toString(mor));
        return mor;
    }

    @SuppressLint("SetTextI18n")
    private int changeCategoriesWomen(int mor, int kli) {

        spinnerCategories.setOnItemSelectedListener(null);

        if (mor <= 0) {
            spinnerCategories.setSelection(0, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(0));
            return 0;
        } else if (mor >= (2 * kli)) {
            spinnerCategories.setSelection(8, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(2 * kli));
            return 2 * kli;
        }

        double mtonos = ktonos * kli;
        double mtonio = ktonio * kli;

        if (mor <= (int) Math.round(3 * mtonos + mtonio)) {
            spinnerCategories.setSelection(0, false);
        } else if (mor <= (int) Math.round(4 * mtonos + mtonio)) {
            spinnerCategories.setSelection(1, false);
        } else if (mor <= (int) Math.round(4 * mtonos + 2 * mtonio)) {
            spinnerCategories.setSelection(2, false);
        } else if (mor <= kli) {
            spinnerCategories.setSelection(3, false);
        } else if (mor <= (kli + (int) Math.round(mtonos))) {
            spinnerCategories.setSelection(4, false);
        } else if (mor <= (kli + (int) Math.round(2 * mtonos))) {
            spinnerCategories.setSelection(5, false);
        } else if (mor <= (kli + (int) Math.round(2 * mtonos + mtonio))) {
            spinnerCategories.setSelection(6, false);
        } else if (mor <= (kli + (int) Math.round(3 * mtonos + mtonio))) {
            spinnerCategories.setSelection(7, false);
        } else {
            spinnerCategories.setSelection(8, false);
        }

        spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
        moria.setText(Integer.toString(mor));
        return mor;
    }

    @SuppressLint("SetTextI18n")
    private int changeCategoriesChildren(int mor, int kli) {

        spinnerCategories.setOnItemSelectedListener(null);

        if (mor <= 0) {
            spinnerCategories.setSelection(0, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(0));
            return 0;
        } else if (mor >= (2 * kli)) {
            spinnerCategories.setSelection(3, false);
            spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
            moria.setText(Integer.toString(2 * kli));
            return 2 * kli;
        }

        double mtonos = ktonos * kli;

        if (mor <= kli) {
            spinnerCategories.setSelection(0, false);
        } else if (mor <= (kli + (int) Math.round(mtonos))) {
            spinnerCategories.setSelection(1, false);
        } else if (mor <= (kli + (int) Math.round(2 * mtonos))) {
            spinnerCategories.setSelection(2, false);
        } else {
            spinnerCategories.setSelection(3, false);
        }

        spinnerCategories.setOnItemSelectedListener(categoriesAdapter);
        moria.setText(Integer.toString(mor));
        return mor;
    }

    private void savePrefs() {

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        try {

            editor.putString(genos + " φωνη", voice);
            editor.putInt(genos + " μορια", Integer.parseInt(moria.getText().toString()));
            editor.putInt(genos + " νηπα", Integer.parseInt(nhpa.getText().toString()));
            editor.putInt(genos + " παβου", Integer.parseInt(pabou.getText().toString()));
            editor.putInt(genos + " βουγα", Integer.parseInt(bouga.getText().toString()));
            editor.putInt(genos + " γαδι", Integer.parseInt(gadi.getText().toString()));
            editor.putInt(genos + " δικε", Integer.parseInt(dike.getText().toString()));
            editor.putInt(genos + " κεζω", Integer.parseInt(kezw.getText().toString()));
            editor.putInt(genos + " ζωνη", Integer.parseInt(zwnh.getText().toString()));
            editor.putInt(genos + " κλιμακα", Integer.parseInt(klimaka.getText().toString()));
            editor.putInt("volume", Integer.parseInt(volume.getText().toString()));

            editor.apply();

        } catch (NumberFormatException ignored) {

        }
    }

    @SuppressLint("SetTextI18n")
    private void resetPrefs() {

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        switch (genos) {
            case StringConstants.Genoi.diat:
                editor.putInt(genos + " νηπα", 199);
                editor.putInt(genos + " παβου", 157);
                editor.putInt(genos + " βουγα", 130);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 199);
                editor.putInt(genos + " κεζω", 157);
                editor.putInt(genos + " ζωνη", 130);
                break;
            case StringConstants.Genoi.enam:
            case StringConstants.Genoi.bare:
                editor.putInt(genos + " νηπα", 199);
                editor.putInt(genos + " παβου", 199);
                editor.putInt(genos + " βουγα", 88);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 199);
                editor.putInt(genos + " κεζω", 199);
                editor.putInt(genos + " ζωνη", 88);
                break;
            case StringConstants.Genoi.xrwm:
                editor.putInt(genos + " νηπα", 130);
                editor.putInt(genos + " παβου", 226);
                editor.putInt(genos + " βουγα", 130);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 130);
                editor.putInt(genos + " κεζω", 226);
                editor.putInt(genos + " ζωνη", 130);
                break;
            case StringConstants.Genoi.sklh:
            case StringConstants.Genoi.plbt:
                editor.putInt(genos + " νηπα", 88);
                editor.putInt(genos + " παβου", 329);
                editor.putInt(genos + " βουγα", 69);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 88);
                editor.putInt(genos + " κεζω", 329);
                editor.putInt(genos + " ζωνη", 69);
                break;
            case StringConstants.Genoi.pldt:
                editor.putInt(genos + " νηπα", 199);
                editor.putInt(genos + " παβου", 157);
                editor.putInt(genos + " βουγα", 130);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 157);
                editor.putInt(genos + " κεζω", 130);
                editor.putInt(genos + " ζωνη", 199);
                break;
            case StringConstants.Genoi.atet:
                editor.putInt(genos + " νηπα", 157);
                editor.putInt(genos + " παβου", 130);
                editor.putInt(genos + " βουγα", 199);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 157);
                editor.putInt(genos + " κεζω", 130);
                editor.putInt(genos + " ζωνη", 199);
                break;
            case StringConstants.Genoi.bara:
                editor.putInt(genos + " νηπα", 130);
                editor.putInt(genos + " παβου", 199);
                editor.putInt(genos + " βουγα", 157);
                editor.putInt(genos + " γαδι", 130);
                editor.putInt(genos + " δικε", 199);
                editor.putInt(genos + " κεζω", 157);
                editor.putInt(genos + " ζωνη", 199);
                break;
            case StringConstants.Genoi.bard:
                editor.putInt(genos + " νηπα", 130);
                editor.putInt(genos + " παβου", 199);
                editor.putInt(genos + " βουγα", 157);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 130);
                editor.putInt(genos + " κεζω", 268);
                editor.putInt(genos + " ζωνη", 88);
                break;
            case StringConstants.Genoi.bart:
                editor.putInt(genos + " νηπα", 130);
                editor.putInt(genos + " παβου", 199);
                editor.putInt(genos + " βουγα", 157);
                editor.putInt(genos + " γαδι", 199);
                editor.putInt(genos + " δικε", 130);
                editor.putInt(genos + " κεζω", 199);
                editor.putInt(genos + " ζωνη", 157);
                break;
            case StringConstants.Genoi.barp:
                editor.putInt(genos + " νηπα", 130);
                editor.putInt(genos + " παβου", 199);
                editor.putInt(genos + " βουγα", 157);
                editor.putInt(genos + " γαδι", 130);
                editor.putInt(genos + " δικε", 199);
                editor.putInt(genos + " κεζω", 199);
                editor.putInt(genos + " ζωνη", 157);
                break;
        }

        editor.putInt(genos + " κλιμακα", 1171);
        editor.putString(genos + " φωνη", StringConstants.Voices.andr);
        editor.putInt(genos + " μορια", 0);
        editor.apply();

        spinnerVoices.setOnItemSelectedListener(null);
        spinnerVoices.setSelection(0, false);
        voice = StringConstants.Voices.andr;
        voicesAdapter.setCategories();
        spinnerVoices.setOnItemSelectedListener(voicesAdapter);

        spinnerCategories.setOnItemSelectedListener(null);
        spinnerCategories.setSelection(4, false);
        spinnerCategories.setOnItemSelectedListener(categoriesAdapter);

        klimaka.setText(Integer.toString(1171));
        moria.setText(Integer.toString(0));

        zwnh.removeTextChangedListener(textWatcher);
        zwnh.setText(Integer.toString(sharedPref.getInt(genos + " ζωνη", 130)));
        zwnh.addTextChangedListener(textWatcher);
        kezw.removeTextChangedListener(textWatcher);
        kezw.setText(Integer.toString(sharedPref.getInt(genos + " κεζω", 157)));
        kezw.addTextChangedListener(textWatcher);
        dike.removeTextChangedListener(textWatcher);
        dike.setText(Integer.toString(sharedPref.getInt(genos + " δικε", 199)));
        dike.addTextChangedListener(textWatcher);
        gadi.removeTextChangedListener(textWatcher);
        gadi.setText(Integer.toString(sharedPref.getInt(genos + " γαδι", 203)));
        gadi.addTextChangedListener(textWatcher);
        bouga.removeTextChangedListener(textWatcher);
        bouga.setText(Integer.toString(sharedPref.getInt(genos + " βουγα", 130)));
        bouga.addTextChangedListener(textWatcher);
        pabou.removeTextChangedListener(textWatcher);
        pabou.setText(Integer.toString(sharedPref.getInt(genos + " παβου", 157)));
        pabou.addTextChangedListener(textWatcher);
        nhpa.removeTextChangedListener(textWatcher);
        nhpa.setText(Integer.toString(sharedPref.getInt(genos + " νηπα", 199)));
        nhpa.addTextChangedListener(textWatcher);

        double b = Double.longBitsToDouble(sharedPref.getLong(genos + " βαση", Double.doubleToRawLongBits(130.37)));
        restartIfToneWasPlayedOrRebase(b);
    }

    private void setWidthsAndHeights() {

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int dp = (int) (5 * (displayMetrics.densityDpi / 160f));
        int h = (int) (displayMetrics.heightPixels / 10.5);

        TableRow.LayoutParams tp = new TableRow.LayoutParams(displayMetrics.widthPixels / 4 - (2 * dp), h);
        tp.setMargins(dp, 0, dp, 0);
        buttonAdi.setLayoutParams(tp);
        buttonAga.setLayoutParams(tp);
        buttonAbou.setLayoutParams(tp);
        buttonApa.setLayoutParams(tp);
        buttonAnh.setLayoutParams(tp);
        buttonZw.setLayoutParams(tp);
        buttonKe.setLayoutParams(tp);
        buttonDi.setLayoutParams(tp);
        buttonGa.setLayoutParams(tp);
        buttonBou.setLayoutParams(tp);
        buttonPa.setLayoutParams(tp);
        buttonNh.setLayoutParams(tp);
        buttonKzw.setLayoutParams(tp);
        buttonKke.setLayoutParams(tp);
        buttonKdi.setLayoutParams(tp);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(displayMetrics.widthPixels / 4 - (2 * dp), h);
        lp.setMargins(dp, 0, dp, 0);
        klimaka.setLayoutParams(lp);
        zwnh.setLayoutParams(lp);
        kezw.setLayoutParams(lp);
        dike.setLayoutParams(lp);
        gadi.setLayoutParams(lp);
        bouga.setLayoutParams(lp);
        pabou.setLayoutParams(lp);
        nhpa.setLayoutParams(lp);

        bash.setLayoutParams(lp);
        sta.setLayoutParams(lp);

        kate.setLayoutParams(lp);
        moria.setLayoutParams(lp);
        anev.setLayoutParams(lp);

        plyn.setLayoutParams(lp);
        volume.setLayoutParams(lp);
        syn.setLayoutParams(lp);

        apoth.setLayoutParams(lp);
        epan.setLayoutParams(lp);
        anap.setLayoutParams(lp);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(displayMetrics.widthPixels / 2 - (2 * dp), h);
        lp2.setMargins(dp, 0, dp, 0);
        spinnerGenoi.setLayoutParams(lp2);

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(displayMetrics.widthPixels / 3 - (2 * dp), h);
        lp3.setMargins(dp, 0, dp, 0);
        spinnerVoices.setLayoutParams(lp3);

        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(displayMetrics.widthPixels / 3 * 2 - (2 * dp), h);
        lp4.setMargins(dp, 0, dp, 0);
        spinnerCategories.setLayoutParams(lp4);
    }

}
