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
                dockerImage = docker.build("node${env.BRANCH_NAME}:v1.0")
            }
        }
    }
}