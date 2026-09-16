pipeline {
    agent any

    stages {
        stage('ssh') {
            steps {
                echo 'SSH'
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'ec2-ssh-key',
                        keyFileVariable: 'SSH_KEY',
                        usernameVariable: 'EC2_USER'
                    ),
                    string(credentialsId: 'ec2-host', variable: 'EC2_HOST'),
                    string(credentialsId: 'ec2-port', variable: 'EC2_PORT')
                ]) {
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'git clone https://github.com/Hyndai-Autoever-RAPA-cloud/Jenkins_spring_gradle.git' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'chmod 777 Jenkins_spring_gradle/script.sh' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'Jenkins_spring_gradle/script.sh' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'rm -rf Jenkins_spring_gradle' '''
                }
            }
        }
    }
}
