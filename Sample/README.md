# How to Use this Sample

### 1.How to execute RxKotlin Sample
unComment this code in __AndroidManifest.xml__

```xml
<activity
    android:name=".RxKotlin.HomeActivity"
    android:exported="true" >
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

### 2.How to execute RxJava Sample
unComment this code in __AndroidManifest.xml__

```xml
<activity
    android:name=".RxJava.HomeActivity"
    android:exported="true" >
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
<activity android:name=".RxJava.RxActivity"/>
<activity android:name=".RxJava.BasicActivity"/>
```
