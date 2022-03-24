package com.example.ch08_07e2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.CompoundButton;
import android.widget.AdapterView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numPicker;
    private Spinner sprMain;
    private ImageView imgView;
    private CheckBox chkShrimp, chkTofu;
    private RadioGroup rdgDessert;
    private Button btnOrder;

    private  String mainDish= "", sideDish= "", dessert = "", result = "";
    private int table = 0, mainPrice = 0, sidePrice = 0, total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numPicker = findViewById(R.id.npkTable);
        sprMain = findViewById(R.id.sprMain);
        imgView = findViewById(R.id.imgView);
        chkShrimp = findViewById(R.id.chkShrimp);
        chkTofu = findViewById(R.id.chkTofu);
        rdgDessert = findViewById(R.id.rdgDessert);
        btnOrder = findViewById(R.id.btnOrder);

        numPicker.setMinValue(1);
        numPicker.setMaxValue(10);
        numPicker.setValue(1);

        sprMain.setOnItemSelectedListener(sprMainOnItemSelected);
        chkShrimp.setOnCheckedChangeListener(chkListener);
        chkTofu.setOnCheckedChangeListener(chkListener);
        btnOrder.setOnClickListener(btnOrderClick);
    }
    private AdapterView.OnItemSelectedListener sprMainOnItemSelected = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
            switch(i){
                case 0:
                    mainDish = getString(R.string.grilledFishSteak);
                    mainPrice = 200;
                    imgView.setImageResource(R.drawable.fish);
                    break;
                case 1:
                    mainDish = getString(R.string.bakedPrawns);
                    mainPrice = 250;
                    imgView.setImageResource(R.drawable.prawn);
                    break;
                case 2:
                    mainDish = getString(R.string.frenchRoastChicken);
                    mainPrice = 300;
                    imgView.setImageResource(R.drawable.chicken);
                    break;
                case 3:
                    mainDish = getString(R.string.sauteedSteak);
                    mainPrice = 350;
                    imgView.setImageResource(R.drawable.steak);
                    break;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView){
        }
    };

    private CheckBox.OnCheckedChangeListener chkListener = new CheckBox.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            String sideDishShrimp = "", sideDishTofu = "";
            int priceShrimp = 0, priceTofu = 0;
            if (chkShrimp.isChecked()){
                sideDishShrimp = getString(R.string.chkShrimp);
                priceShrimp = 80;
            } else{
                sideDishShrimp = "";
                priceShrimp = 0;
            }
            if (chkTofu.isChecked()){
                sideDishTofu = getString(R.string.chkTofu);
                priceTofu = 50;
            } else{
                sideDishTofu = "";
                priceTofu = 0;
            }
            sideDish = sideDishShrimp + sideDishTofu;
            sidePrice = priceShrimp + priceTofu;
        }
    };

    private View.OnClickListener btnOrderClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int table = numPicker.getValue();
            switch (rdgDessert.getCheckedRadioButtonId()) {
                case R.id.rdbIceCream:
                    dessert = getString(R.string.rdbIceCream);
                    break;
                case R.id.rdbJelly:
                    dessert = getString(R.string.rdbJelly);
                    break;
            }
            if (sideDish == "")
                sideDish = getString(R.string.nothing);
            total = mainPrice + sidePrice;

            result = getString(R.string.txtTable) + table + "\n"
                      + getString(R.string.txtMain) + mainDish + "\n"
                      + getString(R.string.txtSide) + sideDish + "\n"
                      + getString(R.string.txtDesert) + dessert + "\n"
                      + getString(R.string.total) + total + getString(R.string.dollar);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.alerDlgTitle)
                    .setMessage(result)
                    .setIcon(android.R.drawable.ic_input_get)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.btnYes),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    Toast.makeText(MainActivity.this,
                                            getString(R.string.yesMsg), Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                    .setNegativeButton(getString(R.string.btnCancel),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    Toast.makeText(MainActivity.this,
                                            getString(R.string.cancelMsg), Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                    .show();
        }
    };
}