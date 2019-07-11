# SplLib
Simpalm Logging Library: an enhancement of native Android logging mechanism to improve the functionality. Coders can additionally track user navigation path, permissions granted along with internet connectivity status.

Add the library in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:

```
dependencies {
	        implementation 'com.github.ramsimpalm:SplLib:1.0.3'
	}
```

Initialize default logging configuration in your application class:

```
SplConfig.setSplConfig(new SplConfig.Builder(this).build());
```
  
Here is sample builder for all available options:

```
SplConfig.setSplConfig(new SplConfig.Builder(this)
                .setEnableLogOutput(true)
                .setEnableVerboseLogs(true)
                .setEnableDebugLogs(true)
                .setEnableInfoLogs(true)
                .setEnableWarningLogs(true)
                .setEnableErrorLogs(true).build());
```
                
Delete all cached log files with:

```
SplConfig.get().clearLogs();
```

In order to email the logs call method like this:

```
private void emailLogs(String toEmail) {
        String body = SplConfig.get().getAllLogs();
        Intent sharingIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",toEmail, null));
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.Share_Using)));
    }
```
    
For logging use methods like:

```
Spl.v(this, e)
Spl.v(this, "This is a test verbose log")
Spl.v(this, "This is a test verbose log", e)
```

All available methods are:
v, d, i, w, e, logBitmapInfo, logUserInfo, and logMethod

Print full user navigation path by adding following lines in BaseActivity onCreate method:

```
Spl.d(this, "onCreate")
```
