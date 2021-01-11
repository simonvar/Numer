package com.shine.semav.numbersystem.presentation.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shine.semav.numbersystem.BuildConfig
import com.shine.semav.numbersystem.R
import com.shine.semav.numbersystem.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInfoBinding.bind(view)

        binding.infoRate.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PACKAGE)))
            } catch (anfe: Throwable) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + PACKAGE)))
            }
        }

        binding.fragmentInfoGithub.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_github))))
        }
    }

    companion object {
        const val PACKAGE = BuildConfig.APPLICATION_ID
    }
}
