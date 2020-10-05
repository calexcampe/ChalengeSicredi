package com.admin.chalengesicredi.ui.main

import android.os.Bundle
import android.widget.Toast
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.model.CheckinResult
import com.admin.chalengesicredi.model.dto.CheckinDto
import com.admin.chalengesicredi.ui.main.viewmodel.CheckinViewModel
import kotlinx.android.synthetic.main.activity_checkin.*
import kotlinx.android.synthetic.main.activity_detail_event_activity.*
import com.admin.chalengesicredi.base.BaseActivity as BaseActivity1

class CheckinActivity : BaseActivity1<CheckinViewModel>() {

    private lateinit var  bundle: Bundle
    private var idevento: String? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar2)

        bundle = intent.extras!!
        idevento = bundle.getString("id");

        configtoolba()
        listener()
    }

    fun listener(){

        btnconfirmar.setOnClickListener {

            val checkinDto = CheckinDto(edtnome.text,edtemail.text, idevento.toString())
            postCheckin()
            viewModel.postCheckin(checkinDto)
        }

    }

    fun configtoolba(){

        toolbar2.setNavigationIcon(R.drawable.backwhite)
        toolbar2.setNavigationOnClickListener {

            onBackPressed()
        }
    }

    fun postCheckin(){

        viewModel.repoResult.observe(this) { result ->
            when (result) {
                is CheckinResult.Success -> {

                    val codresp = result.data
                    Toast.makeText(this, "Checkin realizado com sucesso", Toast.LENGTH_LONG).show()
                    onBackPressed()
                }
                is CheckinResult.Error -> {
                    Toast.makeText(
                        this,
                        "\uD83D\uDE28 Falha $result.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun getViewModel() = CheckinViewModel::class.java
    override val layoutRes = R.layout.activity_checkin
}