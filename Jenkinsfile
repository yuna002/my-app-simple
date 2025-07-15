pipeline {
    agent any

    triggers {
    cron('H/20 * * * *')
    }

    environment {
        PATH = "/usr/share/maven/bin:${env.PATH}"
    }

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

    post {
    always {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}

}
