package com.sun

class SunDeploy {    
    
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
        return "1"
    }
    
    def initSunDeploy(String yamlFilePath) {
        
        nameSpace = getDirName( yamlFilePath )
        releaseName = getReleaseName( yamlFilePath )
        
        initTag()
    }
    
    def initTag() {
        if ( nameSpace == 'develop' ) dockerTag='dev'
        else if ( nameSpace == 'qa') dockerTag='qa'
        else dockerTag = getTagFromYaml()
    }
    
    def print() {
        println("k8s_namespace: "+this.nameSpace+
        "\nhelm_release_name: "+this.releaseName+
        "\ndockerTag: "+dockerTag)
    }
}

