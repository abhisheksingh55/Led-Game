package com.led.game.main

import androidx.lifecycle.Observer
import com.led.domain.model.ButtonOption
import com.led.game.R
import com.led.game.base.BaseActivity
import com.led.game.base.BaseAdapterItemClick
import com.led.game.extensions.getViewModel
import com.led.game.main.adapter.ButtonOptionsAdapter
import com.led.game.main.adapter.LedLightAdapter
import com.led.game.main.adapter.NotationAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(), BaseAdapterItemClick<ButtonOption> {

    private var ledLightAdapter: LedLightAdapter? = null
    private var buttonOptionAdapter: ButtonOptionsAdapter? = null
    private var notationAdapter: NotationAdapter? = null

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelInstance(): MainViewModel {
        return viewModelFactory.getViewModel(this)
    }

    override fun setUpView() {
        super.setUpView()
        ledLightAdapter = LedLightAdapter(this)
        ledRv.setHasFixedSize(true)
        ledRv.adapter = ledLightAdapter

        buttonOptionAdapter = ButtonOptionsAdapter(this)
        buttonRv.setHasFixedSize(true)
        buttonRv.adapter = buttonOptionAdapter

        notationAdapter = NotationAdapter(this)
        notationRv.setHasFixedSize(true)
        notationRv.adapter = notationAdapter
    }

    override fun onItemClick(viewType: Int, data: ButtonOption) {
        super.onItemClick(viewType, data)
        viewModel.buttonPressed(data)
    }


    override fun observeViewModelEvents() {
        super.observeViewModelEvents()
        viewModel.observeLedLights().observe(this, Observer {
            ledLightAdapter?.updateDiffList(it)
        })

        viewModel.observeButtonOptions().observe(this, Observer {
            buttonOptionAdapter?.addItems(it)
        })

        viewModel.observeColorNotation().observe(this, Observer {
            notationAdapter?.addItems(it)
        })

        viewModel.generatedString().observe(this, Observer {
            generatedStringTv.text = it
        })
    }
}