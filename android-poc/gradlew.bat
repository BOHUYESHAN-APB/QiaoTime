@echo off
set DIRNAME=%~dp0
set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar
if not exist "%CLASSPATH%" (
  echo ERROR: gradle-wrapper.jar not found in %DIRNAME%gradle\wrapper\.
  echo Please generate the wrapper jar by running "gradle wrapper --gradle-version 8.4" or import the project in Android Studio which can generate it for you.
  exit /b 1
)
"%JAVA_HOME%\bin\java" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
