package com.guillermoraya.bank.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guillermoraya.domain.BankRow
import com.guillermoraya.bank.ui.common.ScopedViewModel
import com.guillermoraya.usecases.GetBankTransactionInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getBankTransactions: GetBankTransactionInfo) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val bankList: List<BankRow>) : UiModel()
        class Navigation(val bankRow: BankRow) : UiModel()
        object RequestContent : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestContent
    }

    fun onContent() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getBankTransactions.invoke())
        }
    }

    fun onBankRowClicked(bankRow: BankRow) {
        _model.value = UiModel.Navigation(bankRow)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}