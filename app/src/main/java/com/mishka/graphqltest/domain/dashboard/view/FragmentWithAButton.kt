package com.mishka.graphqltest.domain.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.FragmentCharacterDetailBinding
import com.mishka.graphqltest.databinding.FragmentWithAButtonBinding
import com.mishka.graphqltest.domain.dashboard.viewmodel.CharacterDetailViewModel
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class FragmentWithAButton : Fragment() {

    private lateinit var binding: FragmentWithAButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_with_a_button, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        binding.buttonDisappear.setOnClickListener{
            binding.textCommonText.visibility = if(binding.textCommonText.isVisible) View.GONE else View.VISIBLE
        }
    }

}