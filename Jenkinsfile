pipeline {
    agent any

    stages {
        stage('Build'){
            steps {
                sh './mvnw clean install -DskipTests'
            }
        }
        stage('Tests'){
            steps {
                sh './mvnw test'
            }
        }
    }
}