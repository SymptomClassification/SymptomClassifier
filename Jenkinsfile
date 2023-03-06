pipeline {
    agent any

    tools {
        jdk 'JDK17'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://dagere.comiles.eu/git/SymptomClassification/SymptomClassifier.git'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Maven Package') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Check and Stop Container') {
            steps {
                script {
                    def containers = sh(returnStdout: true, script: 'docker ps --format "{{.Names}}"').split("\n")
                    if (containers.contains('symptomclassifier-api')) {
                        sh "docker stop symptomclassifier-api"
                        sh "docker rm symptomclassifier-api"
                    }
                }
            }
        }
        stage('Start Container') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}