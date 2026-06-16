package com.example.heydoc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var patientName: EditText
    private lateinit var date: EditText
    private lateinit var btnBook: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_book_appointment)

        patientName = findViewById(R.id.patientName)
        date = findViewById(R.id.date)
        btnBook = findViewById(R.id.bookBtn)

        val doctor = intent.getStringExtra("doctor")

        val db = DatabaseHelper(this)

        btnBook.setOnClickListener {

            val result = db.bookAppointment(
                patientName.text.toString(),
                doctor,
                date.text.toString()
            )

            if(result){

                Toast.makeText(
                    this,
                    "Appointment Booked",
                    Toast.LENGTH_LONG
                ).show()

            }else{

                Toast.makeText(
                    this,
                    "Booking Failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}