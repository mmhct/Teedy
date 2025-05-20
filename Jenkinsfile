pipeline {
agent any
environment {
// define environment variable
// Jenkins credentials configuration
DOCKER_HUB_CREDENTIALS = credentials('dockerhub_credentials') // Docker Hub credentials ID store in Jenkins
// Docker Hub Repository's name
DOCKER_IMAGE = 'mmhct1/teedy' // your Docker Hub user name and Repository's name
DOCKER_TAG = "${env.BUILD_NUMBER}" // use build number as tag
}
stages {
stage('Start Minikube') {
steps {
sh '''
if ! minikube status | grep -q "Running"; then
echo "Starting Minikube..."
minikube start --force
else
echo "Minikube already running."
fi
'''
}
}
stage('Set Image') {
steps {
sh '''
echo "Setting image for deployment..."
'''
}
}
stage('Verify') {
steps {
sh 'echo "kubectl get pods"'
}
}
}
}
