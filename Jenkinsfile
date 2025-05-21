pipeline {
    agent any

    stages {
	
		stage('Build') {
            steps {
                sh 'ls -la'
                sh 'mvn clean package'  
            }
        }
		
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
