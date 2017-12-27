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
          sh "mvn clean compile"
        }
      }
    }
    stage('Test') {
      steps {
        withMaven(jdk: 'Current JDK 8',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn test"
        }
      }
    }
    stage('Integration Test') {
      steps {
        withMaven(jdk: 'Current JDK 8',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn verify"
        }
      }
    }
    stage('Artifact Install (for Reports)') {
      steps {
        withMaven(jdk: 'Current JDK 8',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn install"
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
      script {
        if (currentBuild.result == null) {
          currentBuild.result = 'SUCCESS'    
        }
      }
      emailext notifyEveryUnstableBuild: true,
        to: 'ci@sw4j.org',
        recipientProviders: [[$class: 'CulpritsRecipientProvider'],
                             [$class: 'DevelopersRecipientProvider'],
                             [$class: 'FailingTestSuspectsRecipientProvider'],
                             [$class: 'FirstFailingBuildSuspectsRecipientProvider']],
        replyTo: 'ci@sw4j.org',
        subject: '${DEFAULT_SUBJECT}',
        body: '${DEFAULT_CONTENT}',
        mimeType: 'text/plain',
        attachLog: true,
        compressLog: true
    }
  }
}
