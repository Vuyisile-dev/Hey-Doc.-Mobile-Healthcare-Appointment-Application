package com.example.heydoc

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DoctorListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_doctor_list)

        listView = findViewById(R.id.listDoctors)

        val doctors = arrayOf(
            "Dr Smith - General Practitioner",
            "Dr Naidoo - Dentist",
            "Dr Patel - Cardiologist",
            "Dr Mkhize - Pediatrician",
            "Dr Khumalo - Dermatologist"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            doctors
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->

            val intent = Intent(
                this,
                BookAppointmentActivity::class.java
            )

            intent.putExtra(
                "doctor",
                doctors[position]
            )

            startActivity(intent)
        }
    }
}