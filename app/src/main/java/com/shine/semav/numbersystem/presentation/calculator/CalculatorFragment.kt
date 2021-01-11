package com.shine.semav.numbersystem.presentation.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shine.semav.numbersystem.R

class CalculatorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        return view
    }

    companion object {
        fun newInstance(): CalculatorFragment {
            return CalculatorFragment()
        }
    }

}
