[![](https://jitpack.io/v/kswlee/android-vital-fix.svg)](https://jitpack.io/#kswlee/android-vital-fix)

# Android Vital Fix

Google takes the Android app stability into acccount when rating apps, but sometimes some of the critical crash / ANR issue are not easy to identified or fixed. This lib is going to provide some workaround ways to avoid the crash or ANR issues. 

### Issues Listed 

1. Unexpected RemoteServiceException 
   - Might caused by "Context.startForegroundService() did not then call Service.startForeground()", but actually you've called startForeground in serive::onCreate 
2. ANR caused by SharedPreferences (to be fixed in next release) 

### Howto 
```
// In project build.gradle 
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
  
// In app build.gradle 
dependencies {
    implementation 'com.github.kswlee:android-vital-fix:Tag'
}
  
VitalFixer.Builder()
   .remoteServiceException() // auto fix remote service exception 
   .sharePrefANRByService() // auto fix sharePreferences ANR for service operations 
   .sharePrefANRByActivity() // auto fix sharePreferences 
   .fix()

// Or extend VitalFixApplication to fix vital issues directly 
class GoApplication : VitalFixApplication() {
}
```
