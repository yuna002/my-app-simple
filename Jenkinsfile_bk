pipeline {
    agent any
    //agent none

    environment {
        IMAGE_NAME = "my-springboot-app"
        IMAGE_TAG  = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image in Minikube') {
            steps {
                script {
                    sh '''
                        export PATH=$PATH:/usr/local/bin
                        eval $(minikube -p minikube docker-env)
                        echo "Building image: ${IMAGE_NAME}:${IMAGE_TAG}"
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                        docker images | grep ${IMAGE_NAME}
                    '''
                }
            }
        }

        stage('Deploy to Minikube') {
            agent { label 'minikube' }
            steps {
                script {
                    // 替換 image tag
                    sh "sed -i 's|image: .*|image: ${IMAGE_NAME}:${IMAGE_TAG}|' k8s/deployment.yaml"

                    // 部署到 Minikube
                    sh '''
                        kubectl delete -f k8s/deployment.yaml --ignore-not-found
                        kubectl delete -f k8s/service.yaml --ignore-not-found
                        kubectl apply -f k8s/deployment.yaml
                        kubectl apply -f k8s/service.yaml
                        kubectl get svc
                    '''
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
