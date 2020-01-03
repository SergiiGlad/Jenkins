env.DOCKER_IMAGE_NAME = 'my image name'


node {
  
 // triggers {
//  upstream(upstreamProjects: "test_tag/master", threshold: hudson.model.Result.SUCCESS)
//}

    
    stage('test'){
        
    checkout scm
      
       // These should all be performed at the point where you've
       // checked out your sources on the agent. A 'git' executable
       // must be available.
       // Most typical, if you're not cloning into a sub directory
       shortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim() 
      
       echo "$shortCommit"
       
       echo "${DOCKER_IMAGE_NAME}"
       echo "${JENKINS_SECRET}"
       
       stage('inside') {
           echo "inside test"
       }
        
      


        if  ( env.BRANCH_NAME ==~ /^v\d{1}.\d{1}.\d{1}$/ ) {
              println env.BRANCH_NAME
            return
        }  

        
     def changeLogSets = currentBuild.changeSets
        for (int i = 0; i < changeLogSets.size(); i++) {
           def entries = changeLogSets[i].items
           for (int j = 0; j < entries.length; j++) {
               def files = new ArrayList(entries[j].affectedFiles)
                println "affectedFiles"
                println files[0].path
                println files.path
                             
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
        def onlyOneFile = false
        currentBuild.changeSets.any { changeSet -> 
        if ( changeSet.items.length == 1 ) { onlyOneFile = true }
        changeSet.items.each { entry ->
            
            entry.affectedFiles.each { file -> 
                if (file.path.equals("production-release.txt")) {
                     echo file.path
                     echo 'ONLY Jenkinsfile has changed!!!'    
                }    
            }
         }
        }    
        
        currentBuild.changeSets.any { changeSet ->  
        changeSet.items.each { entry ->
             entry.affectedFiles.each { file -> 
                  if (file.path.equals("Jenkinsfile")) {
                     echo 'Jenkinsfile has changed!!!'
                  }
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
        
        stage('Deploy'){
            //build 'test_tag/master'
          apply from: 'https://raw.githubusercontent.com/SergiiGlad/Jenkins/master/Jenkinsfile.1?token=${env.GITHUB_TOKEN}'
        }
     
        currentBuild.result = 'SUCCESS';  
        echo " ${currentBuild.result} "
        
        return
      
     
     // never echo because return above       
     echo "----END-------"        
        
        
    } // stage
} // node
