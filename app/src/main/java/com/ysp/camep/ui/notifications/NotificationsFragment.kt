package com.ysp.camep.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ysp.camep.databinding.NotificationsFragmentBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: NotificationsFragmentBinding
    private val notificationsViewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = NotificationsFragmentBinding.inflate(layoutInflater, container, false)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })
        return binding.root
    }
}