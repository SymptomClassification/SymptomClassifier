pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://dagere.comiles.eu/git/SymptomClassification/SymptomChapterService'
            }
        }
        stage('Maven Package') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
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
                    if (containers.contains('chapterapi-api')) {
                        sh "docker stop chapterapi-api"
                        sh "docker rm chapterapi-api"
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