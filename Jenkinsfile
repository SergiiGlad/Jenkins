env.DOCKER_IMAGE_NAME = 'my image name'
node {
    
    stage('test'){
        
    checkout scm
       
       echo "${DOCKER_IMAGE_NAME}"
       echo "${JENKINS_SECRET}"
       
       stage('inside') {
           echo "inside test"
       }
        changeLogSets.each { gitChangeSetList ->
        gitChangeSetList.each { gitChangeSet ->
            gitChangeSet.getAffectedPaths().each { path ->
                if(path.tokenize("/").size() > 1) result.put(path.tokenize("/").first(), true)
            }
        }
    }
    }
}
