package com.mishka.graphqltest.domain.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.FragmentCharactersBinding
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.util.BottomMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersDashboardFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    private val viewModel by viewModels<CharactersDashboardViewModel>()

    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setSubscribers()
    }

    private fun setSubscribers() {
        viewModel.viewState.observe(this) {
            adapter.submitList(it.characters)
        }
    }

    private fun setView() {
        adapter = CharacterAdapter(::onClickListener)
        binding.recyclerMain.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = this@CharactersDashboardFragment.adapter
            this.addItemDecoration(BottomMarginDecoration(16))
        }
    }

    private fun onClickListener(character: CharacterModel){
        findNavController().navigate(
            R.id.characterDetailFragment,
            bundleOf("id" to character.id)
        )
    }
}