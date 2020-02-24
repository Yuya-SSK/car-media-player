package com.ssk.car.media.player.log

import android.util.Log

object YLog {
    private const val tag = "CarMediaPlayer"
    private const val LOG_PREFIX_METHOD_IN = "[in ]"
    private const val LOG_PREFIX_METHOD_OUT = "[out]"
    private val LOG_ENABLED = BuildConfig.DEBUG
    private val LOG_LEVEL = LogLevel.Verbose
    private const val PRINT_THREAD_NAME = true
    fun d(message: String?) = printLog(LogLevel.Debug, message)
    fun i(message: String?) = printLog(LogLevel.Info, message)
    fun w(message: String?) = printLog(LogLevel.Warn, message)
    fun e(message: String?) = printLog(LogLevel.Error, message)
    fun methodIn(message: String? = "") = printLog(LogLevel.Verbose, message, LOG_PREFIX_METHOD_IN)
    fun methodOut(message: String? = "") = printLog(LogLevel.Verbose, message, LOG_PREFIX_METHOD_OUT)
    private fun printLog(logLevel: LogLevel, message: String?, prefix: String = "") {
        if (!LOG_ENABLED) return
        if (LOG_LEVEL.ordinal > logLevel.ordinal) return
        val threadName = if (PRINT_THREAD_NAME) "[${Thread.currentThread().name}]" else ""
        Log.println(logLevel.value, tag, "$threadName $prefix $message")
    }

    private enum class LogLevel(val value: Int) {
        Verbose(Log.VERBOSE), Debug(Log.DEBUG), Info(Log.INFO), Warn(Log.WARN), Error(Log.ERROR);
    }
}