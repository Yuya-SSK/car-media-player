package com.ysp.camep.ui.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ysp.camep.databinding.AudioFragmentBinding

class AudioFragment : Fragment() {

    private lateinit var binding: AudioFragmentBinding
    private val audioViewModel: AudioViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AudioFragmentBinding.inflate(layoutInflater, container, false)
        audioViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })
        return binding.root
    }
}