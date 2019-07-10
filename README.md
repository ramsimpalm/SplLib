# SplLib
Simpalm Logging Library an enhance of native Android logging mechanism to enhance functionality. User can additionally track user navigation path, permissions granted along wit network status.

Initialize default logging configuration in your application class:

  SplConfig.setSplConfig(new SplConfig.Builder(this).build());
  
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
Spl.v(this, "This is a test verbose log")
```
