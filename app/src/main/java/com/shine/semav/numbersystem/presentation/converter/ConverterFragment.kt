package com.shine.semav.numbersystem.presentation.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.shine.semav.numbersystem.R
import com.shine.semav.numbersystem.common.Translator
import com.shine.semav.numbersystem.databinding.FragmentConverterBinding

class ConverterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConverterBinding.bind(view)

        binding.numberEditText.doOnTextChanged { text, _, _, _ ->
            calculate(binding, text)
        }

        binding.numberSystemPicker.setOnValueChangedListener { _, _, _ ->
            calculate(binding, binding.numberEditText.text)
        }

        binding.answerSystemPicker.setOnValueChangedListener { _, _, _ ->
            calculate(binding, binding.numberEditText.text)
        }
    }

    private fun calculate(binding: FragmentConverterBinding, text: CharSequence?) {
        val num = if (text.isNullOrBlank()) "0" else text.toString()
        val result = Translator.Translate(
            num,
            binding.numberSystemPicker.value.toString(),
            binding.answerSystemPicker.value.toString()
        )
        binding.numberAnswer.text = result
    }


    companion object {
        fun newInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }
}
