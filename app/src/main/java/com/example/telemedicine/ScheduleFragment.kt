package com.example.telemedicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.example.telemedicine.api.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ScheduleFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorListAdapter: DoctorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        doctorListAdapter = DoctorListAdapter()
        ApiService.instance.sendFormRequest(
            Request.Method.GET, "Doctor/GetDoctorList", null,
            {
                val doctorList: List<Doctor> = Gson().fromJson(it, object : TypeToken<List<Doctor>>() {}.type)
                doctorListAdapter.doctorList.addAll(doctorList)
            }
        )
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorListAdapter
        }
        return view
    }
}