pipeline{
    agent any
    tools{
        maven'Maven'
    }
    stages{
        stage("test"){
            steps{
                bat'mvn --version'
                echo "========Geeting Maven Version========"
            }
           
        }
        stage("Build"){
            steps{
                bat'mvn package'
                echo "========Creating Build Successfully ========"
            }
           
        }
        stage("Deploy on test"){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'adminserver1', path: '', url: 'http://localhost:9002/')], contextPath: '/votingApplication', war: '**/*.war'
                echo "========Complete Depoly On Tomcat========"
            }
           
        }
        stage("Deploy on prod"){
            steps{
                //  deploy adapters: [tomcat8(credentialsId: 'adminserver1', path: '', url: 'http://localhost:9002/')], contextPath: '/app', war: '**/*.war'
                echo "========Complete Depoly On Tomcat Prod========"
            }
           
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}
