package com.sample.browserstack.samplecalculator;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sample.browserstack.samplecalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private double firstNum = Double.NaN;
    private double secondNum;
    private boolean equalClicked = false;
    private String operator = "";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.editText.setText("");

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText("");
            }
        });
        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("0");
            }
        });
        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("1");
            }
        });
        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("2");
            }
        });
        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("3");
            }
        });
        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("4");
            }
        });
        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("5");
            }
        });
        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("6");
            }
        });
        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("7");
            }
        });
        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("8");
            }
        });
        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput("9");
            }
        });
        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInput(".");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation("+");
            }
        });
        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation("-");
            }
        });
        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation("/");

            }
        });
        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation("*");
            }
        });
        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondNum = Double.parseDouble(binding.editText.getText().toString());
                Double result = Double.NaN;
                if (!Double.isNaN(firstNum)) {
                    if (operator == "+") {
                        result = firstNum + secondNum;
                    } else if (operator == "-") {
                        result = firstNum - secondNum;
                    } else if (operator == "/") {
                        result = firstNum / secondNum;
                    } else if (operator == "*") {
                        result = firstNum * secondNum;
                    }
                }
                operator = "";
                equalClicked = true;
                firstNum = Double.NaN;
                DecimalFormat df = new DecimalFormat("#.####");
                if (Double.isNaN(result)) {
                    binding.editText.setText("");
                } else {
                    binding.editText.setText(df.format(result).toString());
                }
            }
        });
    }

    private void handleOperation(String inputOperation) {
        if (operator == "") {
            firstNum = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText("");
            operator = inputOperation;
        } else {
            operator = inputOperation;
        }
        equalClicked = false;
    };

    private void handleInput(String s) {
        if (equalClicked) {
            binding.editText.setText("");
        }
        binding.editText.setText(binding.editText.getText() + s);
        equalClicked = false;
    }
}
