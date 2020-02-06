package com.sun

class SunDeploy {
    String nameSpace
    String releaseName
    
    SunDeploy(String yamFilePath) {
        this.nameSpace = yamlFilePath.split('/')[0]
        this.releaseName = yamlFilePath.split('/')[1].split(/\./)[0]
    }

    def print() {
        println("k8s_namespace: "+this.nameSpace+
        "\nhelm_release_name: "+this.releaseName)
    }
}

