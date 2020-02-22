pipeline {
    agent { dockerfile true }
    stages {
        stage('Test') {
          steps {
            echo '*** Hello... ****'
            sh 'echo customvar= $testVar'
          }
        }
    }
}