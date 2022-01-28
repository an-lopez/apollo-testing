package com.mishka.graphqltest.domain.dashboard.view

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.FragmentCharacterDetailBinding
import com.mishka.graphqltest.domain.dashboard.viewmodel.CharacterDetailViewModel
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import com.mishka.graphqltest.util.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    private val viewModel by viewModels<CharacterDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setSubscribers()
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    private fun setView() {
        binding.textImageHolder.textChanges().debounce(200).onEach {
            binding.imageProfile.setImageURI(it.toString())
        }.launchIn(lifecycleScope)

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, items2)
        binding.textGenderHolder.setAdapter(adapter)
        binding.textOriginHolder.setAdapter(adapter2)

        binding.editSave.setOnClickListener {
            detectIfSaveOrUpdate()
        }
    }

    private fun detectIfSaveOrUpdate() {
        val characterModel = buildObject()
        if(arguments?.containsKey("id") == true){
            viewModel.updateCharacter(characterModel.copy(id = arguments?.getInt("id", 0) ?: 0))
        }else{
            viewModel.saveNewCharacter(characterModel)
            findNavController().navigateUp()
        }

    }

    private fun buildObject(): CharacterModel {
        return CharacterModel(
            id = 0,
            origin = OriginModel(0, binding.textOriginHolder.text.toString(), "", ""),
            episodes = emptyList(),
            gender = binding.textGenderHolder.text.toString(),
            image = binding.textImageHolder.text.toString(),
            name = binding.textNameHolder.text.toString()
        )
    }

    private fun setSubscribers() {
        viewModel.viewState.observe(viewLifecycleOwner){ vs ->
            vs.characters?.let{
                binding.textNameHolder.setText(it.name)
                binding.textImageHolder.setText(it.image)
                items2.withIndex().firstOrNull { obj -> obj.value == it.origin.name }?.let { pair ->
                    binding.textOriginHolder.setText(pair.value, false)
                }
                items.withIndex().firstOrNull { obj -> obj.value == it.gender }?.let { pair ->
                    binding.textGenderHolder.setText(pair.value, false)
                }
            }
        }
    }

    companion object{
        val items = listOf("Masculino", "Femenino")
        val items2 = listOf("Nuevo León", "Colombia", "Estado de México", "CDMX", "Sonora")
    }

}