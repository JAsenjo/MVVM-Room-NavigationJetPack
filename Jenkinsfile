pipeline {
       agent {
          label 'mca-paytvapp-android'
       }
     stages {
         stage('Test') {
           steps {
             echo '*** Hello... ****'
           }
         }
         stage('build') {
         environment {
             ANDROID_SDK_ROOT = "/opt/android-sdk-linux"
         }
         steps {
         echo '*** AssembleDebug... ****'
         sh './gradlew assembleDebug'
         }
         }
     }
}