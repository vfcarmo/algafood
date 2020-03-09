node {
    stage 'Clone the project'
    git 'https://github.com/vfcarmo/algafood-api.git'

    dir('algafood-api') {
        stage("Compilation and Analysis") {
            parallel 'Compilation': {
                sh "./gradlew clean install -x test"
            }, 'Static Analysis': {
                stage("Checkstyle") {
                    sh "./gradlew checkstyle"

                    step([$class: 'CheckStylePublisher',
                          canRunOnFailed: true,
                          defaultEncoding: '',
                          healthy: '100',
                          pattern: '**/build/checkstyle-result.xml',
                          unHealthy: '90',
                          useStableBuildAsReference: true
                    ])
                }
            }
        }

        stage("Test and Deployment") {
            parallel 'Unit tests': {
                stage("Runing unit tests") {
                    try {
                        sh "./gradlew test -Punit"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults:
                          '**/build/surefire-reports/TEST-*UnitTest.xml'])
                        throw err
                    }
                    step([$class: 'JUnitResultArchiver', testResults:
                        '**/target/surefire-reports/TEST-*UnitTest.xml'])
                }
            }, 'Integration tests': {
                stage("Runing integration tests") {
                    try {
                        sh "./gradlew test -Pintegration"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults:
                          '**/build/surefire-reports/TEST-*IntegrationTest.xml'])
                        throw err
                    }
                    step([$class: 'JUnitResultArchiver', testResults:
                        '**/build/surefire-reports/TEST-*IntegrationTest.xml'])
                }
            }

            stage("Staging") {
                sh "pid=\$(lsof -i:8080 -t); kill -TERM \$pid || kill -KILL \$pid"
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'nohup ./gradlew spring-boot:run -Dserver.port=8080 &'
                }
            }
        }
    }
}