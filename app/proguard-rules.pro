# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#不混淆第三方Jar包


-dontskipnonpubliclibraryclassmembers
-dontshrink
-optimizations !code/simplification/arithmetic
-allowaccessmodification
-printmapping bin\classes-processed.map
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature
-renamesourcefileattribute SourceFile
-dontpreverify
-dontnote com.android.vending.licensing.ILicensingService
-dontwarn android.support.**
-ignorewarnings

-keep class android.support.**{*;}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

-keep public class * extends android.widget.RelativeLayout
-keep class com.qq.e.** { *; }
-keep class com.androidquery.** { *; }
-keep class com.flyco.tablayout.** { *; }
-keep class com.scwang.smartrefresh.** { *; }
-keep class org.jsoup.** { *; }

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**





# OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.**{*;}

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-keepattributes Signature
-keepattributes Exceptions

-keep class io.reactivex.**{*;}
-keep class rx.**{*;}

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keep class com.ant.poy.entity.**{*;}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
-keep class com.google.gson.**{*;}


-keep class com.facebook.stetho.** { *; }
-keep class com.squareup.okhttp3.** { *; }
-keep class com.squareup.retrofit2.** { *; }
-keep class com.jakewharton.rxbinding.** { *; }

-keep class com.nostra13.universalimageloader.** { *; }

-keepclassmembers class * extends java.io.Serializable {
       static final long serialVersionUID;
      private static final java.io.ObjectStreamField[] serialPersistentFields;
      !static !transient <fields>;
      !private <fields>;
      !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util.AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
    public void set*(...);
}

# Preserve all classes that have special context constructors, and the
# constructors themselves.
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

# Preserve all classes that have special context constructors, and the
# constructors themselves.
-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve the required interface from the License Verification Library
# (but don't nag the developer if the library is not used at all).
-keep public interface  com.android.vending.licensing.ILicensingService

# Preserve the special static methods that are required in all enumeration
# classes.
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}




