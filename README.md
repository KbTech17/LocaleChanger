# LocaleChanger

[![MIT Java](https://www.vectorlogo.zone/logos/java/java-ar21.svg)]()
[![GPLv3 Kotlin](https://www.vectorlogo.zone/logos/kotlinlang/kotlinlang-ar21.svg)]()
[![AGPL Android](https://www.vectorlogo.zone/logos/android/android-ar21.svg)]()


LocaleChanger is an extension Android library that changes **Locale** in an efficient and easy way. You can use it for changing **Locale** without recreating Activity.

**Setup Guide**
=
> [!IMPORTANT]
Add the following dependency to your build.gradle.

```groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

dependencies {
    implementation 'com.github.KbTech17:LocaleChanger:3.2.7'
}
```
or
```groovy
repositories {
  google()
  mavenCentral()
}

dependencies {
    implementation 'com.github.KbTech17:LocaleChanger:3.2.7'
}
```

Then you are ready to go.

**Features**
=
1. Changes language on-the-fly
2. Persists the changes in `Preferences` automatically
3. Detects changes when activity loads from backstack
4. Detects Right-To-Left (RTL) languages and updates layout direction automatically
6. Lightweight and easy to use


**Usage Guide**
=

> [!IMPORTANT]
**Extend your Application class** by **LocaleApplication** class as follows
```java
public class MyApplication extends LocaleApplication {

	public MyApplication() {}
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected void attachBaseContext(@NonNull Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
```

> [!IMPORTANT]
**Extend your All classes which you need to change Language in future** e.g. **MainActivity, SecondActivity** by **LocaleCompatActivity** as follows

```java
public class MainActivity extends LocaleCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.toENButton).setOnClickListener(view -> updateLocale(new Locale("en"))); //You can do by this way or
        findViewById(R.id.toItButton).setOnClickListener(view -> updateLocale(new Locale("it"))); //You can do by this way or
        findViewById(R.id.toArButton).setOnClickListener(view -> updateLocale(Locales.INSTANCE.getArabic())); //by this way


        findViewById(R.id.secondButton).setOnClickListener(view -> startActivity(new Intent(this, SecondActivity.class)));
    }

}
```

> [!IMPORTANT]
SecondActivity.java
```java
public class SecondActivity extends LocaleCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        findViewById(R.id.toENButton).setOnClickListener(view -> updateLocale(new Locale("en"))); //You can do by this way or
        findViewById(R.id.toItButton).setOnClickListener(view -> updateLocale(new Locale("it"))); //You can do by this way or
        findViewById(R.id.toArButton).setOnClickListener(view -> updateLocale(Locales.INSTANCE.getArabic())); //by this way

        findViewById(R.id.backButton).setOnClickListener(view -> finish());
    }
}
```
> [!IMPORTANT]
**Remember your Application class added to** `AndroidManifest.xml`
```xml
--------------------------------------
<application
        android:name=".MyApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity
            android:name=".MainActivity"
            android:exported="true" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
          ----------
    </application>
--------------------------------------
```

> [!IMPORTANT]
**Remember to add Right-To-Left(RTL) if your app is in Arabic or RTL format in** `AndroidManifest.xml`
```xml
<application
	android:supportsRtl="true">
</application>
``` 
> [!TIP]
> <h1 color="red">Quite easy yeah ? That's it</h1>

</br>

> [!NOTE]
><h1>There is no need any more codings, All the jobs handle by LocaleChanger itself</h1>


> [!WARNING]
**Google introduced a new App Bundle** format to split apk files in smaller sizes when they’re being installed on the user`s devices. However, this means that we cannot have dynamic language changes in our applications.
To prevent that split for language files we need to add extra lines in our build.gradle file inside the app folder as follows.

```groovy
android {
    //...
    bundle {
        language {
            enableSplit = false
        }
    }
}
```

## Do your code fly ? Buy a cup of coffee for me 😋

[<img src="https://github.com/KbTech17/DPermission/blob/master/img/pp_donate.png?raw=true" width="32%" />](https://www.paypal.com/ncp/payment/ARPPRFA6HKS2S) [<img src="https://github.com/KbTech17/DPermission/blob/master/img/buy_cof.png?raw=true" width="32%" />](https://www.paypal.com/ncp/payment/ARPPRFA6HKS2S)


## License

```
Copyright (C) DPermission Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
