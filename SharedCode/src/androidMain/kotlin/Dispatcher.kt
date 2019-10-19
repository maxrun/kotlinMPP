package com.jarroyo.sharedcode

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext = Dispatchers.Default

internal actual val uiDispatcher : CoroutineContext = Dispatchers.Main