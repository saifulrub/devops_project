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
                docker { image 'node:current-alpine3.13'}
            }
            environment {
                    withEnv([
                  /* Override the npm cache directory to avoid: EACCES: permission denied, mkdir '/.npm' */
                    'npm_config_cache=npm-cache',
                    /* set home to our current directory because other bower
                    * nonsense breaks with HOME=/, e.g.:
                    * EACCES: permission denied, mkdir '/.config'
                    */
        
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
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
            
        }

    }
}