package com.example.home_test_travel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home_test_travel.R
import com.example.home_test_travel.common.constant.Constants.ATTRACTION_DETAIL
import com.example.home_test_travel.data.attractions.AttractionsData
import com.example.home_test_travel.data.category.Category
import com.example.home_test_travel.databinding.FragmentHomeBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    @Inject
    lateinit var gson: Gson

    private val homeViewModel by viewModels<HomeViewModel>()

    private val attractionsAdapter: AttractionsAdapter by lazy {
        AttractionsAdapter(adapterCallback)
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    private val adapterCallback = object : AttractionsAdapter.AttractionAdapterListener {

        override fun onAttractionClick(attractionsData: AttractionsData) {
            var bundle = bundleOf(
                ATTRACTION_DETAIL to gson.toJson(attractionsData)
            )
            findNavController().navigate(R.id.navigation_attraction_detail, bundle)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setupMenu()
        homeViewModel.attractionListLiveData.observe(
            viewLifecycleOwner,
            Observer(this@HomeFragment::observeAttractionList)
        )
        homeViewModel.categoryListLiveData.observe(
            viewLifecycleOwner,
            Observer(this@HomeFragment::observeCategoryList)
        )


    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.action_change_lang -> {
                        openDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
    private fun observeAttractionList(attractionsData: List<AttractionsData>?) {
        if (attractionsData != null) {
            attractionsAdapter.items = attractionsData
            attractionsAdapter.notifyDataSetChanged()
        }

    }

    private fun observeCategoryList(categoryData: List<Category>?) {
        if (categoryData != null) {
            categoryAdapter.items = categoryData
            categoryAdapter.notifyDataSetChanged()
        }

    }


    private fun initUi() {
        val tvHomeTitle: TextView = binding.tvTitle
        val tvHomeUserName: TextView = binding.tvUserName
        val tvCategoryTitle: TextView = binding.tvCategoryTitle
        val tvAttractionTitle: TextView = binding.tvAttractionTitle
        val ivAvtar : CircleImageView = binding.ivUserAvatar
        val rvAttractions: RecyclerView = binding.rvAttractions
        val rvCategory: RecyclerView = binding.rvCategory
        homeViewModel.text.observe(viewLifecycleOwner) {
            tvHomeTitle.text = it
        }
        tvHomeUserName.text = "Alex"
        tvCategoryTitle.text = "Category"
        tvAttractionTitle.text = "Popular places"
        ivAvtar.setImageDrawable(context?.getDrawable(R.drawable.ic_user_avatar))

        var itemDecorator =  DividerItemDecoration(
            context,
            DividerItemDecoration.HORIZONTAL
        )
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) };
        rvCategory.run {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            adapter = categoryAdapter
            itemDecorator = itemDecorator
        }

        var itemDecoratorVertical =  DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) };
        rvAttractions.run {
            layoutManager = LinearLayoutManager(context)
            adapter = attractionsAdapter
            itemDecorator = itemDecoratorVertical
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle("Switch language")
            .setItems(
                arrayOf("zh-tw", "zh-cn", "en", "ja", "ko", "es", "id", "th", "vi")
            ) { _, which ->
                val lang =  when (which) {
                    0 -> "zh-tw"
                    1 -> "zh-cn"
                    2 -> "en"
                    3 -> "ja"
                    4 -> "ko"
                    5 -> "es"
                    6 -> "id"
                    7 -> "th"
                    8 -> "vi"
                    else -> "zh-tw"
                }
                homeViewModel.getAllAttractions(
                    lang = lang
                )
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}