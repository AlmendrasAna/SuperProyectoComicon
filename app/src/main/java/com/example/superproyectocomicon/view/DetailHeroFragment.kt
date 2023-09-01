package com.example.superproyectocomicon.view

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.superproyectocomicon.R
import com.example.superproyectocomicon.data.local.HeroDetailsEntity
import com.example.superproyectocomicon.databinding.FragmentDetailHeroBinding

private const val ARG_KEY = "id"

class DetailHeroFragment : Fragment() {
    private var paramId = 0
    lateinit var binding: FragmentDetailHeroBinding
    private val heroVM: HeroVM by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt(ARG_KEY)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailHeroBinding.inflate(inflater, container, false)
        heroVM.getOneDetailsHero(paramId)
        init()
        return binding.root
    }

    private fun init() {
        val id = paramId
        val heroDetails = heroVM.getOneDetailsHeroId(id)
        heroDetails.observe(viewLifecycleOwner) { heroDetails ->
            if (heroDetails != null) {
                binding.nameDetailHeroTxt.text = heroDetails.name
                binding.imageHero.load(heroDetails.img)
                binding.power.text = heroDetails.power
                binding.origin.text = heroDetails.origin
                binding.year.text = heroDetails.yearCreation
                binding.color.text = heroDetails.color

                var txt: String
                if (heroDetails.translation) {
                    txt = getString(R.string.translation_true)
                } else {
                    txt = getString(R.string.translation_false)
                }
                binding.traslation.text = txt

                binding.sendMailButton.setOnClickListener {

                    sendMail(heroDetails)
                }

            }
        }
    }
    fun  sendMail(heroDetailsEntity: HeroDetailsEntity){


        val mail = getString(R.string.destinatario_msn)
        val bodyMsn = getString(R.string.body_msn, heroDetailsEntity.name)
        val asunt = getString(R.string.asunt, heroDetailsEntity.name)
        val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(mail))

        intentEmail.type = "plain/text"
        intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))

        intentEmail.putExtra(Intent.EXTRA_SUBJECT, asunt)

        intentEmail.putExtra(Intent.EXTRA_TEXT, bodyMsn)

        startActivity(Intent.createChooser(intentEmail, asunt))
    }
}