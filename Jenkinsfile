pipline {
 
    agent any
 
    stages{
       
       stage('Checkout SCM'){
          steps{
              checkout scm
                
          }
        }
        stage('Build FrontEnd'){
            agent {
                docker { image 'node:current-alpine3.13'}
            }
            environment {
                   HOME = '.'
            }
            steps{
                sh 'npm install'
                sh 'npm build'
                
            }

        }
        stage('Build BackEnd'){
            agent{
                    docker {image 'maven:3.6.3-adoptopenjdk-8'}
                }
            steps{
                sh 'mvn package'
                
            }
            
        }

    }
}
