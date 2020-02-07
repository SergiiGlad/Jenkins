package com.sun

class SunStage extends SunDeploy {
    String stageName


    SunStage(String yamlFilePath) {
       
       stageName = yamlFilePath
    } 
    
    def print() {
        println stageName
        super.print()
        println()
    }
    
    def deployStage() {
        if ( super.dockerTag ) helmDeploy()
    }
    
    def helmDeploy() {
        print()
    }
}