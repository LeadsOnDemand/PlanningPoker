node {

    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        try {
            sh './gradlew clean build jacocoTestReport jacocoTestCoverageVerification'
        } catch (err) {
            throw err
        } finally {
            junit '**/build/test-results/test/TEST-*.xml'
            jacoco(execPattern: '**/build/jacoco/jacoco.exec', exclusions: ['**/Test*'])
        }
    }

    if ('develop' == env.BRANCH_NAME || env.BRANCH_NAME.contains('release')) {

        stage('SonarQube Analysis') {
            withSonarQubeEnv('SonarQube') {
                sh './gradlew sonarqube --info'
            }
        }

    }

}

