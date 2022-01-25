package com.mishka.graphqltest.domain.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.FragmentCharacterDetailBinding
import com.mishka.graphqltest.databinding.FragmentCharactersBinding
import com.mishka.graphqltest.databinding.FragmentCharactersBindingImpl
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.util.BottomMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private lateinit var adapter: EpisodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setSubscribers()
    }

    private fun setSubscribers() {
        viewModel.viewState.observe(this) {
            it?.characters?.let { character ->
                updateViewWithCharacter(character)
            }
        }
    }

    private fun updateViewWithCharacter(character: CharacterModel) {
        binding.imageProfile.setImageURI(character.image)
        binding.textOriginHolder.text = character.origin.name
        binding.textGenderHolder.text = character.gender
        binding.textNameHolder.text = character.name
        adapter.submitList(character.episodes)
    }

    private fun setView() {

        adapter = EpisodeAdapter()
        binding.recyclerEpisodes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.addItemDecoration(BottomMarginDecoration(16))
            this.adapter = this@CharacterDetailFragment.adapter
        }
    }
}