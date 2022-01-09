package com.basemibrahim.redditnews.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.basemibrahim.redditnews.databinding.DetailsFragmentBinding
import com.basemibrahim.redditnews.databinding.ListFragmentBinding
import com.basemibrahim.redditnews.utils.Constants.Companion.BODY
import com.basemibrahim.redditnews.utils.Constants.Companion.IMG
import com.basemibrahim.redditnews.utils.Constants.Companion.TITLE


class DetailsFragment : Fragment() {
    private lateinit var _binding: DetailsFragmentBinding
    private lateinit var title: String
    private lateinit var img: String
    private lateinit var body: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE).toString()
            img = it.getString(IMG).toString()
            body = it.getString(BODY).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (img!="null") {
            _binding.image.load(img)
        } else {
            _binding.image.visibility = View.GONE
        }
        _binding.body.text = body
    }

   }