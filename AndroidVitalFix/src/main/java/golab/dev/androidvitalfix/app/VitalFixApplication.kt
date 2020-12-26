package golab.dev.androidvitalfix.app

import android.app.Application
import android.content.Context
import golab.dev.androidvitalfix.VitalFixer


/**
 * Class VitalFixApplication
 *
 * Base application class to fix vital issues automatically
 */
open class VitalFixApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        VitalFixer.Builder()
            .remoteServiceException()
            .sharePrefANRByService()
            .sharePrefANRByActivity()
            .fix()
    }
}