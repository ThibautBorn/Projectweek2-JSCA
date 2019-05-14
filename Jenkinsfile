pipeline {
    agent {
        label 'projectweek' 
    }

    environment {
        PROJECT_NAME = 'competenties-verplichtaanwezig'
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn -B package -DskipTests'
            }
        }

        stage('test'){
            steps {
                sh 'rm -rf /usr/local/tomcat/webapps/ROOT'
                sh 'cp -r target/*.war /usr/local/tomcat/webapps/ROOT.war'
                sh '/usr/local/tomcat/bin/startup.sh'
                sh 'mvn -B clean verify'
            }
        }

        stage('deploy') {
            steps {
                sshagent(credentials: ['b937ad75-8f46-4abb-b138-380d63028115']) {
                    sh "scp -P 22345 -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null target/*.war build@tomcat.projectweek.be:/opt/tomcat/teams/${env.PROJECT_NAME}/ROOT.war"
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
