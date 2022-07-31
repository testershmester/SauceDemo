pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "3.8.6"
    }

    parameters {
        credentials credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '66a23836-a761-45e9-9fe0-c6be97aab61a', name: 'CREDS', required: false
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
    }

    stages {
        stage('Build') {
            withCredentials([string(credentialsId: '${params.CREDS}', usernameVariable: 'username_var', passwordVariable: 'password_var')]) {
                steps {
                    // Get some code from a GitHub repository
                    git branch: "${params.BRANCH}", url: 'https://github.com/testershmester/SauceDemo.git'

                    // Run Maven on a Unix agent.
                    // sh "mvn clean test -DsuiteXmlFile=src/main/resources/smoke.xml"

                    // To run Maven on a Windows agent, use
                    bat "mvn clean test -DsuiteXmlFile=src/main/resources/smoke.xml"
                }
            }
        }
        stage('Allure') {
            steps {
                script {
                    allure([includeProperties: false,
                            jdk              : '',
                            properties       : [],
                            reportBuildPolicy: 'ALWAYS',
                            results          : [[path: 'target/allure-results']]])
                }
            }
        }
    }
}
