# SplLib
Simpalm Logging Library an enhance of native Android logging mechanism to enhance functionality. User can additionally track user navigation path, permissions granted along wit network status.

Add it in your root build.gradle at the end of repositories:

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
	        implementation 'com.github.ramsimpalm:SplLib:1.0.2'
	}
```

Initialize default logging configuration in your application class:

```
SplConfig.setSplConfig(new SplConfig.Builder(this).build());
```
  
Here is sample statement for all available configuration options:

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
