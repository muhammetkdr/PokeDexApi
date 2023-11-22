package com.muhammetkdr.pokemondex.utils.indicator


import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.muhammetkdr.pokemondex.R
import javax.inject.Inject

class DefaultIndicatorPresenter @Inject constructor(
    private val context: Context
) : IndicatorPresenter {

    private var loadingDialog: AlertDialog? = null

    init {
        initLoadingDialog()
    }

    private fun initLoadingDialog() {
        val builder = MaterialAlertDialogBuilder(context, R.style.StyleLoadingDialog)
        builder.setCancelable(false)
        builder.setView(R.layout.dialog_view_progress)
        loadingDialog = builder.create()
        loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        (context as AppCompatActivity).lifecycle.addObserver(
            DialogDismissLifecycleObserver(loadingDialog)
        )
    }

    private fun isActivityRunning() =
        (context as AppCompatActivity).lifecycle.currentState != Lifecycle.State.DESTROYED

    override fun show() {
        if (loadingDialog == null || isActivityRunning().not()) return

        loadingDialog?.let {
            if (it.isShowing.not()) {
                it.show()
            }
        }
    }

    override fun hide() {
        if (loadingDialog == null || isActivityRunning().not()) return
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }
}

class DialogDismissLifecycleObserver(
    private var dialog: AppCompatDialog?
) : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            dialog?.dismiss()
            dialog = null
        }
    }
}