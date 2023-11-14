package com.thecocktailapp.presentation.alerts

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import com.mzaragozaserrano.presentation.view.base.BaseComposeView
import com.thecocktailapp.presentation.vo.ErrorAlertVO

class ErrorAlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseComposeView<ErrorAlertVO>(context, attrs, defStyleAttr) {
    override var content: @Composable () -> Unit = {
        ErrorAlert(idMessage = item.idMessage) {
            item.onRetryButtonClicked()
        }
    }
}