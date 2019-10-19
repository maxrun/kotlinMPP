package com.jarroyo.sharedcode

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext = Dispatchers.IO
internal actual val uiDispatcher: CoroutineContext = Dispatchers.Main