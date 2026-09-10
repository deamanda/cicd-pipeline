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
                    if (env.BRANCH_NAME=='dev') {
                        sh 'cp /var/jenkins_home/logos/dev.svg src/logo.svg'
                    }
                    else {
                        sh 'cp /var/jenkins_home/logos/main.svg src/logo.svg'
                    }
                    dockerImage = docker.build("node${env.BRANCH_NAME}:v1.0")
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def port = 3000
                    if (env.BRANCH_NAME=='dev') {
                        port=3001
                    }
                    sh "docker rm -f app || true"
                    sh "docker run -d --name app --expose ${port} -p ${port}:3000 node${env.BRANCH_NAME}:v1.0"
                }
            }
        }       
    }
}
