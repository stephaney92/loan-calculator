package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText carCost;
    private EditText downPayment;
    private EditText apr;
    private RadioButton loanButton;
    private RadioButton leaseButton;
    private SeekBar lengthOfLease;
    private TextView barLabel;
    private EditText monthlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carCost = findViewById(R.id.carCost);
        downPayment = findViewById(R.id.downPayment);
        apr = findViewById(R.id.apr);
        loanButton = findViewById(R.id.loanButton);
        leaseButton = findViewById(R.id.leaseButton);
        lengthOfLease = findViewById(R.id.lengthOfLease);
        barLabel = findViewById(R.id.barLabel);
        monthlyPayment = findViewById(R.id.monthlyPayment);

        lengthOfLease.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //connects the seek bar to the bar label
                //when the bar slides up the number in the view gets higher
                barLabel.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void convert(View v) {
        if (loanButton.isChecked()) {
            String inputCarCost = carCost.getText().toString();
            String inputDownPayment = downPayment.getText().toString();
            String inputApr = apr.getText().toString();
            String inputNumMonths = barLabel.getText().toString();
            if (inputCarCost.isEmpty() || inputDownPayment.isEmpty() || inputApr.isEmpty() || inputNumMonths.isEmpty()){
                Toast.makeText(this, "All fields must be entered", Toast.LENGTH_SHORT).show();
            }
            else {
                //turns string into a number
                double carCostValue = Double.parseDouble(inputCarCost);
                double downPaymentValue = Double.parseDouble(inputDownPayment);
                double aprValue = Double.parseDouble(inputApr);
                //grabbing the input from the seek bar view
                double barLabelValue = Double.parseDouble(inputNumMonths);
                //total apr seems to work when the format is .0377
                double totalApr = aprValue / 12;
                double loanAmount = carCostValue - downPaymentValue;
                double power = Math.pow((1 + totalApr), -barLabelValue);
                double monthlyPaymentValue = (totalApr * loanAmount) / (1 - power);
                //does the calculation of monthly payment value and sets it in the monthly payment
                monthlyPayment.setText(String.format("%.2f", monthlyPaymentValue));

            }

        } else if (leaseButton.isChecked()) {
            String inputCarCost = carCost.getText().toString();
            String inputDownPayment = downPayment.getText().toString();
            String inputApr = apr.getText().toString();
            String inputNumMonths = barLabel.getText().toString();
            if (inputCarCost.isEmpty() || inputDownPayment.isEmpty() || inputApr.isEmpty() || inputNumMonths.isEmpty()){
                Toast.makeText(this, "All fields must be entered", Toast.LENGTH_SHORT).show();
            }
            else {
                //turns string into a number
                double carCostValue = Double.parseDouble(inputCarCost);
                double downPaymentValue = Double.parseDouble(inputDownPayment);
                double aprValue = Double.parseDouble(inputApr);
                //grabbing the input from the seek bar view
                double barLabelValue = Double.parseDouble(inputNumMonths);
                double totalApr = aprValue / 12;
                double loanAmount = (carCostValue / 3) - downPaymentValue;
                double power = Math.pow((1 + totalApr), -barLabelValue);
                double monthlyPaymentValue = (totalApr * loanAmount) / (1 - power);
                //does the calculation of monthly payment value and sets it in the monthly payment
                monthlyPayment.setText(String.format("%.2f", monthlyPaymentValue));
            }
        }

    }
}
