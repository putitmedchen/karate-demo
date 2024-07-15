pipeline{
    agent any

    stages {
        stage('Build API') {
            steps {
                // Build the Spring Boot API project using Maven
                dir("${WORKSPACE}/events-api") {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Start API') {
            steps {
                // Start the API in the background
                dir("${WORKSPACE}/events-api/target") {
                    bat 'start java -jar events-api-0.0.1-SNAPSHOT.jar'
                }
                //Wait for API to start (adjust the sleep duration as needed)
                sleep 30
            }
        }

        stage('Run Tests') {
            steps {
                // Run Karate tests against the API
                dir("${WORKSPACE}/events-api-tests") {
                    bat 'mvn test'
                }
            }
        }
    }

    post {
        always {
            // Stop the API process
            bat 'taskkill /f /im java.exe /fi "WINDOWTITLE eq events-api-0.0.1-SNAPSHOT.jar"'
        }
            success {
                dir("${WORKSPACE}/events-api-tests") {
                    junit 'target/karate-reports/*.xml'
                    cucumber 'target/karate-reports/*.json'
                }
            }
    }
}
