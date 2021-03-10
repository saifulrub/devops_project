pipeline{
    agent any

    stages{
        stage('Checkout SCM'){
            steps{
                checkout scm
            }
        }
        stage('Build FrontEnd'){
            agent {
                docker { image 'node:ubuntu'}
            }
            environment {
                   HOME = '.'
            }
            steps{
                sh 'sudo npm install'
                sh 'sudo npm build'
                
            }

        }
        stage('Build BackEnd'){
            agent{
                    docker {image 'maven:3.6.3-adoptopenjdk-8'}
                }
            steps{
                sh 'mvn package'
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
            
        }

    }
}
