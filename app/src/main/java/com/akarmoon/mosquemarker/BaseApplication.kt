package com.akarmoon.mosquemarker

import android.app.Application
import com.akarmoon.mosquemarker.data.MarkerDatabase

class BaseApplication : Application() {
    val database: MarkerDatabase by lazy { MarkerDatabase.getDatabase(this) }
}