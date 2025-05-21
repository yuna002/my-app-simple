pipeline {
  agent any

  environment {
    TELEGRAM_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
    TELEGRAM_CHAT = credentials('TELEGRAM_CHAT')
  }

  stages {
    stage('Build & Deploy') {
      steps {
        script {
          // 你的 Minikube 部署步驟
          sh '''
            eval $(minikube docker-env)
            docker build -t springboot-app:latest .
            kubectl apply -f k8s/deployment.yaml
          '''
        }
      }
    }

    stage('Notify Success') {
      steps {
        script {
          def message = URLEncoder.encode("✅ Jenkins 部署成功", "UTF-8")
          sh """
            curl -s -X POST https://api.telegram.org/bot${TELEGRAM_TOKEN}/sendMessage \\
              -d chat_id=${TELEGRAM_CHAT_ID} \\
              -d text="${message}"
          """
        }
      }
    }
  }

  post {
    failure {
      script {
        def message = URLEncoder.encode("❌ Jenkins 部署失敗", "UTF-8")
        sh """
          curl -s -X POST https://api.telegram.org/bot${TELEGRAM_TOKEN}/sendMessage \\
            -d chat_id=${TELEGRAM_CHAT_ID} \\
            -d text="${message}"
        """
      }
    }
  }
}
