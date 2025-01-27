package com.thecocktailapp.presentation.base

import android.content.DialogInterface
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mzs.core.presentation.base.CoreBaseActivity
import com.mzs.core.presentation.base.CoreMVIViewModel
import com.mzs.core.presentation.utils.generic.emptyText
import com.thecocktailapp.presentation.R

abstract class TheCocktailAppBaseActivity<State, Intent, Action, Result, VB : ViewBinding, VM : CoreMVIViewModel<State, Intent, Action, Result>> :
    CoreBaseActivity<State, Intent, Action, Result, VB, VM>() {

    override var loadingRaw: Int? = R.raw.loading

    fun setUpMenuToolbar(
        allowGoBack: Boolean,
        drawerLayout: DrawerLayout,
        @ColorRes scrimColorId: Int,
        @ColorRes titleColorId: Int,
        @StringRes titleTextId: Int?,
        toolbar: Toolbar,
        @ColorRes toolbarBackgroundColorId: Int,
        toolbarTitle: AppCompatTextView?,
    ) {

        val iconNavigationId =
            if (allowGoBack) com.mzs.core.R.drawable.core_ic_arrow_back else com.mzs.core.R.drawable.core_ic_menu

        val scrimColor = ContextCompat.getColor(this@TheCocktailAppBaseActivity, scrimColorId)
        val titleColor = ContextCompat.getColor(this@TheCocktailAppBaseActivity, titleColorId)
        val toolbarBackgroundColor =
            ContextCompat.getColor(this@TheCocktailAppBaseActivity, toolbarBackgroundColorId)

        val toggle = ActionBarDrawerToggle(
            this@TheCocktailAppBaseActivity,
            drawerLayout,
            toolbar,
            R.string.open_navigation_drawer,
            R.string.close_navigation_drawer
        )

        titleTextId?.let { titleId ->
            toolbarTitle?.apply {
                text = ContextCompat.getString(this@TheCocktailAppBaseActivity, titleId)
                setTextColor(titleColor)
                setBackgroundColor(toolbarBackgroundColor)
            }
        }

        with(toolbar) {
            title = emptyText
            setBackgroundColor(toolbarBackgroundColor)
            setNavigationIcon(iconNavigationId)
            when (iconNavigationId) {
                com.mzs.core.R.drawable.core_ic_menu -> {
                    drawerLayout.apply {
                        setScrimColor(scrimColor)
                        drawerElevation = 50f
                    }
                    toggle.syncState()
                }

                com.mzs.core.R.drawable.core_ic_arrow_back -> {
                    setNavigationOnClickListener {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        }

    }

    fun showAlertDialog(
        @ColorRes backgroundColorId: Int,
        @StringRes messageId: Int,
        @ColorRes messageTextColorId: Int,
        @StringRes titleId: Int,
        @ColorRes titleTextColorId: Int,
        @StringRes positiveButtonId: Int,
        @DrawableRes positiveButtonTextBackgroundId: Int,
        @ColorRes positiveButtonTextColorId: Int,
        onPositiveButtonClicked: () -> Unit,
    ) {

        val dialog = MaterialAlertDialogBuilder(this@TheCocktailAppBaseActivity)
            .setTitle(ContextCompat.getString(this@TheCocktailAppBaseActivity, titleId))
            .setMessage(ContextCompat.getString(this@TheCocktailAppBaseActivity, messageId))
            .setPositiveButton(
                ContextCompat.getString(this@TheCocktailAppBaseActivity, positiveButtonId)
            ) { dialog, _ ->
                onPositiveButtonClicked()
                dialog.dismiss()
            }.create()

        val dialogView = dialog.window?.decorView

        dialogView?.setBackgroundColor(
            ContextCompat.getColor(
                this@TheCocktailAppBaseActivity,
                backgroundColorId
            )
        )

        val titleTextView =
            dialogView?.findViewById<TextView>(com.google.android.material.R.id.alertTitle)
        titleTextView?.setTextColor(
            ContextCompat.getColor(
                this@TheCocktailAppBaseActivity,
                titleTextColorId
            )
        )

        val messageTextView =
            dialogView?.findViewById<TextView>(com.google.android.material.R.id.confirm_button)
        messageTextView?.setTextColor(
            ContextCompat.getColor(
                this@TheCocktailAppBaseActivity,
                messageTextColorId
            )
        )

        dialog.setOnShowListener { dialogInterface ->
            val positiveButton =
                (dialogInterface as AlertDialog).getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(
                ContextCompat.getColor(
                    this@TheCocktailAppBaseActivity,
                    positiveButtonTextColorId
                )
            )
            positiveButton.setBackgroundResource(positiveButtonTextBackgroundId)
        }

        dialog.show()

    }

}