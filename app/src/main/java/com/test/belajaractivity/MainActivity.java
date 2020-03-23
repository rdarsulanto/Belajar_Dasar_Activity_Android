package com.test.belajaractivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth;
    private EditText edtLength;
    private EditText edtHeight;
    private Button btntambah;
    private Button btnkurang;
    private Button btnbagi;
    private Button btnKali;
    private TextView tvResult;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btntambah = findViewById(R.id.btn_tambah);
        btnkurang = findViewById(R.id.btn_kurang);
        btnbagi = findViewById(R.id.btn_bagi);
        btnKali = findViewById(R.id.btn_kali);
        tvResult = findViewById(R.id.tv_result);

        btntambah.setOnClickListener(this);
        btnkurang.setOnClickListener(this);
        btnbagi.setOnClickListener(this);
        btnKali.setOnClickListener(this);

        if (savedInstanceState != null) {
            String hasil = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(hasil);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {

            String inputPanjang = edtLength.getText().toString().trim();
            String inputLebar = edtWidth.getText().toString().trim();
            String inputTinggi = edtHeight.getText().toString().trim();

            boolean kaloKosong = false;
            boolean kaloDoubleSalah = false;

            if (TextUtils.isEmpty(inputPanjang)){
                kaloKosong = true;
                edtLength.setError("Kolom tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLebar)){
                kaloKosong = true;
                edtWidth.setError("Kolom tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputTinggi)){
                kaloKosong = true;
                edtHeight.setError("Kolom tidak boleh kosong");
            }

            Double panjang = buatDouble (inputPanjang);
            Double lebar = buatDouble (inputLebar);
            Double tinggi = buatDouble(inputTinggi);

            if (panjang == null) {
                kaloDoubleSalah = true;
                edtLength.setError("Field harus berupa Angka");
            }
            if (lebar == null) {
                kaloDoubleSalah = true;
                edtWidth.setError("Field harus berupa Angka");
            }
            if (tinggi == null) {
                kaloDoubleSalah = true;
                edtHeight.setError("Field harus berupa Angka");
            }

        if (v.getId() == R.id.btn_kali ) {
            if (!kaloKosong && !kaloDoubleSalah) {
                double perkalian = panjang * lebar * tinggi;

                tvResult.setText(String.valueOf(perkalian));
            }

        }

        if (v.getId() == R.id.btn_tambah ) {
            if (!kaloKosong && !kaloDoubleSalah) {
                double pertambahan = panjang + lebar + tinggi;

                tvResult.setText(String.valueOf(pertambahan));
            }

        }

        if (v.getId() == R.id.btn_bagi ) {
            if (!kaloKosong && !kaloDoubleSalah) {
                double pembagian = panjang / lebar / tinggi;

                tvResult.setText(String.valueOf(pembagian));
            }

        }

        if (v.getId() == R.id.btn_kurang ) {
            if (!kaloKosong && !kaloDoubleSalah) {
                double pengurangan = panjang - lebar - tinggi;

                tvResult.setText(String.valueOf(pengurangan));
            }

        }
    }

    private Double buatDouble (String input) {
        try {
            return Double.valueOf(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
