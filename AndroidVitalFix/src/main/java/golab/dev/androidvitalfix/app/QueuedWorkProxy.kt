package golab.dev.androidvitalfix.app

import golab.dev.androidvitalfix.utils.Reflection
import java.util.concurrent.ConcurrentLinkedQueue

object QueuedWorkProxy {
    private var pendingWorks: ConcurrentLinkedQueue<Runnable>? = null

    fun consumePendingWorks() {
        if (null == pendingWorks) {
            pendingWorks = Reflection.getClassMember("android.app.QueuedWork",
                "sPendingWorkFinishers", null) as? ConcurrentLinkedQueue<Runnable>
        }

        pendingWorks?.apply {
            clear()
        }
    }
}