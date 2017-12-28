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
        withMaven(jdk: 'Current JDK 8',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn clean install"
        }
      }
    }
    stage('Deploy') {
      when {
        environment name: 'CHANGE_FORK', value: ''
        expression { GIT_URL ==~ 'https://github.com/sw4j-org/.*' }
      }
      steps {
        echo 'Deploy the artifact'
      }
    }
  }
  post {
    always {
      step([$class: 'Mailer',
            notifyEveryUnstableBuild: true,
            recipients: emailextrecipients([[$class: 'CulpritsRecipientProvider'],
                                            [$class: 'DevelopersRecipientProvider'],
                                            [$class: 'FailingTestSuspectsRecipientProvider'],
                                            [$class: 'FirstFailingBuildSuspectsRecipientProvider']])
      ])
    }
  }
}
