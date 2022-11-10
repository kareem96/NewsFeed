package com.kareemdev.newsfeed.presentation.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kareemdev.core.data.Resource
import com.kareemdev.newsfeed.R
import com.kareemdev.newsfeed.databinding.FragmentDashboardBinding
import com.kareemdev.newsfeed.presentation.adpater.NewsAdapter
import com.kareemdev.newsfeed.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private val dashBoardViewModel: DashboardViewModel by viewModel()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val newsAdapter = NewsAdapter()
            newsAdapter.onItemClick = { selectedData ->
                Log.d("TAG", "To detail screen")
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            dashBoardViewModel.news.observe(viewLifecycleOwner) { news ->
                if (news != null){
                    when(news){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success ->{
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(news.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = news.message ?: getString(R.string.something_error)
                        }
                    }
                }
            }
            with(binding.rvNews){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}