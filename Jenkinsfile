pipeline {
    agent any
    tools {
        nodejs 'node'
    }
    stages {
        stage('Build') { 
            steps {
                sh 'npm install' 
            }
        }
        stage('Test') {
            steps {
                sh 'npm test'
            }
        }
        stage('Docker build') {
            steps {
                script {
                    dockerImage = docker.build("node${env.BRANCH_NAME}:v1.0")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                    dockerImage.push()
                }
            }
        }
        stage('Deploy_to_main') {
            when {branch 'main'}
            steps {
                script {
                    def name = "app_${env.BRANCH_NAME}".toLowerCase()
                    def image = "node${env.BRANCH_NAME}:v1.0"
                    sh "docker pull ${image}"
                    sh "docker rm -f ${name} || true"
                    sh "docker run -d --name ${name} --expose 3000 -p 3000:3000 ${image}"
                }
            }
        }
        stage('Deploy_to_dev') {
            when {branch 'dev'}
            steps {
                script {
                    def name = "app_${env.BRANCH_NAME}".toLowerCase()
                    def image = "node${env.BRANCH_NAME}:v1.0"
                    sh "docker pull ${image}"
                    sh "docker rm -f ${name} || true"
                    sh "docker run -d --name ${name} --expose 3001 -p 3001:3000 ${image}"
                }
            }
        }        
    }
}