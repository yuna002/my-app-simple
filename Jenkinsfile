pipeline {
  agent any  // 這表示 Jenkins 將在任意可用代理節點上運行該 Pipeline

  stages {
    // Checkout 階段，將從 Git 倉庫拉取程式碼
    stage('Checkout') {
      steps { 
        git 'https://github.com/yuna002/my-app-simple.git'  // 從 Git 倉庫拉取程式碼
      }
    }

    // Build 階段，清理並打包你的應用
    stage('Build') {
      steps { 
        sh 'mvn clean package'  // 使用 Maven 打包應用
      }
    }

    // Test 階段，執行測試
    stage('Test') {
      steps { 
        sh 'mvn test'  // 使用 Maven 執行測試
      }
    }

    // Docker Build & Push 階段，建構並推送 Docker 映像檔
    stage('Docker Build & Push') {
      steps {
        sh 'docker build -t springboot-app:latest .'  // 根據 Dockerfile 建立映像檔
       
      }
    }

    // Deploy 階段，將應用部署到 Kubernetes
    stage('Deploy') {
      steps {
        sh 'kubectl apply -f k8s/deployment.yaml'  // 使用 kubectl 應用 Kubernetes 配置
	sh 'kubectl apply -f k8s/service.yaml'
      }
    }
  }
}
