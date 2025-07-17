pipeline {
    agent any
    //agent none

    environment {
        IMAGE_NAME = "my-springboot-app"
        IMAGE_TAG  = "${env.BUILD_NUMBER}"
        KUBECONFIG = '/var/jenkins_home/.kube/config'
    }

    stages {

        stage('Prepare kubeconfig') {
            steps {
                script {
                    sh '''
                        echo "Preparing kubeconfig..."
                        mkdir -p /var/jenkins_home/.kube

                         if [ -f /var/jenkins_home/.kube/config ]; then
                             sed -i 's|/home/ub|/var/jenkins_home|g' /var/jenkins_home/.kube/config
                         else
                             echo "WARNING: kube config not found!"
                             exit 1
                         fi

                    '''
                }
            }
        }


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
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                        docker images | grep ${IMAGE_NAME}
                    '''
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                script {

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
