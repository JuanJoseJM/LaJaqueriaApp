package com.example.lajaqueriaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lajaqueriaapp.data.model.Payment
import com.example.lajaqueriaapp.data.repository.PaymentRepository

class PaymentsViewModel : ViewModel() {

    private val _payments = MutableLiveData<List<Payment>>()
    val payments: LiveData<List<Payment>> get() = _payments

    private val repository = PaymentRepository()

    fun getPayments() {
        _payments.value = repository.getPayments()
    }
}