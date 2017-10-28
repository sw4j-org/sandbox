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
  }
}
