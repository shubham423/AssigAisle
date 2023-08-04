package com.example.assigaisle.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.assigaisle.databinding.FragmentNotesBinding
import com.example.assigaisle.presentation.notes.adapters.InvitesAdapter
import com.example.assigaisle.presentation.notes.adapters.LikesAdapter
import com.example.assigaisle.utils.Resource
import com.example.assigaisle.utils.gone
import com.example.assigaisle.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment() {
    private lateinit var binding:FragmentNotesBinding
    private lateinit var invitesAdapter: InvitesAdapter
    private lateinit var likesAdapter: LikesAdapter
    private val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotes()
        invitesAdapter= InvitesAdapter()
        binding.rvInvites.adapter=invitesAdapter

        likesAdapter= LikesAdapter()
        binding.rvLikes.adapter=likesAdapter
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notesState.collect{
                when(it){
                    is Resource.Error -> {
                        binding.progressBar.gone()
                    }
                    is Resource.Loading ->{
                        binding.progressBar.visible()
                    }
                    is Resource.Success ->{
                        binding.progressBar.gone()
                        it.let {
                            invitesAdapter.submitList(it.data?.invites?.profiles)
                            likesAdapter.submitList(it.data?.likes?.profiles)
                        }
                    }
                }
            }
        }
    }
}