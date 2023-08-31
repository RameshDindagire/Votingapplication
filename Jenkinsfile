pipeline{
    agent any
    tools{
        maven'Maven'
    }
    stages{
        stage("test"){
            steps{
                bat'mvn --version'
                echo "========executing A========"
            }
           
        }
        stage("Build"){
            steps{
                bat'mvn package'
                echo "========executing A========"
            }
           
        }
        stage("Deploy on test"){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'adminserver1', path: '', url: 'http://localhost:9002/')], contextPath: '/VotingApplication1', war: '**/*.war'
                echo "========executing A========"
            }
           
        }
        stage("Deploy on prod"){
            steps{
                //  deploy adapters: [tomcat8(credentialsId: 'adminserver1', path: '', url: 'http://localhost:9002/')], contextPath: '/ReadJsonData', war: '**/*.war'
                echo "========executing A========"
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