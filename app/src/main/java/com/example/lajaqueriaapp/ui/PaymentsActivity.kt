package com.example.lajaqueriaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lajaqueriaapp.databinding.ActivityPaymentsBinding
import com.example.lajaqueriaapp.ui.adapters.PaymentAdapter
import com.example.lajaqueriaapp.viewmodel.PaymentsViewModel

class PaymentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentsBinding
    private lateinit var paymentsViewModel: PaymentsViewModel
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos el ViewModel
        paymentsViewModel = ViewModelProvider(this)[PaymentsViewModel::class.java]

        // Configurar el RecyclerView
        paymentAdapter = PaymentAdapter()
        binding.rvPayments.layoutManager = LinearLayoutManager(this)
        binding.rvPayments.adapter = paymentAdapter

        // Observar los datos del ViewModel
        paymentsViewModel.payments.observe(this) { payments ->
            paymentAdapter.submitList(payments)
        }

        // Cargar pagos
        paymentsViewModel.getPayments()
    }
}