pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        deleteDir()
      }
    }
    stage('Checkout') {
      steps {
        checkout scm
        sh """
          git submodule init
          git submodule update
          printenv
        """
      }
    }
    stage('Build') {
      steps {
        buildStep()
        doCheckstyle()
      }
    }
    stage('Deploy') {
      // run this stage only when on master in the original repository and build is successful
      when {
        environment name: 'CHANGE_FORK', value: ''
        expression { GIT_URL ==~ 'https://github.com/sw4j-org/.*' }
        expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
      }
      steps {
        echo 'Deploy the artifact'
        sshagent(['4cab8b17-578f-49fc-908b-0e318625d63b']) {
          withMaven(jdk: 'Current JDK 8',
                  maven: 'Current Maven 3',
                  mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/',
                  globalMavenSettingsConfig: '03c863c2-c19c-4ed5-bc3a-7650b8f73ecf') {
            sh "mvn post-site scm-publish:publish-scm"
          }
        }
      }
    }
  }
  post {
    always {
      resultMailer()
    }
  }
}
