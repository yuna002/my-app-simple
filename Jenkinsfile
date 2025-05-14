pipeline {
  agent any
  stages {
    stage('Git Checkout') {
      steps {
        git 'https://github.com/yuna002/my-app-simple.git'
        sh 'ls -la'
      }
    }
  }
}
