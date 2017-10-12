package com.shine.semav.numbersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shine.semav.numbersystem.common.Translator;

/**
 * Created by semav on 04.09.2016.
 */
public class CalculatorFragment extends Fragment {

    private EditText mEditTextNumber1;
    private EditText mEditTextNumberSystem1;
    private EditText mEditTextNumber2;
    private EditText mEditTextNumberSystem2;
    private ImageButton mButtonClearAll;
    private TextView mTextViewResult;
    private EditText mEditTextResultSystem;
    private FloatingActionButton mFloatingActionButton;

    private RadioGroup mRadioGroup;
    private int mSign;

    public static CalculatorFragment newInstance() {
        return new CalculatorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        setHasOptionsMenu(true);

        mEditTextNumber1 = (EditText)view.findViewById(R.id.calculator_number_1);
        mEditTextNumber2 = (EditText)view.findViewById(R.id.calculator_number_2);
        mEditTextNumberSystem1 = (EditText) view.findViewById(R.id.calculator_number_system_1);
        mEditTextNumberSystem2 = (EditText) view.findViewById(R.id.calculator_number_system_2);

        mTextViewResult = (TextView) view.findViewById(R.id.calculator_result);
        mEditTextResultSystem = (EditText) view.findViewById(R.id.calculator_result_system);

        mButtonClearAll = (ImageButton) view.findViewById(R.id.calculator_clear_all);
        mButtonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTextNumberSystem1.setText("");
                mEditTextNumberSystem2.setText("");
                mEditTextNumber1.setText("");
                mEditTextNumber2.setText("");
                mTextViewResult.setText("");
                mEditTextResultSystem.setText("");
            }
        });

        RadioButton plus = (RadioButton) view.findViewById(R.id.calculator_radio_plus);
        plus.setChecked(true);
        mSign = 1;

        mRadioGroup = (RadioGroup) view.findViewById(R.id.calculator_radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case -1:
                        mSign = 0;
                        break;
                    case R.id.calculator_radio_plus:
                        mSign = 1;
                        break;
                    case R.id.calculator_radio_minus:
                        mSign = 2;
                        break;
                    case R.id.calculator_radio_mul:
                        mSign = 3;
                        break;
                    case R.id.calculator_radio_div:
                        mSign = 4;
                        break;
                }
            }
        });

        mFloatingActionButton = view.findViewById(R.id.floating_action_button_calculator);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = mEditTextNumber1.getText().toString().toUpperCase();
                String number2 = mEditTextNumber2.getText().toString().toUpperCase();
                String numberSystem1 = mEditTextNumberSystem1.getText().toString();
                String numberSystem2 = mEditTextNumberSystem2.getText().toString();
                String resultSystem = mEditTextResultSystem.getText().toString();

                String errorText;
                Snackbar snackbar;


                if (number1.equals("") || number2.equals("")
                        || numberSystem1.equals("") || numberSystem2.equals("") || resultSystem.equals("")){
                    errorText = getString(R.string.error_field_is_empty);
                    snackbar = Snackbar.make(view, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }

                int res = Translator.CheckCalculator(number1, number2, numberSystem1, numberSystem2, resultSystem);
                switch (res){
                    case 0 :
                        String result = Translator.Calculate(number1, number2, numberSystem1, numberSystem2, resultSystem, mSign);
                        if (result.length() > 25){
                            mTextViewResult.setText(getString(R.string.error_too_big));

                        }else{
                            mTextViewResult.setText(result);
                        }
                        break;
                    case 1:
                        errorText = getString(R.string.error_number_not_correct);
                        snackbar = Snackbar.make(view, errorText, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                    case 2:
                        errorText = getString(R.string.error_system_from_not_correct);
                        snackbar = Snackbar.make(view, errorText, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                    case 3:
                        errorText = getString(R.string.error_system_range_from);
                        snackbar = Snackbar.make(view, errorText, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                    case 4:
                        errorText = getString(R.string.error_number_not_in_system);
                        snackbar = Snackbar.make(view, errorText, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                }
            }
        });

        return view;
    }

}
