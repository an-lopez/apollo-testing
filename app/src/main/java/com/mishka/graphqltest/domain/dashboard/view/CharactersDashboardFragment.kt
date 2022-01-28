package com.mishka.graphqltest.domain.dashboard.view

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.FragmentCharactersBinding
import com.mishka.graphqltest.domain.dashboard.components.CharacterAdapter
import com.mishka.graphqltest.domain.dashboard.viewmodel.CharactersDashboardViewModel
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.util.BottomMarginDecoration
import com.mishka.graphqltest.util.Order
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersDashboardFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    private val viewModel by viewModels<CharactersDashboardViewModel>()

    private lateinit var adapter: CharacterAdapter

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters(Order.AtoZ)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.a_to_z -> viewModel.getCharacters(Order.AtoZ)
            R.id.z_to_a -> viewModel.getCharacters(Order.ZtoA)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setSubscribers()
    }

    private fun setSubscribers() {
        viewModel.viewState.observe(requireActivity()) {
            it.characters?.let{ list ->
                adapter.submitList(list)
            }
        }
    }

    private fun setView() {
        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(
                R.id.characterDetailFragment
            )
        }

        adapter = CharacterAdapter(::onItemClickCallback)
        binding.recyclerMain.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = this@CharactersDashboardFragment.adapter
            this.addItemDecoration(BottomMarginDecoration(16))
        }
    }

    private fun onItemClickCallback(character: CharacterModel){
        findNavController().navigate(
            R.id.characterDetailFragment,
            bundleOf("id" to character.id)
        )
    }


}