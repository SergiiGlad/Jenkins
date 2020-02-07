package com.sun

class Sun {   

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
    
    static String getTagFromYaml(){
        def values = readYaml 'file': stageName
        dockerTag = values.image.tag
    }

     Sun(String yamlFilePath) {
        stageName = yamlFilePath
    } 
    
    def initDeploy(String yamlFilePath) {
        
        nameSpace = getDirName( yamlFilePath )
        releaseName = getReleaseName( yamlFilePath )
        
        initTag()
    }

    def setDockerTag(String tag) {
        dockerTag = tag
    }
    
    def initTag() {
        if ( nameSpace != 'develop' || nameSpace == 'qa' ) 
            dockerTag = getTagFromYaml()
    }
    
    def print() {
        println(stageName)
        println("k8s_namespace: "+this.nameSpace+
        "\nhelm_release_name: "+this.releaseName+
        "\ndockerTag: "+dockerTag)
    }
    
    def deployStage() {
        if ( dockerTag ) helmDeploy()
    }
    
    def helmDeploy() {
        print()
    }
}

