package com.sun

class Sun implements Serializable {   

    String stageName
    String nameSpace
    String releaseName
    String dockerTag

    
    static String getDirName(String yamlFilePath) {
        return yamlFilePath.split('/')[0]
    }
    
    static String getReleaseName(String yamlFilePath) {
        return yamlFilePath.split('/')[1].split(/\./)[0]
    }
    

     Sun(String yamlFilePath) {
        stageName = yamlFilePath
    } 
    
    def initDeploy(String yamlFilePath, String tag) {
        
        nameSpace = getDirName( yamlFilePath )
        releaseName = getReleaseName( yamlFilePath )
        dockerTag = tag
        println("init Deploy")
    }
    

    def print() {
        return stageName+
        "\nk8s_namespace: "+this.nameSpace+
        "\nhelm_release_name: "+this.releaseName+
        "\ndockerTag: "+dockerTag
    }
    
    def deployStage(script) {
        if ( dockerTag ) {
           
               script{ 
                container('helm') {
                withKubeConfig([credentialsId: 'kubeconfig']) {
                        output()    
                }
               }    
       
            }    
            
        }
    }
    
 
}

