pipeline {
 agent any
 
 environment {
 // define environment variable
// Jenkins credentials configuration
 DOCKER_HUB_CREDENTIALS = credentials('mmhct') // Docker Hub credentials ID store in Jenkins
 // Docker Hub Repository's name
DOCKER_IMAGE = 'mmhct1/teedy' // your Docker Hub user name and Repository's name
 DOCKER_TAG = "${env.BUILD_NUMBER}" // use build number as tag
 }
 
 stages {
 stage('Build') {
 steps {
 checkout scmGit(
 branches: [[name: '*/master']], 
 extensions: [], 
 userRemoteConfigs: [[url: 'https://github.com/mmhct/Teedy.git']]
// your github Repository
 )
 sh 'mvn -B -DskipTests clean package'
 }
 }
 
 // Building Docker images
 stage('Building image') {
 steps {
 script {
 // assume Dockerfile locate at root 
 docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
 }
 }
 }
 
 // Uploading Docker images into Docker Hub
 stage('Upload image') {
 steps {
 script {
 // sign in Docker Hub
 docker.withRegistry('https://registry.hub.docker.com',
'mmhct') {
 // push image
docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
 
// ：optional: label latest
docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
 }
 }
}
 }
 
 // Running Docker container
 stage('Run containers') {
    steps {
        script {
            // Define ports and container names
            def ports = [8082:8080, 8083:8080, 8084:8080]
            
            ports.each { hostPort, containerPort ->
                def containerName = "teedy-container-${hostPort}"
                
                // Stop and remove existing containers if they exist
                sh "docker stop ${containerName} || true"
                sh "docker rm ${containerName} || true"
                
                // Run new container
                docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run(
                    "--name ${containerName} -d -p ${hostPort}:${containerPort}"
                )
            }
            
            // Optional: list all teedy-containers
            sh 'docker ps --filter "name=teedy-container"'
        }
    }
}
 }
}
