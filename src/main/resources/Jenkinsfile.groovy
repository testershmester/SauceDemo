pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "3.8.6"
    }

    properties([parameters([gitParameter(branch: '', branchFilter: '.*', defaultValue: 'master', name: 'branch', type: 'GitParameterDefinition')])])

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'jenkins_creds_pipeline', url: 'https://github.com/testershmester/SauceDemo.git'

                // Run Maven on a Unix agent.
                // sh "mvn clean test -DsuiteXmlFile=src/main/resources/smoke.xml"

                // To run Maven on a Windows agent, use
                bat "mvn clean test -DsuiteXmlFile=src/main/resources/smoke.xml"
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