package com.flowers.ui.information

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flowers.databinding.FragmentInformationBinding


class InformationFragment: Fragment() {

    private var _binding: FragmentInformationBinding?=null

    private val binding get() = _binding!!

    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View{
        _binding= FragmentInformationBinding.inflate(inflater,container,false)
        val root: View= binding.root
        initializeButtons()

        return root
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }
    fun initializeButtons() {
        binding.defButton.setOnClickListener {
            goToUrl("https://www.cio.com/article/230929/information-blindness.html")
        }
        binding.exeButton.setOnClickListener {
            goToUrl("https://www.health.harvard.edu/mind-and-mood/six-relaxation-techniques-to-reduce-stress")
        }
        binding.difButton.setOnClickListener {
            goToUrl("https://lifehacker.com/four-tricks-to-help-you-make-any-difficult-decision-987762341")
        }
    }
}