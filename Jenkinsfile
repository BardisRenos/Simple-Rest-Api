pipeline {
    agent any

    stages {
        stage('Build'){
            steps {
                sh './mvnw clean install'
            }
        }
        stage('Tests'){
            steps {
                sh './mvnw test'
            }
        }
    }
}