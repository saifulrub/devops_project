pipeline{
    agent any

      node {
        def commit_id
        stage('Checkout SCM'){
            steps{
                checkout scm
                sh "git rev-parse --short HEAD > .git/commit-id"
                commit_id = readfile('.git/commit-id').trim()
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
                sh 'npm install --only=dev'
                sh 'npm build'
                
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
