package com.admin.chalengesicredi.adapter

import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.Event


interface CellClickListene {

    fun onCellClickListener(data : Event)

    fun onCellClickListener2(data : DetailEvent)
}