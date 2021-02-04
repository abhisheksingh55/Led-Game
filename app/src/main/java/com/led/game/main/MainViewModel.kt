package com.led.game.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.led.domain.model.*
import com.led.game.base.BaseViewModel
import com.led.game.base.SchedulersProvider
import com.led.game.extensions.delayRun
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val schedulersProvider: SchedulersProvider) :
    BaseViewModel() {

    private var lastInput: StringBuffer = StringBuffer()

    private lateinit var sysGenerateSeq: String

    private val listOfButton = ArrayList<ButtonOption>(3)
    private var ledLightCount = 3
    private val colorLedLd = MutableLiveData<List<LedModel>>()
    private val buttonOptionLd = MutableLiveData<List<ButtonOption>>()
    private val colorNotationLd =
        MutableLiveData<List<LedLight>>().apply { postValue(LedLight.values().toList()) }
    private val generatedStringLd = MutableLiveData<String>()
    private val inputSeqSubject = PublishSubject.create<ButtonOption>()

    init {
        setUpInitialView()
        observeInputPress()
    }

    private fun setUpInitialView() {
        //initialize led list
        initializeLed()
        // initialize button list
        initializeButton()
        // generate system sequence
        generateSeq()
        // fixed last observe button press
        //lastInput.setLength(list.size)
    }

    fun observeLedLights(): LiveData<List<LedModel>> {
        return colorLedLd
    }

    fun observeButtonOptions(): LiveData<List<ButtonOption>> {
        return buttonOptionLd
    }

    fun observeColorNotation(): LiveData<List<LedLight>> {
        return colorNotationLd
    }

    fun generatedString(): LiveData<String> {
        return generatedStringLd
    }

    fun buttonPressed(newInput: ButtonOption) {
        inputSeqSubject.onNext(newInput)
    }

    private fun observeInputPress() {
        inputSeqSubject.map {
            if (lastInput.length == ledLightCount) {
                lastInput.deleteCharAt(0)
            }
            lastInput.append(it.name)
            lastInput.toString()
        }
            .subscribeOn(schedulersProvider.computation())
            .subscribe(this::handleInputSeq) {
                Timber.d(it)
            }.addTo(compositeDisposable)
    }

    private fun handleInputSeq(inputSeq: String) {
        val colorAfterCompare = compareString(sysGenerateSeq, inputSeq)
        colorLedLd.postValue(colorAfterCompare)
        // is guessed right sequence
        if (checkIsGuessSuccess(colorAfterCompare)) {
            postToastMsg("Congratulation, Your guess is right!!")
            1500L.delayRun {
                setUpInitialView()
            }.addTo(compositeDisposable)
        }
    }

    private fun compareString(sysSeq: String, inputSeq: String): List<LedModel> {
        Timber.d("sys $sysSeq,  guessed $inputSeq")
        val lengthDiff = sysSeq.length.minus(inputSeq.length)

        val newLedColor = getLedList()
        inputSeq.forEachIndexed { index, char ->
            val color = when {
                sysSeq[index] == char -> {
                    LedLight.GREEN
                }
                sysSeq.contains(char) -> {
                    LedLight.ORANGE
                }
                else -> {
                    LedLight.RED
                }
            }
            Timber.d("guess= $inputSeq, char=$char, color = ${color.name}")
            newLedColor[index + lengthDiff].light = color
        }
        return newLedColor
    }

    // check all light are green
    private fun checkIsGuessSuccess(colors: List<LedModel>): Boolean {
        return colors.all { it.light == LedLight.GREEN }
    }

    private fun getLedList(): List<LedModel> {
        val list = ArrayList<LedModel>()
        for (i in 0 until ledLightCount) {
            list.add(LedModel(light = LedLight.OFF, name = "LED${i + 1}"))
        }
        return list
    }

    private fun initializeButton() {
        if (listOfButton.isNullOrEmpty()) {
            ButtonOption.values().forEach {
                listOfButton.add(it)
            }
            buttonOptionLd.postValue(listOfButton)
        }
    }

    private fun initializeLed() {
        val list = getLedList()
        colorLedLd.postValue(list)
    }

    private fun generateSeq() {
        sysGenerateSeq = listOfButton.fold("") { R, _ -> R.plus(listOfButton.random().name) }
        if (lastInput.isNotEmpty()) {
            lastInput.delete(0, lastInput.length)
        }
        generatedStringLd.postValue(sysGenerateSeq)
    }
}