package com.mishka.graphqltest.domain.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mishka.graphqltest.R
import com.mishka.graphqltest.databinding.ItemCharacterBinding
import com.mishka.graphqltest.interactors.dashboard.CharacterModel

class CharacterAdapter :
    ListAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(characterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CharacterModel) {
            binding.imageCharacter.setImageURI(model.image)
            binding.textGender.text = model.gender
            binding.textName.text = model.name
            binding.textOrigin.text = model.origin.name
        }
    }

    companion object {

        fun characterDiffUtil(): DiffUtil.ItemCallback<CharacterModel> {
            return object : DiffUtil.ItemCallback<CharacterModel>() {

                override fun areItemsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem == newItem
                }
            }

        }

    }

}