pipeline {
  agent any

  environment{
     IMAGE_NAME= "z9shay/freight-app"
     IMAGE_TAG= "v1.0.${BUILD_NUMBER}"
  }

  stages {

      stage('Checkout') {
         steps {
            git 'https://github.com/akshay-rahangdale/freight-tracking-application.git'
         }
      }

      stage('Build'){
         steps{
            sh '''
            docker run --rm \
              -v $(pwd):/app \
              -w /app \
              maven:3.9.9-eclipse-temurin-21 \
              mvn clean package -DskipTests
            '''
         }
      }

      stage('Build Docker Image'){
         steps{
            sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
         }
      }

      stage('Docker Login'){
         steps{
            withCredentials([usernamePassword (
              credentialsId:'dockerhub-creds',
              usernameVariable: 'DOCKER_USER',
              passwordVariable: 'DOCKER_PASS'
            )]){
               sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
            }
         }
      }

      stage('Push Image'){
         steps{
            sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
            sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest"
            sh "docker push ${IMAGE_NAME}:latest"
         }
      }

      stage('Run Container (Test)') {
          steps {
             sh 'docker rm -f freight-test || true'
             sh "docker run -d --name freight-test -p 8082:8080 ${IMAGE_NAME}:latest"
          }
      }

       stage('Deploy') {
           steps {
               sh '''
               cd /var/lib/jenkins/freight-app

               docker-compose pull
               docker-compose up -d
               '''
           }
       }

  }
}