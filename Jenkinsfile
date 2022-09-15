pipeline {
    agent any

    stages {
        stage('Hello'){
            steps {
                echo 'Hello World'
            }
        }
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