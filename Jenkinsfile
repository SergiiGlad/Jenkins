env.DOCKER_IMAGE_NAME = 'my image name'
node {
    
    stage('test'){
        
    checkout scm
       
       echo "${DOCKER_IMAGE_NAME}"
       echo "${JENKINS_SECRET}"
       
       stage('inside') {
           echo "inside test"
       }
    }
}
