package com.shine.semav.numbersystem.presentation.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shine.semav.numbersystem.R
import com.shine.semav.numbersystem.common.Translator
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.android.synthetic.main.fragment_converter.view.*

class ConverterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_converter, container, false)
        setHasOptionsMenu(true)

        view.number_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val num = if (s.isNullOrBlank()) "0" else s.toString()
                val result = Translator.Translate(num,
                        number_system_picker.value.toString(),
                        answer_system_picker.value.toString())
                view.number_answer.text = result
            }
        })

        view.number_system_picker.setOnValueChangedListener { _, _, newVal ->
            val s = view.number_edit_text.text
            val num = if (s.isNullOrBlank()) "0" else s.toString()
            val result = Translator.Translate(num,
                    newVal.toString(),
                    answer_system_picker.value.toString())
            view.number_answer.text = result
        }

        view.answer_system_picker.setOnValueChangedListener { _, _, newVal ->
            val s = view.number_edit_text.text
            val num = if (s.isNullOrBlank()) "0" else s.toString()
            val result = Translator.Translate(num,
                    number_system_picker.value.toString(),
                    newVal.toString())
            view.number_answer.text = result
        }

        return view
    }

    companion object {

        fun newInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }


}
