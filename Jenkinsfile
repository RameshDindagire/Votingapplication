pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("Test") {
            steps {
                bat 'mvn --version'
                echo "========Getting Maven Version========"
            }
        }
        stage("Build") {
            steps {
                bat 'mvn clean package'
                echo "========Creating Build Successfully ========"
            }
        }
        stage("Deploy on Test") {
            steps {
                script {
                    def tomcatServer = Tomcat8(adapters: [tomcat8(credentialsId: 'adminserver1', path: '', url: 'http://localhost:9002/')])
                    def deployed = tomcatServer.deploy(contextPath: '/votingApplication', war: '**/*.war')
                    if (deployed) {
                        echo "========Complete Deploy on Tomcat========"
                    } else {
                        error "Deployment on Test failed"
                    }
                }
            }
        }
        stage("Deploy on Prod") {
            steps {
                script {
                    // Similar deployment steps as the test stage
                    // Modify credentialsId, contextPath, and other parameters accordingly
                    echo "========Complete Deploy on Tomcat Prod========"
                }
            }
        }
    }
    post {
        always {
            echo "========Always========"
        }
        success {
            echo "========Pipeline executed successfully ========"
        }
        failure {
            echo "========Pipeline execution failed========"
        }
    }
}
