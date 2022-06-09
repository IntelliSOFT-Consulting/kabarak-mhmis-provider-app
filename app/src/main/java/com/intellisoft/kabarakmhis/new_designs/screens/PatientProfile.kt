package com.intellisoft.kabarakmhis.new_designs.screens

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.intellisoft.kabarakmhis.R
import com.intellisoft.kabarakmhis.helperclass.FormatterClass
import com.intellisoft.kabarakmhis.new_designs.antenatal_profile.AntenatalProfile
import com.intellisoft.kabarakmhis.new_designs.medical_history.MedicalHistory
import com.intellisoft.kabarakmhis.new_designs.medical_history.MedicalSurgicalHistoryView
import com.intellisoft.kabarakmhis.new_designs.new_patient.PatientDetailsView
import com.intellisoft.kabarakmhis.new_designs.physical_examination.PhysicalExamination
import com.intellisoft.kabarakmhis.new_designs.physical_examination.PhysicalExaminationView
import com.intellisoft.kabarakmhis.new_designs.previous_pregnancy.PreviousPregnancy
import com.intellisoft.kabarakmhis.new_designs.previous_pregnancy.PreviousPregnancyView
import kotlinx.android.synthetic.main.activity_patient_profile.*

class PatientProfile : AppCompatActivity() {

    private val formatter = FormatterClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        cardViewHistory.setOnClickListener {
            val intent = Intent(this, MedicalHistory::class.java)
            startActivity(intent)
        }
        cardViewPastPreg.setOnClickListener {
            val intent = Intent(this, PreviousPregnancy::class.java)
            startActivity(intent)
        }
        cardViewPhysicalExam.setOnClickListener {
            val intent = Intent(this, PhysicalExamination::class.java)
            startActivity(intent)
        }
        cardViewAntenatal.setOnClickListener {
            val intent = Intent(this, AntenatalProfile::class.java)
            startActivity(intent)
        }
        linearLayoutCall.setOnClickListener {

            val txtPhone = tvKinDetails.text.toString()
            if (!TextUtils.isEmpty(txtPhone)){
                calluser(txtPhone)
            }

        }

    }

    private fun calluser(value: String){
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$value")
        startActivity(dialIntent)
    }

    override fun onStart() {
        super.onStart()

        getPatientData()
    }

    private fun getPatientData() {

        val patientName = formatter.retrieveSharedPreference(this, "name")
        val dob = formatter.retrieveSharedPreference(this, "dob")

        val kinRelationShip = formatter.retrieveSharedPreference(this, "kinRelationShip")
        val kinName = formatter.retrieveSharedPreference(this, "kinName")
        val kinPhoneNumber = formatter.retrieveSharedPreference(this, "kinPhoneNumber")

        if (kinRelationShip != null && kinName != null && kinPhoneNumber != null){

            val kinDetails = "$kinName \n$kinPhoneNumber"
            tvKinDetails.text = kinPhoneNumber

        }

        tvName.text = patientName
        tvAge.text = dob

    }

    fun navigatePreviousPreg(view: View) {
        val intent = Intent(this, PreviousPregnancy::class.java)
        startActivity(intent)
    }

    fun navigateAntenatalProfile(view: View) {
        val intent = Intent(this, AntenatalProfile::class.java)
        startActivity(intent)
    }
    fun medicalHistory(view: View) {
        val intent = Intent(this, MedicalHistory::class.java)
        startActivity(intent)
    }
    fun navigatePhysical(view: View) {
        val intent = Intent(this, PhysicalExamination::class.java)
        startActivity(intent)
    }

    fun navigatePatientDetails(view: View) {
        val intent = Intent(this, PatientDetailsView::class.java)
        startActivity(intent)
    }
}