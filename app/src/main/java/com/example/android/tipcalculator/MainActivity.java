package com.example.android.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.billAmount)
    EditText editText;
    int tipPercent = 10;
    double billAmount;
    @BindView(R.id.tipPercent)
    TextView tipPercentView;
    @BindView(R.id.tipAmount)
    TextView tipAmountView;
    @BindView(R.id.totalBill)
    TextView totalBillView;
    double totalAmount;

    public void setPercent() {
        tipPercentView.setText(tipPercent + ".0% Tip Percent");
    }

    public void setAmount() {
        tipAmountView.setText("$" + billAmount * tipPercent / 100.0 + " Tip Total");
    }


    @OnClick(R.id.sad)
    public void sadClick() {
        tipPercent = 10;
        updatePage();
    }

    @OnClick(R.id.moderate)
    public void moderateClick() {
        tipPercent = 15;
        updatePage();
    }

    @OnClick(R.id.happy)
    public void happyClick() {
        tipPercent = 20;
        updatePage();
    }

    public void updatePage() {
        setPercent();
        setAmount();
        setTotalAmount();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editText.getText().toString();
                if (text.length() != 0) {
                    billAmount = Double.parseDouble(text);
                    updatePage();
                } else {
                    billAmount = 0.0;
                    updatePage();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTotalAmount() {
        calculateTotalAmount();
        totalBillView.setText("$" + totalAmount);
    }

    private void calculateTotalAmount() {
        totalAmount = billAmount + (billAmount * tipPercent / 100.0);
    }
}
