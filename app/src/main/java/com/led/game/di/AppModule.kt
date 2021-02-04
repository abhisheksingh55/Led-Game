package com.led.game.di

import android.app.Application
import android.content.Context
import com.led.game.base.SchedulersProvider
import com.led.game.rx.SchedulersFacade
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider
}