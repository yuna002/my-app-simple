pipeline {
    agent any

    environment {
        IMAGE_NAME = "my-springboot-app"      // 你的 image 名稱，可自訂
        IMAGE_TAG  = "${env.BUILD_NUMBER}"    // 使用 Jenkins build 編號作為 tag
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
					echo "Building image: ${IMAGE_NAME}:${IMAGE_TAG}"
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Load Image to Minikube') {
            steps {
                script {
                    sh "/usr/local/bin/minikube image load ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                // 替換 deployment.yaml 中的 image tag（用簡單方式）
                sh "sed -i 's|image: .*|image: ${IMAGE_NAME}:${IMAGE_TAG}|' k8s/deployment.yaml"

                // 刪除舊資源並套用新的
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

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
