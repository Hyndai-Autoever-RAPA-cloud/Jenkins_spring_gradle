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
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'git clone https://깃헙URL' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'chmod 777 디렉토리/script.sh' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" '디렉토리/script.sh' '''
                    sh '''ssh -i "$SSH_KEY" -p "$EC2_PORT" -o StrictHostKeyChecking=no "${EC2_USER}@${EC2_HOST}" 'rm -rf 디렉토리' '''
                }
            }
        }
    }
}
