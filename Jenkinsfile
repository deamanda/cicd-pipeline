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
        stage('Build') {
            steps {
                sh 'docker build -t node${env.BRANCH_NAME}:v1.0. .'
            }
        }
    }
}