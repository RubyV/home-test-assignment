package com.example.home_test_travel.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.home_test_travel.R
import com.example.home_test_travel.common.constant.Constants.ATTRACTION_DETAIL
import com.example.home_test_travel.common.extension.load
import com.example.home_test_travel.common.extension.setTextHighLight
import com.example.home_test_travel.data.attractions.AttractionsData
import com.example.home_test_travel.databinding.FragmentAttractionDetailBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AttractionDetailFragment : Fragment() {

    companion object {
        fun newInstance() = AttractionDetailFragment()
    }
    private var _binding: FragmentAttractionDetailBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var gson: Gson

    private var attractionDetail: AttractionsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated() called with: view = $view, savedInstanceState = $savedInstanceState")
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initUI()

    }

    private fun initUI() {
        val ivAttractionDetail: ImageView = binding.ivAttractionDetail
        val tvAttractionTitle: TextView = binding.tvAttractionTitle
        val tvPlaceUrl: TextView = binding.tvPlaceUrl
        val webViewUrl: WebView = binding.webViewUrl

        if((attractionDetail?.images?.size ?: 0) > 0)
        {
            ivAttractionDetail.load(attractionDetail?.images?.get(0)?.src ?: "", requireContext())
        }
        else
        {
            ivAttractionDetail.setImageDrawable(requireContext().getDrawable(R.drawable.ic_loading))
        }
        tvAttractionTitle.text = attractionDetail?.name
        tvAttractionTitle.text = attractionDetail?.introduction
        tvPlaceUrl.setTextHighLight(attractionDetail?.url ?: "") {
            webViewUrl.visibility = View.VISIBLE
            webViewUrl.loadUrl(attractionDetail?.url ?: "")
        }
    }


    private fun initArguments() {
        attractionDetail = gson.fromJson(arguments?.getString(ATTRACTION_DETAIL, ""), AttractionsData::class.java)
    }

}