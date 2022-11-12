package com.shine.semav.numbersystem;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shine.semav.numbersystem.common.StringPreferences;
import com.shine.semav.numbersystem.common.Translator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConverterFragment extends Fragment {

    private EditText mEditTextNumber;
    private EditText mEditTextFrom;
    private EditText mEditTextTo;
    private ImageButton mImageButtonClearNumber;
    private ImageButton mImageButtonClearAll;
    private ImageButton mImageButtonReverseSystems;
    private FloatingActionButton fab;

    private TextView mTextViewResult;

    private TextView mTextViewResult2;
    private TextView mTextViewResult8;
    private TextView mTextViewResult10;
    private TextView mTextViewResult16;

    private ImageView mImageViewHowIt;

    String numberText;

    public static ConverterFragment newInstance() {
        return new ConverterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_new, container, false);
        setHasOptionsMenu(true);

        mEditTextNumber = view.findViewById(R.id.number_edit_text);
        mEditTextFrom = view.findViewById(R.id.system_from_edit_text);
        mEditTextTo = (EditText) view.findViewById(R.id.system_to_edit_text);

        mEditTextNumber.setText(StringPreferences.getNumber(getActivity()));
        mEditTextFrom.setText(StringPreferences.getSystemFrom(getActivity()));
        mEditTextTo.setText(StringPreferences.getSystemTo(getActivity()));


        mImageButtonClearNumber = (ImageButton) view.findViewById(R.id.delete_number_button);
        mImageButtonClearNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextNumber.setText(null);
            }
        });

        mImageButtonClearAll = (ImageButton) view.findViewById(R.id.image_button_clear_all);
        mImageButtonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextNumber.setText(null);
                mEditTextTo.setText(null);
                mEditTextFrom.setText(null);
                mTextViewResult.setText(null);
                mTextViewResult2.setText(null);
                mTextViewResult8.setText(null);
                mTextViewResult10.setText(null);
                mTextViewResult16.setText(null);
                StringPreferences.saveStrings(getActivity(), "", "", "");
            }
        });

        mImageButtonReverseSystems = (ImageButton) view.findViewById(R.id.reverse_systems_button);
        mImageButtonReverseSystems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFrom = mEditTextFrom.getText().toString();
                String textTo = mEditTextTo.getText().toString();

                mEditTextFrom.setText(textTo);
                mEditTextTo.setText(textFrom);
            }
        });

        mTextViewResult = view.findViewById(R.id.result_text_view);
        mTextViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mTextViewResult.getText().toString();
                if (!text.equals("")) {
                    ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboardManager != null) {
                        clipboardManager.setPrimaryClip(ClipData.newPlainText("Convert", text));
                        Toast.makeText(getActivity(), R.string.copied, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mTextViewResult2 = view.findViewById(R.id.result_2_system);
        mTextViewResult8 = view.findViewById(R.id.result_8_system);
        mTextViewResult10 = view.findViewById(R.id.result_10_system);
        mTextViewResult16 = view.findViewById(R.id.result_16_system);

        mImageViewHowIt = view.findViewById(R.id.how_it_happen);
        mImageViewHowIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numberText = mEditTextNumber.getText().toString().toUpperCase();
                String systemFrom = mEditTextFrom.getText().toString();
                String systemTo = mEditTextTo.getText().toString();


                if (checkNumbers(numberText, systemFrom, systemTo)){
                    String result = Translator.Translate(numberText, systemFrom, systemTo);
                    if (result.length() > 20) {
                        Snackbar.make(view, R.string.error_too_big, Snackbar.LENGTH_SHORT).show();
                    } else {
                        Intent i = HelpActivity.newIntent(getActivity(), numberText, systemFrom, systemTo);
                        StringPreferences.saveStrings(getActivity(), numberText, systemFrom, systemTo);
                        startActivity(i);
                    }
                }

            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberText = mEditTextNumber.getText().toString().toUpperCase();
                String systemFrom = mEditTextFrom.getText().toString();
                String systemTo = mEditTextTo.getText().toString();

                if (checkNumbers(numberText, systemFrom, systemTo)) {
                    StringPreferences.saveStrings(getActivity(), numberText, systemFrom, systemTo);
                    String result = Translator.Translate(numberText, systemFrom, systemTo);
                    String system2Result = Translator.Translate(numberText, systemFrom, "2");
                    String system8Result = Translator.Translate(numberText, systemFrom, "8");
                    String system10Result = Translator.Translate(numberText, systemFrom, "10");
                    String system16Result = Translator.Translate(numberText, systemFrom, "16");

                    if (result.length() > 20) {
                        mTextViewResult.setText(getString(R.string.error_too_big));

                    } else {
                        mTextViewResult.setText(result);
                    }

                    if (system2Result.length() > 20) {
                        mTextViewResult2.setText(getString(R.string.error_too_big));

                    } else {
                        mTextViewResult2.setText(system2Result);
                    }

                    if (system8Result.length() > 20) {
                        mTextViewResult8.setText(getString(R.string.error_too_big));

                    } else {
                        mTextViewResult8.setText(system8Result);
                    }

                    if (system10Result.length() > 20) {
                        mTextViewResult10.setText(getString(R.string.error_too_big));

                    } else {
                        mTextViewResult10.setText(system10Result);
                    }

                    if (system16Result.length() > 20) {
                        mTextViewResult16.setText(getString(R.string.error_too_big));

                    } else {
                        mTextViewResult16.setText(system16Result);
                    }
                }
            }
        });

        return view;
    }


    private boolean checkNumbers(String number, String systemFrom, String systemTo) {
        String errorText;
        Snackbar snackbar;

        View v = getActivity().findViewById(R.id.floating_action_button);

        if (number.equals("") || systemFrom.equals("") || systemTo.equals("")) {
            errorText = getString(R.string.error_field_is_empty);
            snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }

        int res = Translator.Check(number, systemFrom, systemTo);
        if (res == 0) {
            return true;
        } else {
            switch (res) {
                case 1:
                    errorText = getString(R.string.error_number_not_correct);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                case 2:
                    errorText = getString(R.string.error_system_from_not_correct);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                case 3:
                    errorText = getString(R.string.error_system_to_not_correct);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                case 4:
                    errorText = getString(R.string.error_system_range_from);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                case 5:
                    errorText = getString(R.string.error_system_range_to);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                case 6:
                    errorText = getString(R.string.error_number_not_in_system);
                    snackbar = Snackbar.make(v, errorText, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                default:
                    return false;
            }
            return false;
        }
    }

}
