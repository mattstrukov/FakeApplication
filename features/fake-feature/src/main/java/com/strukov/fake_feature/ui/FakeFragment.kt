package com.strukov.fake_feature.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.strukov.core.adapter.DiffAdapter
import com.strukov.core.data.Result
import com.strukov.core.extensions.gone
import com.strukov.core.extensions.visible
import com.strukov.fake_feature.databinding.FakeFragmentBinding
import com.strukov.fake_feature.ui.adapter.fakeAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FakeFragment : Fragment() {

    companion object {
        const val TAG = "FakeFragment"

        fun newInstance() = FakeFragment()
    }

    private var _binding: FakeFragmentBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val adapter = DiffAdapter(fakeAdapterDelegate())
    private lateinit var viewModel: FakeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FakeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        viewModel = ViewModelProvider(this).get(FakeViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progress.visible()
                        }
                        is Result.Empty -> {
                            binding.progress.gone()
                        }
                        is Result.Error -> {
                            binding.progress.gone()
                            binding.error.visible()
                        }
                        is Result.Success -> {
                            binding.progress.gone()
                            adapter.items = result.data
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.posts.adapter = null
        _binding = null
    }

    private fun initRecycler() {
        with(binding.posts) {
            adapter = this@FakeFragment.adapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                true
            )
        }
    }
}