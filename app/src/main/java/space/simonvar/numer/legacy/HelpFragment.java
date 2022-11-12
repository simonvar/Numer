package space.simonvar.numer.legacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import space.simonvar.numer.R;
import space.simonvar.numer.legacy.common.Translator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {
    private  static final String TAG = "IntentHelp";

    private String mNumber;
    private String mSystemFrom;
    private String mSystemTo;

    TextView mTextViewHelpTask;
    TextView mTextViewHelpAnswer;

    TextView mTvAlgorithm1;

    TextView mTvAlgorithm2_div;
    TextView mTvAlgorithm2_ost;
    TextView mTvAlgorithm2_res;

    TextView mTvHelpText1;
    TextView mTvHelpText2;


    public static HelpFragment newInstance(){
        return new HelpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        setHasOptionsMenu(true);
        mNumber = getActivity().getIntent().getStringExtra(HelpActivity.EXTRA_NUMBER);
        mSystemFrom = getActivity().getIntent().getStringExtra(HelpActivity.EXTRA_SYSTEM_FROM);
        mSystemTo = getActivity().getIntent().getStringExtra(HelpActivity.EXTRA_SYSTEM_TO);

        //задача
        String task = mNumber + "(" + mSystemFrom + ")" + " = ???(" + mSystemTo + ")";
        mTextViewHelpTask = view.findViewById(R.id.text_view_help_task);
        mTextViewHelpTask.setText(task);

        //Шаг 1
        mTvAlgorithm1 =  view.findViewById(R.id.help_1_algorithm);
        mTvAlgorithm1.setText(algorithmTo10(mNumber, mSystemFrom));

        mTvHelpText1 = view.findViewById(R.id.help_step_1_algorithm_text);
        mTvHelpText1.setText(getResources().getString(R.string.help_text_translate, mSystemFrom, "10"));


        //Шаг 2
        mTvAlgorithm2_div = view.findViewById(R.id.help_2_algorithm_1);
        mTvAlgorithm2_ost = view.findViewById(R.id.help_2_algorithm_2);
        mTvAlgorithm2_res = view.findViewById(R.id.help_2_algorithm_3);
        algorithmFrom10(Translator.Translate(mNumber, mSystemFrom, "10"), mSystemTo);
        mTvHelpText2 = view.findViewById(R.id.help_step_2_algorithm_text);
        mTvHelpText2.setText(getResources().getString(R.string.help_text_translate, "10", mSystemTo));

        //Ответ
        mTextViewHelpAnswer = view.findViewById(R.id.text_view_help_answer);
        String answer = Translator.Translate(mNumber, mSystemFrom, mSystemTo);
        mTextViewHelpAnswer.setText(answer);

        if (mSystemFrom.equals("10")){
            View card = view.findViewById(R.id.card_step_1);
            ((ViewGroup)card.getParent()).removeView(card);

            TextView header = view.findViewById(R.id.header_step_2);
            header.setText(R.string.help_step_1);
        }


        if (mSystemTo.equals("10")){
            View card = view.findViewById(R.id.card_step_2);
            ((ViewGroup)card.getParent()).removeView(card);
        }

        return view;
    }

    private void algorithmFrom10(String number, String systemTo){
        long num = Long.parseLong(number);
        long ost;
        long system = Long.parseLong(systemTo);

        StringBuilder strDiv = new StringBuilder(mTvAlgorithm2_div.getText().toString());
        StringBuilder strOst = new StringBuilder(mTvAlgorithm2_ost.getText().toString());
        StringBuilder strRes = new StringBuilder(mTvAlgorithm2_res.getText().toString());

        while (num > 0){

            ost = num % system;
            strOst.append(String.valueOf(ost)).append("\n");

           if (ost >= 10) {
               ost += 55L;
               char c = (char) ost;
               strRes.append(c);
           }else{
                strRes.append(String.valueOf(ost));
            }
            strRes.append("\n");
            strDiv.append(String.valueOf(num)).append(" / ").append(systemTo).append("\n");
            num /= system;
        }

        mTvAlgorithm2_div.setText(strDiv.toString());
        mTvAlgorithm2_ost.setText(strOst.toString());
        mTvAlgorithm2_res.setText(strRes.toString());
    }

    private String algorithmTo10(String number, String systemFrom){
        StringBuilder result = new StringBuilder(number + "(" + systemFrom + ") = ");
        char ch;

        for (int i = number.length() - 1; i >= 0; i--){
            ch = number.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                int mul = (int) ch - 55;
                result.append(String.valueOf(mul));
            }else{
                result.append(number.charAt(i));
            }

            result.append("*").append(systemFrom).append(getAllPower(number.length() - 1 - i));
            if (i != 0){
                result.append(" + ");
            }
        }
        result.append(" = ");
        result.append(Translator.Translate(number, systemFrom, "10")).append("(10)");

        return result.toString();
    }

    private String getAllPower(int i){
        if(i % 10 == i){
            return getPower(i);
        }else{
            return getPower((i/10)%10) + getPower(i%10);
        }
    }

    private String getPower(int i){
        switch (i){
            case 0:
                return "⁰";
            case 1:
                return "¹";
            case 2:
                return "²";
            case 3:
                return "³";
            case 4:
                return "⁴";
            case 5:
                return "⁵";
            case 6:
                return "⁶";
            case 7:
                return "⁷";
            case 8:
                return "⁸";
            case 9:
                return "⁹";
            default:
                return "";
        }
    }

}
