package com.guillermoraya.bank.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.guillermoraya.bank.databinding.ActivityMainBinding
import com.guillermoraya.bank.ui.main.MainViewModel.UiModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BankRowAdapter

    private val presenter = MainPresenter() // this presenter could be injected

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BankRowAdapter(viewModel::onBankRowClicked)
        binding.recycler.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {

        Timber.i("model is ${model}")

        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> adapter.bankTransactionList = presenter.sortedListByDate(model.bankList)
            UiModel.RequestContent -> viewModel.onContent() // you can use this approach to perform more actions before displaying data
        }
    }
}