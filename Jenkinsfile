pipeline {
  agent any
  stages {

    stage('Check Path') {
    steps {
        sh 'echo $PATH'
        sh 'which mvn'
        sh 'mvn -v'
    }
}

    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
  }
}
