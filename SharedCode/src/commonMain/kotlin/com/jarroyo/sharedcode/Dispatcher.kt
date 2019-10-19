package com.jarroyo.sharedcode

import kotlin.coroutines.CoroutineContext

internal expect val ApplicationDispatcher: CoroutineContext

internal expect val uiDispatcher: CoroutineContext