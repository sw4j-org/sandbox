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
      }
    }
  }
  post {
    always {
      resultMailer()
    }
  }
}
