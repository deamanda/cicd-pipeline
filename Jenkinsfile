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
                    dockerImage = docker.build("${DOCKER_USERNAME}/node${env.BRANCH_NAME}:v1.0")
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def name = "app_${env.BRANCH_NAME}".toLowerCase()
                    def port = 3000
                    if (${env.BRANCH_NAME}=='dev') {
                        port=3001
                    }
                    sh "docker rm -f app || true"
                    sh "docker run -d --name app --expose ${port} -p ${port}:3000 ${image}"
                }
            }
        }       
    }
}
