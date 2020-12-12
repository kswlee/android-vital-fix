package golab.dev.androidvitalfix

import android.os.Build
import android.os.Handler
import android.os.Message
import golab.dev.androidvitalfix.utils.Reflection

/**
 * Class ActivityThreadHooker
 *
 * Use to hook ActivityThread handler callback to override system behavior
 */
class ActivityThreadHooker {
    companion object {
        private const val sScheduleCrash = 134
        private const val sActivityThreadName = "android.app.ActivityThread"
    }

    private val msgHandlers: MutableMap<Int, (() -> Boolean)?> = mutableMapOf()

    private val callbackHandler = object: Handler.Callback {
        override fun handleMessage(message: Message): Boolean {
            if (msgHandlers.containsKey(message.what)) {
                return msgHandlers[message.what]?.invoke() == true
            }

            return false
        }
    }

    init {
        msgHandlers[sScheduleCrash] = {
            // Fix RemoteServiceException
            true
        }
    }

    fun hook() {
        val requireHook = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        if (!requireHook) {
            return
        }

        try {
            hookActivityThread()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun hookActivityThread() {
        val activityThread = Reflection.getClassMember(sActivityThreadName,
            "sCurrentActivityThread")
        val internalHandler = Reflection.getClassMember(sActivityThreadName,
            "mH", activityThread)
        Reflection.setClassMember(Handler::class.java as Class<Any>, "mCallback",
            internalHandler, callbackHandler)
    }
}