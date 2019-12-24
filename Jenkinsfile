env.DOCKER_IMAGE_NAME = 'my image name'
node {
    
    stage('test'){
        
    checkout scm
       
       echo "${DOCKER_IMAGE_NAME}"
       echo "${JENKINS_SECRET}"
       
       stage('inside') {
           echo "inside test"
       }
     def changeLogSets = currentBuild.changeSets
           
        for (int i = 0; i < changeLogSets.size(); i++) {
           def entries = changeLogSets[i].items
           for (int j = 0; j < entries.length; j++) {
               def files = new ArrayList(entries[j].affectedFiles)
                println "affectedFiles"
                println files[0].path
                
                             
               for (int k = 0; k < files.size(); k++) {
                   def file = files[k]
                   println k
                   println file
                   if (file.path.equals("Jenkinsfile")  ) {
                       
                       echo "Only Jenkinsfile changed"
                   }
               }
            }
    }
        
        changeLogSets.items.each { entry ->
             entry.affectedFiles.any { file -> 
                  if (file.path.equals("Jenkinsfile")) {
                     echo 'Jenkinsfile has changed!!!'
                  }
             }        
         }
        

        
        changeLogSets.each { gitChangeSetList ->
        gitChangeSetList.each { gitChangeSet ->
            gitChangeSet.getAffectedPaths().each { path ->
                if(path.tokenize("/").size() > 1) result.put(path.tokenize("/").first(), true)
            }
        }
    }
        
        currentBuild.changeSets.any { changeSet -> 
          changeSet.items.any { entry -> 
            entry.affectedFiles.any { file -> 
              if (file.path.equals("Jenkinsfile")) {
                 echo 'Jenkinsfile has changed'
              }
            }
          }
        }
     
        currentBuild.result = 'SUCCESS';  
        echo " ${currentBuild.result} "
        
        return
      
     
     // never echo because return above       
     echo "----END-------"        
        
        
    } // stage
} // node
