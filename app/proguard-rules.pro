# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.ifreedomer.cplus.entity.**{*;}
-keep class com.ifreedomer.cplus.http.**{*;}


-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}


-keep class com.bumptech.glide.**{*;}
-keep class com.google.gson.**{*;}
-keep class butterknife.**{*;}
-keep class com.umeng.analytics.**{*;}
-keep class com.zhy.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-keep class retrofit2.**{*;}
-keep class com.squareup.**{*;}
-keep class com.tbruyelle.**{*;}
-keep class org.hamcrest.**{*;}
-keep class io.reactivex.**{*;}
-keep class com.jakewharton.**{*;}
-keep class com.jakewharton.**{*;}
-keep class com.sun.mail.**{*;}

-keep class com.sun.mail.**{*;}
-keep class com.alipay.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
-keep class org.apache.harmony.**{*;}
-keep class myjava.awt.datatransfer.**{*;}
-keep class com.sun.activation.**{*;}
-keep class javax.activation.**{*;}

#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------





#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-dontwarn android.support.v4.**
-keep class android.support.v4.**  { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment




-optimizationpasses 5
#
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
#
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
#
# 包含有类名->混淆后类名的映射关系
-verbose
#
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-ignorewarnings  # 忽略警告，避免打包时某些警告出现

#
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
#
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
#
# 避免混淆泛型
-keepattributes Signature
#
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*

-dontwarn
-dontnote

-ignorewarnings  # 忽略警告，避免打包时某些警告出现
